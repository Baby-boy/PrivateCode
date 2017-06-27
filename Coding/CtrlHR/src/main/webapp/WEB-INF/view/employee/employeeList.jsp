<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/view/include/taglibs.jspf"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>员工列表</title>
<meta name="description" content="">
<%@ include file="/WEB-INF/view/include/head_include.jspf"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="${ctx}/css/employee.css">
<style>
	.checkParam{padding:20px 10px 0;}
	.checkParam .el-checkbox-group{display: inline-block;}
	/*.checkParam .el-form-item{float: left;}*/
</style>
</head>
<body>
	<%@ include file="/WEB-INF/view/components/page_top.jspf"%>
	<%@ include file="/WEB-INF/view/components/search.jspf"%>
	<%@ include file="/WEB-INF/view/components/left_nav.jspf"%>
	<%@ include file="/WEB-INF/view/components/rightCard.jspf"%>
	<%@ include file="/WEB-INF/view/components/header.jspf"%>
	<div class="wrap" id="elPageTop" v-cloak >
		<div class="warpInner">
			<header-nav></header-nav>
			<!--实例-->
			<div class="contentWrap">
				<div>
					<left-nav :left-list="leftList" :current="0"></left-nav>
					<!--顶部搜索组件-->
					<page-top page-title="员工管理" page-description="在这里您可以对公司进行考勤信息进行设置">
						<div class="pageTopButton fr">
							<el-button type="button" class="el-button el-button--primary"
								:disabled="true"> <i class="el-icon-plus"></i> 新建
							</el-button>
						</div>
						<div class="pageTopSearch fr">
						<search search-tips="搜索姓名或者职员代码..." @search="searchAttendance"></search>
					</div>
					</page-top>
					<!--头部帅选-->
					<el-form :model="param" class="checkParam" ref="paramForm">
						<el-row>
							<el-col :span="6">
								<el-form-item prop="deptIds">
									<slot name="label">
								  		<i class="iconfont icon-circle"></i> <span>选择部门</span>
								  	</slot>
									<el-cascader v-model="param.deptIds"  clearable @change="deptChange" :options="departmentData" :props="deptConfig" filterable change-on-select>
									</el-cascader>
								</el-form-item>
							</el-col>
							<el-col :span="6">
								<el-form-item prop="jobId">
									<slot name="label">
								  		<i class="iconfont icon-circle"></i> <span>选择职位</span>
								  	</slot>
									<el-select v-model="param.jobId" clearable filterable> 
										<el-option :value="item.id" :label="item.name"  v-for="item in positions" :key="item.id">{{item.name}}</el-option>
									</el-select>
								</el-form-item>
							</el-col>
							<el-col :span="5" style="margin-right: 30px">
								<el-form-item prop="jobStates">
									<slot name="label">
								  		<i class="iconfont icon-circle"></i> <span>在职状态</span>
								  	</slot>
									<el-checkbox-group v-model="param.jobStates" :min="1"> 
										<el-checkbox :label="state.id" v-for="state in states" :key="state.id">{{state.state}}</el-checkbox>
									</el-checkbox-group>
								</el-form-item>
							</el-col>
							<el-col :span="6">
								<el-form-item prop="gatherMsgStates">
									<slot name="label">
								  		<i class="iconfont icon-circle"></i> <span>采集状态</span>
								  	</slot>
									<el-checkbox-group v-model="param.gatherMsgStates" :min="1"> 
										<el-checkbox :label="atherMsgData.type" v-for="atherMsgData in gatherMsgList">{{atherMsgData.state}}</el-checkbox>
									</el-checkbox-group>
								</el-form-item>
							</el-col>
						</el-row>
						<el-row>
							<el-col :span="8">
								<el-form-item >
									<slot name="label">
										<el-col :span="5">
											<i class="iconfont icon-circle"></i> <span>入司时间</span>
										</el-col>
								  	</slot>
								  	<el-col :span="8">
								  		<el-form-item prop="entryDateFrom">
								  			<el-date-picker type="date" placeholder="开始日期"
												v-model="param.entryDateFrom">
								  			</el-date-picker>
								  		</el-form-item>
								  	</el-col>
								  	<el-col :span="1">-</el-col>
								  	<el-col :span="8">
								  		<el-form-item prop="entryDateTo">
								  			<el-date-picker type="date" placeholder="结束日期"
												v-model="param.entryDateTo">
								  			</el-date-picker>
								  		</el-form-item>
								  	</el-col>
								</el-form-item>
							</el-col>
							<el-col :span="8">
								<el-form-item >
									<slot name="label">
										<el-col :span="5">
											<i class="iconfont icon-circle"></i> <span>转正时间</span>
										</el-col>
								  	</slot>
								  	<el-col :span="8">
								  		<el-form-item prop="formalDateFrom">
								  			<el-date-picker type="date" placeholder="开始日期"
												v-model="param.formalDateFrom">
								  			</el-date-picker>
								  		</el-form-item>
								  	</el-col>
								  	<el-col :span="1">-</el-col>
								  	<el-col :span="8">
								  		<el-form-item prop="formalDateTo">
								  			<el-date-picker type="date" placeholder="结束日期"
												v-model="param.formalDateTo">
								  			</el-date-picker>
								  		</el-form-item>
								  	</el-col>
								</el-form-item>
							</el-col>
							<el-col :span="8">
								<el-form-item >
									<slot name="label">
										<el-col :span="5">
											<i class="iconfont icon-circle"></i> <span>离职时间</span>
										</el-col>
								  	</slot>
								  	<el-col :span="8">
								  		<el-form-item prop="quitDateFrom">
								  			<el-date-picker type="date" placeholder="开始日期"
												v-model="param.quitDateFrom">
								  			</el-date-picker>
								  		</el-form-item>
								  	</el-col>
								  	<el-col :span="1">-</el-col>
								  	<el-col :span="8">
								  		<el-form-item prop="quitDateTo">
								  			<el-date-picker type="date" placeholder="结束日期"
												v-model="param.quitDateTo">
								  			</el-date-picker>
								  		</el-form-item>
								  	</el-col>
								</el-form-item>
							</el-col>
						</el-row>
						<el-form-item style="text-align: center">
							<el-button type="primary" type="button" class="el-button el-bitton--primary"
									@click="goToSearch" :loading="searchLoading"> 确定 
							</el-button>
							<el-button type="button" class="el-button el-bitton-default"
									@click="$refs.paramForm.resetFields()"> <span>清空</span> 
							</el-button>
						</el-form-item>
					</el-form>
					<!--列表操作按钮-->
					<div class="pageOperate clearfix" style="padding:0 0 20px 0;">
						<div class="leftBtns fl">
							<el-button type="primary" class="iconfont icon-leave"
								size="small" :disabled="false"
								@click.stop="showViewMemberToggle"> 离职</el-button>
							<el-button type="primary" class="iconfont icon-export"
								size="small" :disabled="true"> 导出</el-button>
						</div>
					</div>
					<!--员工信息列表-->
					<el-table :data="employeeList.list" stripe v-loading="isloading"
						@selection-change="selsChange">
						<el-table-column
						label="全选" type="selection">
						</el-table-column>
						<el-table-column label="姓名" align="center" prop="name">
						<el-checkbox-group></el-checkbox-group> </el-table-column> <el-table-column
							label="职员代码" align="center" prop="code"></el-table-column>
						<el-table-column label="部门名称" align="center" prop="deptName">
							
						</el-table-column>
						<el-table-column
							label="职位" align="center" prop="jobName">
							</el-table-column> <el-table-column
							label="在职状态" align="center" prop="jobStateName"></el-table-column>
						<el-table-column label="入司时间" align="center" prop="entryDate">
							
						</el-table-column>
						<el-table-column label="转正时间" align="center" prop="formalDate">
							
						</el-table-column>
						<el-table-column label="离职时间" align="center" prop="quitDate">
							
						</el-table-column>
						<el-table-column colspan="2" align="center" label="操作">
							<template scope="scope">
								<el-button type="text"
								size="small" @click.stop="handleEdit(scope.row)">编辑</el-button> <!--<el-button type="text" size="small">删除</el-button>-->
								<el-button type="text" size="small"
								@click="seeMsg(scope.row.id,scope.row.code)">采集信息</el-button>
							</template>
						</el-table-column>
					</el-table>
					<div class="pagination" v-if="employeeList.totalCount > 0">
						<el-pagination layout="total,sizes,prev,pager,next,jumper" :current-page="page"
							:page-size="pageSize" @current-change="handleSizeChange"
							:total="employeeList.totalCount"
							@size-change="sizeChange"
							:page-sizes="[10,20,30]">
						</el-pagination>
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
							<td><img
								:src="hadFinger?'../../images/hadMsg.png':'../../images/noMsg.png'"
								width="60" height="60" /></td>
						</tr>
						<tr>
							<td>面部信息：</td>
							<td><img
								:src="hadFace?'../../images/hadMsg.png':'../../images/noMsg.png'"
								width="60" height="60" /></td>
						</tr>
						<tr>
							<td colspan="2">考勤地点：</td>
						</tr>
						<tr>
							<td colspan="2">
								<div class="checkWorkWrap">
									<div class="defaultCheck clearfix">
										<span class="fl"> 考勤点：</span>
										<div class="fl checkWrap"
											v-if="message.signPoints && message.signPoints.length > 0">
											<span v-for="general in message.signPoints">{{general.signPointName}}</span>
										</div>
										<div class="fl checkWrap" v-else>暂无数据</div>
									</div>
									<div class="chesckpart clearfix">
										<span class="fl">考勤群组：</span>
										<div class="fl checkWrap"
											v-if="message.signGroups && message.signGroups.length > 0">
											<template v-for="groups in message.signGroups"> <span>{{groups.signGroupName}}</span>&nbsp;
											</template>

										</div>
										<div class="fl checkWrap" v-else>暂无数据</div>
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

			<!--batch quit popup-->
			<!--查看成员弹窗-->
			<el-dialog :visible.sync="showViewMember" :close-on-click-modal="false" size="tiny" title="查看成员" :before-close="showViewMemberCut">
				<div class="viewMember">
					<el-form label-position="right" label-width="80px">
						<el-form-item label="员工代码">
							<el-input placeholder="支持多选,请用英文逗号分隔" icon="search" v-model="memberIds" @keydown.native.enter="getByCodes" :on-icon-click.prevent="getByCodes"></el-input>
						</el-form-item>
					</el-form>
					<div class="viewMemberBd">
						<el-table ref="etMember" :data="viewMemberData" stripe
							v-loading="viewMemberLoading" empty-text="查询的员工不存在或已离职" height="200"> <el-table-column
							prop="name" label="姓名"></el-table-column> <el-table-column
							prop="code" align="center" label="职员代码"></el-table-column> <el-table-column
							prop="deptName" align="center" label="部门"></el-table-column> <el-table-column
							prop="jobName" align="center" label="职位"></el-table-column> </el-table>
					</div>
					<el-form label-position="right" label-width="80px">
						<el-form-item label="离职日期">
							<el-date-picker class="quit-date-date" type="date"
								placeholder="请选择离职日期" v-model="quitDate"></el-date-picker>
						</el-form-item>
					</el-form>
					<el-row type="flex" class="row-bg" justify="center"> <el-col
						:span="12" :offset="10"> <el-button type="primary"
						@click.stop="confirmQuit">确定</el-button></el-col> <el-col :span="12">
					<el-button @click.stop="showViewMemberToggleCle">取消</el-button></el-col>
					</el-row>
				</div>
			</el-dialog>
			<!--编辑员工-->
			<el-dialog :visible.sync="editMember" :close-on-click-modal="false" title="编辑员工信息" :before-close="popupShowToggle">
				<el-form :model="userMsg" ref="employeeForm"
					class="member-edit-form" label-position="right" label-width="120px"
					:rules="employeeFormRules">
					<el-form-item label="职员代码" prop="code">
						<el-input v-model="userMsg.code" placeholder="请输入职员代码" :disabled="true"></el-input>
					</el-form-item>
					<el-form-item label="姓名" prop="name">
						<el-input v-model="userMsg.name"
						placeholder="请输入姓名" :disabled="true"></el-input> </el-form-item> <el-form-item label="手机号码">
					<el-input v-model="userMsg.mobile" placeholder="请输入手机号码">
						
					</el-input>
					</el-form-item> <el-form-item label="性别"> <el-radio-group
						@change="sexChange" v-model="userMsg.sex"> 
						<el-radio :label="1">男</el-radio> 
						<el-radio :label="2">女</el-radio> 
					</el-radio-group> </el-form-item> <el-form-item label="在职状态">
					<el-radio-group v-model="userMsg.jobState"
						@change="jobStateChange"> 
						<el-radio :label="1">试用</el-radio>
						<el-radio :label="2">在职</el-radio> 
						<el-radio :label="3">离职</el-radio>
					</el-radio-group> </el-form-item> <el-form-item label="入职时间"> <el-date-picker
						class="common-date" type="date" placeholder="请选择入职时间"
						v-model="userMsg.entryDate"></el-date-picker>
						
					</el-form-item> <el-form-item label="转正时间"> <el-date-picker
						class="common-date" type="date" placeholder="请选择转正时间"
						v-model="userMsg.formalDate"></el-date-picker>
						
					</el-form-item> <el-form-item label="离职时间"> <el-date-picker
						class="common-date" type="date" placeholder="请选择离职时间"
						v-model="userMsg.quitDate"></el-date-picker>
					</el-form-item>
					<!--<el-form-item label="部门"> 
						<a class="link-sign"
							href="javascript:;" >
							//TODO 未确认当前页是否能够编辑部门属性，接口暂未开发
							//@click.stop="deptSelToggle"
							<span v-if="userMsg.deptName">{{userMsg.deptName}}</span>
							<span v-else>选择部门</span>
						</a> 
					</el-form-item> -->
					
					
					
					<!--<el-form-item
					label="职位"> <el-select class="common-sel"
					v-model='employeeForm.jobId' placeholder='请选择'> <el-option
					v-for="item in positions" :key="item.id" :label="item.name"
					:value="item.id">{{item.name}}</el-option> </el-select> </el-form-item>
					
					
					<el-form-item
					label="基本工资档位"> 
						<el-select class="common-sel"
						v-model='employeeForm.salaryId' placeholder='请选择'> <el-option
						v-for="item in salarys" :key="item.id" :label="item.name"
						:value="item.id">
						</el-option>
						</el-select> 
					</el-form-item>
					
					
					<el-form-item label="绩效档位">
						<el-select class="common-sel" v-model='employeeForm.perfId'
							placeholder='请选择'> 
							<el-option v-for="item in perfs"
								:key="item.id" :label="item.name" :value="item.id">
							</el-option>
						</el-select>
					</el-form-item> -->
				</el-form>
				<div class="edit">
					<el-row type="flex" class="row-bg" justify="center">
						<el-col :span="8">
							<el-button type="primary" @click.stop="editEmployee">确定</el-button>
						</el-col>
						<el-col :span="8" :offset="4">
							<el-button @click.stop="popupShowToggle">取消</el-button>
						</el-col>
					</el-row>
				</div>
			</el-dialog>
			<!--选择部门-->
			<div class="dept-sel-wrapper" :class="{block:deptSel}">
				<div class="dept-sel-title clearfix">
					<span>选择部门</span> <a href="javascript:;"
						class="el-icon-close color6 fr" @click.stop="deptSelToggle"></a>
				</div>
				<el-tree :data="deptsTree" show-checkbox default-expand-all
					node-key="id" ref="tree" highlight-current :props="defaultProps">
				</el-tree>
				<div>
					<el-row type="flex" class="row-bg" justify="center"> <el-col
						:span="8" :offset="8"> <el-button type="primary"
						@click.stop="confirmSelDept">确定</el-button></el-col> <el-col :span="16">
					<el-button @click.stop="deptSelToggle">取消</el-button></el-col> </el-row>
				</div>
			</div>
		</div>
	</div>
	<!--wrap-->
	<script>
			new Vue({
				el: '#elPageTop',
				data: {
					employeeList: [],
					dialogVisible: false,
					page:1,
					pageSize:10,
					popShow:false,
					popQuitShow:false,
					isloading:true,
					companyName:'',
					jobText:"",
					states:[],
					message:{},
					hadFinger:true,
					hadFace:true,
					viewMemberData: [],
					viewMemberLoading: false,
					showViewMember: false,
					searchLoading:false,
					memberIds:'',
					quitDate:"",
					editMember:false,
					selDepts:[],
					gatherMsgList:[
						{
							state:"未采集",
							type:1
						},
						{
							state:"部分采集",
							type:2
						},
						{
							state:"已采集",
							type:3
						}
					],
//					employeeForm:{
//						name:'',
//						code:'',
//						mobile:'',
//						sex:1,
//						jobState:1,
//						entryDate:'',
//						formalDate:'',
//						quitDate:'', 
//						deptId:0,
//						jobId:0,
//						salaryId:0,
//						perfId:0
//					},
					employeeFormRules:{
						name:[
							{required: true,message:'请输入员工姓名',trigger: 'blur'}
						],
						code: [
							{required: true,message:'请输入员工代码',trigger: 'blur'}
						]
					},
					positions:[],
					selPosition:'',
					selJobState:1,
					companys:[],
					depts:[],
					deptSel:false,
					salarys:[],
					perfs:[],
					deptsPlus:[],
					deptsTree:[],
					defaultProps:{
						children:'children',
						label:'label'
					},
					userMsg:{},
					httpUrl: {
						listUrl: "${ctx}/api/employee/list",
						msgBrand:"${ctx}/api/employee/signInfo",
						dimission:"${ctx}/api/employee/dimission",
						getByCodes:"${ctx}/api/employee/staff",
						getPositions:"${ctx}/api/job/simpleList",
						getDepts:"${ctx}/api/dept/simpleCapList",
						getSalarys:"${ctx}/api/salaryStall/simpleList",
						getPerfs:"${ctx}/api/perfSatll/simpleList",
						editEmployee:"${ctx}/api/employee/update",
						getTreeDept: "${ctx}/api/dept/treeDept",
						getInfoApi:"${ctx}/api/employee/getInfo",    //获取员工个人信息
						getDeptTreeApi:"${ctx}/api/dept/getDeptTree"
					},
					param:{
						keyword:"",
						jobId:"",
						jobStates:[2],
						gatherMsgStates:[1],
						deptIds:[],
						entryDateFrom:"",
						entryDateTo:"",
						formalDateFrom:"",
						formalDateTo:"",
						quitDateFrom:"",
						quitDateTo:""
					},
					deptIds:[],
					leftList: [
						{
							"id":1,
							"text": "员工信息",
							"link":"javascript:;"
						}
					],
					searchTxt:"",
					departmentData:[],
					deptConfig:{
						children: 'childDept',
						label: 'name',
						value:"id",
					}
				},
				mounted: function() {
					this.getDepData();
					this.getPositions()
					this.getStaffList();
					this.states=employeeState;
				},
				methods: {
					//获取部门树数据
					getDepData:function(){
						var vm = this;
						Vue.http.get(vm.httpUrl.getDeptTreeApi)
						.then(function(response) {
							response.json().
							then(function(responseData) {
								vm.departmentData=responseData.data;
							})					
						})
					},
//					resetJobState:function(){
//						var vm =this;
//						var temp = [];
//						vm.states.forEach(function(state){
//							temp.push(state.id);
//						});
//						vm.param.jobStates=temp;
//					},
//					resetGatherMsg:function(){
//						var vm = this;
//						var temp = [];
//						vm.gatherMsgList.forEach(function(state){
//							temp.push(state.type);
//						})
//						vm.param.gatherMsgStates=temp;
//					},
					//获取员工列表
					getStaffList: function(){
						var vm = this;
						var param={};
						vm.searchLoading=true;
						param.entryDateFrom=vm.dateToTimestamp(vm.param.entryDateFrom);
						param.entryDateTo = vm.dateToTimestamp(vm.param.entryDateTo);
						param.formalDateFrom = vm.dateToTimestamp(vm.param.formalDateFrom);
						param.formalDateTo = vm.dateToTimestamp(vm.param.formalDateTo);
						param.quitDateFrom = vm.dateToTimestamp(vm.param.quitDateFrom);
						param.quitDateTo = vm.dateToTimestamp(vm.param.quitDateTo);		
						param.jobStates=vm.param.jobStates.join(',');
						param.gatherMsgStates= vm.param.gatherMsgStates.join(',');
						if(vm.param.deptIds.length)param.deptIds = vm.param.deptIds[vm.param.deptIds.length-1];
						
						param.jobId=vm.param.jobId;
						param.pageSize=vm.pageSize;
						param.page=vm.page;
						param.keyword=vm.param.keyword;
						Vue.http.get(vm.httpUrl.listUrl +"?"+Util.parseParam(param)).then(function(response) {
							Util.processRes(response,function(responseData) {
								vm.searchLoading=false;
								vm.employeeList = responseData.data;
								for(var i=0; i<vm.employeeList.list.length; i++){
									var employee = vm.employeeList.list[i];
									if(employee.entryDate != null){
										employee.entryDate = new Date(employee.entryDate).format('yyyy-MM-dd');
									}

									if(employee.formalDate != null){
										employee.formalDate = new Date(employee.formalDate).format('yyyy-MM-dd');
									}

									if(employee.quitDate != null){
										employee.quitDate = new Date(employee.quitDate).format('yyyy-MM-dd');
									}
									
									for(var j = 0;j<employeeState.length;j++){
										if(employee.jobState == employeeState[j].id){
											vm.$set(employee, "jobStateName", employeeState[j].state);
											vm.$set(employee, "jobStateId", employeeState[j].id);
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
					dateToTimestamp:function(date){
						if(date){
							return date.getTime();
						}
						else{
							return null;
						}
					},
					showViewMemberCut:function(){
						this.showViewMember = false;
					},
					goToSearch:function(){
						var vm = this;
						//TO DO 高级筛选中的选择公司、部门、职位一期中不可点，后期需要添加
						vm.page = 1;
						vm.getStaffList();
					},
					//分页方法
					handleSizeChange:function(val){
						this.page = val;
						this.getStaffList();
					},
					sizeChange:function(val){
						this.pageSize=val;
						this.getStaffList();
					},
					// 员工弹出框toggle
					showViewMemberToggle:function() {
						var vm = this;
						if(vm.memberIds == ''){
							vm.$message('请选择最少一个用户!');
						}else{ 
							vm.getByCodes('state');
						}
						
					},
					showViewMemberToggleCle:function(){
						this.showViewMember = false;
					},
					// 编辑员工
					editEmployee: function() {
						var vm = this;
						vm.$refs.employeeForm.validate(function(valid){
							if(valid) {
								vm.listLoading = true;

								var para = $.extend({}, vm.userMsg);//Object.assign({},vm.userMsg);

								if(para.mobile && (para.mobile.length != 11 || isNaN(para.mobile))){
									vm.$message.error('您输入的手机号码有误');
									return;
								}
								// 删除接口不需要字段
								delete para.deptName;
								delete para.jobName;
								delete para.salaryName;
								delete para.perfName;
								
								if(para.quitDate){
									para.quitDate = Date.parse(new Date(para.quitDate))
								}
								if(para.entryDate){
									para.formalDate = Date.parse(new Date(para.formalDate))
								}
								if(para.entryDate){
									para.entryDate = Date.parse(new Date(para.entryDate))
								}
								
								if(para.jobState == 1){
									para.formalDate = null;
									para.quitDate = null;
									if(!para.entryDate){
										vm.$message('入职时间不能为空!');
										return false
									}
								}
								if(para.jobState == 2){
									para.quitDate = null;
									if(!para.formalDate){
										vm.$message('转正时间不能为空!');
										return false
									}
								}
								if(para.jobState == 3){
									if(!para.quitDate){
										vm.$message('离职时间不能为空!');
										return false
									}
								}
								
								console.log(para);
								Vue.http.post(vm.httpUrl.editEmployee, para, {
									emulateJSON: true
								}).then(function(response) {
									response.json().then(function(responseData) {
										console.log(responseData)
										if(responseData.code == '200') {
											vm.getStaffList(); 
											vm.$message({message:'编辑成功！',type:'success'});
											vm.editMember = false;
										} else {
											vm.$message.error(responseData.msg);
										}
									})
								});
							}else{
								return false;
							}
						});
					},
					selsChange: function(sels) {
						var temp = [];
						sels.forEach(function(sel){
							temp.push(sel.code);
						});
						this.memberIds = temp.join(",");
						console.log(sels);
					},
					// 确定离职时间
					getByCodes: function(e) {
						var vm = this;
						vm.listLoading = true;
						Vue.http.get(vm.httpUrl.getByCodes+"?codes="+vm.memberIds).then(function(response) {
							response.json().then(function(responseData) {
								vm.viewMemberData = responseData.data;
								console.log(e)
								if(e && vm.viewMemberData.length > 0){
									vm.showViewMember = !vm.showViewMember;
									if(!vm.showViewMember) {
										vm.quitDate =  '';
									}
								}else{
									vm.$message('您选择的用户已离职!')
								}
							})
						})
					},
					popupShowToggle:function(){
						this.editMember = false;
					},
					// 编辑员工
					handleEdit: function(row) {
						var vm = this;
						
						//vm.employeeForm = row;

						//vm.getDepts(1);
//						console.log(row)
						Vue.http.get(vm.httpUrl.getInfoApi+"?id="+row.id).then(function(response) {
							response.json().then(function(responseData) {
								vm.userMsg = responseData.data;
								
								vm.editMember = true;
//								console.log(responseData)
								// TODO 后续工资档位   绩效档位   需要确认字段
								//vm.getSalarys(1);
								//vm.getPerfs(1);
								//vm.getDepts(1);
								//vm.getPositions(1);
							/* 	if(vm.employeeForm) {
									vm.selPosition = vm.employeeForm.jobId;
								} */
							})
						})
						//vm.getPositions(row.deptId);
					},
					// 获取职位
					getPositions:function(deptIds) {
						var vm = this;
						vm.listLoading = true;
						var api="";
						var param="?deptIds=";
						if(deptIds){
							vm.param.jobId="";
							param+=deptIds;
						}
						Vue.http.get(vm.httpUrl.getPositions+param).then(function(response) {
								response.json().then(function(responseData) {
									vm.positions = responseData.data;
								/* 	if(vm.employeeForm) {
										vm.selPosition = vm.employeeForm.jobId;
									} */
								})
							})
					},
					// 性别change
					sexChange:function() {
						
					},
					// 在职状态change
					jobStateChange:function(sel) {
						var vm = this;
//						console.log(sel);
						vm.selJobState = sel;
					},
					// 获取公司列表
					getCompanys:function() {
						var vm = this;
						vm.listLoading = true;
						Vue.http.get(vm.httpUrl.getCompanys).then(function(response) {
							response.json().then(function(responseData) {
								vm.companys = responseData.data;
							})
						})
					},
					// 获取部门
					getDepts:function(companyId,parentId) {
						var vm = this;
						vm.listLoading = true;
						Vue.http.get(vm.httpUrl.getDepts+"?companyId="+companyId).then(function(response) {
							response.json().then(function(responseData) {
								vm.depts = responseData.data;
							})
						})
					},
					// 获取部门plus
					getDeptsPlus:function(companyId,parentId) {
						var vm = this;
						vm.listLoading = true;
						Vue.http.get(vm.httpUrl.getDepts+"?companyId="+companyId).then(function(response) {
							response.json().then(function(responseData) {
								vm.deptsPlus = [];
								responseData.data.forEach(function (dept, index) {
									vm.deptsPlus.push({
										label:dept.name,
										key:index
									});
								});
								/*
								responseData.data.forEach((dept,index) => {
									vm.deptsPlus.push({
										label:dept.name,
										key:index
									});
								});
								*/
							})
						})
					},
					// 部门改变
					deptChange:function() {
						var vm = this;
						// JQ 设置 部门筛选 层联框显示位置
//						if($('.el-cascader-menus .el-cascader-menu')){
//							var left="";
//							if(vm.param.deptIds>vm.deptIds){
//								alert(1)
//								left= parseInt($('.el-cascader-menus').css("left"))-160;
//							}else{
//								alert(2)
//								left= parseInt($('.el-cascader-menus').css("left"))+160;
//							}
//							alert(left)
//							$('.el-cascader-menus').css({'left':left,"transition":"0.5s"})
//						}
						var deptIds="";
						vm.param.jobId="";
						if(vm.param.deptIds.length) deptIds= vm.param.deptIds[vm.param.deptIds.length-1];
						vm.getPositions(deptIds);
					},
					// Toggle 部门选择改变
					deptSelToggle:function() {
						var vm = this;
						vm.deptSel = !vm.deptSel;
						if(vm.deptSel) {
							vm.getTreeDept(1);	
						}
					},
					renderFunc:function() {
						
					},
					handleChange:function() {
						
					},
					// 获取工资档位
					getSalarys:function(jobGradeId) {
						var vm = this;
						vm.listLoading = true;
						Vue.http.get(vm.httpUrl.getSalarys+"?jobGradeId="+jobGradeId).then(function(response) {
							response.json().then(function(responseData) {
								vm.salarys = responseData.data;
							})
						})
					},
					// 获取绩效档位
					getPerfs:function(jobGradeId) {
						var vm = this;
						vm.listLoading = true;
						Vue.http.get(vm.httpUrl.getPerfs+"?jobGradeId="+jobGradeId).then(function(response) {
							response.json().then(function(responseData) {
								vm.perfs = responseData.data;
							})
						})
					},
					// 确定选择部门
					confirmSelDept:function() {
						var vm = this;
						// TODO  未确认参数是否还会发生更改
						vm.getSalarys(1);
						vm.getPerfs(1);
						vm.getDepts(1);
						vm.getPositions();
						vm.deptSel = false;
					},
					// 获取部分树状结构
					getTreeDept:function(companyId) {
						var vm = this;
						vm.listLoading = true;
						Vue.http.get(vm.httpUrl.getTreeDept+"?companyId="+companyId).then(function(response) {
							response.json().then(function(responseData) {
//								console.log(responseData)
								vm.deptsTree = responseData.data;
							})
						})
					},
					// 确定离职
					confirmQuit: function() {
						var vm = this;
						//var codes = vm.sels.map(item => item.code).toString();
						var quitDate = null;
						if(vm.quitDate){
							quitDate = Date.parse(new Date(vm.quitDate))
						}
						if(vm.viewMemberData.length == 0){
							vm.$message('数据不能为空!');
						}else if(quitDate == null){
							vm.$message('请设置离职时间!');
						}else{
							vm.$confirm('确认设置批量离职吗','提示',{
								type: 'info'
							}).then(function(){
								vm.listLoading = true;
								var para = {codes:vm.memberIds,quitDate:quitDate};
//								console.log(para)
								Vue.http.post(vm.httpUrl.dimission, para, {
									emulateJSON: true
								}).then(function(response) {
									response.json().then(function(responseData) {
										if(responseData.code == '200') {
											vm.getStaffList();
											vm.showViewMemberToggle();
											vm.$message({message:'批量设置成功！',type:'success'});
											vm.listLoading = false;
										} else {
											vm.$message.error(responseData.msg);
										}
									})
								})
							});
						}
					}
				},
			})
			
		</script>
</body>
</html>