define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysManagerService){
    	//分页设置 
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysManagerPageListCtrlPerPage',
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
    			managerAccount:"",
    			managerIdcard:"",
    			managerState:""
    	};
    	
    	//列表数据(后台管理员)
    	$scope.managerList=[];
    	$scope.onePageCount="";
    	
    	//获取列表(后台管理员)
    	$scope.getManagerList=function(){
    		//当前页与每页显示条数
    		$scope.para.currentPage =$scope.paginationConf.currentPage;
    		$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		sysManagerService.sysManagerPagePageList($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.managerList=data.data.list;
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
    	
    	
    	
    	//请求参数(后台管理员)
    	$scope.manager={
    	};
    	//显示(后台管理员)
    	$scope.showManagerDetails=function(m){
    		$scope.manager=m;
    		$('#myModal').modal();
    	};
    	
    	//修改(后台管理员)
    	$scope.updateSysManager=function(){
    		/** JS校验 **/
    		// 账户
    		if ($scope.manager.managerAccount == "" || $scope.manager.managerAccount == null) {
    			checkSingle("managerAccount","账户不能为空");
    			return;
			}
    		if ($scope.manager.managerAccount != "" && $scope.manager.managerAccount != null) {
    			var reg = /^[a-zA-Z]\w{2,9}$/;
        		if(!reg.test($scope.manager.managerAccount)){
        			check("managerAccount","账户长度3-10字节,需以字母开头");
        			return;
        		}
			}
    		// 密码
    		if ($scope.manager.managerPassword != "" && $scope.manager.managerPassword != null) {
    			var reg = /^(\w){6,12}$/;
        		if(!reg.test($scope.manager.managerPassword)){
        			checkSingle("managerPassword","密码长度6-12字节");
        			return;
        		}
    		}
    		// 名称
    		if ($scope.manager.managerName == "" || $scope.manager.managerName == null) {
    			checkSingle("managerName","名称不能为空");
    			return;
    		}
    		// 联系方式
    		if ($scope.manager.managerTel == "" || $scope.manager.managerTel == null) {
    			checkSingle("managerTel","联系方式不能为空");
    			return;
    		}
    		if ($scope.manager.managerTel != "" && $scope.manager.managerTel != null) {
    			var reg = /^(^(\d{3,4}-)?\d{7,8})$|(1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\d{8})$/;
    			if(!reg.test($scope.manager.managerTel)){
    				check("managerTel","联系方式格式不正确");
    				return;
    			}
    		}
    		// 身份证号码
    		if ($scope.manager.managerIdcard == "" || $scope.manager.managerIdcard == null) {
    			check("managerIdcard","身份证号码不能为空");
    			return;
    		}
    		if ($scope.manager.managerIdcard != "" && $scope.manager.managerIdcard != null) {
    			var reg = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
        		if(!reg.test($scope.manager.managerIdcard)){
        			check("managerIdcard","身份证号码格式不正确");
        			return;
        		}
    		}
    		// 地址
    		if ($scope.manager.managerAddress == "" || $scope.manager.managerAddress == null) {
    			checkSingle("managerAddress","地址不能为空");
    			return;
    		}
    		// Email
    		if ($scope.manager.managerEmail == "" || $scope.manager.managerEmail == null) {
    			checkSingle("managerEmail","Email不能为空");
    			return;
    		}
    		if ($scope.manager.managerEmail != "" && $scope.manager.managerEmail !== null) {
    			var reg = /^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/;
        		if(!reg.test($scope.manager.managerEmail)){
        			checkSingle("managerEmail","Email格式不正确");
        			return;
        		}
    		}
    		
    		var m={
    				managerId:$scope.manager.managerId,
    				managerAccount:$scope.manager.managerAccount,
    				managerPassword:$scope.manager.managerPassword,
    				managerName:$scope.manager.managerName,
    				managerTel:$scope.manager.managerTel,
    				managerIdcard:$scope.manager.managerIdcard,
    				managerAddress:$scope.manager.managerAddress,
    				managerEmail:$scope.manager.managerEmail,
    				managerState:$scope.manager.managerState
    		};
    		sysManagerService.updateSysManager($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("修改成功");
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    			$('#myModal').modal('hide');
    		});
    	};
    	
    	
    	//请求参数(添加后台管理员)
    	$scope.managerAdd={
    		managerState:1,
    		managerAccount:"",
    		managerPassword:"",
    		managerName:"",
    		managerTel:"",
    		managerIdcard:"",
    		managerAddress:"",
    		managerEmail:""
    	};
    	
    	// 显示弹窗
     	$scope.showManagerAdd = function(){
     		$('#managerAdd').modal();
     	}
     	
    	//添加(后台管理员)
    	$scope.addSysManager=function(){
    		/** JS校验 **/
    		// 账户
    		if ($scope.managerAdd.managerAccount == "") {
    			checkSingle("managerAccountAdd","账户不能为空");
    			return;
			}
    		if ($scope.managerAdd.managerAccount != "") {
    			var reg = /^[a-zA-Z]\w{2,9}$/;
    			if(!reg.test($scope.managerAdd.managerAccount)){
        			check("managerAccountAdd","账户长度3-10字节,需以字母开头");
        			return;
        		}
    		}
    		// 密码
    		if ($scope.managerAdd.managerPassword == "") {
    			checkSingle("managerPasswordAdd","密码不能为空");
    			return;
    		}
    		if ($scope.managerAdd.managerPassword != "") {
    			var reg = /^(\w){6,12}$/;
        		if(!reg.test($scope.managerAdd.managerPassword)){
        			checkSingle("managerPasswordAdd","密码长度6-12字节");
        			return;
        		}
    		}
    		// 名称
    		if ($scope.managerAdd.managerName == "") {
    			checkSingle("managerNameAdd","名称不能为空");
    			return;
    		}
    		// 联系方式
    		if ($scope.managerAdd.managerTel == "") {
    			checkSingle("managerTelAdd","联系方式不能为空");
    			return;
    		}
    		if ($scope.managerAdd.managerTel != "") {
    			var reg = /^(^(\d{3,4}-)?\d{7,8})$|(1(3[0-9]|4[57]|5[0-35-9]|7[0135678]|8[0-9])\d{8})$/;
    			if(!reg.test($scope.managerAdd.managerTel)){
    				check("managerTelAdd","联系方式格式不正确");
    				return;
    			}
    		}
    		// 身份证号码
    		if ($scope.managerAdd.managerIdcard == "") {
    			check("managerIdcardAdd","身份证号码不能为空");
    			return;
    		}
    		if ($scope.managerAdd.managerIdcard != "") {
    			var reg = /^(\d{6})(\d{4})(\d{2})(\d{2})(\d{3})([0-9]|X)$/;
        		if(!reg.test($scope.managerAdd.managerIdcard)){
        			check("managerIdcardAdd","身份证号码格式不正确");
        			return;
        		}
    		}
    		// 地址
    		if ($scope.managerAdd.managerAddress == "") {
    			checkSingle("managerAddressAdd","地址不能为空");
    			return;
    		}
    		// Email
    		if ($scope.managerAdd.managerEmail == "") {
    			checkSingle("managerEmailAdd","Email不能为空");
    			return;
    		}
    		if ($scope.managerAdd.managerEmail != "") {
    			var reg = /^[\w!#$%&'*+/=?^_`{|}~-]+(?:\.[\w!#$%&'*+/=?^_`{|}~-]+)*@(?:[\w](?:[\w-]*[\w])?\.)+[\w](?:[\w-]*[\w])?$/;
        		if(!reg.test($scope.managerAdd.managerEmail)){
        			checkSingle("managerEmailAdd","Email格式不正确");
        			return;
        		}
    		}
    		
    		var m={
    				managerAccount:$scope.managerAdd.managerAccount,
    				managerPassword:$scope.managerAdd.managerPassword,
    				managerName:$scope.managerAdd.managerName,
    				managerTel:$scope.managerAdd.managerTel,
    				managerIdcard:$scope.managerAdd.managerIdcard,
    				managerAddress:$scope.managerAdd.managerAddress,
    				managerEmail:$scope.managerAdd.managerEmail,
    				managerState:$scope.managerAdd.managerState
    		};
    		sysManagerService.addSysManager($http,m).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("添加成功");
    				$scope.getManagerList();
    			}else{
    				alert("请求失败，请稍后再试");
    				$scope.getManagerList();
    			}
    			$('#managerAdd').modal('hide');
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
		
		
    	// 权限弹窗
		$scope.pStr="";
		$scope.pow={
				powerId:null,
				managerId:null,
				powerModularStr:""
		};
     	$scope.showPower = function(man){
     		$scope.pow.powerId=man.powerId;
     		$scope.pow.managerId=man.managerId;
     		$scope.pStr=man.powerModularStr==null?"":man.powerModularStr;
     		$('#powerMan').modal();
     	};
     	//data-id="1"
		$scope.dataAction1=1;
		$scope.dataItemClass1="dd-item";
		$scope.dataCollapseStyle1={
				"display": "block"
		};
		$scope.dataExpandStyle1={
				"display": "none"
		};
		//data-id="7"
		$scope.dataAction7=0;
		$scope.dataItemClass7="dd-item dd-collapsed";
		$scope.dataCollapseStyle7={
				"display": "none"
		};
		$scope.dataExpandStyle7={
				"display": "block"
		};
		//data-id="12"
		$scope.dataAction12=0;
		$scope.dataItemClass12="dd-item dd-collapsed";
		$scope.dataCollapseStyle12={
				"display": "none"
		};
		$scope.dataExpandStyle12={
				"display": "block"
		};
		//data-id="19"
		$scope.dataAction19=0;
		$scope.dataItemClass19="dd-item dd-collapsed";
		$scope.dataCollapseStyle19={
				"display": "none"
		};
		$scope.dataExpandStyle19={
				"display": "block"
		};
		//data-id="21"
		$scope.dataAction21=0;
		$scope.dataItemClass21="dd-item dd-collapsed";
		$scope.dataCollapseStyle21={
				"display": "none"
		};
		$scope.dataExpandStyle21={
				"display": "block"
		};
		//data-id="24"
		$scope.dataAction24=0;
		$scope.dataItemClass24="dd-item dd-collapsed";
		$scope.dataCollapseStyle24={
				"display": "none"
		};
		$scope.dataExpandStyle24={
				"display": "block"
		};
		//data-id="26"
		$scope.dataAction26=0;
		$scope.dataItemClass26="dd-item dd-collapsed";
		$scope.dataCollapseStyle26={
				"display": "none"
		};
		$scope.dataExpandStyle26={
				"display": "block"
		};
		$scope.dataActionShow=function(index){
			if(index==1){
				if($scope.dataAction1==1){
					$scope.dataAction1=0;
					$scope.dataItemClass1="dd-item dd-collapsed";
					$scope.dataCollapseStyle1={
							"display": "none"
					};
					$scope.dataExpandStyle1={
							"display": "block"
					};
				}else{
					$scope.dataAction1=1;
					$scope.dataItemClass1="dd-item";
					$scope.dataCollapseStyle1={
							"display": "block"
					};
					$scope.dataExpandStyle1={
							"display": "none"
					};
				}
			}else if(index==7){
				if($scope.dataAction7==1){
					$scope.dataAction7=0;
					$scope.dataItemClass7="dd-item dd-collapsed";
					$scope.dataCollapseStyle7={
							"display": "none"
					};
					$scope.dataExpandStyle7={
							"display": "block"
					};
				}else{
					$scope.dataAction7=1;
					$scope.dataItemClass7="dd-item";
					$scope.dataCollapseStyle7={
							"display": "block"
					};
					$scope.dataExpandStyle7={
							"display": "none"
					};
				}
			}else if(index==12){
				if($scope.dataAction12==1){
					$scope.dataAction12=0;
					$scope.dataItemClass12="dd-item dd-collapsed";
					$scope.dataCollapseStyle12={
							"display": "none"
					};
					$scope.dataExpandStyle12={
							"display": "block"
					};
				}else{
					$scope.dataAction12=1;
					$scope.dataItemClass12="dd-item";
					$scope.dataCollapseStyle12={
							"display": "block"
					};
					$scope.dataExpandStyle12={
							"display": "none"
					};
				}
			}else if(index==19){
				if($scope.dataAction19==1){
					$scope.dataAction19=0;
					$scope.dataItemClass19="dd-item dd-collapsed";
					$scope.dataCollapseStyle19={
							"display": "none"
					};
					$scope.dataExpandStyle19={
							"display": "block"
					};
				}else{
					$scope.dataAction19=1;
					$scope.dataItemClass19="dd-item";
					$scope.dataCollapseStyle19={
							"display": "block"
					};
					$scope.dataExpandStyle19={
							"display": "none"
					};
				}
			}else if(index==21){
				if($scope.dataAction21==1){
					$scope.dataAction21=0;
					$scope.dataItemClass21="dd-item dd-collapsed";
					$scope.dataCollapseStyle21={
							"display": "none"
					};
					$scope.dataExpandStyle21={
							"display": "block"
					};
				}else{
					$scope.dataAction21=1;
					$scope.dataItemClass21="dd-item";
					$scope.dataCollapseStyle21={
							"display": "block"
					};
					$scope.dataExpandStyle21={
							"display": "none"
					};
				}
			}else if(index==24){
				if($scope.dataAction24==1){
					$scope.dataAction24=0;
					$scope.dataItemClass24="dd-item dd-collapsed";
					$scope.dataCollapseStyle24={
							"display": "none"
					};
					$scope.dataExpandStyle24={
							"display": "block"
					};
				}else{
					$scope.dataAction24=1;
					$scope.dataItemClass24="dd-item";
					$scope.dataCollapseStyle24={
							"display": "block"
					};
					$scope.dataExpandStyle24={
							"display": "none"
					};
				}
			}else if(index==26){
				if($scope.dataAction26==1){
					$scope.dataAction26=0;
					$scope.dataItemClass26="dd-item dd-collapsed";
					$scope.dataCollapseStyle26={
							"display": "none"
					};
					$scope.dataExpandStyle26={
							"display": "block"
					};
				}else{
					$scope.dataAction26=1;
					$scope.dataItemClass26="dd-item";
					$scope.dataCollapseStyle26={
							"display": "block"
					};
					$scope.dataExpandStyle26={
							"display": "none"
					};
				}
			}
		};
		$scope.powerAdd=function(str,farStr){
			if($scope.pStr.indexOf(str)>=0){
				$scope.pStr=$scope.pStr.replace(str+",","");
				
				//父节点选取
				if(str=="/1/"){
					$scope.pStr=$scope.pStr.replace("/2/,","").replace("/3/,","").replace("/4/,","")
											.replace("/27/,","").replace("/5/,","");
					return ;
				}else if(str=="/6/"){
					$scope.pStr=$scope.pStr.replace("/7/,","").replace("/8/,","").replace("/9/,","")
											.replace("/10/,","");
					return ;
				}else if(str=="/11/"){
					$scope.pStr=$scope.pStr.replace("/12/,","").replace("/13/,","").replace("/14/,","")
											.replace("/15/,","").replace("/16/,","").replace("/17/,","");
					return ;
				}else if(str=="/18/"){
					$scope.pStr=$scope.pStr.replace("/19/,","");
					return ;
				}else if(str=="/20/"){
					$scope.pStr=$scope.pStr.replace("/21/,","").replace("/22/,","");
					return ;
				}else if(str=="/23/"){
					$scope.pStr=$scope.pStr.replace("/24/,","");
					return ;
				}else if(str=="/25/"){
					$scope.pStr=$scope.pStr.replace("/26/,","");
					return ;
				}
				
				//删除最后一个父节点
				if(farStr=="/1/" 
					&& $scope.pStr.indexOf("/2/")<0
					&& $scope.pStr.indexOf("/3/")<0
					&& $scope.pStr.indexOf("/4/")<0
					&& $scope.pStr.indexOf("/27/")<0
					&& $scope.pStr.indexOf("/5/")<0){
					$scope.pStr=$scope.pStr.replace(farStr+",","");
				}else if(farStr=="/6/" 
					&& $scope.pStr.indexOf("/7/")<0
					&& $scope.pStr.indexOf("/8/")<0
					&& $scope.pStr.indexOf("/9/")<0
					&& $scope.pStr.indexOf("/10/")<0){
					$scope.pStr=$scope.pStr.replace(farStr+",","");
				}else if(farStr=="/11/" 
					&& $scope.pStr.indexOf("/12/")<0
					&& $scope.pStr.indexOf("/13/")<0
					&& $scope.pStr.indexOf("/14/")<0
					&& $scope.pStr.indexOf("/15/")<0
					&& $scope.pStr.indexOf("/16/")<0
					&& $scope.pStr.indexOf("/17/")<0){
					$scope.pStr=$scope.pStr.replace(farStr+",","");
				}else if(farStr=="/18/" 
					&& $scope.pStr.indexOf("/19/")<0){
					$scope.pStr=$scope.pStr.replace(farStr+",","");
				}else if(farStr=="/20/" 
					&& $scope.pStr.indexOf("/21/")<0
					&& $scope.pStr.indexOf("/22/")<0){
					$scope.pStr=$scope.pStr.replace(farStr+",","");
				}else if(farStr=="/23/" 
					&& $scope.pStr.indexOf("/24/")<0){
					$scope.pStr=$scope.pStr.replace(farStr+",","");
				}else if(farStr=="/25/" 
					&& $scope.pStr.indexOf("/26/")<0){
					$scope.pStr=$scope.pStr.replace(farStr+",","");
				}
			}else{
				$scope.pStr=$scope.pStr+str+",";
				
				//父节点选取
				if(str=="/1/"){
					$scope.pStr=$scope.pStr.replace("/2/,","").replace("/3/,","").replace("/4/,","")
											.replace("/27/,","").replace("/5/,","");
					$scope.pStr=$scope.pStr+"/2/,"+"/3/,"+"/4/,"+"/27/,"+"/5/,";
					return ;
				}else if(str=="/6/"){
					$scope.pStr=$scope.pStr.replace("/7/,","").replace("/8/,","").replace("/9/,","")
											.replace("/10/,","");
					$scope.pStr=$scope.pStr+"/7/,"+"/8/,"+"/9/,"+"/10/,";
					return ;
				}else if(str=="/11/"){
					$scope.pStr=$scope.pStr.replace("/12/,","").replace("/13/,","").replace("/14/,","")
											.replace("/15/,","").replace("/16/,","").replace("/17/,","");
					$scope.pStr=$scope.pStr+"/12/,"+"/13/,"+"/14/,"+"/15/,"+"/16/,"+"/17/,";
					return ;
				}else if(str=="/18/"){
					$scope.pStr=$scope.pStr.replace("/19/,","");
					$scope.pStr=$scope.pStr+"/19/,";
					return ;
				}else if(str=="/20/"){
					$scope.pStr=$scope.pStr.replace("/21/,","").replace("/22/,","");
					$scope.pStr=$scope.pStr+"/21/,"+"/22/,";
					return ;
				}else if(str=="/23/"){
					$scope.pStr=$scope.pStr.replace("/24/,","");
					$scope.pStr=$scope.pStr+"/24/,";
					return ;
				}else if(str=="/25/"){
					$scope.pStr=$scope.pStr.replace("/26/,","");
					$scope.pStr=$scope.pStr+"/26/,";
					return ;
				}
				
				//添加第一个父节点
				if($scope.pStr.indexOf(farStr)<0){
					$scope.pStr=$scope.pStr+farStr+",";
				}
			}
			
		};
		$scope.powerUpe=function(){
			$scope.pow.powerModularStr=$scope.pStr;
    		sysManagerService.updatePower($http,$scope.pow).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("修改成功");
    				$scope.getManagerList();
    			}else if(data.stateCode=="1010"){
    				alert("修改成功");
    				alert(data.message);
    				$.cookie("p","");
                	$.cookie("redirectUrlPlat","");
                	window.location = "login.html";
    			}else{
    				alert("请求失败，请稍后再试");
    			}
    			$('#powerMan').modal('hide');
    		});
		};
		
    	$scope.getManagerList();
    };
    return ["sysManagerPageListCtrl", _controller];
});