var addActOrderApp = angular.module("addActOrderApp", ['myservices']);
addActOrderApp.controller("addActOrderCtrl", function($scope,myservices,$http,$filter) {
		var $dialog = $('.weui_dialog_alert');
		var actId = $('#actId').val();//活动id
		var lingangToken = $('#lingangToken').val();//用户id
		var storage = window.sessionStorage;
		//活动信息
		$scope.actinfo = JSON.parse(storage.getItem("actinfo"));
		//选中的参与人
		$scope.selectedactors = [];
		//获取常用联系人
		$scope.getCommonContacts11 = function(){
			$http({
				url:myservices.url+'/lingang-consumer/getCommonContacts.do',
				params:{
					"contactsType":1,//1=常用联系人 2=常用参与者 3=常用收件地址
					"lingangToken":lingangToken,
//					"pageSize":1,
//					"pageIndex":1,
				},
				method:'POST'
			}).success(function(data,header,config,status){
				console.log(data);
				if(data.ok){
					 $scope.linkUser = data.data.list[0];
					 $scope.linktel=$scope.linkUser.contactsTel;
					$scope.linkuser=$scope.linkUser.contactsName;
				}else{
					console.log(data.message);
				}
			}).error(function(data,header,config,status){
				console.log("系统繁忙，请稍后再试");
			});
		};
		$scope.getCommonContacts1 = function(){
			$('.dialog-fade').show();
			$('.dialog-person').show(500);
			$http({
				url:myservices.url+'/lingang-consumer/getCommonContacts.do',
				params:{
					"contactsType":1,//1=常用联系人 2=常用参与者 3=常用收件地址
					"lingangToken":lingangToken,
				},
				method:'POST'
			}).success(function(data,header,config,status){
				console.log(data);
				if(data.ok){
					$scope.linkusers = data.data.list;
				}else{
					console.log(data.message);
				}
			}).error(function(data,header,config,status){
				console.log("系统繁忙，请稍后再试");
			});
		};
		//获取常用参与者
		$scope.getCommonContacts2 = function(){
			
			$http({
				url:myservices.url+'/lingang-consumer/getCommonContacts.do',
				params:{
					"contactsType":2,//1=常用联系人 2=常用参与者 3=常用收件地址
					"lingangToken":lingangToken,
				},
				method:'POST'
			}).success(function(data,header,config,status){
				console.log(data);
				if(data.ok){
					$scope.actors = data.data.list;
				}else{
					console.log(data.message);
				}
			}).error(function(data,header,config,status){
				console.log("系统繁忙，请稍后再试");
			});
		};
		//获取优惠券
		$scope.getCoupons = function(){
			$('.dialog-fade').show();
			$('.dialog-yh').show(500);
			$http({
				url:myservices.url+'/lingang-consumer/getCouponsInAct.do',
				params:{
					"useScope":1,//适用范围(0.不限1.活动消费2书社消费)
					"lingangToken":lingangToken,
					"pageSize":100,
					"pageIndex":1,
				},
				method:'POST'
			}).success(function(data,header,config,status){
				console.log(data);
				if(data.ok){
					$scope.coupons = data.data.list;
				}else{
					$scope.coupons = "";
					console.log(data.message);
				}
			}).error(function(data,header,config,status){
				console.log("系统繁忙，请稍后再试");
			});
		};
		$scope.getCommonContacts11();
		$scope.getCommonContacts2();
//		$scope.getCoupons();
		$scope.discount =0.00;//折扣
    	$scope.sum =0.00;//总金额
    	$scope.initialsum =0.00;//总金额
    	$scope.selactor = 0;//是否选人
		//选择联系人
		window.linkuserCheck = function(t,c){
			$('.dialog-fade').hide();
			$('.dialog-person').hide(500);
			var check = $('.check-i.person.check');
			if(check.length<1){return;}
			var name = check.parents('.row').find('.name').text();
			var card = check.parents('.row').find('.card').text();
			$('.s-name').text(name);
			$('.phonenum').text(card);
//			$scope.useraddr=$(t).find('.useraddr').html();
			$scope.linktel=$('.phonenum').text();
			$scope.linkuser=$('.s-name').text();
//    		$('.address-list').find('.sub-div').removeClass('active');
//    		$(t).addClass('active');
//    		var checkI = $(t).find('.check-i');
//    		cgCheck(checkI,c);
//    		$('.dialog-fade').hide();
//    		$('.dialog-person').hide(500);
    	};
    	//选择参与者
    	$scope.actorCheck = function(th,t,c){
    		cgCheck2(t,c);
    	};
    	function itemChange(t){
    		var _this = $(t);
    		var checkbox = _this.find('input[type="checkbox"]');
    		var isCheck = checkbox.prop('checked');
    		if(isCheck == true){
    			_this.removeClass('check');
    			checkbox.prop('checked',false);
    			$('.checkAll').removeClass('check');
    			$('.checkAll').find('input[type="checkbox"]').prop('checked',false);
    		}else{
    			_this.addClass('check');
    			checkbox.prop('checked',true);
    		}
    	}
    	function cgCheck(t,c,n){
    		var check = $(t).find('input[type="checkbox"]');
    		if(check.prop('checked')){
    			$(t).removeClass('check');
    			check.prop('checked',false);
    		}else{
    			if(n){
    				$('.check-i.'+n).removeClass('check');
    				$('.check-i.'+n).find('input[type="checkbox"]').prop('checked',false);
    				_this.addClass('check');
    				checkbox.prop('checked',true);
    				return;
    			}
				$('.check-i').find('input[type="checkbox"]').prop('checked',false);
				$('.check-i').removeClass('check');
    			$(t).addClass('check');
    			check.prop('checked',true);
    		}
    	};
    	function cgCheck2(t,c){
    		$scope.selectedactors = [];
    		$scope.sum = 0;
			$scope.initialsum = 0;
			var checks =  $('.cyz').eq(t).find('input[type="checkbox"]');
			var checki =  $('.cyz').eq(t).find('i');
			if(checks.prop('checked')){
				checki.removeClass('check');
				checks.prop('checked',false);
			}else{
				checki.addClass('check');
    			checks.prop('checked',true);
			}
    		for(var i =0;i<$('.cyz').length;i++){
    			var check = $('.cyz').eq(i).find('input[type="checkbox"]');
    			var cc = $scope.actors[i];
    			if(check.prop('checked')){
    				var jine = jisuanamount(cc.contactsIdNo,cc.contactsHeight);
    				$scope.selactor += 1;
    				$scope.selectedactors.push(cc);
    				$scope.sum += jine;
        			$scope.initialsum += jine;
    			}
    		}
    		if($scope.discount>0){
				$scope.sum = $scope.initialsum-$scope.discount;
			}
//    		return;
//    		if(check.prop('checked')){
//    			$scope.selactor -= 1;
//    			$(t).removeClass('check');
//    			check.prop('checked',false);
//    			var jine = jisuanamount(c.contactsIdNo,c.contactsHeight);
//    			$scope.sum -= jine;
//    			$scope.initialsum -= jine;
//    			$scope.selectedactors.splice(t, 1);
//    		}else{
//    			
//    			$scope.selactor += 1;
//    			$(t).addClass('check');
//    			check.prop('checked',true);
//    			$scope.selectedactors.push(c);
//    			var jine = jisuanamount(c.contactsIdNo,c.contactsHeight);
//    			$scope.sum += jine;
//    			$scope.initialsum += jine;
//    			
//    		}
//
    		if($scope.sum < 0){
    			$scope.sum = 0.00;
    		}
    	};
    	//$scope.youhuicache = 0.00;
    	
    	//选择优惠券
    	window.selectCoupon = function(){
    		$('.dialog-fade').hide();
    		$('.dialog-yh').hide(500);
    		var check = $('.check-i.yh.check');
    		if(check.length<1){return;}
    		var name = check.parents('.row').find('.yhCard').text();
    		$('.s-yhCard').text(name);
    		$scope.usedcoupon = name;
    		var usedcouponId = check.find('input[type="checkbox"]').val();
    		$scope.$apply(function(){
    			if(usedcouponId != null && usedcouponId != '' && usedcouponId != undefined){
    				$scope.couponobj = $scope.coupons[usedcouponId].id;
        			if($scope.discount == 0){
            			
        				$scope.discount = $scope.coupons[usedcouponId].coupon.couponAmount;
                		$scope.sum -= $scope.discount;
        			
    	    		}else{
    	    			if($scope.selactor > 0){
    		    			$scope.discount = $scope.coupons[usedcouponId].coupon.couponAmount;
    		        		$scope.sum =$scope.initialsum - $scope.discount;
    	    			}else{
    	    				$scope.discount = $scope.coupons[usedcouponId].coupon.couponAmount;
    	    				$scope.sum -= $scope.discount;
    	    			}
    	    			
    	    		}
        		}else{
        			$scope.discount = 0;
        			$scope.sum =$scope.initialsum;
        		}
        		
        		if($scope.sum < 0){
        			$scope.sum = 0.00;
        		}
			});
    		
    	};
    	function jisuanamount (UUserCard,Uheight){
    		var storage = window.sessionStorage;
    		//活动信息
    		var actinfo = JSON.parse(storage.getItem("actinfo"));
    		var out = "";
    		var myDate = new Date();
            var month = myDate.getMonth() + 1;
            var day = myDate.getDate();
            var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
            if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
                age++;
            }
            out =actinfo.actPrices[0].price;
    		/*if(actinfo.actPrices[0].type == 1){//统一价格
    			out = actinfo.actPrices[0];
    		}else */
    		if(actinfo.actPrices[0].type == 2){//价格不统一
    			if(actinfo.actPrices[1].heightthan != 0){
    				if(age <= actinfo.actPrices[1].youngerthan && Uheight <= actinfo.actPrices[1].heightthan){
        				out =actinfo.actPrices[1].price;
        			}
    			}else{
    				if(age <= actinfo.actPrices[1].youngerthan){
        				out =actinfo.actPrices[1].price;
        			}
    			}
    			if(age > actinfo.actPrices[2].olderthan){
    				out =actinfo.actPrices[2].price;
    			}
    		}else{//基础价格
				out =actinfo.actPrices[0].price;
			}
            
    		return out;
    	};
    	
    	function jisuanamount2 (UUserCard,Uheight){
    		var storage = window.sessionStorage;
    		//活动信息
    		var actinfo = JSON.parse(storage.getItem("actinfo"));
    		var out = {};
    		var myDate = new Date();
    		var month = myDate.getMonth() + 1;
    		var day = myDate.getDate();
    		var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
    		if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
    			age++;
    		}
    		out =actinfo.actPrices[0].price;
    		/*if(actinfo.actPrices[0].type == 1){//统一价格
    			out = actinfo.actPrices[0];
    		}else */
    		if(actinfo.actPrices[0].type == 2){//价格不统一
    			if(actinfo.actPrices[1].heightthan != 0){
    				if(age <= actinfo.actPrices[1].youngerthan && Uheight <= actinfo.actPrices[1].heightthan){
        				out =actinfo.actPrices[1].price;
        			}
    			}else{
    				if(age <= actinfo.actPrices[1].youngerthan){
        				out =actinfo.actPrices[1].price;
        			}
    			}
    			if(age > actinfo.actPrices[2].olderthan){
    				out =actinfo.actPrices[2].price;
    			}
    		}else{//基础价格
				out =actinfo.actPrices[0].price;
			}
    		
    		return out;
    	};
    	$scope.addOrderbtn = function(){
    		if($scope.selectedactors.length <= 0){
//    			alert("请选择活动参与者");
    			$scope.dialogBody="请选择活动参与者";
    			$dialog.show();
    			return;
    		}
    		if($scope.linkuser == '' || $scope.linkuser == undefined){
//    			alert("请选择联系人");
    			$scope.dialogBody="请选择联系人";
    			$dialog.show();
    			return;
    		}
    		$('#loadingToast').show();
    		var actage = "";
    		if($scope.actinfo.limitAge == 1){//限定年龄
    			if($scope.actinfo.maxAge == null){
    				actage = $scope.actinfo.minAge+"岁以上";
    			}else{
    				actage = $scope.actinfo.minAge+"~"+$scope.actinfo.maxAge+"岁";
    			}
    			
    		}else{
    			actage = "混班";
    		}
    		var actOrderParticipants = [];
    		for(var i = 0; i < $scope.selectedactors.length; i++){
    			var selectedactor = $scope.selectedactors[i];
    			var priceobj = jisuanamount2(selectedactor.contactsIdNo,selectedactor.contactsHeight);
    			var actOrderParticipant={
    					contactsName : selectedactor.contactsName,
    					contactsIdType : selectedactor.contactsIdType,
    					contactsIdNo : selectedactor.contactsIdNo,
    					contactsId : selectedactor.contactsId,
    					contactsHeight : selectedactor.contactsHeight,
    					contactsSex : selectedactor.contactsSex,
    					perprice : priceobj.price,
    					actPriceId : priceobj.id,
    					actInfoId : $scope.actinfo.actId,
    			};
    			actOrderParticipants.push(actOrderParticipant);
    		}
    		var dateFilter = $filter('date');
    		var acttime="";
    		if($scope.actinfo.actEtime!=null){
    			acttime=dateFilter($scope.actinfo.actStime,"yyyy-MM-dd")+'-'+dateFilter($scope.actinfo.actEtime,"yyyy-MM-dd");
    		}else{
    			acttime=dateFilter($scope.actinfo.actStime,"yyyy-MM-dd");
    		}
    		var actOrder = {
    				vipId : $scope.selectedactors[0].vipId,
    				totalAmt : $scope.initialsum,
    				discountAmt : $scope.discount,
    				orderAmt : $scope.sum,
    				orderState : 2,
    				linkUser : $scope.linkuser,
    				linkTel : $scope.linktel,
    				couponListId : $scope.couponobj,
    				actimg : $scope.actinfo.resourceInfos[0].imgPath,
    				acttitle : $scope.actinfo.actName,
    				acttime : acttime,
    				actage : actage,
    				actprice : $scope.actinfo.actPrice,
    				actInfoId : $scope.actinfo.actId,
    				regionId : $scope.actinfo.regionId,
    				orderType : 1,//订单类型(1活动订单   2,借阅卡订单)
    				actOrderParticipants : actOrderParticipants,
    		};
    		var data = {
    				"actOrder":actOrder
    		};
//    		$http.post(
//				myservices.url+'/lingang-consumer/addActOrder.do',
//				data,
//				myservices.postConfig
    		var config = {
                    headers: {
                        'Content-Type': "application/json"
                    }
                };
//    		$http({
//				url:myservices.url+'/lingang-consumer/addActOrder.do',
//				params:{
//					"actOrder":actOrder
//				},
//				method:'POST',
    		$http.post(
				myservices.url+'/lingang-consumer/addActOrder.do',
				JSON.stringify(actOrder),
				config
			).success(function(data,header,config,status){
				console.log(data);
				if(data.ok){
//					 $scope.linkusers = data.data.list;
//					window.location = 'pay/pay.jspx?amount='+$scope.sum+"&channel=wx_pub";
					var actOrder = data.data;
					var vipInfo = data.dataMap.vipInfo;
					var dataobj={
	    	        		amount : actOrder.orderAmt,	
	    	        		channel : 'wx_pub',
	    	        		userId : actOrder.vipId,
	    	        		currency : 'cny',
	    	        		prodIds : actOrder.actInfoId,
	    	        		proType : '1',
	    	        		subject : actOrder.acttitle,
	    	        		body : actOrder.acttitle,
	    	        		order_no : actOrder.actOrderNo,
	    	        		openid : vipInfo.openId
	    				};
					$('#loadingToast').hide();
					wap_pay(dataobj);
				}else{
					console.log(data.message);
					$scope.dialogBody=data.message;
	    			$dialog.show();
				}
			}).error(function(data,header,config,status){
				console.log("系统繁忙，请稍后再试");
			});
    	};
    	//支付
