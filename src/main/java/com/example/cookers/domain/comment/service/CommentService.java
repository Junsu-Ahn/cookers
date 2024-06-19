package com.example.cookers.domain.comment.service;

import com.example.cookers.domain.comment.entity.Comment;
import com.example.cookers.domain.comment.repository.CommentRepository;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecipeService recipeService;

    public void create(Recipe recipe, String content) {
        Comment comment= new Comment();
        comment.setContent(content);
        comment.setCreateDate(LocalDateTime.now());
        comment.setRecipe(recipe);
        this.commentRepository.save(comment);
    }

    // 댓글 삭제하기
    public void delete(Comment comment) {
        this.commentRepository.delete(comment);
    }


}