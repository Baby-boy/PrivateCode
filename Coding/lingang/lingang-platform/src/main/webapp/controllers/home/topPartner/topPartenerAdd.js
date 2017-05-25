define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,topPartnerService){
//    	if($routeParams.value==null || $routeParams.value==""){
//    		alert("信息异常..");
//    	}
    	
    	// 加载所有合作层级
    	$scope.basicsList={};
    	topPartnerService.getTopPartnerBasicsAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.basicsList=data.data;
			}
		});
    	
    	// 加载所有合作类型
    	$scope.typeList={};
    	topPartnerService.getTopPartnerTypeAll($http,$scope.para).then(function(data) {
    		if(data.stateCode=="1000"){
    			$scope.typeList=data.data;
    		}
    	});
    	
    	var ue = UM.getEditor('partnerContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	$scope.partner={
				partnerName:"",
				partnerCompany:"",
				partnerSimple:"",
				partnerState:2,
				partnerContent:"",
				logoImgId:"",
				imgId:"",
				basicsId:"",
				typeId:""
    	};
    	
    	//logo
    	$scope.imgSrcMax = "";
        $scope.previewImageMax = function(){
    		$scope.imgSrcMax = window.imgUrl;
  		};
		$scope.delPreImgMax = function(){
			$scope.imgSrcMax = "";
		};
    	//上传
	      $scope.uploadImageMax = function(file){
				if(!$scope.imgSrcMax){
					layer.msg("未选择图片", 2, 2);
					return;
				}
	  		var url = "fileUpload/imageUpload.do";
	  		var data = {
	  				objectId:"",
	  				objectNo:"",
	  				objectType:7
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
									$scope.partner.logoImgId=data.data.imgId;
								});
								layer.msg('上传成功', 2, 1);
							}
						},
						error:function(data){
							layer.msg('系统繁忙，请稍后再试!', 2, 2);
						}
				});
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
	  				objectType:3
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
									$scope.partner.imgId=data.data.imgId
								});
								layer.msg('上传成功', 2, 1);
							}
						},
						error:function(data){
							layer.msg('系统繁忙，请稍后再试!', 2, 2);
						}
				});
	  	};
    	
    	ue.setContent($scope.partner.partnerContent);
    	//添加
    	$scope.addTopPartnerDetails=function(){
    		$scope.para={
    				partnerName:$scope.partner.partnerName,
    				partnerCompany:$scope.partner.partnerCompany,
    				partnerSimple:$scope.partner.partnerSimple,
    				partnerState:$scope.partner.partnerState,
    				partnerContent:ue.getContent(),
    				logoImgId:$scope.partner.logoImgId,
    				imgId:$scope.partner.imgId,
    				basicsId:$scope.partner.basicsId,
    				typeId:$scope.partner.typeId
    		};
    		topPartnerService.addTopPartnerDetails($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				location.href="#/home/topPartner/topPartnerPageList/index";
    			}else{
    				alert("请求失败，请稍后再试");
    				location.href="#/home/topPartner/topPartnerPageList/index";
    			}
    		});
    	};
    	
    	
    };
    return ["topPartnerAdd", _controller];
});