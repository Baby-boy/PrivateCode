$(function(){
	$(".jqzoom").imagezoom();
				
	$(".product-show .thumbnail ul li").mouseenter(function(){
		$(this).addClass("active").siblings().removeClass("active");
		if( $(this).index() === 0 ){
			$(".detail-video").show();
			$(".big-picture").hide();
		}else{
			$(".detail-video").hide();
			$(".big-picture").show();
			$(".big-picture").find("a").attr("href",$(this).find("img").attr("src"));
			$(".big-picture").find("img").attr("src",$(this).find("img").attr("src"));
			$(".big-picture").find("img").attr("rel",$(this).find("img").attr("big"));
		}
	});
	
	// 评论列表图片点击
	$("#comment-0").on("click",".p-show-img ul li",function(){
		$(this).parents(".p-show-img").next(".show-img-list").addClass("show-img-active");
	});
	
	$("#comment-0").on("click",".img-list ul li",function(){
		$(this).addClass("active").siblings().removeClass("active");
		$(this).parents(".thumb-list").next(".big-img").find("img").attr("src", $(this).find("img").attr("src") );
	});
	
	function moveImg( obj , direction , num ){
		marginLeft = parseInt(obj.parent().find(".img-list ul").css("margin-left"));
		var liObj = obj.parent().find(".img-list ul li");
		var liIndex = obj.parent().find(".img-list ul li.active").index();
		var ulWidth = liObj.length * (liObj.outerWidth()+10);
		var divWidth = (liObj.outerWidth()+10) * 7 ;
		if( direction === 1 ){
			marginLeft += (liObj.outerWidth()+10)*num;
			liIndex--;
			if( liIndex < 0 ){
				marginLeft = -(divWidth);
			}else{
				if(marginLeft > 0 ){
					marginLeft = 0;
				}
			}
		}else{
			marginLeft -= (liObj.outerWidth()+10)*num;
			liIndex++;
			if( liIndex > liObj.length-1 ){
				if( marginLeft < -(ulWidth - divWidth) ){
					marginLeft = 0 ;
					liIndex=0;
				}
			}
			
		}
		liObj.eq(liIndex).addClass("active").siblings().removeClass("active");
		obj.parent().next(".big-img").find("img").attr("src",liObj.eq(liIndex).find("img").attr("src"));
		obj.parent().find(".img-list ul").animate({"margin-left": marginLeft+"px"},500);
	}
	
	
	var dateTime = new Date();
	$("#comment-0").on("click",".thumb-list a.next",function(){
		if(new Date()-dateTime>500){
			moveImg( $(this) , 2 , 1 );
			dateTime = new Date();
		}
	});
	
	$("#comment-0").on("click",".thumb-list a.prev",function(){
		if(new Date()-dateTime>500){
			moveImg( $(this) , 1 , 1 );
			dateTime = new Date();
		}
	});
	
	$("#comment-1").on("click",".thumb-list a.next",function(){
		if(new Date()-dateTime>500){
			moveImg( $(this) , 2 , 1 );
			dateTime = new Date();
		}
	});
	
	$("#comment-1").on("click",".thumb-list a.prev",function(){
		if(new Date()-dateTime>500){
			moveImg( $(this) , 1 , 1 );
			dateTime = new Date();
		}
	});
	
	// 关闭图片组
	$("#comment-0").on("click",".big-img img",function(){
		$(this).parents(".show-img-list").removeClass("show-img-active");
	});
	
	// 内容tab切换
	$(".details-page .top-screen ul li").click(function(){
		$(this).addClass("active").siblings().removeClass("active");
		$(".tab-switch").eq( $(this).index() ).show().siblings(".tab-switch").hide();
	});
	
	
	$(".tab-main ul li").click(function(){
		var index = $( this ).index();
		$(this).addClass("active").siblings().removeClass("active");
		switch(index){
			case 0 :
				if( $(".tab-con .screen-item").eq(index).find(".comments-item").length < 1 ){
					console.log("要执行的ajax");
				}
			break;
			case 1 :
				if( $(".tab-con .screen-item").eq(index).find(".img-list ul li").length < 1 ){
					console.log("要执行的ajax");
				}
			break;
			case 2 :
				if( $(".tab-con .screen-item").eq(index).find(".comments-item").length < 1 ){
					console.log("要执行的ajax");
				}
			break;
			case 3 :
				if( $(".tab-con .screen-item").eq(index).find(".comments-item").length < 1 ){
					console.log("要执行的ajax");
				}
			break;
			case 4 :
				if( $(".tab-con .screen-item").eq(index).find(".comments-item").length < 1 ){
					console.log("要执行的ajax");
				}
			break;
		}
		$(".tab-con .screen-item").eq( index ).show().siblings().hide();
	});
	
	
	// 倒计时
	if( $(".end-time").length > 0 ){
		setInterval(function(){
			updateEndTime(".end-time");
		},1000);
	}
	
	
	// 
	$(".product-show .right-info .bid-record a").click(function(){
		$("html,body").animate({"scrollTop":$("#container").position().top-100},500);
		$(".details-page .top-screen ul li").eq(1).addClass("active").siblings().removeClass("active");
		$(".tab-switch").eq( 1 ).show().siblings(".tab-switch").hide();
	});
	
});


