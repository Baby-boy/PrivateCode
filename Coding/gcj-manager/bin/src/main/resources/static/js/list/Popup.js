$(function(){
	$(".xieyi a").click(function(){
		$(document.body).css({"overflow":"hidden","padding-right":"17px"});
		$(".yd-fabao-xieyi").removeClass("dis-none");
	});
	$(".yd-fabao-xieyi .guanbi,.yd-fabao-xieyi button").click(function(){
		$(".yd-fabao-xieyi").addClass("dis-none");
		$(document.body).css({"overflow":"auto","padding-right":"0"});		
	});
	$(".xieyi input").click(function(){
		if($(this)[0].checked==true){
			$(".hall-box .fabu button").addClass("now");
		}else{
			$(".hall-box .fabu button").removeClass("now");
		}
	})
})