$(function(){
	var pagedate={
		pageCount:3,
		current:1,
		backFn:function(p){
			//console.log(p);
		}
	};
	$(".tcdPageCode").createPage(pagedate);

	$('.yd-trade-title li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$(this).parent().siblings('.yd-online-content').eq($(this).index()).show().siblings('.yd-online-content').hide();
	})
})