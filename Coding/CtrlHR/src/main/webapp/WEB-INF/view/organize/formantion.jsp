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
<!--<link rel="stylesheet" href="${ctx}/css/formantion.css">-->
	<style>
		.el-dialog{width:539px;}
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
	<div class="wrapInner" id="wrapInner" v-cloak>
			<header-nav></header-nav>
			<div class="contentWrap">
				<left-nav :left-list="leftList" :current="2"></left-nav>
				<!--顶部筛选-->
				<page-top page-title="编制管理" page-description="在这里您可以对部门进行设置">
				<div class="pageTopButton fr">
						<el-button type="primary" icon="el-icon-plus" @click="addNew('editForm')"> 
							<i class="el-icon-plus"></i> 新建
						</el-button>
				</div>
				<div class="pageTopSearch fr">
					<search search-tips="搜索编制名称或代码..." @search="searchIt"></search>
				</div>
				</page-top>
				<!--数据列表-->
				<div class="tree">
					<fieldset>
					    <div style="text-align:left;line-height:40px;height:40px;font-weight:bold;font-size:14px;">
					        <span style="padding-left:50px;">编制名称</span>
					        <span style="width:200px;float:right;text-align:center">操作</span>
					        <span style="width:200px;float:right;text-align:center">工作制</span>
					        <span style="width:200px;float:right;text-align:center">在职</span>
					        <span style="width:200px;float:right;text-align:center">编制</span>
					    </div>
					    <el-tree style="text-align:left;font-size:14px;"
					        :data="formantionData"
					        :props="treeTemp"
					        node-key="id"
					        default-expand-all
					        :expand-on-click-node="false"
					        :render-content="renderTree">
					    </el-tree>
					</fieldset>
				 </div>
				<!--编辑-->
				<el-dialog  v-model="editShow" :before-close="closeDialog" :close-on-click-modal="false">
					<div slot="title" class="dialog-title text-center hasLine">
						<span>{{dialogTitle}}编制</span>
					</div>
					<el-form :model="formantion" :rules="rules" ref="editForm" label-width="100px" >
						<el-form-item label="选择部门" prop="deptId" required>
							<el-cascader :options="deptTree" v-model="formantion.deptId" :props="deptConfig" @change="changeDet" placeholder="请选择部门" filterable change-on-select>
							</el-cascader>
						</el-form-item> 
						<el-form-item label="选择职位" prop="jobId" required>
								<el-select v-model="formantion.jobId"  placeholder="请选职位"  filterable>
									<el-option v-for="pos in posLists" :key="pos.id"
	      								:label="pos.name"
	     								:value="pos.id">
									</el-option>
								</el-select>
						</el-form-item> 
						<el-form-item label="编制人数" prop="organizeNum" required>
							<el-input-number v-model="formantion.organizeNum" :min="1"></el-input-number>
						</el-form-item> 
						<el-form-item label="工作制类型" prop="workSystemId" required>
								<el-select v-model="formantion.workSystemId"  placeholder="请选择职位类型"  filterable>
									<el-option v-for="type in workSystems" :key="type.id"
	      								:label="type.name"
	     								:value="type.id">
									</el-option>
								</el-select>
						</el-form-item> 
						<el-form-item label="管辖子部门" prop="deptIds">
								<el-select v-model="formantion.deptIds" multiple filterable  placeholder="请选管辖子部门"  >
									<el-option v-for="childrenDep in childrenDepts" :key="childrenDep.id"
	      								:label="childrenDep.name"
	     								:value="childrenDep.id">
									</el-option>
								</el-select>
						</el-form-item> 
					</el-form>
					<div slot="footer" class="text-center">
							<el-button type="primary" :loading="saveLoading" @click="saveIt('editForm')">确定</el-button> 
							<el-button @click="cancleIt('editForm')">取消</el-button>
					</div>
				</el-dialog>
			</div>
	</div>
