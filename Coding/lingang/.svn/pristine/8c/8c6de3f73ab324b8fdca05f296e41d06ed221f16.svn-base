define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysCompanyService){
    	//页面滚动坐标初始化(主要针对级联跳转)
		window.scrollTo(0,0);
    	//分页设置 
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysCompanyPageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getCompanyList();
                    }
                }
        };
    	
    	//请求参数(公司列表)
    	$scope.para={
    			companyName:""
    	};
    	
    	//列表数据(公司)
    	$scope.companyList=[];
    	$scope.onePageCount="";
    	
    	//获取列表(公司)
    	$scope.getCompanyList=function(){
    		//当前页与每页显示条数
    		$scope.para.currentPage =$scope.paginationConf.currentPage;
    		$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		if($routeParams.value=='index'){
    			sysCompanyService.selectCompanyPageListP($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.companyList=data.data.list;
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
    			sysCompanyService.selectCompanyPageList($http,$scope.para).then(function(data) {
        			if(data.stateCode=="1000"){
        				$scope.companyList=data.data.list;
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
    	if($routeParams.value=="addPartnerCompany"){
			$scope.cascade=1;
			$scope.para.assortType=2;
			$scope.objectId=$.cookie("addPartnerCompany_partnerId");
		}else if($routeParams.value=="addServiceCompany"){
			$scope.cascade=1;
			$scope.para.assortType=3;
			$scope.objectId=$.cookie("addServiceCompany_serviceId");
		}else if($routeParams.value=="addStationCompany"){
			$scope.cascade=1;
			$scope.para.assortType=4;
			$scope.objectId=$.cookie("addStationCompany_stationId");
		}else if($routeParams.value=="addPublicCompany"){
			$scope.cascade=1;
			$scope.para.assortType=5;
			$scope.objectId=$.cookie("addPublicCompany_publicId");
		}
    	
    	$scope.addCompanyCascade=function(comId){
    		var para={
    				objId:$scope.objectId,
    				companyId:comId,
    				assortType:$scope.para.assortType
    		};
    		sysCompanyService.addCompanyCascade($http,para).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    			}else if(data.message !=null && data.message !=""){
    				alert(data.message);
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    			//跳转处理
				if($routeParams.value=="addPartnerCompany"){
					$.cookie("addPartnerCompany_partnerId", null);
					window.location="#/seek/sysPartner/sysPartnerDetails/"+$scope.objectId;
				}else if($routeParams.value=="addServiceCompany"){
					$.cookie("addServiceCompany_serviceId", null);
					window.location="#/seek/sysService/sysServiceDetails/"+$scope.objectId;
				}else if($routeParams.value=="addStationCompany"){
					$.cookie("addStationCompany_stationId", null);
					window.location="#/seek/sysStation/sysStationDetails/"+$scope.objectId;
				}else if($routeParams.value=="addPublicCompany"){
					$.cookie("addPublicCompany_publicId", null);
					window.location="#/seek/sysPublic/sysPublicDetails/"+$scope.objectId;
				}
    		});
    	};
    	
    	
    	//请求参数(公司)
    	$scope.company={};
    	//显示(公司)
    	$scope.showCompanyDetails=function(m){
    		$scope.company=m;
    		$('#companyUpdate').modal();
    	};
    	
    	//修改(公司)
    	$scope.updateSysCompany=function(){
    		/** JS校验 **/
    		// 名称
    		if ($scope.company.companyName == "" || $scope.company.companyName == null) {
    			checkSingle("companyName","公司名称不能为空");
    			return;
			}
    		
    		var m={
    				companyId:$scope.company.companyId,
    				companyName:$scope.company.companyName
    		};
    		sysCompanyService.updateCompanyDetails($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("修改成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    			$('#companyUpdate').modal('hide');
    		});
    	};
    	
    	
    	//请求参数(添加公司)
    	$scope.companyAdd={
    		companyName:""
    	};
    	
    	// 显示弹窗
     	$scope.showCompanyAdd = function(){
     		$('#companyAdd').modal();
     	}
     	
    	//添加(公司)
    	$scope.addSysCompany=function(){
    		/** JS校验 **/
    		// 名称
    		if ($scope.companyAdd.companyName == "" || $scope.companyAdd.companyName == null) {
    			checkSingle("companyNameAdd","公司名称不能为空");
    			return;
			}
    		var m={
    				companyName:$scope.companyAdd.companyName
    		};
    		sysCompanyService.addCompanyDetails($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				$scope.getCompanyList();
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    			$('#companyAdd').modal('hide');
    		});
    	};
    	
    	// JS校验的方法
		var checkSingle = function(id,msg){
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
		var check = function(id,msg){
			$("#" + id).tips({
				side : 2,// 
				color:'#F00',// 字体颜色
				msg : msg,// 提示信息
				bg : '#FFF',// 背景颜色
				time : 2,// 显示时间
				x : 0,//横向偏移
				y : 2//纵向偏移 
			});
			$("#" + id).focus();
		};
    	
    	$scope.getCompanyList();
    };
    return ["sysCompanyPageListCtrl", _controller];
});