define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysServiceService){
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysServicePageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getServiceList();
                    }
                }
        };
    	//请求参数(后台产业园区)
    	$scope.para={
    			serviceNameKeywords:"",
    			regionNameKeywords:"",
    			serviceTeamKeywords:"",
    			serviceState:2
    	};
    	//列表数据(后台产业园区)
    	$scope.serviceList=[];
    	$scope.onePageCount="";
    	
    	//获取列表(后台产业园区)
    	$scope.getServiceList=function(){
    		if($routeParams.value=="update"){
    			$scope.para.currentPage =$.cookie("serviceList_currentPage");
    			$scope.para.onePageCount = $.cookie("serviceList_onePageCount");
    			$scope.para.serviceNameKeywords = $.cookie("serviceList_serviceNameKeywords");
    			$scope.para.regionNameKeywords = $.cookie("serviceList_regionNameKeywords");
    			$scope.para.serviceTeamKeywords = $.cookie("serviceList_serviceTeamKeywords");
    			$scope.para.serviceState = $.cookie("serviceList_serviceState");
    			$routeParams.value='index';
    		}else{
    			//当前页与每页显示条数
    			$scope.para.currentPage =$scope.paginationConf.currentPage;
    			$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		}
    		// 把当前页和每页总条数放入cookie中
    		$.cookie("serviceList_currentPage",$scope.paginationConf.currentPage);
    		$.cookie("serviceList_onePageCount",$scope.paginationConf.itemsPerPage);
    		$.cookie("serviceList_serviceNameKeywords",$scope.para.serviceNameKeywords);
    		$.cookie("serviceList_regionNameKeywords",$scope.para.regionNameKeywords);
    		$.cookie("serviceList_serviceTeamKeywords",$scope.para.serviceTeamKeywords);
    		$.cookie("serviceList_serviceState",$scope.para.serviceState);
    		
    		if($routeParams.value=='index'){
    			sysServiceService.sysServicePageListP($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.serviceList=data.data.list;
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
    			sysServiceService.sysServicePageList($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.serviceList=data.data.list;
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
    	
    	$scope.getServiceList();
    };
    return ["sysServicePageListCtrl", _controller];
});