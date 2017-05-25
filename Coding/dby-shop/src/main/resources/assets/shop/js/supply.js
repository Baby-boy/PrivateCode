$(function(){
	/*倒计时*/
    obt=function(){
        var time=60;
        var time_f;
        time_f=setInterval(function(){
            if(time==1){
                clearInterval(time_f);
                $(" .send_code .repeat").hide();
                $(".send_code .obtain").show().html("重新获取");
                $(" .send_code .repeat .count_down").html('已发送'+'('+60+'s)');
            }else{
                 time--;
                $(" .send_code .repeat .count_down").html('已发送'+'('+time+'s)');
            }
        },1000);
    }


	//倒计时
	$(".min-word a").click(function(){
        $(this).hide();
        $(".send_code").show();
        $(".send_code .repeat").show();
		obt();
    });
    $(".min-word .send_code .obtain").click(function(){
        $(this).hide();
        $(".min-word .send_code .repeat").css("display","block");
        obt();
    });	
    
    //取消
    $('.min-tip .min-top i').click(function(){
    	$('.tip-box').hide();
    })
});


