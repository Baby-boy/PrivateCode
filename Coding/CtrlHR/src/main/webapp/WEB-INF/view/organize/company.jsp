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
<link rel="stylesheet" type="text/css" href="${ctx}/css/company.css" />
</head>
<body>
	<%@ include file="/WEB-INF/view/components/left_nav.jspf"%>
	<%@ include file="/WEB-INF/view/components/page_top.jspf"%>
	<%@ include file="/WEB-INF/view/components/search.jspf"%>
	<%@ include file="/WEB-INF/view/components/rightCard.jspf"%>
	<%@ include file="/WEB-INF/view/components/header.jspf"%>
	<div class="wrapInner" id="wrapInner" v-cloak>
		<div class="wrap">
			<header-nav></header-nav>
			<div class="contentWrap">
				<left-nav :left-list="leftList" :current="0"></left-nav>
				<!--顶部筛选-->
				<page-top page-title="公司管理" page-description="在这里您可以对公司信息进行设置">
				<div class="pageTopButton fr">
						<el-button type="button" class="el-button el-button--primary" @click="addNew"> 
							<i class="el-icon-plus"></i> 新建
						</el-button>
				</div>
				<div class="pageTopSearch fr">
					<search search-tips="搜索姓名或者职员代码..." @search="searchAttendance"></search>
				</div>
				</page-top>
				<!--数据操作按钮-->
				<div class="pageOperate clearfix">
					<el-button @click="delCheck">删除</el-button>
				</div>
				<!--数据列表-->
				
				<el-table :data="companyData.list" stripe v-loading="isloading">
					<el-table-column label="全选" type="selection"> <el-checkbox></el-checkbox></el-table-column> 
					<el-table-column prop="name" label="公司名称" width="110"></el-table-column>
					<el-table-column prop="fullName" align="center" label="公司全称" ></el-table-column> 
					<el-table-column prop="shortName" align="center" label="公司简称"></el-table-column> 
					<el-table-column prop="chief" align="center" label="上级公司"></el-table-column>
					<el-table-column prop="lowerNames" align="center" label="下级公司"></el-table-column>
					<el-table-column colspan="2" align="center" label="操作">
						<template scope="scope"> 
							<el-button type="text" size="small" @click="edit(scope.row)">编辑</el-button>
							<el-button type="text" size="small" @click="delIt(scope.row)">删除</el-button> 
						</template> 
					</el-table-column>
				</el-table>
				
				<!--分页-->
				<div class="pagination" v-if="companyData.list && companyData.list.length>0">
					<el-pagination 
						layout="prev,pager,next" 
						:current-page="page"
						:page-size="pageSize" 
						:total="companyData.totalCount"
						@current-change="handleCurrentChange">
					</el-pagination>
				</div>
				<!--编辑公司-->
				<el-dialog  v-model="editShow" class="hasModal">
					<div slot="title" class="dialog-title text-center hasLine">
						<span>{{dialogTitle}}</span>
					</div>
					<el-form :model="company" label-width="120px">
						<el-form-item>
							<span class="fr c-grey">*为必填项</span>
						</el-form-item> 
						<el-form-item label="* 公司全称">
							<el-input v-model="company.fullName" placeholder="请输入公司全称"></el-input>
						</el-form-item> 
						<el-form-item label="* 公司简称">
							<el-input v-model="company.shortName" placeholder="请输入公司简称"></el-input> 
						</el-form-item>
						<el-form-item label="上级公司">
							<template>
								<el-select v-model="company.chief" class="block" clearable filterable placeholder="请选择上级公司">
									<el-option 
										v-for="companyIt in companys"
										:key="companyIt.key"
										:label="companyIt.value"
										:value="companyIt.key"
										>
									</el-option>
								</el-select>
							</template>
						</el-form-item>
						<el-form-item label="下级公司">
							<ul class="lowerList">
								<li class="lower" v-for="lower in company.lowers">
									<el-select v-model="lower.name" clearable filterable placeholder="请选择下级公司">
									<el-option 
										v-for="companyIt in companys"
										:key="companyIt.key"
										:label="companyIt.value"
										:value="companyIt.key"
										>
									</el-option>
									</el-select>
									<el-select v-model="lower.type"  placeholder="请选择字母公司">
										<el-option  key="类型1" label="子公司" value="类型1"></el-option>
										<el-option  key="类型2" label="母公司" value="类型1"></el-option>
									</el-select>
									<el-checkbox v-model="lower.power" class="margin-l-10">具有控股权</el-checkbox>
									<span class="fr v-center">
										<el-button type="primary" class="circle circle-small "><i class="el-icon-plus c-white"></i></el-button>
									</span>
									<div>
										<el-checkbox v-model="lower.power">设置当前公司和下级关系</el-checkbox>
									</div>
								</li>	
							</ul>
						</el-form-item>
					</el-form>
					<div slot="footer" class="text-center">
							<el-button type="primary" @click.stop="confirmQuit">确定</el-button> 
							<el-button @click.stop="editShow=false">取消</el-button>
					</div>
				</el-dialog>
			</div>
		</div>
	</div>
