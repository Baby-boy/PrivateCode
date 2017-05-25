document.onkeydown=keyDownSearch;
function keyDownSearch(e) {  
    // 兼容FF和IE和Opera  
    var theEvent = e || window.event;  
    var code = theEvent.keyCode || theEvent.which || theEvent.charCode;  
    if (code == 13) {   
        return false;  
    }  
    return true;  
} 

var where = {};	// 全局变量 条件
var formValue = {};	// 全局变量 form表单值
var _data = {};	// 全局变量 循环的所有值
$(function(){
	// 编辑-商家地址
	$("body").on("click", ".s-edit-address", function() {
		var id = $(this).attr("data-id");
		
		// 填充input
		restoreFormInput(_data[id], ".s-edit-address-body");
		// 填充select
		var selectData = {saProvinceId: _data[id].saProvinceId, saCityId: _data[id].saCityId, saAreaId: _data[id].saAreaId};
		restorePca(selectData, ".s-edit-address-body");
		
		$(".s-edit-address-body").css("display", "block");
	});
	
	
	// 操作修改一条数据
	$("body").on("click", ".btn-operate-row-data", function () {
		
	});
	
	// 选择分类
	$("body").on("change", ".select-class select", function() {
		getClass($(this));
	});
	
	// 选择店铺分类
	$("body").on("change", ".depot_classify12", function() {
		
	});
	
	// 选择ctc分类
	$("body").on("change", ".select-ctc-class select", function() {
		getCtcClassSon($(this));
	});
	
	// 选择省市区-填充
	$("body").on("change", ".select-city select", function() {
		var str = '';
		$(this).parent().find("select").each(function() {
			str += $(this).find("option:selected").text()+"-";
		});
		str = str.substring(0, str.length-1);
		$(this).parent().find("input[data-pca='pca']").val( str );
	});
	
	
	// 搜索
	$("body").on("click", ".btn-search-body", function() {
		var node = $(this).parents("form.search-body-form");
		var data = node.serializeArray();
		$.each(data, function(k, v) {
			where[ v["name"] ] = v["value"];
		});
		where["p"] = 1;
		window[ node.attr("data-search-func") ]( $(".btn-page-top li").eq(where["btnq"]) );
	});
	
	
	// 新增-修改数据集
	$("body").on("click", ".btn-add-edit", function() {
		var parentNode = $(this).parents("form.form-ajax-sub");
		var value = parentNode.serializeArray();
		var verify = false;
		$.each(value, function(k, v) {
			if ( parentNode.find('*[name="'+v["name"]+'"]').attr("data-verify") == "true" && parentNode.find('*[name="'+v["name"]+'"]').val() == "" ) {
				verify = true;
				$.alert({info: $("*[name='"+v["name"]+"']").attr("data-error-info") });
				return false;
			}
			formValue[ v["name"] ] = v["value"];
		});
		
		if ( !verify ) {
			window[ $(this).parents("form.form-ajax-sub").attr("data-func") ]();
		}
	});
	
	
	// 商品图片 - 左右移动
	$('body').on('click', '.up-image-goods-pics a.prev', function() {
		var aa = $(this).parent();
		$(aa).prev().before( aa );
		
		_goodsPics = [];
		$(".up-image-goods-pics ul li").each(function(k, v) {
			_goodsPics.push( $(this).attr("data-img") );
		});
	});
	$('body').on('click', '.up-image-goods-pics a.next', function() {
		var aa = $(this).parent();
		$(aa).next().after( aa );
		
		_goodsPics = [];
		$(".up-image-goods-pics ul li").each(function(k, v) {
			_goodsPics.push( $(this).attr("data-img") );
		});
	});
	
	// 商品详情图片 - 左右移动
	$('body').on('click', '.up-image-goods-content-pics a.prev', function() {
		var aa = $(this).parent();
		$(aa).prev().before( aa );
		
		_goodsContentPics = [];
		$(".up-image-goods-content-pics ul li").each(function(k, v) {
			_goodsContentPics.push( $(this).attr("data-img") );
		});
	});
	$('body').on('click', '.up-image-goods-content-pics a.next', function() {
		var aa = $(this).parent();
		$(aa).next().after( aa );
		
		_goodsContentPics = [];
		$(".up-image-goods-content-pics ul li").each(function(k, v) {
			_goodsContentPics.push( $(this).attr("data-img") );
		});
	});
	
	
	// 添加商品页-运费
	$("body").on("click", ".goods_freight input[type='radio']", function() {
		$(this).parents(".goods_freight").find(".gf div").css("display", "none");
		$(this).parent().next().css("display", "block");
	});
	
	// 添加商品页-属性
	$("body").on("click", ".btn-add-attr", function() {
	 	var str = '';
	 	str = '<div class="attr-node">'+
                '<input type="text" name="attr_title" placeholder="属性标题" /> '+
                '<input type="text" name="attr_value" placeholder="属性值" /> <a class="prev" href="javascript:void(0)">向上</a><a class="next" href="javascript:void(0)">向下</a><a class="del" href="javascript:void(0)">移除</a>'+
            '</div>';
	 	$('.goods-create-page .attr-lists').append(str);
	});
	$('body').on('click', '.cg-attr-lists .attr-node a.del', function() {
		if ($('.cg-attr-lists .attr-node').length <= 1) { return; }
		$(this).parent().remove();
	});
	$('body').on('click', '.cg-attr-lists .attr-node a.prev', function() {
		var aa = $(this).parent();
		$(aa).prev().before( aa );
	});
	$('body').on('click', '.cg-attr-lists .attr-node a.next', function() {
		var aa = $(this).parent();
		$(aa).next().after( aa );
	});

	// 按钮上传
	$("body").on("click", ".btn-file a", function() {
		$(this).next("input[type='file']").click();
	});
	
	// 全选
	$("body").on("click", ".check-all", function() {
		checkAll( $(this).attr("data-check-class") );
	});
	
	// 全选-取消
	$("body").on("click", ".check-cancel-all", function() {
		checkCancelAll( $(this).attr("data-check-class") );
	});
	
	// 返选
	$("body").on("click", ".check-return-all", function() {
		checkReturnAll( $(this).attr("data-check-class") );
	});
	
	// 关闭窗口-隐藏
	$("body").on("click", ".close-win", function() {
		$( $(this).attr("data-close-class") ).css("display", "none");
	});
	
	// 删除弹窗
	$("body").on("click", ".del-alert", function() {
		var func = $(this).attr("data-func");
		var me = $(this);
		$.confirm({
			text:"是否确认删除？",
			intro:"删除后将无法找到。",
			callback: function () {
				window[func]( me );
			}
		});
	});
	
	// 上传商品图片，删除
	$("body").on("click", ".up-image-body li a.del", function() {
		var name = $(this).parent().parent().prev().attr("name");
		if ( name == "goods_pics" ) {
			for(var i = 0; i < _goodsPics.length; i++) {
				if ( _goodsPics[i] == $(this).parent().attr("data-img") ) {
					_goodsPics.splice( i, 1 );
				}
			}
		} else if ( name == "goods_content_pics" ) {
			for(var i = 0; i < _goodsContentPics.length; i++) {
				if ( _goodsContentPics[i] == $(this).parent().attr("data-img") ) {
					_goodsContentPics.splice( i, 1 );
				}
			}
		}
		
		$(this).parent().remove();
	});
	
	// 为上传图片按钮添加事件
	$("body").on("change", "input[data-btn='upload']", function() {
		uploadCode = $(this).attr("data-upload-code");
		uploadFile( $(this).attr("id") );
	});
	
	// 店铺banner 删除事件
	$("body").on("click", ".store-branner-thumb-list a.del", function() {
		$(this).parent().remove();
	});
	
	// 发布商品 - 选择分类后获取的信息
	$("body").on("change", ".goods-create-page select[name='depot_classify3']", function() {
		getGoodsType( $(this) );
	}).on("change", ".goods-c-e select[name='brand_id']", function() {
		$(this).prev().val( $(this).find("option:selected").text() );
	});

	
	/**
	 * 取消订单-提交
	 */
	$("body").on("click", "#btnCancelOrder", function() {
		orderCancel();
	});
	
	
	/**
	 * 编辑收货地址
	 */
	$("body").on("click", ".btn-edit-take-delivery", function () {
		$(".s-edit-address-take-body").css("display", "block");
	});
	
	
	/**
	 * 更新收货地址
	 */
	$("body").on("click", ".btn-edit-take-address-sub", function() {
		takeDeliveryUpdate();
	});
	
	
	/**
	 * 编辑发货地址
	 */
	$("body").on("click", ".btn-edit-arss", function() {
		$(".s-edit-address-body").css("display", "block");
	});
	
	
	/**
	 * 选择发货地址
	 */
	$("body").on("click", ".btn-sel-delivery-arss", function() {
		$(".geliver-goods-address").html( $(this).attr("data-data") );
		$(".s-edit-address-body").css("display", "none");
		$(".geliver-goods-address").attr("data-id", $(this).attr("data-id"));
	});
	
	
	/**
	 * 确认发货
	 */
	$("body").on("click", ".btn-confirm-delivery", function() {
		var shippingExpress = $("select[name='shipping_express']").val();	// 物流公司
		var logisCode = $("input[name='logis_code']").val();				// 物流公司编号
		var shippingCode = $("input[name='shipping_code']").val();			// 物流单号
		var deliverExplain = $("textarea[name='deliver_explain']").val();	// 发货备注
		var shippingAddress = $(".geliver-goods-address").html();			// 发货地址
		var shippingId = $(".geliver-goods-address").attr("data-id");		// 发货地址id
		
		var json = {};
		json["shipping_express"] = shippingExpress;
		json["logis_code"] = logisCode;
		json["shipping_code"] = shippingCode;
		json["deliver_explain"] = deliverExplain;
		json["shipping_address"] = shippingAddress;
		json["shipping_id"] = shippingId;
		json["order_id"] = orderId;
		
		if (shippingExpress == "") {
			$.alert({
				type: 3,
				info: "请输入物流公司"
			});
			return;
		}
		
		if (shippingCode == "") {
			$.alert({
				type: 3,
				info: "请输入物流单号"
			});
			return;
		}
		
		if (shippingId == "") {
			$.alert({
				type: 3,
				info: "请选择发货地址"
			});
			return;
		}
		
		// 发货
		orderDeliverySub(json);
	});
	
	
	// 退款-退货  - 处理
	$("body").on("click", ".btn-refund-handle", function() {
		sellerRefundHandle( $(this) );
	});
	
	// 商家 - 退款-退货  - 详情
	$("body").on("click", ".btn-refund-detail", function() {
		sellerRefundDetail( $(this) );
	});
	
	// 用户 - 退款-退货  - 详情
	$("body").on("click", ".btn-member-refund-detail", function() {
		memberRefundDetail( $(this) );
	});
	
	// 优惠券-类型选择
	$("body").on("change", "input[name='coupon_store_type']", function() {
		if ( $(this).val() == 1 ) {
			$(".coupon_full_money").show();
		} else {
			$(".coupon_full_money").hide();
		}
	});
	
	
	// 添加秒杀商品 - 搜索商品
	$("body").on("click", ".seckill-goods-create .btn-search-goods", function() {
		var val = $(this).prev().val();
		if ( val == "" ) {
			$.alert({
				type: 1,
				info: "请输入商品名称"
			});
			return;
		} else {
			searchGoods(val, $(".seckill-goods-lists"));
		}
	});
	
	
	// 选中秒杀商品
	$("body").on("click", ".seckill-goods-lists .select-godos-item", function() {
		$(this).siblings().removeClass("active");
		$(this).addClass("active");
		$("#depotId").val( $(this).attr("data-id") );
	});
});




