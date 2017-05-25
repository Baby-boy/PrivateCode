define(['mySession', 'route-config', 'serviceBase','layer','My97DatePicker'], function (mySession, routeConfig, serviceBase,My97DatePicker) {
    var _controller = function($scope, $http, $routeParams, $location,sysMessageService){
    	//分页设置
    	$scope.paginationConf = {
        	    currentPage: 1, //页数
                totalItems: 0, //查询结果总数
                perPageOptions: [5, 10, 15, 20, 30, 50],
                itemsPerPage: 15, //每页查询条数
                rememberPerPage: 'sysMessagePageListCtrlPerPage',
                autoLoadData: true,
                onChange: function () {
                    if (this.currentPage > 0) {
                    	//调用方法
                    	$scope.getMessageList();
                    }
                }
        };
    	/*************************信息纠错列表***************************/
    	//请求参数(信息纠错列表)
    	$scope.para={
    			messageType:1,
    			linkType:"",
    			messageState:1
    	};
    	$scope.showMessageContent=function(m){
    		$scope.message=m;
    		$('#myModal').modal();
    	};
    	$scope.messageReply=function(m){
    		$scope.message=m;
    		$('#myReply').modal();
    	};
    	//列表数据(信息纠错列表)用来页面遍历数据
    	$scope.messageList=[];
    	$scope.onePageCount="";
    	
    	//获取列表(信息纠错列表)
    	$scope.getMessageList=function(){
    		//当前页与每页显示条数
    		$scope.para.currentPage =$scope.paginationConf.currentPage;
    		$scope.para.onePageCount = $scope.paginationConf.itemsPerPage;
    		//对应上面传过来的sysMessageService
    		sysMessageService.sysMessagePagePageListE($http,$scope.para).then(function(data) {
    			if(data.stateCode=="1000"){
    				$scope.messageList=data.data.list;
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
    	/*************************回复***************************/
    	$scope.sysMessageReply=function(){
    		$scope.message={
    				messageId:$scope.message.messageId,
    				replyContent:$scope.message.replyContent
    		};
    		sysMessageService.sysMessageReply($http,$scope.message).then(function(data) {
    			if(data.stateCode=="1000"){
    				alert("回复完毕!");
    				$scope.getMessageList();
    			}else{
    				alert("回复异常!");
    				$scope.getMessageList();
    			}  
    			$('#myReply').modal('hide');
    		});
    	};
    	/*************************删除***************************/

    	$scope.delMessage=function(m){
    		//$scope.message=m;
    	    var result = confirm('是否删除！'); 
    	    if(result){  
        		sysMessageService.delMessage($http,m).then(function(data) {
        			if(data.stateCode=="1000"){
        				alert("回复完毕!");
        				$scope.getMessageList();
        			}else{
        				alert("回复异常!");
        				$scope.getMessageList();
        			}  
        			$('#myReply').modal('hide');
        		}); 
    	    }else{  
    	    }
 
    	};
    	
    	$scope.getMessageList();
    };
    return ["sysMessagePageListCtrl", _controller];
});
