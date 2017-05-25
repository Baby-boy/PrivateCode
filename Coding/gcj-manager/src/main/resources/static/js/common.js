$(function () {
    // 人才大厅服务商介绍
    $(".fuwushang .fenye span").click(function () {
        var index=$(this).index(".fuwushang .fenye span");
        var obj={str:$(window).scrollTop()};
        // $(this).addClass("now").siblings().removeClass("now");
        var tops=[$(".fuwushang .fenye").offset().top,
                    $(".fuwushang .anli").offset().top,
                    $(".fuwushang .haoping").offset().top];
        $(obj).animate({"str":tops[index]},{
            speed:1000,
            step:function (a) {
                $(window).scrollTop(a);
            }
        });
    });
    // 注册页面个人，雇主切换
    $(".sjbd-box .zhuce li").click(function () {
        $(this).parent().find("li").addClass("now").parent().siblings().find("li").removeClass("now");
    });
    $(".logins .zhanghao input").focus(function () {
        $(this).parent().addClass("now");
    });
    $(".logins .zhanghao input").blur(function () {
        $(this).parent().removeClass("now");
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
        $(".popup-zhifu").addClass("dis-none");
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
        $(".popup-fukuanjia").removeClass("dis-none");
    });
    // 增加模块
    $(".addmokuai").click(function () {
        $(window).scrollTop(1);
        $(".zhezhao").removeClass("dis-none");
        $(".popup-mokuaijia").removeClass("dis-none");
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
    $(".buchon").click(function () {
        $(window).scrollTop(1);
        $(".zhezhao").removeClass("dis-none");
        $(".fbgg-popup").removeClass("dis-none");
    });
    $(".fbgg-popup button").click(function () {
        $(".zhezhao").addClass("dis-none");
        $(".fbgg-popup").addClass("dis-none");
    });
    
});