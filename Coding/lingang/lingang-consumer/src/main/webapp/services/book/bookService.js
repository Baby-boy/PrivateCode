/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
    var items = {};
    //案例馆列表信息
    items.getBookList = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/borrowIndex.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //收藏
    items.collectBookT = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/collectBook.do', data, {params:data});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //取消收藏
    items.collectBookF = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/deleteCollectBook.do', data, {params:data});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //借阅车数量统计
    items.myReadCarCount = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/myReadCarCount.do', data, {params:data});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //加入借阅车
    items.addReadCar = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/addReadCar.do', data, {params:data});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //我的信息查询
    items.vipInfoMes = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/vipInfoMes.do', data, {params:data});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //书籍详情
    items.bookDetail = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/bookDetails.do', data, {params:data});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //我的借阅车
    items.myReadCar = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/myReadCar.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //删除借阅车书籍
    items.deleteBook = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/deleteThisReadCar.do', data, {params:data});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //快递地址
    items.addressList = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/getAddress.do', data, {params:data});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //
    items.getIncomeExpenditure = function ($http,req) {
    	var data = req;
        var request = $http.post('selectBalance.htm', data, {params:data});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //立即借阅
    items.borrowBookNow = function ($http,req) {
    	var data = req;
        var request = $http.post('borrowBook/borrowBook.do', data, serviceBase.postConfig);
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取优惠券
    items.getCoupons = function($http,useScope,lingangToken,pageSize,pageIndex){
    	var request = $http({
    		url:'getCouponsInAct.do',
    		params:{
    			"useScope":useScope,//适用范围(0.不限1.活动消费2书社消费)
				"lingangToken":lingangToken,
				"pageSize":pageSize,
				"pageIndex":pageIndex,
    		},
    		method:'POST'
    	});
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    return ['bookService', items]
});