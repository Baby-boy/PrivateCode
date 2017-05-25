/*倒计时*/
var time_f;
function obt() {
	clearInterval(time_f);
    var time=60;
   
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
	

	
//    obt = function(){
//    	
//    }


	//倒计时
    /*$("body").on("click", ".Z-shoujiyanzhen a", function() {
        $(this).hide();
        $(".send_code").css("display","block");
        $(".send_code .repeat").css("display","block");
		obt();
    });
    $("body").on("click", ".Z-shoujiyanzhen .send_code .obtain", function() {
        $(this).hide();
        $(".Z-shoujiyanzhen .send_code .repeat").css("display","block");
        obt();
    });	*/



    //普通用户注册
//    $(".Z-twoinput a").click(function(){
//        $(this).hide();
//        $(".Z-twoinput .send_code").css("display","block");
//        $(".Z-twoinput .send_code .repeat").css("display","block");
//            obt();
//    });
    $(".Z-twoinput .send_code .obtain").click(function(){
//        $(this).hide();
//        $(".Z-twoinput .send_code .repeat").css("display","block");
//        obt();
    	$(this).prev().click();
    });	


    //企业用户注册
    $(".Z-twoinput.Z-ttwo a").click(function(){
//        $(this).hide();
//        $(".Z-twoinput.Z-ttwo .send_code").css("display","block");
//        $(".Z-twoinput.Z-ttwo .send_code .repeat").css("display","block");
//            obt();
    });
    $(".Z-twoinput.Z-ttwo .send_code .obtain").click(function(){
//        $(this).hide();
//        $(".Z-twoinput.Z-ttwo .send_code .repeat").css("display","block");
//        obt();
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
	
    // 订单列表 未付款 - 发起支付
    $("body").on("click", "a.order-pay", function() {
    	memberOrderPay( $(this) );
    });
    
    // 退款
    $("body").on("click", ".order-refund", function() {
    	memberOrderFefund( $(this) );
    });
    
    // 交易投诉
    $("body").on("click", ".btn-order-complain", function() {
    	memberOrderComplain( $(this) );
    });
    
    // 加-减
    $("body").on("click", ".num-control span", function() {
    	var cl = $(this).attr("class");
    	var vl = parseInt( $(".num-control input").val() );
    	if ( cl == "reduce" ) {	// 减
    		if ( vl > 1 ) {
    			vl--;
    			$(".num-control input").val( vl );
    		}
    	} else if ( cl == "plus" ) {	// 加
    		if ( vl < _refund_num ) {
	    		vl++;
	    		$(".num-control input").val( vl );
    		}
    	}
    });

    
    // 退款图片
	$("body").on("click", ".refund-pics-list i", function() {
		for(var i = 0; i < _goodsPics.length; i++) {
			if ( _goodsPics[i] == $(this).attr("data-img") ) {
				_goodsPics.splice( i, 1 );
			}
		}
		$(this).parent().remove();
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
