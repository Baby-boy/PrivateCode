<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/view/include/taglibs.jspf"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>组织管理</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<%@ include file="/WEB-INF/view/include/head_include.jspf"%>
<style type="text/css">
	.rightCard.active{right: 0 !important;}
	.el-dialog{width: 468px !important;}
	.pageTopSlot{line-height: 56px;}
	.pageTop{padding-bottom: 0;}
</style>
</head>
<body>
	<%@ include file="/WEB-INF/view/components/left_nav.jspf"%>
	<%@ include file="/WEB-INF/view/components/page_top.jspf"%>
	<%@ include file="/WEB-INF/view/components/search.jspf"%>
	<%@ include file="/WEB-INF/view/components/rightCard.jspf"%>
	<%@ include file="/WEB-INF/view/components/header.jspf"%>
	<div class="wrapInner clearfix" id="wrapInner" v-cloak>
			<header-nav></header-nav>
			<div class="contentWrap">
				<left-nav :left-list="leftList" :current="1"></left-nav>
				<!--顶部筛选-->
				<page-top page-title="职位管理" page-description="您在这里可以对职位进行设置">
				<div class="pageTopButton fr">
						<el-button type="primary" @click="addNew"> 
							<i class="el-icon-plus"></i>	 新建
						</el-button>
				</div>
				<div class="pageTopSearch fr">
					<search search-tips="搜索职位名称..." @search="searchPosition"></search>
				</div>
				</page-top>
				<!--高级筛选按钮-->
				<div class="pageOperate">
					<a href="javascript:;" @click="openRight" class="fr btnFillter" style="margin-right: 20px;"><i class="iconfont icon-sort"></i>&nbsp;高级筛选</a>
				</div>
				<!--右侧高级筛选-->
				<right-card card-title="筛选器"  v-model="showRight"  ref="rightCard" style="width: 370px;right: -370px;">
					<el-form :model="searchParam" ref="searchParam">
						<el-form-item  prop="jobTypeIds">
						  	<slot name="label">
						  		<div><i class="iconfont icon-circle"></i> 职位类型</div>
						  	</slot>
						  	<el-select v-model="searchParam.jobTypeIds" clearable filterable multiple placeholder="请选择职位类型">
									<el-option v-for="type in jobTypes" :key="type.id"
	      								:label="type.name"
	     								:value="type.id">
									</el-option>
							</el-select>
						</el-form-item>
					    <el-form-item>
						  	<slot name="label">
						  		<div><i class="iconfont icon-circle"></i> 职位等级</div>
						  	</slot>
						  	<el-col :span="11">
						  		<el-form-item prop="jobGradeStarId">
								  	<el-select v-model="searchParam.jobGradeStarId" clearable filterable placeholder="请选择职位等级"   style="width: 145px;">
											<el-option v-for="grade in jobGrades" :key="grade.id"
			      								:label="grade.name"
			     								:value="grade.id">
											</el-option>
									</el-select>
								</el-form-item>
							</el-col>
							<el-col :span="2" class="text-center">~</el-col>
							<el-col :span="11">
								<el-form-item prop="jobGradeEndId">
									<el-select v-model="searchParam.jobGradeEndId" clearable filterable  placeholder="请选择职位等级"   style="width: 145px;">
											<el-option v-for="grade in jobGrades" :key="grade.id"
			      								:label="grade.name"
			     								:value="grade.id">
											</el-option>
									</el-select>
								</el-form-item>	
							</el-col>
					    </el-form-item>
					    <el-form-item prop="salaryTypeId">
						  	<slot name="label">
						  		<div><i class="iconfont icon-circle"></i> 薪资类型</div>
						  	</slot>
						  	<el-select v-model="searchParam.salaryTypeId" clearable filterable multiple placeholder="请选择薪资类型"   style="width: 145px;">
									<el-option v-for="salary in jobSalarys" :key="salary.id"
	      								:label="salary.name"
	     								:value="salary.id">
									</el-option>
							</el-select>
					    </el-form-item>
					</el-form>
					<div class="searchBtns">
						<el-button type="primary" @click="fillterdate" :loading="loading.search">
							<span>确 定</span>
						</el-button>
						<el-button type="button" @click="resetSearch('searchParam')">
							<span>重置</span>
						</el-button>
					</div>
				</right-card>	
				<!--数据列表-->
				<el-table :data="positionsData.list" stripe v-loading="isloading" style="width: 100%;text-align: center;">
					<el-table-column prop="name" label="职位名称" align="center"  width="180"></el-table-column>
					<el-table-column prop="jobType" align="center" label="职位类型" width="120"></el-table-column> 
					<el-table-column prop="jobGradeId" align="center" label="职位等级" width="100"></el-table-column> 
					<el-table-column prop="salaryType" align="center" label="薪资类型" width="100"></el-table-column>
					<el-table-column prop="salary1" align="center" label="基本工资1档"  width="130" ></el-table-column>
					<el-table-column prop="salary2" align="center" label="基本工资2档"  width="130" ></el-table-column>
					<el-table-column prop="salary3" align="center" label="基本工资3档"  width="130" ></el-table-column>
					<el-table-column prop="salary4" align="center" label="基本工资4档"  width="130" ></el-table-column>
					<el-table-column prop="salary5" align="center" label="基本工资5档"  width="130" ></el-table-column>
					<el-table-column prop="salary6" align="center" label="基本工资6档"  width="130" ></el-table-column>
					<el-table-column prop="salary7" align="center" label="基本工资7档"  width="130" ></el-table-column>
					<el-table-column prop="salary8" align="center" label="基本工资8档"  width="130" ></el-table-column>
					<el-table-column prop="salary9" align="center" label="基本工资9档"  width="130" ></el-table-column>
					<el-table-column prop="salary10" align="center" label="基本工资10档"  width="130" ></el-table-column>
					<el-table-column prop="meritPay1" align="center" label="绩效工资1档"  width="130" ></el-table-column>
					<el-table-column prop="meritPay2" align="center" label="绩效工资2档"  width="130" ></el-table-column>
					<el-table-column prop="meritPay3" align="center" label="绩效工资3档"  width="130" ></el-table-column>
					<el-table-column prop="meritPay4" align="center" label="绩效工资4档"  width="130" ></el-table-column>
					<el-table-column prop="meritPay5" align="center" label="绩效工资5档"  width="130" ></el-table-column>
					<el-table-column colspan="2" align="center" label="操作" fixed="right" width="220">
						<template scope="scope"> 
							<el-button type="text" size="small" @click="edit(scope.row)" class="h-b">编辑</el-button>
							<el-button type="text" size="small" @click="handleDelete(scope.row)" class="h-b">删除</el-button> 
						</template> 
					</el-table-column>
				</el-table>
				<!--分页-->
				<div class="pagination" v-if="positionsData.list && positionsData.list.length>0">
					<el-pagination 
						layout="total,sizes,prev,pager,next,jumper" 
						@size-change="sizeChange"
						:current-page="page"
						:page-size="pageSize" 
						:total="positionsData.totalCount"
						:page-sizes="[10,20,30]"
						@current-change="handleCurrentChange">
					</el-pagination>
				</div>
				<!--编辑或新建-->
				<el-dialog  v-model="editShow" :before-close="closeDialog" :close-on-click-modal="false" >
					<div slot="title" class="dialog-title text-center hasLine">
						<span>{{dialogTitle}}职位</span>
					</div>
					<el-form :model="positionIt" label-width="80px" :rules="rules" ref="positionForm">
						<el-form-item required label="职位名称" prop="name">
							<el-input v-model="positionIt.name" placeholder="请输入职位名称"></el-input>
						</el-form-item> 
						<el-form-item label="职位类型" required prop="jobTypeId">
							<el-select v-model="positionIt.jobTypeId" placeholder="请选择职位类型" filterable>
									<el-option v-for="type in jobTypes" :key="type.id"
	      								:label="type.name"
	     								:value="type.id">
									</el-option>
							</el-select>
						</el-form-item> 
						<el-form-item label="职位等级" required prop="jobGradeId">
							<el-select v-model="positionIt.jobGradeId" placeholder="请选择职位等级" filterable>
								<el-option v-for="grade in jobGrades" :key="grade.id"
	      								:label="grade.name"
	     								:value="grade.id">
								</el-option>
							</el-select>
						</el-form-item> 
						<el-form-item label="薪资类型" required prop="salaryTypeId">
							<!--<el-radio-group v-model="position.salaryTypeId">
								<el-radio-button v-for="type in jobTypes" label="type.id">{{type.name}}</el-radio-button>
							</el-radio-group>-->
							<el-select v-model="positionIt.salaryTypeId"  placeholder="请选择薪资类型" filterable>
									<el-option v-for="salary in jobSalarys" :key="salary.id"
	      								:label="salary.name"
	     								:value="salary.id">
									</el-option>
							</el-select>
						</el-form-item> 
					</el-form>
					<div slot="footer" class="text-center">
							<el-button type="primary" :loading="loading.saveLoading" @click.stop="confirmQuit('positionForm')">确定</el-button> 
							<el-button @click.stop="cancleIt('positionForm')">取消</el-button>
					</div>
				</el-dialog>
			</div>
	</div>
