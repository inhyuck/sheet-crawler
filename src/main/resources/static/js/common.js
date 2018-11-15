$(function() {
    $("[data-url]").click(function () {
        var url = $(this).attr("data-url");
        location.href = url;
    })
})