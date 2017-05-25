define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "sysServicePageList":
	            controllerUrl="controllers/seek/sysService/sysServicePageListCtrl";
	            rightViewUrl = "views/seek/sysService/sysServicePageList.html";
	            strTitle="管理员账户列表";
	            $scope.nav = 1;
	            break;
	            
        	case "sysServiceDetails":
	            controllerUrl="controllers/seek/sysService/sysServiceDetailsCtrl";
	            rightViewUrl = "views/seek/sysService/sysServiceDetails.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	case "sysServiceAdd":
	            controllerUrl="controllers/seek/sysService/sysServiceAddCtrl";
	            rightViewUrl = "views/seek/sysService/sysServiceAdd.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	default:
        		controllerUrl="controllers/seek/sysService/sysServicePageListCtrl";
	            rightViewUrl = "views/seek/sysService/sysServicePageList.html";
	            strTitle="";
	            $scope.nav = 1;
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