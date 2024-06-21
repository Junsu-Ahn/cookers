package com.example.cookers.domain.comment.service;

import com.example.cookers.domain.comment.entity.Comment;
import com.example.cookers.domain.comment.repository.CommentRepository;
import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.service.RecipeService;
import com.example.cookers.global.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final RecipeService recipeService;

    public void create(Recipe recipe,Member member, String content) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setAuthor(member);
        comment.setCreateDate(LocalDateTime.now());
        comment.setRecipe(recipe);
        this.commentRepository.save(comment);
    }

    public Comment getComment(Long id) {
        Optional<Comment> comment = this.commentRepository.findById(id);
        if (comment.isPresent()) {
            return comment.get();
        } else {
            throw new DataNotFoundException("comment not found");
        }
    }
    // 댓글 삭제하기
    public void delete(Comment comment) {
        this.commentRepository.delete(comment);
    }


}