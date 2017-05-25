define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysPolicyService){
    	if($routeParams.value==null || $routeParams.value==""){
    		alert("信息异常..");
    	}
    	var ue = UM.getEditor('policyContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	//获取政策详情
    	$scope.policy={};
    	$scope.getPolicyDetails=function(){
    		$scope.para={
    				policyId:$routeParams.value
    		};
    		sysPolicyService.getPolicyDetails($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.policy=data.data;
    				$scope.imgSrcMax = $scope.policy.imgPath;
    				ue.setContent($scope.policy.policyContent);
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
	  				objectId:$scope.policy.policyId,
	  				objectNo:0,
	  				objectType:13
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
									$scope.policy.imgId=data.data.imgId;
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
	  	
	  	
	  	
    	//修改政策基本信息
    	$scope.updatePolicyDetails=function(){
    		/** JS校验 */
    		// 标题
    		if ($scope.policy.policyTitle == "") {
    			check("policyTitle","政策标题不能为空")
    			return;
			}
    		// 政策内容
    		if (ue.getContent() == "") {
    			alert("请完善政策内容后再进行提交!")
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
	  		    				policyId:$scope.policy.policyId,
	  		    				policyTitle:$scope.policy.policyTitle,
	  		    				policyState:$scope.policy.policyState,
	  		    				policyContent:ue.getContent(),
	  		    				imgId:$scope.policy.imgId
	  		    		
	  		    		};
	  		    		sysPolicyService.updatePolicyDetails($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				 layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			location.href="#/home/sysPolicy/sysPolicyPageList/update";
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
    	
    	$scope.getPolicyDetails();
    };
    return ["sysPolicyMsg", _controller];
});