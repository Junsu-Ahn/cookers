package com.example.cookers.domain.ranking.service;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.repository.MemberRepository;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.repository.RecipeRecommendationRepository;
import com.example.cookers.domain.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final MemberRepository memberRepository;
    private final RecipeRepository recipeRepository;
    private final RecipeRecommendationRepository recipeRecommendationRepository;

    //추가
    public List<Recipe> getRecipesByNickname(String nickname) {
        return recipeRepository.findByAuthorNickname(nickname);
    }

    public Member getMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다: " + nickname));
    }
    // 여기까지

    public Page<Member> getRankedMembers(Pageable pageable) {
        List<Member> allMembers = memberRepository.findAll()
                .stream()
                .filter(member -> member.getId() != 1) // 관리자를 제외
                .sorted((m1, m2) -> Long.compare(
                        Optional.ofNullable(recipeRepository.sumHitsByAuthor(m2.getId())).orElse(0L),
                        Optional.ofNullable(recipeRepository.sumHitsByAuthor(m1.getId())).orElse(0L)))
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allMembers.size());
        List<Member> paginatedMembers = allMembers.subList(start, end);

        return new PageImpl<>(paginatedMembers, pageable, allMembers.size());
    }

    public Long getTotalRecommendations() {
        return Optional.ofNullable(recipeRepository.sumHitsByAuthor(null)).orElse(0L); // 전체 추천수는 모든 레시피의 hit 합계로 대체
    }

    public Map<Long, Long> getMemberRecommendations() {
        List<Member> allMembers = memberRepository.findAll();
        Map<Long, Long> memberRecommendations = new HashMap<>();
        for (Member member : allMembers) {
            Long recommendations = Optional.ofNullable(recipeRepository.sumHitsByAuthor(member.getId())).orElse(0L);
            memberRecommendations.put(member.getId(), recommendations);
        }
        return memberRecommendations;
    }

}
