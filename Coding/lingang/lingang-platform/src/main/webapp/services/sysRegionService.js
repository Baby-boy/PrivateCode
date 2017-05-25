/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
    var items = {};
    
    items.sysRegionPagePageListP = function ($http,data) {
        var request = $http.post('sysRegion/sysRegionPagePageListP.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    //通知
    items.sysRegionPagePageList = function ($http,data) {
    	/*var data = {
    			para:req
    	};*/
        var request = $http.post('sysRegion/sysRegionPagePageList.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    //修改地区详情
    items.updateSysRegion = function ($http,data) {
        var request = $http.post('sysRegion/updateSysRegion.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    //添加地区
    items.sysRegionAdd = function ($http,data) {
        var request = $http.post('sysRegion/sysRegionAdd.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
//   //保存通讯内容
//    items.saveLcMsgLog = function ($http,uuid,content) {
//    	var data = {
//    		uuid:uuid,
//    		content:content
//    	};
//    	var request = $http.post('msgLog/saveLcMsgLog.do', data, {params:data});
//    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
//    };
    
    
    return ['sysRegionService', items];
});