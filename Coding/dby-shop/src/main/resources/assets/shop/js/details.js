$(function() {
	$(".jqzoom").imagezoom();

	$(".product-show .thumbnail ul li").mouseenter(function() {
		$(this).addClass("active").siblings().removeClass("active");
		/*
		 * if( $(this).index() === 0 ){ $(".detail-video").show(); $(".big-picture").hide(); }else{ $(".detail-video").hide(); $(".big-picture").show(); $(".big-picture").find("a").attr("href",$(this).find("img").attr("src")); $(".big-picture").find("img").attr("src",$(this).find("img").attr("src")); $(".big-picture").find("img").attr("rel",$(this).find("img").attr("big")); }
		 */

		$(".big-picture").find("a").attr("href", $(this).find("img").attr("src"));
		$(".big-picture").find("img").attr("src", $(this).find("img").attr("mid"));
		$(".big-picture").find("img").attr("rel", $(this).find("img").attr("big"));
	});

	// 评论列表图片点击
	$("#comment-0").on("click", ".p-show-img ul li", function() {
		$(this).parents(".p-show-img").next(".show-img-list").addClass("show-img-active");
	});

	$("#comment-0,#comment-1").on("click", ".img-list ul li", function() {
		$(this).addClass("active").siblings().removeClass("active");
		$(this).parents(".thumb-list").next(".big-img").find("img").attr("src", $(this).find("img").attr("src"));
	});

	function moveImg(obj, direction, num) {
		marginLeft = parseInt(obj.parent().find(".img-list ul").css("margin-left"));
		var liObj = obj.parent().find(".img-list ul li");
		var liIndex = obj.parent().find(".img-list ul li.active").index();
		var ulWidth = liObj.length * (liObj.outerWidth() + 10);
		var divWidth = (liObj.outerWidth() + 10) * 7;
		if (direction === 1) {
			marginLeft += (liObj.outerWidth() + 10) * num;
			liIndex--;
			if (liIndex < 0) {
				marginLeft = -(divWidth);
			} else {
				if (marginLeft > 0) {
					marginLeft = 0;
				}
			}
		} else {
			marginLeft -= (liObj.outerWidth() + 10) * num;
			liIndex++;
			if (liIndex > liObj.length - 1) {
				if (marginLeft < -(ulWidth - divWidth)) {
					marginLeft = 0;
					liIndex = 0;
				}
			}

		}
		liObj.eq(liIndex).addClass("active").siblings().removeClass("active");
		obj.parent().next(".big-img").find("img").attr("src", liObj.eq(liIndex).find("img").attr("src"));
		obj.parent().find(".img-list ul").animate({
			"margin-left" : marginLeft + "px"
		}, 500);
	}

	var dateTime = new Date();
	$("#comment-0").on("click", ".thumb-list a.next", function() {
		if (new Date() - dateTime > 500) {
			moveImg($(this), 2, 1);
			dateTime = new Date();
		}
	});

	$("#comment-0").on("click", ".thumb-list a.prev", function() {
		if (new Date() - dateTime > 500) {
			moveImg($(this), 1, 1);
			dateTime = new Date();
		}
	});

	$("#comment-1").on("click", ".thumb-list a.next", function() {
		if (new Date() - dateTime > 500) {
			moveImg($(this), 2, 1);
			dateTime = new Date();
		}
	});

	$("#comment-1").on("click", ".thumb-list a.prev", function() {
		if (new Date() - dateTime > 500) {
			moveImg($(this), 1, 1);
			dateTime = new Date();
		}
	});

	// 关闭图片组
	$("#comment-0").on("click", ".big-img img", function() {
		$(this).parents(".show-img-list").removeClass("show-img-active");
	});

	// 内容tab切换
	$(".details-page .top-screen ul li").click(function() {
		$(this).addClass("active").siblings().removeClass("active");
		$(".tab-switch").eq($(this).index()).show().siblings(".tab-switch").hide();
	});

	$(".tab-main ul li").click(function() {
		var index = $(this).index();
		$(this).addClass("active").siblings().removeClass("active");
		$(".tab-con .screen-item").eq(index).show().siblings().hide();

	});

	// 倒计时
	/*
	 * if( $(".end-time").length > 0 ){ setInterval(function(){ updateEndTime(".end-time"); },1000); }
	 */

	// 
	$(".product-show .right-info .bid-record a").click(function() {
		$("html,body").animate({
			"scrollTop" : $("#container").position().top - 100
		}, 500);
		$(".details-page .top-screen ul li").eq(1).addClass("active").siblings().removeClass("active");
		$(".tab-switch").eq(1).show().siblings(".tab-switch").hide();
	});

	$(".product-show .left-info .product-info ul li").eq(1).click(function() {
		// $(this).toggleClass("active");
	});

	/**
	 * 加入购物车
	 */
	$("input#add-cart").click(function() {
		var goods_json = {
			depot_id : $(this).attr("data-id")
		}
		cart_store(goods_json);
	});

	/**
	 * 一键购买
	 */
	$("input#buy").click(function() {
		var ids = $(this).attr("data-id");
		$.ajax(ajaxSettings({
			url : '/pay/generate',
			type : 'post',
			data : {
				ids : ids,
				type : ""
			},
			success : function(data) {
				if (data.success) {
					window.location.href = "/pay/order_confirm?spm=" + data.data;
				} else {
					errorShopAlert(data);
				}
			},
			error : function(datas) {
				var data = {};
				data["info"] = datas.info;
				errorShopAlert(data);
			}
		}));
		// 
	});

	/**
	 * 当前用户添加普通商品留言
	 */
	$("#inMessage").click(function() {
		var im_store_id = $("input[name='im_store_id']").val();
		var im_goods_id = $("input[name='im_depot_id']").val();
		var im_message = $("#im_message").val();
		$.ajax(ajaxSettings({
			url : '/message/goodsSave',
			type : 'post',
			data : {
				im_store_id : im_store_id,
				im_goods_id : im_goods_id,
				message_content : im_message
			},
			success : function(data) {
				if (data.success) {
					$.alert({
						type : 4,
						info : "留言成功"
					})
					setTimeout(function sysInfo() {
						location.reload();
					}, 1000);

				} else {

					$.alert({
						type : 4,
						info : "留言失败"
					})
				}
			},
			error : function(data) {
				var data = {};
				data["info"] = "请先登录";
				errorShopAlert(data);
			}
		}));
	});
});
/**
 * 当前用户添加ctc商品留言
 */
