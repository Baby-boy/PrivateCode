// 用户注册

auth0_request_member_register = {
	url : function(args){
		return "/register_member";
	},
	params : function(args){
		return {
			"user_username" : $("#member_name").val(),
			"mobile" : $("#member_mobile").val(),
			"password" : $("#member_password").val(),
			"token" : $("#member_token").val(),
			"salt" : $("#member_salt").val()
		};
	},
	verification : function(args, params){
		
		if($("#member_name").val().length == 0)
		{
			$.alert({
				type:3,
				info:"会员名不能为空"
			});
			return false;
		}
		
		if($("#member_name").val().length <= 3 )
		{
			$.alert({
				type:3,
				info:"会员名必须大于三个字符"
			});
			return false;
		}
		
		if($("#member_mobile").val().length == 0)
		{
			$.alert({
				type:3,
				info:"手机号不能为空"
			});
			return false;
		}
		

		if($("#member_salt").val().length == 0)
		{
			$.alert({
				type:3,
				info:"验证码不能为空"
			});
			return false;
		}
		if($("#member_password").val().length == 0)
		{
			$.alert({
				type:3,
				info:"密码不能为空"
			});
			return false;
		}
		if($("#member_password").val().length < 6)
		{
			$.alert({
				type:3,
				info:"密码长度必须大于6位"
			});
			return false;
		}
		if ($("#member_password").val() != $("#member_repeat_password").val()) {
			$.alert({
				type:3,
				info:"两次密码输入不一致"
			});
			return false;
		}
		
		if ( $("#agree").prop("checked") == false ) {
			$.alert({
				type:3,
				info:"未同意《多宝鱼用户注册协议》"
			});
			return false;
		}
		
		return true;
	},
	callback : function(args, data){
		if(data.code == 401)
		{
			$.alert({
				type:3,
				info: data.msg
			});
		}
		else
		{
			store.set('Authorization', data.data.token);
			store.set('user', data.data.user);
			window.location.href = "/member"; 
		}
	}
};

auth0_request_member_register_sms = {
	url : function(args){
		return "/register_member_sms";
	},
	params : function(args){
		return { 
			"mobile" : $(args).parent().prev().find("input").val()
		};
	},
	verification : function(args, params){
		var moValue = $(args).parent().prev().find("input").val();
		if ( moValue == "" || moValue == undefined || !_mobileReg.test( moValue ) ) {
			$.alert({
				type: 3,
				info: "请输入有效的手机号！"
			});
			return false;
		}
		return true;
	},
	callback : function(args, response){
		if ( response.code == 401 ) {
			$.alert({
				type: 3,
				info: response.msg
			});
		} else {
			_token = response.data.token
			$("#member_token").val( response.data.token );
			$(args).hide();
			$(args).next().css("display","block");
			$(args).next().find(".repeat").css("display","block");
			obt();
		}
	}
};


/**
 * 忘记密码 - 发送短信验证码
 */
forgetPasswordSms_ = {
	url : function(args){
		return "/forgetPasswordSms";
	},
	params : function(args){
		return { 
			"mobile" : $(args).parent().prev().find("input").val()
		};
	},
	verification : function(args, params){
		var moValue = $(args).parent().prev().find("input").val();
		if ( moValue == "" || moValue == undefined || !_mobileReg.test( moValue ) ) {
			$.alert({
				type: 3,
				info: "请输入有效的手机号！"
			});
			return false;
		}
		return true;
	},
	callback : function(args, response){
		if ( response.code == 401 ) {
			$.alert({
				type: 3,
				info: response.msg
			});
		} else {
			_token = response.data.token
			$("#member_token").val( response.data.token );
			$(args).hide();
			$(args).next().css("display","block");
			$(args).next().find(".repeat").css("display","block");
			obt();
		}
	}
};



//用户注册

forgetPassword_ = {
	url : function(args){
		return "/forgetPassword";
	},
	params : function(args){
		return {
			"mobile" : $("#member_mobile").val(),
			"password" : $("#member_password").val(),
			"password_repeat" : $("#member_repeat_password").val(),
			"token" : $("#member_token").val(),
			"salt" : $("#member_salt").val()
		};
	},
	verification : function(args, params){
		return true;
	},
	callback : function(args, data){
		if ( data.success ) {
			$.alert({
				type: 4,
				info: data.info
			});
			setTimeout(function() {
				window.location.href = "/login";
			}, 2000);
		} else {
			$.alert({
				type: 3,
				info: data.info
			});
		}
	}
};





/**
 * 商家注册
 */
var verify = true;
auth_request_seller_register = {
	url : function(args){
		return "/register_seller";
	},
	params : function(args){
		var value = $("#sellerRegisterForm").serializeArray();
		var formValue = {};
		$.each(value, function(k, v) {
			verify = true;
			if ( $("#sellerRegisterForm").find('*[name="'+v["name"]+'"]').attr("data-verify") == "true" && $("#sellerRegisterForm").find('*[name="'+v["name"]+'"]').val() == "" ) {
				verify = false;
				$.alert({info: $("*[name='"+v["name"]+"']").attr("data-error-info") });
				return false;
			}
			formValue[ v["name"] ] = v["value"];
		});
		formValue["token"] = _token;
		return formValue;
	},
	verification : function(args, params){
		if ( $("#sellerRegisterForm input[name='user_username']").val() <= 3 ) {
			$.alert({
				type:3,
				info:"会员名必须大于三个字符"
			});
			return false;
		}
		
		if ( !verify ) {
			return false;
		}
		
		if ( $("#sellerRegisterForm input[name='password']").val() != $("#sellerRegisterForm input[name='password_confirmation']").val() ) {
			$.alert({
				type:3,
				info:"两次密码输入不一致"
			});
			return false;
		}
		
		if ( $("#sellerRegisterForm input[name='agree']").prop("checked") == false ) {
			$.alert({
				type:3,
				info:"未同意《多宝鱼用户注册协议》"
			});
			return false;
		}
		return true;
	},
	callback : function(args, data){
		if(data.code == 401)
		{
			$.alert({
				type:3,
				info: data.msg
			});
		}
		else
		{
			store.set('Authorization', data.data.token);
			store.set('user', data.data.user);
			window.location.href = "/member"; 
		}
	}
};


