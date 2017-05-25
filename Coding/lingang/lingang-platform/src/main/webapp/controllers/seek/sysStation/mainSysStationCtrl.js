define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "sysStationPageList":
	            controllerUrl="controllers/seek/sysStation/sysStationPageListCtrl";
	            rightViewUrl = "views/seek/sysStation/sysStationPageList.html";
	            strTitle="管理员账户列表";
	            $scope.nav = 1;
	            break;
	            
        	case "sysStationDetails":
	            controllerUrl="controllers/seek/sysStation/sysStationDetailsCtrl";
	            rightViewUrl = "views/seek/sysStation/sysStationDetails.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	case "sysStationAdd":
	            controllerUrl="controllers/seek/sysStation/sysStationAddCtrl";
	            rightViewUrl = "views/seek/sysStation/sysStationAdd.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	default:
        		controllerUrl="controllers/seek/sysStation/sysStationPageListCtrl";
	            rightViewUrl = "views/seek/sysStation/sysStationPageList.html";
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