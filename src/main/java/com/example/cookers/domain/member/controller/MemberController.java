package com.example.cookers.domain.member.controller;


import com.example.cookers.domain.member.service.MemberService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;

    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String loginPage() {
        return "member/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignForm signForm) {
        memberService.signup(signForm.getUsername(), signForm.getPassword(), signForm.getNickname(), signForm.getEmail(), signForm.getTypeCode(), signForm.getProfile_url());
        return "redirect:/member/login";
    }

    @PostMapping("/signup/google")
    public String signupGoogle(@Valid GoogleSignForm signForm) {
        memberService.signupGoogle(signForm.getUsername(), signForm.getNickname(), signForm.getEmail());
        return "redirect:/member/login"; // 회원가입 후 메인 페이지로 이동하도록 수정
    }

    @ToString
    @Getter
    @Setter
    public static class SignForm {
        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String password_confirm;

        @NotBlank
        private String nickname;

        @NotBlank
        private String email;

        private String TypeCode;

        private String profile_url;
    }

    @ToString
    @Getter
    @Setter
    public static class GoogleSignForm {
        @NotBlank
        private String username;

        @NotBlank
        private String nickname;

        @NotBlank
        private String email;

        private String profileUrl;
    }

}