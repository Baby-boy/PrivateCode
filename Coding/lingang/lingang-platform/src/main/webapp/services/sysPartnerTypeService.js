/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
	 var items = {};
	    
	    items.selectPartnerTypePageListP = function ($http,data) {
	    	var request = $http.post('sysPartnerType/selectPartnerTypePageListP.do', data, serviceBase.postConfig);
	    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    // 获取所有合作类型
	    items.selectPartnerTypePageList = function ($http,data) {
	    	var request = $http.post('sysPartnerType/selectPartnerTypePageList.do', data, serviceBase.postConfig);
	    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 修改合作类型
	    items.updatePartnerTypeDetails = function ($http,data) {
	    	var request = $http.post('sysPartnerType/updatePartnerType.do', data, serviceBase.postConfig);
	    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 添加合作类型
	    items.addPartnerTypeDetails = function ($http,data) {
	    	var request = $http.post('sysPartnerType/addPartnerType.do', data, serviceBase.postConfig);
	    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };

    return ['sysPartnerTypeService', items];
});