<script>
new Vue({
	el: '#wrapInner',
	data: {
	companys:[{
		key:"选项1",
		value:"伽然"		
	}],
		companyData:{},
		company:{
				name:"伽然",
				fullName:"伽然",
				shortName:"伽然",
				chief:"伽然",
				lowerNames:"伽然,伽然",
				lowers:[
					{
						name:"伽然",
						type:"子公司",
						power:true,
					},
					{
						name:"伽然",
						type:"子公司",
						power:true,
					}
				]
		},
		page:1,
		pageSize:10,
		popupShow: false,
		isloading: true,
		dialogTitle:"",
		searchTxt: '',
		checkedCompany: [],
		checkAll: false,
		isIndeterminate: false,
		checkedEquipment: [],
		editShow:false,
		ids: [],
		httpUrl: {
			companys: "${ctx}/api/signGroup/list",
		},
		param:{},
		leftList: [
			{
				"id": 1,
				"text": "公司管理",
				"link": "${ctx}/pageTo/organize/company?viewName=0;"
			},
			{
				"id": 2,
				"text": "部门管理",
				"link": "${ctx}/pageTo/organize/department?viewName=0;"
			},
			{
				"id": 3,
				"text": "职位管理",
				"link": "${ctx}/pageTo/organize/position?viewName=0;"
			},
			{
				"id": 4,
				"text": "编制管理",
				"link": "javascript:;"
			},
			{
				"id": 5,
				"text": "架构一览",
				"link": "javascript:;"
			}
		],
	},
	mounted:function(){
		this.getCompanyData();
	},
	methods: {
		//顶部搜索
		searchAttendance: function(txt) {
			var vm = this;
			vm.searchTxt = txt;
			vm.page = 1;
			vm.getCompanyData();
		},
		//批量删除公司
		delCheck:function(){
			this.$confirm("您确定要删除此公司吗？","提示",{
				confirmButtonText:"确认",
				cancelButtonText:"取消",
				customClass:"delConfirm",
				type:"warning"
			}).then(function(){
				
			}).catch(function(){
				
			})
		},
		//获取公司数据
		getCompanyData:function(){
			var vm = this;
			Vue.http.get(vm.httpUrl.companys + "?keyword=" + vm.searchTxt)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					vm.companyData={
						id:"1",
						code:"2",
						totalCount:1,
						list:[
								{
									name:"伽然",
									fullName:"伽然",
									shortName:"伽然",
									chief:"伽然",
									lowerNames:"伽然,伽然",
									lowers:[{
												name:"伽然",
												type:"子公司",
												power:true,
											},
											{
												name:"伽然",
												type:"子公司",
												power:true,
											}
										]
								}
							]
						}
					vm.isloading = false;
				})					
			})
		},
		//分页方法
		handleCurrentChange(val){
			this.page = val;
			this.getCompanyData();
		},
		//全选和单选部分
		handleCheckAllChange(event) {
			var vm = this;
			this.checkedEquipment = event.target.checked ? this.equipmentData.list : []
			this.isIndeterminate = false;
			for (var i = 0; i < vm.equipmentData.list.length; i++) {
				vm.equipmentData.list[i].active = true;
			}
		},
		//编辑公司
		edit:function(company){
			this.dialogTitle="编辑公司"
			this.company=company;
			this.editShow=true;
		},
		addNew:function(){
			this.dialogTitle="新建公司";
			this.company={
				name:"",
				fullName:"",
				shortName:"",
				chief:"",
				lowerNames:"",
				lowers:[
					{
						name:"",
						type:"",
						power:true,
					},
					{
						name:"",
						type:"",
						power:true,
					}
				]
			};
			this.editShow=true;
		},
		delIt:function(it){
		},
		confirmQuit:function(){
			this.editShow=false;
		},
		
	}
})
</script>
</body>
</html>