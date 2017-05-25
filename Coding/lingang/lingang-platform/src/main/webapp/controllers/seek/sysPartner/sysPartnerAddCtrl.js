define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysPartnerService){
//    	if($routeParams.value==null || $routeParams.value==""){
//    		alert("信息异常..");
//    	}
    	
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
    	
    	var ue = UM.getEditor('partnerContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	$scope.partner={
				partnerName:"",
				partnerCompany:"",
				partnerSimple:"",
				partnerState:2,
				partnerContent:"",
				logoImgId:"",
				imgId:"",
				basicsId:"",
				typeId:"",
				signTime:""
    	};
    	
    	//logo
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
									$scope.partner.imgId=data.data.imgId
								});
								layer.msg('上传成功', 2, 1);
							}
						},
						error:function(data){
							layer.msg('系统繁忙，请稍后再试!', 2, 2);
						}
				});
	  	};
    	
    	ue.setContent($scope.partner.partnerContent);
    	//添加
    	$scope.addPartnerDetails=function(){
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
	  		        msg: '您确定要添加吗？',
	  		        btns: 2,         
	  		        type: 4,
	  		        btn: ['添加','取消'],
	  		        yes: function(){
	  		        	$scope.para={
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
	  		    		sysPartnerService.addPartnerDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				 layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/seek/sysPartner/sysPartnerPageList/index";
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
    return ["sysPartnerAddCtrl", _controller];
});