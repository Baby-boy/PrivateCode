define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysIndustryService){
    	//页面滚动坐标初始化(主要针对级联跳转)
		window.scrollTo(0,0);
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysIndustryPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getIndustryList();
                    }
                }
        };
    	//请求参数(后台产业园区)
    	$scope.para={
    			industryTitleKeywords:"",
    			industryState:2
    	};
    	//列表数据(后台产业园区)
    	$scope.industryList=[];
    	$scope.onePageCount="";
    	
    	//获取列表(后台产业园区)
    	$scope.getIndustryList=function(){
    		if($routeParams.value=="update"){
    			$scope.para.currentPage = $.cookie("industryList_currentPage");
    			$scope.para.onePageCount = $.cookie("industryList_onePageCount");
    			$scope.para.industryTitleKeywords= $.cookie("industryList_industryTitleKeywords");
    			$scope.para.industryState= $.cookie("industryList_industryState");
    			$routeParams.value='index';
    		}else{
    			//当前页与每页显示条数
    			$scope.para.currentPage =$scope.paginationConf.currentPage;
    			$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		}
    		// 把当前页和每页总条数放入cookie中
    		$.cookie("industryList_currentPage",$scope.paginationConf.currentPage);
    		$.cookie("industryList_onePageCount",$scope.paginationConf.itemsPerPage);
    		$.cookie("industryList_industryTitleKeywords",$scope.para.industryTitleKeywords);
    		$.cookie("industryList_industryState",$scope.para.industryState);
    		
    		if($routeParams.value=='index'){
    			sysIndustryService.sysIndustryPageListP($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.industryList=data.data.list;
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
    			sysIndustryService.sysIndustryPageList($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.industryList=data.data.list;
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
    	
    	
    	//级联信息操作
    	$scope.cascade=0;
    	$scope.objectId="";
    	if($routeParams.value=="addParkIndustry"){
			$scope.cascade=1;
			$scope.para.industryState=2;
			$scope.para.industryType=1;
			$scope.objectId=$.cookie("addParkIndustry_parkId");
		}else if($routeParams.value=="addStationIndustry"){
			$scope.cascade=1;
			$scope.para.industryState=2;
			$scope.para.industryType=2;
			$scope.objectId=$.cookie("addStationIndustry_parkId");
		}
    	//添加级联
    	$scope.addIndustryCascade=function(indId){
    		var para={
    				objId:$scope.objectId,
    				industryId:indId,
    				industryType:$scope.para.industryType
    		};
    		sysIndustryService.addIndustryCascade($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				if($routeParams.value=="addParkIndustry"){
    					$.cookie("addParkIndustry_parkId", null);
        				window.location="#/seek/sysPark/sysParkDetails/"+$scope.objectId;
    				}else if($routeParams.value=="addStationIndustry"){
    					$.cookie("addStationIndustry_parkId", null);
        				window.location="#/seek/sysStation/sysStationDetails/"+$scope.objectId;
    				}
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	$scope.getIndustryList();
    };
    return ["sysIndustryPageListCtrl", _controller];
});