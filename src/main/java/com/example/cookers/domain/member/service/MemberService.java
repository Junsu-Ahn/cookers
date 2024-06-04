package com.example.cookers.domain.member.service;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

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
                .provider(typeCode)
                .build();

        return memberRepository.save(member);
    }

    @Transactional
    public Member whenSocialLogin(String providerTypeCode, String username, String nickname, String profileImageUrl) {
        Optional<Member> opMember = findByUsername(username);

        if (opMember.isPresent()) return opMember.get();

        // 소셜 로그인를 통한 가입시 비번은 없다.
        return signup(username, "", nickname, "", providerTypeCode, profileImageUrl); // 최초 로그인 시 딱 한번 실행
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
        return memberRepository.findByusername(username);
    }
    public Optional<Member> findByUsernameAndProviderTypeCode(String username, String providerTypeCode) {
        return memberRepository.findByUsernameAndProviderTypeCode(username, providerTypeCode);
    }
    public Optional<Member> findById(long id)
    {
        return memberRepository.findById(id);
    }
}