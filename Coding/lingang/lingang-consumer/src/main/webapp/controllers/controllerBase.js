/**
 */
define(['route-config'], function (routeConfig) {
    routeConfig.regController();
    var _controller = function ($scope, $routeParams) {
        var strTitle="";
        var viewUrl = "", controllerUrl = "";
        requirejs([controllerUrl], function (ct) {
            $scope.$apply(function () {
                routeConfig.regController(ct);
            });
        });
    };
    return _controller;
});
