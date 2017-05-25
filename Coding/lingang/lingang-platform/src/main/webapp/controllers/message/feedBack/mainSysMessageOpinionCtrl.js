define(['mySession', 'route-config'], function (mySession, routeConfig) {
    var _controller = function($scope, $http, $routeParams){
    	var strTitle="";
        var rightViewUrl = "",controllerUrl="";
        $scope.nav = 1;
        switch ($routeParams.childModule) {
        	case "opinion":
	            controllerUrl="controllers/message/feedBack/sysMessageOpinionListCtrl";
	            rightViewUrl = "views/message/feedBack/opinion.html";
	            strTitle="意见反馈列表";
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