var cart_json;
var cart_ajax = {};
// var _urlGoods = "http://woadby.oss-cn-beijing.aliyuncs.com/dby/";
// var _urlOther = "http://woadby.oss-cn-beijing.aliyuncs.com/dby/";
var _urlGoods = "https://woadby.oss-cn-beijing.aliyuncs.com/dby/";
var _urlOther = "https://woadby.oss-cn-beijing.aliyuncs.com/dby/";
var _mobileReg = /^1[3|4|5|7|8][0-9]\d{8}$/;
var _cartOn = true;
$(function(){
	
	//$("img").lazyload({effect: "fadeIn"});
	
	// 头部菜单
	$("#header .top-bar .right ul li.more,#header-store .top-bar .right ul li.more,#header-cart .top-bar .right ul li.more").bind({
		"mouseenter":function(){
			if( $(this).find(".qr-code").length > 0 ){
				$(this).find(".qr-code").show();
			}else if( $(this).find(".more-list").length > 0){
				$(this).find(".more-list").show();
			}
		},
		"mouseleave":function(){
			if( $(this).find(".qr-code").length > 0 ){
				$(this).find(".qr-code").hide();
			}else if( $(this).find(".more-list").length > 0){
				$(this).find(".more-list").hide();
			}
		}
	});
	
	// 搜索类型选择
	$("#header .search .select span").click(function(){
		$(this).next("ul").slideToggle(200);
	});
	
	$("#header .search .select ul li").click(function(){
		$(this).parent().slideToggle(200);
		$(this).parent().prev("span").html( $(this).html() );
		$(this).parent().next("input").val( $(this).attr("data-val") );
	});
	
	// 购物车展开
	$("#header .head-layout .cart").bind({
		"mouseenter":function(){
			$(this).addClass("active-cart");
			// 获取数据
			if ( _cartOn ) {				
				cart_window();
			}
		},
		"mouseleave":function(){
			$(this).removeClass("active-cart");
		}
	});
	
	
	$(".cart").on("click", ".del a", function() {
		cart_delete($(this));
	});
	
	
	function common_total(){
	    var $box = $(".cart .eject-cart .yes-product ul li");
	    var priceList = [];
	    var total = 0;
	    var number = 0;
	    var totalNum = 0 ;
	    $box.each(function( index , value ){
	        var price_sp = $box.eq( index ).find(".price strong").eq(0).html().split("¥");
	        var price = price_sp[1];
	        var num =$box.eq( index ).find(".price strong").eq(1).html();
	        priceList[index] = price+","+num;
	    });
	    $.each( priceList ,function( index ,value ){
	       var price_num = value.split(","); 
	       total += parseFloat( parseFloat(price_num[0]) * parseInt( price_num[1]) ) ;
	       number += parseInt( price_num[1]);
	    });
	    
	    $(".yes-product ul li .price-del .price strong").eq(1).each(function( index , value ){
	    	totalNum += parseInt( $(this).val() ); 
	    });
	    $(".cart .eject-cart .yes-product .go-end span small").eq(0).html( number );
	    $(".cart .eject-cart .yes-product .go-end span small").eq(1).html("¥"+total.toFixed(2));
	}
	common_total();
	
	
	$("#header .main-nav .nav .classify > ul > li").bind({
		"mouseenter":function(){
			if( $(this).index() > 2 ){
				if( $(this).parents(".classify").hasClass("classify1") ){
					$(this).find(".menu").css("bottom","-2px").show();
				}else{
					$(this).find(".menu").css("bottom","-1px").show();
				}
			}else{
				if( $(this).parents(".classify").hasClass("classify1") ){
					$(this).find(".menu").css("top","-2px").show();
				}else{
					$(this).find(".menu").css("top","-1px").show();
				}
				
			}
			$(this).addClass("bor");
		},
		"mouseleave":function(){
			$(this).find(".menu").hide();
			$(this).removeClass("bor");
		}
	});
	
	$("#header-store .main-nav .nav .classify > ul > li").bind({
		"mouseenter":function(){
			if( $(this).index() > 2 ){
				if( $(this).parents(".classify").hasClass("classify1") ){
					$(this).find(".menu").css("bottom","-2px").show();
				}else{
					$(this).find(".menu").css("bottom","-1px").show();
				}
			}else{
				if( $(this).parents(".classify").hasClass("classify1") ){
					$(this).find(".menu").css("top","-2px").show();
				}else{
					$(this).find(".menu").css("top","-1px").show();
				}
				
			}
			$(this).addClass("bor");
		},
		"mouseleave":function(){
			$(this).find(".menu").hide();
			$(this).removeClass("bor");
		}
	});
	
	// 分类鼠标悬停
	$("#header-store .main-nav .nav .classify").bind({
		"mouseenter":function(){
			if( !$(this).hasClass("active") ){
				$(this).children("ul").show();
				$(this).find("ul.hidden-section").hide();
				$(this).find(".tip-text").show();
				$(this).parents(".nav").find(".more-classify").show();
			}
		},
		"mouseleave":function(){
			if( !$(this).hasClass("active") ){
				$(this).children("ul").hide();
				$(this).find("ul.hidden-section").hide();
				$(this).find(".tip-text").hide();
				$(this).parents(".nav").find(".more-classify").hide();
			}
		}
	});
	
	
	/**
	 * 收藏
	 */
	$("body").on("click", ".btn-follow", function() {
		var me = $(this);
		var dataType = 1;
		if ( $(this).attr("data-type") == "goods" ) {
			dataType = 2;
		} else if( $(this).attr("data-type") == "store" ) {
			dataType = 1;
		}else if( $(this).attr("data-type") == "ctcs" ){
			dataType = 4;
		}
		$.ajax(ajaxSettings({
		    url: '/favorite/store',
		    type: 'post',
		    data: {fav_type: dataType, fav_fid: $(this).attr("data-id")},
		    success: function(data) {
		    	if (data.success) {
		    		me.addClass("active");
		    		me.addClass("btn-follow-cancel");
		    		me.removeClass("btn-follow");
		    		$.alert({
		    			type:4,
		    			info: data.info
		    		});
		    	} else {
		    		errorShopAlert(data);
		    	}
		    },
		    error: function(data) {
		    	errorShopAlert(data);
		    }
		}));
	});
	
	
	// 取消收藏
	$("body").on("click", ".btn-follow-cancel.active", function() {
		var me = $(this);
		var dataType = 1;
		if ( $(this).attr("data-type") == "goods" ) {
			dataType = 2;
		} else if( $(this).attr("data-type") == "store" ) {
			dataType = 1;
		}else if( $(this).attr("data-type") == "ctcs" ){
			dataType = 4;
		}
		$.ajax(ajaxSettings({
		    url: '/favorite/destory',
		    type: 'post',
		    data: {fav_type: dataType, fav_fid: $(this).attr("data-id")},
		    success: function(data) {
		    	if (data.success) {
		    		me.removeClass("active");
		    		me.addClass("btn-follow");
		    		$.alert({
		    			type:4,
		    			info: data.info
		    		});
		    	} else {
		    		errorShopAlert(data);
		    	}
		    },
		    error: function(data) {
		    	errorShopAlert(data);
		    }
		}));
	});
	
	
	$("#header .main-nav .nav .classify").bind({
		"mouseenter":function(){
			if( !$(this).hasClass("active") ){
				$(this).children("ul").show();
				$(this).find("ul.hidden-section").hide();
				$(this).find(".tip-text").show();
				$(this).parents(".nav").find(".more-classify").show();
			}
		},
		"mouseleave":function(){
			if( !$(this).hasClass("active") ){
				$(this).children("ul").hide();
				$(this).find("ul.hidden-section").hide();
				$(this).find(".tip-text").hide();
				$(this).parents(".nav").find(".more-classify").hide();
			}
		}
	});
	
	if(!$("#header .main-nav .nav .classify1").hasClass("active")){
		$("#header .main-nav .nav .classify1").find(".tip-text").hide();
	}
	
	$("#header").bind("mouseenter",function(){
		
	});
	
	
	
	// 菜单显示
	$("#header .main-nav .nav .more-classify,#header-store .main-nav .nav .more-classify").click(function(){
		if( $(this).hasClass("more-classify-active") ){
			$(this).removeClass("more-classify-active");
			$(this).parents(".classify").find(".hidden-section").slideUp(100);
		}else{
			$(this).addClass("more-classify-active");
			$(this).parents(".classify").find(".hidden-section").slideDown(100);
		}
	});
	
	
	// 二级banner
	if( $(".second-banner .banner").length > 0 ){
		$(".second-banner .banner").flexslider({
			animation : 'slide',
			controlNav : true,
			directionNav : true,
			animationLoop : true,
			slideshow : true,
			useCSS : false,
			slideshowSpeed:5000,
			nextText: '&gt;',
        	prevText: '&lt;'
		});
	}
	
	if( $(".second-banner .banner-2").length > 0 ){
		$(".second-banner .banner-2").flexslider({
			animation : 'slide',
			controlNav : true,
			directionNav : true,
			animationLoop : true,
			slideshow : true,
			useCSS : false,
			slideshowSpeed:5000,
			nextText: '&gt;',
        	prevText: '&lt;'
		});
	}
	
	if( $(".second-banner .banner-3").length > 0 ){
		$(".second-banner .banner-3").flexslider({
			animation : 'slide',
			controlNav : true,
			directionNav : true,
			animationLoop : true,
			slideshow : true,
			useCSS : false,
			slideshowSpeed:5000,
			nextText: '&gt;',
        	prevText: '&lt;'
		});
	}
	
	if( $(".second-banner .banner-4").length > 0 ){
		$(".second-banner .banner-4").flexslider({
			animation : 'slide',
			controlNav : true,
			directionNav : true,
			animationLoop : true,
			slideshow : true,
			useCSS : false,
			slideshowSpeed:5000,
			nextText: '&gt;',
        	prevText: '&lt;'
		});
	}
	
	
	// 操作
	$("body").on("click", ".btn-operate-alert", function() {
		var func = $(this).attr("data-func");
		var me = $(this);
		$.confirm({
			text:"是否确认执行此操作？",
			intro:"是否确认执行此操作？",
			callback: function () {
				window[func]( me );
			}
		});
	});
	
	
	// 放入购物车
	$("body").on("click", ".add-cart", function() {
		var goods_json = {
			depot_id : $(this).attr("data-id")
		}
		cart_store(goods_json);
	});
	
});

