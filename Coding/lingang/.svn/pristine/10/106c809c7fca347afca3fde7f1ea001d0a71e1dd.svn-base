/**
 * Created by FxLsoft on 2015/12/3.
 */
'use strict';

define(["serviceBase"], function (serviceBase) {
	 var items = {};
	 
	 	items.trsPost = function ($http,data) {
	        var request = $http.post('trs/trsPost.do', data, serviceBase.postConfig);
	        return( request.then(serviceBase.handleSuccess, serviceBase.handleError) );
	    };

    return ['trsService', items];
});