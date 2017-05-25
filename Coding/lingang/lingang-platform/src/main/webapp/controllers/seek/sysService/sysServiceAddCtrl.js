define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysServiceService){
//    	if($routeParams.value==null || $routeParams.value==""){
//    		alert("信息异常..");
//    	}
    	
    	// 加载所有地区
    	$scope.regionList={};
    	sysServiceService.getRegionAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.regionList=data.data;
			}
		});
    	
    	// 加载所有服务信息
    	$scope.basicsList={};
    	sysServiceService.getServiceBasicsAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.basicsList=data.data;
			}
		});
    	
    	var ue = UM.getEditor('serviceContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	$scope.service={
				serviceName:"",
				serviceSimple:"",
				serviceTeam:"",
				serviceAddress:"",
				serviceFax:"",
				serviceLink:"",
				serviceCompany:"",
				serviceState:2,
				serviceContent:"",
				logoImgId:"",
				minImgId:"",
				regionId:"",
				basicsId:""
    	};
    	
    	
    	 //图片(大图)
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
	  				objectType:15
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
									$scope.service.logoImgId=data.data.imgId;
//									$scope.service.maxImgPath=data.data.imgPath;
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
	  				objectType:14
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
									$scope.service.minImgId=data.data.imgId
//									$scope.service.imgPath=data.data.imgPath;
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
	  	
    	
    	ue.setContent($scope.service.serviceContent);
    	//添加
    	$scope.addServiceDetails=function(){
    		/** JS校验 */
    		// 名称
    		if($scope.service.serviceName == ""){
				check("serviceName","机构名称不能为空")
				return;
			}
    		// 简称
    		if($scope.service.serviceSimple == ""){
    			check("serviceSimple","机构简称不能为空")
    			return;
    		}
    		// 服务项目
    		if($scope.service.serviceTeam == ""){
    			check("serviceTeam","服务项目不能为空")
    			return;
    		}
    		// 地址
    		if($scope.service.serviceAddress == ""){
    			check("serviceAddress","机构地址不能为空")
    			return;
    		}
    		// 传真
    		if($scope.service.serviceFax == ""){
    			check("serviceFax","传真内容不能为空");
				return;
    		}
    		if($scope.service.serviceFax != ""){
    			var reg =  /^(\d{3,4}-)?\d{7,8}$/;
    			if(!reg.test($scope.service.serviceFax)){
    				check("serviceFax","传真格式不正确");
    				return;
    			}
    		}
    		// 网址
    		if($scope.service.serviceLink == ""){
    			check("serviceLink","网址不能为空");
				return;
    		}
    		/*if($scope.service.serviceLink != ""){
    			var reg = /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;
    			if(!reg.test($scope.service.serviceLink)){
    				check("serviceLink","网址格式不正确");
    				return;
    			}
    		}*/
    		// 所属公司
    		if($scope.service.serviceCompany == ""){
    			check("serviceCompany","所属公司不能为空");
    			return;
    		}
    		// 地区
    		if($scope.service.regionId == "" || $scope.service.regionId == null ){
    			check("regionId","请选择地区")
    			return;
    		}
    		// 服务信息
    		if($scope.service.basicsId == "" || $scope.service.basicsId == null ){
    			check("basicsId","请选择服务信息")
    			return;
    		}
    		
    		$.layer({
	  			shade: [0.5, '#000'],
	  			area: ['auto','auto'],
	  			dialog: {
	  		        msg: '您确定要添加吗？',
	  		        btns: 2,         
	  		        type: 4,
	  		        btn: ['添加','取消'],
	  		        yes: function(){
	  		    		$scope.para={
	  		    				serviceName:$scope.service.serviceName,
	  		    				serviceSimple:$scope.service.serviceSimple,
	  		    				serviceTeam:$scope.service.serviceTeam,
	  		    				serviceAddress:$scope.service.serviceAddress,
	  		    				serviceFax:$scope.service.serviceFax,
	  		    				serviceLink:$scope.service.serviceLink,
	  		    				serviceCompany:$scope.service.serviceCompany,
	  		    				serviceState:$scope.service.serviceState,
	  		    				serviceContent:ue.getContent(),
	  		    				logoImgId:$scope.service.logoImgId,
	  		    				minImgId:$scope.service.minImgId,
	  		    				regionId:$scope.service.regionId,
	  		    				basicsId:$scope.service.basicsId
	  		    		};
	  		    		sysServiceService.addServiceDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				 layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysService/sysServicePageList/index";
	  		    		});
	  		        }, no: function(){
	  		            layer.msg('已取消', 1, 2);
	  		        }
	  		    }
	  		});
    	};
    	
    	// JS校验的方法
		var check = function(id,msg){
			$("#" + id).tips({
				side : 2,// 
				color:'#F00',// 字体颜色
				msg : msg,// 提示信息
				bg : '#FFF',// 背景颜色
				time : 2,// 显示时间
				x : 10,//横向偏移
				y : 2//纵向偏移 
			});
			$("#" + id).focus();
		};
		
    };
    return ["sysServiceAddCtrl", _controller];
});