// 全选
function checkAll(classStr) {
	$(classStr).find("input").each(function() {
		$(this).prop("checked", true);
	});
}
// 全选-取消
function checkCancelAll(classStr) {
	$(classStr).find("input").each(function() {
		$(this).prop("checked", false);
	});
}
// 返选
function checkReturnAll(classStr) {
	$(classStr).find("input").each(function() {
		if ( $(this).prop("checked") == true) {
			$(this).prop("checked", false);
		} else {
			$(this).prop("checked", true);
		}
	});
}

/**
 * select optoin each
 * @param data array 数据集
 * @param me object  $(this)
 */
function eachOption(data, me) {
	me.nextAll("select").css("display", "none");
	var str = "<option value=''>"+me.next("select").attr("data-first-title")+"</option>";
	var classData = data.data;
	
	for (var ele in classData) {
		str += "<option value='"+classData[ele]["classId"]+"'>"+classData[ele]["classTitle"]+"</option>";
	}
	me.next('select').html(str);
	me.next('select').removeAttr("disabled");
	me.next('select').css("display", "inline-block");
}


/**
 * select optoin each
 * @param data array 数据集
 * @param me object  $(this)
 */
function eachCtcOption(data, me) {
	me.nextAll("select").css("display", "none");
	var str = "<option value=''>"+me.next("select").attr("data-first-title")+"</option>";
	var classData = data.data;
	
	for (var ele in classData) {
		str += "<option value='"+classData[ele]["cc_id"]+"'>"+classData[ele]["cc_name"]+"</option>";
	}
	me.next('select').html(str);
	me.next('select').removeAttr("disabled");
	me.next('select').css("display", "inline-block");
}

