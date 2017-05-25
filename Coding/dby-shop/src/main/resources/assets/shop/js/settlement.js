$(function () {

    // 收货地址选择
    $(".settlement-page .buyer").on("click", " ul li i", function () {
        $(this).parents("li").addClass("active").siblings().removeClass("active");
    });

    // 送货方式
    $(".settlement-page .inventory .left ul li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
        if ($(this).attr("data-val") == 1) {
            $(this).parent().next().css("display", "block");
        } else {
            $(this).parent().next().css("display", "none");
        }
    });


    // 运送
    $(".btn-deliver-mode").click(function () {
        var me = $(this);

        formValue["uu"] = _uu;
        formValue["token"] = _token;
        formValue["type"] = $(this).attr("data-reactid");
        formValue["value"] = $(this).attr("data-val");
        $.ajax(ajaxSettings({
            url: '/pay/update_order_confirm',
            type: 'post',
            data: formValue,
            success: function (data) {
                var data = data.data;
                _uu = data.uu;
                _token = data.token;
                me.parents("div[class='order-order'][data-reactid='" + data.sn + "']").find(".g_price").html("￥" + data.shopSubtotal);
                me.parents("div[class='order-order'][data-reactid='" + data.sn + "']").find(".g_price").attr("data-val", data.shopSubtotal);

                me.addClass("active").siblings().removeClass("active");
                if (me.attr("data-val") == 1) {
                    me.parents(".order-deliveryMethod").next().css("display", "block");
                } else {
                    me.parents(".order-deliveryMethod").next().css("display", "none");
                }
                computeTotalAmount();
            },
            error: function (data) {
                $.alert({
                    info: "数据错误！请刷新页面重试"
                });
            }
        }));
    });


    // 优惠
    $("select.ins-select").change(function () {
        var me = $(this);
        var val = $(this).find("option:selected").val();

        formValue["uu"] = _uu;
        formValue["token"] = _token;
        formValue["type"] = $(this).attr("data-reactid");
        formValue["value"] = val;
        $.ajax(ajaxSettings({
            url: '/pay/update_order_confirm',
            type: 'post',
            data: formValue,
            success: function (data) {
                var data = data.data;
                _uu = data.uu;
                _token = data.token;
                me.parents("div[class='order-order'][data-reactid='" + data.sn + "']").find(".g_price").html("￥" + data.shopSubtotal);
                me.parents("div[class='order-order'][data-reactid='" + data.sn + "']").find(".g_price").attr("data-val", data.shopSubtotal);
                computeTotalAmount();

                $(".ins-select option").css("display", "block");
                $(".ins-select option[data-type='1'][value='" + val + "']").css("display", "none");

                $(".ins-select option[data-type='1']:selected").each(function () {
                    me.find("option[value='" + $(this).val() + "']").css("display", "none");
                });
            },
            error: function (data) {
                $.alert({
                    info: "数据错误！请刷新页面重试"
                });
            }
        }));

    });

    // 提交订单 - 普通商品提交
    var addressId = 0;	// 地址id
    var message = {};	// 留言信息
    var invoice = {};	// 发票信息
    invoice["type"] = 1;	// 默认没有发票
    $(".go-btn-order").click(function () {
        // $(".address-list ul li.active")
        // 判断是否选择地址
        if (orderNum != $(".btn-deliver-mode.active[data-val='1']").length) {
            if ($(".address-list ul li.active").length == 0) {
                $.alert({info: "未选择收货地址！"});
                return;
            } else if ($(".address-list ul li.active").length > 1) {
                $.alert({info: "选择收货地址选择有误！"});
                return;
            }
            addressId = $(".address-list ul li.active").attr("data-val");
        }

        // 获取留言信息
        $(".text-area-input").each(function (k, v) {
            message[$(this).attr("data-reactid")] = $(this).val();
        });

        // 提交
        delete formValue["_method"];
        formValue["uu"] = _uu;
        formValue["token"] = _token;
        formValue["key"] = key;
        formValue["addressId"] = addressId;
        formValue["message"] = JSON.stringify(message);
        formValue["invoice"] = JSON.stringify(invoice);
        $.ajax(ajaxSettings({
            url: '/pay/generate_order',
            type: 'post',
            data: formValue,
            success: function (data) {
                window.location.href = "/pay/select?uu=" + data.data.uu;
            },
            error: function (data) {
                var info = "数据错误！请刷新页面重试";
                if (data.info != undefined && data.info != "") {
                    info = data.info;
                }
                $.alert({
                    info: info
                });
            }
        }));
    });


    // 提交订单 - ctc商品提交
    var addressCtcId = 0;	// 地址id
    var messageCtc = {};	// 留言信息
    $(".go-ctc-btn-order").click(function () {
        // $(".address-list ul li.active")
        // 判断是否选择地址
        if (orderNum != $(".btn-deliver-mode.active[data-val='1']").length) {
            if ($(".address-list ul li.active").length == 0) {
                $.alert({info: "未选择收货地址！"});
                return;
            } else if ($(".address-list ul li.active").length > 1) {
                $.alert({info: "选择收货地址选择有误！"});
                return;
            }
        }
        addressCtcId = $(".address-list ul li.active").attr("data-val");
        // 获取留言信息
        messageCtc = $(".text-area-input").val();

        // 提交
        delete formValue["_method"];
        formValue["addressId"] = addressCtcId;
        formValue["message"] = messageCtc;
        formValue["ctc_id"] = _ctcId;
        $.ajax(ajaxSettings({
            url: '/pay/generate_ctc_order',
            type: 'post',
            data: formValue,
            success: function (data) {
                window.location.href = "/pay/ctc_select?uu=" + data.data;
            },
            error: function (data) {
                var info = "数据错误！请刷新页面重试";
                if (data.info != undefined && data.info != "") {
                    info = data.info;
                }
                $.alert({
                    info: info
                });
            }
        }));
    });


    /**
     * 支付 - 验证
     */
    $("#paySub").click(function () {
        $.ajax({
            type: "post",
            url: "/pay/payment",
            data: {key: orderUu, type: $(".payment-type li.active").attr("data-val")},
            dataType: "json",
            success: function (data) {
                pingpp.createPayment(data.data, function (result, err) {
                    if (result == "success") {
                        // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的支付结果都会跳转到 extra 中对应的 URL。
                    } else if (result == "fail") {
                        // charge 不正确或者微信公众账号支付失败时会在此处返回
                    } else if (result == "cancel") {
                        // 微信公众账号支付取消支付
                    }
                });
            },
            error: function () {
                alert("错误")
            }
        });
    });


    /**
     * 支付 - ctc - 验证
     */
    $("#payCtcSub").click(function () {
        $.ajax({
            type: "post",
            url: "/pay/paymentCtc",
            data: {key: orderCtcUu, type: $(".payment-type li.active").attr("data-val")},
            dataType: "json",
            success: function (data) {
                pingpp.createPayment(data.data, function (result, err) {
                    if (result == "success") {
                        // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的支付结果都会跳转到 extra 中对应的 URL。
                    } else if (result == "fail") {
                        // charge 不正确或者微信公众账号支付失败时会在此处返回
                    } else if (result == "cancel") {
                        // 微信公众账号支付取消支付
                    }
                });
            },
            error: function () {
                alert("错误")
            }
        });
    });


    // 新增收货地址
    $(".settlement-page .section .title a.add-address").click(function () {
        $(".add-consignee").fadeIn(300);
        $(".add-consignee .add-consignee-c").css({"-webkit-transform": "scale(1)", "transform": "scale(1)"});
    });

    $(".add-consignee .add-consignee-c .title span").click(function () {
        $(".add-consignee .add-consignee-c").css({"-webkit-transform": "scale(0)", "transform": "scale(0)"});
        $(".add-consignee").fadeOut(300);
    });

    // 支付方式选择
    $(".cashier-page .payment-type ul li").click(function () {
        $(this).addClass("active").siblings().removeClass("active");
    });

    // 新增发票单位
    $(".settlement-page .invoice a").click(function () {
        $(".add-invoice").fadeIn(300);
        $(".add-invoice .add-invoice-c").css({"-webkit-transform": "scale(1)", "transform": "scale(1)"});
    });

    $(".add-invoice .add-invoice-c .title span").click(function () {
        $(".add-invoice .add-invoice-c").css({"-webkit-transform": "scale(0)", "transform": "scale(0)"});
        $(".add-invoice").fadeOut(300);
    });


    $(".add-invoice .add-invoice-c .content ul li > span").click(function () {
        var ddLength = $(".add-invoice-c .content ul li:first-child dl dd").length;
        if (ddLength < 5) {
            $(".add-invoice-c .content ul li:first-child dl").append('<dd><input type="text" value="" placeholder="新增单位发票抬头" /><span class="save">保存</span></dd>');
        }
    });

    //保存
    $(".add-invoice-c .content ul li:first-child dl").on("click", "dd span.save", function () {
        var val = $(this).parents("dd").find("input").val();
        if (val != "") {
            $(".add-invoice-c .content ul li:first-child dl dd").removeClass("active");
            $(this).parents("dd").addClass("active").html('<input type="text" value="' + val + '" disabled="disabled" /><span class="del">删除</span><span class="edit">编辑</span>');
        }
    });

    //删除
    $(".add-invoice-c .content ul li:first-child dl").on("click", "dd span.del", function () {
        $(this).parents("dl").find("dd").eq(0).addClass("active");
        $(this).parents("dd").remove();
    });

    //编辑
    $(".add-invoice-c .content ul li:first-child dl").on("click", "dd span.edit", function () {
        var val = $(this).parents("dd").find("input").val();
        $(".add-invoice-c .content ul li:first-child dl dd").removeClass("active");
        $(this).parents("dd").html('<input type="text" value="' + val + '" /><span class="save">保存</span>');
    });

    $(".add-invoice-c .content ul li dl").on("click", "dd", function () {
        $(this).addClass("active").siblings().removeClass("active");
    });


    $(".add-invoice-c .content ul li .submit").click(function () {
        var fir_index = $(".add-invoice-c .content ul li:first-child dl dd.active").index();
        var sec_index = $(".add-invoice-c .content ul li:first-child + li dl dd.active").index();
        var val = $(".add-invoice-c .content ul li:first-child dl dd.active input").val();
        if (sec_index == 0) {
            // $(".bond-page .order-info dl dd").eq(4).find("span").html("不开发票");
            $(".settlement-page .invoice").attr("data-val", 0);
            $(".content.invoice .text").html("不开发票");
            invoice["type"] = 1;
        } else {
            if (fir_index == 0) {
                $(".settlement-page .invoice .text").html("普通发票（纸质）&nbsp;&nbsp;个人&nbsp;&nbsp;明细");
                $(".settlement-page .invoice").attr("data-val", 1);
                invoice["type"] = 3;
            } else {
                $(".settlement-page .invoice .text").html("普通发票（纸质）&nbsp;&nbsp;" + val + "&nbsp;&nbsp;明细");
                $(".settlement-page .invoice").attr("data-val", 2);
                invoice["type"] = 2;
                invoice["no"] = val;
            }
        }
        $(".add-invoice .add-invoice-c").css({"-webkit-transform": "scale(0)", "transform": "scale(0)"});
        $(".add-invoice").fadeOut(300);
    });

});

