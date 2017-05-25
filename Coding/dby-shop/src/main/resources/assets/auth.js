auth0_request_member_login = {
	url : function(args){
		return "/login";
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
				info:"用户名不能为空"
			});
			return false;
		}
		if($("#password").val().length == 0)
		{
			$.alert({
				type:3,
				info:"密码不能为空"
			});
			return false;
		}
		return true;
	},
	callback : function(args, data){
		if(data.code == 200)
		{
			SetCookie("dbyLoginState", 1);
			if( $("#remember").prop("checked") ){
				SetCookie("dbyUsername", $("#username").val());
				SetCookie("dbyPassword", $("#password").val());
			}else{
				DelCookie("dbyLoginState");
				DelCookie("dbyUsername");
				DelCookie("dbyPassword");
			}
			
			window.location.href = "/";
			store.set('Authorization', data.data.token);
			store.set('user', data.data.user);
//			auth0_event_member_autologin();
		}
		else
		{
			$.alert({
				type:3,
				info:"用户名或者密码错误"
			});
		}
	}
};

auth0_request_member_autologin = {
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
		if(response.status != 401){
			$("body").html(response.responseText);
//			auth0_event_refresh();
		}
	}
};

function auth0_event_member_logout() {
	$.ajax({
		url: "/member/logout",
		type: "post",
		data: {},
		success: function() {
			store.remove('user');
			store.remove('Authorization');
			location.href = "/login";
//			auth0_event_refresh();
		},
		error: function() {
			$.alert({info: "退出失败！"});
		}
	});
}
/*
auth0_event_member_logoutt = {
		url : function(args){
			return "/member/logout";
		},
		params : function(args){
			return {};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, response){
			store.remove('user');
			store.remove('Authorization');
			location.href = "/login";
			auth0_event_refresh();
		}
	};
*/
//auth0_event_member_logout = function(){
//	store.remove('user');
//	store.remove('Authorization');
//	location.href = "/member";
//	auth0_event_refresh();
//};

auth0_event_refresh = function(){
//	if(store.get('user'))
//	{
//		$("#auth_a").html(
//				"欢迎&nbsp;"+ store.get('user').user_nickname +
//				"&nbsp;来到多宝鱼&nbsp;&nbsp;" +
//				"<a class=\"login\" href=\"javascript:void(0);\" onclick='auth0_event_member_logout( $(this) )'>注销</a>"
//			);
//	}
//	else
//	{
//		$("#auth_a").html(
//				"欢迎来到多宝鱼&nbsp;&nbsp;请" +
//				"<a class=\"login\" href=\"/member/login\">登录</a>" +
//				"<a class=\"register\" href=\"/register\">免费注册</a>"
//			);
//	}
};


//$(function() {
//	$.ajaxSetup({
//		cache: false,
//		beforeSend: function(xhr) {
//			xhr.setRequestHeader('Authorization', store.get('Authorization'));
//		}
//	});
	
//	var xmlhttp = new XMLHttpRequest();
//	xmlhttp.open("GET","/",true); 
//	xmlhttp.setRequestHeader("Authorization", store.get('Authorization'));
	
//	 $("meta[name='Authorization']").attr("content", store.get("Authorization"));
//	auth0_event_refresh();
	/*
//	auth0_event_member_autologin();
	*/
//})


// 用户注册


auth0_request_member_register = {
	url : function(args){
		return "/member";
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
				info:"用户名不能为空"
			});
			return false;
		}
		if($("#password").val().length == 0)
		{
			$.alert({
				type:3,
				info:"密码不能为空"
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
//			auth0_event_member_autologin();
		}
		else
		{
			$.alert({
				type:3,
				info:"用户名或者密码错误"
			});
		}
	}
};

auth0_request_member_register_sms = {
	url : function(args){
		return "/register_member_sms";
	},
	params : function(args){
		return { 
			"mobile" : $("#member_mobile").val()
		};
	},
	verification : function(args, params){
		return true;
	},
	callback : function(args, response){
		console.log(response)
	}
};


$(document).ready(function(){
	
});
$(function() {
	/**
	 * 记住账号 - 自动登录
	 */
	if ( GetCookie("dbyLoginState") != 1 && GetCookie("dbyUsername") != null && GetCookie("dbyUsername") != "" ) {
		member_login_automatic();
	}
});



member_login_automatic_ = {
		url : function(args){
			return "/login";
		},
		params : function(args){
			return {
				"username" : GetCookie("dbyUsername"),
				"password" : GetCookie("dbyPassword")
			};
		},
		verification : function(args, params){
			return true;
		},
		callback : function(args, data){
			if(data.code == 200)
			{
				window.location.href = "/";
				store.set('Authorization', data.data.token);
				store.set('user', data.data.user);
//				auth0_event_member_autologin();
			}
			else
			{
				$.alert({
					type:3,
					info:"用户名或者密码错误"
				});
			}
		}
	};