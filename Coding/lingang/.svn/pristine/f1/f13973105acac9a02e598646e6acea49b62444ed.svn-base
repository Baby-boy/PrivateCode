/**
 * Created by FxLsoft on 2015/12/3.
 */
define(['route-config'], function (routeConfig) {
    /*routeConfig.regController();*/
    var _controller = function ($scope, $routeParams) {
        var viewUrl = "", controllerUrl = "";
        requirejs([controllerUrl], function (ct) {
            $scope.$apply(function () {
                routeConfig.regController(ct);
            });
        });
    };
    return _controller;
});