function updateEndTime(obj){
	var date = new Date();
	var time = date.getTime();
 
 	$(obj).each(function(i){
 
 		var endDate =this.getAttribute("data-endtime"); 
 		var endDate1 = eval('new Date(' + endDate.replace(/\d+(?=-[^-]+$)/, function (a) { return parseInt(a, 10) - 1; }).match(/\d+/g) + ')');
 		var endTime = endDate1.getTime(); 
 		var lag = (endTime - time) / 1000; 
  		if(lag > 0){
   			var second = Math.floor(lag % 60);    
   			var minite = Math.floor((lag / 60) % 60);
   			var hour = Math.floor((lag / 3600) % 24);
   			var day = Math.floor((lag / 3600) / 24);
			$(this).html("<strong>"+day+"</strong>天<strong>"+hour+"</strong>时<strong>"+minite+"</strong>分<strong>"+second+"</strong>秒");
  		}else{
   			$(this).html("0天0时0分0秒");
   		}
 	});
}

function updateEndTime1(obj,current){
	var time = current*1000;
 	$(obj).each(function(i){
 		var endDate = this.getAttribute("data-endtime")*1000;
 		var lag = (endDate - time) / 1000; 
  		if(lag > 0){
   			var second = Math.floor(lag % 60);    
   			var minite = Math.floor((lag / 60) % 60);
   			var hour = Math.floor((lag / 3600) % 24);
   			var day = Math.floor((lag / 3600) / 24);
			$(this).html("剩余时间"+day+"天"+hour+"时"+minite+"分"+second+"秒");
  		}else{
   			$(this).html("0天0时0分0秒");
   		}
 	});
}

