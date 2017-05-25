define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysNewsService){
    	
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysNewsPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getNewsList();
                    }
                }
        };
    	//请求参数
    	$scope.para={
    			newAuthor:"",
    			newTitle:"",
    			newsState:2
    	};
    	//列表数据
    	$scope.sysNewsList=[];
    	$scope.onePageCount="";
    	
    	//获取列表
    	$scope.getNewsList=function(){
    		if($routeParams.value=="update"){
    			$scope.para.currentPage = $.cookie("newsList_currentPage");
    			$scope.para.onePageCount = $.cookie("newsList_onePageCount");
    			$scope.para.newAuthor = $.cookie("newsList_newAuthor");
    			$scope.para.newTitle = $.cookie("newsList_newTitle");
    			$scope.para.newsState = $.cookie("newsList_newsState");
    			$routeParams.value='index';
    		}else{
    			//当前页与每页显示条数
    			$scope.para.currentPage =$scope.paginationConf.currentPage;
    			$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		}
    		// 把当前页和每页总条数放入cookie中
    		$.cookie("newsList_currentPage",$scope.paginationConf.currentPage);
    		$.cookie("newsList_onePageCount",$scope.paginationConf.itemsPerPage);
    		$.cookie("newsList_newAuthor",$scope.para.newAuthor);
    		$.cookie("newsList_newTitle",$scope.para.newTitle);
    		$.cookie("newsList_newsState",$scope.para.newsState);
    		
    		sysNewsService.sysNewsPageList($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.sysNewsList=data.data.data.list;
    				// 当前页与每页条数
    				$scope.paginationConf.currentPage=data.data.data.currentPage;
    				$scope.onePageCount=data.data.data.onePageCount;
    				//页数获取
    				$scope.paginationConf.totalItems=data.data.data.countRecord;
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	$scope.getNewsList();
    };
    return ["sysNewsPageListCtrl", _controller];
});