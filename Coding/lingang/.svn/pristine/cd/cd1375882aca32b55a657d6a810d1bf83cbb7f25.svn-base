define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysPublicService){
    	if($routeParams.value==null || $routeParams.value==""){
    		alert("信息异常..");
    	}
    	var ue = UM.getEditor('publicContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	
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
    	
    	//获取园区详情
    	//获取
    	$scope.pPublic={};
    	$scope.getPublicDetails=function(){
    		$scope.para={
    				publicId:$routeParams.value
    		};
    		sysPublicService.getPublicDetails($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.pPublic=data.data;
    				ue.setContent($scope.pPublic.publicContent);
    				$scope.imgSrcMax = data.data.imgPath;
    				$scope.imgSrcMin = data.dataMap.minImgPath;
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	
    	//图片(大图)
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
	  				objectId:$scope.pPublic.publicId,
	  				objectNo:0,
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
	  				objectId:$scope.pPublic.publicId,
	  				objectNo:0,
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
    	$scope.updatePublicDetails=function(){
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
	  		        msg: '您确定要修改吗？',
	  		        btns: 2,         
	  		        type: 4,
	  		        btn: ['修改','取消'],
	  		        yes: function(){
	  		    		$scope.para={
	  		    				publicId:$scope.pPublic.publicId,
	  		    				publicTitle:$scope.pPublic.publicTitle,
	  		    				publicAddress:$scope.pPublic.publicAddress,
	  		    				publicFax:$scope.pPublic.publicFax,
	  		    				publicLink:$scope.pPublic.publicLink,
	  		    				publicCompany:$scope.pPublic.publicCompany,
	  		    				publicState:$scope.pPublic.publicState,
	  		    				publicContent:ue.getContent(),
	  		    				minImgId:$scope.pPublic.minImgId,
	  		    				maxImgId:$scope.pPublic.maxImgId,
	  		    				regionId:$scope.pPublic.regionId,
	  		    				basicsId:$scope.pPublic.basicsId
	  		    		
	  		    		};
	  		        	sysPublicService.updatePublicDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				ayer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysPublic/sysPublicPageList/update";
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
    				wayId:$scope.pPublic.ways[wayIndex].wayId
    		};
    		
    		sysPublicService.delWay($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.pPublic.ways.splice(wayIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除标签
    	$scope.delLab=function(labIndex){
    		var para={
    				publicId:$scope.pPublic.publicId,
    				labelId:$scope.pPublic.labels[labIndex].labelId
    		};
    		
    		sysPublicService.delLab($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.pPublic.labels.splice(labIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除园区
    	$scope.delPark=function(parkIndex){
    		var para={
    				publicId:$scope.pPublic.publicId,
    				parkId:$scope.pPublic.parks[parkIndex].parkId
    		};
    		
    		sysPublicService.delPark($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.pPublic.parks.splice(parkIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除公司级联
    	$scope.delCom=function(comIndex){
    		var para={
    				assortType:5,
    				objId:$scope.pPublic.publicId,
    				companyId:$scope.pPublic.companys[comIndex].companyId
    		};
    		
    		sysPublicService.delCom($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.pPublic.companys.splice(comIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//添加公司
    	$scope.addCom=function(){
    		$.cookie("addPublicCompany_publicId", $scope.pPublic.publicId);
    		window.location="#/basics/sysCompany/sysCompanyPageList/addPublicCompany";
    	};
    	
    	
    	// 添加电话
	  	$scope.wayTel="";
     	$scope.showAddWay = function(){
     		$('#wayAdd').modal();
     	}
     	$scope.addWay = function(){
     		/** JS校验 */
     		// 电话
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
     				wayType:3,
     				wayObjId:$scope.pPublic.publicId
     		};
     		sysPublicService.addWay($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.wayTel="";
    				$scope.pPublic.ways.push(data.data);
    				alert("添加成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
     		$('#wayAdd').modal('hide');
     	}
    	
    	//添加标签
    	$scope.addLab=function(){
    		$.cookie("addPublicLabel_PublicId", $scope.pPublic.publicId);
    		window.location="#/basics/sysLabel/sysLabelTypePageList/addPublicLabel";
    	};
    	
    	//添加所属园区
    	$scope.addPark=function(){
    		$.cookie("addPublicPark_PublicId", $scope.pPublic.publicId);
    		window.location="#/seek/sysPark/sysParkPageList/addPublicPark";
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
			$scope.uman=$scope.pPublic.umanagers[index];
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
			sysPublicService.seleceAdUser($http,$scope.uManName).then(function(data) {
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
					publicUmanagerId:$scope.pPublic.umanagers[index].umanagerId
			};
			sysPublicService.delUman($http,para).then(function(data) {
				if(data.stateCode=="1000"){
					$scope.pPublic.umanagers.splice(index,1);
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
								objId:$scope.pPublic.publicId,
								userAccount:account
						};
						sysPublicService.addUManager($http,para).then(function(data) {
							if(data.stateCode=="1000"){
								layer.msg('操作成功', 1, 1);
								$scope.pPublic.umanagers.push(data.data);
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
    	
    	$scope.getPublicDetails();
    };
    return ["sysPublicDetailsCtrl", _controller];
});