define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysPartnerService){
    	if($routeParams.value==null || $routeParams.value==""){
    		alert("信息异常..");
    	}
    	var ue = UM.getEditor('partnerContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	
    	// 加载所有合作层级
    	$scope.basicsList={};
    	sysPartnerService.getPartnerBasicsAll($http,$scope.para).then(function(data) {
			if(data.stateCode=="1000"){
				$scope.basicsList=data.data;
			}
		});
    	
    	// 加载所有合作类型
    	$scope.typeList={};
    	sysPartnerService.getSysPartnerTypeAll($http,$scope.para).then(function(data) {
    		if(data.stateCode=="1000"){
    			$scope.typeList=data.data;
    		}
    	});
    	
    	//获取详情
    	$scope.partner={};
    	$scope.getPartnerDetails=function(){
    		$scope.para={
    				partnerId:$routeParams.value
    		};
    		sysPartnerService.getPartnerDetails($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.partner=data.data;
    				ue.setContent($scope.partner.partnerContent);
    				$scope.imgSrcMax =data.data.imgPath;
    				$scope.imgSrcMin =data.dataMap.minImgPath;
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
	  				objectId:$scope.partner.partnerId,
	  				objectNo:0,
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
//									$scope.imgSrcMax=data.data.imgPath;
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
	  				objectId:$scope.partner.partnerId,
	  				objectNo:0,
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
									$scope.partner.imgId=data.data.imgId;
//									$scope.imgSrcMin=data.data.imgPath;
								});
								layer.msg('上传成功', 2, 1);
							}
						},
						error:function(data){
							layer.msg('系统繁忙，请稍后再试!', 2, 2);
						}
				});
	  	};
	  	
	  	
    	
    	//修改伙伴基本信息
    	$scope.updatePartnerDetails=function(){
    		/** JS校验 */
    		// 名称
    		if($scope.partner.partnerName == ""){
				check("partnerName","伙伴名称不能为空")
				return;
			}
    		// 简称
    		if($scope.partner.partnerSimple == ""){
    			check("partnerSimple","伙伴简称不能为空")
    			return;
    		}
    		// 所属公司
    		if($scope.partner.partnerCompany == ""){
    			check("partnerCompany","所属公司不能为空")
    			return;
    		}
    		// 合作层级
    		if($scope.partner.basicsId == "" || $scope.partner.basicsId == null ){
    			check("basicsId","请选择合作层级")
    			return;
    		}
    		// 合作类型
    		if($scope.partner.typeId == "" || $scope.partner.typeId == null ){
    			check("typeId","请选择合作类型")
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
	  		    				partnerId:$scope.partner.partnerId,
	  		    				partnerName:$scope.partner.partnerName,
	  		    				partnerCompany:$scope.partner.partnerCompany,
	  		    				partnerSimple:$scope.partner.partnerSimple,
	  		    				partnerState:$scope.partner.partnerState,
	  		    				partnerContent:ue.getContent(),
	  		    				logoImgId:$scope.partner.logoImgId,
	  		    				imgId:$scope.partner.imgId,
	  		    				basicsId:$scope.partner.basicsId,
	  		    				typeId:$scope.partner.typeId,
	  		    				signTime:$scope.partner.signTime
	  		    		};
	  		        	sysPartnerService.updatePartnerDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysPartner/sysPartnerPageList/update";
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
    				partnerId:$scope.partner.partnerId,
    				labelId:$scope.partner.labels[labIndex].labelId
    		};
    		
    		sysPartnerService.delLab($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.partner.labels.splice(labIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//删除公司级联
    	$scope.delCom=function(comIndex){
    		var para={
    				assortType:2,
    				objId:$scope.partner.partnerId,
    				companyId:$scope.partner.companys[comIndex].companyId
    		};
    		
    		sysPartnerService.delCom($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.partner.companys.splice(comIndex,1);
    				alert("删除成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	//添加公司
    	$scope.addCom=function(){
    		$.cookie("addPartnerCompany_partnerId", $scope.partner.partnerId);
    		window.location="#/basics/sysCompany/sysCompanyPageList/addPartnerCompany";
    	};
    	
    	//添加标签
    	$scope.addLab=function(){
    		$.cookie("addPartnerLabel_partnerId", $scope.partner.partnerId);
    		window.location="#/basics/sysLabel/sysLabelTypePageList/addPartnerLabel";
    	};
    	
    	// 显示客户经理
    	$scope.uman={
    			umanagerName:'',
    			umanagerCompany:'',
    			umanagerDepartment:'',
    			umanagerEmail:'',
    			umanagerTel:'',
    			umanagerMobile:''
    	};
//    	$scope.umanState="";
     	$scope.showUman = function(index){
     		$scope.uman=$scope.partner.umanagers[index];
//     		$scope.umanState=1;
     		$('#uManDetail').modal();
     	};
     	$scope.showUmanAdd = function(){
     		$scope.uman={};
//     		$scope.umanState=2;
     		$('#uMan').modal();
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
    		sysPartnerService.seleceAdUser($http,$scope.uManName).then(function(data) {
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
    				partnerUmanagerId:$scope.partner.umanagers[index].umanagerId
    		};
    		sysPartnerService.delUman($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.partner.umanagers.splice(index,1);
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
	  		        			objId:$scope.partner.partnerId,
	  		        			userAccount:account
	  		        	};
	  		        	sysPartnerService.addUManager($http,para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				layer.msg('操作成功', 1, 1);
	  		    				$scope.partner.umanagers.push(data.data);
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
    	$scope.getPartnerDetails();
    };
    return ["sysPartnerDetailsCtrl", _controller];
});