<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/view/include/taglibs.jspf" %>
<!DOCTYPE html>
<html class="no-js">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>登录</title>
		<meta name="description" content="">
		<%@ include file="/WEB-INF/view/include/head_include.jspf" %>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="${ctx}/css/employee.css">
		<style>
			#login{
				background: url(${ctx}/images/bg-login.jpg) center;
				background-size:100%;
				position: absolute;
				top:0;
				bottom:0;
				left:0;
				right:0;
			}
			
			.login-title{
				font-size: 16px;	
			}
			
			.login-box{
				width:400px;
				position: absolute;
				top:50%;
				margin-top: -180px ;
				left: 50%;
				margin-left: 50px;
			}
		</style>
	</head>
	<body>
		<div id="login">
			<el-card class="login-box">
				<div slot="header">
					<h1 class="login-title">登录</h1>
				</div>
				<el-form label-position="right" label-width="70px">
					<el-form-item label="用户名">
						<el-input v-model="user.name" placeholder="请输入用户名"></el-input>
					</el-form-item>
					<el-form-item label="密　码">
						<el-input v-model="user.pwd" type="password" placeholder="请输入密码" @keyup.enter.native="login"></el-input>
					</el-form-item>
					<el-form-item>
						<el-button type="primary" :loading="status.loading" @click="login" >{{status.loginBtnText}}</el-button>
						<el-button  @click="reset" >重置</el-button>
					</el-form-item>
				</el-form>
			</el-card>
		</div>
		<script>
			new Vue({
				el: '#login',
				mounted: function() {
				},
				data: {
					user:{
						name:"",
						pwd:""
					},
					status:{
						loading:false,
						loginBtnText:"登录"
					},
					urls:{
						login:"${ctx}/api/user/login",
						mainPage:"${ctx}/pageTo/attendance/attendanceList?viewName=2"
					}
				},
				methods: {
					switchLoginBtnStatus:function(loading){
						if(loading){
							this.status.loading=true;
							this.status.loginBtnText="加载中..."
						}else{
							this.status.loading=false;
							this.status.loginBtnText="登录"
						}
					},
					reset:function(){
						this.user.name="";
						this.user.pwd="";
					},
					login:function(){
						var vm = this;
						
						if(!vm.user.name){
							vm.$message.error("请输入用户名");
							return;
						}
						
						if(!vm.user.pwd){
							vm.$message.error("请输入密码");
							return;
						}
						
						vm.switchLoginBtnStatus(true);
						Vue.http.get(vm.urls.login +"?name="+vm.user.name+"&pwd="+vm.user.pwd).then(function(response){
							response.json().then(function(responseData){
								if(responseData.code == "200"){
									window.location = vm.urls.mainPage;
								}else{
									if(responseData.code == "204"){
										vm.$message.error("用户名或密码错误");
									}else{
										vm.$message.error("发生未知错误， 请您稍后重试");
									}
									vm.switchLoginBtnStatus(false);
								} 
							});
						}).catch(function(response){
							vm.$message.error(response);
							vm.switchLoginBtnStatus(false);
						});
					}
				}
			})
		</script>
	</body>
</html>