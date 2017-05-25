define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysParkService){
    	if($routeParams.value==null || $routeParams.value==""){
    		alert("信息异常..");
    	}
    	var ue = UM.getEditor('parkContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	
    	// 加载所有地区(用于产业产业园区的关联)
    	$scope.regionList={};
		sysParkService.getRegionAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.regionList=data.data;
			}
		});
    	
    	//获取园区详情
    	$scope.park={};
    	$scope.getParkDetails=function(){
    		$scope.para={
    				parkId:$routeParams.value
    		};
    		sysParkService.getParkDetails($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.park=data.data;
    				$scope.imgSrcMax = data.data.maxImgPath;
    				$scope.imgSrcMin =  data.dataMap.minImgPath;
    				ue.setContent($scope.park.parkContent);
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
	  				objectId:$scope.park.parkId,
	  				objectNo:0,
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
	  				objectId:$scope.park.parkId,
	  				objectNo:0,
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
	  	
	  	
	  	 //视频封面图
	  	$scope.videoIndex=0;
	  	$scope.videoImageShow=1;
        $scope.previewImageVideo = function(){
    		$scope.imgSrcVideo = window.imgUrl;
  		};
		$scope.delPreImgVideo = function(){
			$scope.imgSrcVideo = "";
			$scope.videoImageShow=1;
		};
    	//上传
	      $scope.uploadImageVideo = function(file){
				if(!$scope.imgSrcVideo){
					layer.msg("未选择图片", 2, 2);
					return;
				}
	  		var url = "fileUpload/imageUpload.do";
	  		var data = {
	  				objectId:$scope.park.files[$scope.videoIndex].fileId,
	  				objectNo:0,
	  				objectType:16
	  		};
	  		$.ajaxFileUpload({
						url:url,
						secureuri:false,
						fileElementId:file,
						dataType:'json',
						data:data,
						success:function(data){
							if(data.stateCode="1000"){
								$scope.$apply(function(){
									$scope.park.files[$scope.videoIndex].imgIdVideo =data.data.imgId;
//									$scope.imgSrcMax=data.data.imgPath;
//									$scope.imgSrc = "";
									$scope.videoImageShow=0;
								});
								layer.msg('上传成功', 2, 1);
								
							}
						},
						error:function(data){
							layer.msg('系统繁忙，请稍后再试!', 2, 2);
						}
				});
	  	};
	  	$scope.uploadFileParkVideoImage=function(){
	  		var para={
	  				fileId:$scope.park.files[$scope.videoIndex].fileId,
	  				imgId:$scope.park.files[$scope.videoIndex].imgIdVideo
	  		};
	  		sysParkService.uploadFileParkVideoImage($http,para).then(function(data) {
	    			if(data.stateCode=="1000"){
	    				 layer.msg('操作成功', 1, 1);
	    				 $scope.videoImageShow=1;
	    				 $('#parkVideo').modal('hide');
	    			}else{
	    				layer.msg('请求失败，请稍后再试', 1, 2);
	    			}
	    		});
	  	};
	  	
	  	
	  	//宣传册
	  	$scope.bookShow=1;
        $scope.previewFileBook = function(){
    		$scope.fileSrcBook = document.getElementById('fileBook').files[0].name;
    		$scope.bookShow=0;
    		
  		};
  		$scope.delFileBook = function(){
			$scope.fileSrcBook = "";
			$scope.bookShow=1;
		};
    	//上传宣传册
	    $scope.uploadFileBook = function(file){
				if(!$scope.fileSrcBook){
					layer.msg("未选择文件", 2, 2);
					return;
				}
	  		var url = "parkFileUpload/fileUpload.do";
	  		var data = {
	  				objectId:$scope.park.parkId,
	  				objectNo:0,
	  				objectType:1
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
									$scope.park.files.push(data.data);
									$scope.fileSrcBook = "";
									$scope.bookShow=1;
								});
								layer.msg('上传成功', 2, 1);
							}
						},
						error:function(data){
							layer.msg('系统繁忙，请稍后再试!', 2, 2);
						}
				});
	  	};
	  	
	  	
	  	//宣传片
	  	$scope.videoShow=1;
        $scope.previewFileVideo = function(){
    		$scope.fileSrcVideo = document.getElementById('fileVideo').files[0].name;
    		$scope.videoShow=0;
    		
  		};
  		$scope.delFileVideo = function(){
			$scope.fileSrcVideo = "";
			$scope.videoShow=1;
		};
    	//上传宣传片
	    $scope.uploadFileVideo = function(file){
				if(!$scope.fileSrcVideo){
					layer.msg("未选择文件", 2, 2);
					return;
				}
	  		var url = "parkFileUpload/fileUpload.do";
	  		var data = {
	  				objectId:$scope.park.parkId,
	  				objectNo:0,
	  				objectType:2
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
									$scope.park.files.push(data.data);
									$scope.fileSrcVideo = "";
									$scope.videoShow=1;
								});
								layer.msg('上传成功', 2, 1);
							}
						},
						error:function(data){
							layer.msg('系统繁忙，请稍后再试!', 2, 2);
						}
				});
	  	};
	  	
	  	
	  	// 宣传册更新/下载
	  	$scope.fileSrcParkBook="";
  		$scope.parkBookShow=1;
  		$scope.fileIndex=0;
	  	$scope.showParkBook=function(index){
	  		$scope.fileSrcParkBook="";
	  		$scope.parkBookShow=1;
	  		$scope.fileSrcParkBook=$scope.park.files[index].fileTitle;
	  		$scope.fileIndex=index;
	  		$scope.videoIndex=index;
	  		$('#parkBook').modal();
	  	};
     	$scope.previewFileParkBook = function(fileIndex){
     		$scope.fileSrcParkBook = document.getElementById('fileParkBook').files[0].name;
    		$scope.parkBookShow=0;
     	};
     	$scope.delFileParkBook = function(){
			$scope.fileSrcParkBook = "";
			$scope.parkBookShow=1;
		};
	    $scope.uploadFileParkBook = function(file){
				if(!$scope.fileSrcParkBook){
					layer.msg("未选择文件", 2, 2);
					return;
				}
	  		var url = "parkFileUpload/fileUpload.do";
	  		var data = {
	  				objectId:$scope.park.parkId,
	  				objectNo:$scope.park.files[$scope.fileIndex].fileId,
	  				objectType:1
	  		};
	  		$.ajaxFileUpload({
						url:url,
						secureuri:false,
						fileElementId:file,
						dataType:'json',
						data:data,
						success:function(data){
							if(data.stateCode="1000"){
								$scope.$apply(function(){
									$scope.park.files[$scope.fileIndex]=data.data;
									$scope.fileSrcBook = "";
									$scope.bookShow=1;
								});
								layer.msg('更新成功', 2, 1);
								$('#parkBook').modal('hide');
							}
						},
						error:function(data){
							layer.msg('系统繁忙，请稍后再试!', 2, 2);
						}
						
				});
	  	};
	  	
	  	// 宣传片封面
	  	$scope.fileSrcParkVideo="";
  		$scope.parkVideoShow=1;
  		$scope.imgSrcVideo="";
	  	$scope.showParkVideo=function(index){
	  		$scope.fileSrcParkVideo="";
	  		$scope.parkVideoShow=1;
	  		$scope.videoImageShow=1;
	  		$scope.fileSrcParkVideo=$scope.park.files[index].fileTitle;
	  		$scope.imgSrcVideo=$scope.park.files[index].imgPathVideo;
	  		$scope.fileIndex=index;
	  		$scope.videoIndex=index;
	  		$('#parkVideo').modal();
	  	};
     	$scope.previewFileParkVideo = function(fileIndex){
     		$scope.fileSrcParkVideo = document.getElementById('fileParkVideo').files[0].name;
    		$scope.parkVideoShow=0;
     	};
     	$scope.delFileParkVideo = function(){
			$scope.fileSrcParkVideo = "";
			$scope.parkVideoShow=1;
		};
	    $scope.uploadFileParkVideo = function(file){
				if(!$scope.fileSrcParkVideo){
					layer.msg("未选择文件", 2, 2);
					return;
				}
	  		var url = "parkFileUpload/fileUpload.do";
	  		var data = {
	  				objectId:$scope.park.parkId,
	  				objectNo:$scope.park.files[$scope.fileIndex].fileId,
	  				objectType:2
	  		};
	  		$.ajaxFileUpload({
						url:url,
						secureuri:false,
						fileElementId:file,
						dataType:'json',
						data:data,
						success:function(data){
							if(data.stateCode="1000"){
								$scope.$apply(function(){
									$scope.park.files[$scope.fileIndex]=data.data;
									$scope.fileSrcVideo = "";
									$scope.videoShow=1;
								});
								layer.msg('更新成功', 2, 1);
								$('#parkVideo').modal('hide');
							}
						},
						error:function(data){
							layer.msg('系统繁忙，请稍后再试!', 2, 2);
						}
						
				});
	  	};
	  	
	  	
	  	
    	//修改园区基本信息
    	$scope.updateParkDetails=function(){
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
    			if(!reg.test($scope.park.parkFax)){
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
	  		        msg: '您确定要修改吗？',
	  		        btns: 2,         
	  		        type: 4,
	  		        btn: ['修改','取消'],
	  		        yes: function(){
	  		    		var para={
	  		    				parkId:$scope.park.parkId,
	  		    				parkName:$scope.park.parkName,
	  		    				parkAddress:$scope.park.parkAddress,
	  		    				parkFax:$scope.park.parkFax,
	  		    				parkLink:$scope.park.parkLink,
	  		    				parkState:$scope.park.parkState,
	  		    				parkContent:ue.getContent(),
	  		    				minImgId:$scope.park.imgId,
	  		    				maxImgId:$scope.park.maxImgId,
	  		    				regionId:$scope.park.regionId,
	  		    				detailLink:$scope.park.detailLink
	  		    		};
	  		    		sysParkService.updateParkDetails($http,para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				 layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysPark/sysParkPageList/update";
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
    				wayId:$scope.park.ways[wayIndex].wayId
    		};
    		
    		sysParkService.delWay($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.park.ways.splice(wayIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除标签
    	$scope.delLab=function(labIndex){
    		var para={
    				parkId:$scope.park.parkId,
    				labelId:$scope.park.labels[labIndex].labelId
    		};
    		
    		sysParkService.delLab($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.park.labels.splice(labIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	//删除覆盖产业
    	$scope.delIndustry=function(industryIndex){
    		var para={
    				parkId:$scope.park.parkId,
    				industryId:$scope.park.industrys[industryIndex].industryId
    		};
    		
    		sysParkService.delIndustry($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.park.industrys.splice(industryIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除宣传册/宣传片
    	$scope.delFile=function(fileIndex){
    		var para={
    				fileId:$scope.park.files[fileIndex].fileId
    		};
    		
    		sysParkService.delFile($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.park.files.splice(fileIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
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
     				wayType:1,
     				wayObjId:$scope.park.parkId
     		};
    		sysParkService.addWay($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.wayTel="";
    				$scope.park.ways.push(data.data);
    				alert("添加成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
     		$('#wayAdd').modal('hide');
     	}
    	
    	
    	//添加标签
    	$scope.addLab=function(){
    		$.cookie("addParkLabel_parkId", $scope.park.parkId);
    		window.location="#/basics/sysLabel/sysLabelTypePageList/addParkLabel";
    	};
    	
    	//添加覆盖产业
    	$scope.addIndustry=function(){
    		$.cookie("addParkIndustry_parkId", $scope.park.parkId);
    		window.location="#/seek/sysIndustry/sysIndustryPageList/addParkIndustry";
    	};
    	
    	//客户经理
    	$scope.showUman = function(index){
     		$scope.uman=$scope.park.umanagers[index];
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
     		sysParkService.seleceAdUser($http,$scope.uManName).then(function(data) {
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
     				parkUmanagerId:$scope.park.umanagers[index].umanagerId
     		};
     		sysParkService.delUman($http,para).then(function(data) {
     			if(data.stateCode=="1000"){
     				$scope.park.umanagers.splice(index,1);
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
     							objId:$scope.park.parkId,
     							userAccount:account
     					};
     					sysParkService.addUManager($http,para).then(function(data) {
     						if(data.stateCode=="1000"){
     							layer.msg('操作成功', 1, 1);
     							$scope.park.umanagers.push(data.data);
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
    	
    	$scope.getParkDetails();
    };
    return ["sysParkDetailsCtrl", _controller];
});