define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
	var _controller = function($scope, $http, $routeParams, $location,sysRegionService){
    	//分页设置 
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysRegionPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getRegionList();
                    }
                }
        };
    	//请求参数
    	$scope.para={
    		regionType:1	
    	};
    	//列表数据
    	$scope.regionList=[];
    	$scope.onePageCount="";
    	
    	//获取列表
    	$scope.getRegionList=function(){
    		//当前页与每页显示条数
    		$scope.para.currentPage =$scope.paginationConf.currentPage;
    		$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		
    		if($routeParams.value=='index'){
    			sysRegionService.sysRegionPagePageListP($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.regionList=data.data.list;
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
    			sysRegionService.sysRegionPagePageList($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.regionList=data.data.list;
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
    	}
    	
    	//显示(地区详情)
    	$scope.showRegionDetails=function(m){
    		$scope.region=m;
    		$('#myModalRegion').modal();
    	};
    	
    	//修改(地区详情)
    	$scope.updateSysRegion=function(){
    		/** JS校验 */
    		if ($scope.region.regionName == "") {
    			check("regionName","地区名称不能为空")
    			return;
			}
    		
    		var m={
    				regionId:$scope.region.regionId,
    				regionName:$scope.region.regionName,
    				regionType:$scope.region.regionType
    				
    		};
    		sysRegionService.updateSysRegion($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("修改成功");
    				$scope.getRegionList();
    			}else{
    				alert("请求失败，请稍后再试");
    				$scope.getRegionList();
    			}
    			$('#myModalRegion').modal('hide');
    		});
    	};
    	
    	$scope.regionAdd={
    		regionType:1,
    		regionName:""
    	};
    	//添加(地区)
    	$scope.basicRegionAdd=function(){
    		$('#myModalRegionAdd').modal();
    	};
    	
    	//添加(地区)
    	$scope.sysRegionAdd=function(){
    		/** JS校验 */
    		if ($scope.regionAdd.regionName == "") {
    			check("regionNameAdd","地区名称不能为空")
    			return;
			}
    		
    		var m={
    				regionId:$scope.regionAdd.regionId,
    				regionName:$scope.regionAdd.regionName,
    				regionType:$scope.regionAdd.regionType
    				
    		};
    		sysRegionService.sysRegionAdd($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				$scope.getRegionList();
    			}else{
    				alert("请求失败，请稍后再试");
    				$scope.getRegionList();
    			}
    			$('#myModalRegionAdd').modal('hide');
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
    	
    	$scope.getRegionList();
    };
    return ["sysRegionPageListCtrl", _controller];
    
});