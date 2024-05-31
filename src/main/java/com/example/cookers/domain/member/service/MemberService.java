package com.example.cookers.domain.member.service;

import com.example.cookers.DataNotFoundException;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.DataException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.zip.DataFormatException;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public Member signup(String username, String password, String nickname, String email, String typeCode, String url){
        Member member = Member
                .builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .profile_url(url)
                .nickname(nickname)
                .email(email)
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    public Member whenSocialLogin(String providerTypeCode, String username, String nickname, String profileImageUrl) {
        Optional<Member> opMember = findByUsernameAndProviderTypeCode(username, providerTypeCode);

        if (opMember.isPresent()) return opMember.get();

        // 소셜 로그인를 통한 가입시 비번은 없다.
        return signup(username, "", nickname, "", providerTypeCode, profileImageUrl); // 최초 로그인 시 딱 한번 실행
    }

    public Optional<Member> findByUsername(String username) {
        return memberRepository.findByusername(username);
    }
    public Optional<Member> findByUsernameAndProviderTypeCode(String username, String providerTypeCode) {
        return memberRepository.findByUsernameAndProviderTypeCode(username, providerTypeCode);
    }

    // (닉네임 가져오기) 필선 작성
    public Member getMember(String name) {
        Optional<Member> member = this.memberRepository.findByusername(name);
        if (member.isPresent()){
            return member.get();
        } else{
            throw new DataNotFoundException("Member not found");
        }
    }
}