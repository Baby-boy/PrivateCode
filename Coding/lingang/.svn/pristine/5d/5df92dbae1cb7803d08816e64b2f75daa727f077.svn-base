/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase","mySession"], function (serviceBase,mySession) {
    var items = {};
    //获取地址
    items.getJSON = function ($http) {
        var request = $http.get('json/citysheet.json', {cache:true});
        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取系列
    items.getActSeriess = function($http){
    	var request = $http.post('getActSeriess.do?regionId=1', null, serviceBase.postConfig);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取活动
    items.getActDetail = function($http,id,lingangToken){
    	var request = $http.post('getActInfo.do?actId='+id+'&lingangToken='+lingangToken, null, serviceBase.postConfig);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取首页pc
    items.getIndexInfoPc = function($http){
    	var request =  $http({
			url:'getIndexInfoPc.do',
			params:{
				"pageSize":pageSize,
				"pageIndex":pageIndex,
				"subjectId":subjectId,
				"actname":actname,
				"releaseStatus":releaseStatus,
				"regionId":regionId,
				"age":age,
				"sort":sort,
				"lingangToken":lingangToken,
			},
			method:'POST'
		});
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取系列
    items.getActSeriess2 = function($http){
    	var request = $http.post('getActSeriess2.do?regionId=1', null, serviceBase.postConfig);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取科目
    items.getActSubjectBySeries = function($http,seriesId,pageSize,pageIndex){
    	var request = $http({
			url:'getActSubjectBySeries.do',
			params:{
				"seriesId":seriesId,
				"releaseStatus":1,
				"pageSize":pageSize,
				"pageIndex":pageIndex,
			},
			method:'POST'
		});
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取活动
    items.getActInfos = function($http,pageSize,pageIndex,subjectId,actname,releaseStatus,regionId,age,sort,lingangToken,recommendState){
//    	var request = $http.post('getActInfos.do?regionId='+regionId+'&pageSize='+pageSize+'&pageIndex='+pageIndex+
//    			'&subjectId='+subjectId+'&actname='+actname+'&releaseStatus='+releaseStatus+'&age='+age+'&sort='+sort+'&lingangToken='+lingangToken, null, serviceBase.postConfig);
    	
    	var request = $http({
			url:'getActInfos.do',
			params:{
				"pageSize":pageSize,
				"pageIndex":pageIndex,
				"subjectId":subjectId,
				"actname":actname,
				"releaseStatus":releaseStatus,
				"regionId":regionId,
				"age":age,
				"sort":sort,
				"lingangToken":lingangToken,
				"recommendState":recommendState,
			},
			method:'POST'
		});
    	
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取首页广告图
    items.getIndexCarousel = function($http){
    	var request = $http.post('selectResourceInfoByHomeType.do', null, serviceBase.postConfig);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取大图 根据类型
    items.getResources = function($http,objectType){
    	var request = $http({
			url:'getResources.do',
			params:{
				"objectType":objectType,
			},
			method:'POST'
		});
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取资讯
    items.informationList = function($http,query){
    	var request = $http.post('informationList.do?regionId=1', query, serviceBase.postConfig);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取资讯信息
    items.informationMes = function($http,id){
    	var request = $http.post('informationMes.do?id='+id, null, serviceBase.postConfig);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取活动pc
    items.getActInfosPc = function($http,pageSize,pageIndex,subjectId,actname,releaseStatus,
    		regionId,age,sort,lingangToken,actTime,seriesId,recommendState){
    	var request = $http({
			url:'getActInfosPc.do',
			params:{
				"pageSize":pageSize,
				"pageIndex":pageIndex,
				"subjectId":subjectId,
				"actname":actname,
				"releaseStatus":releaseStatus,
				"regionId":regionId,
				"age":age,
				"sort":sort,
				"lingangToken":lingangToken,
				"actTime":actTime,
				"seriesId":seriesId,
				"recommendState":recommendState,
			},
			method:'POST'
		});
    	
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取常用联系人参与者地址
    items.getCommonContacts = function($http,contactsType,lingangToken){
    	var request = $http({
			url:'getCommonContacts.do',
			params:{
				"contactsType":contactsType,//1=常用联系人 2=常用参与者 3=常用收件地址
				"lingangToken":lingangToken,
			},
			method:'POST'
		});
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
    //添加活动订单
    items.addActOrder = function($http,actOrder){
    	var request = $http.post(
				'addActOrder.do',
				actOrder,
				serviceBase.postConfig
			);
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取活动订单
    items.getActOrders = function($http,pageSize,pageIndex,lingangToken,orderState){
    	var request =$http({
			url:'getActOrders.do',
			params:{
				"pageSize":pageSize,
				"pageIndex":pageIndex,
				"lingangToken":lingangToken,
				"orderState":orderState, //订单状态 订单状态1已支付2未支付3申请退款
			},
			method:'POST'
		});
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取优惠券
    items.getCouponsInUserCenter = function($http,pageSize,pageIndex,lingangToken){
    	var request =$http({
			url:'getCoupons.do',
			params:{
				"pageSize":pageSize,
				"pageIndex":pageIndex,
//				"status":0, //0=未使用 1=已使用
				"lingangToken":lingangToken,
			},
			method:'POST'
		});
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //兑换优惠券
    items.exchangeCoupon = function($http,couponNo,lingangToken){
    	var request =$http({
			url:'exchangeCoupon.do',
			params:{
				"couponNo":couponNo,
				"lingangToken":lingangToken,
			},
			method:'POST'
		});
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取会员卡信息
    items.getVipCard = function($http,lingangToken){
    	var request =$http({
			url:'getVipCard.do',
			params:{
				"lingangToken":lingangToken,
			},
			method:'POST'
		});
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    //获取收支记录
    items.selectIncomeExpenditure = function($http,pageSize,pageIndex,lingangToken){
    	var request =$http({
			url:'selectIncomeExpenditure2.htm',
			params:{
				"pageSize":pageSize,
				"pageIndex":pageIndex,
				"lingangToken":lingangToken,
			},
			method:'POST'
		});
    	return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
    };
    return ['webService', items];
});