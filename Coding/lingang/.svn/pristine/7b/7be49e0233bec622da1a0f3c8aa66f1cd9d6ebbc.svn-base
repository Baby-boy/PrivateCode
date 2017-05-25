/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
	 var items = {};
	 
	    items.sysPartnerPageListP = function ($http,data) {
	        var request = $http.post('sysPartner/sysPartnerPageListP.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取合作伙伴列表
	    items.sysPartnerPageList = function ($http,data) {
	        var request = $http.post('sysPartner/sysPartnerPageList.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取详情
	    items.getPartnerDetails = function ($http,data) {
	        var request = $http.post('sysPartner/partnerDetails.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //修改信息
	    items.updatePartnerDetails = function ($http,data) {
	        var request = $http.post('sysPartner/updatePartnerDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加信息
	    items.addPartnerDetails = function ($http,data) {
	        var request = $http.post('sysPartner/addPartnerDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取所有已发布合作层级
	    items.getPartnerBasicsAll = function ($http,data) {
	        var request = $http.post('sysBasics/getPartnerBasicsAll.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取所有合作类型
	    items.getSysPartnerTypeAll = function ($http,data) {
	    	var request = $http.post('sysPartner/getSysPartnerTypeAll.do', data, serviceBase.postConfig);
	    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除标签
	    items.delLab = function ($http,data) {
	        var request = $http.post('sysPartner/delLab.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除公司级联
	    items.delCom = function ($http,data) {
	        var request = $http.post('company/delCompanyCascade.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 置顶
	    items.partenerTop = function ($http,data) {
	        var request = $http.post('sysPartner/partenerTop.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 查询客户经理
	    items.seleceAdUser = function ($http,data) {
	        var request = $http.post('sysPartner/seleceAdUser.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除客户经理
	    items.delUman = function ($http,data) {
	        var request = $http.post('sysPartner/delUman.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加客户经理
	    items.addUManager = function ($http,data) {
	        var request = $http.post('sysPartner/addUManager.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };

    return ['sysPartnerService', items];
});