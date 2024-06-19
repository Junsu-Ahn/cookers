package com.example.cookers;

import com.example.cookers.domain.member.service.MemberService;
import com.example.cookers.domain.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SpringBootTest
class CookersApplicationTests {

	@Autowired
	RecipeService recipeService;

	@Autowired
	MemberService memberService;

	@Test
	void 멤버_생성() {
		List<String> nicknames = Arrays.asList(
				"맛있는향기", "요리의여왕", "요리의달인", "맛의연금술사", "달콤한주방",
				"행복한밥상", "요리천재", "맛의마법사", "주방의요정", "요리사랑",
				"맛의비법", "요리비책", "요리사꿈나무", "달콤한비밀", "요리의신",
				"맛있는레시피", "주방의마술사", "맛있는이야기", "행복한주방", "요리하는즐거움",
				"초보요리사", "요리초보", "주방초보", "요리입문", "맛의초보",
				"새내기요리사", "요리수련생", "주방새내기", "요리도전", "맛초보",
				"요리고수", "맛의고수", "주방의달인", "요리명인", "맛의명인",
				"주방고수", "요리전문가", "맛의장인", "요리마스터", "주방장인",
				"푸른하늘", "초록잎새", "물방울향기", "꽃잎향기", "은하수길",
				"무지개다리", "해바라기", "달토끼", "구름산책", "별구름"
		);

		Collections.shuffle(nicknames);

		for (int i = 1; i <= 50; i++) {
			String username = String.format("user%d", i);
			String password = String.format("user%d", i); // 테스트용 비밀번호
			String passwordConfirm = String.format("user%d", i); // 비밀번호 확인
			String nickname = nicknames.get(i - 1);
			String email = String.format("user%d@example.com", i);
			String typeCode = ""; // 예시 타입 코드
			String url = "";
			Long hit = 0L;

			memberService.signup("", username, password, passwordConfirm, nickname, email, hit, url);
			// public Member signup(String providerTypeCode, String username, String password, String passwordConfirm, String nickname, String email, Long hit, String url){
		}
	}

//	@Test
//	void 카테고리별로_레시피출력() throws IOException {
//		String fileDirPath = "C:/work/cookersmember/src/main/resources/static/imagefile/post";
//
//		// 디렉토리가 존재하지 않으면 생성
//		Path dirPath = Paths.get(fileDirPath);
//		if (Files.notExists(dirPath)) {
//			Files.createDirectories(dirPath);
//		}
//
//		for (long i = 1; i <= 20; i++) { // 1
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i);
//			String categoryValue = String.format("밥요리");
//			long hit = 0;
//
//
//			// 임의의 이미지 파일 생성
//			Path tempFile = Files.createTempFile("temp-thumbnail", ".jpg");
//			Files.write(tempFile, "Dummy content".getBytes());
//			FileInputStream fis = new FileInputStream(tempFile.toFile());
//
//			MockMultipartFile thumbnail = new MockMultipartFile("thumbnail", "thumbnail.jpg", "image/jpeg", fis);
//
//			recipeService.create(title, subject, content, nickname, categoryValue, hit, thumbnail);
//		}

//		for (int i =1; i<= 2; i++){ // 2 ~ 3
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+1);
//			String categoryValue = String.format("국 & 찌개");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}
//		for (int i =1; i<= 3; i++){ // 4 ~ 6
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+3);
//			String categoryValue = String.format("면요리");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}
//		for (int i =1; i<= 4; i++){ // 7 ~ 10
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+6);
//			String categoryValue = String.format("튀김요리");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}
//		for (int i =1; i<= 5; i++){ // 11 ~ 16
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+10);
//			String categoryValue = String.format("볶음요리");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}
//		for (int i =1; i<= 6; i++){ // 17 ~ 23
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+16);
//			String categoryValue = String.format("찜 & 조림");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}
//		for (int i =1; i<= 7; i++){ // 23 ~ 30
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+22);
//			String categoryValue = String.format("샐러드");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}
//		for (int i =1; i<= 8; i++){ // 31 ~ 39
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+30);
//			String categoryValue = String.format("밑반찬");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}
//		for (int i =1; i<= 9; i++){ // 40 ~ 49
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+39);
//			String categoryValue = String.format("간식 & 분식");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}
//		for (int i =1; i<= 10; i++){ // 50 ~ 60
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+49);
//			String categoryValue = String.format("디저트");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}
//		for (int i =1; i<= 11; i++){ // 61 ~ 72
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+60);
//			String categoryValue = String.format("명절음식");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}
//		for (int i =1; i<= 12; i++){ // 73 ~
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i+72);
//			String categoryValue = String.format("기타요리");
//			recipeService.create(title, subject, content, nickname, categoryValue);
//		}

//	}


}
