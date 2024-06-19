package com.example.cookers.domain.member.controller;


import com.example.cookers.domain.Email.EmailService;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.repository.MemberRepository;
import com.example.cookers.domain.member.service.MemberService;
import com.example.cookers.domain.recipe.service.RecipeService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {


    private final RecipeService recipeService;
    private final MemberService memberService;
    private final EmailService emailService;
    private final MemberRepository memberRepository;

    @Autowired
    private AuthenticationManager authenticationManager;



    @Data
    public static class LoginRequest {
        @NotBlank
        private String username;

        @NotBlank
        private String password;
    }


    @ControllerAdvice
    @RequiredArgsConstructor
    public class GlobalControllerAdvice {
        private final MemberRepository memberRepository;
        @ModelAttribute
        public void addAttributes(Model model, Principal principal) {
            if (principal != null) {
                String username = principal.getName();
                Optional<Member> memberOptional = memberRepository.findByUsername(username);
                if (memberOptional.isPresent()) {
                    Member member = memberOptional.get();
                    String profileImageUrl = member.getProfile_url();
                    model.addAttribute("profileImageUrl", profileImageUrl);
                }
            }
        }
    }
    @PreAuthorize("isAnonymous()")
    @GetMapping("/login")
    public String loginPage() {

        return "member/login";
    }



    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional<Member> optionalMember = memberRepository.findByUsername(loginRequest.getUsername());
        Member member = optionalMember.get();
        // 사용자의 Role 정보를 가져옴 (여기서는 Enum으로 가정)
        String role = member.getRole().toString();

        // Role 정보를 Spring Security의 SecurityContext에 등록
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), null, AuthorityUtils.createAuthorityList(role));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        checkUserAuthorities(authentication);

        return ResponseEntity.ok("로그인 성공. 사용자의 Role: " + role);
    }

    private void checkUserAuthorities(Authentication authentication) {
        if (authentication != null && authentication.isAuthenticated()) {
            // 사용자가 가진 모든 권한을 가져오기
            for (GrantedAuthority authority : authentication.getAuthorities()) {
                System.out.println("사용자 권한: " + authority.getAuthority());
            }
            // 여기에 추가적인 작업을 수행할 수 있음
        }
    }


    @GetMapping("/signup")
    public String signupPage() {
        return "member/signup";
    }

    @PostMapping("/signup")
    public String signup(@Valid SignForm signForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "member/signup";
        }
        try {
            memberService.signup(
                    signForm.getProviderTypeCode(),
                    signForm.getUsername(),
                    signForm.getPassword(),
                    signForm.getPassword_confirm(),
                    signForm.getNickname(),
                    signForm.getEmail(),
                    0L,
                    signForm.getProfile_url()
            );
        } catch (DataIntegrityViolationException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "member/signup";
        }
        return "redirect:/member/login";
    }

    @GetMapping("/findId")
    public String find_id() {
        return "member/findId";
    }

    @PostMapping("/findId")
    public String find_id2(@RequestParam("email") String email, Model model) {
        List<Member> members = memberService.findByUserEmail(email);
        if(members.isEmpty())  // 멤버를 찾을 수 없는 경우 처리
        { model.addAttribute("error", "입력하신 이메일로 등록된 계정이 없습니다.");
            return "member/findId";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>");
        sb.append("<h2>당신의 오내요 아이디는 다음과 같습니다:</h2>");
        sb.append("<ul>");

        // 멤버들의 아이디를 StringBuilder에 추가
        for (Member member : members) {
            sb.append("<li>").append(member.getUsername()).append("</li>");
        }

        sb.append("</ul>");
        sb.append("</body></html>");

        // 이메일 발송
        emailService.sendHtml(email, "당신의 오내요 아이디 입니다!", sb.toString());

        return "redirect:/member/login";
    }


    @GetMapping("find_pw")
    public String find_pw() {
        return "member/find_pw";
    }

    @PostMapping("/find_pw")
    public String find_pw2() {
        return "redirect:/member/find_pw";
    }

    @GetMapping("/admin/memberList")
    @PreAuthorize("hasRole('ADMIN')")
    public String memberList(Model model) {
        List<Member> members = memberService.getAllMembers();
        for (Member member : members) {
            Long totalHits = memberService.calculateTotalHitsForMember(member.getId());
            member.setHit(totalHits); // Member 엔티티의 hit 필드에 총 조회수 설정
        }
        model.addAttribute("members", members);
        return "admin/memberList";
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

        private Long hit;

        private String profile_url;

        private String providerTypeCode;

        private String role; // 권한 필드 추가
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

    // 필선 작성
    @GetMapping("/profile")
    public String userProfile(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/member/login";
        }
        String username = principal.getName();
        Member member = memberService.getMemberByUsername(username);
        long recipeCount = recipeService.countUserRecipes(username);
        long totalHits = recipeService.sumUserRecipeHits(username);
        model.addAttribute("member", member);
        model.addAttribute("recipeCount", recipeCount);
        model.addAttribute("totalHits", totalHits);
        return "member/profile";
    }

}