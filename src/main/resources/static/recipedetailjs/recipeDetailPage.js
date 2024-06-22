$(document).ready(function() {
         $(".modify").on("click", function() {
               var modifyUri = $(this).data("uri");
               console.log('Modify URI:', modifyUri);  // URI 확인을 위한 로그
               if (modifyUri && modifyUri !== "undefined") {
                   $('<form>', {
                       "id": "modifyForm",
                       "html": '<input type="hidden" name="id" value="' + modifyUri.split('/').pop() + '" />',
                       "method": "post",
                       "action": modifyUri
                   }).appendTo(document.body).submit();
               } else {
                   console.error('Invalid modify URI:', modifyUri);
               }
           });

    $(".delete").on("click", function() {
        var deleteUri = $(this).data("uri");
        if (deleteUri && deleteUri !== "undefined") {
            if (confirm("정말로 삭제하시겠습니까?")) {
                location.href = deleteUri;
            }
        } else {
            console.error('Invalid delete URI:', deleteUri);
        }
    });
        console.log('Document is ready'); // 디버깅용 로그

        // 초기 상태 설정
        if ($('#content').val().trim() === '') {
            $('#submit-button').prop('disabled', true);
            $('#warning-message').show();
        } else {
            $('#submit-button').prop('disabled', false);
            $('#warning-message').hide();
        }

        $('#content').on('input', function() {
            console.log('Content input event triggered'); // 디버깅용 로그
            if ($(this).val().trim() === '') {
                console.log('Content is empty'); // 디버깅용 로그
                $('#submit-button').prop('disabled', true);
                $('#warning-message').show();
            } else {
                console.log('Content is not empty'); // 디버깅용 로그
                $('#submit-button').prop('disabled', false);
                $('#warning-message').hide();
            }
        });

        $('#submit-button').on('click', function(event) {
            console.log('Submit button clicked'); // 디버깅용 로그
            if ($('#content').val().trim() === '') {
                console.log('Content is empty on submit'); // 디버깅용 로그
                $('#warning-message').show();
                event.preventDefault(); // 폼 제출을 막음
            } else {
                console.log('Content is not empty on submit'); // 디버깅용 로그
                $('#warning-message').hide();
            }
        });

    $('.content').click(function() {
        var $heart = $(this).find('.heart');
        var recipeId = $(this).attr('data-id');
        if (recipeId && recipeId !== "undefined") {
            $.ajax({
                method: 'POST',
                url: '/recipe/recommend',
                data: { id: recipeId },
                success: function(response) {
                    if (typeof response === 'string') {
                        if (confirm(response + " 로그인 페이지로 이동하시겠습니까?")) {
                            window.location.href = "/member/login"; // 로그인 페이지로 리다이렉트
                        }
                    } else {
                        $('#hitCount').text(response.hit);
                        if ($('.content').hasClass("heart-active")) {
                            $heart.removeClass("heart-active");
                            $('.content').removeClass("heart-active");
                            $('.text').removeClass("heart-active");
                            $('.numb').removeClass("heart-active");
                        } else {
                            $('.content').addClass("heart-active");
                            $('.text').addClass("heart-active");
                            $('.numb').addClass("heart-active");
                            $heart.addClass("heart-active");
                        }
                    }
                },
                error: function(error) {
                    if (error.status === 401) {
                        if (confirm("로그인 후 이용 가능합니다. 로그인 페이지로 이동하시겠습니까?")) {
                            window.location.href = "/member/login"; // 로그인 페이지로 리다이렉트
                        }
                    } else {
                        console.error('Error:', error);
                    }
                }
            });
        } else {
            console.error('Invalid recipeId:', recipeId);
        }
    });

    const recipeLevel = 3; // 실제 데이터로 대체해야 함

    const stars = document.querySelectorAll('#recipeLevelDetail .star');
    stars.forEach(star => {
        if (star.getAttribute('data-value') <= recipeLevel) {
            star.classList.add('on');
        }
    });

    $(".delete").on("click", function() {
        if (confirm("정말로 삭제하시겠습니까?")) {
            location.href = $(this).data("uri");
        }
    });
});