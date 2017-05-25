define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker','uEditor', 'uEditorConfig', 'uEditorCulture','ajaxUpload'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysNewsService){
    	var ue = UM.getEditor('newsContent',{
	        initialFrameWidth:'100%',
	        initialFrameHeight:200
        });
    	//请求参数
    	$scope.para={
    			newsId:0,
    			newsTitle:"",
    			newsAuthor:"",
    			newsContent:"",
    			newsState:2,
    			imgId:0
    	};
    	
    	ue.setContent($scope.para.newsContent);
    	
    	//获取列表
    	$scope.getNewsDetail=function(){
    		$scope.para.newsId = $routeParams.value;
    		if($scope.para.newsId ==0){alert("系统错误，请刷新后再试！");return;}
    		sysNewsService.sysNewsDetail($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.para=data.data;
    				$scope.imgSrcMax=data.dataMap.imgPath;
    				ue.setContent($scope.para.newsContent);
    			}else if(data.stateCode=="501"){
					alert("修改成功");
				}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	
    	 //图片(大图)
//    	$scope.resourceInfos=[];
//    	$scope.imgSrcMax = $scope.park.maxImgPath;
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
	  				objectId:$scope.para.newsId,
	  				objectNo:0,
	  				objectType:2
	  		};
	  		$.ajaxFileUpload({
				url : url,
				secureuri : false,
				fileElementId : file,
				dataType : 'json',
				data : data,
				success : function(data) {
					// console.log(data);
					// if(data.stateCode == "00"){ alert(data.message);
					// mySession.logout();
					// window.location=serviceBase.url+"login.html";
					// return;
					// }
					if (data.stateCode = "1000") {
						$scope.$apply(function() {
							$scope.para.imgId = data.data.imgId;
							// $scope.park.maxImgPath=data.data.imgPath;
							// $scope.imgSrc = "";
						});
						layer.msg('上传成功', 2, 1);
					}
				},
				error : function(data) {
					layer.msg('系统繁忙，请稍后再试!', 2, 2);
				}
			});
	  	};
	  	
    	
    	$scope.delNewsMsg=function(){
    		if(!window.confirm("您确认要删除吗？")){
    			return;
    		}
    		$scope.para.newsId = $routeParams.value;
    		if($scope.para.newsId ==0){alert("系统错误，请刷新后再试！");return;}
    		sysNewsService.sysNewsDel($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				window.location.href="#/home/sysNews/sysNewsPageList/index";
    			}else if(data.stateCode=="501"||data.stateCode=="502"||data.stateCode=="503"){
				}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	$scope.updateNewsMsg=function(){
    		/** JS校验 */
    		if ($scope.para.newsTitle == "") {
    			check("newsTitle","新闻标题不能为空")
    			return;
			}
    		if ($scope.para.newsAuthor == "") {
    			check("newsAuthor","新闻作者不能为空")
    			return;
			}
    		if (ue.getContent() == "") {
				alert("请完善新闻内容后再进行提交!");
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
	  		        	$scope.para.newsId = $routeParams.value;
	  			    	$scope.para.newsContent = ue.getContent();
	  			    	if($scope.para.newsId == 0){
	  			    		alert("系统错误，请刷新后再试！");
	  			    		return;
	  			    	}
	  		    		sysNewsService.sysNewsUpdate($http,$scope.para).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				 layer.msg('操作成功', 1, 1);
	  		    			}else{
	  		    				layer.msg('请求失败，请稍后再试', 1, 2);
	  		    			}
	  		    			window.location.href="#/home/sysNews/sysNewsPageList/update";
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
    	
    	$scope.getNewsDetail();
    };
    return ["sysNewsMsgCtrl", _controller];
});