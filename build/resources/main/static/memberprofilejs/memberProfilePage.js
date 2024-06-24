$(document).ready(function() {
    $('#userIcon').click(function(event) {
        event.preventDefault();
        event.stopPropagation();
        $('.user_two_menu').toggleClass('visible');
        $('.arrow-up').toggleClass('visible');
    });

    $(document).click(function(event) {
        if (!$(event.target).closest('#userIcon, .user_two_menu').length) {
            $('.user_two_menu').removeClass('visible');
            $('.arrow-up').removeClass('visible');
        }
    });

    $('.user_two_menu').click(function(event) {
        event.stopPropagation();
    });
});