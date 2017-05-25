/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
    var items = {};
    // 获取管理员列表
    items.sysManagerPagePageList = function ($http,data) {
        var request = $http.post('sysManager/sysManagerPagePageList.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    // 修改管理员信息
    items.updateSysManager = function ($http,data) {
        var request = $http.post('sysManager/updateSysManager.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    // 添加管理员
    items.addSysManager = function ($http,data) {
    	var request = $http.post('sysManager/addSysManager.do', data, serviceBase.postConfig);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    // 修改权限
    items.updatePower = function ($http,data) {
    	var request = $http.post('sysManager/updatePower.do', data, serviceBase.postConfig);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    // 获取管理员列表
    items.sysLogPagePageList = function ($http,data) {
        var request = $http.post('sysManager/selectLogPageList.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    return ['sysManagerService', items];
});