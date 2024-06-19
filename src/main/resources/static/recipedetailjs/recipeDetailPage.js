$(document).ready(function(){
         $('.content').click(function(){
                        var $heart = $(this).find('.heart');
                        var recipeId = $(this).attr('data-id');
                        $.ajax({
                            type: 'POST',
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
                                        // Remove the animation class to reset the heart to its original state
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
                    });
    const recipeLevel = /*[[${recipe.recipeLevel}]]*/ 3;

    document.addEventListener('DOMContentLoaded', function() {
        const stars = document.querySelectorAll('#recipeLevelDetail .star');
        stars.forEach(star => {
            if (star.getAttribute('data-value') <= recipeLevel) {
                star.classList.add('on');
            }
        });
    });
});