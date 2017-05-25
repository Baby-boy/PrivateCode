/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
    var items = {};
    
    items.selectCompanyPageListP = function ($http,data) {
        var request = $http.post('company/selectCompanyPageListP.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    // 获取公司列表
    items.selectCompanyPageList = function ($http,data) {
        var request = $http.post('company/selectCompanyPageList.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    // 修改公司信息
    items.updateCompanyDetails = function ($http,data) {
        var request = $http.post('company/updateCompanyDetails.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    // 添加公司信息
    items.addCompanyDetails = function ($http,data) {
    	var request = $http.post('company/addCompanyDetails.do', data, serviceBase.postConfig);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    // 添加公司信息(级联)
    items.addCompanyCascade = function ($http,data) {
    	var request = $http.post('company/addCompanyCascade.do', data, serviceBase.postConfig);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    return ['sysCompanyService', items];
});