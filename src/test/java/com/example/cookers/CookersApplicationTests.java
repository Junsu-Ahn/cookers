package com.example.cookers;

import com.example.cookers.domain.member.entity.Member;
import com.example.cookers.domain.member.service.MemberService;
import com.example.cookers.domain.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CookersApplicationTests {
	@Autowired
	private MemberService memberService;

	@Autowired
	private RecipeService recipeService;

//	@Test
//	void contextLoads() {
//		for (int i =1; i<= 10; i++){
//			String title = String.format("제목 %d", i);
//			String content = String.format("내용 %d", i);
//			Member nickname = memberService.getMember("user1");
//			recipeService.create(title, content, nickname);
//		}
//	}

}
