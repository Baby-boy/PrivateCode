define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysUserService){
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysUserPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getUserList();
                    }
                }
        };
    	//请求参数(APP用户)
    	$scope.para={
    			userAccount:"",
    			userName:""
    	};
    	//列表数据(APP用户)
    	$scope.userList=[];
    	$scope.currentPage="";
    	$scope.onePageCount="";
    	
    	//获取列表(APP用户)
    	$scope.getUserList=function(){
    		//当前页与每页显示条数
    		$scope.para.currentPage =$scope.paginationConf.currentPage;
    		$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		sysUserService.sysUserPagePageList($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.userList=data.data.list;
    				// 当前页与每页条数
    				$scope.currentPage=data.data.currentPage;
    				$scope.onePageCount=data.data.onePageCount;
    				//页数获取
    				$scope.paginationConf.totalItems=data.data.countRecord;
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	$scope.getUserList();
    };
    return ["sysUserPageListCtrl", _controller];
});