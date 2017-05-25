/**
 * Created by Administrator on 2016/11/29.
 */
$(function () {
    $("input").placeholder();
    // 删除技能
    $(".myapprove-ziliao i").click(function () {
        if(window.confirm('你确定要删除该技能吗？')){
            $(this).parent().parent().remove();
        }else{

        }
    }); 
});