var myservices = angular.module('myservices', []);
myservices.factory('myservices', [ '$http', function($http, $scope) {
	return {
//		url : 'http://localhost:8080',
		url : 'http://www.lingangsd.com',
		postConfig : {
			headers : {
				'Content-Type' : 'application/json',
				'Accept' : 'application/json',
				"Cache-Control" : "no-cache"
			},
			transformRequest : function(data) {
				return JSON.stringify(data);

			}
		},
		errorCode : function(data) {
		}
	};

} ]);

function wxjump(url) {
	if (url != null && url != undefined && url != '') {
		window.location = url;
	} else {
		alert("地址为空");
	}

}

(function(){
        var $dialog = $('.weui_dialog_alert');
//        $dialog.show();
        $dialog.find('.weui_btn_dialog').bind('click', function () {
            $dialog.hide();
        });
})();