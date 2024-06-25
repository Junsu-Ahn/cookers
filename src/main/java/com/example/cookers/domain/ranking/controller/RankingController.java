package com.example.cookers.domain.ranking.controller;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.ranking.service.RankingService;
import com.example.cookers.domain.recipe.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ranking")
@RequiredArgsConstructor
public class RankingController {

    private final RankingService rankingService;

    @GetMapping
    public String showRankingPage(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        int pageSize = 50;
        Pageable pageable = PageRequest.of(page, pageSize, Sort.by(Sort.Direction.DESC, "hit"));

        Page<Member> rankedMembersPage = rankingService.getRankedMembers(pageable);
        Long totalRecommendations = rankingService.getTotalRecommendations();
        Map<Long, Long> memberRecommendations = rankingService.getMemberRecommendations();

        model.addAttribute("rankedMembersPage", rankedMembersPage);
        model.addAttribute("currentPage", page);
        model.addAttribute("totalRecommendations", totalRecommendations);
        model.addAttribute("memberRecommendations", memberRecommendations);

        return "ranking/ranking";
    }

    @GetMapping("/member/{nickname}")
    public String showMemberProfilePage(@PathVariable(name = "nickname") String nickname, Model model) {
        Member member = rankingService.getMemberByNickname(nickname);
        List<Recipe> recipes = rankingService.getRecipesByNickname(nickname);

        model.addAttribute("member", member);
        model.addAttribute("recipes", recipes);

        return "ranking/member_profile"; // member_profile.html로 이동
    }
}
