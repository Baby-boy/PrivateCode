$(function(){
	//我的订单
	$("body").on('click','.d-right ul.d-top li',function(){
        $(this).addClass('d-edit').siblings().removeClass('d-edit');
        $(this).parent().siblings('.pop').eq($(this).index()).css('display','block').siblings('.pop').css('display','none');
    });
		


})