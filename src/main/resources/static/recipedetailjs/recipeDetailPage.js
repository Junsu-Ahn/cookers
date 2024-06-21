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
 function deleteComment(element) {
            var uri = element.getAttribute("data-uri");
            var csrfToken = document.querySelector('meta[name="_csrf"]').getAttribute('content');
            var csrfHeader = document.querySelector('meta[name="_csrf_header"]').getAttribute('content');

            if (confirm("정말 삭제하시겠습니까?")) {
                fetch(uri, {
                    method: 'DELETE',
                    headers: {
                        'Content-Type': 'application/json',
                        [csrfHeader]: csrfToken
                    }
                })
                .then(response => {
                    if (response.ok) {
                        alert("댓글이 삭제되었습니다.");
                        location.reload();
                    } else {
                        response.text().then(text => alert(text));
                    }
                })
                .catch(error => console.error('Error:', error));
            }
        }

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
});