/**
 * 添加收货地址
 */
function storeMemberAddress() {
    formValue["_method"] = "POST";
    $.ajax(ajaxSettings({
        url: '/api/v1/get/member/address/storePayPage',
        type: 'post',
        data: formValue,
        success: function (data) {

            var html = "<li data-val='" + data.data + "'><i></i>";
            html += "<span>" + $("#formStoreAddress input[name='ad_linkman']").val() + "</span>";
            html += "<span>" + $("#formStoreAddress input[name='ad_pca']").val() + "</span>";
            html += "<span>" + $("#formStoreAddress input[name='ad_linktel']").val() + "</span>";
            html += "</li>";
            $(".settlement-page .section .buyer ul li").siblings().removeClass("active");
            $(".settlement-page .section .buyer ul").append(html);

            $(".add-consignee .add-consignee-c").css({"-webkit-transform": "scale(0)", "transform": "scale(0)"});
            $(".add-consignee").fadeOut(300);
        },
        error: function (data) {
            $.alert({info: "添加地址失败！"});
        }
    }));
}

// 计算总金额
function computeTotalAmount() {
    var money = 0;
    $(".g_price").each(function (k, v) {
        money += parseFloat($(this).attr("data-val"))
    });
    $(".total-price strong").html("￥" + toDecimal2(money));
}

var ajaxSettings = function (opt) {
    var url = opt.url;
    // var href = opt.href;
    // 判断是否跨域请求
    var requestType = 'json';
    /*
     if (url.indexOf(location.host) > -1)
     requestType = 'json';
     requestType = opt.dataType || requestType;
     */
    // 是否异步请求
    var async = (opt.async === undefined ? true : opt.async);
    return {
        url: url,
        async: async,
        type: opt.type || 'get',
        dataType: requestType,
        cache: false,
        data: opt.data,
        success: function (data, textStatus, xhr) {
            if ((requestType === 'json' || requestType === "jsonp") && typeof(data) === "string") {
                data = JSON.parse(data);
            }
            if (data.success) {
                opt.success(data);
                return;
            }
            if (opt.error) {
                opt.error(data);
            }
        },
        error: function (xhr, status, handler) {
            if (opt.error)
                opt.error();
        }
    };
};

//保留两位小数 - 不够补 00
function toDecimal2(x) {
    var f = parseFloat(x);
    if (isNaN(f)) {
        return false;
    }
    var f = Math.round(x * 100) / 100;
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