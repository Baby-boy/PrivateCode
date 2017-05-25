$(function () {
    $(".yinhangse").select();
    $("input").placeholder();
    $(".refund-se").select();
    var yanzheng=new gongneng($(".popup-fukuan span"));
    yanzheng.yanzhengma();
    var pagedate={
        pageCount:3,
        current:1,
        backFn:function(p){
            //console.log(p);
        }
    };
    /*$(".tcdPageCode").createPage(pagedate);*/

    $(".tixian").click(function () {
    	$(".zhezhao").removeClass("dis-none");
        $(".tixian-tc").removeClass("dis-none");
    });
    $(".popup-fukuan button").eq(1).click(function () {
        $(".zhezhao").addClass("dis-none");
        $(".popup-fukuan").addClass("dis-none");
    });
    $(".popup-fukuan button").eq(0).click(function () {
        $(".popup-fukuan").addClass("dis-none");
        $(".popup-daozhang").removeClass("dis-none");
    });
    
//  支付弹出
    $(".chongzhi").click(function () {
    	$(".zhezhao").removeClass("dis-none");
        $(".chongzhi-tc").removeClass("dis-none");
    });
    $(".chongzhi-tc button").eq(0).click(function(){
    	if($('.price').val()!=''){
    		$('.payPrice').html($('.price').val());
    	}else{
    		Popups('请输入充值金额！');
    		return;
    	}
    	
    	$(".chongzhi-tc").addClass("dis-none");
    	$(".popup-zhifu").removeClass("dis-none");
    });
    $(".chongzhi-tc button").eq(1).click(function(){
    	
    	$(".chongzhi-tc").addClass("dis-none");
    	$(".zhezhao").addClass("dis-none");
    	$(".popup-zhifu").addClass("dis-none");
    });
    $(".popup-zhifu button").click(function(){
    	$(".popup-zhifu").addClass("dis-none");
    	$(".chongzhi-tc").removeClass("dis-none");
    })
    // 点击删除银行账户
    $(".ziliaos i").click(function () {
        if(window.confirm('你确定要取消交易吗？')){
            $(this).parent().remove();
        }
    });

    // 账户收起
    var zhflag=true;
    $(".account-shouqi span").click(function () {
        var that=this;
        $(this).parent().parent().find(".ziliao").slideToggle(function () {
            if(zhflag){
                zhflag=false;
                $(that).html("展开")
            }else if(!zhflag){
                zhflag=true;
                $(that).html("收起")
            }
        });
    });

    $(".popup-daozhang button").click(function () {
        $(".zhezhao").addClass("dis-none");
        $(".popup-daozhang").addClass("dis-none");
    });
});