function updateEndTime2(obj,current){
	var time = current*1000;
 	$(obj).each(function(i){
 		var endDate = this.getAttribute("data-endtime")*1000;
 		var lag = (endDate - time) / 1000; 
  		if(lag > 0){
   			var second = Math.floor(lag % 60);    
   			var minite = Math.floor((lag / 60) % 60);
   			var hour = Math.floor((lag / 3600) % 24);
   			var day = Math.floor((lag / 3600) / 24);
			$(this).html("剩余时间"+day+"天"+hour+"时"+minite+"分"+second+"秒");
			if( day > 0 ){
				$(this).html("剩"+day+"天");
				return ;
			}else if( hour > 0 ){
				$(this).html("剩"+hour+"时");
				return ;
			}else if( minite > 0 ){
				$(this).html("剩"+minite+"分");
				return ;
			}else if( second > 0 ){
				$(this).html("剩"+second+"秒");
				return ;
			}
  		}else{
   			$(this).html("已结束");
   		}
 	});
}

function updateEndTime3(obj,current){
	var time = current*1000;
 	$(obj).each(function(i){
 		var endDate = this.getAttribute("data-endtime")*1000;
 		var lag = (endDate - time) / 1000; 
  		if(lag > 0){
   			var second = Math.floor(lag % 60);    
   			var minite = Math.floor((lag / 60) % 60);
   			var hour = Math.floor((lag / 3600) % 24);
   			var day = Math.floor((lag / 3600) / 24);
			$(this).html(day+"天"+hour+"时"+minite+"分"+second+"秒");
  		}else{
   			$(this).html("0天0时0分0秒");
   		}
 	});
}


