/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define([ "serviceBase" ], function(serviceBase) {
	var items = {};
	// 获取管理员列表
	items.sysPolicyPageList = function($http, data) {
		var request = $http.post('policy/queryAllByPage.do', data,
				serviceBase.postConfig);
		return (request
				.then(serviceBase.handleSuccess, serviceBase.handleError));
	};
	// 获取管理员列表
	items.getPolicyDetails = function($http, data) {
		// var policyId=data.policyId;
		var request = $http.post('policy/sysPoliyById.do', data, {
			params : data
		});
		return (request
				.then(serviceBase.handleSuccess, serviceBase.handleError));
	};
	// 修改指定政策
	items.updatePolicyDetails = function($http, data) {
		var request = $http.post('policy/updatePolicyDetails.do', data, {
			params : data
		});
		return (request
				.then(serviceBase.handleSuccess, serviceBase.handleError));
	};

	// 添加政策
	items.addPolicyDetails = function($http, data) {
		var request = $http.post('policy/addPolicyDetails.do', data,
				serviceBase.postConfig);
		return (request
				.then(serviceBase.handleSuccess, serviceBase.handleError));
	};

	return [ 'sysPolicyService', items ];
});