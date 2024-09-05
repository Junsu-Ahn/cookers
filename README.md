# <img src="https://github.com/Junsu-Ahn/cookers/assets/132266117/377131c0-ea3d-42a7-bc52-87da88deaac2">

## 🚀프로젝트 명 : 오늘은 내가 요리사
# <img src="https://github.com/user-attachments/assets/d2014fbb-1dc1-4044-bc8c-36fd1d26124f">


## 📢 프로젝트 설명
* 요리를 좋아하는 모든 사용자들을 위한 레시피 제공
* 회원들의 추천을 통해 간단한 요리를 제조
* 외식에 대한 식비 지출 절감

## 🧑‍🤝‍🧑 맴버구성
# <img src="https://github.com/user-attachments/assets/eb4fb45c-afab-46bb-9bf1-af71046aa26f">

## ⚙ 개발 환경
* 운영체제 : Windows 10 -통합개발환경(IDE) : IntelliJ
* JDK 버전 : Project SDK 17
*  데이터 베이스 : MariaDB
*  빌드 툴 : Gradle
*  관리 툴 : GitHub

## ⏱️개발 기간
- 전체 개발 기간 : 2024-05-27 ~ 2024-06-27
- UI 구현 : 2024-05-27 ~ 2024-06-27
- 기능 구현 : 2024-05-27 ~ 2024-06-27

## 🔌 Dependencies
* Spring Boot DevTools
* Lombok
* Spring Data JPA
* MariaDB Driver
* Spring Security
* Spring Web
* Oauth2-client
* Thymeleaf
* Thymeleaf Layout Dialect
* Thymeleaf Extras Spring Security6
* Spring Boot Starter Validation
* Spring Boot Starter Mail

## 💻 기술 스택
* 백엔드
  : Spring Boot
  Spring Security
  Spring Data JPA

* 프론트엔드
  : HTML
  CSS
  JavaScript
  Bootstrap
  Thymeleaf

* 데이터베이스
  : MariaDB
  MySQL Workbench

## 🛠 DB 설계
* Member
* Recipe
* Ranking
* Email
* Comment

## 🕹 구현 기능
* Entity 설계
* 회원 로그인/회원가입
* 소셜로그인
* 메인페이지 제작
* 레시피 등록/삭제
* MemberDetail 페이지 제작
* Email 기능
* 회원정보 수정 기능
* 관리자 페이지 제작
* 랭킹 기능

## 🔗 ER - Diagram
# <img src="https://github.com/Junsu-Ahn/cookers/assets/134615615/8ef42ef8-8c9e-4667-b9f7-9fbc1806cefd">

## 📝 USE CASE
# <img src="https://github.com/user-attachments/assets/f9e968ea-a756-4551-ac47-70530079b44c">

