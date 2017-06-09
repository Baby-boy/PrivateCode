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
.el-dialog{width: 602px;}
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
				<left-nav :left-list="leftList" :current="0"></left-nav>
				<!--顶部筛选-->
				<page-top page-title="部门管理" page-description="在这里您可以对部门进行设置">
				<div class="pageTopButton fr">
						<el-button type="primary" icon="el-icon-plus" @click="addNew"> 
							<i class="el-icon-plus"></i> 新建
						</el-button>
				</div>
				<div class="pageTopSearch fr">
					<search search-tips="搜索部门名称或代码..." @search="searchDepartment"></search>
				</div>
				</page-top>
				<!--数据列表-->
				<div class="tree">
					  <fieldset id="orgTree">
					      <div style="text-align:left;line-height:40px;height:40px;font-weight:bold;font-size:14px;">
					        <span style="padding-left:50px;">部门名称</span>
					        <span style="width:200px;float:right;text-align:center">操作</span>
					        <span style="width:200px;float:right;text-align:center">职位类型</span>
					        <span style="width:200px;float:right;text-align:center">部门代码</span>
					      </div>
					      <el-tree style="text-align:left;font-size:14px;"
					        :data="departmentData"
					        :props="treeTemp"
					        node-key="id"
					        default-expand-all
					        :expand-on-click-node="false"
					        :render-content="renderTree">
					      </el-tree>
					  </fieldset>
				 </div>
				<!--编辑-->
				<el-dialog :close-on-click-modal="false" v-model="editShow" :before-close="closeDialog" :lock-scroll="false">
					<div slot="title" class="dialog-title text-center hasLine">
						<span>{{dialogTitle}}</span>
					</div>
					<el-form :model="department" :rules="rules" ref="editForm" label-width="80px" >
						<el-form-item label="部门名称" prop="name" required>
							<el-input v-model="department.name" placeholder="请输入部门名称"></el-input>
						</el-form-item> 
						<el-form-item label=" 部门代码" prop="code">
							<el-input v-model="department.code" placeholder="请输入部门代码"></el-input>
						</el-form-item> 
						<el-form-item label="上级部门" prop="parentId">
							<el-cascader clearable @change="department.childDeptIds = []" :options="deptOptions" v-model="department.parentId" v-if="dialogTitle=='新建部门'&&departmentData.length" :props="deptConfig"  placeholder="请选择上级部门" filterable change-on-select>
							</el-cascader>
							<span v-if="department.parentDept">{{department.parentDept.name}}</span>
							<span v-else>无</span>
						</el-form-item> 
						<el-form-item label="下级部门" prop="childDeptIds">
							<el-select v-if="dialogTitle=='新建部门'"  v-model="department.childDeptIds" multiple filterable placeholder="请选择下级部门"  >
								<el-option v-for="childrenDep in validSubDept" :key="childrenDep.id"
      								:label="childrenDep.name"
     								:value="childrenDep.id">
								</el-option>
							</el-select>
							<span v-else-if="department.childDept" v-for="(child,index) in department.childDept">{{child.name}} <span v-if="index < (department.childDept.length-1)">,</span> </span>
							<span v-else>无</span>
						</el-form-item> 
						<el-form-item label="部门职责" prop="duty">
							<el-input v-model="department.duty" type="textarea" relize="false" placeholder="请输入部门职责" class="noResize"></el-input>
						</el-form-item> 
						<el-form-item label="职位类型" prop="jobTypeIds" clearable>
								<el-select v-model="department.jobTypeIds" multiple filterable placeholder="请选择职位类型"  >
									<el-option v-for="type in jobTypes" :key="type.id"
	      								:label="type.name"
	     								:value="type.id">
									</el-option>
								</el-select>
						</el-form-item> 
					</el-form>
					<div slot="footer" class="text-center">
							<el-button type="primary" @click="saveIt('editForm')" :loading="saveLoading">确定</el-button> 
							<el-button @click="cancleIt('editForm')">取消</el-button>
					</div>
				</el-dialog>
			</div>
	</div>