<script>
new Vue({
	el: '#wrapInner',
	data: {
		formantionData:[],
		childId:"",
		saveLoading:false,
		treeTemp:{
		 		children: 'child',
				label: 'name'
		},
		deptTree:[],
		formantion:{},
		childrenDepts:[],
		workSystems:[],
		posLists:[],
		deptConfig:{
			children:'childDept',
			label:'name',
			value:"id",
		},
		isloading: true,
		searchTxt: '',
		dialogTitle:"",
		editShow:false,
		rules: {
          deptId: [
            { required: true, message: '请选择部门'},
          ],
          jobId: [
            { required: true, message: '请选择职位',trigger:'blur'},
          ],
          organizeNum: [
            { required: true, message: '请填写编制人数'},
          ],
          workSystemId: [
            { required: true, message: '请选择工作制'},
          ],
    	},
    	msg:{type:"",msg:""},
		api: {
			treeApi: "${ctx}/api/jobOrg/list",
			deptTreeApi: "${ctx}/api/dept/getDeptTree",
			deptApi:"${ctx}/api/dept/getDept",
			workSystemApi:"${ctx}/api/job/getWorkSystem",
			childrenDeptsApi:"${ctx}/api/jobOrg/getChindDept",
			workSystemApi:"${ctx}/api/job/getWorkSystem",
			posListApi:"${ctx}/api/job/simpleList",
			addApi:"${ctx}/api/jobOrg/add",
			editApi:"${ctx}/api/jobOrg/update",
			orgDetailApi:"${ctx}/api/jobOrg/getInfo"
		},
		leftList: [
//			{
//				"id": 1,
//				"text": "公司管理",
//				"link": "${ctx}/pageTo/organize/formantion?viewName=0"
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
		this.getTreeData();
	},
	computed:{
		
	},
	methods: {
		//顶部搜索
		searchIt: function(txt) {
			var vm = this;
			vm.searchTxt = txt;
			vm.page = 1;
			vm.getTreeData();
		},
		//获取部编制树数据
		getTreeData:function(){
			var vm = this;
			var param=vm.searchTxt?"?kw=" + vm.searchTxt:"";
			Vue.http.get(vm.api.treeApi+param)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					vm.formantionData=responseData.data;
					vm.isloading = false;
				})					
			})
		},
		//获取部门树数据
		getDepTree:function(){
			var vm = this;
			Vue.http.get(vm.api.deptTreeApi)
			.then(function(response) {
				response.json().
				then(function(responseData) {
						vm.deptTree=responseData.data;
//					console.log(vm.deptTree);
				})					
			})
		},
