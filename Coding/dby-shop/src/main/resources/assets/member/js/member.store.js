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
var _mobileCodeToken = "";		// 全局变量  发送手机号返回的token
var _imageCodeToken = "";		// 全局变量  图片验证码返回的token
$(function(){
	// 编辑-商家地址
	$("body").on("click", ".s-edit-address", function() {
		var id = $(this).attr("data-id");
		
		// 填充input
		restoreFormInput(_data[id], ".s-edit-address-body");
		// 填充select
		var selectData = {adProvinceId: _data[id].adProvinceId, adCityId: _data[id].adCityId, adAreaId: _data[id].adAreaId};

		restorePca(selectData, ".s-edit-address-body");
		$(".s-edit-address-body").find("select[name='adSex'] option[value='"+_data[id].adSex+"']").prop("selected", true);

		$(".s-edit-address-body").css("display", "block");
	});
	
	
	// 操作修改一条数据
	$("body").on("click", ".btn-operate-row-data", function () {
		
	});
	
	// 选择分类
	$("body").on("change", ".select-class select", function() {
		getClass($(this));
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
		if ( !verify && parentNode.find('input[name="password"]').length > 0 && parentNode.find('input[name="password_confirmation"]').length > 0 && parentNode.find('input[name="password"]').val() != parentNode.find('input[name="password_confirmation"]').val() ) {
			verify = true;
			$.alert({info: "两次密码输入不一致" });
		}
		
		if ( !verify ) {
			window[ $(this).parents("form.form-ajax-sub").attr("data-func") ]();
		}
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
                '<input type="text" name="attr_title[]" placeholder="属性标题"> '+
                '<input type="text" name="attr_value[]" placeholder="属性值"> <a class="prev" href="javascript:void(0)">向上</a><a class="next" href="javascript:void(0)">向下</a><a class="del" href="javascript:void(0)">移除</a>'+
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
		$(this).next("input").click();
	});
	
	// 全选
	$("body").on("click", ".check-all", function() {
		checkAll( $(this).attr("data-check-class") );
		if ( $(this).prop("checked") == true ) {
			$(this).removeClass("check-all");
			$(this).addClass("check-cancel-all");
		}
	});
	
	// 全选-取消
	$("body").on("click", ".check-cancel-all", function() {
		 checkCancelAll( $(this).attr("data-check-class") );
		 if( $(this).prop("checked") == false ) {
				$(this).removeClass("check-cancel-all");
				$(this).addClass("check-all");
		 }
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
	var str = "<option value='0'>"+me.next("select").attr("data-first-title")+"</option>";
	var classData = data.data;
	
	for (var ele in classData) {
		str += "<option value='"+classData[ele]["classId"]+"'>"+classData[ele]["classTitle"]+"</option>";
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
			map[ v["scId"] ] = v;;
			map[ v["scId"] ]["son"] = [];
		} else {
			map[ v["pid"] ]["son"].push( v );
		}
	});
	
	var strHtml = '';
	strHtml += "<option value=''>"+str+"</option>";
	for(var ele in map) {
		var son = map[ele]["son"];
		var selected = map[ele]["scId"] == val ? " selected = 'selected' " : '';
		strHtml += "<option "+selected+" value="+map[ele]["scId"]+">"+map[ele]["title"]+"</option>";
		for(var el in son) {
			var selected = son[el]["scId"] == val ? " selected = 'selected' " : '';
			strHtml += "<option "+selected+" value="+son[el]["scId"]+">&nbsp;&nbsp;&nbsp;"+son[el]["title"]+"</option>";
		}
	}
	$(node).html(strHtml);
}


function treeEachStoreGoodsCLassify(data, node) {
	var map = [];
	$.each(data, function (k, v) {
		if (v["pid"] == 0) {
			map[ v["scId"] ] = v;;
			map[ v["scId"] ]["son"] = [];
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
						+'<td><a href="javascript:void(0)" data-id='+map[ele]["scId"]+' data-is-show='+map[ele]["isShow"]+' onclick="storeGoodsClassifySetShow(this)">';
		strHtml += map[ele]["isShow"] == 1 ? '显示' : '隐藏';
		strHtml += '</a></td>'
						+'<td class="btn-t-o">'
							+'<span><a href="javascript:void(0)" data-id='+map[ele]["scId"]+' onclick="storeGoodsClassifyEdit(this)" >编辑</a></span>'
							+'<span><a href="javascript:void(0)" class="del-alert" data-id='+map[ele]["scId"]+' data-func="storeGoodsClassifyDestroy">删除</a></span>'
						+'</td>'
					+'</tr>';
		for(var el in son) {
			strHtml += '<tr>'
				+'<td class="w50"><input type="checkbox" /></td>'
				+'<td class="al son"><i></i>'+son[el]["title"]+'</td>'
				+'<td>'+son[el]["sort"]+'</td>'
				+'<td><a href="javascript:void(0)" data-id='+son[el]["scId"]+' data-is-show='+son[el]["isShow"]+' onclick="storeGoodsClassifySetShow(this)">';
				strHtml += son[el]["isShow"] == 1 ? '显示' : '隐藏';
				strHtml += '</a></td>'
				+'<td class="btn-t-o">'
					+'<span><a href="javascript:void(0)" data-id='+son[el]["scId"]+' onclick="storeGoodsClassifyEdit(this)" >编辑</a></span>'
					+'<span><a href="javascript:void(0)" class="del-alert" data-id='+son[el]["scId"]+' data-func="storeGoodsClassifyDestroy">删除</a></span>'
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
 * btn 筛选 - 二级btn
 * @param args  this 
 */
function btnQTwoActive(args) {
	$(".btn-page-top-two li").removeClass("active");
	if (args[2] != undefined) {
		where["btnq"] = args[2];
	} else {
		if (typeof(args[0]) == "undefined") {
			where["btnq"] = 0;
		} else {
			where["btnq"] = where["btnq"] == undefined ? 0 : where["btnq"];
		}
	}
	$(".btn-page-top-two li").eq( where["btnq"] ).addClass("active");
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
	});
}

/**
 * 倒计时
 */
var countDownI = 3;
function countDownUrl(node, func) {
	countDownI--;
	if (countDownI == 0) {
		window[ func ]();
		countDownI = 3;
		return false;
	}
	setTimeout(function() {
		$(node).html( countDownI );
		countDownUrl(node, func);
	}, 1000);
}