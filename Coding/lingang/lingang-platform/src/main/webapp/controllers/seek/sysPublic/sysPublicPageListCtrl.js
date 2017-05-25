define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysPublicService){
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysPublicPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getPublicList();
                    }
                }
        };
    	//请求参数(后台产业园区)
    	$scope.para={
    			publicTitleKeywords:"",
    			regionNameKeywords:"",
    			parkNameKeywords:"",
    			publicState:2
    	};
    	//列表数据(后台产业园区)
    	$scope.publicList=[];
    	$scope.onePageCount="";
    	
    	//获取列表(后台产业园区)
    	$scope.getPublicList=function(){
    		if($routeParams.value=="update"){
    			$scope.para.currentPage =$.cookie("publicList_currentPage");
    			$scope.para.onePageCount = $.cookie("publicList_onePageCount");
    			$scope.para.publicTitleKeywords = $.cookie("publicList_publicTitleKeywords");
    			$scope.para.regionNameKeywords = $.cookie("publicList_regionNameKeywords");
    			$scope.para.parkNameKeywords = $.cookie("publicList_parkNameKeywords");
    			$scope.para.publicState = $.cookie("publicList_publicState");
    			$routeParams.value='index';
    		}else{
    			//当前页与每页显示条数
    			$scope.para.currentPage =$scope.paginationConf.currentPage;
    			$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		}
    		// 把当前页和每页总条数放入cookie中
    		$.cookie("publicList_currentPage",$scope.paginationConf.currentPage);
    		$.cookie("publicList_onePageCount",$scope.paginationConf.itemsPerPage);
    		$.cookie("publicList_publicTitleKeywords",$scope.para.publicTitleKeywords);
    		$.cookie("publicList_regionNameKeywords",$scope.para.regionNameKeywords);
    		$.cookie("publicList_parkNameKeywords",$scope.para.parkNameKeywords);
    		$.cookie("publicList_publicState",$scope.para.publicState);
    		
    		if($routeParams.value=='index'){
    			sysPublicService.sysPublicPageListP($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.publicList=data.data.list;
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
    			sysPublicService.sysPublicPageList($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.publicList=data.data.list;
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
    	
    	$scope.getPublicList();
    };
    return ["sysPublicPageListCtrl", _controller];
});