define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "sysIndustryPageList":
	            controllerUrl="controllers/seek/sysIndustry/sysIndustryPageListCtrl";
	            rightViewUrl = "views/seek/sysIndustry/sysIndustryPageList.html";
	            strTitle="管理员账户列表";
	            // $scope.nav = 1;
	            break;
	            
        	case "sysIndustryDetails":
	            controllerUrl="controllers/seek/sysIndustry/sysIndustryDetailsCtrl";
	            rightViewUrl = "views/seek/sysIndustry/sysIndustryDetails.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	case "sysIndustryAdd":
	            controllerUrl="controllers/seek/sysIndustry/sysIndustryAddCtrl";
	            rightViewUrl = "views/seek/sysIndustry/sysIndustryAdd.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	default:
        		controllerUrl="controllers/seek/sysIndustry/sysIndustryPageListCtrl";
	            rightViewUrl = "views/seek/sysIndustry/sysIndustryPageList.html";
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