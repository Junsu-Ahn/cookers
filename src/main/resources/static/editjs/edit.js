  $(document).ready(function() {
        // 저장 버튼 클릭 시 이벤트 핸들러
        $(".save-button").on("click", function(event) {
            // 확인 메시지 표시
            if (confirm("저장하시겠습니까?")) {
                // 폼 제출
                $("#editForm").submit();
            } else {
                event.preventDefault(); // 확인을 누르지 않으면 폼 제출을 막습니다.
            }
        });

        // 탈퇴하기 버튼 클릭 시 이벤트 핸들러
        $(".delete-button").on("click", function(event) {
            // 확인 메시지 표시
            if (confirm("탈퇴하시겠습니까?")) {
                // Ajax를 이용해 회원 탈퇴 요청을 서버로 전송
                $.post("/member/delete", function(response) {
                    if (response === "success") {
                        // 탈퇴 완료 메시지
                        alert("탈퇴되었습니다.");
                        // 메인 페이지로 이동
                        window.location.href = "/";
                    } else {
                        alert("탈퇴에 실패하였습니다. 다시 시도해주세요.");
                    }
                }).fail(function() {
                    alert("탈퇴에 실패하였습니다. 다시 시도해주세요.");
                });
            }
        });

        // 기본 프로필로 변경 버튼 클릭 시 이벤트 핸들러
        $(".default-profile-button").on("click", function() {
            if (confirm("기본 프로필 이미지로 변경하시겠습니까?")) {
                $.post("/member/setDefaultProfile", function() {
                    alert("기본 프로필 이미지로 변경되었습니다.");
                    // 메인 페이지로 이동
                    window.location.href = "/";
                }).fail(function() {
                    alert("기본 프로필 이미지로 변경에 실패하였습니다. 다시 시도해주세요.");
                });
            }
        });

        // 프로필 이미지 미리보기 함수
        function readURL(input, previewId) {
            if (input.files && input.files[0]) {
                var reader = new FileReader();
                reader.onload = function(e) {
                    $('#' + previewId).attr('src', e.target.result);
                };
                reader.readAsDataURL(input.files[0]);
            }
        }

        // 프로필 이미지 변경 시 미리보기 표시
        $('#profileImgInput').change(function() {
            readURL(this, 'preview');
        });
    });