/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
	 var items = {};
	    //获取管理员列表
	    items.sysNewsPageList = function ($http,data) {
	        var request = $http.post('sysNews/newsList.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    items.sysNewsAdd = function ($http,data) {
	        var request = $http.post('sysNews/insert.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    items.sysNewsDetail = function ($http,data) {
	        var request = $http.post('sysNews/queryById.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    items.sysNewsDel = function ($http,data) {
	        var request = $http.post('sysNews/del.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    items.sysNewsUpdate = function ($http,data) {
	        var request = $http.post('sysNews/update.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
    return ['sysNewsService', items];
});