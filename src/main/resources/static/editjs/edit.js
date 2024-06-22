$(document).ready(function() {
    // 저장 버튼 클릭 시 이벤트 핸들러
    $(".save-button").on("click", function(event) {
        // 확인 메시지 표시
        if (confirm("저장하시겠습니까?")) {
            // 폼 제출
            $("#editForm").submit();

            // 저장 완료 메시지
            alert("저장되었습니다.");
        } else {
            event.preventDefault(); // 확인을 누르지 않으면 폼 제출을 막습니다.
        }
    });

    // 탈퇴하기 버튼 클릭 시 이벤트 핸들러
    $(".delete-button").on("click", function(event) {
        // 확인 메시지 표시
        if (confirm("탈퇴하시겠습니까?")) {
            // Ajax를 이용해 회원 탈퇴 요청을 서버로 전송
            $.post("/member/delete", function() {
                // 탈퇴 완료 메시지
                alert("탈퇴되었습니다.");

                // 메인 페이지로 이동
                window.location.href = "/";
            }).fail(function() {
                alert("탈퇴에 실패하였습니다. 다시 시도해주세요.");
            });
        }
    });

    // 폼이 제출된 후 서버 응답이 완료되면 메인 페이지로 이동
    $("#editForm").on("submit", function() {
        // 여기서 실제 데이터베이스 업데이트가 이루어지고,
        // 서버 응답이 완료되면 메인 페이지로 이동하도록 설정합니다.
        setTimeout(function() {
            window.location.href = "/"; // 메인 페이지 URL로 변경해주세요.
        }, 1000); // 1초 후 메인 페이지로 이동
    });
});
