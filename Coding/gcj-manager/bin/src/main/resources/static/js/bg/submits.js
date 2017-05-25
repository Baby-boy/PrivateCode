
var myreg = /^1[34578]\d{9}$/;
var codeReg = /\d{6}/;
var daoji = new gongneng($(".list>span"));
//发送注册手机验证码
function sendRegist(tag){
	var state = $(tag).attr("data-state");
	if(state==0){
		var phoneNumBtn = $(".phoneNum");
		var phoneNum = $(phoneNumBtn).val();
		if(!myreg.test(phoneNum))
		{ 
		    Popups('请输入有效的手机号码！');
		    return;
		}
		var url = "/page/sendCode/registSend";
		var datas = {
			"phone":phoneNum
		}
		var result = function(data){
			Popups(data.msg,function(){
				if(data.code == 200){
					$(phoneNumBtn).focus();
					$(tag).attr("data-state","1");
					daoji.yanzhengma();
				}
			});
		}
		sendPost(url,datas,result);
	}
}

//发送注册手机验证码
function sendforget(tag){
	var state = $(tag).attr("data-state");
	if(state==0){
		var phoneNumBtn = $(".userPhone");
		var phoneNum = $(phoneNumBtn).val();
		if(!myreg.test(phoneNum))
		{ 
		    Popups('请输入有效的手机号码！');
		    return;
		}
		var url = "/page/sendCode/sendforget";
		var datas = {
			"phone":phoneNum
		}
		var result = function(data){
			Popups(data.msg,function(){
				if(data.code == 200){
					$(phoneNumBtn).focus();
					$(tag).attr("data-state","1");
					daoji.yanzhengma();
				}
			});
		}
		sendPost(url,datas,result);
	}
}

function send(tag,phone,sessionName){
	var daoji1 = new gongneng($(tag));
	var state = $(tag).attr("data-state");
	if(state==0){
		var url = "/page/sendCode/send";
		var datas = {
			"phone":phone,
			"sessionName":sessionName
		}
		var result = function(data){
			Popups(data.msg,function(){
				if(data.code == 200){
					$(tag).attr("data-state","1");
					daoji1.yanzhengma();
				}
			});
		}
		sendPost(url,datas,result);
	}
}

function upphoneNum(tag,phone,sessionName){
	var newPhone = $(".newPhone").val();
	var reNewPhone = $(".reNewPhone").val();
	if(newPhone!=""){
		if(newPhone == reNewPhone){
			var daoji1 = new gongneng($(tag));
			var state = $(tag).attr("data-state");
			if(state==0){
				var url = "/page/sendCode/send";
				var datas = {
					"phone":phone,
					"sessionName":sessionName
				}
				var result = function(data){
					Popups(data.msg,function(){
						if(data.code == 200){
							$(tag).attr("data-state","1");
							daoji1.yanzhengma();
						}
					});
				}
				sendPost(url,datas,result);
			}
		}else{
			Popups("两次输入的手机号不一致！");
		}
	}else{
		Popups("请输入新手机号！");
	}
	
}

//实名认证发送验证码
function smrz(tag,phone,sessionName){
	var newPhone = $(".newPhone").val();
	var reNewPhone = $(".reNewPhone").val();
	if(newPhone!=""){
		if(newPhone == reNewPhone){
			var daoji1 = new gongneng($(tag));
			var state = $(tag).attr("data-state");
			if(state==0){
				var url = "/page/sendCode/send";
				var datas = {
					"phone":phone,
					"sessionName":sessionName
				}
				var result = function(data){
					Popups(data.msg,function(){
						if(data.code == 200){
							$(tag).attr("data-state","1");
							daoji1.yanzhengma();
						}
					});
				}
				sendPost(url,datas,result);
			}
		}else{
			Popups("两次输入的手机号不一致！");
		}
	}else{
		Popups("请输入新手机号！");
	}
	
}

var isAgree = 0;
var userType = 1;
function setIsAgree(tag){
	isAgree = isAgree==0?1:0;
	$(tag).attr("value",isAgree);
}
function selType(typeNum){
	userType = typeNum;
}

