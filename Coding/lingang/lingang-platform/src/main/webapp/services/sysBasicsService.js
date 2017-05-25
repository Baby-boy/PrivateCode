/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
    var items = {};
    items.sysBasicsPagePageListP = function ($http,data) {
        var request = $http.post('sysBasics/sysBasicsPagePageListP.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //通知
    items.sysBasicsPagePageList = function ($http,data) {
    	/*var data = {
    			para:req
    	};*/
        var request = $http.post('sysBasics/sysBasicsPagePageList.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    //修改基础设置信息
    items.updateSysBasics = function ($http,data) {
        var request = $http.post('sysBasics/updateSysBasics.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //添加基础设置
    items.sysBasicsAdd = function ($http,data) {
        var request = $http.post('sysBasics/sysBasicsAdd.do', data, serviceBase.postConfig);
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
    
    
    return ['sysBasicsService', items];
});