## 📱 디렉토리 구조
```
├─.gradle
│  ├─8.7
│  │  ├─checksums
│  │  ├─dependencies-accessors
│  │  ├─executionHistory
│  │  ├─expanded
│  │  ├─fileChanges
│  │  ├─fileHashes
│  │  └─vcsMetadata
│  ├─buildOutputCleanup
│  └─vcs-1
├─.idea
├─build
│  ├─reports
│  │  └─tests
│  │      └─test
│  │          ├─classes
│  │          ├─css
│  │          ├─js
│  │          └─packages
│  ├─resources
│  │  └─main
│  │      ├─static
│  │      │  ├─createformjs
│  │      │  ├─css
│  │      │  ├─editjs
│  │      │  ├─imagefile
│  │      │  │  └─post
│  │      │  │      └─post
│  │      │  ├─images
│  │      │  ├─listjs
│  │      │  ├─mainjs
│  │      │  ├─memberprofilejs
│  │      │  ├─rankingjs
│  │      │  └─recipedetailjs
│  │      └─templates
│  │          ├─admin
│  │          ├─home
│  │          ├─member
│  │          ├─ranking
│  │          └─recipe
│  ├─test-results
│  │  └─test
│  │      └─binary
│  └─tmp
│      ├─compileJava
│      │  └─compileTransaction
│      │      └─stash-dir
│      └─compileTestJava
│          └─compileTransaction
│              └─stash-dir
├─cookers
├─gradle
│  └─wrapper
├─out
│  ├─production
│  │  ├─classes
│  │  │  └─com
│  │  │      └─example
│  │  │          └─cookers
│  │  │              ├─domain
│  │  │              │  ├─comment
│  │  │              │  │  ├─controller
│  │  │              │  │  ├─dto
│  │  │              │  │  ├─entity
│  │  │              │  │  ├─repository
│  │  │              │  │  └─service
│  │  │              │  ├─Email
│  │  │              │  ├─home
│  │  │              │  │  └─controller
│  │  │              │  ├─member
│  │  │              │  │  ├─controller
│  │  │              │  │  ├─dto
│  │  │              │  │  ├─entity
│  │  │              │  │  ├─repository
│  │  │              │  │  └─service
│  │  │              │  ├─ranking
│  │  │              │  │  ├─controller
│  │  │              │  │  ├─entity
│  │  │              │  │  ├─repository
│  │  │              │  │  └─service
│  │  │              │  └─recipe
│  │  │              │      ├─controller
│  │  │              │      ├─dto
│  │  │              │      ├─entity
│  │  │              │      ├─repository
│  │  │              │      └─service
│  │  │              └─global
│  │  │                  ├─config
│  │  │                  ├─controller
│  │  │                  ├─jpa
│  │  │                  └─security
│  │  └─resources
│  │      ├─static
│  │      │  ├─createformjs
│  │      │  ├─css
│  │      │  ├─editjs
│  │      │  ├─imagefile
│  │      │  │  └─post
│  │      │  │      └─post
│  │      │  ├─images
│  │      │  ├─listjs
│  │      │  ├─mainjs
│  │      │  ├─memberprofilejs
│  │      │  ├─rankingjs
│  │      │  └─recipedetailjs
│  │      └─templates
│  │          ├─admin
│  │          ├─home
│  │          ├─member
│  │          ├─ranking
│  │          └─recipe
│  └─test
│      └─classes
│          └─com
│              └─example
│                  └─cookers
├─src
│  ├─main
│  │  ├─generated
│  │  ├─java
│  │  │  └─com
│  │  │      └─example
│  │  │          └─cookers
│  │  │              ├─domain
│  │  │              │  ├─comment
│  │  │              │  │  ├─controller
│  │  │              │  │  ├─dto
│  │  │              │  │  ├─entity
│  │  │              │  │  ├─repository
│  │  │              │  │  └─service
│  │  │              │  ├─Email
│  │  │              │  ├─home
│  │  │              │  │  └─controller
│  │  │              │  ├─member
│  │  │              │  │  ├─controller
│  │  │              │  │  ├─dto
│  │  │              │  │  ├─entity
│  │  │              │  │  ├─repository
│  │  │              │  │  └─service
│  │  │              │  ├─ranking
│  │  │              │  │  ├─controller
│  │  │              │  │  ├─entity
│  │  │              │  │  ├─repository
│  │  │              │  │  └─service
│  │  │              │  └─recipe
│  │  │              │      ├─controller
│  │  │              │      ├─dto
│  │  │              │      ├─entity
│  │  │              │      ├─repository
│  │  │              │      └─service
│  │  │              └─global
│  │  │                  ├─config
│  │  │                  ├─controller
│  │  │                  ├─db
│  │  │                  ├─jpa
│  │  │                  └─security
│  │  └─resources
│  │      ├─static
│  │      │  ├─createformjs
│  │      │  ├─css
│  │      │  ├─editjs
│  │      │  ├─imagefile
│  │      │  │  └─post
│  │      │  │      └─post
│  │      │  ├─images
│  │      │  ├─listjs
│  │      │  ├─mainjs
│  │      │  ├─memberprofilejs
│  │      │  ├─rankingjs
│  │      │  └─recipedetailjs
│  │      └─templates
│  │          ├─admin
│  │          ├─home
│  │          ├─member
│  │          ├─ranking
│  │          └─recipe
│  └─test
│      └─java
│          └─com
│              └─example
│                  └─cookers
├─upload-dir
└─work
    └─cookersmember
        └─src
            └─main
                └─resources
                    └─static
                        └─imagefile
                            └─post
```

## 📌 페이지 기능 소개
### 메인화면
><img src="https://github.com/user-attachments/assets/977669e1-aeb7-42cb-876b-c431e11301ed">
>
>1. 홈페이지 접속 시 초기화면으로 상단의 기본 구조는 상단 메인로고 및 검색 , 로그인, 글쓰기 구분 되어 있다.
    >   - 최초 상단구조 바로 아래에는 4개의 네비로 구성
>   - 메인은 메인메뉴로 이동하는 버튼
>   - 레시피는 클릭 시 전체 레시피 확인 (마우스를 hover할 경우 12개의 카테고리를 확인할 수 있고, 클릭 시 해당 카테고리의 레시피 리스트 구성)
>   - 쉐프랭킹은 유저가 직접 작성한 레시피의 추천의 총 개수에 따라 랭킹이 결정됨
>   - 스토어는 마켓으로 이동하는 버튼으로 구성
>
><img src="https://github.com/user-attachments/assets/57059172-f67e-455b-acdd-0762eb88a12f">
>
>2. 인기 레시피는 레시피별로 추천 개수가 가장 많은 순서대로 정렬된다.