/**
 * 购物车删除
 */
cart_ajax.delete = {
	url : function(args){
		return "/cart/delete";
	},
	params : function(args){
		return {cart_id: $(args).attr("data-id")};
	},
	verification : function(args, params){
		return true;
	},
	callback : function(args, data){
		if ( data.success ) {
			if ( $(args).parent().attr("class") == "del" ) {
				cart_window();
				_cartOn = false;
			} else {
				window.location.reload()
			}
		} else {
			$.alert({
				info: data.info
			});
		}
	}
};


cart_ajax.window = {
	url : function(args){
		return "/cart/window";
	},
	params : function(args){
		return {};
	},
	verification : function(args, params){
		return true;
	},
	callback : function(args, data){
		if ( data.data.length > 0 ) {
			// 购物车HTML节点追加
			$(".yes-product").css("display", "block");
			$(".no-product").css("display", "none");
			cartWindowHtml(data.data);
			$(".my-cart small").html( data.total );
		} else {
			$(".no-product").css("display", "block");
			$(".yes-product").css("display", "none");
		}
	}
};


function cartWindowHtml(data) {
	var str = "";
	for(var ele in data) {
		str += '<li th:each="item,niter:${obj}" th:inline="text">'+
					'<dl>'+
						'<dt>'+
							'<a target="_blank" href="/g/goods/'+data[ele]["depot_id"]+'">'+
								'<img src="'+_urlGoods+data[ele]["goods_cover"]+'?x-oss-process=image/resize,m_pad,h_40,w_40" />'+
							'</a>'+
						'</dt>'+
						'<dd class="title"><a target="_blank" href="/g/goods/'+data[ele]["depot_id"]+'">'+data[ele]["goods_name"]+'</a></dd>'+
					'</dl>'+
					'<div class="price-del">'+
						'<div class="price"><strong>¥'+toDecimal2(data[ele]["goods_price"])+'</strong><span>X</span><strong>'+data[ele]["cart_num"]+'</strong></div>'+
						'<div class="del"><a data-id="'+data[ele]["cart_id"]+'" href="javascript:;">删除</a></div>'+
					'</div>'+
				'</li>';
	}
	
	$("#cartWindow").html( str );
}



/**
 * 加入购物车
 */
cart_ajax.store = {
	url : function(args){
		return "/cart/store";
	},
	params : function(args){
		return args;
	},
	verification : function(args, params){
		return true;
	},
	callback : function(args, data){
		if (data.success) {
			_cartOn = true;
//			if ( $("#add-cart").prev().attr("id") == "buy") {
//				$.alert({
//					info: data.info
//				});
//			}
			$.alert({
//				type:4,
				info: data.info
			});
		} else {
			$.alert({
				info: data.info
			});
		}
	}
};


