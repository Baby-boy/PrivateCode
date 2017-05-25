define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "sysPartnerPageList":
	            controllerUrl="controllers/seek/sysPartner/sysPartnerPageListCtrl";
	            rightViewUrl = "views/seek/sysPartner/sysPartnerPageList.html";
	            strTitle="";
	            // $scope.nav = 1;
	            break;
	            
        	case "sysPartnerDetails":
	            controllerUrl="controllers/seek/sysPartner/sysPartnerDetailsCtrl";
	            rightViewUrl = "views/seek/sysPartner/sysPartnerDetails.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	case "sysPartnerAdd":
	            controllerUrl="controllers/seek/sysPartner/sysPartnerAddCtrl";
	            rightViewUrl = "views/seek/sysPartner/sysPartnerAdd.html";
	            strTitle="";
	            // $scope.nav = 2;
	            break;
	            
        	default:
        		controllerUrl="controllers/seek/sysPartner/sysPartnerPageListCtrl";
	            rightViewUrl = "views/seek/sysPartner/sysPartnerPageList.html";
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