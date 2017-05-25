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
		var divWidth = liObj.outerWidth() * 4 ;
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
	
});
