/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
	 var items = {};
	 	
	    items.sysStationPageListP = function ($http,data) {
	        var request = $http.post('sysStation/sysStationPageListP.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    //获取入驻企业列表
	    items.sysStationPageList = function ($http,data) {
	        var request = $http.post('sysStation/sysStationPageList.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取详情
	    items.getStationDetails = function ($http,data) {
	        var request = $http.post('sysStation/stationDetails.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //修改信息
	    items.updateStationDetails = function ($http,data) {
	        var request = $http.post('sysStation/updateStationDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加信息
	    items.addStationDetails = function ($http,data) {
	        var request = $http.post('sysStation/addStationDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 获取所有地区
	    items.getRegionAll = function ($http,data) {
	        var request = $http.post('sysRegion/getRegionAll.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除标签
	    items.delLab = function ($http,data) {
	        var request = $http.post('sysStation/delLab.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除园区
	    items.delPark = function ($http,data) {
	        var request = $http.post('sysStation/delPark.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除覆盖产业
	    items.delIndustry = function ($http,data) {
	        var request = $http.post('sysStation/delIndustry.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除公司级联
	    items.delCom = function ($http,data) {
	        var request = $http.post('company/delCompanyCascade.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //置顶
	    items.stationTop = function ($http,data) {
	        var request = $http.post('sysStation/stationTop.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    
	    // 查询客户经理
	    items.seleceAdUser = function ($http,data) {
	        var request = $http.post('sysPartner/seleceAdUser.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除客户经理
	    items.delUman = function ($http,data) {
	        var request = $http.post('sysStation/delUman.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加客户经理
	    items.addUManager = function ($http,data) {
	        var request = $http.post('sysStation/addUManager.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };

    return ['sysStationService', items];
});