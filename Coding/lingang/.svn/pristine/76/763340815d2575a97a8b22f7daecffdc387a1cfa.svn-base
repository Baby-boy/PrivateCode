/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
    var items = {};
    //案例馆列表信息
    items.informationList = function ($http,req) {
    	var data = req;
        var request = $http.post('informationList.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //案例馆列表信息
    items.informationDetail = function ($http,req) {
    	var data = req;
        var request = $http.post('informationMes.do', data, {params:data});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    return ['informationService', items]
});