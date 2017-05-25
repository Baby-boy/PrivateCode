$(function(){
	$(".reg-switch").click(function(){
		$(this).addClass("active").siblings(".reg-switch").removeClass("active");
		$(".reg-section").eq($(this).index()).show().siblings(".reg-section").hide();
		if($(this).index()==1){
			$(".Z-contentBox .Z-twomenu div.Z-fuwuxuzhi").show();
		}else{
			$(".Z-contentBox .Z-twomenu div.Z-fuwuxuzhi").hide();
		}
	});
	
	$(".Z-firstinputt input[type='password']").bind("keyup",function(){
		var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
		var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
		var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		if (false == enoughRegex.test($(this).val())) {
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(2).removeClass("Z-libac3");
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(1).removeClass("Z-libac2");
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(0).removeClass("Z-libac1");
		} else if (strongRegex.test($(this).val())) {
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(0).addClass("Z-libac1");
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(1).addClass("Z-libac2");
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(2).addClass("Z-libac3");
		} else if (mediumRegex.test($(this).val())) {
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(2).removeClass("Z-libac3");
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(1).addClass("Z-libac2");
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(0).addClass("Z-libac1");
		}else{
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(2).removeClass("Z-libac3");
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(1).removeClass("Z-libac2");
			$(".Z-zhanghu .Z-anquanchengdu ul li").eq(0).addClass("Z-libac1");
		}
		return true;
	});
});