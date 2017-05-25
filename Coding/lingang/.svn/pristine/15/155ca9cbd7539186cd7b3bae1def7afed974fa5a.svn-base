/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
	 var items = {};
	 
	 	items.sysServicePageListP = function ($http,data) {
	        var request = $http.post('sysService/sysServicePageListP.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    //获取服务机构列表
	    items.sysServicePageList = function ($http,data) {
	        var request = $http.post('sysService/sysServicePageList.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取详情
	    items.getServiceDetails = function ($http,data) {
	        var request = $http.post('sysService/serviceDetails.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //修改信息
	    items.updateServiceDetails = function ($http,data) {
	        var request = $http.post('sysService/updateServiceDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加信息
	    items.addServiceDetails = function ($http,data) {
	        var request = $http.post('sysService/addServiceDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取所有已发布服务信息
	    items.getServiceBasicsAll = function ($http,data) {
	        var request = $http.post('sysBasics/getServiceBasicsAll.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 获取所有地区
	    items.getRegionAll = function ($http,data) {
	        var request = $http.post('sysRegion/getRegionAll.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除标签
	    items.delLab = function ($http,data) {
	        var request = $http.post('sysService/delLab.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除园区
	    items.delPark = function ($http,data) {
	        var request = $http.post('sysService/delPark.do', data, {params:data});
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
	    
	    // 删除公司级联
	    items.delCom = function ($http,data) {
	        var request = $http.post('company/delCompanyCascade.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 查询客户经理
	    items.seleceAdUser = function ($http,data) {
	        var request = $http.post('sysPartner/seleceAdUser.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除客户经理
	    items.delUman = function ($http,data) {
	        var request = $http.post('sysService/delUman.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加客户经理
	    items.addUManager = function ($http,data) {
	        var request = $http.post('sysService/addUManager.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };

    return ['sysServiceService', items];
});