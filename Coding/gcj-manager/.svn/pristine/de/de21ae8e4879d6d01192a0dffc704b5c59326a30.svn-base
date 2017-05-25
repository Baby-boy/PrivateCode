$(function(){
	$(".rewuselect").select({
		change:function () {
			// 下拉菜单内容改变事件
		}
	});
	$(".rewuselect2").select();
	$(".rewuselect3").select();

	$(".yd-clearing").click(function () {
		$(".yd-type select").attr("data-value","");
		$(".yd-type select").val("");
		$(".select_text").each(function (i) {
			$(".select_text").eq(i).text($(".yd-type select").eq(i).find("option").eq(0).text())
		})
	});




	// 任务大厅导航栏滑动
	$('.yd-detail li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
		$(this).parents('.yd-detail-big').siblings('.yd-detail-text').eq($(this).index()).show().siblings('.yd-detail-text').hide();
	});
	// 任务大厅项目类型
	$(".yd-major-type li").click(function () {
		var index=$(this).index();
		if(index==0){
			$(this).addClass("active").siblings().removeClass("active");
		}else{
			$(".yd-major-type li").eq(0).removeClass("active");
			$(this).toggleClass("active");
		}
		condition();
	});
	// 任务互动 回复
	$('.yd-good-plan').click(function(){
		$(this).parents('.yd-good-left').siblings('.yd-good-replan').show(200);
	});
	
	$(".yd-good-replan .back").click(function(){
		$(this).parents(".yd-good-replan").hide(200);
	});

	// 任务大厅
	$('.yd-sort ul li').click(function(){
		$(this).addClass('active').siblings().removeClass('active');
	});


	//任务页面 页数
	$('.yd-body-pager .yd-body-left').click(function(){
		var num =$('.yd-all-pager i').text();
		if(num > 1){
			num--;
		}
		$(this).next("span").find('i').text(num);
	});
	$('.yd-body-pager .yd-body-right').click(function(){
		var num =$('.yd-all-pager i').text();
		if( num < 4){
			num++;
		}
		$(this).prev("span").find('i').text(num);
	});

	
});