//    	var data={
//        		amount : amount,	
//        		channel : channel,
//        		userId : 6,
//        		currency : 'cny',
//        		prodAttrIds : '1,2',
//        		subject : '测试商品',
//        		body : '测试商品测试商品测试商品',
//        		order_no : 'SORD'+now.getTime(),
//        		openid : 'oLCwVt78znkXE8mtDHjvzE-X1CDg'
//			};
    	function wap_pay(dataobj) {
    	        $.ajax({
    				url : 'pay/topayServlet.do',//还没写
    				type : 'POST',
    				cache : false,
    				data : dataobj,
    				dataType : 'json',
    				success : function(data) {
    					//alert("成功");
    					console.log(data);
    					if(dataobj.channel=='wx_pub'){
    						pingpp.createPayment(data.data, function(result, err){
    							  // 处理错误信息
    							 console.log(result);
    			                 console.log(err);
    							 if (result == "success") {
    						        // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的 wap 支付结果都是在 extra 中对应的 URL 跳转。
//    								 alert('支付成功');
    								 window.location = 'resource/wechat/paySuccess.jsp?actId='+actId;
    						    } else if (result == "fail") {
    						        // charge 不正确或者微信公众账号支付失败时会在此处返回
    						    	 //alert('支付失败');
    						    	 $scope.dialogBody='支付失败';
				    				 $dialog.show();
    						    	 
    						    } else if (result == "cancel") {
    						        // 微信公众账号支付取消支付
//    						    	 alert('取消支付');
    						    	 $scope.dialogBody='取消支付';
				    				 $dialog.show();
    						    }
    							
    						});
    					}else if(channel=='alipay_pc_direct'){
    						pingppPc.createPayment(data.data, function(result, err){
    							  // 处理错误信息
    							 console.log(result);
    			                 console.log(err);
    							
    						});
    					}else if(channel=='wx_pub_qr'){
    						var qr = data.data.credential.wx_pub_qr;
    						window.location = qr;
    					}
    					
    				},
    				error : function(data) {
    					console.log("服务繁忙，请稍候再试");
    				},
    				beforeSend : function() {
    				}
    			}); 
            
        }
    	
    	$scope.getActComment = function(){
    		window.location = "wechat/getCommonList.jspx?lingangToken=" + lingangToken +"&contactsType=" + 2;
    	};
});

