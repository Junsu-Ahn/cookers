package com.example.cookers.domain.comment.controller;

import com.example.cookers.domain.comment.entity.Comment;
import com.example.cookers.domain.comment.service.CommentService;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.service.MemberService;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;
    private final MemberService memberService;

    private final RecipeService recipeService;

    @PostMapping("/create/{id}")
    public String createAnswer(Model model, @PathVariable("id") Long id, @RequestParam(value="content") String content) {
        Recipe recipe = this.recipeService.getRecipe(id);
        commentService.create(recipe, content);
        return String.format("redirect:/recipe/detail/%s", id);
    }
}