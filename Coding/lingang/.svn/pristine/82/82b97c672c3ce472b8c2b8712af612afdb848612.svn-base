/**
 */

var config = {};
config.paths = {
	'appConfig' : 'configuration/appSetting',
	'mySession' : 'common/my_session',
	'jCookie' : 'library/jquery/cookie/jquery.cookie'
};
config.shim = {
		mySession : ["jCookie"]
};
config.waitSeconds = 120;
requirejs.config(config);
requirejs(['mySession', 'appConfig'], function(mySession, appConfig) {
	var path = "/lingang-platform";
	
	//服务器校验
	window.severCheck = function () {
			var userName = $("#loginname").val();
			var password = $("#password").val();
			$.ajax({
				type : "POST",
				url : path + '/login.htm',
				data : {
					userAccount : userName,
					userPwd : password
				},
				dataType : 'json',
				cache : false,
				success : function(data) {
					if (data.stateCode=="1000") {
						$.cookie("p", data.dataMap.p);
						location.href = "index.html";
					} else if(data.message!=""){
						$("#loginname").tips({
							side : 1,
							msg : data.message,
							bg : '#FF5080',
							time : 3
						});
						$("#loginname").focus();
					}
				}
			});
		}
});