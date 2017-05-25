define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        
        switch ($routeParams.childModule) {
        	case "sysNewsPageList":
	            controllerUrl="controllers/home/sysNews/sysNewsPageListCtrl";
	            rightViewUrl = "views/home/sysNews/sysNewsPageList.html";
	            strTitle="新闻列表";
//	            $scope.nav = 1;
	            break;
        	case "sysNewsAdd":
        		controllerUrl="controllers/home/sysNews/sysNewsAddCtrl";
	            rightViewUrl = "views/home/sysNews/sysNewsAdd.html";
	            strTitle="添加新闻";
//	            $scope.nav = 1;
        		break;
        	case "sysNewsMsg":
        		controllerUrl="controllers/home/sysNews/sysNewsMsgCtrl";
	            rightViewUrl = "views/home/sysNews/sysNewsMsg.html";
	            strTitle="新闻详情";
//	            $scope.nav = 1;
        		break;
        	default:
        		 controllerUrl="controllers/home/sysNews/sysNewsPageListCtrl";
	            rightViewUrl = "views/home/sysNews/sysNewsPageList.html";
	            strTitle="新闻列表";
	//            $scope.nav = 1;
	            break;
	    }
	    requirejs([controllerUrl], function (ct) {
	        $scope.$apply(function () {
	            routeConfig.regController(ct);
	            $scope.rightContentUrl = rightViewUrl;
	            document.title=strTitle;
	        });
	    });
    };
    return  _controller;
});