><img src="https://github.com/user-attachments/assets/211d5360-a520-44d0-9576-a305a2184dca">
>
>3. 최신 레시피는 가장 최근에 등록된 레시피 순서대로 정렬된다.

### 로그인 및 회원가입
><img src="https://github.com/user-attachments/assets/22b7d492-7097-434c-b007-a8b1331548d0">
>
>1. 회원가입을 통해 생성된 ID와 PW로 로그인을 수행.
>
>
>2. 카카오, 네이버, 구글을 통한 소셜 로그인은 버튼을 눌러 각 플랫폼에 로그인하면 자동으로 계정 생성과 동시에 로그인을 수행.
>
>
>3. 로그인에 성공하면 메인화면으로 이동합니다.
>
><img src="https://github.com/user-attachments/assets/8ada0e13-7ceb-4b9f-bdbd-423ba8c28ee0">
>
>1. 회원가입의 모든 항목에 대한 유효성 검사를 적용하여 입력하지 않으면 회원가입이 진행되지 않는다.
>
>
>2. ID는 다른 유저의 ID와 중복이 될 수 없으며, 비밀번호는 비밀번호 확인절차를 거치게 된다.
>
>
>3. 회원가입이 완료되면 로그인 화면으로 이동.

### 아이디 및 비밀번호 찾기
><img src="https://github.com/user-attachments/assets/da520fa3-f374-4c79-a1fb-ceb101ae0ac8">
><img src="https://github.com/user-attachments/assets/eb61453d-8f6b-4958-b274-5608631ef05e">
>
>1. 아이디 찾기 버튼을 통해 회원가입 시 입력한 이메일 주소를 입력하고 찾기 버튼을 누르면 해당 회원의 아이디를 이메일로 전송.
>
><img src="https://github.com/user-attachments/assets/82789b79-8078-47b8-825e-e43fda4b8bc1">
><img src="https://github.com/user-attachments/assets/d7995db4-97f7-400e-b879-fbe40bce4fa5">
>
>2. 비밀번호 찾기 버튼을 통해 회원가입 시 입력한 아이디 입력해면 해당 이메일 주소로 임시 비밀번호가 전송 된다.
>- 전송받은 임시비밀번호로 로그인 할 수 있으며, 기존에 있던 비밀번호는 사용이 불가하게 된다.
>- 임시비밀번호를 발급받은 후 정보수정 페이지에서 비밀번호 변경을 해야 안전함.

### 유저 네비바
><img src="https://github.com/user-attachments/assets/07d471f1-9119-4d11-bd00-39eba4ee6f93">
>
>1. 로그인이 되어있는 사용자만 유저 네비바를 이용할 수 있다.
>
>
> 2. 유저 네비바는 기존 로그인 버튼이 로그인 할 경우에는 회원의 프로필로 변경된다.
     >   - 프로필을 클릭하게 되면 레시피노트, 회원정보수정, 로그아웃 버튼이 활성화 된다.

### 레시피노트
><img src="https://github.com/user-attachments/assets/5b310e78-b4ef-4313-a336-8bb52ebccd78">
>
>1. 자기 자신의 레시피 노트를 확인할 때는 유저 네비바에서 레시피노트를 클릭해 이동하는 방법이 있다.
>
><img src="https://github.com/user-attachments/assets/a3f49728-3751-4df8-a774-705e96eb01f9">
>2. 다른 유저의 레시피 노트를 확인하고 싶을 때는 랭킹에 있는 유저의 아이콘을 클릭하면 이동이 가능하다.

### 회원정보수정
><img src="https://github.com/user-attachments/assets/778d9cdd-65bf-427b-8874-8791bb17dd79">
>
>
>1. 로그인이 되어있는 사용자만 회원 정보 수정이 가능하다.
>
>
>2. 회원 정보 수정에는 닉네임, 이메일, 프로필사진, 비밀번호 변경이 가능하며 회원탈퇴도 해당 페이지에서 진행한다.

### 관리자페이지
><img src="https://github.com/user-attachments/assets/9977f8a2-9808-4669-ab72-529018feaaca">
>
>1. 관리자로 로그인 할 경우 프로필(유저 네비바) 옆에 관리자페이지 이동 버튼이 생성된다.
>
>
><img src="https://github.com/user-attachments/assets/118d5bcd-b261-4ce0-b178-6c9d2b8b6a1d">
>
>
>2. 관리자 페이지에서는 유저의 회원정보를 확인할 수 있으며, 강제탈퇴까지 가능하다.

### 레시피-LIST
><img src="https://github.com/user-attachments/assets/8f7941c4-4960-417c-bba2-69b81bb109c6">
>
>1. 상단에 있는 레시피 버튼을 클릭하거나, 카테고리를 클릭하면 레시피 리스트를 확인할 수 있다.
>
>
>2. 검색을 통해서 레시피 리스트를 확인 할 수있다.
>
>
>3. 한 페이지당 10개의 리스트로 구성되어 있으며, 클릭 시 DETAIL페이지로 넘어간다.

