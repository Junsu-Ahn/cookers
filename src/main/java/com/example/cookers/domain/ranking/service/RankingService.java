package com.example.cookers.domain.ranking.service;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.repository.MemberRepository;
import com.example.cookers.domain.recipe.entity.Recipe;
import com.example.cookers.domain.recipe.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RankingService {

    private final MemberRepository memberRepository;
    private final RecipeRepository recipeRepository;

    //추가
    public List<Recipe> getRecipesByNickname(String nickname) {
        return recipeRepository.findByNickname(nickname);
    }

    public Member getMemberByNickname(String nickname) {
        return memberRepository.findByNickname(nickname)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자를 찾을 수 없습니다: " + nickname));
    }
    // 여기까지

    public Page<Member> getRankedMembers(Pageable pageable) {
        // 전체 멤버를 추천수(hit)에 따라 내림차순으로 정렬하여 가져옵니다.
        List<Member> allMembers = memberRepository.findAll(Sort.by(Sort.Direction.DESC, "hit"));


        // 페이지네이션 처리
        int start = (int) pageable.getOffset();
        int end = Math.min((start + pageable.getPageSize()), allMembers.size());
        List<Member> paginatedMembers = allMembers.subList(start, end);

        return new PageImpl<>(paginatedMembers, pageable, allMembers.size());
    }

}
