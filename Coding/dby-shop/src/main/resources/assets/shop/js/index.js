$(function(){
	
	var autoSlideBanner;
	//banner
	function slideBanner( num ){
		if(num > $( "#main .main-top .banner dl dd" ).length - 1 ){
			num = 0 ;
		}
		$( "#main .main-top .banner ul li" ).eq(num).fadeIn().siblings().fadeOut();
		$( "#main .main-top .banner dl dd" ).eq(num).addClass("active").siblings().removeClass("active");
	}
	
	slideBanner(0);
	
	autoSlideBanner = setInterval(function(){
		slideBanner( $( "#main .main-top .banner dl dd.active" ).index() + 1 );
	},5000);
	
	$( "#main .main-top .banner dl dd" ).click(function(){
		clearInterval(autoSlideBanner);
		slideBanner( $(this).index() );
		autoSlideBanner = setInterval(function(){
			slideBanner( $( "#main .main-top .banner dl dd.active" ).index() + 1 );
		},5000);
	});
	$(".container .section .col-title ul li").mouseenter(function(){
		$(this).addClass("active").siblings().removeClass("active");
		$(this).parents(".section").find(".list").eq( $(this).index() ).show().siblings(".list").hide();
	});
	
	
	// 热门推荐
	var marginLeft = 0 ;
	var dateTime = new Date();
	function slidePic(direction,num){
		var liObj = $("#main .hot-recom .list ul li");
		var ulWidth = liObj.length * liObj.outerWidth();
		var divWidth = liObj.outerWidth() * 5 ;
		if( direction === 1 ){
			marginLeft += liObj.outerWidth()*num;
			if(marginLeft>0){
				marginLeft = -(ulWidth - divWidth);
			}
		}else{
			marginLeft -= liObj.outerWidth()*num;
			if( marginLeft < -(ulWidth - divWidth) ){
				marginLeft = 0 ;
			}
		}
		$("#main .hot-recom .list ul").animate({"margin-left": marginLeft+"px"},500);
	}
	
	$("#main .hot-recom .col-title a.next").click(function(){
		if(new Date()-dateTime>500){
			slidePic(0,2);
			dateTime = new Date();
		}
	});
	$("#main .hot-recom .col-title a.prev").click(function(){
		if(new Date()-dateTime>500){
			slidePic(1,2);
			dateTime = new Date();
		}
	});
	
	// 楼层锚点
	$(window).scroll(function(){
		var arr = [];
		$.each($(".container .section"),function(index,value){
			arr.push( $(this).offset().top - $("html,body").scrollTop() );
		});
		if( arr[0] < 400 ){
			$(".floor-anchor").show();
		}else{
			$(".floor-anchor").hide();
		}
		$.each(arr, function(index,value) {
			if( value < 200 && value > 0 ){
				$(".floor-anchor ul li").eq(index).addClass("active").siblings().removeClass("active");
			}
		});
	});
	
	$("body").on("click",".floor-anchor ul li",function(){
		$(this).addClass("active").siblings().removeClass("active");
		var setTop = $(".container .section").eq( $(this).index() ).offset().top - 200;
		$("html,body").animate({
			scrollTop:setTop
		},500);
	});
	
	
	// 右边菜单
	$("body").on("mouseenter","ul.right-menu li span",function(){
		$(this).parents("a").find(".text").stop().animate({"right":"0px"},200);
	}).on("mouseleave","ul.right-menu li span",function(){
		$(this).parents("a").find(".text").stop().animate({"right":"-100px"},200);
	});
	
	$("body").on("click","ul.right-menu li.back-top",function(){
		$("html,body").animate({
			scrollTop:0
		},500);
	});
	
});
