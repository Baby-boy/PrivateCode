define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,topStationService){
//    	if($routeParams.value==null || $routeParams.value==""){
//    		alert("信息异常..");
//    	}
    	
    	// 加载所有地区
    	$scope.regionList={};
    	topStationService.getRegionAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.regionList=data.data;
			}
		});
    	
    	var ue = UM.getEditor('stationContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	$scope.station={
    			stationTitle:"",
    			stationSimple:"",
    			regNumber:"",
    			creditCode:"",
				stationCompany:"",
				isFictitious:"",
				stationState:2,
				lossState:"",
				stationContent:"",
				imgId:"",
				regionId:""
    	};
    	
    	
    	//缩略图
	  	$scope.imgSrcMin = "";
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
	  				objectId:"",
	  				objectNo:"",
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
	  	
    	
    	ue.setContent($scope.station.stationContent);
    	//添加
    	$scope.addTopStationDetails=function(){
    		$scope.para={
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
    		topStationService.addTopStationDetails($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				location.href="#/home/topStation/topStationPageList/index";
    			}else{
    				alert("请求失败，请稍后再试");
    				location.href="#//home/topStation/topStationPageList/index";
    			}
    		});
    	};
    	
    	
    };
    return ["topStationAdd", _controller];
});