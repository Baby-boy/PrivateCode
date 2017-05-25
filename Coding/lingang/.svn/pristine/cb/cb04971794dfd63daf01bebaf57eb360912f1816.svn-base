define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "topStationPageList":
	            controllerUrl="controllers/home/topStation/topStationListCtrl";
	            rightViewUrl = "views/home/topStation/topStationPageList.html";
	            strTitle="入驻企业列表";
//	            $scope.nav = 1;
	            break;
        	case "topStationDetails":
	            controllerUrl="controllers/home/topStation/topStationDetails";
	            rightViewUrl = "views/home/topStation/topStationDetails.html";
	            strTitle="入驻企业详情";
//	            $scope.nav = 1;
	            break;
        	case "topStationAdd":
	            controllerUrl="controllers/home/topStation/topStationAdd";
	            rightViewUrl = "views/home/topStation/topStationAdd.html";
	            strTitle="添加入驻企业";
//	            $scope.nav = 1;
	            break;
	            
        	default:
	            controllerUrl="controllers/home/topStation/topStationListCtrl";
	            rightViewUrl = "views/home/topStation/topStationPageList.html";
	            strTitle="入驻企业列表";
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