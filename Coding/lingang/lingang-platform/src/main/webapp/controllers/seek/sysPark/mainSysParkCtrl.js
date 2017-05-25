define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "sysParkPageList":
	            controllerUrl="controllers/seek/sysPark/sysParkPageListCtrl";
	            rightViewUrl = "views/seek/sysPark/sysParkPageList.html";
	            strTitle="";
	            // $scope.nav = 1;
	            break;
	            
        	case "sysParkDetails":
	            controllerUrl="controllers/seek/sysPark/sysParkDetailsCtrl";
	            rightViewUrl = "views/seek/sysPark/sysParkDetails.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	case "sysParkAdd":
	            controllerUrl="controllers/seek/sysPark/sysParkAddCtrl";
	            rightViewUrl = "views/seek/sysPark/sysParkAdd.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	default:
        		controllerUrl="controllers/seek/sysPark/sysParkPageListCtrl";
	            rightViewUrl = "views/seek/sysPark/sysParkPageList.html";
	            strTitle="";
//	            $scope.nav = 1;
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