/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
    var items = {};
    //获取用户列表
    items.sysUserPagePageList = function ($http,data) {
        var request = $http.post('user/sysUserPagePageList.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    return ['sysUserService', items];
});