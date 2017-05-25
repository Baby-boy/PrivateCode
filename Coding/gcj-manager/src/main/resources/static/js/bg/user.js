$(".touxiang input").change(function () {
	var userId = $(".headimgbtn").attr("data");
	var urls = '/page/file/uploadheadimg/'+userId;
	$.ajaxFileUpload({
 		url:urls,//用于文件上传的服务器端请求地址  
        secureuri:false,//一般设置为false
        fileElementId:"m",//文件上传空间的id属性  <input type="file" id="file" name="file" />  
        dataType: 'json',
        type:"POST",
        success: function (data){
         	if(data.code == 200){
         	   $(".headimg").attr("src",data.path);
         	   window.history.go(0);
         	}
        }
     });
});
//创建Blob对象
function getObjectURL(file) {
    var url = null ;
    if (window.createObjectURL!=undefined) {
        url = window.createObjectURL(file) ;
    } else if (window.URL!=undefined) {
        url = window.URL.createObjectURL(file) ;
    } else if (window.webkitURL!=undefined) {
        url = window.webkitURL.createObjectURL(file) ;
    }
    return url ;
}


var myreg = /^1[34578]\d{9}$/;
var codeReg = /\d{6}/;
//修改登录密码
function updateLoginPwd(userId){
	
	var oldPwd = $(".oldPwd").val();
	var newPwd = $(".newPwd").val();
	var reNewPwd = $(".reNewPwd").val();
	
	if(oldPwd != ""){
		if(newPwd != ""){
			if(newPwd == reNewPwd){
				
				var url = "/page/user/updateLoginPwd";
				var datas = {
					"userId":userId,
					"oldPwd":oldPwd,
					"newPwd":newPwd
				}
				var result = function(data){
					Popups(data.msg,function(){
						if(data.code==200){
							window.history.go(0);
						}
					});
				}
				sendPost(url,datas,result);
				
			}else{
				Popups("两次密码不一致！");
			}
		}else{
			Popups("请输入新密码！");
		}
	}else{
		Popups("请输入当前密码！");
	}
}

//修改交易密码
function updatePayPwd(userId,phoneNum){
	var phoneCode = $(".phoneCode").val();
	var payPwd = $(".payPwd").val();
	var rePayPwd = $(".rePayPwd").val();
	if(phoneCode != ""){
		if(payPwd != ""){
			if(payPwd == rePayPwd){
				var url = "/page/user/updatePayPwd";
				var datas = {
					"userId":userId,
					"phoneNum":phoneNum,
					"phoneCode":phoneCode,
					"payPwd":payPwd
				}
				var result = function(data){
					Popups(data.msg,function(){
						if(data.code==200){
							window.history.go(0);
						}else if(data.code == 600){
							window.location.href = "/list-login/login";
						}
					});
				}
				sendPost(url,datas,result);
				
			}else{
				Popups("两次密码不一致！");
			}
		}else{
			Popups("请输入新交易密码！");
		}
	}else{
		Popups("请输入手机验证码！");
	}
}

//忘记密码
function forget(){
	var userPhone = $(".userPhone").val();
	var userPhoneCode = $(".userPhoneCode").val();
	var userPwd = $(".userPwd").val();
	var userPwdRe = $(".userPwdRe").val();
	
	if(userPhone == ""){
		Popups("请输入手机号！");
		return;
	} 
	if(!myreg.test(userPhone)){
		Popups("请输入有效手机号码！");
		return;
	}
	if(userPwd == ""){
		Popups("请输入密码！");
		return;
	}
	if(userPwd.length < 8){
		Popups("密码不能小于8位英文、数字、字符组合！");
		return;
	}
	if(userPwd != userPwdRe){
		Popups("两次密码输入不一致！");
		return;
	}
	var url = "/page/user/forget";
	var datas = {
		"userPhone":userPhone,
		"userPhoneCode":userPhoneCode,
		"userPwd":userPwd,
		"userPwdRe":userPwdRe
	}
	var result = function(data){
		Popups(data.msg,function(){
			if(data.code == 200){
				window.location.href="/index"
			}
		});
	}
	sendPost(url,datas,result);
}

