var shop = {};
var where = {};
$(function(){
	
	
	/**
	 * 商家商品
	 */
	shop.store_goods_all = {
		url : function(args){
			return "/store/goods";
		},
		tpl: function(args){
			return "shop/public/store_goods::main";
		},
		params : function(args){
			if (args[1] == undefined) {
				where["p"] = 1;
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where; 
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$(".store-pro-list").html(response.responseText);
		}
	};
	
	shop.store_info = {
		url : function(args){
			return "/store/info";
		},
		params : function(args){
			return where; 
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			var _storeInfo = response.dataStoreInfo;
			var bannerData = JSON.parse( _storeInfo.store_banner );
			var str = "";
			$.each(bannerData, function(k, v) {
				str += '<li><a target="_blank" href="'+v.href+'"><img src="'+_urlOther+v.src+'" alt="" title="" /></a></li>';
			});
			$(".store-slides").html( str );
		}
	};
	
	
	/**
	 * 获取商品评价
	 */
	shop.goods_comment = {
		url : function(args){
			return "/comment/lists";
		},
		tpl: function(args){
			return "shop/public/goods_comment::main";
		},
		params : function(args){
			
			if ($(args[0]).attr("data-state") == undefined) {
				var st = $(".comments-list ul li.active").attr("data-state");
				if ( st == "" ) {
					st = 0;
				}
				where["state"] = st;
			} else {
				where["state"] = $(args[0]).attr("data-state");
			}
			
			
			if (args[1] == undefined) {
				where["p"] = 1;
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#goodsComments").html(response.responseText);
			
			pagination(".pagination ul", $("#totalPage").val(), $("#p").val(), "getGoodsComment");
			
		}
	};
	
	
	
	/*
	shop.news_lists = {
		url : function(args){
			return "/news";
		},
		params : function(args){
			return where; 
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			console.log( response );
		}
	};
	*/
});


/**
 * 流通提交
 */
function onlinebookingStore() {
	if ( formValue["check"] != 1 ) {
		$.alert({
			type: 4,
			info: "请勾选  同意《流通置换》协议",
			callback: ""
		});
		return;
	}
	formValue["goods_pics"] = _goodsPics.join(",");
	formValue["type"] = 3;
	formValue["goods_cover"] = "";
	if ( _goodsPics.length > 0 ) {
		formValue["goods_cover"] = _goodsPics[0];
	}
	$.ajax(ajaxSettings({
	    url: '/circulation/appointment',
	    type: 'post',
	    data: formValue,
	    success: function(data) {
	    	if (data.success) {
	    		$.alert({
	    			type: 4,
	    			info: data.info,
	    			callback: ""
	    		});
	    		setTimeout(function() {	    			
	    			window.location.reload();
	    		}, 2000);
	    	} else {
	    		errorShopAlert(data);
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}));
}