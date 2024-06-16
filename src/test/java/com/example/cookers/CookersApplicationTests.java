package com.example.cookers;

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

@SpringBootTest
class CookersApplicationTests {

	@Autowired
	RecipeService recipeService;
	@Test
	void 카테고리별로_레시피출력() throws IOException {
		String fileDirPath = "C:/work/cookers/src/main/resources/static/imagefile/post";

		// 디렉토리가 존재하지 않으면 생성
		Path dirPath = Paths.get(fileDirPath);
		if (Files.notExists(dirPath)) {
			Files.createDirectories(dirPath);
		}

		for (long i = 1; i <= 20; i++) { // 1
			String title = String.format("제목 %d", i);
			String subject = String.format("소제목 %d", i);
			String content = String.format("내용 %d", i);
			String nickname = String.format("user%d", i);
			String categoryValue = String.format("밥요리");
			long hit = 0;


			// 임의의 이미지 파일 생성
			Path tempFile = Files.createTempFile("temp-thumbnail", ".jpg");
			Files.write(tempFile, "Dummy content".getBytes());
			FileInputStream fis = new FileInputStream(tempFile.toFile());

			MockMultipartFile thumbnail = new MockMultipartFile("thumbnail", "thumbnail.jpg", "image/jpeg", fis);

			recipeService.create(title, subject, content, nickname, categoryValue, hit, thumbnail);
		}
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

	}

//	@Test
//	void 카테고리_페이징() {
//		for (int i =1; i<= 12; i++){
//			String title = String.format("제목 %d", i);
//			String subject = String.format("소제목 %d", i);
//			String content = String.format("내용 %d", i);
//			String nickname = String.format("user%d", i);
//			recipeService.create(title, subject, content, nickname);
//		}
//	}
}