$("#ctcMessage").click(function() {
	//留言内容
	var message_content = $("#ctc_message").val();
	//卖家id
	var seller_userId = $("input[name='seller_userId']").val();
	//ctc商品名称
	var ctc_name = $("input[name='ctc_name']").val();
	//ctc商品价格
	var ctc_price = $("input[name='ctc_price']").val();
	
	//ctc商品id
	var ctc_id = $("input[name='ctc_id']").val();
	
	//ctc商品简介
	var ctc_summary = $("input[name='ctc_summary']").val();
	
	//商品图片
	var ctc_img = $("input[name='ctc_img']").val();
	
	$.ajax(ajaxSettings({
		url : '/message/ctcSave',
		type : 'post',
		data : {
			message_content:message_content,
			seller_userId:seller_userId,
			ctc_name:ctc_name,
			ctc_price:ctc_price,
			ctc_id:ctc_id,
			ctc_summary:ctc_summary,
			ctc_img:ctc_img
		},
		success : function(data) {
			if (data.success) {
				$.alert({
					type : 4,
					info : "留言成功"
				})
				setTimeout(function sysInfo() {
					location.reload();
				}, 1000);
				
			} else {
				
				$.alert({
					type : 4,
					info : "留言失败"
				})
			}
		},
		error : function(data) {
			var data = {};
			data["info"] = "请先登录";
			errorShopAlert(data);
		}
	}));
});


