define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysStationService){
//    	if($routeParams.value==null || $routeParams.value==""){
//    		alert("信息异常..");
//    	}
    	
    	// 加载所有地区
    	$scope.regionList={};
    	sysStationService.getRegionAll($http,$scope.para).then(function(data) {
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
    	$scope.addStationDetails=function(){
    		/** JS校验 */
    		// 名称
    		if($scope.station.stationTitle == ""){
				check("stationTitle","企业名称不能为空")
				return;
			}
    		// 简称
    		if($scope.station.stationSimple == ""){
    			check("stationSimple","企业简称不能为空")
    			return;
    		}
    		// 注册号
    		if($scope.station.regNumber == ""){
    			check("regNumber","注册号不能为空")
    			return;
    		}
    		// 信用代码
    		if($scope.station.creditCode == ""){
    			check("creditCode","信用代码不能为空")
    			return;
    		}
    		// 所属公司
    		if($scope.station.stationCompany == ""){
    			check("stationCompany","所属公司不能为空")
    			return;
    		}
    		// 注册类型
    		if($scope.station.isFictitious == "" || $scope.station.isFictitious == null ){
    			check("isFictitious","请选择注册类型")
    			return;
    		}
    		// 流失状态
    		if($scope.station.lossState == "" || $scope.station.lossState == null ){
    			check("lossState","请选择流失状态")
    			return;
    		}
    		// 地区
    		if($scope.station.regionId == "" || $scope.station.regionId == null ){
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
	  		    		sysStationService.addStationDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				 layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysStation/sysStationPageList/index";
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
    return ["sysStationAddCtrl", _controller];
});