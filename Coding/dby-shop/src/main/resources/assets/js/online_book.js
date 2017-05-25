var c2c = {};
$(function(){
	
	$(".enclosure div span").click(function(){
		$(this).next("input").click();
	});
	
	// 删除图片
	$(".enclosure dl").on("click","dd i",function(){
		$(this).parents("dd").remove();
		uploader.files.splice($(this).parents("dd").index(),1);
		$(".moxie-shim-html5").css({
			left:$("#selectfiles").position().left,
			top:$("#selectfiles").position().top
		});
	});
	
	// 图片选择
	
	$('.select-city').cxSelect({
		selects: ['province', 'city', 'area'],
		nodata: 'none',
		url:"/assets/shop/js/city.json"
	});
	
	c2c.release = {
		url : function(args){
			return "/api/shop/c2c";
		},
		params : function(args){
			return args;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, data){
			if(data.data === 1){
				$.alert({
					type:4,
					text:"提交成功"
				});
			}else{
				$.alert({
					type:3,
					text:"提交失败"
				});
			}
		}
		
	};
	
	c2c.pics = [];
	
	$(".c2c-release ul li .submit input").click(function(){
		var json = {
			"user_id":1,
			"ctc_name":$("input[name='ctc_name']").val(),
            "ctc_summary":$("textarea[name='ctc_summary']").val(),
            "ctc_pics":"["+c2c.pics+"]",
            "ctc_price":$("input[name='ctc_price']").val(),
            "ctc_province_id":$("select.province").val(),
            "ctc_city_id":$("select.city").val(),
            "ctc_province_value":$("select.province").find("option:selected").html(),
            "ctc_city_value":$("select.city").find("option:selected").html()
		}
		c2c_release(json);
	});
	
});

// 创建Blob对象
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