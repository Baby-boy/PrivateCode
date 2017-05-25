define(['mySession', 'route-config'], function (mySession, routeConfig) {
	
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "sysLabelTypePageList":
	            controllerUrl="controllers/basics/sysLabel/sysLabelPageListCtrl";
	            rightViewUrl = "views/basics/sysLabel/sysLabelTypePageList.html";
	            strTitle="标签列表";
	            // $scope.nav = 1;
	            break;
	            
	        default:
	        	controllerUrl="controllers/basics/sysLabel/sysLabelPageListCtrl";
	            rightViewUrl = "views/basics/sysLabel/sysLabelTypePageList.html";
	            strTitle="标签列表";
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