/**
 * 二级tree，循环option
 * @param data	object	数据集
 * @param str	string	默认option文字信息
 * @param node	string	追加到哪个class或id中
 */
function treeEachOption(data, str, node, val) {
	var map = [];
	$.each(data, function (k, v) {
		if (v["pid"] == 0) {
			map[ v["sc_id"] ] = v;;
			map[ v["sc_id"] ]["son"] = [];
		} else {
			map[ v["pid"] ]["son"].push( v );
		}
	});
	
	var strHtml = '';
	strHtml += "<option value='0'>"+str+"</option>";
	for(var ele in map) {
		var son = map[ele]["son"];
		var selected = map[ele]["sc_id"] == val ? " selected = 'selected' " : '';
		strHtml += "<option "+selected+" value="+map[ele]["sc_id"]+">"+map[ele]["title"]+"</option>";
		for(var el in son) {
			var selected = son[el]["sc_id"] == val ? " selected = 'selected' " : '';
			strHtml += "<option "+selected+" value="+son[el]["sc_id"]+">&nbsp;&nbsp;&nbsp;"+son[el]["title"]+"</option>";
		}
	}
	$(node).html(strHtml);
}


function treeEachStoreGoodsCLassify(data, node) {
	var map = [];
	$.each(data, function (k, v) {
		if (v["pid"] == 0) {
			map[ v["sc_id"] ] = v;;
			map[ v["sc_id"] ]["son"] = [];
		} else {
			map[ v["pid"] ]["son"].push( v );
		}
	});
	var strHtml = '';
	for(var ele in map) {
		var son = map[ele]["son"];
		strHtml += '<tr>'
						+'<td class="w50"><input type="checkbox" /></td>'
						+'<td class="al">'+map[ele]["title"]+'</td>'
						+'<td>'+map[ele]["sort"]+'</td>'
						+'<td><a href="javascript:void(0)" data-id='+map[ele]["sc_id"]+' data-is-show='+map[ele]["is_show"]+' onclick="storeGoodsClassifySetShow(this)">';
		strHtml += map[ele]["is_show"] == 1 ? '显示' : '隐藏';
		strHtml += '</a></td>'
						+'<td class="btn-t-o">'
							+'<span><a href="javascript:void(0)" data-id='+map[ele]["sc_id"]+' onclick="storeGoodsClassifyEdit(this)" >编辑</a></span>'
							+'<span><a href="javascript:void(0)" class="del-alert" data-id='+map[ele]["sc_id"]+' data-func="storeGoodsClassifyDestroy">删除</a></span>'
						+'</td>'
					+'</tr>';
		for(var el in son) {
			strHtml += '<tr>'
				+'<td class="w50"><input type="checkbox" /></td>'
				+'<td class="al son"><i></i>'+son[el]["title"]+'</td>'
				+'<td>'+son[el]["sort"]+'</td>'
				+'<td><a href="javascript:void(0)" data-id='+son[el]["sc_id"]+' data-is-show='+son[el]["is_show"]+' onclick="storeGoodsClassifySetShow(this)">';
				strHtml += son[el]["is_show"] == 1 ? '显示' : '隐藏';
				strHtml += '</a></td>'
				+'<td class="btn-t-o">'
					+'<span><a href="javascript:void(0)" data-id='+son[el]["sc_id"]+' onclick="storeGoodsClassifyEdit(this)" >编辑</a></span>'
					+'<span><a href="javascript:void(0)" class="del-alert" data-id='+son[el]["sc_id"]+' data-func="storeGoodsClassifyDestroy">删除</a></span>'
				+'</td>'
			+'</tr>';
		}
	}
	$(node).html(strHtml);
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

/**
 * 搜索赋值
 */
function eachSearchVal() {
	$.each(where, function(k, v) {
		$('form.search-body-form input[name="'+k+'"]').val( v );
	});
}

/**
 * btn 筛选
 * @param args  this 
 */
function btnQActive(args) {
	$(".btn-page-top li").removeClass("active");
	if (args[2] != undefined) {
		where["btnq"] = args[2];
	} else {
		if (typeof(args[0]) == "undefined") {
			where["btnq"] = 0;
		} else {
			where["btnq"] = where["btnq"] == undefined ? 0 : where["btnq"];
		}
	}
	$(".btn-page-top li").eq( where["btnq"] ).addClass("active");
}

/**
 * 循环的所有值 以id为键
 */
function _dataIdKey (data, key) {
	$.each(data, function(k, v) {
		_data[v[key]] = v;
	});
}

/**
 * 编辑-还原省市区
 * param p integer  省
 * param c integer  市
 * param a integer  区
 */
function restorePca(data, node) {
	$.each(data, function(k, v) {
		if ( $(node).find("select[name='"+k+"']").length > 0 ) {
			$(node).find("select[name='"+k+"'] option[value='"+v+"']").prop("selected", true);
			$(node).find("select[name='"+k+"']").change();
		}
	});
}

/**
 * 编辑-还原所有input
 */
function restoreFormInput(data, node) {
	$.each(data, function(k, v) {
		$(node).find("input[type='text'][name='"+k+"']").val( v );
		$(node).find("input[type='hidden'][name='"+k+"']").val( v );
		$(node).find("input[type='number'][name='"+k+"']").val( v );
	});
}


/**
 * 获取搜索的值
 */
function getSearchParam() {
	var node = $(this).parents("form.search-body-form");
	var data = node.serializeArray();
	$.each(data, function(k, v) {
		where[ v["name"] ] = v["value"];
	});
}