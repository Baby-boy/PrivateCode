$(function () {
    // 修改资料页面点击修改
    $(".ziliaos p i").click(function () {
        var index=$(this).index(".ziliaos p i");
        $(".ziliaos p i").css("color","#999999").eq(index).css("color","#3CC8B4");
        $(this).parent().parent().find(".news").removeClass("dis-none").parent().siblings().find(".news").addClass("dis-none");
    });
    $(".ziliaos").each(function (i) {
        $(".ziliaos:eq("+i+") .news button").eq(0).click(function () {
            if($(this).parents(".ziliaos").find("input").val()!=""){
                $(this).parents(".ziliaos").find("span").html($(this).parents(".ziliaos").find("input").val());
            }
            $(this).parents(".news").addClass("dis-none");
            $(this).parents(".ziliaos").find("i").css("color","#999999");
        });
        $(".ziliaos:eq("+i+") .news button").eq(1).click(function () {
            $(this).parents(".news").addClass("dis-none");
            $(this).parents(".ziliaos").find("i").css("color","#999999");
        })
    });

});