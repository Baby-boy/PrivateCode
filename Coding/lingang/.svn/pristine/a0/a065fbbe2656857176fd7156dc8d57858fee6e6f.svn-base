define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,topPartnerService){
    	
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'topPartnerPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getPartnerList();
                    }
                }
        };
    	//请求参数
    	$scope.para={
    			partnerName:"",
    			partnerState:2
    	};
    	//列表数据
    	$scope.topPartnerList=[];
    	$scope.onePageCount="";
    	
    	//获取列表
    	$scope.getPartnerList=function(){
    		if($routeParams.value=="update"){
    			$scope.para.currentPage = $.cookie("topPartnerList_currentPage");
    			$scope.para.onePageCount = $.cookie("topPartnerList_onePageCount");
    			$scope.para.partnerName = $.cookie("topPartnerList_partnerName");
    			$scope.para.partnerState = $.cookie("topPartnerList_partnerState");
    			$routeParams.value='index';
    		}else{
    			//当前页与每页显示条数
    			$scope.para.currentPage =$scope.paginationConf.currentPage;
    			$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		}
    		// 把当前页和每页总条数放入cookie中
    		$.cookie("topPartnerList_currentPage",$scope.paginationConf.currentPage);
    		$.cookie("topPartnerList_onePageCount",$scope.paginationConf.itemsPerPage);
    		$.cookie("topPartnerList_partnerName",$scope.para.partnerName);
    		$.cookie("topPartnerList_partnerState",$scope.para.partnerState);
    		
    		topPartnerService.topPartnerPageList($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.topPartnerList=data.data.data.list;
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
    	/******************************取消置顶*********************************/
    	$scope.delPartenrTop=function(m){
    		$scope.partner={
    				partnerId:m.partnerId
    		};
    		topPartnerService.delPartenrTop($http,$scope.partner).then(function(data) {
    			if(data.stateCode=="1000"){
    				layer.msg("已取消！",2,2);
    				$scope.getPartnerList();
    			}else{
    				layer.msg("取消失败！",2,2);
    				$scope.getPartnerList();
    			}  
    			
    		});
    	};

    	$scope.getPartnerList();
    };
    return ["toPartnerPageListCtrl", _controller];
});