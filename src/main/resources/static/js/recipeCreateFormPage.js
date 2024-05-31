$(document).ready(function(){
 alert("ddd");
});



//
//$('.star_rating > .star').click(function() {
//  $(this).parent().children('span').removeClass('on');
//  $(this).addClass('on').prevAll('span').addClass('on');
//})
//
//const wrapper_1 = $('#ingredients'); // 입력 필드를 포함하는 컨테이너 선택
//const addButton_1 = $('#plus_btn_1'); // 추가 버튼 선택
//const maxFields_1 = 30; // 최대 입력 필드 수 설정
//let fieldCount_1 = 1; // 현재 입력 필드 수
//
//// '추가하기' 버튼 클릭 시 이벤트
//addButton_1.click(function(e) {
//    e.preventDefault(); // 페이지 리로드 방지
//    if (fieldCount_1 < maxFields_1) { // 최대 필드 수 체크
//        fieldCount_1++; // 필드 수 증가
//        // 새 입력 필드 추가
//        wrapper_1.append(`
//            <div class="ingredients_name hide_on_btn">
//              <input class="main" type="text" placeholder="ex) 김치">
//              <input class="sub" type="text" placeholder=" g,ml(단위)">
//              <button class="minus_btn" type="button">삭제</button>
//            </div>
//        `);
//    }
//});

// '삭제' 링크 클릭 시 이벤트
wrapper_1.on('click', '.minus_btn', function(e) {
    e.preventDefault(); // 페이지 리로드 방지
    $(this).parent('.ingredients_name').remove(); // 필드 제거
    fieldCount_1--; // 필드 수 감소
});

const wrapper_2 = $('#seasoning'); // 입력 필드를 포함하는 컨테이너 선택
const addButton_2 = $('#plus_btn_2'); // 추가 버튼 선택
const maxFields_2 = 30; // 최대 입력 필드 수 설정
let fieldCount_2 = 1; // 현재 입력 필드 수

// '추가하기' 버튼 클릭 시 이벤트
addButton_2.click(function(e) {
    e.preventDefault(); // 페이지 리로드 방지
    if (fieldCount_2 < maxFields_2) { // 최대 필드 수 체크
        fieldCount_2++; // 필드 수 증가
        // 새 입력 필드 추가
        wrapper_2.append(`
            <div class="seasoning_name hide_on_btn">
                <input class="main" type="text" placeholder=" ex) 고추장">
                <input class="sub" type="text" placeholder="  g,ml(단위)">
                <button class="minus_btn" type="button">삭제</button>
              </div>
        `);
    }
});

// '삭제' 링크 클릭 시 이벤트
wrapper_2.on('click', '.minus_btn', function(e) {
    e.preventDefault(); // 페이지 리로드 방지
    $(this).parent('.seasoning_name').remove(); // 필드 제거
    fieldCount_2--; // 필드 수 감소
});