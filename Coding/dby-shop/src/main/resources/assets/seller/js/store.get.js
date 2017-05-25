var get = {};
$(function(){
	
	/**
	 * 商家发布商品
	 */
	get.goods_create = {
		url : function(args){
			return "/seller/goods/create";
		},
		tpl: function(args){
			return "seller/temp/goods/goods_create::main";
		},
		params : function(args){
			return {};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家发布商品
	 */
	get.goods_store = {
		url : function(args){
			return "/seller/goods/store";
		},
		params : function(args){
			// 自定义属性
			delete formValue["attr_title"];
			delete formValue["attr_value"];
			var attrData = {};
			var i = 0;
			$(".attr-lists .attr-node").each(function(k, v) {
				var attr_title = $(this).find("input[name='attr_title']").val();
				if ( attr_title != "" ) {
					var attr_value = $(this).find("input[name='attr_value']").val();
					attrData[i] = {};
					attrData[i]["key"] = attr_title;
					attrData[i]["value"] = attr_value;
					i++;
				}
			});

			if ( !$.isEmptyObject( attrData ) ) {
				formValue["goods_attrs"] = JSON.stringify( attrData );
			} else {
				formValue["goods_attrs"] = "";
			}
			
			// 选择属性
			var goodsAttrSelect = {};
			var i = 0;
			if ( $(".goods-attr-each select").length > 0 ) {
				$(".goods-attr-each select").each(function(k, v) {
					var dataTitle = $(this).attr("data-title");
					var dataValueTitle = $(this).find("option:selected").text();
					goodsAttrSelect[i] = {};
					goodsAttrSelect[i]["key"] = dataTitle;
					goodsAttrSelect[i]["value"] = dataValueTitle;
					i++;
				});
				formValue["goods_attr_select"] = JSON.stringify( goodsAttrSelect );
			} else {
				formValue["goods_attr_select"] = "";
			}
			
			formValue["goods_pics"] = _goodsPics.join(",");
			formValue["goods_cover"] = _goodsPics[0];
			if ( _goodsContentPics.length > 0 ) {
				formValue["goods_content_pics"] = _goodsContentPics.join(",");				
			} else {
				formValue["goods_content_pics"] = "";
			}
			
			// 商品属性
			if ($(".goods-attr-each select").length > 0) {
				var attrs = {};
				var i = 0;
				$(".goods-attr-each select").each(function(k, v) {
					var dataId = $(this).attr("data-id");
					var dataValueId = $(this).find("option:selected").val();
					attrs[i] = {"attr_id": dataId, "attr_value_id": dataValueId};
					i++;
				});
				formValue["goods_attr_value"] = JSON.stringify( attrs );
			}
			return formValue;
		},
		verification : function(args, params){
			if ( $(".up-image-body ul li").length <= 0 ) {
				$.alert({info: "请上传商品图片！" });
				return false;
			}
			if ( $(".goods-attr-each select").length > 0 ) {
				if ( $(this).find("option:selected").val() ) {
					$.alert({info: "请选择属性" });
					return false;
				}
			}
			return true;
		},
		callback : function(args, response){
			if (response.success) {
				$.alert({
					type: 4,
					info: response.info
				});
				setTimeout(function() {
					$("#goodsIndex").click();
				}, 2000);
			} else {
				$.alert({
					type: 3,
					info: response.info
				});
			}
		}
	};
	
	
	/**
	 * 商家编辑商品
	 */
	get.goods_edit = {
		url : function(args){
			return "/seller/goods/edit";
		},
		tpl: function(args){
			return "seller/temp/goods/goods_edit::main";
		},
		params : function(args){
			return {id: $(args).attr("data-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家更新商品
	 */
	get.goods_update = {
		url : function(args){
			return "/seller/goods/update";
		},
		params : function(args){
			/*
			delete formValue["attr_title"];
			delete formValue["attr_value"];
			var attrData = {};
			$(".attr-lists .attr-node").each(function(k, v) {
				var attr_title = $(this).find("input[name='attr_title']").val();
				var attr_value = $(this).find("input[name='attr_value']").val();
				attrData[attr_title] = attr_value;
			});
			formValue["goodsAttrs"] = JSON.stringify( attrData );
			formValue["goodsPics"] = JSON.stringify( _goodsPics );
			formValue["goods_cover"] = _goodsPics[0];
			return formValue;
			*/
			// 自定义属性
			delete formValue["attr_title"];
			delete formValue["attr_value"];
			var attrData = {};
			var i = 0;
			$(".attr-lists .attr-node").each(function(k, v) {
				var attr_title = $(this).find("input[name='attr_title']").val();
				if ( attr_title != "" ) {
					var attr_value = $(this).find("input[name='attr_value']").val();
					attrData[i] = {};
					attrData[i]["key"] = attr_title;
					attrData[i]["value"] = attr_value;
					i++;
				}
			});

			if ( !$.isEmptyObject( attrData ) ) {
				formValue["goodsAttrs"] = JSON.stringify( attrData );
			} else {
				formValue["goodsAttrs"] = "";
			}
			
			// 选择属性
			var goodsAttrSelect = {};
			var i = 0;
			if ( $(".goods-attr-each select").length > 0 ) {
				$(".goods-attr-each select").each(function(k, v) {
					var dataTitle = $(this).attr("data-title");
					var dataValueTitle = $(this).find("option:selected").text();
					goodsAttrSelect[i] = {};
					goodsAttrSelect[i]["key"] = dataTitle;
					goodsAttrSelect[i]["value"] = dataValueTitle;
					i++;
				});
				formValue["goods_attr_select"] = JSON.stringify( goodsAttrSelect );
			} else {
				formValue["goods_attr_select"] = "";
			}

			formValue["goods_pics"] = _goodsPics.join(",");
			formValue["goods_cover"] = _goodsPics[0];
			if ( _goodsContentPics.length > 0 ) {
				formValue["goodsContentPics"] = JSON.stringify( _goodsContentPics );				
			} else {
				formValue["goodsContentPics"] = "";
			}
			
			// 商品属性
			if ($(".goods-attr-each select").length > 0) {
				var attrs = {};
				var i = 0;
				$(".goods-attr-each select").each(function(k, v) {
					var dataId = $(this).attr("data-id");
					var dataValueId = $(this).find("option:selected").val();
					attrs[i] = {"attr_id": dataId, "attr_value_id": dataValueId};
					i++;
				});
				formValue["goods_attr_value"] = JSON.stringify( attrs );
			}
			return formValue;
		},
		verification : function(args, params){
			if ( $(".up-image-body ul li").length <= 0 ) {
				$.alert({info: "请上传商品图片！" });
				return false;
			}
			if ( $(".goods-attr-each select").length > 0 ) {
				if ( $(this).find("option:selected").val() ) {
					$.alert({info: "请选择属性" });
					return false;
				}
			}

			return true;
		},
		callback : function(args, response){
			if (response.success) {
				$.alert({
					type: 4,
					info: response.info
				});
				setTimeout(function() {
					$("#goodsIndex").click();
				}, 2000);
			} else {
				$.alert({
					type: 3,
					info: response.info
				});
			}
		}
	};
	
	
	/**
	 * 商家商品删除
	 */
	get.goods_destroy = {
		url : function(args){
			return "/seller/goods/destroy";
		},
		params : function(args){
			return {depot_id: $(args).attr("data-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$(args).parents(".d-show2").remove();
		}
	};
	
	
	/**
	 * 发布商品 - 获取商品类型
	 */
	get.get_goods_type = {
		url : function(args){
			return "/api/v1/get/seller/type";
		},
		params : function(args){
			return {class_id: $(args).val() };
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			if ( response.data != null && response.data != "") {
				
				// 属性选择
				$(".goods-attr-li").css("display", "block");
				$("input[name='type_id']").val( response.data.type_id );
				var dataAttrs = response.dataAttrs;
				var dataAttrValues = response.dataAttrValues;
				var str = "";
				for(var ele in dataAttrs) {
					str += '<select data-id="'+dataAttrs[ele]['attr_id']+'" data-title="'+dataAttrs[ele]['attr_name']+'"><option value="0">请选择'+dataAttrs[ele]['attr_name']+'</option>'
					var attrValues = dataAttrs[ele]['attr_value'].split(",");
					for(var e in dataAttrValues) {
						if (dataAttrValues[e]['attr_id'] == dataAttrs[ele]['attr_id']) {
							str += "<option value='"+dataAttrValues[e]['attr_value_id']+"'>"+dataAttrValues[e]['attr_value_name']+"</option>";
						}
					}
					str += '</select>';
				}
				$(".goods-attr-each").html(str);
				
				// 品牌选择
				
				if ( response.dataBrand != null ) {
					$(".goods-brand").css("display", "block");
					var str = "";
					str += "<option value='0'>请选择品牌...</option>";
					$.each(response.dataBrand, function(k, v) {
						str += '<option value="'+v.brand_id+'">'+v.brand_name+'</option>';
					});
					$("select[name='brand_id']").html( str );
				}
			} else {
				$("input[name='type_id']").val( 0 );
				$(".goods-attr-li").css("display", "none");
				$(".goods-brand").css("display", "none");
				$("select[name='brand_id']").html( "<option value='0'>请选择品牌...</option>" );
			}
		}
	};
	
	
	/**
	 * 获取分类
	 */
	get.classify = {
		url : function(args){
			$(".goods-attr-li").css("display", "none");
			$(".goods-brand").css("display", "none");
			return "/api/v1/get/seller/classify";
		},
		params : function(args){
			return {class_id: $(args).val()};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			eachOption(response, $(args));
			var classId = $(args).next().attr("data-class-id");
			if ( classId != undefined) {
				$(args).next().find("option[value='"+classId+"']").prop("selected", true);
				$(args).next().change();
			}
		}
	};
	
	
	/**
	 * 获取ctc分类
	 */
	get.ctcClassSon = {
		url : function(args){
			return "/api/v1/get/member/ctcclass/son";
		},
		params : function(args){
			return {cc_id: $(args).val()};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			console.log(response);
			eachCtcOption(response, $(args));
		}
	};
	
	
	/**
	 * 出售中的商品
	 */
	get.goods_index = {
		url : function(args){
			return "/seller/goods";
		},
		tpl: function(args){
			return "seller/temp/goods/goods_index::main";
		},
		params : function(args){
			where["depot_type"] = $(args[0]).attr("data-f1");
			where["depot_is_available"] = $(args[0]).attr("data-f2");
			if (args[1] != undefined) {
				delete where["goods_name"];
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			where["goods_name"] = $("input[name='goods_name']").val();
			return where;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
			btnQActive(args);
			eachSearchVal();
		}
	};
	
	
	/**
	 * 仓库中的商品
	 */
	get.goods_depot = {
		url : function(args){
			return "/seller/goods/depot";
		},
		tpl: function(args){
			return "seller/temp/goods/goods_depot::main";
		},
		params : function(args){
			where["depot_type"] = 0;
			where["depot_is_available"] = 2;
			
			if (args[1] != undefined) {
				delete where["goods_name"];
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
			eachSearchVal();
		}
	};
	
	
	/**
	 * 违规下架的商品
	 */
	get.goods_illegal = {
		url : function(args){
			return "/seller/goods/illegal";
		},
		tpl: function(args){
			return "seller/temp/goods/goods_illegal::main";
		},
		params : function(args){
			where["depot_type"] = 0;
			where["depot_is_available"] = 3;
			
			if (args[1] != undefined) {
				delete where["goods_name"];
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
			eachSearchVal();
		}
	};
	
	
	
	/**
	 * 商家图片空间
	 */
	get.goods_pic = {
		url : function(args){
			return "/seller/goods_pic/pic";
		},
		tpl: function(args){
			return "seller/temp/goods_pic/pic::main";
		},
		params : function(args){
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家订单
	 */
	get.order_index = {
		url : function(args){
			return "/seller/order";
		},
		tpl: function(args){
			
			if ( $(args[0]).attr("data-state") == undefined ) {
				where["state"] = 0;
			} else {
				where["state"] = $(args[0]).attr("data-state");
			}
			return "seller/temp/order/order_index::main";
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
			$("#right").html(response.responseText);
			btnQActive(args);
			eachSearchVal();
		}
	};
	
	
	/**
	 * 商家订单 - 发货页面
	 */
	get.order_delivery = {
		url : function(args){
			return "/seller/order/delivery";
		},
		tpl: function(args){
			return "seller/temp/order/delivery::main";
		},
		params : function(args){
			return {order_id: $(args).attr("data-id")}; 
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家发货地址
	 */
	get.store_address = {
		url : function(args){
			return "/api/v1/get/seller/address";
		},
		tpl: function(args){
			return "seller/temp/store_address/store_address::main";
		},
		params : function(args){
			return {};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家发货地址-新增
	 */
	get.store_address_store = {
		url : function(args){
			return "/api/v1/get/seller/address/store";
		},
		tpl: function(args){
			return "seller/temp/store_address/store_address::main";
		},
		params : function(args){
			return formValue;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家发货地址-删除
	 */
	get.store_address_destroy = {
		url : function(args){
			return "/api/v1/get/seller/address/destroy";
		},
		tpl: function(args){
			return "seller/temp/store_address/store_address::main";
		},
		params : function(args){
			return {id: $(args).attr("data-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家发货地址-修改
	 */
	get.store_address_update = {
		url : function(args){
			return "/api/v1/get/seller/address/update";
		},
		tpl: function(args){
			return "seller/temp/store_address/store_address::main";
		},
		params : function(args){
			return formValue;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};

	
	/**
	 * 商家发货地址-设为默认
	 */
	get.store_address_set_default = {
		url : function(args){
			return "/api/v1/get/seller/address/setdefault";
		},
		tpl: function(args){
			return "seller/temp/store_address/store_address::main";
		},
		params : function(args){
			return {id: $(args).attr("data-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	
	/**
	 * 商家评价管理
	 */
	get.comment = {
		url : function(args){
			return "/api/v1/get/seller/comment";
		},
		tpl: function(args){
			return "seller/temp/comment/comment::main";
		},
		params : function(args){
			console.log(where);
			return where;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
			eachSearchVal();
		}
	};
	
	/**
	 * 商家设置
	 */
	get.store_setting = {
		url : function(args){
			return "/api/v1/get/seller/setting";
		},
		tpl: function(args){
			return "seller/temp/store/setting::main";
		},
		params : function(args){
			return {};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家信息-提交修改
	 */
	get.store_setting_update = {
		url : function(args){
			return "/api/v1/get/seller/setting/update";
		},
		tpl: function(args){
			return "seller/temp/store/setting::main";
		},
		params : function(args){
			return formValue;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
			$.alert({info: "修改成功" });
		}
	};
	
	
	/**
	 * 店铺幻灯片
	 */
	get.slide = {
		url : function(args){
			return "/api/v1/get/seller/setting/slide";
		},
		tpl: function(args){
			return "seller/temp/store/slide::main";
		},
		params : function(args){
			return {};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	get.slide_update = {
			url : function(args){
				return "/api/v1/get/seller/setting/slide_update";
			},
			params : function(args){
				var slideData = {};
				var i = 0;
				$(".store-branner-thumb-list li").each(function(k, v) {
					var src = $(this).find("input[name='slide_src']").val();
					var href = $(this).find("input[name='slide_href']").val();
					slideData[i] = {};
					slideData[i]["src"] = src;
					slideData[i]["href"] = href;
					i++;
				});
				formValue["store_banner"] = JSON.stringify(slideData);
				console.log(formValue);
				return formValue;
			},
			verification : function(args, params){
				return true;
			},
			callback : function(args, response){
				$("#right").html(response.responseText);
			}
		};
	
	
	/**
	 * 店铺导航
	 */
	get.store_nav = {
		url : function(args){
			return "/api/v1/get/seller/nav";
		},
		tpl: function(args){
			return "seller/temp/nav/nav::main";
		},
		params : function(args){
			return {};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 店铺导航-删除
	 */
	get.store_nav_destroy = {
		url : function(args){
			return "/api/v1/get/seller/nav/destroy";
		},
		tpl: function(args){
			return "seller/temp/nav/nav::main";
		},
		params : function(args){
			return {id: $(args).attr("data-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
			if ( response.data > 0 ) {
				$(args).parents("li").remove();
			}
		}
	};
	
	
	/**
	 * 店铺导航-创建
	 */
	get.navCreate = {
		url : function(args){
			return "/api/v1/get/seller/nav/create";
		},
		tpl: function(args){
			return "seller/temp/nav/nav_create::main";
		},
		params : function(args){
			return {};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家导航-新增保存
	 */
	get.store_nav_store = {
		url : function(args){
			return "/api/v1/get/seller/nav/store";
		},
		tpl: function(args){
			return "seller/temp/nav/nav::main";
		},
		params : function(args){
			return formValue;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	/**
	 * 商家导航-修改
	 */
	get.store_nav_edit = {
		url : function(args){
			return "/api/v1/get/seller/nav/edit";
		},
		tpl: function(args){
			return "seller/temp/nav/nav_edit::main";
		},
		params : function(args){
			return {id: $(args).attr("data-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家导航-修改
	 */
	get.store_nav_update = {
		url : function(args){
			return "/api/v1/get/seller/nav/update";
		},
		tpl: function(args){
			return "seller/temp/nav/nav::main";
		},
		params : function(args){
			return formValue;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家导航-显示-隐藏
	 */
	get.store_nav_setshow = {
		url : function(args){
			return "/api/v1/get/seller/nav/setshow";
		},
		tpl: function(args){
			return "seller/temp/nav/nav::main";
		},
		params : function(args){
			var isShow = $(args).attr("data-is-show") == 1 ? 2 : 1;
			return {id: $(args).attr("data-id"), isShow: isShow};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 店铺分类
	 */
	get.store_classify = {
		url : function(args){
			return "/api/v1/get/seller/store/classify";
		},
		tpl: function(args){
			return "seller/temp/classify/classify::main";
		},
		params : function(args){
			return {};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	/**
	 * 店铺分类-创建
	 */
	get.classifyCreate = {
		url : function(args){
			return "/api/v1/get/seller/store/classify/create";
		},
		tpl: function(args){
			return "seller/temp/classify/classify_create::main";
		},
		params : function(args){
			return {};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 店铺分类-显示-隐藏
	 */
	get.store_goods_classify_setshow = {
		url : function(args){
			return "/api/v1/get/seller/store/classify/setshow";
		},
		tpl: function(args){
			return "seller/temp/classify/classify::main";
		},
		params : function(args){
			var isShow = $(args).attr("data-is-show") == 1 ? 2 : 1;
			return {id: $(args).attr("data-id"), isShow: isShow};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 店铺分类-删除
	 */
	get.store_goods_classify_destroy = {
		url : function(args){
			return "/api/v1/get/seller/store/classify/destroy";
		},
		tpl: function(args){
			return "seller/temp/classify/classify::main";
		},
		params : function(args){
			return {id: $(args).attr("data-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 店铺分类-新增保存
	 */
//	get.store_goods_classify_store = {
//		url : function(args){
//			return "/api/v1/get/seller/store/classify/store";
//		},
//		params : function(args){
//			return formValue;
//		},
//		verification : function(args, params){
//			return true;
//		},
//		callback : function(args, response){
//			$("#right").html(response.responseText);
//		}
//	};
	
	/**
	 * 店铺分类-修改
	 */
	get.store_goods_classify_edit = {
		url : function(args){
			return "/api/v1/get/seller/store/classify/edit";
		},
		tpl: function(args){
			return "seller/temp/classify/classify_edit::main";
		},
		params : function(args){
			return {id: $(args).attr("data-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 店铺导航-修改提交
	 */
//	get.store_goods_classify_update = {
//		url : function(args){
//			return "/api/v1/get/seller/store/classify/update";
//		},
//		tpl: function(args){
//			return "seller/temp/classify/classify::main";
//		},
//		params : function(args){
//			return formValue;
//		},
//		verification : function(args, params){
//			return true;
//		},
//		callback : function(args, response){
//			$("#right").html(response.responseText);
//		}
//	};
	
	
	/**
	 * 售后服务
	 */
	get.store_service = {
		url : function(args){
			return "seller/refund";
		},
		tpl: function(args){
			return "seller/temp/service/service::main";
		},
		params : function(args){
			where['state_service'] = $(args[0]).attr("data-type")
			if (args[1] != undefined) {
				where["p"] = args[1];
			} else {
				where["p"] = where["p"] == undefined ? 1 : where["p"];
			}
			return where;
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
			$(".service-top-title").html( $(args).attr("data-title") );
			eachSearchVal();
		}
	};
	
	
	/**
	 * 退款-退货订单处理
	 */
	get.store_refund_handle = {
		url : function(args){
			return "seller/refund/handle";
		},
		tpl: function(args){
			return "seller/temp/service/order_refund::main";
		},
		params : function(args){
			return {refundId: $(args).attr("data-refund-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 商家 - 退款-退货详情
	 */
	get.store_refund_detail = {
		url : function(args){
			return "seller/refund/detail";
		},
		tpl: function(args){
			return "seller/temp/service/order_detail::main";
		},
		params : function(args){
			return {refundId: $(args).attr("data-refund-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 用户 - 退款-退货详情
	 */
	get.member_refund_detail = {
		url : function(args){
			return "member/refund/detail";
		},
		tpl: function(args){
			return "member/temp/service/order_detail::main";
		},
		params : function(args){
			return {refundId: $(args).attr("data-refund-id")};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 店铺优惠券列表
	 */
	get.store_coupon = {
		url : function(args){
			return "seller/coupon";
		},
		tpl: function(args){
			return "seller/temp/coupon/coupon::main";
		},
		params : function(args){
			if (args[1] == undefined || args[1] == 1) {
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
			$("#right").html(response.responseText);
		}
	};

	/**
	 * 删除优惠券
	 */
	get.coupon_destroy = {
			url : function(args){
				return "seller/coupon/destroy";
			},
			tpl: function(args){
				return "seller/temp/coupon/coupon::main";
			},
			params : function(args){
				return {coupon_id: $(args).attr("data-id")};
			},
			verification : function(args, params){
				return true;
			},
			callback : function(args, response){
				$("#right").html(response.responseText);
			}	
	};
	
	/**
	 * 店铺优惠券添加
	 */
	get.couponCreate = {
		url : function(args){
			return "seller/coupon/create";
		},
		tpl: function(args){
			return "seller/temp/coupon/coupon_create::main";
		},
		params : function(args){
			return {};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			$("#right").html(response.responseText);
		}
	};
	
	
	/**
	 * 添加店铺优惠券
	 */
	get.coupon_save = {
			url : function(args){
				return "seller/coupon/store";
			},
			tpl: function(args){
				return "seller/temp/coupon/coupon::main";
			},
			params : function(args){
				return formValue;
			},
			verification : function(args, params){
				return true;
			},
			callback : function(args, response){
				$("#right").html(response.responseText);
			}	
	};
	
	/**
	 * 修改店铺优惠券之前先查到优惠券
	 */
	get.coupon_edit = {
			url : function(args){
				return "seller/coupon/edit";
			},
			tpl: function(args){
				return "seller/temp/coupon/coupon_edit::main";
			},
			params : function(args){
				return {coupon_id: $(args).attr("data-id")};
			},
			verification : function(args, params){
				return true;
			},
			callback : function(args, response){
				$("#right").html(response.responseText);
			}	
	};
	
	/**
	 * 确认修改店铺优惠券
	 */
	get.coupon_update = {
			url : function(args){
				return "seller/coupon/update";
			},
			tpl: function(args){
				return "seller/temp/coupon/coupon::main";
			},
			params : function(args){
				return formValue;
			},
			verification : function(args, params){
				return true;
			},
			callback : function(args, response){
				$("#right").html(response.responseText);
			}
	};
	
	
	
	
});


/**
 * 店铺分类添加
 */
function storeGoodsClassifyStore() {
	$.ajax(ajaxSettings({
	    url: '/api/v1/get/seller/store/classify/store',
	    type: 'post',
	    data: formValue,
	    success: function(data) {
	    	successShopAlert("storeClassify");
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 店铺分类更新
 */
function storeGoodsClassifyUpdate() {
	$.ajax(ajaxSettings({
	    url: '/api/v1/get/seller/store/classify/update',
	    type: 'post',
	    data: formValue,
	    success: function(data) {
	    	successShopAlert("storeClassify");
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 用户订单列表 - 发起支付
 */
function memberOrderPay(me) {
	var orderId = $(me).attr("data-id");
	$.ajax(ajaxSettings({
	    url: '/pay/order_pay',
	    type: 'post',
	    data: {orderId: orderId},
	    success: function(data) {
	    	if ( data.success ) {
//	    		$("#clickOpenHref").attr("href", "/pay/select?uu="+data.data.uu);
//	    		$("#clickOpenHref").click();
//	    		$("#clickOpenHref").trigger("click");
	    		window.location.href="/pay/select?uu="+data.data.uu;

	    	} else {
	    		$.alert({
	    			type: 3,
	    			info: data.info,
	    			callback: ""
	    		});
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 取消订单
 */
function orderCancel() {
	$.ajax(ajaxSettings({
	    url: '/member/order/cancel',
	    type: 'post',
	    data: {id: orderId, text: $("select[name='cancel_reason'] option:selected").val()},
	    success: function(data) {
	    	if ( data.success ) {
	    		$.alert({
	    			type: 4,
	    			info: data.info
	    		});
	    		$(".s-edit-address-body").css("display", "none");
	    		setTimeout(function() {
	    			$("#memberOrderIndex").click();
	    			$(".yd-tipbox-mask").remove();
	    		}, 1000);
	    	} else {
	    		$.alert({
	    			type: 3,
	    			info: data.info
	    		});
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 删除取消订单
 */
function memberOrderDelete(me) {
	$.ajax(ajaxSettings({
		url: '/member/order/delete',
		type: 'post',
		data: {id: me.attr("data-id") },
		success: function(data) {
			if ( data.success ) {

				setTimeout(function () {
					$.alert({
						type: 4,
						info: data.info
					});
				}, 800);
				setTimeout(function() {
					$("#memberOrderIndex").click();
					$(".yd-tipbox-mask").remove();
				}, 1500);
			} else {
				$.alert({
					type: 3,
					info: data.info
				});
			}
		},
		error: function(data) {
			errorShopAlert(data);
		}
	}))
}


/**
 * 确认收货
 */
function orderConfirmReceipt(me) {
	$.ajax(ajaxSettings({
	    url: '/member/order/receipt',
	    type: 'post',
	    data: {id: me.attr("data-id")},
	    success: function(data) {
	    	if ( data.success ) {
	    		setTimeout(function() {
	    			$.alert({
		    			type: 4,
		    			info: data.info,
		    			callback: ""
		    		});
	    		}, 500);
	    		setTimeout(function() {
	    			$("#memberOrderIndex").click();
	    			$(".yd-tipbox-mask").remove();
	    		}, 2000);
	    	} else {
	    		$.alert({
	    			type: 3,
	    			info: data.info,
	    			callback: ""
	    		});
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 退款-提交
 */
function postRefund() {
	formValue["reasonNum"] = $(".reasonNum").val();
	formValue["picInfo"] = _goodsPics.join(",");
	$.ajax(ajaxSettings({
	    url: '/member/refund/store',
	    type: 'post',
	    data: formValue,
	    success: function(data) {
	    	if ( data.success ) {
				$.alert({
					type: 4,
					info: data.info,
					callback: ""
				});
				setTimeout(function () {
					$("#myService").click();
				}, 1500);
	    	} else {
	    		$.alert({
	    			type: 3,
	    			info: data.info,
	    			callback: ""
	    		});
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 用户投诉-提交
 */
function memberComplainStore() {
	formValue["complain_pics"] = _goodsPics.join(",");
	
	if ( formValue["complain_subject_id"] == undefined ) {
		$.alert({
			type: 3,
			info: "请选择投诉主题",
			callback: ""
		});
		return;
	}
	
	$.ajax(ajaxSettings({
	    url: '/member/complain/store',
	    type: 'post',
	    data: formValue,
	    success: function(data) {
	    	if ( data.success ) {
				$.alert({
					type: 4,
					info: data.info,
					callback: ""
				});
				setTimeout(function () {
					$("#myService").click();
				}, 1500);
	    	} else {
	    		$.alert({
	    			type: 3,
	    			info: data.info,
	    			callback: ""
	    		});
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 用户取消投诉
 */
function memberComplainDelete(me) {
	$.ajax(ajaxSettings({
	    url: '/member/complain/delete',
	    type: 'post',
	    data: {complainId: me.attr("data-id")},
	    success: function(data) {
	    	if ( data.success ) {
				setTimeout(function () {
					$.alert({
						type: 4,
						info: data.info,
						callback: ""
					});
				}, 500);
				setTimeout(function () {
					$("#myComplain").click();
				}, 1500);
	    	} else {
	    		errorShopAlert(data);
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}



/**
 * 商家处理退款-提交
 */
function sellerHandleRefund() {
	// formValue["reasonNum"] = $(".reasonNum").val();
	$.ajax(ajaxSettings({
	    url: '/seller/refund/update',
	    type: 'post',
	    data: formValue,
	    success: function(data) {
	    	if ( data.success ) {
				$.alert({
					type: 4,
					info: data.info,
					callback: ""
				});
				setTimeout(function () {
					$("#storeRefund").click();
				}, 1000);
			} else {
	    		$.alert({
	    			type: 3,
	    			info: data.info,
	    			callback: ""
	    		});
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 商家收到退货商品并执行退款
 */
function sellerReceiveGoods(me) {
	$.ajax(ajaxSettings({
	    url: '/seller/refund/playMoney',
	    type: 'post',
	    data: {refundId: me.attr("data-refund-id")},
	    success: function(data) {
	    	if ( data.success ) {
	    		
	    	} else {
	    		$.alert({
	    			type: 3,
	    			info: data.info,
	    			callback: ""
	    		});
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 商品上架-下架
 */
function goodsShowHide(me) {
	var up = me.attr("data-show") == 2 ? 1 : 2;
	$.ajax(ajaxSettings({
	    url: '/seller/goods/show-hide',
	    type: 'post',
	    data: {id: me.attr("data-id"), up: up},
	    success: function(data) {
	    	if ( data.success ) {
	    		setTimeout(function() {
	    			$.alert({
		    			type: 4,
		    			info: data.info,
		    			callback: ""
		    		});
	    		}, 500);
	    		setTimeout(function() {
	    			if ( up == 1 ) {
	    				$("#goodsDepot").click();
	    				var th = $("#goodsDepot");
	    				goodsDepot([th, 1]);
	    			} else {
	    				$("#goodsIndex").click();
	    			}
	    			
	    			$(".yd-tipbox-mask").remove();
	    		}, 2000);
	    	} else {
	    		$.alert({
	    			type: 3,
	    			info: data.info,
	    			callback: ""
	    		});
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 更新收货地址
 */
function takeDeliveryUpdate() {
	$.ajax(ajaxSettings({
	    url: '/seller/order/update-take',
	    type: 'post',
	    data: {order_id: $(".btn-edit-take-address-sub").attr("data-id"), text: $(".area-take-address").val()},
	    success: function(data) {
	    	if ( data.success ) {
	    		setTimeout(function() {
	    			$.alert({
		    			type: 4,
		    			info: data.info,
		    			callback: ""
		    		});
	    			$(".s-edit-address-take-body").css("display", "none");
	    			$(".text-receipt-address").html( $(".area-take-address").val() );
	    		}, 300);
	    		setTimeout(function() {
	    			$(".yd-tipbox-mask").remove();
	    		}, 1500);
	    	} else {
	    		$.alert({
	    			type: 3,
	    			info: data.info,
	    			callback: ""
	    		});
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 发货
 */
function orderDeliverySub(dataJson) {
	$.ajax(ajaxSettings({
	    url: '/seller/order/deliver',
	    type: 'post',
	    data: dataJson,
	    success: function(data) {
	    	if ( data.success ) {
	    		setTimeout(function() {
	    			$.alert({
		    			type: 4,
		    			info: data.info,
		    			callback: ""
		    		});
	    		}, 300);
	    		setTimeout(function() {
	    			$("#storeOrderIndex").click();
	    		}, 1500);
	    	} else {
	    		$.alert({
	    			type: 3,
	    			info: data.info,
	    			callback: ""
	    		});
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 秒杀取消
 */
function seckillCancel(me) {
	$.ajax(ajaxSettings({
	    url: '/seller/seckill/cancel',
	    type: 'post',
	    data: {sgId: me.attr("data-id")},
	    success: function(data) {
	    	if ( data.success ) {
	    		setTimeout(function() {
	    			$.alert({
		    			type: 4,
		    			info: data.info,
		    			callback: ""
		    		});
	    		}, 300);
	    		setTimeout(function() {
	    			window.location.href = "/seller/seckill";
	    		}, 1500);
	    	} else {
	    		errorShopAlert(data);
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}))
}


/**
 * 搜索商品
 */
function searchGoods(goodsName, node) {
	$.ajax(ajaxSettings({
	    url: '/seller/goods/searchName',
	    type: 'post',
	    data: {goodsName: goodsName},
	    success: function(data) {
	    	if ( data.success ) {
	    		var str = "";
	    		var datas = data.data.data;
	    		for(var ele in datas) {
	    			str = '<div class="select-godos-item" data-id="'+ datas[ele]["depotId"] +'">'+
			        		'<img src="'+ _urlGoods+ datas[ele]["goodsCover"]+"?x-oss-process=image/resize,m_pad,h_120,w_120" +'" />'+
			        		'<p>'+ datas[ele]["goodsName"] +'</p>'+
			        		'<p>价格：￥'+ datas[ele]["depotPrice"] +'</p>'+
			        	'</div>';
	    			node.append( str );
	    		}
	    	} else {
	    		errorShopAlert(data);
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}));
}


/**
 * 添加秒杀商品
 */
function seckillGoodsStore() {
	$.ajax(ajaxSettings({
	    url: '/seller/seckill/store',
	    type: 'post',
	    data: formValue,
	    success: function(data) {
	    	if ( data.success ) {
	    		setTimeout(function() {
	    			$.alert({
	    				type: 4,
	    				info: data.info
	    			});
	    		}, 500);
	    		setTimeout(function() {
	    			window.location.href = "/seller/seckill";
	    		}, 1500);
	    	} else {
	    		errorShopAlert(data);
	    	}
	    },
	    error: function(data) {
	    	errorShopAlert(data);
	    }
	}));
}