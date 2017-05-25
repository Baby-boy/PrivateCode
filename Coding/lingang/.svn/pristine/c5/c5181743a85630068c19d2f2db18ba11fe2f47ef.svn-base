define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysParkService){
//    	if($routeParams.value==null || $routeParams.value==""){
//    		alert("信息异常..");
//    	}
    	
    	// 加载所有地区(用于产业产业园区的关联)
    	$scope.regionList={};
		sysParkService.getRegionAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.regionList=data.data;
			}
		});
    	
    	var ue = UM.getEditor('parkContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	$scope.park={
				parkName:"",
				parkAddress:"",
				parkFax:"",
				parkLink:"",
				parkState:2,
				parkContent:"",
				maxImgId:"",
				imgId:"",
				regionId:"",
				detailLink:""
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
	  				objectType:8
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
									$scope.park.maxImgId=data.data.imgId;
//									$scope.park.maxImgPath=data.data.imgPath;
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
	  				objectType:5
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
									$scope.park.imgId=data.data.imgId
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
	  	
    	ue.setContent($scope.park.parkContent);
    	//添加园区
    	$scope.addParkDetails=function(){
    		/** JS校验 */
    		// 名称
    		if($scope.park.parkName == ""){
				check("parkName","园区名称不能为空")
				return;
			}
    		// 地址
    		if($scope.park.parkAddress == ""){
    			check("parkAddress","园区地址不能为空")
    			return;
    		}
    		// 传真
    		if($scope.park.parkFax == ""){
    			check("parkFax","传真内容不能为空")
    			return;
    		}
    		if($scope.park.parkFax != ""){
    			var reg =  /^(\d{3,4}-)?\d{7,8}$/;
    			if(!reg.test($scope.park.parkFax )){
    				check("parkFax","传真格式不正确");
    				return;
    			}
    		}
    		// 网址
    		if($scope.park.parkLink == ""){
    			check("parkLink","网址不能为空")
    			return;
    		}
    		/*if($scope.park.parkLink != ""){
    			var reg = /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;
    			if(!reg.test($scope.park.parkLink)){
    				check("parkLink","网址格式不正确");
    				return;
    			}
    		}*/
    		// 地区
    		if($scope.park.regionId == "" || $scope.park.regionId == null ){
    			check("regionId","请选择地区")
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
	  		    				parkName:$scope.park.parkName,
	  		    				parkAddress:$scope.park.parkAddress,
	  		    				parkFax:$scope.park.parkFax,
	  		    				parkLink:$scope.park.parkLink,
	  		    				parkState:$scope.park.parkState,
	  		    				parkContent:ue.getContent(),
	  		    				maxImgId:$scope.park.maxImgId,
	  		    				minImgId:$scope.park.imgId,
	  		    				regionId:$scope.park.regionId,
	  		    				detailLink:$scope.park.detailLink
	  		    		};
	  		    		sysParkService.addParkDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				 layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysPark/sysParkPageList/index";
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
    return ["sysParkAddCtrl", _controller];
});