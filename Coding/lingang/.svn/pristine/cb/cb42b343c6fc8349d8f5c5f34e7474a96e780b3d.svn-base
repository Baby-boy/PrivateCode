define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,topStationService){
    	
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysTopStationPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getStationList();
                    }
                }
        };
    	//请求参数
    	$scope.para={
    			stationTitle:"",
    			stationState:2
    	};
    	//列表数据
    	$scope.topStationList=[];
    	$scope.onePageCount="";
    	
    	//获取列表
    	$scope.getStationList=function(){
    		if($routeParams.value=="update"){
    			$scope.para.currentPage =$.cookie("topStationList_currentPage");
    			$scope.para.onePageCount = $.cookie("topStationList_onePageCount");
    			$scope.para.stationTitle = $.cookie("topStationList_stationTitle");
    			$scope.para.stationState = $.cookie("topStationList_stationState");
    			$routeParams.value='index';
    		}else{
    			//当前页与每页显示条数
    			$scope.para.currentPage =$scope.paginationConf.currentPage;
    			$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		}
    		// 把当前页和每页总条数放入cookie中
    		$.cookie("topStationList_currentPage",$scope.paginationConf.currentPage);
    		$.cookie("topStationList_onePageCount",$scope.paginationConf.itemsPerPage);
    		$.cookie("topStationList_stationTitle",$scope.para.stationTitle);
    		$.cookie("topStationList_stationState",$scope.para.stationState);
    		
    		topStationService.topStationPageList($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.topStationList=data.data.list;
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
    	/******************************取消置顶*********************************/
    	$scope.delStationTop=function(m){
    		$scope.station={
    				stationId:m.stationId
    		};
    		topStationService.delStationTop($http,$scope.station).then(function(data) {
    			if(data.stateCode=="1000"){
    				layer.msg("已取消！",2,2);
    				$scope.getStationList();
    			}else{
    				layer.msg("取消失败！",2,2);
    				$scope.getStationList();
    			}  
    			
    		});
    	};
    	$scope.getStationList();
    };
    return ["topStationListCtrl", _controller];
});