package com.example.cookers.domain.member.service;

import com.example.cookers.domain.Email.EmailService;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.entity.Role;
import com.example.cookers.domain.member.repository.MemberRepository;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.entity.RecipeRecommendation;
import com.example.cookers.domain.recipe.repository.RecipeRepository;
import com.example.cookers.global.DataNotFoundException;
import com.example.cookers.global.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;


    public Member signup(String providerTypeCode, String username, String password, String passwordConfirm, String nickname, String email, Long hit, String url) {

        if (!password.equals(passwordConfirm)) {
            throw new PasswordMismatchException("비밀번호가 서로 다릅니다.");
        }

        if (memberRepository.existsByUsername(username)) {
            throw new DataIntegrityViolationException("이미 존재하는 아이디입니다.");
        }

        // 중복 닉네임 확인
        if (memberRepository.existsByNickname(nickname)) {
            throw new DataIntegrityViolationException("이미 존재하는 닉네임입니다.");
        }

        Member member = Member
                .builder()
                .providerTypeCode(providerTypeCode)
                .username(username)
                .password(passwordEncoder.encode(password))
                .profile_url(url)
                .nickname(nickname)
                .email(email)
                .hit(hit)
                .role(Role.ROLE_USER)  // 기본적으로 USER 권한을 부여
                .build();


        // emailService.send(email, "오내요 회원가입을 환영합니다!", "오내요 회원가입이 정상적으로 완료되었습니다^^~!");
        return memberRepository.save(member);
    }

    @Transactional
    public Member createAdmin(String username, String password) {
        if (memberRepository.existsByUsername(username)) {
            throw new DataIntegrityViolationException("이미 존재하는 아이디입니다.");
        }

        Member admin = Member.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .role(Role.ROLE_ADMIN)
                .build();

        return memberRepository.save(admin);
    }

    @Transactional
    public Member whenSocialLogin(String providerTypeCode, String username, String nickname, String profileImageUrl, String email) {
        Optional<Member> opMember = findByUsernameAndProviderTypeCode(username, providerTypeCode);

        if (opMember.isPresent()) return opMember.get();

        // 소셜 로그인를 통한 가입시 비번은 없다.
        return signup(providerTypeCode, username,  "", "",nickname, email, 0L, profileImageUrl); // 최초 로그인 시 딱 한번 실행
    }

    @Transactional
    public void deleteMemberByAdmin(String username) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("해당 회원을 찾을 수 없습니다. ID: " + username));
        memberRepository.delete(member);
    }


    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
    public Optional<Member> findByUsernameAndProviderTypeCode(String username, String providerTypeCode) {
        return memberRepository.findByUsernameAndProviderTypeCode(username, providerTypeCode);
    }
    public List<Member> findByUserEmail(String email) {
        return memberRepository.findByemail(email);
    }
    public List<Member> getAllMembers() {
        List<Member> members = memberRepository.findAll();
        return members != null ? members : Collections.emptyList();
    }

    public boolean authenticateMember(String username, String password) {
        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return passwordEncoder.matches(password, member.getPassword());
        }
        return false;
    }

    public Member getMember(String username) {
        Optional<Member> member = this.memberRepository.findByUsername(username);
        if (member.isPresent()) {
            return member.get();
        } else {
            throw new DataNotFoundException("member not found");
        }
    }

    public Member getMemberByUsername(String username) {
        return memberRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Member not found"));
    }


    @Transactional(readOnly = true)
    public Long calculateTotalHitsForMember(Long memberId) {
        Member member = memberRepository.findById(memberId).orElse(null);
        if (member == null) {
            return 0L;
        }

        Set<RecipeRecommendation> recipeRecommendations = member.getRecipeRecommendations();
        Long totalHits = 0L;
        for (RecipeRecommendation recommendation : recipeRecommendations) {
            Recipe recipe = recommendation.getRecipe();
            if (recipe != null) {
                totalHits += recipe.getHit();
            }
        }
        return totalHits;
    }

    public void save(Member member) {
        memberRepository.save(member);
    }

    public void delete(Member member) {memberRepository.delete(member);}

    // 추가//
    @Transactional
    public Member updateMember(String username, String nickname, String email, String profile_url) {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("회원을 찾을 수 없습니다."));
        member.setNickname(nickname);
        member.setEmail(email);
        member.setProfile_url(profile_url);
        return memberRepository.save(member);
    }

    public Optional<Member> findByusername(String username) {
        return memberRepository.findByUsername(username);
    }

    public void deleteMember(String username) {
        Optional<Member> memberOptional = memberRepository.findByUsername(username);
        if (memberOptional.isPresent()) {
            memberRepository.delete(memberOptional.get());
        } else {
            throw new IllegalArgumentException("회원을 찾을 수 없습니다.");
        }
    }
    //여기까지
}