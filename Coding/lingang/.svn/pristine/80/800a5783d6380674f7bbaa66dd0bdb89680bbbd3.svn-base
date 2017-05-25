/*线上借书*/
define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "sysBasicsTypePageList":
	            controllerUrl="controllers/basics/sysBasics/sysBasicsPageListCtrl";
	            rightViewUrl = "views/basics/sysBasics/sysBasicsTypePageList.html";
	            strTitle="基础设置列表";
	            // $scope.nav = 1;
	            break;
	            
	        default:
	        	controllerUrl="controllers/basics/sysBasics/sysBasicsPageListCtrl";
	            rightViewUrl = "views/basics/sysBasics/sysBasicsTypePageList.html";
	            strTitle="基础设置列表";
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