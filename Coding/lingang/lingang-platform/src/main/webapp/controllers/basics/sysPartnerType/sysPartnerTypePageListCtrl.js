define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysPartnerTypeService){
    	//分页设置 
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysPartnerTypePageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getPartnerTypeList();
                    }
                }
        };
    	
    	//请求参数(合作类型列表)
    	$scope.para={
    			typeName:""
    	};
    	
    	//列表数据(合作类型)
    	$scope.partnerTypeList=[];
    	$scope.onePageCount="";
    	
    	//获取列表(合作类型)
    	$scope.getPartnerTypeList=function(){
    		//当前页与每页显示条数
    		$scope.para.currentPage =$scope.paginationConf.currentPage;
    		$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		if($routeParams.value=='index'){
    			sysPartnerTypeService.selectPartnerTypePageListP($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.partnerTypeList=data.data.list;
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
    			sysPartnerTypeService.selectPartnerTypePageList($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.partnerTypeList=data.data.list;
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
    	    	
    	//请求参数(合作类型)
    	$scope.partnerType={};
    	//显示(合作类型)
    	$scope.showPartnerTypeDetails=function(m){
    		$scope.partnerType=m;
    		$('#partnerTypeUpdate').modal();
    	};
    	
    	//修改(合作类型)
    	$scope.updateSysPartnerType=function(){
    		/** JS校验 **/
    		// 名称
    		if ($scope.partnerType.typeName == "" || $scope.partnerType.typeName == null) {
    			checkSingle("typeName","类型名称不能为空");
    			return;
			}
    		
    		var m={
    				typeId:$scope.partnerType.typeId,
    				typeName:$scope.partnerType.typeName
    		};
    		sysPartnerTypeService.updatePartnerTypeDetails($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("修改成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    			$('#partnerTypeUpdate').modal('hide');
    		});
    	};
    	
    	
    	//请求参数(添加合作类型)
    	$scope.partnerTypeAdd={
    		typeName:""
    	};
    	
    	// 显示弹窗
     	$scope.showPartnerTypeAdd = function(){
     		$('#partnerTypeAdd').modal();
     	}
     	
    	//添加(合作类型)
    	$scope.addSysPartnerType=function(){
    		/** JS校验 **/
    		// 名称
    		if ($scope.partnerTypeAdd.typeName == "" || $scope.partnerTypeAdd.typeName == null) {
    			checkSingle("typeNameAdd","类型名称不能为空");
    			return;
			}
    		var m={
    				typeName:$scope.partnerTypeAdd.typeName
    		};
    		sysPartnerTypeService.addPartnerTypeDetails($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				$scope.getPartnerTypeList();
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    			$('#partnerTypeAdd').modal('hide');
    		});
    	};
    	
    	// JS校验的方法
		var checkSingle = function(id,msg){
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
	
    	$scope.getPartnerTypeList();
    };
    return ["sysPartnerTypePageListCtrl", _controller];
});