/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
    var items = {};
    //获取信息纠错列表
    items.sysMessagePagePageListE = function ($http,data) {
    	//controller路径
        var request = $http.post('sysMessage/sysMessagePagePageListE.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    items.sysMessagePagePageList = function ($http,data) {
    	//controller路径
        var request = $http.post('sysMessage/sysMessagePagePageList.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    items.sysMessageOpinionPageList = function ($http,data) {
    	//controller路径
        var request = $http.post('sysMessage/sysMessagePagePageList.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    items.sysMessageReply = function ($http,data) {
    	//controller路径
        var request = $http.post('sysMessage/sysMessageReply.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    items.delMessage = function ($http,data) {
    	alert(data.messageId);
    	var m={
    			messageId:data.messageId
    	}
    	//controller路径
        var request = $http.post('sysMessage/delMessage.do', m, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    
    return ['sysMessageService', items];
});