//注册
function submitRegist(){
	var userPhoneIpt = $("input[name='userPhone']");
	var phoneCodeIpt = $("input[name='phoneCode']");
	var userPwdIpt = $("input[name='userPwd']");
	var userRePwdIpt = $("input[name='userRePwd']");
	var userPhone = $(userPhoneIpt).val();
	var phoneCode = $(phoneCodeIpt).val();
	var userPwd = $(userPwdIpt).val();
	var userRePwd = $(userRePwdIpt).val();
	if(!myreg.test(userPhone)){
		Popups("请输入有效的手机号！");
		return false;
	}else if(!codeReg.test(phoneCode)){
		Popups("请输入正确的验证码！");
		$(phoneCodeIpt).focus();
		return false;
	}else if(userPwd.length < 6){
		Popups("密码为字母、数字、符号且不能小于6位的字符！");
		$(userPwdIpt).focus();
		return false;
	}else if(userRePwd!=userPwd){
		Popups("两次输入的密码不一致！");
		$(userRePwdIpt).focus();
		return false;
	}else if(isAgree==0){
		Popups("必须同意注册协议才能注册账号！");
		return false;
	}
	
	var datas = {
		"userType":userType
	}
	sendAjax('/page/user/regist','mark','','name',datas,function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				location.href="/index";
			}
		});
	});
}

var isAutoLogin = 0
//是否自动登录
function userIsAutoLogin(tag){
	isAutoLogin = isAutoLogin==0?1:0;
	$(tag).val(isAutoLogin)
}

//登录
function submitLogin(loginUserType){
	var url = "/page/user/login";
	var result = function(data){
		if(data.code == 200){
			location.href="/index";
		}else{
			Popups(data.msg);
		}
	}
	sendAjax(url,'login','','name',{"userType":loginUserType},result);
}

//修改昵称
function updateNickname(){
	var nickname = $(".nickname").val();
	var userId = $(".nickname").attr("data");
	var url = "/page/user/updateNickname";
	var datas = {
		"userId":userId,
		"nickname":nickname
	}
	var result = function(data){
		Popups(data.msg);
		if(data.code==200){
			
		}else if(data.code==503){
			window.location.href="/index";
		}
	}
	sendPost(url,datas,result);
}


//修改用户所在地
function updateUserAddr(){
	var userAddr = $(".userAddr").val();
	var userId = $(".userAddr").attr("data");
	var url = "/page/user/updateUserAddr";
	var datas = {
		"userId":userId,
		"userAddr":userAddr
	}
	var result = function(data){
		Popups(data.msg);
		if(data.code==200){
		}else if(data.code==503){
			window.location.href="/index";
		}
	}
	sendPost(url,datas,result);
}

//修改用户简历
function updateUserResume(){
	var userResume = $(".userResume").val();
	var userId = $(".userResume").attr("data");
	var url = "/page/user/updateUserResume";
	var datas = {
		"userId":userId,
		"userResume":userResume
	}
	var result = function(data){
		Popups(data.msg);
		if(data.code==200){
		}else if(data.code==503){
			window.location.href="/index";
		}
	}
	sendPost(url,datas,result);
}

//修改用户邮箱
function updateUserEmail(){
	var userEmail = $(".userEmail").val();
	var userId = $(".userEmail").attr("data");
	var url = "/page/user/updateUserEmail";
	var datas = {
		"userId":userId,
		"userEmail":userEmail
	}
	var result = function(data){
		Popups(data.msg);
		if(data.code==200){
		}else if(data.code==503){
			window.location.href="/index";
		}
	}
	sendPost(url,datas,result);
}

//修改用户QQ号码
function updateUserQQ(){
	var userQQ = $(".userQQ").val();
	var userId = $(".userQQ").attr("data");
	var url = "/page/user/updateUserQQ";
	var datas = {
		"userId":userId,
		"userQQ":userQQ
	}
	var result = function(data){
		Popups(data.msg);
		if(data.code==200){
		}else if(data.code==503){
			window.location.href="/index";
		}
	}
	sendPost(url,datas,result);
}

