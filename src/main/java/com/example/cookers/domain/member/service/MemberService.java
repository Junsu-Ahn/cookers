package com.example.cookers.domain.member.service;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.repository.MemberRepository;
import com.example.cookers.domain.recipe.repository.RecipeRepository;
import com.example.cookers.global.DataNotFoundException;
import com.example.cookers.global.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RecipeRepository recipeRepository;

    public Member signup(String providerTypeCode, String username, String password, String passwordConfirm, String nickname, String email, Long hit, String url) {
        if (!password.equals(passwordConfirm)) {
            throw new PasswordMismatchException("비밀번호와 비밀번호 확인이 일치하지 않습니다.");
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
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    public Member whenSocialLogin(String providerTypeCode, String username, String nickname, String profileImageUrl, String email) {
        Optional<Member> opMember = findByUsernameAndProviderTypeCode(username, providerTypeCode);

        if (opMember.isPresent()) return opMember.get();

        // 소셜 로그인를 통한 가입시 비번은 없다.
        return signup(providerTypeCode, username, "", "", nickname, email, 0L, profileImageUrl); // 최초 로그인 시 딱 한번 실행
    }

    @Transactional
    public Member signupGoogle(String username, String nickname, String email) {
        // 구글 로그인으로부터 받은 정보로 Member 객체 생성
        Member member = Member.builder()
                .username(username)
                .password("") // 구글 로그인으로부터 받은 정보만으로 회원가입이 이루어지므로 비밀번호는 필요하지 않음
                .nickname(nickname)
                .email(email)
                .build();

        // 회원 저장
        return memberRepository.save(member);
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }

    public Optional<Member> findByUsernameAndProviderTypeCode(String username, String providerTypeCode) {
        return memberRepository.findByUsernameAndProviderTypeCode(username, providerTypeCode);
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
}