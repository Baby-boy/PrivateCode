var c2c = {};
var online_book = {};
$(function(){
	
	/*
	$(".enclosure div span").click(function(){
		$(this).next("input").click();
	});
	*/
	// 删除图片
	$("#ossfile").on("click", "i", function() {
		for(var i = 0; i < _goodsPics.length; i++) {
			if ( _goodsPics[i] == $(this).attr("data-img") ) {
				_goodsPics.splice( i, 1 );
			}
		}
		$(this).parent().remove();
	});
	
	
	// 删除图片
	/*
	$("body").on("click",".enclosure dl dd i",function(){
		$(this).parents("dd").remove();
		uploader.files.splice($(this).parents("dd").index(),1);
		$(".moxie-shim-html5").css({
			left:$("#selectfiles").position().left,
			top:$("#selectfiles").position().top
		});
	});
	*/
	
	// 图片选择
	
	$("#container .online-book ul li").eq(2).cxSelect({
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
					text:"提交成功",
					callback:function(){
						c2c.pics = [];
					}
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
	
	/*
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
		console.log(json);
		c2c_release(json);
	});
	*/
	
	$("#container .online-book ul li .submit input").click(function(){
		if($("#container .online-book ul li .agreement input").is(":checked")){
			var json = {
				"type":$(this).attr("data-type"),
				"user_name":$("input[name='user_name']").val(),
	            "user_phone":$("input[name='user_phone']").val(),
	            "goods_summary":$("textarea[name='goods_summary']").val(),
	            "goods_name":$("input[name='goods_name']").val(),
	            "goods_pics":"["+c2c.pics+"]",
	            "goods_cover":c2c.pics[0],
	            "address_province_id":$("select.province").val(),
	            "address_city_id":$("select.city").val(),
	            "address_area_id":$("select.province").val(),
	            "address_street":$("input[name='address_street']").val()
			}
			online_book(json);
		}else{
			$.alert({
				type:2,
				text:"请同意流通协议"
			});
		}
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