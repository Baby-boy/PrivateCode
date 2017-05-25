/**
 * Created by Administrator on 2016/11/29.
 */
$(function () {
    var pagedate={
        pageCount:3,
        current:1,
        backFn:function(p){
            //console.log(p);
        }
    };
    /*$(".tcdPageCode").createPage(pagedate);*/
    
    $(".people-se").select({
        change:function () {
            
        }
    });
    $(".people-se2").select();
    $(".people-se3").select();

    $(".yd-location-right").click(function () {
        $(".yd-location-wrap select").attr("data-value","");
        $(".yd-location-wrap select").val("");
        $(".select_text").each(function (i) {
            $(".select_text").eq(i).text($(".yd-location-wrap select").eq(i).find("option").eq(0).text())
        })
    });
});