<script>
new Vue({
	el: '#wrapInner',
	data: {
		departmentData:[],
		childId:"",
		treeTemp:{
		 		children: 'childDept',
				label: 'name'
		},
		department:{},
		childrenDepts:[],
		deptOptions:[],
		jobTypes:[],
		deptConfig:{
			children: 'childDept',
			label: 'name',
			value:"id",
		},
		saveLoading:false,
		isloading: true,
		searchTxt: '',
		dialogTitle:"",
		editShow:false,
		rules: {
          name: [
            { required: true, message: '请输入部门名称', trigger: 'blur' },
            { min: 2, message: '长度应大于 2个字符', trigger: 'submit' }
          ]
    	},
    	msg:{type:"",msg:""},
		api: {
			deptTreeApi: "${ctx}/api/dept/getDeptTree",
			deptApi:"${ctx}/api/dept/getDept",
			jobTypesApi:"${ctx}/api/job/getJobType",
			childrenDeptsApi:"${ctx}/api/dept/getNotParentDept",
			addOrUpdateApi:"${ctx}/api/dept/addOrUpdateDept",
			delDept:"${ctx}/api/dept/delDept"
		},
		leftList: [
//			{
//				"id": 1,
//				"text": "公司管理",
//				"link": "${ctx}/pageTo/organize/department?viewName=0"
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
		this.getDepData();
	},
	computed:{
		validSubDept:function(){
			var vm = this, rootDeptId = null, currentParentId = vm.department ? vm.department.parentId:null;
			
			if(currentParentId){
				rootDeptId = currentParentId[0];
				var temp = [];
				vm.childrenDepts.forEach(function(child){
					if(child.id != rootDeptId){
						temp.push(child);
					}
				})
				
				return temp;
			}
			else{
				return vm.childrenDepts;
			}	
		}
	},
	methods: {
		//顶部搜索
		searchDepartment: function(txt) {
			var vm = this;
			vm.searchTxt = txt;
			vm.page = 1;
			vm.getDepData();
		},
		//获取部门树数据
		getDepData:function(){
			var vm = this;
			var param=vm.searchTxt?"?kw=" + vm.searchTxt:"";
			Vue.http.get(vm.api.deptTreeApi + param)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					vm.departmentData=responseData.data;
					vm.isloading = false;
				})					
			})
		},
		//获取部门数据
		getDepts:function(id){
			var vm = this;
			//初始化解决点击x号关闭，再次打开报错;
			this.department={
					name:"",
					code:"",
					parentId:[],
					childDeptIds:[],
					jobTypeIds:[],
					duty:""
					};
			Vue.http.get(vm.api.deptApi+"?id="+id)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					console.log(responseData.data);
					var childDeptIds=[];
					if(responseData.data.childDept&&responseData.data.childDept.length){
						for (var i = 0; i < responseData.data.childDept.length; i++) {
							childDeptIds.push(responseData.data.childDept[i].id)
						}
						vm.department.childDeptIds=childDeptIds.join(",");
					}else{
						vm.department.childDeptIds="";
					}
					var jobTypeIds=[];
					if(responseData.data.jobTypes&&responseData.data.jobTypes.length){
						for (var i = 0; i < responseData.data.jobTypes.length; i++) {
							jobTypeIds.push(responseData.data.jobTypes[i].id)
						}
					}
					vm.department.jobTypeIds=jobTypeIds;
					vm.department.parentDept=responseData.data.parentDept;
					vm.department.childDept=responseData.data.childDept;
					vm.department.id=responseData.data.id;
					vm.department.name=responseData.data.name;
					vm.department.code=responseData.data.code;
					vm.department.parentId=responseData.data.parentId;
					vm.department.duty=responseData.data.duty;
					console.log(vm.department);
				    vm.isloading = false;
				})					
			})
		},
		//获取所有部门
		getDeptOptions:function(){
			var vm = this;
			Vue.http.get(vm.api.deptTreeApi)
			.then(function(response) {
				response.json().
				then(function(responseData) {
					vm.deptOptions=responseData.data;
					vm.isloading = false;
				})					
			})
		},
		//获取下部门级数据
		getChildrenDepts:function(){
			var vm = this;
			Vue.http.get(vm.api.childrenDeptsApi)
			.then(function(response) {
				response.json().
				then(function(responseData) {
//					if(responseData.data){
						vm.childrenDepts=responseData.data;
//					}
//					console.log("下级部门")
//					console.log(responseData);
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
		//新增
		addNew:function(){
			this.department={
							name:"",
							code:"",
							parentId:[],
							childDeptIds:[],
							jobTypeIds:[],
							duty:""}
			this.dialogTitle="新建部门";
			this.getJobTypes();
			this.getChildrenDepts();
			this.getDeptOptions();
			this.editShow=true;
		},
		//编辑
		handleEdit: function handleEdit(data) {
			this.dialogTitle="编辑部门";
//			this.department=data;
			this.getJobTypes();
			this.getChildrenDepts();
			this.getDeptOptions();
			this.getDepts(data.id);
			this.editShow=true;
		},
		//删除
		handleDelete: function handleDelete(data) {
				var _this2 = this;
				this.$confirm('是否确认删除该部门?', '提示', {
						confirmButtonText: '确定',
						cancelButtonText: '取消',
						type: 'warning'
				}).then(function () {
					if(data.childDept==null||!data.childDept.length){
						Vue.http.post(_this2.api.delDept,{ids:data.id},{emulateJSON:true})
						.then(function(response) {
							response.json().
							then(function(responseData) {
								if(responseData.code=="200"){
									_this2.msg.type="success";
									_this2.msg.msg="删除成功";
									_this2.getDepData();
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
					}else{
						_this2.$message({
										type:"warning",
										message: "此部门有子部门或职位不能删除"
									});
					}
				}).catch(function () {
						_this2.$message({
								type: 'info',
								message: '已取消删除'
						});
				});
		},
		//保存
		saveIt: function(formName) {
				var vm = this;
				var type=vm.dialogTitle=="新建部门"?1:0;//判断提交类型1是新增。0是编辑
				vm.$refs[formName].validate(function(valid) {
							if(valid) {
								vm.saveLoading=true;
								var param ={};
								if(type){
									if(vm.department.parentId.length) {
										param.parentId = vm.department.parentId[vm.department.parentId.length - 1]
									} else {
										param.parentId ="";
						            }
									if(vm.department.childDeptIds.length){
										var ids="";
						          	  	vm.department.childDeptIds.forEach(function(id){
						          		ids+=","+id;
						          		});
						           		param.childDeptIds=ids.substring(1);
						            }else{
						           		param.childDeptIds="";
						            }
								}else{
								    param.id=vm.department.id;
								    param.childDeptIds=vm.department.childDeptIds;
									param.parentId=vm.department.parentId;
								}
								debugger;
								param.jobTypeIds=vm.department.jobTypeIds.join(",");
								param.name=vm.department.name;
							    param.code=vm.department.code;
							    param.duty=vm.department.duty;
							   
				            Vue.http.post(vm.api.addOrUpdateApi,param,{emulateJSON:true})
							.then(function(response) {
								response.json().
								then(function(responseData) {
									vm.$refs[formName].resetFields();
									vm.editShow=false;
									vm.saveLoading=false;
									if(responseData.code=="200"){
										vm.msg.type="success";
										vm.msg.msg=type?"新增成功":"编辑成功";
										vm.searchTxt="";
										vm.getDepData();
									}else{
										vm.msg.type="error";
										vm.msg.msg=type?"新增失败":"编辑失败";				
									}
									vm.$message({
											type:vm.msg.type,
											message:vm.msg.msg
									});
								})	
							}).catch(function(){
								vm.$message({
										type:"error",
										message:type?"新增失败":"编辑失败"
								});
								vm.$refs[formName].resetFields();
								vm.editShow=false;
							})
		        } else {
		            return false;
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
		renderTree: function(h, _ref) {
				var _this = this;

				var node = _ref.node,
				    data = _ref.data,
				    store = _ref.store;
				var jobTypes=[];
				for (var i = 0; i < _ref.data.jobTypes.length; i++) {
					jobTypes.push(_ref.data.jobTypes[i].name);
				}
				var jobTypesName=jobTypes.join(",");
				return h(
						'span',
						{ 'class': 'tree-table-row' },
						[h(
								'span',
								null,
								[data.name]
						), h(
								'span',
								{ 'class': 'tree-table-cell', style: 'width:200px;text-align:center;' },
								[h(
										'el-button',
										{
												attrs: { type: 'text', size: 'small' },
												on: {
														'click': function click(e) {
																return _this.handleEdit(data);
														}
												}
										},
										['\u7F16\u8F91']
								), h(
										'el-button',
										{
												attrs: { type: 'text', size: 'small' },
												on: {
														'click': function click(e) {
																return _this.handleDelete(data);
														}
												}
										},
										['\u5220\u9664']
								)]
						), h(
								'span',
								{ 'class': 'tree-table-cell',attrs:{
									title:[jobTypesName]
								},style: 'width:200px;text-align:center;overflow:hidden;white-space:nowrap;text-overflow:ellipsis;' },
								[jobTypesName?jobTypesName: "-"]
						), h(
								'span',
								{ 'class': 'tree-table-cell', style: 'width:200px;text-align:center' },
								[data.code]
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