/**
 * 更新购物车
 */
cart_ajax.update = {
	url : function(args){
		return "/cart/update";
	},
	params : function(args){
		return cart_json;
	},
	verification : function(args, params){
		return true;
	},
	callback : function(args, data){
		if (data.success) {
			var num = 0;
			if ( $(args).attr("class") == "reduce" ) {
				num = parseInt( $(args).next("input").val() );
				num--;
				$(args).next("input").val( num )
			} else {
				num = parseInt( $(args).prev("input").val() );
				num++;
				$(args).prev("input").val( num )
			}
	        var parice = $(args).parents('ul').find(".price span").html();
	        var subtotal = parseFloat(parice)*num;
	        $(args).parents('ul').find(".subtotal span").html(subtotal);
		} else {
			$.alert({
				info: data.info
			});
		}
	}
};


/**
 * 购物车窗口 - 删除
 */
function onCart() {
//	$("#cartWindow").on("click", ".del a", function() {
//		cart_delete($(this));
//	});
}


/**
 * 分页
 * @param node 
 * @param count
 */
function pagination(node, totalPage, cpage, func) {
	$(node).createPage({
        pageCount: totalPage,
        current: cpage,
        backFn:function(p){
        	where["p"] = p;
        	window[ func ]( [$(".btn-page-top li").eq(where["btnq"]), p]);
            //单击回调方法，p是当前页码
        }
    });
}


//返回的是字符串形式的参数，例如：class_id=3&id=2&  
function getUrlArgStr(arr){
  var q = location.search.substr(1);
  q = q.replace("=&", "");
  var qs = "";
  if ( q != "" ) {
	  qs = q.split('&');
  }
  var argStr='';
  if(qs){
      for(var i=0;i<qs.length;i++){
      	if ( arr.indexOf( qs[i].substring(0,qs[i].indexOf('=')) ) > -1 ) {
      		continue;
      	}
          argStr += qs[i].substring(0,qs[i].indexOf('='))+'='+qs[i].substring(qs[i].indexOf('=')+1)+'&';  
      }  
  }  
  return argStr;  
}

//保留两位小数 - 不够补 00
function toDecimal2(x) {
    var f = parseFloat(x);    
    if (isNaN(f)) {    
        return false;    
    }    
    var f = Math.round(x*100)/100;    
    var s = f.toString();    
    var rs = s.indexOf('.');    
    if (rs < 0) {    
        rs = s.length;    
        s += '.';    
    }    
    while (s.length <= rs + 2) {    
        s += '0';    
    }    
    return s;    
}  

/**
 * 操作失败执行的函数
 * @param data
 */
function errorShopAlert(data) {
	setTimeout(function() {
		var info = "操作失败";
		if (data != "" && data != undefined && data.info != "" && data.info != undefined) {
			info = data.info;
		}
		$.alert({
			type: 3,
			info: info,
			callback: ""
		});
	}, 500);
}



function GetCookieVal(offset)
//获得Cookie解码后的值
{
	var endstr = document.cookie.indexOf (";", offset);
	if (endstr == -1)
	endstr = document.cookie.length;
	return unescape(document.cookie.substring(offset, endstr));
}

function SetCookie(name, value) {
	var today = new Date();
	var expires = new Date();
	expires.setTime(today.getTime() + 1000*60*60*24*365);
	document.cookie = name + "=" + escape(value) + "; expires=" + expires.toGMTString();
}

function DelCookie(name)
//删除Cookie
{
	var exp = new Date();
	exp.setTime (exp.getTime() - 1);
	var cval = GetCookie (name);
	document.cookie = name + "=" + cval + "; expires="+ exp.toGMTString();
}

function GetCookie(name)
//获得Cookie的原始值isMemPassword
{
	var arg = name + "=";
	var alen = arg.length;
	var clen = document.cookie.length;
	var i = 0;
	while (i < clen)
	{
	var j = i + alen;
	if (document.cookie.substring(i, j) == arg)
	return GetCookieVal (j);
	i = document.cookie.indexOf(" ", i) + 1;
	if (i == 0) break;
	}
	return null;
}