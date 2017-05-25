var config0 = {};

config0.login_index_url = "/member";
config0.login_submit_url = "/member";
config0.login_logout_url = "/logout";


var auth0 = {};

auth0.logout = function(){
	store.remove('user');
	store.remove('Authorization');
	location.href = config0.login_logout_url;
	auth0.refresh();
}

auth0.login = {
	url : function(args){
		return config0.login_submit_url;
	},
	params : function(args){
		return {
			"username" : $("#username").val(),
			"password" : $("#password").val()
		};
	},
	verification : function(args, params){
		if($("#username").val().length == 0)
		{
			$.alert({
				type:3,
				text:"用户名不能为空"
			});
			return false;
		}
		if($("#password").val().length == 0)
		{
			$.alert({
				type:3,
				text:"密码不能为空"
			});
			return false;
		}
		return true;
	},
	callback : function(args, data){
		if(data.code == 200)
		{
			store.set('Authorization', data.data.token);
			store.set('user', data.data.user);
			autologin();
		}
		else
		{
			$.alert({
				type:3,
				text:"用户名或者密码错误"
			});
		}
	}
};



auth0.autologin = {
	url : function(args){
		return "/member/autologin";
	},
	params : function(args){
		return {};
	},
	verification : function(args, params){
		return true;
	},
	callback : function(args, response){
		console.log(response)
		if(response.code != 401)
		{
			$("body").html(response.responseText);
			auth0.refresh();
		}
	}
};

auth0.refresh = function(){
	console.log("user:");
	console.log(store.get('user'));
	if(store.get('user'))
	{
//		$("#auth_a").html(
//				"欢迎&nbsp;"+ store.get('user_nickname') +
//				"&nbsp;来到多宝鱼&nbsp;&nbsp;" +
//				"<a class=\"login\" href=\"/javascript:void(0)\" onclick='auth0.logout()'>注销</a>"
//			);
	}
	else
	{
//		$("#auth_a").html(
//				"欢迎来到多宝鱼&nbsp;&nbsp;请" +
//				"<a class=\"login\" href=\"/member/login\">登录</a>" +
//				"<a class=\"register\" href=\"/register\">免费注册</a>"
//			);
	}
}


$(function() {
	$.ajaxSetup({
		cache: false,
		beforeSend: function(xhr) {
			xhr.setRequestHeader('Authorization', store.get('Authorization'));
		}
	});
	store.remove('user');
	store.remove('Authorization');	
	auth0.refresh();
	autologin();
})