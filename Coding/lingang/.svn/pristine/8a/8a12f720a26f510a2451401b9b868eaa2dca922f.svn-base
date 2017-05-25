define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "sysPublicPageList":
	            controllerUrl="controllers/seek/sysPublic/sysPublicPageListCtrl";
	            rightViewUrl = "views/seek/sysPublic/sysPublicPageList.html";
	            strTitle="管理员账户列表";
	            $scope.nav = 1;
	            break;
	            
        	case "sysPublicDetails":
	            controllerUrl="controllers/seek/sysPublic/sysPublicDetailsCtrl";
	            rightViewUrl = "views/seek/sysPublic/sysPublicDetails.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	case "sysPublicAdd":
	            controllerUrl="controllers/seek/sysPublic/sysPublicAddCtrl";
	            rightViewUrl = "views/seek/sysPublic/sysPublicAdd.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	default:
        		controllerUrl="controllers/seek/sysPublic/sysPublicPageListCtrl";
	            rightViewUrl = "views/seek/sysPublic/sysPublicPageList.html";
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