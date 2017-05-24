<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/view/include/taglibs.jspf" %>
<!DOCTYPE html>
<html class="no-js">
	<head>
		<meta charset="utf-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge">
		<title>员工列表</title>
		<meta name="description" content="">
		<%@ include file="/WEB-INF/view/include/head_include.jspf" %>
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" href="${ctx}/css/employee.css">
	</head>
	<body>
		<%@ include file="/WEB-INF/view/components/page_top.jspf" %>
		<%@ include file="/WEB-INF/view/components/search.jspf" %>
		<%@ include file="/WEB-INF/view/components/left_nav.jspf" %>
		<%@ include file="/WEB-INF/view/components/rightCard.jspf" %>
		<%@ include file="/WEB-INF/view/components/header.jspf" %>
		<div class="wrap">
			<div class="warpInner" id="elPageTop" v-cloak>		
			<header-nav></header-nav>	
				<!--实例-->
				<div class="contentWrap">
					<div>
						<left-nav :left-list="leftList" :current="0"></left-nav>
						<!--顶部搜索组件-->
						<page-top  page-title="员工管理" page-description="在这里您可以对公司进行考勤信息进行设置">
							<div class="pageTopButton fr">
								<el-button type="button" class="el-button el-button--primary" :disabled="true"><i class="el-icon-plus"></i> 新建</el-button>
							</div>
							<div class="pageTopSearch fr">
								<search search-tips="搜索姓名或者职员代码..." @search="searchAttendance"></search>
							</div>
						</page-top>
						<!--列表操作按钮-->
						<div class="pageOperate clearfix">
							<div class="leftBtns fl">
								<el-button type="primary" class="iconfont icon-leave" size="small"  :disabled="true"> 离职</el-button>
								<el-button type="primary" class="iconfont icon-export" size="small" :disabled="true"> 导出</el-button>
							</div>
							<!--右侧高级搜索-->					
							<a href="javascript:;" class="fr searchMore" @click="moreSearch"><i class="iconfont icon-sort"></i>高级搜索</a>
						</div>
						<right-card card-title="筛选器" width="350px"  :show="showRight" @hide="showRightCard" ref="rightCard">
							<div class="searchWrap">
								<ul>
									<li>
										<div class="searchTitle"><i class="iconfont icon-circle"></i> <span>选择公司</span></div>
										<div class="searchWhere">
											<el-select v-model="companyName" placehodler="请选择" :disabled="true">
												<el-option :label="item.label" :value="item.value" v-for="item in conpany"></el-option>
											</el-select>
										</div>
									</li>
									<li>
										<div class="searchTitle"><i class="iconfont icon-circle"></i> <span>选择部门</span></div>
										<div class="searchWhere">
											<el-button type="text" :disabled="true">经营企划</el-button>
										</div>
									</li>
									<li>
										<div class="searchTitle"><i class="iconfont icon-circle"></i> <span>选择职位</span></div>
										<div class="searchWhere">
											<el-select v-model="jobText" :disabled="true">
												<el-option :value="item.value" :label="item.label" v-for="item in job"></el-option>
											</el-select>
										</div>
									</li>
									<li>
										<div class="searchTitle"><i class="iconfont icon-circle"></i> <span>在职状态</span></div>
										<div class="searchWhere">
											<div class="jobStateWrap">
												<el-checkbox-group v-model="jobState">
													<el-checkbox :label="state.id" v-for="state in states">{{state.state}}</el-checkbox>
												</el-checkbox-group>
												<!--<el-button v-for="(btn,index) in btnList" class="active" size="small" @click="jobSatate(index)">{{btn.text}}</el-button>-->
											</div>
										</div>
									</li>
									<li>
										<div class="searchTitle"><i class="iconfont icon-circle"></i> <span>入职时间</span></div>
										<div class="searchWhere">
											<div class="dateChoice">
												<el-date-picker type="date" placeholder="开始日期" v-model="entryDateFrom"></el-date-picker> ~
												<el-date-picker type="date" placeholder="结束日期" v-model="entryDateTo"></el-date-picker>
											</div>
										</div>
									</li>
									<li>
										<div class="searchTitle"><i class="iconfont icon-circle"></i> <span>转正时间</span></div>
										<div class="searchWhere">
											<div class="dateChoice">
												<el-date-picker type="date" placeholder="开始日期" v-model="formalDateFrom"></el-date-picker> ~
												<el-date-picker type="date" placeholder="结束日期" v-model="formalDateTo"></el-date-picker>
											</div>
										</div>
									</li>
									<li>
										<div class="searchTitle"><i class="iconfont icon-circle"></i> <span>离职时间</span></div>
										<div class="searchWhere">
											<div class="dateChoice">
													<el-date-picker type="date" placeholder="开始日期" v-model="quitDateFrom"></el-date-picker> ~
													<el-date-picker type="date" placeholder="结束日期" v-model="quitDateTo"></el-date-picker>
											</div>
										</div>
									</li>
								</ul>
								<div class="searchBtns">
									<el-button type="primary" class="el-button el-bitton--primary" @click="goToSearch">
										<span>确定</span>
									</el-button>
									<el-button type="button" class="el-button el-bitton-default" @click="resetSearch">
										<span>清空</span>
									</el-button>
								</div>
							</div>
						</right-card>
						<!--员工信息列表-->
						<el-table  :data="employeeList.list" stripe v-loading="isloading">
							<el-table-column label="全选" type="selection">
								<el-checkbox></el-checkbox>
							</el-table-column>
							<el-table-column label="姓名" align="center" prop="name">
								<el-checkbox-group></el-checkbox-group>
							</el-table-column>
							<el-table-column label="职员代码" align="center" prop="code"></el-table-column>
							<el-table-column label="部门名称" align="center" prop="deptName"> </el-table-column>
							<el-table-column label="职位" align="center" prop="jobName"></el-table-column>
							<el-table-column label="在职状态" align="center" prop="jobStateName"></el-table-column>
							<el-table-column label="入职时间" align="center" prop="entryDate"></el-table-column>
							<el-table-column label="转正时间" align="center" prop="formalDate"></el-table-column>
							<el-table-column label="离职时间" align="center"prop="quitDate"></el-table-column>
							<el-table-column colspan="2" align="center" label="操作">
								<template scope="scope">
									<!--<el-button type="text" size="small">删除</el-button>-->
									<el-button type="text" size="small" @click="seeMsg(scope.row.id,scope.row.code)">采集信息</el-button>
								</template>
							</el-table-column>
						</el-table>
						<div class="pagination">
							<el-pagination layout="prev,pager,next" :current-page="page" :page-size="pageSize" @current-change="handleSizeChange" :total="employeeList.totalCount"></el-pagination>
						</div>
					</div>
				</div>
				<!--查看信息采集弹窗-->
				<div class="msgPopWrap" @click="closePop" :class="{isShow:popShow}">
				<div class="msgPopContent">
					<p class="popTitle">信息采集</p>
					<i class="closeIcon iconFont el-icon-close" @click="closePop"></i>
					<table class="msgTable">
						<tr>
							<td>指纹信息：</td>
							<td>
								<img :src="hadFinger?'../../images/hadMsg.png':'../../images/noMsg.png'" width="60" height="60" />
							</td>
						</tr>
						<tr>
							<td>面部信息：</td>
							<td>
								<img :src="hadFace?'../../images/hadMsg.png':'../../images/noMsg.png'" width="60" height="60" />
							</td>
						</tr>
						<tr>
							<td colspan="2">考勤地点：</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="checkWorkWrap">
									<div class="defaultCheck clearfix">
									<span class="fl">　考勤点：</span>
									<div class="fl checkWrap" v-if="message.signPoints && message.signPoints.length > 0">
										<span  v-for="general in message.signPoints">{{general.signPointName}}</span>
									</div>
									<div class="fl checkWrap" v-else>
										暂无数据
									</div>
									</div>
									<div class="chesckpart clearfix">
										<span class="fl">考勤群组：</span>
										<div class="fl checkWrap" v-if="message.signGroups && message.signGroups.length > 0">
											<template v-for="groups in message.signGroups">
												<span>{{groups.signGroupName}}</span>&nbsp;
											</template>
											
										</div>
										<div class="fl checkWrap" v-else>
											暂无数据
										</div>
										<!--<div class="fl checkWrap" v-if="message.signPoints && message.signPoints.length > 0">
											<span  v-for="general in message.signPoints">{{general.signPointName}}</span>
											<div class="checkShow" v-if="message.signGroups && message.signGroups.length > 0">
												<a href=":;" v-for="groups in message.signGroups">{{groups.signGroupName}}</a>
											</div>
											<div class="checkShow" v-else>
												暂无数据
											</div>
										</div>
										<div class="fl checkWrap" v-else>
											暂无数据
										</div>-->
									</div>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</div>
			</div>	
		</div><!--wrap-->
		<script>
			new Vue({
				el: '#elPageTop',
				mounted: function() {
					var vm = this;
					vm.getStaffList(1);
					
					document.addEventListener("click",function(e){
						if(!vm.$refs.rightCard.$el.contains(e.target)){
							vm.showRight = false;
						}
					})
					
					vm.states = employeeState;
					vm.resetJobState();
				},
				data: {
					showRight:false,
					employeeList: [],
					dialogVisible: false,
					page:1,
					pageSize:10,
					popShow:false,
					isloading:true,
					jobState:[],
					jobStates:"",
					companyName:'',
					jobText:"",
					entryDateFrom:"",
					entryDateTo:"",
					formalDateFrom:"",
					formalDateTo:"",
					quitDateFrom:"",
					quitDateTo:"",
					states:[],
					message:{},
					hadFinger:true,
					hadFace:true,
					httpUrl: {
						listUrl: "${ctx}/api/employee/list",
						msgBrand:"${ctx}/api/employee/signInfo"
					},
					param:{
						"keyword":"",
						"conpanyId":"",
						"deptIds":"",
						"jobId":"",
						"entryDateFrom":"",
						"entryDateTo":"",
						"formalDateFrom":"",
						"formalDateTo":"",
						"quitDateFrom":"",
						"quitDateTo":""
					},
					leftList: [
						{
							"id":1,
							"text": "员工信息",
							"link":"javascript:;"
						}
					],
					conpany:[
					],
					job:[
					],
				},
				methods: {
					resetJobState:function(){
						var vm =this;
						var temp = [];
						vm.states.forEach(function(state){
							temp.push(state.id);
						});
						
						vm.$set(vm.$data,"jobState",temp);
					},
					//获取员工列表
					getStaffList: function() {
						var vm = this;
						Vue.http.get(vm.httpUrl.listUrl + "?page=" + vm.page + "&pageSize=" + vm.pageSize + "&" + Util.parseParam(vm.param) + "&jobStates=" + vm.jobStates).then(function(response) {
							response.json().then(function(responseData) {
								vm.employeeList = responseData.data;
								for(var i=0; i<vm.employeeList.list.length; i++){
									var employee = vm.employeeList.list[i];
									if(employee.entryDate != null){
										employee.entryDate = new Date(employee.entryDate).format('yyyy-MM-dd');
									}
									if(employee.formalDate != null){
										employee.formalDate = new Date(employee.formalDate).format('yyyy-MM-dd');
									}
									if(employee.quitDate){
										employee.quitDate = new Date(employee.quitDate).format('yyyy-MM-dd');
									}
									for(var j = 0;j<employeeState.length;j++){
										if(employee.jobState == employeeState[j].id){
											vm.$set(employee, "jobStateName", employeeState[j].state);
										}
									}
								}
								//console.log(vm.httpUrl.listUrl + "?page=" + vm.page + "&pageSize=" + vm.pageSize + "&" + Util.parseParam(vm.param) + "&jobStates=" + vm.jobStates);
								vm.isloading = false;
							})					
						})
					},
					//顶部搜索组件
					searchAttendance:function(txt){
						var vm = this;
						vm.param.keyword = txt;
						vm.getStaffList();
					},
					//查看信息采集
					seeMsg:function(index,code){
						var vm = this;
						vm.popShow = true;
						vm.hadFinger = false;
						vm.hadFace = false;
						Vue.http.get(vm.httpUrl.msgBrand + "?id=" + index + "&code=" + code).then(function(response){
							response.json().then(function(responseData){
								vm.message = responseData.data;
								vm.hadFinger = vm.message.hasFinger == 1;
								vm.hadFace = vm.message.hasFace == 1;
							})							
						})
					},
					//关闭信息采集弹窗
					closePop:function(){
						this.popShow = false;
					},
					//高级搜索
					moreSearch:function(e){
						e.stopPropagation();
						var vm = this;
						vm.showRight = true;
					},
					dateToTimestamp:function(date){
						if(date){
							return date.getTime();
						}
						else{
							return null;
						}
					},
					goToSearch:function(){
						var vm = this;
						//TO DO 高级筛选中的选择公司、部门、职位一期中不可点，后期需要添加
						vm.param.entryDateFrom = vm.dateToTimestamp(vm.entryDateFrom);
						vm.param.entryDateTo = vm.dateToTimestamp(vm.entryDateTo);
						vm.param.formalDateFrom = vm.dateToTimestamp(vm.formalDateFrom);
						vm.param.formalDateTo = vm.dateToTimestamp(vm.formalDateTo);
						vm.param.quitDateFrom = vm.dateToTimestamp(vm.quitDateFrom);
						vm.param.quitDateTo = vm.dateToTimestamp(vm.quitDateTo);				
						vm.jobStates = vm.jobState.join(',');
						vm.page = 1;
						vm.getStaffList();
						vm.showRightCard();
					},
					showRightCard: function(hide){
						this.showRight = hide
					},
					//分页方法
					handleSizeChange(val){
						this.page = val;
						this.getStaffList();
					},
					//取消高级筛选条件
					resetSearch:function(){
						var vm = this;
						vm.resetJobState();
						vm.param.deptIds = "";
						vm.param.jobId = "";
						vm.param.entryDateFrom = "";
						vm.param.entryDateTo = "";
						vm.param.formalDateFrom = "";
						vm.param.formalDateTo = "";
						vm.param.quitDateFrom = "";
						vm.param.quitDateTo = "";
						
						vm.entryDateFrom = "";
						vm.entryDateTo = "";
						vm.formalDateFrom = "";
						vm.formalDateTo = "";
						vm.quitDateFrom = "";
						vm.quitDateTo = "";
						vm.page = 1;
						vm.handleSizeChange(1);
						vm.getStaffList();
                        //$(".jobStateWrap .el-checkbox__input").removeClass("is-checked")
					},
					
					
				}
			})
		</script>
	</body>
</html>