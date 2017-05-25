/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
    var items = {};
    
    items.sysLabelPagePageListP = function ($http,data) {
        var request = $http.post('sysLabel/sysLabelPagePageListP.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    //通知
    items.sysLabelPagePageList = function ($http,data) {
    	/*var data = {
    			para:req
    	};*/
        var request = $http.post('sysLabel/sysLabelPagePageList.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    //修改标签信息
    items.updateSysLabel = function ($http,data) {
        var request = $http.post('sysLabel/updateSysLabel.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    //添加标签
    items.sysLabelAdd = function ($http,data) {
        var request = $http.post('sysLabel/sysLabelAdd.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    //添加标签
    items.addLabelCascade = function ($http,data) {
        var request = $http.post('sysLabel/addLabelCascade.do', data, serviceBase.postConfig);
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
    
    
    return ['sysLabelService', items];
});