(function($){
	
	verification = {
		checkMobile:function(data){
			var mobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[0678]|18[0-9]|14[57])[0-9]{8}$/;//手机号码正则
			if(mobile.test(data)){
				return true;
			}else{
				return false;
			}
		},
		checkEmail:function(data){
			var email = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/;//邮箱
			if(email.test(data)){
				return true;
			}else{
				return false;
			}
		},
		checkLength:function(data,minlength,maxlength){
			if( data.length > minlength && data.length < maxlength ){
				return true;
			}else{
				return false;
			}
		},
		checkCard:function(data){
			var card=/^(\d{18,18}|\d{15,15}|\d{17,17}x)$/; //身份证号码正则表达式
			if(card.test(data)){
				return true;
			}else{
				return false;
			}
		},
		checkPwd:function(data,minlength,maxlength){
			var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
			var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
			var enoughRegex = new RegExp("(?=.{6,}).*", "g");
			if( data.length > minlength && data.length < maxlength ){
				if (strongRegex.test(data)){
					return "1";
				} else if(mediumRegex.test(data)){
					return "2";
				} else if(enoughRegex.test(data)){
					return "3";
				}
			}else{
				return false;
			}
		}
	};
})(jQuery);

