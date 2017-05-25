define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "sysPolicyPageList":
	            controllerUrl="controllers/home/sysPolicy/sysPolicyPageListCtrl";
	            rightViewUrl = "views/home/sysPolicy/sysPolicyPageList.html";
	            strTitle="政策列表";
//	            $scope.nav = 1;
	            break;
        	case "sysPolicyMsg":
	            controllerUrl="controllers/home/sysPolicy/sysPolicyMsg";
	            rightViewUrl = "views/home/sysPolicy/sysPolicyMsg.html";
	            strTitle="政策详情";
//	            $scope.nav = 1;
	            break;
	            
        	case "sysPolicyAdd":
	            controllerUrl="controllers/home/sysPolicy/sysPolicyAddCtrl";
	            rightViewUrl = "views/home/sysPolicy/sysPolicyAdd.html";
	            strTitle="政策详情";
//	            $scope.nav = 1;
	            break;
	            
        	default:
        		controllerUrl="controllers/home/sysPolicy/sysPolicyPageListCtrl";
	            rightViewUrl = "views/home/sysPolicy/sysPolicyPageList.html";
	            strTitle="政策列表";
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