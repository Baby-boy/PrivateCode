$(function () {
   $(".typeofs:eq(0) .kaiguan").click(function () {
       if($(this).attr("add")=="no"){
           $(this).attr("add","ok").find(".flags").addClass("kai").parent().siblings(".kaiguan").attr("add","no").find(".flags").removeClass("kai");
       }else if($(this).attr("add")=="ok"){
           // alert(2);
           $(this).attr("add","no");
           $(this).find(".flags").removeClass("kai");
       }
   });
    $(".typeofs:eq(1) .kaiguan").click(function () {
        if($(this).attr("add")=="no"){
            $(this).attr("add","ok").find(".flags").addClass("kai").parent().siblings(".kaiguan").attr("add","no").find(".flags").removeClass("kai");
        }else if($(this).attr("add")=="ok"){
            // alert(2);
            $(this).attr("add","no");
            $(this).find(".flags").removeClass("kai");
        }
    });

    // 修改资料页面点击修改
    $(".ziliaos p i").click(function () {
        var index=$(this).index(".ziliaos p i");
        $(".ziliaos p i").css("color","#FF9869").eq(index).css("color","#3CC8B4");
        $(this).parent().parent().find(".news").removeClass("dis-none").parent().siblings().find(".news").addClass("dis-none");
    });
    $(".ziliaos").each(function (i) {
        $(".ziliaos:eq("+i+") .news button").eq(0).click(function () {
            if($(this).parents(".ziliaos").find("input").val()!=""){
                $(this).parents(".ziliaos").find("span").html($(this).parents(".ziliaos").find("input").val());
            }
            $(this).parents(".news").addClass("dis-none");
            $(this).parents(".ziliaos").find("i").css("color","#FF9869");
        });
        $(".ziliaos:eq("+i+") .news button").eq(1).click(function () {
            $(this).parents(".news").addClass("dis-none");
            $(this).parents(".ziliaos").find("i").css("color","#FF9869");
        })
    });
    
    $(".job-message input").change(function(){
        $(this).parent().find("i").html($(this).val().slice($(this).val().lastIndexOf("\\")+1))
      })
});