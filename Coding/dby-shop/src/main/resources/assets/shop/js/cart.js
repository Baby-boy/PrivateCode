var cart_json = {};
$(function(){
	
	// 购物车单选
	$(".cart-page .have-product").on("click"," ul.cart-list li.choice span",function(){
		if($(this).hasClass("active")){
            $(this).removeClass("active");
            $(this).next("input[type='checkbox']").prop("checked",false);
        }else{
            $(this).addClass("active");
            $(this).next("input[type='checkbox']").prop("checked",true);
        }
        total();
	});
	
	// 店铺勾选
	$(".cart-page .have-product").on("click",".store-name .checkbox",function(){
		if($(this).hasClass("active")){
            $(this).removeClass("active");
            $(this).next("input[type='checkbox']").prop("checked",false);
            $(this).parent().next(".store-goods").find("li.choice span").removeClass("active");
        }else{
            $(this).addClass("active");
            $(this).next("input[type='checkbox']").prop("checked",true);
            $(this).parent().next(".store-goods").find("li.choice span").addClass("active");
        }
        total();
	});
	
	
	// 购物车全选
	$(".cart-page .have-product").on("click"," ul.top-title li.choice span",function(){
		if($(this).hasClass("active")){
            $(this).removeClass("active");
            $(".cart-store .store-name .checkbox").removeClass("active");
            $("ul.cart-list li.choice span").removeClass("active");
            $("ul.cart-list li.choice input[type='checkbox']").prop("checked",false);
        }else{
            $(this).addClass("active");
            $(".cart-store .store-name .checkbox").addClass("active");
            $("ul.cart-list li.choice span").addClass("active");
            $("ul.cart-list li.choice input[type='checkbox']").prop("checked",true);
        }
        total();
	});
	
	//鼠标移上数量
	$(document).on("mouseenter","ul.cart-list li.number",function(){
		$(this).find(".num-control").addClass("num-control-active");
	});
	$(document).on("mouseleave","ul.cart-list li.number",function(){
		$(this).find(".num-control").removeClass("num-control-active");
	});
	
	//我的购物车，数量加
    $(document).on("click","ul.cart-list li.number span.plus",function(){
    	var num = parseInt( $(this).prev("input").val() );
        num++;
        // total();
        cart_json = {
    		cart_id : $(this).parents("ul").attr("data-id"),
       		depot_id : $(this).parents("ul").attr("data-depot-id"),
			cart_num : num
        };
        cart_update($(this));
	});

    //我的购物车，数量减
    $(document).on("click","ul.cart-list li.number span.reduce",function(){
    	var num = parseInt( $(this).next("input").val() );
    	if ( num == 1 ) {
    		return;
    	}
        if (num > 1) {
            num--;
        }
        /*var parice = $(this).parents('ul').find(".price span").html();
        var subtotal = parseFloat(parice)*num;
        $(this).parents('ul').find(".subtotal span").html(subtotal);
       	$(this).next("input").val(num);
       	total();*/
       	cart_json = {
       		cart_id : $(this).parents("ul").attr("data-id"),
       		depot_id : $(this).parents("ul").attr("data-depot-id"),
			cart_num : num
        };
        cart_update($(this));
	});
    
    //我的购物车，输入框输入
    $(document).on("keyup","ul.cart-list li.number input",function(){
    	if(this.value.length==1){this.value=this.value.replace(/[^1-9]/g,'')}else{this.value=this.value.replace(/\D/g,'')}
        var price = parseFloat($(this).parents(".number").prev(".price").children("span").html());
        var subtotal = $(this).parents(".number").next(".subtotal").children(".subtotal span");
        var num = parseInt($(this).val());
        if($(this).val()==""){
            $(this).val(1);
        }else{
            subtotal.html(num*price);
        }
        total();
        var c_json = {
    		depot_id : $(this).parents("ul").attr("data-id"),
			cart_num : num
        };
        cart_update(c_json);
	});
    
    
    // 计算总价格
    function total(){
	    var $box = $("ul.cart-list li.choice span.active");
	    var priceList = [];
	    var total = 0;
	    var number = 0;
	    var totalNum = 0 ;
	    $box.each(function( index , value ){
	        var price = $box.parents("ul.cart-list").eq( index ).children(".price").children("span").html();
	        var num = $box.parents("ul.cart-list").eq( index ).children(".number").find("input").val();
	        priceList[index] = price+","+num;
	    });
	    $.each( priceList ,function( index ,value ){
	       var price_num = value.split(","); 
	       total += parseFloat( parseFloat(price_num[0]) * parseInt( price_num[1]) ) ;
	       number += parseInt( price_num[1]);
	    });
	    
	    $("ul.cart-list li.number input").each(function( index , value ){
	    	totalNum += parseInt( $(this).val() ); 
	    });
	    $(".go-pay .info span.total-pieces").html( totalNum );
	    $(".go-pay .info span.selected").html( number );
	    $(".go-pay .right-pay .total-price strong").html(total.toFixed(2));
	}
    
    // 购物车商品选择
    $(document).on("click","ul.cart-list li.handle span",function(){
    	var $this = $(this);
    	$.confirm({
			text:"是否确认删除该商品？",
			intro:"删除后不可找回",
			callback:function(){
    	        cart_delete($this);
			}
		});
    });
    
    
    total();
    
    // 促销优惠点击
    $("ul.cart-list li.price .promotion p").click(function(){
    	$(this).parent().toggleClass("active-promotion");
    });
    
    $("ul.cart-list li.price .promotion .content .btn-wrap input").click(function(){
    	$(this).parents(".promotion").removeClass("active-promotion");
    });
    
    
    // 点击结算
    $("#btnCartBalance").click(function() {
    	var val = $(".info .selected").html();
    	if ( parseInt( val ) > 0 ) {
    		var ids = [];
    		var input = $(".span-checkbox.active");
    		$.each(input ,function(k, v) {
    			ids.push( $(this).attr("data-id") );
    		});
    		ids = ids.join(",");
    		$.ajax(ajaxSettings({
    		    url: '/pay/generate',
    		    type: 'post',
    		    data: {ids: ids, type: "cart"},
    		    success: function(data) {
    		    	if (data.success) {
    		    		window.location.href = "/pay/order_confirm?spm=" + data.data;
    		    	} else {
    		    		errorShopAlert(data);
    		    	}
    		    },
    		    error: function(data) {
    		    	errorShopAlert(data);
    		    }
    		}));
    		
    	} else {
    		$.alert({
    			type: 3,
    			info: "请选择要购买的商品"
    		});
    	}
    });
});

