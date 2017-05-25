define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysPolicyService){
    	
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysPolicyPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getPolicyList();
                    }
                }
        };
    	//请求参数
    	$scope.para={
    			policyTitle:"",
    			// policyIstop:1
    			policyState:2
    	};
    	//列表数据
    	$scope.sysPolicyList=[];
    	$scope.onePageCount="";
    	
    	//获取列表
    	$scope.getPolicyList=function(){
    		if($routeParams.value=="update"){
    			$scope.para.currentPage = $.cookie("policyList_currentPage");
    			$scope.para.onePageCount = $.cookie("policyList_onePageCount");
    			$scope.para.policyTitle = $.cookie("policyList_policyTitle");
    			$scope.para.policyState = $.cookie("policyList_policyState");
    			$routeParams.value='index';
    		}else{
    			//当前页与每页显示条数
    			$scope.para.currentPage =$scope.paginationConf.currentPage;
    			$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		}
    		// 把当前页和每页总条数放入cookie中
    		$.cookie("policyList_currentPage",$scope.paginationConf.currentPage);
    		$.cookie("policyList_onePageCount",$scope.paginationConf.itemsPerPage);
    		$.cookie("policyList_policyTitle",$scope.para.policyTitle);
    		$.cookie("policyList_policyState",$scope.para.policyState);
    		
    		sysPolicyService.sysPolicyPageList($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.sysPolicyList=data.data.list;
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
    	
    	$scope.getPolicyList();
    };
    return ["sysPolicyPageListCtrl", _controller];
});