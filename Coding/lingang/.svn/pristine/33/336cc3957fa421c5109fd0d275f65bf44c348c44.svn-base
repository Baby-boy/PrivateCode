/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
	 var items = {};
	 
	    items.sysParkPageListP = function ($http,data) {
	        var request = $http.post('sysPark/sysParkPageListP.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取列表
	    items.sysParkPageList = function ($http,data) {
	        var request = $http.post('sysPark/sysParkPageList.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //获取详情
	    items.getParkDetails = function ($http,data) {
	        var request = $http.post('sysPark/parkDetails.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //修改信息
	    items.updateParkDetails = function ($http,data) {
	        var request = $http.post('sysPark/updateParkDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加信息
	    items.addParkDetails = function ($http,data) {
	        var request = $http.post('sysPark/addParkDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 获取所有地区
	    items.getRegionAll = function ($http,data) {
	        var request = $http.post('sysRegion/getRegionAll.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除标签
	    items.delLab = function ($http,data) {
	        var request = $http.post('sysPark/delLab.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除覆盖产业
	    items.delIndustry = function ($http,data) {
	        var request = $http.post('sysPark/delIndustry.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除宣传册/宣传片
	    items.delFile = function ($http,data) {
	        var request = $http.post('sysPark/delFile.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	 // 删除宣传册/宣传片
	    items.addIndustryParkCascade = function ($http,data) {
	        var request = $http.post('sysPark/addIndustryParkCascade.do', data, {params:data});
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
	    
		  //修改宣传片封面
	    items.uploadFileParkVideoImage = function ($http,data) {
	        var request = $http.post('sysPark/uploadFileParkVideoImage.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 查询客户经理
	    items.seleceAdUser = function ($http,data) {
	        var request = $http.post('sysPartner/seleceAdUser.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 删除客户经理
	    items.delUman = function ($http,data) {
	        var request = $http.post('sysPark/delUman.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //添加客户经理
	    items.addUManager = function ($http,data) {
	        var request = $http.post('sysPark/addUManager.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
    return ['sysParkService', items];
});