//		//获取编制管辖的子部门
		getChildrenDepts:function(id){
			var vm = this;
			Vue.http.get(vm.api.deptApi+"?id="+id)
				.then(function(response) {
					response.json().
					then(function(responseData) {
						vm.childrenDepts=responseData.data.childDept?responseData.data.childDept:[];
						console.log("子部门集合");
						console.log(responseData)
//						console.log(vm.childrenDepts);
					})					
				})
		},
		//获取工作制类型集合
		getworkSystems:function(){
			var vm = this;
			Vue.http.get(vm.api.workSystemApi)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					vm.workSystems=responseData.data;
//					console.log("工作制");
//					console.log(responseData);
				})					
			})
		},
		//职位
		getPosLists:function(id){
			var vm = this;
			var param="?deptIds=";
			if(id)param+=id;
			Vue.http.get(vm.api.posListApi+param)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					vm.posLists=responseData.data;
//					console.log("职位");
//					console.log(responseData);
				})					
			})
		},
		//新增
		addNew:function(){
			this.formantion={
//					deptId:[],
					jobId:"",
					organizeNum:1,
					workSystemId:"",
					deptIds:[],
			}
			this.getPosLists();
			this.getworkSystems();
			this.getDepTree();
			this.dialogTitle="新建";
			this.editShow=true;
		},
		//选择的部门改变
		changeDet:function(ids){
			if(ids.length){
				this.formantion.jobId="";
				this.formantion.deptIds=[];
				this.getPosLists(ids[ids.length-1]);
				this.getChildrenDepts(ids[ids.length-1])				
			}
		},
		//编辑
		handleEdit: function handleEdit(data) {
			console.log(data);
			var vm = this;
			var param={};
			param.id=data.orgId;
			param.deptId=data.deptId;
			param.jobId=data.id;
			param.organizeNum=data.orgCount;
			param.workSystemId=data.workSystemId;
			Vue.http.get(vm.api.childrenDeptsApi+"?id="+data.orgId)
			.then(function(response) {
				response.json().
				then(function(responseData) {
//					console.log("子管辖");
//					console.log(responseData);
					if(responseData.data.length){
						var ids="";
						responseData.data.forEach(function(dep){
							if(dep.deptId) ids+=","+dep.deptId;
						})
						param.childIds=ids.substring(1);
					}
					Vue.http.post(vm.api.editApi,param,{emulateJSON:true})
					.then(function(response){
						response.json().
						then(function(responseData) {
							console.log(responseData);
							console.log()
							if(responseData.code=="200"){
								vm.msg.type="success";
								vm.msg.msg="更新成功";
								vm.searchTxt="";
								vm.getTreeData();
							}else{
								vm.msg.type="error";
								vm.msg.msg="更新失败";	
							}
							vm.$message({
												type:vm.msg.type,
												message:vm.msg.msg
										});
						})					
					})
				})					
			})
		},
		//保存
		saveIt: function(formName) {
				var vm = this;
				vm.$refs[formName].validate(function(valid) {
							if(valid) {
								vm.saveLoading=true;
								var param ={};
									if(vm.formantion.deptId.length) {
										param.deptId = vm.formantion.deptId[vm.formantion.deptId.length - 1]
									} else {
										param.parentId ="";
						            }
						            debugger;
									if(vm.formantion.deptIds.length){
						          	 	var ids="";
						          	  	vm.formantion.deptIds.forEach(function(id){
						          		ids +=","+id;
						          		});
						           		param.deptIds=ids.substring(1);
						            }else{
						           		param.deptIds="";
						            }
								    param.jobId=vm.formantion.jobId;
								    param.workSystemId=vm.formantion.workSystemId;
								    param.organizeNum=vm.formantion.organizeNum;
								    console.log(param)


				            Vue.http.post(vm.api.addApi,param,{emulateJSON:true})
							.then(function(response) {
								response.json().
								then(function(responseData) {
								vm.$refs[formName].resetFields();
								vm.editShow=false;
								vm.saveLoading=false;
								if(responseData.code=="200"){
									vm.msg.type="success";
									vm.msg.msg="新增成功";
									vm.getTreeData();
								}else{
									vm.msg.type="error";
									vm.msg.msg=responseData.msg;				
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
										message:"新增失败"
								});
							})
		        } 
			})
			
		},
		cancleIt:function(formName){
			this.$refs[formName].resetFields();
			this.editShow=false;
		},
		closeDialog:function(done){
			this.$refs["editForm"].resetFields();
			done();
		},
		//创建树结构html
		renderTree:function renderOrgTree3(h, _ref) {
			  var _this = this;
			
			  var node = _ref.node,
			      data = _ref.data,
			      store = _ref.store;
			
			  var name;
			  var editBtn;
			  var formationCount;
			  var emplCount;
			  var workSystem;
			  if (data.type==2) {
			    editBtn = h(
			      "el-button",
			      {
			        attrs: { size: "small" },
			        on: {
			          "click": function click(e) {
			            return _this.handleEdit(data);
			          }
			        }
			      },
			      ["\u66F4\u65B0"]
			    );
			    if (!data.orgCount) {
			      data.orgCount = 1;
			    }
			    formationCount = h(
			      "el-input-number",
			      {
			        attrs: { min: 1, size: "small", value: data.orgCount },
			        on: {
			          "input": function input(e) {
			            return data.orgCount = e;
			          }
			        },
			        "class": "vert-m" },
			      []
			    );
			    workSystem = data.workSystem ? data.workSystem : '暂无';
			    emplCount = data.emplCount ? data.emplCount : "0";
			  } 
			  return h(
			    "span",
			    { "class": "tree-table-row" },
			    [h(
			      "span",
			      null,
			      [h(
			        "span",
			        null,
			        [data.name]
			      )]
			    ), h(
			      "span",
			      { "class": "tree-table-cell", style: "width:200px;text-align:center" },
			      [editBtn]
			    ), h(
			      "span",
			      { "class": "tree-table-cell", style: "width:200px;text-align:center" },
			      [workSystem]
			    ), h(
			      "span",
			      { "class": "tree-table-cell", style: "width:200px;text-align:center" },
			      [emplCount]
			    ), h(
			      "span",
			      { "class": "tree-table-cell", style: "width:200px;text-align:center" },
			      [formationCount]
			    )]
			  );
			},
		confirm: function confirm() {
				var _this3 = this;
				this.$msgbox({
						title: '消息',
						message: '这是一段内容, 这是一段内容, 这是一段内容, 这是一段内容, 这是一段内容, 这是一段内容, 这是一段内容',
						showCancelButton: true,
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						beforeClose: function beforeClose(action, instance, done) {
								if (action === 'confirm') {
										instance.confirmButtonLoading = true;
										instance.confirmButtonText = '执行中...';
										setTimeout(function () {
												done();
												setTimeout(function () {
														instance.confirmButtonLoading = false;
												}, 300);
										}, 3000);
								} else {
										done();
								}
						}
				}).then(function (action) {
						_this3.$message({
								type: 'info',
								message: 'action: ' + action
						});
				});
		},
		alert: function alert() {
				var _this4 = this;

				this.$alert('这是一段内容', '标题名称', {
						confirmButtonText: '确定',
						callback: function callback(action) {
								_this4.$message({
										type: 'info',
										message: 'action: ' + action
								});
						}
				});
		}
	}
})
</script>
</body>
</html>