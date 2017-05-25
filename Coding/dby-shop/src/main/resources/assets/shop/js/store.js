$(function(){
	$(".store .left .sorts ul li h3 em").click(function(){
		$(this).parents("li").find("dl").slideToggle(500);
		$(this).parents("li").toggleClass("active");
	});
});
