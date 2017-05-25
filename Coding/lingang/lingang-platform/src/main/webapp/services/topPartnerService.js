/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
	 var items = {};
	    //获取管理员列表
	    items.topPartnerPageList = function ($http,data) {
	        var request = $http.post('sysPartner/queryAllByPage.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    //获取详情
	    items.getPartnerDetails = function ($http,data) {
	        var request = $http.post('sysPartner/topPartnerDetails.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //修改信息
	    items.topUpdatePartnerDetails = function ($http,data) {
	        var request = $http.post('sysPartner/topUpdatePartnerDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加信息
	    items.addTopPartnerDetails = function ($http,data) {
	        var request = $http.post('sysPartner/addTopPartnerDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取所有已发布合作层级
	    items.getTopPartnerBasicsAll = function ($http,data) {
	        var request = $http.post('sysBasics/getTopPartnerBasicsAll.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取所有合作类型
	    items.getTopPartnerTypeAll = function ($http,data) {
	    	var request = $http.post('sysPartner/getTopPartnerTypeAll.do', data, serviceBase.postConfig);
	    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    //取消置顶
	    items.delPartenrTop = function ($http,data) {
	    	var request = $http.post('sysPartner/delPartenrTop.do', data, serviceBase.postConfig);
	    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };

    return ['topPartnerService', items];
});