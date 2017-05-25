/**
 * Created by Administrator on 2016/11/30.
 */
$(function () {
    $(".mulu2-list li").eq(-1).css("borderLeft","1px solid transparent");
    $("select").select();
    laydate({
        elem: '#yugu', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    });
    laydate({
        elem: '#yujing', //目标元素。由于laydate.js封装了一个轻量级的选择器引擎，因此elem还允许你传入class、tag但必须按照这种方式 '#id .class'
        event: 'focus' //响应事件。如果没有传入event，则按照默认的click
    });

    // 发布公告
    $(".mulu1-list .three button").click(function() {
        $(window).scrollTop(1);
        $(".zhezhao").removeClass("dis-none");
        $(".fbgg-popup").removeClass("dis-none");
    });
    $(".guanbi").click(function () {
        $(".zhezhao").addClass("dis-none");
        $(".fbgg-popup").addClass("dis-none");
        $(".refund-box").addClass("dis-none");
        $(".popup-fukuan").addClass("dis-none");
        $(".popup-queren").addClass("dis-none");
        $(".popup-evaluate").addClass("dis-none");
        $(".popup-shenqing").addClass("dis-none");
        $(".popup-fukuanjia").addClass("dis-none");
        $(".popup-mokuaijia").addClass("dis-none");
        $(".popup-daozhang").addClass("dis-none");
    });

    //点击取消
    $(".popup-shenqing button").eq(1).click(function () {
        $(".zhezhao").addClass("dis-none");
        $(".popup-shenqing").addClass("dis-none");
    });

    // 项目跟进部分按钮点击弹出框
    $(".button-tk").click(function () {
        if($(this).html()=="检查确认"){
            $(window).scrollTop(1);
            $(".zhezhao").removeClass("dis-none");
            $(".popup-queren").removeClass("dis-none");
        }
    });
    // 星级评价
    $(".pingjia").click(function () {
        $(window).scrollTop(1);
        $(".zhezhao").removeClass("dis-none");
        $(".popup-evaluate").removeClass("dis-none");
    });
    // 点击收起流程
    $(".mulu1 i").click(function () {
        var that=this;
        var index=$(this).index(".mulu1 i");
        if(index==0){
            $(this).parent().next(".mulu1-list").find(".two-box").slideToggle(function () {
                $(that).parent().toggleClass("mulushou");
            });
        }else{
            $(this).parent().next(".mulu1-list").slideToggle(function () {
                $(that).parent().toggleClass("mulushou");
            });
        }
    });
    // 增加付款
    $(".addPaying").click(function () {
        $(window).scrollTop(1);
        $(".zhezhao").removeClass("dis-none");
        $(".popup-mokuaijia").removeClass("dis-none");
    });
    // 增加模块
    $(".addmokuai").click(function () {
        $(window).scrollTop(1);
        $(".zhezhao").removeClass("dis-none");
        $(".popup-fukuanjia").removeClass("dis-none");
    });
    // 退款维权申请
    $(".mulubom a").click(function () {
        $(window).scrollTop(1);
        $(".zhezhao").removeClass("dis-none");
        $(".refund-box").removeClass("dis-none");
    });

    // 消息回复
    $(".news-huifu button").click(function () {
        if($(this).html()=="回复"){
            $(window).scrollTop(1);
            $(".zhezhao").removeClass("dis-none");
            $(".fbgg-popup").removeClass("dis-none");
        }
    });

    // 选择人才部分弹出框
    $(".select-persons .btn-2").click(function () {
        $(".zhezhao").removeClass("dis-none");
        $(".refund-box").eq(0).removeClass("dis-none");
    });
    $(".select-persons .btn-3").click(function () {
        $(".zhezhao").removeClass("dis-none");
        $(".refund-box").eq(1).removeClass("dis-none");
    });
    $(".select-tc button").click(function () {
        $(".zhezhao").addClass("dis-none");
        $(".refund-box").addClass("dis-none");
    });

    // 合同状态已签订页面
    $(".sanfang-ht .buchon").click(function () {
        $(window).scrollTop(1);
        $(".zhezhao").removeClass("dis-none");
        $(".fbgg-popup").removeClass("dis-none");
    });
});