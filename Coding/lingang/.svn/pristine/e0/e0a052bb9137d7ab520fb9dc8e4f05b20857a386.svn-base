var actIndexInfoApp = angular.module("actIndexInfoApp", ['myservices']);
actIndexInfoApp.controller("actIndexInfoCtrl", function($scope,myservices,$http) {
		//初始化年龄
		$scope.ages = [];
		for(var i=0; i < 80; i++){
			$scope.ages[i] = i+1;
		}
		//获取轮播图
    	$http({
			url:myservices.url+'/lingang-consumer/getResources.do',
			params:{
				"objectType":3,
			},
			method:'POST'
		}).success(function(data,header,config,status){
			console.log(data);
			if(data.ok){
				 $scope.resources = data.data.list;
//				 $('.carousel').carousel();
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

			}else{
				console.log(data.message);
			}
		}).error(function(data,header,config,status){
			console.log("系统繁忙，请稍后再试");
		});
    	//获取系
    	$http({
    		url:myservices.url+'/lingang-consumer/getActSeriess.do',
    		method:'GET'
    	}).success(function(data,header,config,status){
    		console.log(data);
    		if(data.ok){
    			$scope.actSeries = data.data;
    		}else{
    			console.log(data.message);
    		}
    	}).error(function(data,header,config,status){
    		console.log("系统繁忙，请稍后再试");
    	});
    	var flag = false;
    	//改变系
    	$scope.changeSeries = function(seriesId){
    		$http({
    			url:myservices.url+'/lingang-consumer/getActSubjectBySeries.do',
    			params:{
    				"seriesId":$scope.series,
    				"releaseStatus":1,
    			},
    			method:'POST'
    		}).success(function(data,header,config,status){
    			console.log(data);
    			if(data.ok){
    				 $scope.actSubject = data.data;
    				 if(flag){
    					 $scope.subject=$scope.actSubject[0].subjectId;
    				 }
    				flag = true;
    			}else{
    				console.log(data.message);
    			}
    		}).error(function(data,header,config,status){
    			console.log("系统繁忙，请稍后再试");
    		});
    	};
    	//初始化系
    	$scope.series = $('#actser').val()*1.0;
    	$scope.changeSeries($scope.series);
    	//初始化科目
    	$scope.subject = $('#actsub').val()*1.0;
    	$scope.sort = 0;
    	$scope.lingangToken = $('#lingangToken').val();
    	$scope.pageIndex = 1;
    	//获取活动列表
    	$scope.getActInfos = function(type){
    		if(type==2){
    			$scope.pageIndex =$scope.pageIndex*1.0 + 1;
    		}else{
    			$scope.pageIndex =1;
    		}
    		$http({
    			url:myservices.url+'/lingang-consumer/getActInfos.do',
    			params:{
    				"pageSize":5,
    				"pageIndex":$scope.pageIndex,
    				"subjectId":$scope.subject,
    				"actname":$scope.actname,
    				"releaseStatus":2,
    				"regionId":$scope.regionId,
    				"age":$scope.age,
    				"sort":$scope.sort,
    				"lingangToken":$scope.lingangToken,
    			},
    			method:'POST'
    		}).success(function(data,header,config,status){
    			$('body').show();
    			console.log(data);
    			if(data.ok){
    				if(!$scope.actInfos){
    					$scope.actInfos = data.data.list;
    				}else{
    					if(type==2){
    						for(var i = 0; i< data.data.list.length; i++){
        						$scope.actInfos.push(data.data.list[i]);
        					}
    					}else{
    						$scope.actInfos = data.data.list;
    					}
    					
    				}
    				
    			}else{
    				console.log(data.message);
    				$('.nextPage').text(data.message);
    			}
    		}).error(function(data,header,config,status){
    			console.log("系统繁忙，请稍后再试");
    		});
    	};
    	$scope.today = new Date();
    	$scope.getActInfos();
    	$scope.wxjumpActDetail = function(url){
    		if(url != null && url != undefined && url != ''){
    			window.location = 'wechat/actInfoDetail.jspx?actId='+url+'&lingangToken='+$scope.lingangToken;
    		}else{
    			alert("参数为空");
    		}
    		
    	};
    	//加载更多
    	$scope.nextPage = function(){
    		$scope.getActInfos(2);
    	};
    	
  	//获取活动信息list
	   $scope.getActInfosPc = function(){
		   $http({
			   url:'getActInfosPc.do',
				params:{
					"pageSize":$scope.pageSize,
					"pageIndex":$scope.pageIndex,
					"subjectId":$scope.subjectId,
					"actname":$scope.actName,
					"releaseStatus":$scope.releaseStatus,
					"regionId":$scope.regionId,
					"age":$scope.age,
					"sort":$scope.sort,
					"lingangToken":$scope.lingangToken,
					"actTime":$scope.actTime,
					"seriesId":$scope.seriesId,
				},
   			method:'POST'
   		}).success(function(data,header,config,status){
   			$('body').show();
   			console.log(data);
   			if(data.ok){
 				$scope.acts=data.data.list;
 			    $scope.countPage = data.data.countPage;
 			    $scope.currentPage = data.data.currentPage;
 			 }else{
 			    $scope.acts={};
 				$scope.countPage = 0;
 			    $scope.currentPage = 0;
 			}
   		}).error(function(data,header,config,status){
   			console.log("系统繁忙，请稍后再试");
   		});
	   };
	   //
});

actIndexInfoApp.filter("bmstatusFilter",function(){
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
//actIndexInfoApp.filter("bmstatusFilter",function(){
//	return function(enrollEtime,enrollStime){
//		var today = new Date();
//		var out = "resource/img/acttag1.png";
//		if(today.getTime() < enrollStime){
//			out = "resource/img/tag3.png";
//		}
//		if(enrollEtime >= today.getTime() && today.getTime() >= enrollStime){
//			out = "resource/img/acttag1.png";
//		}
//		if(enrollEtime < today.getTime() ){
//			out = "resource/img/acttag2.png";
//		}
//		
//		return out;
//	};
//});