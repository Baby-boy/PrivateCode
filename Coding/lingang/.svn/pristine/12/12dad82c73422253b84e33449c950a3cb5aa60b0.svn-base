define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysBasicsService){
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: '123',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	$scope.getBasicsTypePageList();
                    }		  
                }
        };
    	//请求参数(后台基础设置列表)
    	$scope.para={
    			basicsType:"",
    			basicsState:2
    	};
    	//列表数据(后台基础设置)
    	$scope.basicsList=[];
    	$scope.onePageCount="";
    	
    	//获取列表(基础设置)
    	$scope.getBasicsTypePageList=function(){
    		//当前页与每页显示条数
    		$scope.para.currentPage =$scope.paginationConf.currentPage;
    		$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		if($routeParams.value=='index'){
        		sysBasicsService.sysBasicsPagePageListP($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.basicsList=data.data.list;
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
        		sysBasicsService.sysBasicsPagePageList($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.basicsList=data.data.list;
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
    	
    	//显示(基础设置)
    	$scope.showBasicsDetails=function(m){
    		$scope.basics=m;
    		$('#myModalBasics').modal();
    	};
    	
    	//修改(基础设置)
    	$scope.updateSysBasics=function(){
    		/** JS校验 */
    		if ($scope.basics.basicsName == "") {
    			check("basicsName","类型名称不能为空")
    			return;
			}
    		
    		var m={
    				basicsId:$scope.basics.basicsId,
    				basicsType:$scope.basics.basicsType,
    				basicsName:$scope.basics.basicsName,
    				basicsState:$scope.basics.basicsState
    				
    		};
    		sysBasicsService.updateSysBasics($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("修改成功");
    				$scope.getBasicsTypePageList();
    			}else{
    				alert("请求失败，请稍后再试");
    				$scope.getBasicsTypePageList();
    			}
    			$('#myModalBasics').modal('hide');
    		});
    	};
    	
    	
    	$scope.basicsAdd={
    		basicsState:2,
    		basicsName:"",
    		basicsType:""
    	};
    	//添加(基础设置)
    	$scope.basicBasicsAdd=function(){
    		$('#myModalBasicsAdd').modal();
    	};
    	
    	//添加(基础设置)
    	$scope.sysBasicsAdd=function(){
    		/** JS校验 */
    		if ($scope.basicsAdd.basicsName == "") {
    			check("basicsNameAdd","类型名称不能为空")
    			return;
			}
    		if ($scope.basicsAdd.basicsType == "" || $scope.basicsAdd.basicsType == null) {
    			check("basicsTypeAdd","请选择基本类型")
    			return;
    		}
    		
    		var m={
    				basicsId:$scope.basicsAdd.basicsId,
    				basicsType:$scope.basicsAdd.basicsType,
    				basicsName:$scope.basicsAdd.basicsName,
    				basicsState:$scope.basicsAdd.basicsState
    				
    		};
    		sysBasicsService.sysBasicsAdd($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				$scope.getBasicsTypePageList();
    			}else{
    				alert("请求失败，请稍后再试");
    				$scope.getBasicsTypePageList();
    			}
    			$('#myModalBasicsAdd').modal('hide');
    		});
    	};
    	
      	// JS校验的方法
		var check = function(id,msg){
			$("#" + id).tips({
				side : 2,// 
				color:'#F00',// 字体颜色
				msg : msg,// 提示信息
				bg : '#FFF',// 背景颜色
				time : 2,// 显示时间
				x : 10,//横向偏移
				y : 2//纵向偏移 
			});
			$("#" + id).focus();
		};
    	
    	$scope.getBasicsTypePageList();
    };
    return ["sysBasicsPageListCtrl", _controller];
});