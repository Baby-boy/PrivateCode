define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysStationService){
    	if($routeParams.value==null || $routeParams.value==""){
    		alert("信息异常..");
    	}
    	var ue = UM.getEditor('stationContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	
    	// 加载所有地区(用于产业产业园区的关联)
    	$scope.regionList={};
    	sysStationService.getRegionAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.regionList=data.data;
			}
		});
    	
    	//获取园区详情
    	$scope.station={};
    	$scope.getStationDetails=function(){
    		$scope.para={
    				stationId:$routeParams.value
    		};
    		sysStationService.getStationDetails($http,$scope.para).then(function(data) {
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
    	$scope.updateStationDetails=function(){
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
	  		        msg: '您确定要修改吗？',
	  		        btns: 2,         
	  		        type: 4,
	  		        btn: ['修改','取消'],
	  		        yes: function(){
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
	  		        	sysStationService.updateStationDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysStation/sysStationPageList/update";
	  		    		});
	  		        }, no: function(){
	  		            layer.msg('已取消', 1, 2);
	  		        }
	  		    }
	  		});
    	};
    	
    	//删除公司级联
    	$scope.delCom=function(comIndex){
    		var para={
    				assortType:4,
    				objId:$scope.station.stationId,
    				companyId:$scope.station.companys[comIndex].companyId
    		};
    		
    		sysStationService.delCom($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.station.companys.splice(comIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//添加公司
    	$scope.addCom=function(){
    		$.cookie("addStationCompany_stationId", $scope.station.stationId);
    		window.location="#/basics/sysCompany/sysCompanyPageList/addStationCompany";
    	};
    	
    	//删除标签
    	$scope.delLab=function(labIndex){
    		var para={
    				stationId:$scope.station.stationId,
    				labelId:$scope.station.labels[labIndex].labelId
    		};
    		
    		sysStationService.delLab($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.station.labels.splice(labIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除园区
    	$scope.delPark=function(parkIndex){
    		var para={
    				stationId:$scope.station.stationId,
    				parkId:$scope.station.parksvo[parkIndex].parkId
    		};
    		
    		sysStationService.delPark($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.station.parksvo.splice(parkIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	
    	//删除覆盖产业
    	$scope.delIndustry=function(industryIndex){
    		var para={
    				stationId:$scope.station.stationId,
    				industryId:$scope.station.industrys[industryIndex].industryId
    		};
    		
    		sysStationService.delIndustry($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.station.industrys.splice(industryIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	
    	
    	//添加标签
    	$scope.addLab=function(){
    		$.cookie("addStationLabel_StationId", $scope.station.stationId);
    		window.location="#/basics/sysLabel/sysLabelTypePageList/addStationLabel";
    	};
    	
    	//添加所在园区
    	$scope.addPark=function(){
    		$.cookie("addStationPark_StationId", $scope.station.stationId);
    		window.location="#/seek/sysPark/sysParkPageList/addStationPark";
    	};
    	
    	
    	//添加所属产业
    	$scope.addIndustry=function(){
    		$.cookie("addStationIndustry_parkId", $scope.station.stationId);
    		window.location="#/seek/sysIndustry/sysIndustryPageList/addStationIndustry";
    	};
    	
    /** js校验 **/
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
			$scope.uman=$scope.station.umanagers[index];
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
			sysStationService.seleceAdUser($http,$scope.uManName).then(function(data) {
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
					stationUmanagerId:$scope.station.umanagers[index].umanagerId
			};
			sysStationService.delUman($http,para).then(function(data) {
				if(data.stateCode=="1000"){
					$scope.station.umanagers.splice(index,1);
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
								objId:$scope.station.stationId,
								userAccount:account
						};
						sysStationService.addUManager($http,para).then(function(data) {
							if(data.stateCode=="1000"){
								layer.msg('操作成功', 1, 1);
								$scope.station.umanagers.push(data.data);
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
		
    	$scope.getStationDetails();
    };
    return ["sysStationDetailsCtrl", _controller];
});