<script>
new Vue({
	el: '#wrapInner',
	data: {
		dialogTitle:"",
		positionsData:{},
		showRight:false,
		positionIt:{},
		rules:{
			name:[
	            { required: true, message: '请输入职位名称', trigger: 'submit' },
	            { min: 2, message: '长度在 2 个字符以上', trigger: 'submit' }
	        ],
	        jobTypeId: [
	            { required: true, message: '请选择职位职位类型'}
            ],
	        jobGradeId: [
	            { required: true, message: '请选择职位等级'}
            ],
	        salaryTypeId: [
	            { required: true, message: '请选薪资类型'}
	        ],
		},
		page:1,
		pageSize:10,
		popupShow: false,
		isloading: true,
		searchParam:{
			keyword:"",
			jobTypeIds:[],
			jobGradeStarId:"",
			jobGradeEndId:"",
			salaryTypeId:[],
		},
		loading:{
			saveLoading:false,
			search:false,
		},
		checkedCompany: [],
		checkAll: false,
		isIndeterminate: false,
		checkedEquipment: [],
		editShow:false,
		ids: [],
		msg:{
			type:"",
			msg:"",
		},
		api: {
			positionsApi: "${ctx}/api/job/list",
			jobTypesApi:"${ctx}/api/job/getJobType",
			jobGradesApi:"${ctx}/api/job/getJobGrade",
			jobSalaryApi:"${ctx}/api/job/getSalaryType",
			addApi:"${ctx}/api/job/add",
			editApi:"${ctx}/api/job/update",
			delApi:"${ctx}/api/job/delete",
		},
		jobTypes:[],
		jobGrades:[],
		jobSalarys:[],
		leftList: [
//			{
//				"id": 1,
//				"text": "公司管理",
//				"link": "${ctx}/pageTo/organize/company?viewName=0;"
//			},
			{
				"id": 2,
				"text": "部门管理",
				"link": "${ctx}/pageTo/organize/department?viewName=0"
			},
			{
				"id": 3,
				"text": "职位管理",
				"link": "${ctx}/pageTo/organize/position?viewName=0"
			},
			{
				"id": 4,
				"text": "编制管理",
				"link": "${ctx}/pageTo/organize/formantion?viewName=0"
			},
			{
				"id": 5,
				"text": "架构一览",
				"link": "javascript:;"
			}
		],
	},
	mounted:function(){
		this.getPositionsData();
	},
	methods: {
		//顶部搜索
		searchPosition: function(txt) {
			var vm = this;
			vm.searchParam.keywords=txt;
			vm.page = 1;
			vm.getPositionsData();
		},
		openRight:function(e){
			e.stopPropagation();
			var vm=this;
			vm.showRight=true;
			vm.getJobTypes();
			vm.getJobGrades();
			vm.getJobSalarys();
			$(".rightCard").on("click", function(e) {
				e.stopPropagation();
			})
			$("body").click(function(e) {
				vm.showRight=false;
			})

		},
		//获取职位数据
		getPositionsData:function(){
			var vm = this;
			var paramStr="?page="+vm.page+"&pageSize="+vm.pageSize;
			if(vm.searchParam.keywords){
				paramStr+="&keyword="+vm.searchParam.keywords;
			}
			if(vm.searchParam.jobTypeIds.length){
				paramStr+="&jobTypeIds="+vm.searchParam.jobTypeIds.join(",");
			}
			if(vm.searchParam.jobGradeStarId&&vm.searchParam.jobGradeEndId){
				paramStr+="&jobGradeIds=";
				var ids="";
				for (var i=vm.searchParam.jobGradeStarId;i<=vm.searchParam.jobGradeEndId;i++){
						ids+=","+i;
				}
				paramStr+=ids.substring(1);
			}else if(vm.searchParam.jobGradeStarId){
				paramStr+="&jobGradeIds=";
				var ids="";
				for (var i=vm.searchParam.jobGradeStarId;i<=vm.jobGrades.length;i++){
						ids+=","+i;
				}
				paramStr+=ids.substring(1);
			}else if(vm.searchParam.jobGradeEndId){
				paramStr+="&jobGradeIds=";
				var ids="";
				for (var i=0;i<=vm.searchParam.jobGradeEndId;i++){
						ids+=","+i;
				}
				paramStr+=ids.substring(1);
			}
			
			if(vm.searchParam.salaryTypeId.length){
				paramStr+="&&salaryTypeIds="+vm.searchParam.salaryTypeId.join(",");
			}
				console.log(paramStr);
			Vue.http.get(vm.api.positionsApi+paramStr)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					vm.loading.search=false;
					vm.positionsData=responseData.data;
					console.log(responseData)
					vm.isloading = false;
				})					
			})
		},
		//获取职位类型集合
		getJobTypes:function(){
			var vm = this;
			Vue.http.get(vm.api.jobTypesApi)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					vm.jobTypes=responseData.data;
				})					
			})
		},
		//获取职位等级集合
		getJobGrades:function(){
			var vm = this;
			Vue.http.get(vm.api.jobGradesApi)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					vm.jobGrades=responseData.data;
				})					
			})
		},
		//获取薪资类型集合
		getJobSalarys:function(){
			var vm = this;
			Vue.http.get(vm.api.jobSalaryApi)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					vm.jobSalarys=responseData.data;
				})					
			})
		},
		//分页方法
		handleCurrentChange(val){
			this.page = val;
			this.getPositionsData();
		},
		sizeChange:function(val){
			this.pageSize=val;
			this.getPositionsData();
		},
		//新增打开
		addNew:function(){
			this.positionIt={
							name:"",
							jobTypeId:"",
							jobGradeId:"",
							salaryTypeId:"",
						};
			this.getJobTypes();
			this.getJobGrades();
			this.getJobSalarys();
			this.dialogTitle="新建";
			this.editShow=true;
		},
		//编辑
		edit:function(positionIt){
			this.dialogTitle="编辑"
			this.positionIt=positionIt;
			this.getJobTypes();
			this.getJobGrades();
			this.getJobSalarys();
			this.editShow=true;
		},
		//保存
		confirmQuit:function(formName){
				var vm = this;
				var type=vm.dialogTitle=="新建"?1:0;//判断提交类型1是新增。0是编辑
				this.$refs[formName].validate(function(valid) {
							if(valid) {
								vm.loading.saveLoading=true;
								var param ={};
								var api="";
								if(type){
									api=vm.api.addApi;
								}else{
									api=vm.api.editApi;
									param.id=vm.positionIt.id;
								}
								param.name=vm.positionIt.name;
								param.jobTypeId=vm.positionIt.jobTypeId;
								param.jobGradeId=vm.positionIt.jobGradeId;
								param.salaryTypeId=vm.positionIt.salaryTypeId;
				            Vue.http.post(api,param,{emulateJSON:true})
							.then(function(response) {
								response.json().
								then(function(responseData) {
								vm.$refs[formName].resetFields();
								vm.editShow=false;
								vm.loading.saveLoading=false;
								if(responseData.code=="200"){
									vm.msg.type="success";
									vm.msg.msg=type?"新键成功":"编辑成功";
									vm.searchParam={};
									vm.getPositionsData();
								}else{
									vm.msg.type="error";
									vm.msg.msg=type?"新建失败":"编辑失败";				
								}
								vm.$message({
										type:vm.msg.type,
										message:vm.msg.msg
								    });
							    })	
							}).catch(function(){
								vm.$refs[formName].resetFields();
								vm.editShow=false;
								vm.$message({
										type:"error",
										message:type?"新增失败":"编辑失败"
								});
							})
		        } else {
		            return false;
		        }
			})
		},
		//删除
		handleDelete: function handleDelete(data) {
				var _this2 = this;
				this.$confirm('是否确认删除该职位？', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
				}).then(function () {
						Vue.http.post(_this2.api.delApi,{ids:data.id},{emulateJSON:true})
						.then(function(response) {
							response.json().
							then(function(responseData) {
								console.log(responseData);	
								if(responseData.code=="200"){
									_this2.msg.type="success";
									_this2.msg.msg="删除成功";
									_this2.getPositionsData();
								}else{
									_this2.msg.type="error";
									_this2.msg.msg="删除失败";
								}
								_this2.$message({
										type: _this2.msg.type,
										message: _this2.msg.msg
									});
							})
						})
				}).catch(function () {
						_this2.$message({
								type: 'info',
								message: '已取消删除'
						});
				});
		},
		//取消
		cancleIt:function(formName){
			this.$refs[formName].resetFields();
			this.editShow=false;
		},
		closeDialog:function(done){
			this.$refs["positionForm"].resetFields();
			done();
		},
		//筛选
		fillterdate:function(){
			this.loading.search=true;
			this.page = 1;
			this.getPositionsData();
		},
		//重置
		resetSearch:function(formName){
			this.$refs[formName].resetFields();
//			this.getPositionsData();
		}
	}
})
</script>
</body>
</html>