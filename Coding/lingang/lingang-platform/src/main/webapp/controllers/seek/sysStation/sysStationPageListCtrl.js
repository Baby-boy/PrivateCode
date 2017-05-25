define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysStationService){
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysStationPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getStationList();
                    }
                }
        };
    	//请求参数(后台产业园区)
    	$scope.para={
    			stationTitleKeywords:"",
    			regionNameKeywords:"",
    			stationState:2
    	};
    	//列表数据(后台产业园区)
    	$scope.stationList=[];
    	$scope.onePageCount="";
    	
    	//获取列表(后台产业园区)
    	$scope.getStationList=function(){
    		if($routeParams.value=="update"){
    			$scope.para.currentPage =$.cookie("stationList_currentPage");
    			$scope.para.onePageCount = $.cookie("stationList_onePageCount");
    			$scope.para.stationTitleKeywords = $.cookie("stationList_stationTitleKeywords");
    			$scope.para.regionNameKeywords = $.cookie("stationList_regionNameKeywords");
    			$scope.para.stationState = $.cookie("stationList_stationState");
    			$routeParams.value='index';
    		}else{
    			//当前页与每页显示条数
    			$scope.para.currentPage =$scope.paginationConf.currentPage;
    			$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		}
    		// 把当前页和每页总条数放入cookie中
    		$.cookie("stationList_currentPage",$scope.paginationConf.currentPage);
    		$.cookie("stationList_onePageCount",$scope.paginationConf.itemsPerPage);
    		$.cookie("stationList_stationTitleKeywords",$scope.para.stationTitleKeywords);
    		$.cookie("stationList_regionNameKeywords",$scope.para.regionNameKeywords);
    		$.cookie("stationList_stationState",$scope.para.stationState);
    		
    		if($routeParams.value=='index'){
    			sysStationService.sysStationPageListP($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.stationList=data.data.list;
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
    			sysStationService.sysStationPageList($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.stationList=data.data.list;
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
    	/******************************置顶*********************************/
    	$scope.stationTop=function(m){
    		$.layer({
	  			shade: [0.5, '#000'],
	  			area: ['auto','auto'],
	  			dialog: {
	  		        msg: '您确定要置顶吗？',
	  		        btns: 2,         
	  		        type: 4,
	  		        btn: ['确定','取消'],
	  		        yes: function(){
	  		        	$scope.station={
	  		    				stationId:m.stationId,
	  		    		};
	  		    		sysStationService.stationTop($http,$scope.station).then(function(data) {
	  		    			if(data.stateCode=="1000"){
	  		    				layer.msg("已置顶！",1,1);
//	  		    				$scope.getStationList();
	  		    			}else{
	  		    				if(data.message !=""){
	  		    					layer.msg(data.message,1,2);
	  		    				}else{
	  		    					layer.msg("置顶失败！",1,2);
//		  		    				$scope.getStationList();
	  		    				}
	  		    			}  
	  		    			
	  		    		});
	  		        }, no: function(){
	  		            layer.msg('已取消', 1, 2);
	  		        }
	  		    }
	  		});
    	};
    	
    	$scope.getStationList();
    };
    return ["sysStationPageListCtrl", _controller];
});