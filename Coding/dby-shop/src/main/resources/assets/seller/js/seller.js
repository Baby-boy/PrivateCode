
$(document).ready(function(){
	$(".Z-xinbie span").click(function(){
		$(this).addClass("active").siblings("span").removeClass("active");
	});

	//设为默认
	$('.Z-add_con ul li .Z-con_state').click(function(){
		$(this).addClass('Z-con_state1').removeClass('Z-con_state').html('默认').parent('li').siblings('li').find('.Z-conn').removeClass('Z-con_state1').addClass('Z-con_state').html('设为默认');
	});

	//页数
	$('.Z-page ul li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	});


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
	$(".Z-shoujiyanzhen a").click(function(){
        $(this).hide();
        $(".send_code").css("display","block");
        $(".send_code .repeat").css("display","block");
		obt();
    });
    $(".Z-shoujiyanzhen .send_code .obtain").click(function(){
        $(this).hide();
        $(".Z-shoujiyanzhen .send_code .repeat").css("display","block");
        obt();
    });	



    //普通用户注册
    $(".Z-twoinput a").click(function(){
        $(this).hide();
        $(".Z-twoinput .send_code").css("display","block");
        $(".Z-twoinput .send_code .repeat").css("display","block");
            obt();
    });
    $(".Z-twoinput .send_code .obtain").click(function(){
        $(this).hide();
        $(".Z-twoinput .send_code .repeat").css("display","block");
        obt();
    });	


    //企业用户注册
    $(".Z-twoinput.Z-ttwo a").click(function(){
        $(this).hide();
        $(".Z-twoinput.Z-ttwo .send_code").css("display","block");
        $(".Z-twoinput.Z-ttwo .send_code .repeat").css("display","block");
            obt();
    });
    $(".Z-twoinput.Z-ttwo .send_code .obtain").click(function(){
        $(this).hide();
        $(".Z-twoinput.Z-ttwo .send_code .repeat").css("display","block");
        obt();
    });	


    //上传图片
    $(".Z-daimabox .Z-rightshangchuan").click(function(){
		$(this).next("input").click();
	});
	
	// 图片选择
	
	$(".head_img").on("change",function(){
       	var objUrl = getObjectURL(this.files[0]) ;
       	if (objUrl) {
	        $(this).siblings('.Z-lefttext').html("<img src='" + objUrl + "' />");
       	}
   });


	//收货地址删除
    $('.Z-con_operation .Z-delete').click(function(){
        if (confirm("是否确认删除该地址？")) {
            $(this).parents('li').remove()
        };
    });

    $('.Z-con_operation .Z-edit').click(function(){
        $('.Z-jump').css('display','block');
    });
    $('.Z-jump span').click(function(){
        $(this).parents('.Z-jump').css('display','none');
    });
	

});
//创建Blob对象
function getObjectURL(file) {
  	var url = null ;
  	if (window.createObjectURL!=undefined) { 
    	url = window.createObjectURL(file) ;
  	} else if (window.URL!=undefined) {
    	url = window.URL.createObjectURL(file) ;
  	} else if (window.webkitURL!=undefined) {
    	url = window.webkitURL.createObjectURL(file) ;
  	}
  	return url ;
}
