define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysParkService){
    	//页面滚动坐标初始化(主要针对级联跳转)
		window.scrollTo(0,0);
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysParkPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getParkList();
                    }
                }
        };
    	//请求参数(后台产业园区)
    	$scope.para={
    			parkNameKeywords:"",
    			parkAddrKeywords:"",
    			parkState:2
    	};
    	//列表数据(后台产业园区)
    	$scope.parkList=[];
    	$scope.onePageCount="";
    	
    	//获取列表(后台产业园区)
    	$scope.getParkList=function(){
    		if($routeParams.value=="update"){
    			$scope.para.currentPage = $.cookie("parkList_currentPage");
    			$scope.para.onePageCount = $.cookie("parkList_onePageCount");
    			$scope.para.parkNameKeywords = $.cookie("parkList_parkNameKeywords");
    			$scope.para.parkAddrKeywords = $.cookie("parkList_parkAddrKeywords");
    			$scope.para.parkState = $.cookie("parkList_parkState");
    			$routeParams.value='index';
    		}else{
    			//当前页与每页显示条数
    			$scope.para.currentPage =$scope.paginationConf.currentPage;
    			$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		}
    		// 把当前页和每页总条数放入cookie中
    		$.cookie("parkList_currentPage",$scope.paginationConf.currentPage);
    		$.cookie("parkList_onePageCount",$scope.paginationConf.itemsPerPage);
    		$.cookie("parkList_parkNameKeywords",$scope.para.parkNameKeywords);
    		$.cookie("parkList_parkAddrKeywords",$scope.para.parkAddrKeywords);
    		$.cookie("parkList_parkState",$scope.para.parkState);
    		
    		if($routeParams.value=='index'){
    			sysParkService.sysParkPageListP($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.parkList=data.data.list;
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
    			sysParkService.sysParkPageList($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.parkList=data.data.list;
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
    	if($routeParams.value=="addIndustryPark"){
			$scope.cascade=1;
			$scope.para.parkType=1;
			$scope.para.parkState=2;
			$scope.objectId=$.cookie("addIndustryPark_IndustryId");
		}else if($routeParams.value=="addServicePark"){
			$scope.cascade=1;
			$scope.para.parkType=2;
			$scope.para.parkState=2;
			$scope.objectId=$.cookie("addServicePark_ServiceId");
		}else if($routeParams.value=="addStationPark"){
			$scope.cascade=1;
			$scope.para.parkType=3;
			$scope.para.parkState=2;
			$scope.objectId=$.cookie("addStationPark_StationId");
		}else if($routeParams.value=="addPublicPark"){
			$scope.cascade=1;
			$scope.para.parkType=4;
			$scope.para.parkState=2;
			$scope.objectId=$.cookie("addPublicPark_PublicId");
		}
    	//添加级联标签
    	$scope.addIndustryParkCascade=function(parkId){
    		var para={
    				objId:$scope.objectId,
    				parkId:parkId,
    				parkType:$scope.para.parkType
    		};
    		sysParkService.addIndustryParkCascade($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				if($routeParams.value=="addIndustryPark"){
    					$.cookie("addParkLabel_parkId", null);
        				window.location="#/seek/sysIndustry/sysIndustryDetails/"+$scope.objectId;
    				}else if($routeParams.value=="addServicePark"){
    					$.cookie("addServicePark_ServiceId", null);
        				window.location="#/seek/sysService/sysServiceDetails/"+$scope.objectId;
    				}else if($routeParams.value=="addStationPark"){
    					$.cookie("addStationPark_StationId", null);
        				window.location="#/seek/sysStation/sysStationDetails/"+$scope.objectId;
    				}else if($routeParams.value=="addPublicPark"){
    					$.cookie("addPublicPark_PublicId", null);
        				window.location="#/seek/sysPublic/sysPublicDetails/"+$scope.objectId;
    				}
    			}else if(data.stateCode=="1006"){
    					alert(data.message);
    					$.cookie("addPublicPark_PublicId", null);
    					window.location="#/seek/sysPublic/sysPublicDetails/"+$scope.objectId;
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    		});
    	};
    	
    	$scope.getParkList();
    };
    return ["sysParkPageListCtrl", _controller];
});