/**
 * Created by FxLsoft on 2015/12/3.
 */

var config={};
config.paths={
    'jquery': 'library/jquery/jquery-2.1.1/jquery.min',
    'angular': 'library/angular/angular-1.3.9/angular.min',
    'bootstrap':'library/bootstrap/bootstrap-3.3.5/js/bootstrap.min',
    'mySession':'common/my_session',
    'plugin' : 'pcResource/js/plugin',
    'layer':'library/plugins/layer/layer.min'
};
config.shim={
    'angular':{
        deps:['jquery'],
        exports:'angular'
    },
    'bootstrap':{
    	deps:['jquery']
    },
    'plugin':{
    	deps:['jquery']
    },
};
config.priority=["angular"];
config.waitSeconds = 120;
requirejs.config(config);
requirejs( [
        'jquery',
        'angular',
        'mySession',
        'bootstrap',
        'layer'
    ],
    function ( $, angular, mySession) {
        var app = angular.module('BaseApp', [], function () { });
        app.controller('BaseCtrl', function ($scope, $http, $interval, $timeout) {
        	$('.userLogin').show();
            $('.userRegister').hide();
            $('.userPwd').hide();
//            $scope.isLogin = true;
            $scope.userName = "";
            $scope.password = "";
            $scope.seCode = "";
            $scope.btnContent = "发送验证码";
            $scope.showLogin = function(){
//                $scope.isLogin = true;
                $('.userLogin').show();
                $('.userRegister').hide();
                $('.userPwd').hide();
            };
            $scope.showRegister = function(){
//                $scope.isLogin = false;
                $('.userLogin').hide();
                $('.userRegister').show();
                $('.userPwd').hide();
            };
            $scope.showPwd=function(){
            	$('.userLogin').hide();
                $('.userRegister').hide();
                $('.userPwd').show();
            }
            //登录
            $scope.login = function(){
//            	console.log("login");
//                var data = {
//                	telephone:$scope.userName,
//                	password:$scope.password
//                };
//                $http.post("webUser/loginUser.do",data).then(function(data){
//                	$scope.errorTip = data.data.message;
//                    if(data.data.success){
//                        //登录成功
//                    	mySession.setTicket(data.data.data);
//                    	$timeout(function(){
//                    		window.location="index.html";
//                    	},200);
//                    }
//                    $('#myModal').modal();
//                });
            	var mobile = $('#login_mobile').val();
				var psw = $('#login_psw').val();
				if (mobile == "") {
					alert('手机号不能为空');
					return;
				}
				var regu = /^1[3|4|5|7|8][0-9]{9}$/;
				var re = new RegExp(regu);
				if (re.test(mobile) == false) {
					alert("手机号有误");
					$('#login_mobile').focus();
					return;
				}
				if (psw == "") {
					alert('密码不能为空');
					return;
				}
				var data = {
					"tel" : mobile,
					"pwd" : psw
				};
				$.ajax({
					url : 'vipLogin.htm',
					type : 'POST',
					cache : false,
					data : data,
					dataType : 'json',
					success : function(data) {
						console.log(data);
						if (data.ok) {
	                        //登录成功
	                    	mySession.setTicket(data.message);
	                    	$timeout(function(){
	                    		var redirectUrl = mySession.get("redirectUrl");
	                    		if(redirectUrl){
	                    			window.location="index.html#"+redirectUrl;
	                    		}else{
	                    			window.location="index.html";
	                    		}
	                    		
	                    	},200);
						} else {
							alert(data.message);
						}
					},
					error : function(data) {
						alert("connection error");
					},
					beforeSend : function() {
					}
				});
            };
            //注册
            $scope.register = function(){
//                var data = {
//                	user : {
//                		telephone : $scope.userName,
//                		password : $scope.password
//                	},
//                    codeStr : $scope.seCode
//                };
//                $http.post("webUser/addUser.do", data).then(function(data){
//                    if(data.data.success){
//                    	//注册成功
//                    	$scope.isLogin = true;
//                        $('.login').show();
//                        $('.register').hide();
//                    }else{
//                    	alert(data.data.message);
//                    	//注册失败
//                    }
//                });
            	var mobile = $('#reg_mobile').val();
				var reg_yzm = $('#reg_yzm').val();
				var psw = $('#reg_psw').val();
				var psws = $('#reg_psws').val();
				var check = $('.my-check');
				
				if (mobile == "") {
					alert('手机号不能为空');
					return;
				}
				var regu = /^1[3|4|5|7|8][0-9]{9}$/;
				var re = new RegExp(regu);
				if (re.test(mobile) == false) {
					alert("手机号有误");
					$('#reg_mobile').focus();
					return;
				}
				if (reg_yzm == "") {
					alert('验证码不能为空');
					return;
				}
				if (psw == "") {
					alert('密码不能为空');
					return;
				}
				if (psw != psws) {
					alert('两次密码不一致');
					return;
				}
				if(!check.prop('checked')){
					alert('请先同意用户协议');
					return;
				}
				var data = {
					"tel" : mobile,
					"pwd" : psw,
					"verifyCode" : reg_yzm
				}
				$.ajax({
					url : 'vipRegister.htm',
					type : 'POST',
					cache : false,
					data : data,
					dataType : 'json',
					success : function(data) {
						if (data.ok) {
							alert('注册成功!');
							//$scope.showLogin();
							$scope.lingangToken = data.data;
							$http({
					 			url:'selectCouponByCardId.do',
					 			params:{
					 				"getWay":3,
					 				"lingangToken":$scope.lingangToken
					 			},
					 			method:'POST'
					 		}).success(function(data,header,config,status){
					 			console.log(data);
					 			if(data.ok){
					 				alert("恭喜您，获得新用户优惠券");
					 			}
					 			window.location ="login.html";
					 		}).error(function(data,header,config,status){
					 			console.log("系统繁忙，请稍后再试");
					 		});	 
							
						} else {
							alert(data.message);
						}
					},
					error : function(data) {
						alert("connection error");
					},
					beforeSend : function() {
					}
				});
            };
            //获取验证码(注册)
            $scope.reg_getSeCode = function(){
//                var count = 60;
//                var timer = $interval(function(){
//                	count--;
//                    if(count > 0){
//                        $scope.btnContent = count + "秒后可重新获取";
//                    }else{
//                    	$interval.cancel(timer);
//                        $scope.btnContent = "发送验证码";
//                    }
//                }, 1000);
//                
//                var data = {
//                	telephone : $scope.userName
//                };
//                $http.post("webUser/getMobileCodeByRegister.do", data).then(function(data){
//                    if(data.data.success){
//                        alert("获取验证码成功");
//                    }else{
//                        alert(data.data.message);
//                    }
//                })
            	var mobile = $('#reg_mobile').val();
				if (mobile == "") {
					alert('手机号不能为空');
					return;
				}
				var regu = /^1[3|4|5|7|8][0-9]{9}$/;
				var re = new RegExp(regu);
				if (re.test(mobile) == false) {
					alert("手机号有误");
					$('#reg_mobile').focus();
					return;
				}
				var data = {
					"tel" : mobile,
					"type" : 1
				}
				$.ajax({
					url : 'getVerifyCode.htm',
					type : 'POST',
					cache : false,
					data : data,
					dataType : 'json',
					success : function(data) {
						if (data.ok) {
							var num = 60;
							var node = $('#reg_getyzm');
							$('#reg_getyzm').attr('disabled', true);
							var interval = setInterval(function() {
								node.text(num);
								--num;
								if (num == 0) {
									clearInterval(interval);
									node.text('获得验证码');
									$('#reg_getyzm').attr('disabled', false);
								}
							}, 1000);
						} else {
							alert("短信获取失败，请稍后再试");
						}
					},
					error : function(data) {
						alert("connection error");
					},
					beforeSend : function() {
					}
				});
            }
            //重置密码
            $scope.userPWD = function(){
            	var mobile = $('#pwd_mobile').val();
				var pwd_yzm = $('#pwd_yzm').val();
				var psw = $('#pwd_psw').val();
				var psws = $('#pwd_psws').val();
				if (mobile == "") {
					alert('手机号不能为空');
					return;
				}
				var regu = /^1[3|4|5|7|8][0-9]{9}$/;
				var re = new RegExp(regu);
				if (re.test(mobile) == false) {
					alert("手机号有误");
					$('#pwd_mobile').focus();
					return;
				}
				if (pwd_yzm == "") {
					alert('验证码不能为空');
					return;
				}
				if (psw == "") {
					alert('密码不能为空');
					return;
				}
				if (psw != psws) {
					alert('两次密码不一致');
					return;
				}
				var data = {
					"tel" : mobile,
					"pwd" : psw,
					"verifyCode" : pwd_yzm
				}
				$.ajax({
					url : 'forgetPWD.htm',
					type : 'POST',
					cache : false,
					data : data,
					dataType : 'json',
					success : function(data) {
						if (data.ok) {
							alert('修改成功!');
//							$scope.showLogin();
							window.location ="login.html";
						} else {
							alert(data.message);
						}
					},
					error : function(data) {
						alert("connection error");
					},
					beforeSend : function() {
					}
				});
            };
            //获取验证码(忘记密码)
            $scope.pwd_getSeCode = function(){
            	var mobile = $('#pwd_mobile').val();
				if (mobile == "") {
					alert('手机号不能为空');
					return;
				}
				var regu = /^1[3|4|5|7|8][0-9]{9}$/;
				var re = new RegExp(regu);
				if (re.test(mobile) == false) {
					alert("手机号有误");
					$('#pwd_mobile').focus();
					return;
				}
				var data = {
					"tel" : mobile,
					"type" : 1
				}
				$.ajax({
					url : 'getVerifyCode.htm',
					type : 'POST',
					cache : false,
					data : data,
					dataType : 'json',
					success : function(data) {
						if (data.ok) {
							var num = 60;
							var node = $('#pwd_getyzm');
							$('#pwd_getyzm').attr('disabled', true);
							var interval = setInterval(function() {
								node.text(num);
								--num;
								if (num == 0) {
									clearInterval(interval);
									node.text('获得验证码');
									$('#pwd_getyzm').attr('disabled', false);
								}
							}, 1000);
						} else {
							alert("短信获取失败，请稍后再试");
						}
					},
					error : function(data) {
						alert("connection error");
					},
					beforeSend : function() {
					}
				});
            }
        });
        $(document).ready(function () {
            angular.bootstrap(document, ['BaseApp']);
        });
    }
);
