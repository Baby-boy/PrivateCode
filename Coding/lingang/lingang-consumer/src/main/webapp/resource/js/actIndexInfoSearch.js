var actListsApp = angular.module("actListsApp", ['myservices']);
actListsApp.controller("actListsCtrl", function($scope,myservices,$http) {
		//初始化年龄
		$scope.ages = [];
		for(var i=0; i < 80; i++){
			$scope.ages[i] = i+1;
		}
		
    	$scope.sort = 0;
    	$scope.lingangToken = $('#lingangToken').val();
    	$scope.age = $('#age').val();
    	$scope.pageIndex = 1;
    	//获取活动列表
    	$scope.getActInfos = function(type){
    		if(type==2){
    			$scope.pageIndex =$scope.pageIndex*1.0 + 1;
    		}else{
    			$scope.pageIndex =1;
    		}
    		$http({
    			url:myservices.url+'/lingang-consumer/getActInfosPc.do',
    			params:{
    				"pageSize":5,
    				"pageIndex":$scope.pageIndex,
//    				"subjectId":$scope.subject,
//    				"actname":$scope.actname,
    				"releaseStatus":2,
    				"regionId":1,
    				"age":$scope.age,
//    				"sort":$scope.sort,
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
					"regionId":1,
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

actListsApp.filter("bmstatusFilter",function(){
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
//actListsApp.filter("bmstatusFilter",function(){
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