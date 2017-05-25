define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
	var _controller = function($scope, $http, $routeParams, $location,sysLabelService){
		//页面滚动坐标初始化(主要针对级联跳转)
		window.scrollTo(0,0);
    	//分页设置 
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysLabelPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getLabelList();
                    }
                }
        };
    	//请求参数
    	$scope.para={
    			labelType:"",
    			labelState:1	
    	};
    	//列表数据
    	$scope.labelList=[];
    	$scope.onePageCount="";
    	
    	//获取列表
    	$scope.getLabelList=function(){
    		//当前页与每页显示条数
    		$scope.para.currentPage =$scope.paginationConf.currentPage;
    		$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		
    		if($routeParams.value=='index'){
    			sysLabelService.sysLabelPagePageListP($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.labelList=data.data.list;
        				// 当前页与每页条数
        				$scope.paginationConf.currentPage=data.data.currentPage;
        				$scope.onePageCount=data.data.onePageCount;
        				//页数获取
        				$scope.paginationConf.totalItems=data.data.countRecord;
        			}else{
        				alert("请求失败，请稍后再试");
        			}
        		});
    		}else{
    			sysLabelService.sysLabelPagePageList($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.labelList=data.data.list;
        				// 当前页与每页条数
        				$scope.paginationConf.currentPage=data.data.currentPage;
        				$scope.onePageCount=data.data.onePageCount;
        				//页数获取
        				$scope.paginationConf.totalItems=data.data.countRecord;
        			}else{
        				alert("请求失败，请稍后再试");
        			}
        		});
    		}
    	};
    	
    	//显示(基础设置)
    	$scope.showLabelDetails=function(m){
    		$scope.label=m;
    		$('#myModalLabel').modal();
    	};
    	
    	//修改(基础设置)
    	$scope.updateSysLabel=function(){
    		/** JS校验 */
    		if ($scope.label.labelName == "") {
    			check("labelName","标签名称不能为空")
    			return;
			}
    		
    		var m={
    				labelId:$scope.label.labelId,
    				labelName:$scope.label.labelName,
    				labelType:$scope.label.labelType,
    				// createTime:$scope.label.createTime,
    				// updateTime:$scope.label.updateTime,
    				labelState:$scope.label.labelState
    				
    		};
    		
    		sysLabelService.updateSysLabel($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("修改成功");
    				$scope.getLabelList();
    			}else{
    				alert("请求失败，请稍后再试");
    				$scope.getLabelList();
    			}
    			$('#myModalLabel').modal('hide');
    		});
    	};
    	
    	
    	$scope.labelAdd={
    		labelState:1,
    		labelName:"",
    		labelType:""
    	};
    	//添加(标签)
    	$scope.basicLabelAdd=function(){
    		$('#myModalLabelAdd').modal();
    	};
    	
    	//添加(标签)
    	$scope.sysLabelAdd=function(){
    		/** JS校验 */
    		if ($scope.labelAdd.labelName == "") {
    			check("labelNameAdd","标签名称不能为空")
    			return;
			}
    		if ($scope.labelAdd.labelType == "" || $scope.labelAdd.labelType == null) {
    			check("labelTypeAdd","请选择标签类型")
    			return;
    		}
    		
    		var m={
    				labelId:$scope.labelAdd.labelId,
    				labelType:$scope.labelAdd.labelType,
    				labelName:$scope.labelAdd.labelName,
    				labelState:$scope.labelAdd.labelState
    				
    		};
    		sysLabelService.sysLabelAdd($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				$scope.getLabelList();
    			}else{
    				alert("请求失败，请稍后再试");
    				$scope.getLabelList();
    			}
    			$('#myModalLabelAdd').modal('hide');
    		});
    	};
    	
    	
    	//级联信息操作
    	$scope.cascade=0;
    	$scope.objectId="";
    	if($routeParams.value=="addParkLabel"){
			$scope.cascade=1;
			$scope.para.labelType=8;
			$scope.para.labelState=1;
			$scope.objectId=$.cookie("addParkLabel_parkId");
		}else if($routeParams.value=="addPartnerLabel"){
			$scope.cascade=1;
			$scope.para.labelType=1;
			$scope.para.labelState=1;
			$scope.objectId=$.cookie("addPartnerLabel_partnerId");
		}else if($routeParams.value=="addIndustryLabel"){
			$scope.cascade=1;
			$scope.para.labelType=10;
			$scope.para.labelState=1;
			$scope.objectId=$.cookie("addIndustryLabel_IndustryId");
		}else if($routeParams.value=="addServiceLabel"){
			$scope.cascade=1;
			$scope.para.labelType=11;
			$scope.para.labelState=1;
			$scope.objectId=$.cookie("addServiceLabel_ServiceId");
		}else if($routeParams.value=="addStationLabel"){
			$scope.cascade=1;
			$scope.para.labelType="3,4,5,6,7";
			$scope.para.labelState=1;
			$scope.objectId=$.cookie("addStationLabel_StationId");
		}else if($routeParams.value=="addPublicLabel"){
			$scope.cascade=1;
			$scope.para.labelType=9;
			$scope.para.labelState=1;
			$scope.objectId=$.cookie("addPublicLabel_PublicId");
		}
    	//添加级联标签
    	$scope.addLabelCascade=function(labId){
    		var para={
    				objId:$scope.objectId,
    				labelId:labId,
    				labelType:$scope.para.labelType
    		};
    		sysLabelService.addLabelCascade($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				if($routeParams.value=="addParkLabel"){
    					$.cookie("addParkLabel_parkId", null);
        				window.location="#/seek/sysPark/sysParkDetails/"+$scope.objectId;
    				}else if($routeParams.value=="addPartnerLabel"){
    					$.cookie("addPartnerLabel_partnerId", null);
        				window.location="#/seek/sysPartner/sysPartnerDetails/"+$scope.objectId;
    				}else if($routeParams.value=="addIndustryLabel"){
    					$.cookie("addIndustryLabel_IndustryId", null);
        				window.location="#/seek/sysIndustry/sysIndustryDetails/"+$scope.objectId;
    				}else if($routeParams.value=="addServiceLabel"){
    					$.cookie("addServiceLabel_ServiceId", null);
        				window.location="#/seek/sysService/sysServiceDetails/"+$scope.objectId;
    				}else if($routeParams.value=="addStationLabel"){
    					$.cookie("addStationLabel_StationId", null);
        				window.location="#/seek/sysStation/sysStationDetails/"+$scope.objectId;
    				}else if($routeParams.value=="addPublicLabel"){
    					$.cookie("addPublicLabel_PublicId", null);
        				window.location="#/seek/sysPublic/sysPublicDetails/"+$scope.objectId;
    				}
    			}else{
    				alert("请求失败，请稍后再试");
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
    	
    	$scope.getLabelList();
    };
    return ["sysLabelPageListCtrl", _controller];
});