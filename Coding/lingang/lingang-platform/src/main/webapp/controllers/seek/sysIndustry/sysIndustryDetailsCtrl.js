define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysIndustryService){
    	if($routeParams.value==null || $routeParams.value==""){
    		alert("信息异常..");
    	}
    	var ue = UM.getEditor('industryContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	//获取园区详情
    	$scope.industry={};
    	$scope.getIndustryDetails=function(){
    		$scope.para={
    				industryId:$routeParams.value
    		};
    		sysIndustryService.getIndustryDetails($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.industry=data.data;
    				ue.setContent($scope.industry.industryContent);
    				$scope.imgSrcMax =data.data.imgPath;
    				$scope.imgSrcMin =data.dataMap.minImgPath;
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
	  				objectId:$scope.industry.industryId,
	  				objectNo:0,
	  				objectType:9
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
									$scope.industry.maxImgId=data.data.imgId;
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
	  				objectId:$scope.industry.industryId,
	  				objectNo:0,
	  				objectType:6
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
									$scope.industry.minImgId=data.data.imgId
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
    	$scope.updateIndustryDetails=function(){
    		/** JS校验 */
    		// 标题
    		if($scope.industry.industryTitle == ""){
				check("industryTitle","产业标题不能为空")
				return;
			}
    		// 简称
    		if($scope.industry.industrySimple ==""){
    			check("industrySimple","产业简称不能为空")
    			return;
    		}
    		// 网址
    		if($scope.industry.industryLink ==""){
				check("industryLink","网址不能为空");
				return;
			}
    		/*if($scope.industry.industryLink !=""){
    			var reg = /(http|ftp|https):\/\/[\w\-_]+(\.[\w\-_]+)+([\w\-\.,@?^=%&amp;:/~\+#]*[\w\-\@?^=%&amp;/~\+#])?/;
    			if(!reg.test($scope.industry.industryLink)){
    				check("industryLink","网址格式不正确");
    				return;
    			}
    		}*/
    		
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
	  		    				industryId:$scope.industry.industryId,
	  		    				industryTitle:$scope.industry.industryTitle,
	  		    				industryLink:$scope.industry.industryLink,
	  		    				industryState:$scope.industry.industryState,
	  		    				industrySimple:$scope.industry.industrySimple,
	  		    				industryContent:ue.getContent(),
	  		    				minImgId:$scope.industry.minImgId,
	  		    				maxImgId:$scope.industry.maxImgId
	  		    		
	  		    		};
	  		        	sysIndustryService.updateIndustryDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysIndustry/sysIndustryPageList/update";
	  		    		});
	  		        }, no: function(){
	  		            layer.msg('已取消', 1, 2);
	  		        }
	  		    }
	  		});
    	};
    	
    	//删除标签
    	$scope.delLab=function(labIndex){
    		var para={
    				industryId:$scope.industry.industryId,
    				labelId:$scope.industry.labels[labIndex].labelId
    		};
    		
    		sysIndustryService.delLab($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.industry.labels.splice(labIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除园区
    	$scope.delPark=function(parkIndex){
    		var para={
    				industryId:$scope.industry.industryId,
    				parkId:$scope.industry.parks[parkIndex].parkId
    		};
    		
    		sysIndustryService.delPark($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.industry.parks.splice(parkIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//添加标签
    	$scope.addLab=function(){
    		$.cookie("addIndustryLabel_IndustryId", $scope.industry.industryId);
    		window.location="#/basics/sysLabel/sysLabelTypePageList/addIndustryLabel";
    	};
    	
    	//添加代表园区
    	$scope.addPark=function(){
    		$.cookie("addIndustryPark_IndustryId", $scope.industry.industryId);
    		window.location="#/seek/sysPark/sysParkPageList/addIndustryPark";
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
			$scope.uman=$scope.industry.umanagers[index];
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
			sysIndustryService.seleceAdUser($http,$scope.uManName).then(function(data) {
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
					industryUmanagerId:$scope.industry.umanagers[index].umanagerId
			};
			sysIndustryService.delUman($http,para).then(function(data) {
				if(data.stateCode=="1000"){
					$scope.industry.umanagers.splice(index,1);
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
								objId:$scope.industry.industryId,
								userAccount:account
						};
						sysIndustryService.addUManager($http,para).then(function(data) {
							if(data.stateCode=="1000"){
								layer.msg('操作成功', 1, 1);
								$scope.industry.umanagers.push(data.data);
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
    	
    	$scope.getIndustryDetails();
    };
    return ["sysIndustryDetailsCtrl", _controller];
});