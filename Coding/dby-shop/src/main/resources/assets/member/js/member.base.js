var get = {};
var _mobileCodeToken = ""; // 全局变量 发送手机号返回的token
var _imageCodeToken = ""; // 全局变量 图片验证码返回的token
$(function() {

	// 获取地址
	get.address = {
		url : function(args) {
			return "/api/v1/member/get/address";
		},
		tpl : function(args) {
			return "member/temp/address::address";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};
	// 获取优惠券
	get.coupon = {
		url : function(args) {
			return "/api/v1/member/get/coupon";
		},
		tpl : function(args) {
			return "member/temp/coupon::coupon";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};
	// 获取账户余额
	get.balance = {
		url : function(args) {
			return "/api/v1/member/get/address";
		},
		tpl : function(args) {
			return "member/temp/balance::balance";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};
	// 账户安全
	get.security = {
		url : function(args) {
			return "/api/v1/member/get/address";
		},
		tpl : function(args) {
			return "member/temp/security::security";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};
	// 账户信息
	get.information = {
		url : function(args) {
			return "/api/v1/member/get/information";
		},
		tpl : function(args) {
			return "member/temp/information::information";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};
	// 我的售后
	get.service = {
		url : function(args) {
			return "/api/v1/member/get/address";
		},
		tpl : function(args) {
			return "member/temp/service::service";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};
	// 商品评价
	get.assess = {
		url : function(args) {
			return "/api/v1/member/get/address";
		},
		tpl : function(args) {
			return "member/temp/assess::assess";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$(".g-content").html(data.responseText);
		}
	};
	// 我的收藏
	get.collect = {
		url : function(args) {
			return "/api/v1/member/get/address";
		},
		tpl : function(args) {
			return "member/temp/collect::collect";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};
	// 我的懒鱼
	get.fish = {
		url : function(args) {
			return "/api/v1/member/get/address";
		},
		tpl : function(args) {
			return "member/temp/fish::fish";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};
	// 我的拍卖
	get.sale = {
		url : function(args) {
			return "/api/v1/member/get/address";
		},
		tpl : function(args) {
			return "member/temp/sale::sale";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};
	// 我的预约
	get.bespoke = {
		url : function(args) {
			return "/api/v1/member/get/address";
		},
		tpl : function(args) {
			return "member/temp/bespoke::bespoke";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};
	// 我的订单
	get.orders = {
		url : function(args) {
			return "/api/v1/member/get/address";
		},
		tpl : function(args) {
			return "member/temp/orders::orders";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			$("#right").html(data.responseText);
		}
	};

	/**
	 * 个人中心首页
	 */
	get.member_index = {
		url : function(args) {
			return "/api/v1/get/member/user";
		},
		tpl : function(args) {
			return "member/temp/user/user_index::main";
		},
		params : function(args) {
			if (args[1] == undefined || args[1] == 1) {
				where["p"] = 1;
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 获取分类
	 */
	get.classify = {
		url : function(args) {
			return "/api/v1/get/seller/classify";
		},
		params : function(args) {
			return {
				class_id : $(args).val()
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			eachOption(response, $(args));
		}
	};

	/**
	 * 用户订单
	 */
	get.member_order_index = {
		url : function(args) {
			return "/member/order";
		},
		tpl : function(args) {

			if ($(args[0]).attr("data-state") == undefined) {
				where["state"] = 0;
				where["order_sn"] = "";
			} else {
				where["state"] = $(args[0]).attr("data-state");
			}
			return "member/temp/order/order_index::main";
		},
		params : function(args) {
			if (args[1] == undefined || args[1] == 1) {
				where["p"] = 1;
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			where["type"] = 0;
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
			btnQActive(args);
			eachSearchVal();
		}
	};

	/**
	 * 用户退款-退货页面
	 */
	get.member_order_refund = {
		url : function(args) {
			return "/member/order/refund";
		},
		tpl : function(args) {
			return "member/temp/order/order_refund::main";
		},
		params : function(args) {
			return {
				orderGoodsId : $(args).attr("data-goods-id"),
				orderId : $(args).attr("data-order-id"),
				orderSn : $(args).attr("data-order-sn")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 用户投诉页面
	 */
	get.member_order_complain = {
		url : function(args) {
			return "/member/order/complain";
		},
		tpl : function(args) {
			return "member/temp/order/order_complain::main";
		},
		params : function(args) {
			return {
				orderGoodsId : $(args).attr("data-goods-id"),
				orderId : $(args).attr("data-order-id"),
				orderSn : $(args).attr("data-order-sn")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 我的拍卖
	 */
	get.member_sale = {
		url : function(args) {
			return "/api/v1/get/member/sale";
		},
		tpl : function(args) {
			if ($(args[0]).attr("data-state") == undefined) {
				where["state"] = 1;
			} else {
				where["state"] = $(args[0]).attr("data-state");
			}
			return "member/temp/sale/sale_index::main";
		},
		params : function(args) {
			if (args[1] == undefined || args[1] == 1) {
				where["p"] = 1;
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
			// btnQTwoActive(args);
		}
	};

	/**
	 * 拍卖订单
	 */
	get.sale_order = {
		url : function(args) {
			return "/member/order";
		},
		tpl : function(args) {
			return "member/temp/sale/sale_order::main";
		},
		params : function(args) {
			if (args[1] == undefined || args[1] == 1) {
				where["p"] = 1;
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			where["state"] = 0;
			where["type"] = 4;
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 我的懒鱼 - c2c
	 */
	get.my_ctc = {
		url : function(args) {
			return "/api/v1/get/member/ctc";
		},
		tpl : function(args) {
			return "member/temp/ctc/ctc_index::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
			btnQActive(args);
		}
	};

	/**
	 * 我的懒鱼 - c2c - 创建
	 */
	get.ctc_create = {
		url : function(args) {
			return "/api/v1/get/member/ctc/create";
		},
		tpl : function(args) {
			return "member/temp/ctc/ctc_create::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 我的懒鱼 - c2c - 创建 - 保存
	 */
	get.ctc_store = {
		url : function(args) {
			return "/api/v1/get/member/ctc/store";
		},
		params : function(args) {
			formValue["ctc_pics"] = _goodsPics.join(",");
			formValue["ctc_cover"] = _goodsPics[0];
			return formValue;
		},
		verification : function(args, params) {
			if (_goodsPics.length <= 0) {
				$.alert({
					info : "请上传图片",
					type : 3
				});
				return false;
			}
			return true;
		},
		callback : function(args, response) {
			if (response.success) {
				$("#my_ctc").click();
				/* errorShopAlert(response); */
				$.alert({
					info : "发布成功",
					type : 4
				});
				setTimeout(function() {
					if ($("#memberCtcStoreId").length > 0) {
						window.location.href = "/c2c";
					}
				}, 2000);

			} else {
				errorShopAlert(response);
			}

		}
	};

	/**
	 * 我的懒鱼 - 删除
	 */
	get.ctc_destory = {
		url : function(args) {
			return "/api/v1/get/member/ctc/delete";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.success) {
				$("#my_ctc").click();
			} else {
				errorShopAlert(response);
			}
		}
	};

	/**
	 * 我的懒鱼 - 下架
	 */
	get.ctc_soldOut = {
		url : function(args) {
			return "/api/v1/get/member/ctc/soldOut";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.success) {
				$("#my_ctc").click();
			} else {
				errorShopAlert(response);
			}
		}
	};

	/**
	 * 我的懒鱼 - 上架
	 */
	get.ctc_grand = {
		url : function(args) {
			return "/api/v1/get/member/ctc/grand";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.success) {
				$("#my_ctc").click();
			} else {
				errorShopAlert(response);
			}
		}
	};

	/**
	 * 我的懒鱼 - 订单删除
	 */
	get.ctc_destory = {
		url : function(args) {
			return "/api/v1/get/member/ctc/delete";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.success) {
				$("#my_ctc").click();
			} else {
				errorShopAlert(response);
			}
		}
	};
	/**
	 * 我的懒鱼 - 订单删除
	 */
	get.ctcOrder_destory = {
		url : function(args) {
			return "/api/v1/get/member/ctc/deleteOrder";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.success) {
				$("#my_ctc").click();
			} else {
				errorShopAlert(response);
			}
		}
	};
	/**
	 * 我的懒鱼 - 取消
	 */
	get.ctc_order_cancel = {
		url : function(args) {
			return "/api/v1/get/member/ctc/cancel";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.success) {
				$("#my_ctc").click();
			} else {
				errorShopAlert(response);
			}
		}
	};

	/**
	 * 我的懒鱼 - c2c - 修改
	 */
	get.ctc_edit = {
		url : function(args) {
			return "/api/v1/get/member/ctc/edit";
		},
		tpl : function(args) {
			return "member/temp/ctc/ctc_edit::main";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 我的懒鱼 - c2c - 修改 - 更新
	 */
	get.ctc_update = {
		url : function(args) {
			return "/api/v1/get/member/ctc/update";
		},
		params : function(args) {
			formValue["ctc_pics"] = _goodsPics.join(",");
			formValue["ctc_cover"] = _goodsPics[0];
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.success) {
				$("#my_ctc").click();
			} else {
				errorShopAlert(response);
			}
		}
	};

	/**
	 * 我的懒鱼订单 - c2c订单
	 */
	get.my_ctc_order = {
		url : function(args) {
			return "/api/v1/get/member/ctc/order";
		},
		tpl : function(args) {
			return "member/temp/ctc/ctc_order::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["type"] = $(args[0]).attr("data-type");
				where["p"] = args[1];
			} else {
				where["type"] = 1;
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
			btnQActive(args);
		}
	};

	/**
	 * 收藏的商品
	 */
	get.follow_goods = {
		url : function(args) {
			return "/api/v1/get/member/follow/goods";
		},
		tpl : function(args) {
			return "member/temp/follow/follow_goods::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			where["type"] = 2;
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 收藏的店铺
	 */
	get.follow_store = {
		url : function(args) {
			return "/api/v1/get/member/follow/store";
		},
		tpl : function(args) {
			return "member/temp/follow/follow_store::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			where["type"] = 1;
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};
	/**
	 * 收藏的懒鱼
	 */
	get.follow_ctcs = {
		url : function(args) {
			return "/api/v1/get/member/follow/ctcs";
		},
		tpl : function(args) {
			return "member/temp/follow/follow_ctcs::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			where["type"] = 4;
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 取消收藏
	 */
	get.follow_destroy = {
		url : function(args) {
			return "/api/v1/get/member/follow/destroy";
		},
		// tpl : function(args) {
		// return "member/temp/follow/follow_goods::main";
		// },
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			where["id"] = $(args).attr("data-id");
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$(".btn-page-top li.active").click();
		}
	};

	/**
	 * 用户信息
	 */
	get.member_info = {
		url : function(args) {
			return "/api/v1/get/member/member/info";
		},
		tpl : function(args) {
			return "member/temp/member/info::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 用户信息-更新
	 */
	get.member_info_update = {
		url : function(args) {
			return "/api/v1/get/member/member/update";
		},
		tpl : function(args) {
			return "member/temp/member/info::main";
		},
		params : function(args) {
			var year = $(".form-ajax-sub").find("select[name='year']").val();
			var month = $(".form-ajax-sub").find("select[name='month']").val();
			var day = $(".form-ajax-sub").find("select[name='day']").val();
			var bir = year + '-' + month + '-' + day;
			var date = new Date(bir);
			var sp = date.getTime() / 1000;
			formValue["user_birthday"] = sp;
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 账户安全
	 */
	get.member_security = {
		url : function(args) {
			return "/api/v1/get/member/member/security";
		},
		tpl : function(args) {
			return "member/temp/member/security::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 修改用户登录密码
	 */
	get.edit_password = {
		url : function(args) {
			return "/api/v1/get/member/member/editPassword";
		},
		tpl : function(args) {
			return "member/temp/member/edit_password::main";
		},
		params : function(args) {
			return {
				type : $(args).attr("data-type")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 修改用户登录密码
	 */
	get.eidt_password_mobile = {
		url : function(args) {
			return "/api/v1/get/member/member/editPasswordVerifyMobile";
		},
		tpl : function(args) {
			return "member/temp/member/edit_password_two::main";
		},
		params : function(args) {
			formValue["type"] = $(".form-ajax-sub").attr("data-type");
			formValue["mobileCodeToken"] = _mobileCodeToken;
			// formValue["imageCodeToken"] = _imageCodeToken;
			return formValue;
		},
		verification : function(args, params) {
			if (formValue["mobileCodeToken"] == "") {
				$.alert({
					info : "请先获取短信验证码"
				});
				return false;
			}
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
			// if (response.code == 200) {
			// editPasswordOne();
			// } else {
			// $.alert({
			// info : response.msg == undefined ? "验证码错误" : response.msg
			// });
			// }
		}
	};

	/**
	 * 跳转-修改密码-输入密码页面
	 */
	get.jump_eidt_password = {
		url : function(args) {
			return "/api/v1/get/member/member/jumpEditPassword";
		},
		tpl : function(args) {
			return "member/temp/member/edit_password_two::main";
		},
		params : function(args) {
			return {
				type : $(".form-ajax-sub").attr("data-type")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 修改用户登录密码
	 */
	get.eidt_password_sub_pass = {
		url : function(args) {
			return "/api/v1/get/member/member/editPasswordSubPass";
		},
		// tpl: function(args){
		// return "member/temp/member/edit_password_three::main";
		// },
		params : function(args) {
			formValue["type"] = $(".form-ajax-sub").attr("data-type");
			formValue["imageCodeToken"] = _imageCodeToken;
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.code == 200) {
				jumpEidtPasswordSuccess();
			} else {
				$.alert({
					info : response.msg
				});
			}
		}
	};

	/**
	 * 跳转-修改密码-修改成功页面
	 */
	get.jump_eidt_password_success = {
		url : function(args) {
			return "/api/v1/get/member/member/jumpEidtPasswordSuccess";
		},
		tpl : function(args) {
			return "member/temp/member/edit_password_three::main";
		},
		params : function(args) {
			return {
				type : $(".form-ajax-sub").attr("data-type")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};
	/**
	 * 验证手机号
	 */
	get.verify_mobile = {
		url : function(args) {
			return "/api/v1/get/member/member/verifyMobile";
		},
		tpl : function(args) {
			return "member/temp/member/verify_mobile::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 修改手机号
	 */
	get.edit_mobile = {
		url : function(args) {
			return "/api/v1/get/member/member/editMobile";
		},
		tpl : function(args) {
			return "member/temp/member/edit_mobile::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 验证手机号-提交
	 */
	get.verify_mobile_code_sub = {
		url : function(args) {
			return "/api/v1/get/member/public/verifyMobileCode";
		},
		// tpl: function(args){
		// return "member/temp/member/edit_mobile_two::main";
		// },
		params : function(args) {
			formValue["mobileCodeToken"] = _mobileCodeToken;
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.code == 200) {
				verifyMobileCodeSuccess();
			} else {
				$.alert({
					info : response.msg
				});
			}
		}
	};

	/**
	 * 验证手机号-成功-跳转页面
	 */
	get.verify_mobile_success = {
		url : function(args) {
			return "/api/v1/get/member/public/verifyMobileSuccess";
		},
		tpl : function(args) {
			return "member/temp/member/edit_mobile_two::main";
		},
		params : function(args) {
			formValue["mobileCodeToken"] = _mobileCodeToken;
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 修改手机号码-提交
	 */
	get.edit_mobile_sub = {
		url : function(args) {
			return "/api/v1/get/member/member/editMobileSub";
		},
		params : function(args) {
			formValue["mobileCodeToken"] = _mobileCodeToken;
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.code) {
				$.alert({
					info : response.msg
				});
				memberSecurity();
			} else {
				$.alert({
					info : response.msg
				});
			}
		}
	};

	/**
	 * 验证手机号-提交
	 */
	get.verify_mobile_sub = {
		url : function(args) {
			return "/api/v1/get/member/member/verifyMobileSub";
		},
		tpl : function(args) {
			return "member/temp/member/verify_mobile_success::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 设置支付密码
	 */
	get.set_pay_password = {
		url : function(args) {
			return "/api/v1/get/member/member/setPayPassword";
		},
		tpl : function(args) {
			return "member/temp/member/set_pay_password::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 设置支付密码
	 */
	get.set_pay_password_two = {
		url : function(args) {
			return "/api/v1/get/member/member/setPayPasswordTwo";
		},
		// tpl : function(args) {
		// return "member/temp/member/set_pay_password_two::main";
		// },
		params : function(args) {
			formValue["mobileCodeToken"] = _mobileCodeToken;
			return formValue;
		},
		verification : function(args, params) {
			if (formValue["mobileCodeToken"] == "") {
				$.alert({
					info : "请先获取短信验证码"
				});
				return false;
			}
			return true;
		},
		callback : function(args, response) {
			if (response.code == 200) {
				setPayPasswordTwoTwo();
			} else {
				$.alert({
					info : response.msg
				});
			}
		}
	};

	/**
	 * 设置支付密码
	 */
	get.set_pay_password_two_two = {
		url : function(args) {
			return "/api/v1/get/member/member/setPayPasswordTwoTwo";
		},
		tpl : function(args) {
			return "member/temp/member/set_pay_password_two::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 设置支付密码-提交更改
	 */
	get.set_pay_password_three = {
		url : function(args) {
			return "/api/v1/get/member/member/setPayPasswordThree";
		},
		// tpl : function(args) {
		// return "member/temp/member/set_pay_password_three::main";
		// },
		params : function(args) {
			formValue["mobileCodeToken"] = _mobileCodeToken;
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			if (response.code == 200) {
				setPayPasswordThreeThree();
			} else {
				$.alert({
					info : response.msg
				});
			}
			// $("#right").html(response.responseText);
		}
	};

	/**
	 * 设置支付密码-跳转页面
	 */
	get.set_pay_password_three_three = {
		url : function(args) {
			return "/api/v1/get/member/member/setPayPasswordThreeThree";
		},
		tpl : function(args) {
			return "member/temp/member/set_pay_password_three::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 账户余额
	 */
	get.member_balance = {
		url : function(args) {
			return "/api/v1/get/member/member/balance";
		},
		tpl : function(args) {
			return "member/temp/member/balance::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 优惠券
	 */
	get.member_coupon = {
		url : function(args) {
			return "/api/v1/get/member/coupon";
		},
		tpl : function(args) {
			return "member/temp/coupon/index::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 用户收货地址
	 */
	get.member_address = {
		url : function(args) {
			return "/api/v1/get/member/address";
		},
		tpl : function(args) {
			return "member/temp/member_address/member_address::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 商家发货地址-新增
	 */
	get.member_address_store = {
		url : function(args) {
			return "/api/v1/get/member/address/store";
		},
		tpl : function(args) {
			return "member/temp/member_address/member_address::main";
		},
		params : function(args) {
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 商家发货地址-删除
	 */
	get.member_address_destroy = {
		url : function(args) {
			return "/api/v1/get/member/address/destroy";
		},
		tpl : function(args) {
			return "member/temp/member_address/member_address::main";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 商家发货地址-修改
	 */
	get.member_address_update = {
		url : function(args) {
			return "/api/v1/get/member/address/update";
		},
		tpl : function(args) {
			return "member/temp/member_address/member_address::main";
		},
		params : function(args) {
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 商家发货地址-设为默认
	 */
	get.member_address_set_default = {
		url : function(args) {
			return "/api/v1/get/member/address/setdefault";
		},
		tpl : function(args) {
			return "member/temp/member_address/member_address::main";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 售后服务
	 */
	get.member_service = {
		url : function(args) {
			return "/member/refund";
		},
		tpl : function(args) {
			return "member/temp/service/service::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
				where["order_sn"] = "";
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}

			where["state"] = $(args).attr("data-state");
			if (where["state"] == undefined) {
				where["state"] = $(".btn-page-top li.active").attr("data-state");
			}

			if (where["state"] == undefined || where["state"] == "0") {
				where["state"] = 1;
			}
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
			btnQActive(args);
			eachSearchVal();
		}
	};

	/**
	 * 用户投诉列表
	 */
	get.member_complain = {
		url : function(args) {
			return "/member/complain";
		},
		tpl : function(args) {
			return "member/temp/complain/complain::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
				where["order_sn"] = "";
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 获取短信验证码
	 */
	get.get_mobile_code = {
		url : function(args) {
			return "/api/v1/get/member/public/sendUserMobileCode";
		},
		params : function(args) {
			return {
				type : $(args).attr("data-type")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, data) {
			if (data.success) {
				_mobileCodeToken = data.data.data.token;
				// $(args).hide();
				$(".send_code").prev().css("display", "none");
				$(".send_code").css("display", "block");
				$(".send_code .repeat").css("display", "block");

				if ($(args).attr("class") == "obtain") {
					$(".Z-shoujiyanzhen .send_code .repeat").css("display", "block");
				}

				obt();
			} else {
				$.alert({
					type : 3,
					info : data.info
				});
			}
		}
	};

	/**
	 * 获取短信验证码
	 */
	get.get_sub_mobile_code = {
		url : function(args) {
			return "/api/v1/get/member/public/sendMobileCode";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return formValue;
		},
		callback : function(args, response) {
			_mobileCodeToken = response.body;
		}
	};

	/**
	 * 添加银行卡
	 */
	get.card_store = {
		url : function(args) {
			return "/api/v1/get/member/card/store";
		},
		tpl : function(args) {
			return "seller/temp/member/balance::main";
		},
		params : function(args) {
			console.log(formValue)
			return formValue;
		},
		verification : function(args, params) {
			return false;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 商家评价管理
	 */
	get.comment = {
		url : function(args) {
			return "/api/v1/get/seller/comment";
		},
		tpl : function(args) {
			return "seller/temp/comment/comment::main";
		},
		params : function(args) {
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
			eachSearchVal();
		}
	};

	/**
	 * 商家设置
	 */
	get.store_setting = {
		url : function(args) {
			return "/api/v1/get/seller/setting";
		},
		tpl : function(args) {
			return "seller/temp/store/setting::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 商家信息-提交修改
	 */
	get.store_setting_update = {
		url : function(args) {
			return "/api/v1/get/seller/setting/update";
		},
		tpl : function(args) {
			return "seller/temp/store/setting::main";
		},
		params : function(args) {
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
			$.alert({
				info : "修改成功"
			});
		}
	};

	/**
	 * 店铺幻灯片
	 */
	get.slide = {
		url : function(args) {
			return "/api/v1/get/seller/setting/slide";
		},
		tpl : function(args) {
			return "seller/temp/store/slide::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 店铺导航
	 */
	get.store_nav = {
		url : function(args) {
			return "/api/v1/get/seller/nav";
		},
		tpl : function(args) {
			return "seller/temp/nav/nav::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};
	/**
	 * 友情链接
	 */
	get.store_link = {
		url : function(args) {
			return "/api/v1/get/seller/link";
		},
		tpl : function(args) {
			return "seller/temp/link/link::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 友情链接-创建
	 */
	get.linkCreate = {
		url : function(args) {
			return "/api/v1/get/seller/link/create";
		},
		tpl : function(args) {
			return "seller/temp/link/link_create::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			if ($(".list-table tbody tr").length >= 10) {
				$.alert({
					info : "最多只能添加10个",
					type : 3
				});
				return false;
			}
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};
	/**
	 * 忘记密码-提交修改
	 * 
	 */
	get.updatePwd = {
		url : function(args) {
			return "/api/v1/get/member/member/updatePwd";
		},
		tpl : function(args) {
			return "member/security/login::main";
		},
		params : function(args) {
			formValue["mobileCodeToken"] = _mobileCodeToken;
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 友情链接-新增保存
	 */
	get.store_link_store = {
		url : function(args) {
			return "/api/v1/get/seller/link/store";
		},
		tpl : function(args) {
			return "seller/temp/link/link::main";
		},
		params : function(args) {
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 友情链接-修改
	 */
	get.store_link_edit = {
		url : function(args) {
			return "/api/v1/get/seller/link/edit";
		},
		tpl : function(args) {
			return "seller/temp/link/link_edit::main";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 友情链接-修改保存
	 */
	get.store_link_update = {
		url : function(args) {
			return "/api/v1/get/seller/link/update";
		},
		tpl : function(args) {
			return "seller/temp/link/link::main";
		},
		params : function(args) {
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};
	/**
	 * 友情链接-删除
	 */
	get.store_link_destroy = {
		url : function(args) {
			return "/api/v1/get/seller/link/destroy";
		},
		tpl : function(args) {
			return "seller/temp/link/link::main";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
			if (response.data > 0) {
				$(args).parents("li").remove();
			}
		}
	};

	/**
	 * 店铺导航-删除
	 */
	get.store_nav_destroy = {
		url : function(args) {
			return "/api/v1/get/seller/nav/destroy";
		},
		tpl : function(args) {
			return "seller/temp/nav/nav::main";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
			if (response.data > 0) {
				$(args).parents("li").remove();
			}
		}
	};

	/**
	 * 店铺导航-创建
	 */
	get.navCreate = {
		url : function(args) {
			return "/api/v1/get/seller/nav/create";
		},
		tpl : function(args) {
			return "seller/temp/nav/nav_create::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 商家导航-新增保存
	 */
	get.store_nav_store = {
		url : function(args) {
			return "/api/v1/get/seller/nav/store";
		},
		tpl : function(args) {
			return "seller/temp/nav/nav::main";
		},
		params : function(args) {
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 商家导航-修改
	 */
	get.store_nav_edit = {
		url : function(args) {
			return "/api/v1/get/seller/nav/edit";
		},
		tpl : function(args) {
			return "seller/temp/nav/nav_edit::main";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 商家导航-修改
	 */
	get.store_nav_update = {
		url : function(args) {
			return "/api/v1/get/seller/nav/update";
		},
		tpl : function(args) {
			return "seller/temp/nav/nav::main";
		},
		params : function(args) {
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 商家导航-显示-隐藏
	 */
	get.store_nav_setshow = {
		url : function(args) {
			return "/api/v1/get/seller/nav/setshow";
		},
		tpl : function(args) {
			return "seller/temp/nav/nav::main";
		},
		params : function(args) {
			var isShow = $(args).attr("data-is-show") == 1 ? 2 : 1;
			return {
				id : $(args).attr("data-id"),
				isShow : isShow
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 店铺分类
	 */
	get.store_classify = {
		url : function(args) {
			return "/api/v1/get/seller/store/classify";
		},
		tpl : function(args) {
			return "seller/temp/classify/classify::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 店铺分类-创建
	 */
	get.classifyCreate = {
		url : function(args) {
			return "/api/v1/get/seller/store/classify/create";
		},
		tpl : function(args) {
			return "seller/temp/classify/classify_create::main";
		},
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 店铺分类-显示-隐藏
	 */
	get.store_goods_classify_setshow = {
		url : function(args) {
			return "/api/v1/get/seller/store/classify/setshow";
		},
		tpl : function(args) {
			return "seller/temp/classify/classify::main";
		},
		params : function(args) {
			var isShow = $(args).attr("data-is-show") == 1 ? 2 : 1;
			return {
				id : $(args).attr("data-id"),
				isShow : isShow
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 店铺分类-删除
	 */
	get.store_goods_classify_destroy = {
		url : function(args) {
			return "/api/v1/get/seller/store/classify/destroy";
		},
		tpl : function(args) {
			return "seller/temp/classify/classify::main";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 店铺分类-新增保存
	 */
	get.store_goods_classify_store = {
		url : function(args) {
			return "/api/v1/get/seller/store/classify/store";
		},
		tpl : function(args) {
			return "seller/temp/classify/classify::main";
		},
		params : function(args) {
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 店铺分类-修改
	 */
	get.store_goods_classify_edit = {
		url : function(args) {
			return "/api/v1/get/seller/store/classify/edit";
		},
		tpl : function(args) {
			return "seller/temp/classify/classify_edit::main";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 店铺导航-修改提交
	 */
	get.store_goods_classify_update = {
		url : function(args) {
			return "/api/v1/get/seller/store/classify/update";
		},
		tpl : function(args) {
			return "seller/temp/classify/classify::main";
		},
		params : function(args) {
			return formValue;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 店铺导航-修改提交
	 */
	get.seller_index = {
		url : function(args) {
			return "/seller";
		},
		// tpl: function(args){
		// return "seller/temp/classify/classify::main";
		// },
		params : function(args) {
			return {};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("body").html(response.responseText);
		}
	};

	/**
	 * 获取当前登录用户的许愿列表
	 */
	get.member_wish = {
		url : function(args) {
			return "/member/wish/queryWish";
		},
		tpl : function(args) {
			return "member/temp/user/wishList::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 流通置换 列表
	 */
	get.circulation_goods = {
		url : function(args) {
			return "/member/circulation/queryCirculation";
		},
		tpl : function(args) {
			return "member/temp/circulation/circulationList::main";
		},
		params : function(args) {
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 流通置换 - 删除
	 */
	get.circulation_delete = {
		url : function(args) {
			return "/member/circulation/deleteCirculation";
		},
		tpl : function(args) {
			return "/member/temp/circulation/circulationList::main";
		},
		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 流通置换 - 详情
	 */
	get.circulation_details = {
		url : function(args) {
			return "/member/circulation/circulationDetailes";
		},
		tpl : function(args) {
			return "/member/temp/circulation/circulationDetail::main";
		},

		params : function(args) {
			return {
				id : $(args).attr("data-id")
			};
		},
		verification : function(args, params) {
			return true;
		},
		callback : function(args, response) {
			$("#right").html(response.responseText);
		}
	};
});