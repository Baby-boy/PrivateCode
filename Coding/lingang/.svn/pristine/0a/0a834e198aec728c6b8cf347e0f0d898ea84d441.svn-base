define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,topStationService){
    	if($routeParams.value==null || $routeParams.value==""){
    		alert("信息异常..");
    	}
    	var ue = UM.getEditor('stationContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	
    	// 加载所有地区(用于产业产业园区的关联)
    	$scope.regionList={};
    	topStationService.getRegionAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.regionList=data.data;
			}
		});
    	
    	//获取园区详情
    	$scope.station={};
    	$scope.topStationDetails=function(){
    		$scope.para={
    				stationId:$routeParams.value
    		};
    		topStationService.topStationDetails($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.station=data.data;
    				ue.setContent($scope.station.stationContent);
    				$scope.imgSrcMin = data.data.imgPath;
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	
    	//缩略图
        $scope.previewImageMin = function(){
    		$scope.imgSrcMin = window.imgUrl;
  		};
		$scope.delPreImgMin = function(){
			$scope.imgSrcMin = "";
		};
    	//上传
	      $scope.uploadImageMin = function(file){
				if(!$scope.imgSrcMin){
					layer.msg("未选择图片", 2, 2);
					return;
				}
	  		var url = "fileUpload/imageUpload.do";
	  		var data = {
	  				objectId:$scope.station.stationId,
	  				objectNo:0,
	  				objectType:4
	  		};
	  		$.ajaxFileUpload({
						url:url,
						secureuri:false,
						fileElementId:file,
						dataType:'json',
						data:data,
						success:function(data){
//							console.log(data);
//							if(data.stateCode == "00"){ alert(data.message);
//			  	        		mySession.logout();
//			  	        		window.location=serviceBase.url+"login.html";
//			  	        		return;
//			   				}
							if(data.stateCode="1000"){
								$scope.$apply(function(){
									$scope.station.imgId=data.data.imgId
//									$scope.park.imgPath=data.data.imgPath;
//									$scope.imgSrc = "";
								});
								layer.msg('上传成功', 2, 1);
							}
						},
						error:function(data){
							layer.msg('系统繁忙，请稍后再试!', 2, 2);
						}
				});
	  	};
	  	
    	
    	//修改园区基本信息
    	$scope.updateTopStationDetails=function(){
    		$scope.para={
    				stationId:$scope.station.stationId,
    				stationTitle:$scope.station.stationTitle,
    				stationSimple:$scope.station.stationSimple,
    				regNumber:$scope.station.regNumber,
    				creditCode:$scope.station.creditCode,
    				stationCompany:$scope.station.stationCompany,
    				isFictitious:$scope.station.isFictitious,
    				stationState:$scope.station.stationState,
    				lossState:$scope.station.lossState,
    				stationContent:ue.getContent(),
    				imgId:$scope.station.imgId,
    				regionId:$scope.station.regionId
    		
    		};
    		topStationService.updateTopStationDetails($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("修改成功");
    				location.href="#/home/topStation/topStationPageList/update";
    			}else{
    				alert("请求失败，请稍后再试");
    				location.href="#/home/topStation/topStationPageList/update";
    			}
    		});
    	};
    	
    	
    	
    	$scope.topStationDetails();
    };
    return ["topStationDetails", _controller];
});