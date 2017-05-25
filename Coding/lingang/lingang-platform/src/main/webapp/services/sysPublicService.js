/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
	 var items = {};
	 
	    items.sysPublicPageListP = function ($http,data) {
	        var request = $http.post('sysPublic/sysPublicPageListP.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    //获取公共平台列表
	    items.sysPublicPageList = function ($http,data) {
	        var request = $http.post('sysPublic/sysPublicPageList.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取详情
	    items.getPublicDetails = function ($http,data) {
	        var request = $http.post('sysPublic/publicDetails.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //修改信息
	    items.updatePublicDetails = function ($http,data) {
	        var request = $http.post('sysPublic/updatePublicDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加信息
	    items.addPublicDetails = function ($http,data) {
	        var request = $http.post('sysPublic/addPublicDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取所有已发布开放层级
	    items.getPublicBasicsAll = function ($http,data) {
	        var request = $http.post('sysBasics/getPublicBasicsAll.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 获取所有地区
	    items.getRegionAll = function ($http,data) {
	        var request = $http.post('sysRegion/getRegionAll.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除标签
	    items.delLab = function ($http,data) {
	        var request = $http.post('sysPublic/delLab.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	 // 删除园区
	    items.delPark = function ($http,data) {
	        var request = $http.post('sysPublic/delPark.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除公司级联
	    items.delCom = function ($http,data) {
	        var request = $http.post('company/delCompanyCascade.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	  //添加电话
	    items.addWay = function ($http,data) {
	        var request = $http.post('sysPark/addWay.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	  //删除电话
	    items.delWay = function ($http,data) {
	        var request = $http.post('sysPark/delWay.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 查询客户经理
	    items.seleceAdUser = function ($http,data) {
	        var request = $http.post('sysPartner/seleceAdUser.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除客户经理
	    items.delUman = function ($http,data) {
	        var request = $http.post('sysPublic/delUman.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加客户经理
	    items.addUManager = function ($http,data) {
	        var request = $http.post('sysPublic/addUManager.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };

    return ['sysPublicService', items];
});