addActOrderApp.filter("cardnoFilter",function(){
	return function(UUserCard,Uheight){
		var storage = window.sessionStorage;
		//活动信息
		var actinfo = JSON.parse(storage.getItem("actinfo"));
		var out = "";
		var myDate = new Date();
        var month = myDate.getMonth() + 1;
        var day = myDate.getDate();
        var age = myDate.getFullYear() - UUserCard.substring(6, 10) - 1;
        if (UUserCard.substring(10, 12) < month || UUserCard.substring(10, 12) == month && UUserCard.substring(12, 14) <= day) {
            age++;
        }
        out =actinfo.actPrices[0].price;
		/*if(actinfo.actPrices[0].type == 1){//统一价格
			out = actinfo.actPrices[0];
		}else */
		if(actinfo.actPrices[0].type == 2){//价格不统一
			if(actinfo.actPrices[1].heightthan != 0){
				if(age <= actinfo.actPrices[1].youngerthan && Uheight <= actinfo.actPrices[1].heightthan){
    				out =actinfo.actPrices[1].price;
    			}
			}else{
				if(age <= actinfo.actPrices[1].youngerthan){
    				out =actinfo.actPrices[1].price;
    			}
			}
			if(age > actinfo.actPrices[2].olderthan){
				out =actinfo.actPrices[2].price;
			}
		}else{//基础价格
			out =actinfo.actPrices[0].price;
		}
        
		return out;
	};
});