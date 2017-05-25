/*线上借书*/
define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "sysCompanyPageList":
	            controllerUrl="controllers/basics/sysCompany/sysCompanyPageListCtrl";
	            rightViewUrl = "views/basics/sysCompany/sysCompanyPageList.html";
	            strTitle="管理员账户列表";
	            // $scope.nav = 1;
	            break;
	            
	        default:
	        	controllerUrl="controllers/system/sysCompany/sysCompanyPageListCtrl";
	            rightViewUrl = "views/system/sysCompany/sysCompanyPageList.html";
	            strTitle="管理员账户列表";
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