//修改用户账号（手机号）
function updateUserPhone(userId,oldPhoneNum){
	var newPhone = $(".newPhone").val();
	var reNewPhone = $(".reNewPhone").val();
	var phoneCode = $(".smsCode").val();
	
	if(newPhone!=""){
		if(newPhone == reNewPhone){
			if(phoneCode != ""){
				var url = "/page/user/updateUserPhone";
				var datas = {
					"userId":userId,
					"phoneCode":phoneCode,
					"oldPhoneNum":oldPhoneNum,
					"newPhoneNum":newPhone
				}
				var result = function(data){
					Popups(data.msg,function(){
						if(data.code==200){
							window.history.go(0);
						}else if(data.code == 600){
							window.location.href = "/list-login/login";
						}
					});
				}
				sendPost(url,datas,result);
			}else{
				Popups("请输入验证码！");
			}
		}else{
			Popups("两次输入的手机号不一致！");
		}
	}else{
		Popups("请输入新手机号！");
	}
}


//取消收藏
function clearCollect(taskId){
	if(window.confirm("你确定要取消收藏吗？")){
		var url = "/page/collection/delete";
		var datas = {
				"taskId":taskId
		}
		var result = function(data){
			Popups(data.msg,function(){
				if(data.code == 200){
					$(".colle"+taskId).remove();
				}else if(data.code == 600){
					window.location.href="/list-login/login";
				}
			});
		}
		sendPost(url,datas,result);
	}
}

//添加用户技能标签
var isUploadLabelFiles = 0;
function labelFileChange(){
	isUploadLabelFiles = 1;
}
function userLabel(){
	if(isUploadLabelFiles == 1){
		var labels = $(".myapprove *[class='now']");
		var labelIds = "";
		for (var i = 0; i < labels.length; i++) {
			var labelId = $(labels[i]).attr("data");
			if (labelId != 0) {
				if (labelIds.length > 0)
					labelIds += ",";
				labelIds += labelId;
			}
		}
		if (labelIds.length > 0) {
			var url = "/page/user/userLabels";
			var datas = {
				"labelIds" : labelIds
			}
			var result = function(data) {
				Popups(data.msg, function() {
					if (data.code == 200) {
						window.history.go(0);
					}
				});
			}
			sendPost(url, datas, result);
		}else{
			Popups("请选择专业技能!");
			return;
		}
	}else{
		Popups("请上传专业认证文件再提交申请!");
		return;
	}
}


//职称认证
var isUploadJobTitleFiles = 0;
function uploadJobTitleFiles(){
	isUploadJobTitleFiles = 1;
}
function jobTitleVerified(){
	if(isUploadJobTitleFiles == 1){
		var level = $(".jobtitle").val();
		if(level == 0){
			Popups("请选择职称!");
			return;
		}
		var url = "/page/user/jobTitleVerified";
		var datas = {
			"level":level
		}
		var result = function(data){
			Popups(data.msg,function(){
				if(data.code == 200){
					window.history.go(0);
				}
			});
		}
		sendPost(url,datas,result);
	}else{
		Popups("请上传认证文件后再提交认证内容!");
		return;
	}
}

//发送邀请消息
function inviteInDetail(userId){
	var url = "/page/message/invite";
	var contents = $(".contents").val();
	var taskId = $(".selectTask").val();
	if(taskId == 0){
		Popups("请选择任务！");
		return;
	}
	if(contents == ""){
		Popups("请填写用于告知对方的内容！");
		return;
	}
	var datas = {
		"userBId":userId,
		"taskId":taskId,
		"contents":contents
	}
	var result = function(data){
		Popups(data.msg);
	}
	sendPost(url,datas,result);
}

