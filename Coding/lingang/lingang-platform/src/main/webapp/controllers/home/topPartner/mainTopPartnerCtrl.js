define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "topPartnerPageList":
	            controllerUrl="controllers/home/topPartner/topPartnerPageListCtrl";
	            rightViewUrl = "views/home/topPartner/topPartnerPageList.html";
	            strTitle="合作信息列表";
//	            $scope.nav = 1;
	            break;
        	case "topPartenrDetails":
	            controllerUrl="controllers/home/topPartner/topPartenrDetails";
	            rightViewUrl = "views/home/topPartner/topPartenerDetails.html";
	            strTitle="合作信息详情";
//	            $scope.nav = 1;
	            break;
        	case "topPartenerAdd":
	            controllerUrl="controllers/home/topPartner/topPartenerAdd";
	            rightViewUrl = "views/home/topPartner/topPartenerAdd.html";
	            strTitle="添加合作伙伴";
//	            $scope.nav = 1;
	            break;
	            
        	default:
        		controllerUrl="controllers/home/topPartner/topPartnerPageListCtrl";
	            rightViewUrl = "views/home/topPartner/topPartnerPageList.html";
	            strTitle="合作信息列表";
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