var actInfoDetailApp = angular.module("actInfoDetailApp", ['myservices']);
actInfoDetailApp.controller("actInfoDetailCtrl", function($scope,myservices,$http,$filter) {
		var actId = $('#actId').val();//活动id
		var lingangToken = $('#lingangToken').val();//用户id
		var storage = window.sessionStorage;
		var $dialog = $('.weui_dialog_alert');
		//获取活动详情
		$scope.getActInfoDetail = function(){
			
			$http({
				url:myservices.url+'/lingang-consumer/getActInfo.do',
				params:{
					"actId":actId,
					"lingangToken":lingangToken,
				},
				method:'POST'
			}).success(function(data,header,config,status){
				$('body').show();
				console.log(data);
				if(data.ok){
					 $scope.actinfo = data.data;
					 storage.setItem("actinfo", JSON.stringify($scope.actinfo));
					 $('.carousel').bind('touchstart',function(e){
							
							var startPos,endPos;
							var startTouch,endTouch;
							startTouch = e.originalEvent.changedTouches[0];
							startPos = {x:startTouch.clientX,y:startTouch.clientY}
							$('.carousel').bind('touchmove',function(e){
								endTouch = e.originalEvent.changedTouches[0];
								endPos = {x:endTouch.clientX,y:endTouch.clientY}
							});
							$('.carousel').bind('touchend',function(e){
								if(parseInt(startPos.x) > parseInt(endPos.x)){
									$('.carousel').carousel('next');
								}else{
									$('.carousel').carousel('prev');
								}
								$(this).unbind('touchmove');
								$(this).unbind('touchend');
							});
						});
//					 $('.carousel').carousel();
//					 $('.name,.next').click(function(){
//							var acDetai1 = $('.acDetai-frame');
//							var acDetai2 = $('.acDetai-frame2');
//							if(acDetai1.is(':hidden')){
//								acDetai1.show();
//								acDetai2.hide();
//							}else{
//								acDetai2.show();
//								acDetai1.hide();
//							}
//						});
				}else{
					$scope.dialogBody=data.message;
	    			$dialog.show();
				}
			}).error(function(data,header,config,status){
				console.log("系统繁忙，请稍后再试");
			});
		};
		//获取活动详情
		$scope.getActInfoDetail();
		//收藏
		$scope.collectAct = function(collectId){
			$http({
				url:myservices.url+'/lingang-consumer/changeCollect.do',
				params:{
					"objectId":actId,
					"lingangToken":lingangToken,
					"collectId":collectId,
					"collectType":2,
				},
				method:'POST'
			}).success(function(data,header,config,status){
				console.log(data);
				if(data.ok){
					$scope.getActInfoDetail();
//					if(collectId != null && collectId != 0){//已收藏
//						$('.sc').find('sc-btn2').addClass('ng-hidden');
//						$('.sc').find('sc-btn').removeClass('ng-hidden');
//					}else{//未收藏
//						$('.sc').find('sc-btn').addClass('ng-hidden');
//						$('.sc').find('sc-btn2').removeClass('ng-hidden');
//					}
//					alert("操作成功");
				}else{
					$scope.dialogBody=data.message;
	    			$dialog.show();
				}
			}).error(function(data,header,config,status){
				console.log("系统繁忙，请稍后再试");
			});
		};
		//报名活动
		$scope.enrollAct = function(){
			var today = new Date();
			if($scope.actinfo.enrollEtime < today.getTime()){
//				alert("活动已截止，不能报名");
				$scope.dialogBody="活动报名已截止，不能报名";
    			$dialog.show();
				return;
			}else if($scope.actinfo.enrollStime > today.getTime()){
//				alert("活动未开始报名，不能报名");
				$scope.dialogBody="活动报名未开始报名，不能报名";
    			$dialog.show();
				return;
			}else if($scope.actinfo.limitPeos != null && $scope.actinfo.limitPeos <= $scope.actinfo.peopleNum ){
//				alert("活动已报满，不能报名");
				$scope.dialogBody="活动已报满，不能报名";
    			$dialog.show();
				return;
			}
			window.location = 'wechat/addActOrder.jspx?actId='+actId+'&lingangToken='+lingangToken;
		};
    	
});

actInfoDetailApp.filter("bmstatusFilter",function(){
	return function(enrollEtime,enrollStime,actStime,limitPeos,peopleNum){
		var today = new Date();
		var out = "报名中";
		
		if(today.getTime() < enrollStime){
			out = "未开始";
		}
		if(enrollEtime >= today.getTime() && today.getTime() >= enrollStime){
			out = "报名中";
		}
		if(enrollEtime < today.getTime() ){
			out = "已截止";
		}
		if(actStime < today.getTime() ){
			out = "已发团";
		}
		if(limitPeos != null && limitPeos <= peopleNum ){
			out = "已报满";
		}
		return out;
	};
});
actInfoDetailApp.filter('to_trusted', ['$sce', function ($sce) {  
	        return function (text) {  
	            return $sce.trustAsHtml(text);  
	        };  
	    }]  
	);