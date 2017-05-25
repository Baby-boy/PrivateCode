define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysManagerService){
    	//分页设置 
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysLogPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getManagerList();
                    }
                }
        };
    	
    	//请求参数(后台管理员列表)
    	$scope.para={
    			managerAccount:'',
    			beginTime:'',
    			endTime:''
    	};
    	
    	//获取列表(后台管理员)
    	$scope.getLogList=function(){
    		//当前页与每页显示条数
    		$scope.para.currentPage =$scope.paginationConf.currentPage;
    		$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		sysManagerService.sysLogPagePageList($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.logList=data.data.list;
    				// 当前页与每页条数
    				$scope.paginationConf.currentPage=data.data.currentPage;
    				$scope.onePageCount=data.data.onePageCount;
    				//页数获取
    				$scope.paginationConf.totalItems=data.data.countRecord;
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	$scope.getLogList();
    };
    return ["sysLogPageListCtrl", _controller];
});