//申请企业认证
function enterEname(){
	var userEname = $(".userEname").val();
	var state = $(".teamState").val();
	var isResid = $(".isResid:checked").val();
	var emprs = new Array();
	
	var empr1 = {
		empr_name:"张三",
		empr_phone:"133222255555"
	}
	emprs.push(empr1);
	
	var empr2 = {
		empr_name:"李四",
		empr_phone:"15588887777"
	}
	emprs.push(empr2);
	
	if(userEname == ""){
		Popups("请输入公司名称！");
		return;
	}
	if(state == 0){
		Popups("请选择团队当前状态！");
		return;
	}
	if(emprs.length == 0){
		Popups("请添加团队人员信息后再进行团队认证！");
		return;
	}
	var url = "/page/user/enterCFA";
	var datas = {
		"userEname":userEname,
		"emprs":JSON.stringify(emprs),
		"state":state,
		"isResid":isResid
	}
	var result = function(data){
		Popups(data.msg);
	}
	sendPost(url,datas,result);
}



//修改用户工作类型（全职、兼职等）
function addUserOtype(userId){
	
	var otypeBtn = $(".otype .kaiguan[add='ok']");
	
	if(otypeBtn.length>0){
		var url = "/page/user/updateOtype";
		var otypeNum = $(otypeBtn).attr("data");
		var datas = {
				"userId":userId,
				"otypeNum":otypeNum
		}
		var result = function(data){
			Popups(data.msg);
			if(data.code == 200){
				
			}else if(data.code == 506){
				window.location.href="list-login/login";
			}
		}
		sendPost(url,datas,result);
	}else{
		Popups("请选择后再保存！");
	}	
}

var caraisupload = false;
var carbisupload = false;

//提交实名认证信息
function verified(vId,userId,phone){
	var isIdNum = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{4}$/;
	var name = $(".userName").val();
	var phoneCode = $(".phoneCode").val();
	var idNum = $(".idNum").val();
	var yosId = $(".yosId").val();
	var url = "/page/verified/insert";
	if(name==""){
		Popups("请输入真实姓名！");
		return;
	}
	if(idNum == ""){
		Popups("请输入身份证号码！");
		return;
	}
	if(!isIdNum.test(idNum)){
		Popups("身份证号码格式不正确！");
		return;
	}
	if(yosId=="" || yosId == 0){
		Popups("请选择从业年限！");
		return;
	}
	if(!caraisupload){
		Popups("请上传身份证的正面照片！");
		return;
	}
	if(!carbisupload){
		Popups("请上传身份证的背面照片！");
		return;
	}
	if(phoneCode==""){
		Popups("请输入手机验证码！");
		return;
	}
	var datas = {
		"vId":vId,
		"userId":userId,
		"name":name,
		"idNum":idNum,
		"yosId":yosId,
		"phone":phone,
		"phoneCode":phoneCode
	}
	
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code==200){
				window.history.go(0);
			}
		});
	}
	sendPost(url,datas,result);
}


function caraupload(userId) {
    $.ajaxFileUpload({
        url: '/page/verified/caraupload/'+userId,//用于文件上传的服务器端请求地址
        secureuri: false,//一般设置为false
        fileElementId: "card-front",//文件上传空间的id属性  <input type="file" id="file" name="file" />
        dataType: 'json',
        type: "POST",
        success: function (data) {
            Popups(data.msg,function(){
            	if (data.code == 200) {
            		caraisupload = true;
                	$(".caraimgtag").attr("src",data.path);
                }
            });
        }
    });
}

function carbupload(userId) {
    $.ajaxFileUpload({
        url: '/page/verified/carbupload/'+userId,//用于文件上传的服务器端请求地址
        secureuri: false,//一般设置为false
        fileElementId: "card-back",//文件上传空间的id属性  <input type="file" id="file" name="file" />
        dataType: 'json',
        type: "POST",
        success: function (data) {
            Popups(data.msg,function(){
            	if (data.code == 200) {
            		carbisupload = true;
                	$(".carbimgtag").attr("src",data.path);
                }
            });
        }
    });
}
