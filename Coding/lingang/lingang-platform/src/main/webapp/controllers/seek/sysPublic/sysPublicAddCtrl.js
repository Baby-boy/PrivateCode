define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysPublicService){
//    	if($routeParams.value==null || $routeParams.value==""){
//    		alert("信息异常..");
//    	}
    	
    	// 加载所有地区
    	$scope.regionList={};
    	sysPublicService.getRegionAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.regionList=data.data;
			}
		});
    	
    	// 加载所有开放层级
    	$scope.basicsList={};
    	sysPublicService.getPublicBasicsAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.basicsList=data.data;
			}
		});
    	
    	var ue = UM.getEditor('publicContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	$scope.pPublic={
    			publicTitle:"",
    			publicAddress:"",
    			publicFax:"",
    			publicLink:"",
    			publicCompany:"",
    			publicState:2,
				publicContent:"",
				maxImgId:"",
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
	  				objectType:11
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
									$scope.pPublic.maxImgId=data.data.imgId;
//									$scope.pPublic.maxImgPath=data.data.imgPath;
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
	  				objectType:10
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
									$scope.pPublic.minImgId=data.data.imgId
//									$scope.pPublic.imgPath=data.data.imgPath;
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
	  	
	  	
    	ue.setContent($scope.pPublic.publicContent);
    	//添加
    	$scope.addPublicDetails=function(){
    		/** JS校验 */
    		// 标题
    		if($scope.pPublic.publicTitle == ""){
				check("publicTitle","平台标题不能为空")
				return;
			}
    		// 地址
    		if($scope.pPublic.publicAddress == ""){
    			check("publicAddress","平台地址不能为空")
    			return;
    		}
    		// 传真
    		if($scope.pPublic.publicFax == ""){
    			check("publicFax","传真内容不能为空")
    			return;
    		}
    		if($scope.pPublic.publicFax != ""){
    			var reg =  /^(\d{3,4}-)?\d{7,8}$/;
    			if(!reg.test($scope.pPublic.publicFax)){
    				check("publicFax","传真格式不正确");
    				return;
    			}
    		}
    		// 网址
    		if($scope.pPublic.publicLink == ""){
    			check("publicLink","网址不能为空")
    			return;
    		}
    		/*if($scope.pPublic.publicLink != ""){
    			var reg = /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;
    			if(!reg.test($scope.pPublic.publicLink)){
    				check("publicLink","网址格式不正确");
    				return;
    			}
    		}*/
    		// 所属公司
    		if($scope.pPublic.publicCompany == ""){
    			check("publicCompany","所属公司不能为空")
    			return;
    		}
    		// 地区
    		if($scope.pPublic.regionId == "" || $scope.pPublic.regionId == null ){
    			check("regionId","请选择地区")
    			return;
    		}
    		// 开发层级
    		if($scope.pPublic.basicsId == "" || $scope.pPublic.basicsId == null ){
    			check("basicsId","请选择开发层级")
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
	  		    				publicTitle:$scope.pPublic.publicTitle,
	  		    				publicAddress:$scope.pPublic.publicAddress,
	  		    				publicFax:$scope.pPublic.publicFax,
	  		    				publicLink:$scope.pPublic.publicLink,
	  		    				publicCompany:$scope.pPublic.publicCompany,
	  		    				publicState:$scope.pPublic.publicState,
	  		    				publicContent:ue.getContent(),
	  		    				maxImgId:$scope.pPublic.maxImgId,
	  		    				minImgId:$scope.pPublic.minImgId,
	  		    				regionId:$scope.pPublic.regionId,
	  		    				basicsId:$scope.pPublic.basicsId
	  		    		};
	  		    		sysPublicService.addPublicDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				 layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysPublic/sysPublicPageList/index";
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
    return ["sysPublicAddCtrl", _controller];
});