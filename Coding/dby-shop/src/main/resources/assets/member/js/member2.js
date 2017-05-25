$(function(){
	//个人中心  订单
 	$('.g-otop .omonth').click(function(){
 		$(this).addClass('store').siblings().removeClass('store');
 		$('.g-obottom').eq($(this).index()).css('display','block').siblings('.g-obottom').css('display','none');
 	});

 	//页数
 	$('.g-pages ul li').click(function(){
 		$(this).addClass('present').siblings('li').removeClass('present');
 	});

 	//热门推荐
 	$("body").on("click", ".g-title ul li", function() {
 		$(this).addClass('hcolor').siblings().removeClass('hcolor');
 		$('.g-substance').eq($(this).index()).css('display','block').siblings('.g-substance').css('display','none');
 	});
 	
    //首页取消按钮
    $('.good7 span').click(function(){
        if(confirm('是否确认取消该订单？')){
            $(this).parents('ul').remove();
        };   
    });

	//我的订单
	$('.d-right ul.d-top li').click(function(){
		$(this).addClass('d-edit').siblings().removeClass('d-edit');
		$(this).parent().siblings('.pop').eq($(this).index()).css('display','block').siblings('.pop').css('display','none');
	});

	//我的拍卖
	$('.p-sale.d-right ul.d-top li').click(function(){
		$(this).parent().siblings('.p-join').eq($(this).index()).css('display','block').siblings('.p-join').css('display','none');
	});

	//留言
	$('.f-words li.f-receive').click(function(){
		$(this).removeClass('f-release').addClass('f-receive').siblings().removeClass('f-receive');
		$(this).parents('.f-words').next('.f-words2').css('display','block').siblings('.f-words2').css('display','none');
	})
	$('.f-words li.f-release').click(function(){
		$(this).removeClass('f-release').addClass('f-receive').siblings().removeClass('f-receive');
		$(this).parents('.f-words').siblings('.f-words2.f-wor').css('display','block').siblings('.f-words2').css('display','none');
	})

	//删除订单
	$('.all-operta').click(function(){
		$('.s-popup').css('display','block');
	});
	$('.s-delete img').click(function(){
		$('.s-popup').css('display','none');
	});
	$('.bnt.cancel').click(function(){
		$('.s-popup').css('display','none');
	});

	//倒计时
	$("#form ul li .gain").click(function(){
        $(this).hide();
        $(".send_code").css("display","block");
        $(".send_code .repeat").css("display","block");

        /*倒计时*/
        obt=function(){
            var time=60;
            var time_f;
            time_f=setInterval(function(){
                if(time==1){
                    clearInterval(time_f);
                    $(" .send_code .repeat").hide();
                    $(".send_code .obtain").show().html("重新获取");
                    $(" .send_code .repeat .count_down").html(60);
                }else{
                    time--;
                    $(" .send_code .repeat .count_down").html('已发送'+'('+time+'s)');
                }
            },1000);
            }
            obt();
    });
      $(".send_code .obtain").click(function(){
        $(this).hide();
        $(".send_code .repeat").css("display","block");
        obt();
    });	

    //上传照片
    $('.t-photo span').click(function(){
    	$(this).parent().siblings('input').click();
    });

    //checked
    $('.t-choice input[type=checkbox]').click(function(){
    	if($(this).is(':checked')){
    		$(this).parents(".t-bottom").find(".table").show();
    	}else{
    		$(this).parents('.t-bottom1').siblings('.table').css('display','none');
    	};
    });

    //我的懒鱼的删除
    $('.f-operate span.delete').click(function(){
    	if(confirm('是否确认删除该订单？')){
    		$(this).parents('.f-number2').remove();
    	};
    });

    //我的消息
    // $('.f-slide').click(function(){
    // 	$(this).prev('span').slideToggle();
    // });
    // 
    //我的预约取消
    $('.y-bespoke .y-operat').click(function(){
    	if(confirm('是否该取消预约')){
    		$(this).parents('ul').remove();
    	};
    });

    //我的订单
    $('.d-show3 img').click(function(){
    	if(confirm('是否确认删除该订单？')){
    		$(this).parents('.d-show2').remove();
    	};
    });

    //我的收藏
    $('.j-stop ul input[type=checkbox]').click(function(){
    	if($(this).is(':checked')){
    		$(".j-sbottom ul li.j-al input[type=checkbox]").prop('checked','checked');
    		$('.j-choose .choose1 input').prop('checked','checked');
    	}else{
    		$(".j-sbottom ul li.j-al input[type=checkbox]").removeProp('checked');
    		$('.j-choose .choose1 input').removeProp('checked');
    	};
    });


    $('.j-choose .choose1 input').click(function(){
    	if($(this).is(':checked')){
    		$(".j-sbottom ul li.j-al input[type=checkbox]").prop('checked','checked');
    		$('.j-stop ul input[type=checkbox]').prop('checked','checked');
    	}else{
    		$(".j-sbottom ul li.j-al input[type=checkbox]").removeProp('checked');
    		$('.j-stop ul input[type=checkbox]').removeProp('checked');
    	};
    });

    //我关注的店铺
    $('.j-stop ul input[type=checkbox]').click(function(){
    	if($(this).is(':checked')){
    		$(this).parents('.j-stop').siblings('.j-sbottom').find('.logo-left .logo-top input[type=checkbox]').prop('checked','checked');
    		$(this).parents('.j-stop').siblings('.j-choose').find('.choose1 input').prop('checked','checked');
    	}else{
    		$(this).parents('.j-stop').siblings('.j-sbottom').find('.logo-left .logo-top input[type=checkbox]').removeProp('checked');
    		$(this).parents('.j-stop').siblings('.j-choose').find('.choose1 input').removeProp('checked');
    	};
    });


    $('.j-choose .choose1 input').click(function(){
    	if($(this).is(':checked')){
    		$(this).parents('.j-choose').siblings('.j-sbottom').find("input[type=checkbox]").prop('checked','checked');
    		$(this).parents('.j-choose').siblings('.j-stop').find('ul input[type=checkbox]').prop('checked','checked');
    	}else{
    		$(this).parents('.j-choose').siblings('.j-sbottom').find("input[type=checkbox]").removeProp('checked');
    		$(this).parents('.j-choose').siblings('.j-stop').find('ul input[type=checkbox]').removeProp('checked');
    	};
    });



    //判断长度
    var len;
    $('.j-sbottom ul input[type=checkbox]').click(function(){
    	len = $(this).parents(".j-sbottom").find("input[type='checkbox']").length;
    	var checkedL = $(this).parents(".j-sbottom").find(":checked").length ;
    	if( checkedL == len ){
    		$(this).parents(".j-shop").find(".j-stop input,.j-choose input").prop("checked","checked");
    	} else{
    		$(this).parents(".j-shop").find(".j-stop input,.j-choose input").removeProp("checked");
    	};

    });

    var shopLen;
    $('.j-sbottom .logo-top input[type=checkbox]').click(function(){
    	shopLen=$(this).parents('.j-sbottom').find('input[type=checkbox]').length;
    	var s_checkedL=$(this).parents('.j-sbottom').find(':checked').length;
    	if(s_checkedL==shopLen){
    		$(this).parents(".j-shop").find(".j-stop input,.j-choose input").prop("checked","checked");
    	} else{
    		$(this).parents(".j-shop").find(".j-stop input,.j-choose input").removeProp("checked");
    	};

    });


})