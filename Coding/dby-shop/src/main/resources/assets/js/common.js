var _data = "";
var _mobileReg = /^1[3|4|5|7|8][0-9]\d{8}$/;
var _token = "";
$(function(){
	//$("img").lazyload({effect: "fadeIn"});
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
		},
		"mouseleave":function(){
			$(this).removeClass("active-cart");
		}
	});
	
	$(".yes-product ul li .price-del .del a").click(function(){
		if( confirm("是否确认删除该商品？") ){
			$( this ).parents("li").remove();
		}
	});
	
	
	
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
			}
		},
		"mouseleave":function(){
			if( !$(this).hasClass("active") ){
				$(this).children("ul").hide();
			}
		}
	});
	
	$("#header .main-nav .nav .classify").bind({
		"mouseenter":function(){
			if( !$(this).hasClass("active") ){
				$(this).children("ul").show();
			}
		},
		"mouseleave":function(){
			if( !$(this).hasClass("active") ){
				$(this).children("ul").hide();
			}
		}
	});
	
	
	// 菜单显示
	$("#header .main-nav .nav .more-classify").click(function(){
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
        	prevText: '&lt;',
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
        	prevText: '&lt;',
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
        	prevText: '&lt;',
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
        	prevText: '&lt;',
		});
	}
	
	var YD = DBY = {
		// 编辑器参数
		kingEditorParams : {
			filePostName  : "uploadFile",
			uploadJson : '/pic/upload',
			dir : "image"
		},
		init : function(data){
	    	this.initPicUpload(data);
	    },
	    // 初始化图片上传组件
	    initPicUpload : function(data){
	    	$(".picFileUpload").each(function(i,e){
	    		var _ele = $(e);
	    		_ele.siblings("div.pics").remove();
	    		_ele.after('\
	    			<div class="pics">\
	        			<ul></ul>\
	        		</div>');
	    		// 回显图片
	        	if(data && data.pics){
	        		var imgs = data.pics.split(",");
	        		for(var i in imgs){
	        			if($.trim(imgs[i]).length > 0){
	        				_ele.siblings(".pics").find("ul").append("<li><a href='"+imgs[i]+"' target='_blank'><img src='"+imgs[i]+"' width='80' height='50' /></a></li>");
	        			}
	        		}
	        	}
	        	$(e).unbind('click').click(function(){
	        		var form = $(this).parentsUntil("form").parent("form");
	        		KindEditor.editor(YD.kingEditorParams).loadPlugin('multiimage',function(){
	        			var editor = this;
	        			editor.plugin.multiImageDialog({
							clickFn : function(urlList) {
								var imgArray = [];
								KindEditor.each(urlList, function(i, data) {
									imgArray.push(data.url);
									form.find(".pics ul").append("<li><a href='"+data.url+"' target='_blank'><img src='"+data.url+"' width='80' height='50' /></a></li>");
								});
								form.find("[name=image]").val(imgArray.join(","));
								editor.hideDialog();
							}
						});
	        		});
	        	});
	    	});
	    },
	    createEditor : function(select){
	    	return KindEditor.create(select, YD.kingEditorParams);
	    }
	};


	
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


/**
 * 操作成功执行的函数
 */
function successShopAlert(id) {
	$("#"+id).click();
}


/**
 * 操作失败执行的函数
 * @param data
 */
function errorShopAlert(data) {
	var info = "操作失败";
	if (data != "" && data != undefined && data.info != "" && data.info != undefined) {
		info = data.info;
	}
	$.alert({
		type: 3,
		info: info,
		callback: ""
	});
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
