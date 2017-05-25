/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
	 var items = {};
	    //获取管理员列表
	    items.topStationPageList = function ($http,data) {
	        var request = $http.post('sysTopStation/queryAllByTop.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    //获取详情
	    items.topStationDetails = function ($http,data) {
	        var request = $http.post('sysTopStation/topStationDetails.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    //修改信息
	    items.updateTopStationDetails = function ($http,data) {
	        var request = $http.post('sysTopStation/updateTopStationDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    //添加信息
	    items.addTopStationDetails = function ($http,data) {
	        var request = $http.post('sysTopStation/addTopStationDetails.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    
	    // 获取所有地区
	    items.getRegionAll = function ($http,data) {
	        var request = $http.post('topRegion/getRegionAll.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };
	    //取消置顶
	    items.delStationTop = function ($http,data) {
	        var request = $http.post('sysTopStation/delStationTop.do', data, {params:data});
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };

    return ['topStationService', items];
});