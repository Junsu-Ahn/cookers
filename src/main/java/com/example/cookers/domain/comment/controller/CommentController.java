package com.example.cookers.domain.comment.controller;

import com.example.cookers.domain.comment.dto.CommentForm;
import com.example.cookers.domain.comment.entity.Comment;
import com.example.cookers.domain.comment.service.CommentService;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.service.MemberService;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.service.RecipeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {
    private static final Logger logger = LoggerFactory.getLogger(CommentController.class);

    private final CommentService commentService;
    private final MemberService memberService;

    private final RecipeService recipeService;

    // 댓글 작성하기
    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create/{id}")
    public String createComment(Model model, @PathVariable("id") Long id, @Valid CommentForm commentForm, @RequestParam(value="content") String content, BindingResult bindingResult , Principal principal) {
        Recipe recipe = this.recipeService.getRecipe(id);

        Member member = memberService.getMember(principal.getName());
        if (bindingResult.hasErrors()) {
            model.addAttribute("recipe", recipe);
            return "recipe_detail";
        }
        Comment comment = this.commentService.create(recipe,
                member, commentForm.getContent());
        return String.format("redirect:/recipe/detail/%s#comment_%s",
                comment.getRecipe().getId(), comment.getId());
    }

     // 댓글 삭제하기
     @PreAuthorize("isAuthenticated()")
     @GetMapping("/delete/{id}")
     public String commentDelete(Principal principal, @PathVariable("id") Long id) {
         Comment comment = this.commentService.getComment(id);
         if (!comment.getAuthor().getUsername().equals(principal.getName())) {
             throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "삭제권한이 없습니다.");
         }
         this.commentService.delete(comment);
         return String.format("redirect:/recipe/detail/%s", comment.getRecipe().getId());
     }
}