define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysServiceService){
    	if($routeParams.value==null || $routeParams.value==""){
    		alert("信息异常..");
    	}
    	var ue = UM.getEditor('serviceContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	
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
    	
    	//获取园区详情
    	$scope.getServiceDetails=function(){
    		$scope.para={
    				serviceId:$routeParams.value
    		};
    		sysServiceService.getServiceDetails($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.service=data.data;
    				ue.setContent($scope.service.serviceContent);
    				$scope.imgSrcMax = data.data.imgPath;
    				$scope.imgSrcMin = data.dataMap.minImgPath;
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	
    	 //logo
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
	  				objectId:$scope.service.serviceId,
	  				objectNo:0,
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
//									$scope.imgSrcMax=data.data.imgPath;
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
	  				objectId:$scope.service.serviceId,
	  				objectNo:0,
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
    	$scope.updateServiceDetails=function(){
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
	  		        msg: '您确定要修改吗？',
	  		        btns: 2,         
	  		        type: 4,
	  		        btn: ['修改','取消'],
	  		        yes: function(){
	  		    		$scope.para={
	  		    				serviceId:$scope.service.serviceId,
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
	  		    		sysServiceService.updateServiceDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysService/sysServicePageList/update";
	  		    		});
	  		        }, no: function(){
	  		            layer.msg('已取消', 1, 2);
	  		        }
	  		    }
	  		});
    	};
    	
    	//删除电话
    	$scope.delWay=function(wayIndex){
    		var para={
    				wayId:$scope.service.ways[wayIndex].wayId
    		};
    		
    		sysServiceService.delWay($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.service.ways.splice(wayIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除标签
    	$scope.delLab=function(labIndex){
    		var para={
    				serviceId:$scope.service.serviceId,
    				labelId:$scope.service.labels[labIndex].labelId
    		};
    		
    		sysServiceService.delLab($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.service.labels.splice(labIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除园区
    	$scope.delPark=function(parkIndex){
    		var para={
    				serviceId:$scope.service.serviceId,
    				parkId:$scope.service.parks[parkIndex].parkId
    		};
    		
    		sysServiceService.delPark($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.service.parks.splice(parkIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除公司级联
    	$scope.delCom=function(comIndex){
    		var para={
    				assortType:3,
    				objId:$scope.service.serviceId,
    				companyId:$scope.service.companys[comIndex].companyId
    		};
    		
    		sysServiceService.delCom($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.service.companys.splice(comIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//添加公司
    	$scope.addCom=function(){
    		$.cookie("addServiceCompany_serviceId", $scope.service.serviceId);
    		window.location="#/basics/sysCompany/sysCompanyPageList/addServiceCompany";
    	};
    	
    	// 添加电话
	  	$scope.wayTel="";
     	$scope.showAddWay = function(){
     		$('#wayAdd').modal();
     	}
     	$scope.addWay = function(){
     		/** JS校验 */
     		if($scope.wayTel ==""){
				check("way","电话号码不能为空");
				return;
			}
     		if($scope.wayTel !=""){
     			var reg = /^(^(\d{3,4}-)?\d{7,8})$|(1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\d{8})$/;
    			if(!reg.test($scope.wayTel)){
    				check("way","电话格式不正确");
    				return;
    			}
     		}
     		
     		var para={
     				wayTel:$scope.wayTel,
     				wayType:2,
     				wayObjId:$scope.service.serviceId
     		};
     		sysServiceService.addWay($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.wayTel="";
    				$scope.service.ways.push(data.data);
    				alert("添加成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
     		$('#wayAdd').modal('hide');
     	}
    	
    	//添加标签
    	$scope.addLab=function(){
    		$.cookie("addServiceLabel_ServiceId", $scope.service.serviceId);
    		window.location="#/basics/sysLabel/sysLabelTypePageList/addServiceLabel";
    	};
    	
    	//添加覆盖园区
    	$scope.addPark=function(){
    		$.cookie("addServicePark_ServiceId", $scope.service.serviceId);
    		window.location="#/seek/sysPark/sysParkPageList/addServicePark";
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
		
		
		//客户经理
		$scope.showUman = function(index){
			$scope.uman=$scope.service.umanagers[index];
			$('#uManDetail').modal();
		};
		$scope.showUmanAdd = function(){
			$scope.uman={};
			$('#uMan').modal();
		};

		//搜索客户经理
		$scope.uManList={};
		$scope.uManName="";
		$scope.seleceAdUser=function(){
			// 名称
			if($scope.uManName == ""){
					$("#uManName").tips({
						side : 1,// 
						color:'#F00',// 字体颜色
						msg : "请输入经理名称",// 提示信息
						bg : '#FFF',// 背景颜色
						time : 2,// 显示时间
					});
					$("#uManName").focus();
					return;
				}
			sysServiceService.seleceAdUser($http,$scope.uManName).then(function(data) {
				if(data.stateCode=="1000"){
					$scope.uManList=data.data.data;
				}else{
					alert("请求失败，请稍后再试");
				}
			});
		};
		//删除 客户经理
		$scope.delUman=function(index){
			var para={
					serviceUmanagerId:$scope.service.umanagers[index].umanagerId
			};
			sysServiceService.delUman($http,para).then(function(data) {
				if(data.stateCode=="1000"){
					$scope.service.umanagers.splice(index,1);
					alert("删除成功");
				}else{
					alert("请求失败，请稍后再试");
				}
			});
		};

		// 添加客户经理
		$scope.addUManager=function(account){
			$.layer({
					shade: [0.5, '#000'],
					area: ['auto','auto'],
					dialog: {
					msg: '您确定要添加吗？',
					btns: 2,         
					type: 4,
					btn: ['添加','取消'],
					yes: function(){
						var para={
								objId:$scope.service.serviceId,
								userAccount:account
						};
						sysServiceService.addUManager($http,para).then(function(data) {
							if(data.stateCode=="1000"){
								layer.msg('操作成功', 1, 1);
								$scope.service.umanagers.push(data.data);
							}else if(data.message !=""){
								layer.msg(data.message, 1, 2);
							}else{
								layer.msg('请求失败，请稍后再试', 1, 2);
							}
							$('#uMan').modal('hide');
						});
					}, no: function(){
					    layer.msg('已取消', 1, 2);
					}
				    }
				});
		}
    	
    	$scope.getServiceDetails();
    };
    return ["sysServiceDetailsCtrl", _controller];
});