### 레시피-DETAIL
><img src="https://github.com/user-attachments/assets/f62e72a5-a6e6-4073-be27-00f95064d17f">
><img src="https://github.com/user-attachments/assets/77933931-8a8d-4682-9c19-0d910022f15d">
><img src="https://github.com/user-attachments/assets/346e4d59-248f-4ac9-8a73-0020cbe02cb0">
><img src="https://github.com/user-attachments/assets/4319eaef-d252-4bc1-8ca2-c4e61e13e7ff">
>
>1. 상단에는 이미지, 제목, 난이도, 내용, 추천, 닉네임으로 구성되어있다.
>
>
>2. 중단에는 해당 요리의 들어가는 재료를 확인할 수 있다.
>
>
>3. 하단에는 해당 요리의 조리 순서를 확인할 수 있다.
>
>
>4. 레시피를 작성한 사용자의 경우 조리 순서 밑에 삭제와 수정 버튼이 활성화된다.
>
>
> 5. 조리 순서 밑에는 댓글을 확인할 수 있으며, 댓글 작성은 로그인 시에만 가능하다.
>
>
> 6. 댓글 수정 및 삭제는 자기 자신과 관리자만 가능하다.

### 레시피-CREATE
><img src="https://github.com/user-attachments/assets/4ba5ea26-3d82-401c-8944-e17421224dea">
><img src="https://github.com/user-attachments/assets/17f1c10f-b7f6-409c-9414-56f2079aa0de">
><img src="https://github.com/user-attachments/assets/5863fcb6-acfc-4097-be7f-dcf27524d16d">
>
>1. 상단에는 레시피의 제목, 소제목, 소개, 카테고리, 요리난이도, 썸네일 이미지를 등록할 수 있다.
    >   - 여기서 소제목은 리스트에서 확인할 수 있다.
>
>
>2. 중단에는 재료 정보를 등록할 수 있으며, 재료가 여러 개일 경우에는 추가 버튼으로 재료를 추가할 수 있다.
>
>
>3. 하단에는 조리 순서를 등록할 수 있으며, 조리 순서가 여러 개일 경우에는 추가 버튼으로 조리 순서를 추가할 수 있다.
    >   - 조리 순서에는 이미지 첨부가 가능하며, 이미지가 없을 경우 생략이 가능하다.
>
>
> 4. 제일 하단에는 저장 버튼이 있으며, 저장버튼을 누르면 리스트에 등록이 된다.

### 랭킹
><img src="https://github.com/user-attachments/assets/ee0b9183-c948-4c49-8cea-668a71dfff5a">
>
>1. 랭킹은 레시피의 추천 수가 많은 순서대로 저장 되며, 해당 카드를 클릭할 경우 레시피 페이지로 이동한다.
>
>
>2. 한 페이지당 50명의 유저가 등록되며 50명이 넘을경우 다음 페이지에 확인 된다.


## 프로젝트 소감
### 👻안준수
    - 인터넷 서비스를 직접 만들면서 평소에 쓰던 간단한 기능 하나에도 생각보다 많은 복잡한 기술들이 사용되는 것을 처음 알았습니다.
     또 팀원들끼리 하나의 작품을 위해 협업을 하는 경험도 좋았습니다.
     다만 프로젝트 진행 중 조장으로서의 역할, 소통 등이 부실했던 것 같아 많이 아쉽습니다.

### 🐬유필선
    - 혼자 진행할 때보다 더 즐거운 프로젝트가 되지 않았나 싶었습니다.
     팀원들과 함께 어려운 부분에 대해 토론하여 정답을 찾아가는 과정에서 더 즐겁게 느낄 수 있었고,
     내가 모르는 부분에 대해서 공부할 수 있는 계기가 되었던 것 같습니다.
     또한 팀 프로젝트를 진행하면서 소통의 중요성에 대해 절실히 느끼게 되었습니다.
     마지막으로 동료들 보다 연장자이면서 잘 이끌어주지 못 한것 같아서 아쉬운이 많이 남는 것 같습니다.
     다음 프로젝트에는 현 시점을 발판삼아 잘 마무리 할 수 있도록 정진하겠습니다.

### 😎김지영
    - 초급 프로젝트와 다르게 신경쓸게 많았고,
     백엔드가 훨씬 어려울 거라 생각했지만 의외로 프론트를 만들 때 어려움을 많이 겪어서 코딩은 역시 어려움의 연속이다 라는 것을 깨달았습니다.
     그치만 좋은 팀원들과 함께 할 수 있어서 영광이었습니다.
     이런 경험을 바탕으로 더 성장할 수 있는 개발자가 될 수 있도록 노력하겠습니다.
     감사합니다.
