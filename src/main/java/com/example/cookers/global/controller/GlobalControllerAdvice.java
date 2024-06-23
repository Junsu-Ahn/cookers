package com.example.cookers.global.controller;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.security.Principal;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    private final MemberService memberService;

    @ModelAttribute
    public void addAttributes(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            Member currentMember = memberService.findByUsername(username)
                    .orElse(null);
            model.addAttribute("currentMember", currentMember);
        }
    }
}