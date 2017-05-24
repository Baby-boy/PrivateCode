<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/view/include/taglibs.jspf" %>
<!DOCTYPE html>
<html class="no-js">
<head>
  <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>考勤记录</title>
    <meta name="description" content="">  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <%@ include file="/WEB-INF/view/include/head_include.jspf" %>
    <link rel="stylesheet" type="text/css" href="${ctx}/css/attendance.css"/>
</head>
	<body>
		<%@ include file="/WEB-INF/view/components/left_nav.jspf" %>
		<%@ include file="/WEB-INF/view/components/page_top.jspf" %>
		<%@ include file="/WEB-INF/view/components/search.jspf" %>
		<%@ include file="/WEB-INF/view/components/rightCard.jspf" %>
		<%@ include file="/WEB-INF/view/components/header.jspf" %>
		<div class="wrapInner" id="wrapInner"  v-cloak>				
			<div class="wrap">
				<header-nav></header-nav>
				<div class="contentWrap">	
					<left-nav :left-list="leftList" :current="0"></left-nav>
					<!--顶部筛选-->
					<page-top page-title="考勤记录" page-description="在这里您可以对公司进行考勤信息进行设置">
						<div class="pageTopButton fr">
							<el-button type="text" icon="setting" class="textBtn" size="mini" @click="popupShow = true">考勤管理</el-button>
						</div>
						<div class="pageTopSearch fr">
							<search search-tips="搜索姓名或者职员代码..." @search="searchAttendance"></search>
						</div>
					</page-top>
					<div class="pageOperate clearfix">
						<el-button type="primary" size="small" @click="getExport"><i class="iconfont icon-export"></i>&nbsp;导出</el-button>
						<el-button type="primary" size="small" @click="getDetailExport"><i class="iconfont icon-export"></i>&nbsp;导出考勤详情</el-button>
						<a href="javascript:;" @click="showRightSearch" class="fr btnFillter" ><i class="iconfont icon-sort"></i>&nbsp;高级筛选</a>
						<!--右侧高级筛选-->
						<right-card card-title="筛选器" :show="showRight" @hide="showRightCard">
							<dl class="dateFillter">
								<dt><i class="el-icon-date"></i> 时间筛选</dt>
								<!--<dd class="clearfix">
									<el-select v-model="fillterYear" placeholder="请选择年" size="small" class="fl">
										<el-option v-for="item in yearList" :label="item+'年'" :value="item"></el-option>
									</el-select>
									<el-select v-model="fillterMonth" placeholder="请选择月" size="small" class="fr">
										<el-option v-for="item in monthList" :label="item+'月'" :value="item"></el-option>
									</el-select>
								</dd>-->
								<dd>
									<div class="dateChoice">
											<el-date-picker type="date" placeholder="开始日期" v-model="beginDate"></el-date-picker> ~
											<el-date-picker type="date" placeholder="结束日期" v-model="endDate"></el-date-picker>
									</div>
								</dd>
							</dl>
							<div class="searchBtns">
								<el-button type="primary" @click="fillterdate">
									<span>确 定</span>
								</el-button>
								<el-button type="button" @click="resetSearch">
									<span>清 空</span>
								</el-button>
							</div>
						</right-card>							
					</div>
					<!--数据列表-->
					<el-table :data="attendanceData.list" stripe v-loading="isloading">
						<el-table-column prop="employeeName" label="姓名" width="110"></el-table-column>
						<el-table-column prop="employeeCode" align="center" label="职员代码" width="160"></el-table-column>
						<el-table-column prop="deptName" align="center" label="部门" width="180"></el-table-column>
						<el-table-column prop="jobName" align="center" label="职位" width="180"></el-table-column>
						<el-table-column prop="dateFrom" align="center" label="考勤开始日期" width="180"></el-table-column>
						<el-table-column prop="dateTo" align="center" label="考勤结束日期" width="180"></el-table-column>
						<el-table-column prop="days" align="center" label="实际出勤天数"></el-table-column>
						<el-table-column prop="hours" align="center" label="实际出勤小时"></el-table-column>
					</el-table>
					<div class="pagination" v-if="attendanceData.list && attendanceData.list.length>0">
							<el-pagination 
								layout="prev,pager,next" 
								:current-page="page"
								:page-size="pageSize" 
								@current-change="handleCurrentChange" 
								:total="attendanceData.totalCount"
							></el-pagination>
					</div>

				</div>
			</div>
			<!--考勤管理弹窗-->
			<div class="popup" :class="{block:popupShow}">
				<div class="popupMask" @click="popupShow=false"></div>
				<div class="popupCard">
					<div class="popupTitle mainColor">
						<i class="el-icon-setting"></i>考勤管理 
						<a href="javascript:;" class="el-icon-close fr color9" @click="popupShow=false"></a>
					</div>
					<div class="popupContent">
						<div class="menuTab clearfix">
							<div class="menuTabTitle">
								<a href="javascript:;" v-for="(item,index) in tabTitle" :class="{active:item.active}" @click="clickTabs(index)">{{item.name}}</a>
							</div>
							<div class="menuTabBox">
								<div class="tabBox" v-if="tabTitle[0].active">
									<page-top page-title="设备管理" page-description="在这里您可以更清晰的查看考勤机的使用情况">											 
									</page-top>
									<div class="equipmentMamage clearfix">
										<el-checkbox :indeterminate="isIndeterminate" v-model="checkAll" @change="handleCheckAllChange">全选</el-checkbox>
										<el-button type="primary" size="small" class="fr" @click="copyStep">同步</el-button>
									</div>										
									<div class="equipmentList clearfix">
									<el-checkbox-group v-model="checkedEquipment" @change="handleCheckedEquipment">
										<div class="equipmentItem" v-for="(item,index) in equipmentData.list" :class="{active:item.active}" >
											<el-checkbox :label="item" @change="checkItem(index)">&nbsp;</el-checkbox>
											<div class="itemAddr">														
												<div class="noAddr" v-if="item.state == 1">
													<p>暂未</p>
													<el-button type="text" @click="addNewPlace(item.id)">添加考勤点</el-button>
												</div>
												<div class="inner"  v-else>{{item.signPointName}}</div>
											</div>
											<div class="itemName">
												<span class="inner">
													<span :title="item.deviceName" :class="{exception:item.state == 3,nomal:item.state == 2 && item.syncState == 3,noStep:item.state == 2 && item.syncState != 3,noMember:item.state == 1}">{{item.deviceName}}</span>
												</span>
											</div>
											<div class="itemInfo">
												<a href="javascript:;" @click="clickViewMember(5,1,'',item)">查看成员</a>
												<p>共{{item.totalPeople}}人</p>
												<p>已同步{{item.totalPeople - item.unsyncPeople}}人/未同步{{item.unsyncPeople}}人</p>
											</div>
										</div>
										</el-checkbox-group>
									</div>									
								</div>
								<div class="tabBox" v-if="tabTitle[1].active">
									<page-top page-title="考勤点管理" page-description="在这里您可以考勤点进行管理"></page-top>
									<p style="text-align: center;font-size: 20px; padding-top: 50px;">此部分正在加急完成中，敬请期待。。。</p>
								</div>
								<div class="tabBox" v-if="tabTitle[2].active">
									<page-top page-title="考勤群组管理" page-description="在这里您可以进行考勤规则管理设置"></page-top>
									<p style="text-align: center;font-size: 20px; padding-top: 50px;">此部分正在加急完成中，敬请期待。。。</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!--查看成员弹窗-->
			<div class="viewMember" :class="{block:showViewMember}">
				<div class="viewMemberHd clearfix">
					<span>查看成员</span>
					<a href="javascript:;" class="el-icon-close color6 fr" @click="showViewMember=false"></a>
				</div>
				<div class="viewMemberAbout">
					查看<el-button type="text" @click="seeDepartmentPop">部门</el-button>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					查看<el-button type="text" class="groupBtn" @click="showGroup">群组</el-button>
				</div>
				<div class="viewMemberFillter clearfix">
					<span class="color6 hadMembers">已有{{viewMemberData.totalCount}}个成员</span>
					<search search-tips="搜索姓名或者职员代码..." @search="searchViewMember" class="fr"></search>
				</div>
				<div class="viewMemberBd">
					<el-table :data="viewMemberData.list" stripe v-loading="viewMemberLoading">
						<el-table-column prop="name" label="姓名"></el-table-column>
						<el-table-column prop="deptName" align="center" label="部门"></el-table-column>
						<el-table-column prop="jobName" align="center" label="职位"></el-table-column>
					</el-table>
					<div class="pagination" v-if="viewMemberData.list && viewMemberData.list.length>0">
						<el-pagination layout="prev,pager,next" :page-size="pageSize" @current-change="viewMemberCurrentChange" 
							:total="viewMemberData.totalCount"></el-pagination>
				</div>
				</div>
			</div>
			<!--查看成员弹窗-查看群组弹窗-->
			<div class="seeGroup groupPop radius" :class="{on:groupShow}">
				<i class="el-icon-close closeGroup" @click="closeGroup"></i>
				<p class="groupTitle">查看群组</p>
				<!--<div class="hadGroup">已有<span>6</span>个群组</div>-->
				<div class="groupWrap">
					<el-tooltip class="item" effect="light" :content="item.name" placement="bottom-start" v-for="item in groupData">
						<el-button type="text">{{item.name}}</el-button>
					</el-tooltip>
				</div>
			</div>
			<!--查看成员弹窗-查看部门弹窗-->
			<div class="seeDepartment groupPop radius" :class="{on:departmentShow}">
				<i class="el-icon-close closeGroup" @click="closeDepartment"></i>
				<p class="groupTitle">查看部门</p>
				<div class="groupWrap">
					<el-tooltip class="item" effect="light" :content="item.name" placement="bottom-start" v-for="item in groupData">
						<el-button type="text">{{item.name}}</el-button>
					</el-tooltip>
				</div>
			</div>
			<!--添加考勤点弹窗-->
			<el-dialog v-model="newPlacePop">
				<div class="newPlaceWrap">
					<div class="topHandle clearfix">
						<span class="fl">共<span>{{aboutPlace.length}}</span>个考勤点</span>
						<div class="rightSearch fr">
							<el-input placeholder="搜索..." v-model="newPlaceSearch" icon="search" autocomplete="off" class="searchInput"></el-input>
						</div>
					</div>
					<div class="newPoint">
						<el-button type="text" :disabled="true"><i class="el-icon-plus"></i> 新增考勤点</el-button>
					</div>	
					<div class="checkList">
						<el-radio-group v-model="placeRadio">
							<el-radio :label="placeItem.id" v-for="placeItem in aboutPlace">{{placeItem.name}}</el-radio>
						</el-radio-group>
					</div>
					<div slot="footer" class="dialog-footer sureBtns">
						<el-button type="primary" @click="checkedPlace">确定</el-button>
						<el-button @click="newPlacePop = false">取消</el-button>
					</div>
				</div>
			</el-dialog>
		</div>
		<script>new Vue({
	el: '#wrapInner',
	data: {
		showRight: false,
		popupShow: false,
		isloading: true,
		leftList: [{
			"id": 1,
			"text": "考勤记录",
			"link": "javascript:;"
		}],
		attendanceData: '',
		list: [],
		tabTitle: [{
			name: '设备管理',
			active: true
		}, {
			name: '考勤点管理',
			active: false
		}, {
			name: '考勤群组管理',
			active: false
		}],
		searchTxt: '',
		beginDate: '',
		endDate: '',
		equipmentData: '',
		groupData: "",
		placeRadio: "",
		newPlaceSearch: "",
		aboutPlace: "",
		keyword: "",
		pageSize: 5,
		page: 1,
		checkedEquipment: [],
		checkAll: false,
		isIndeterminate: false,
		viewMemberData: {},
		viewMemberLoading: false,
		showViewMember: false,
		newPlacePop: false,
		currentViewMember: {},
		ids: [],
		groupShow: false,
		departmentShow: false,
		isexception: false,
		isnoStep: false,
		isnoMember: false,
		httpUrl: {
			group: "${ctx}/api/signGroup/simpleList",
			department: "${ctx}/api/dept/simpleList",
			newPlace: "${ctx}/api/signPoint/simpleList",
			update: "${ctx}/api/device/update"
		},
		params: {
			"dateStart": "",
			"dateEnd": ""
		}
	},
	mounted: function() {
		this.getAttendance();
		this.getEquipment();
//		this.getYesterday();
//		this.getMonthFirst(); 
	},
	methods: {
		//获取昨天的日期
		//TODO 会影响到导出
//		getYesterday:function(){
//				var vm = this;
//				var today = new Date();
//				var oneday = 1000*60*60*24;
//				vm.endDate =  new Date(today - oneday);
//		},
//		//获取每月第一天
//		getMonthFirst:function(){
//			var vm = this;
//			var nowDate = new Date();
//			nowDate.setDate(1);
//			vm.beginDate = nowDate;
//		}, 
		//获取考勤列表
		getAttendance: function() {
			var vm = this;
			vm.isloading = true;
			var getAttendanceApi = "${ctx}/api/monthlySign/list?pageSize=" + vm.pageSize + "&page=" + vm.page + "&keyword=" + vm.searchTxt + "&" + Util.parseParam(vm.params)
			Vue.http.get(getAttendanceApi).then(function(response) {
				response.json().then(function(responseData) {
					vm.attendanceData = responseData.data;
					for (var i = 0; i < vm.attendanceData.list.length; i++) {
						vm.attendanceData.list[i].dateFrom = new Date(vm.attendanceData.list[i].dateFrom).format('yyyy-MM-dd')
						vm.attendanceData.list[i].dateTo = new Date(vm.attendanceData.list[i].dateTo).format('yyyy-MM-dd')
					}
					vm.isloading = false;
				})
			})
		},
		//顶部搜索
		searchAttendance: function(txt) {
			var vm = this;
			vm.searchTxt = txt;
			vm.page = 1;
			vm.getAttendance()
		},
		showRightCard: function() {
			this.showRight = false;
		},
		clickTabs: function(num) {
			for (var i = 0; i < this.tabTitle.length; i++) {
				this.tabTitle[i].active = false;
			}
			this.tabTitle[num].active = true;
		},
		//打开右侧高级筛选
		showRightSearch: function(e) {
			e.stopPropagation();
			var vm = this;
			vm.showRight = true;
			$(".rightCard").on("click", function(e) {
				e.stopPropagation();
			})
			$("body").click(function() {
				vm.showRightCard();
			})
		},
		//转时间戳
		dateToTimestamp: function(date) {
			if (date) {
				return date.getTime();
			} else {
				return null;
			}
		},
		//右侧高级筛选		
		fillterdate: function() {
			var vm = this;
			vm.params.dateStart = vm.dateToTimestamp(vm.beginDate);

			vm.params.dateEnd = vm.dateToTimestamp(vm.endDate) + 24*60*60*1000 -1;

			vm.showRightCard();
			vm.getAttendance();
		},
		//导出列表
		getExport: function() {
			var vm = this;
			window.location.href = "${ctx}/api/monthlySign/export?keyword=" + vm.searchTxt + "&dateStart=" + vm.params.dateStart + "&dateEnd=" + vm.params.dateEnd;
		},
		//导出详情
		getDetailExport: function() {
			var vm = this;
			window.location.href = "${ctx}/api/sign/export?keyword=" + vm.searchTxt + "&dateStart=" + vm.params.dateStart + "&dateEnd=" + vm.params.dateEnd;
		},
		//去除筛选条件
		resetSearch: function() {
			var vm = this;
			vm.params.dateStart = "",
				vm.params.dateEnd = "",
				vm.beginDate = "";
			vm.endDate = "";
			vm.showRightCard();
			vm.getAttendance();
		},
		//分页方法
		handleCurrentChange: function(val) {
			var vm = this;
			vm.page = val;
			vm.getAttendance()
		},
		//获取设备列表
		getEquipment: function() {
			var vm = this;
			vm.viewMemberLoading = true;
			var getEquipmentApi = "${ctx}/api/device/list"
			Vue.http.get(getEquipmentApi).then(function(response) {
				response.json().then(function(responseData) {
					vm.equipmentData = responseData.data;
					for (var i = 0; i < vm.equipmentData.list.length; i++) {
						vm.$set(vm.equipmentData.list[i], 'active', false);
					}
					vm.viewMemberLoading = false;
				})
			})
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
		handleCheckedEquipment(value) {
			var checkedCount = value.length;
			this.checkAll = checkedCount === this.equipmentData.list.length;
			this.isIndeterminate = checkedCount > 0 && checkedCount < this.equipmentData.list.length;
		},
		checkItem: function(num) {
			this.equipmentData.list[num].active = true;
		},
		//数据同步
		copyStep: function() {
			var vm = this;
			var transfor = [];
			for (var i = 0; i < vm.checkedEquipment.length; i++) {
				transfor.push(vm.checkedEquipment[i].id)
			}
			var test = transfor.join(',');
			Vue.http.post("${ctx}/api/device/sync", {
				ids: test
			}, {
				emulateJSON: true
			}).then(function(response) {
				response.json().then(function(responseData) {
					vm.$message("同步成功！");
				})
			})
		},
		//获取查看成员列表
		clickViewMember: function(pageSize, page, keyword, item) {
			var vm = this;
			vm.currentViewMember = item;
			vm.showViewMember = true;
			var getViewMemberAip = "${ctx}/api/employee/queryEmployeeList?pageSize=" + pageSize + "&page=" + page + "&keyword=" + keyword + "&sn=" + item.sn
			Vue.http.get(getViewMemberAip).then(function(response) {
				response.json().then(function(responseData) {
					vm.viewMemberData = responseData.data;
				})
			})
		},
		viewMemberCurrentChange: function(val) {
			this.clickViewMember(this.viewMemberData.pageSize, val, '', this.currentViewMember)
		},
		//查看成员列表模糊搜索
		searchViewMember: function(txt) {
			this.clickViewMember(this.viewMemberData.pageSize, 1, txt, this.currentViewMember)
		},
		//查看群组
		showGroup: function() {
			var vm = this;
			vm.groupShow = true;
			Vue.http.get(vm.httpUrl.group + "?sn=" + vm.currentViewMember.sn).then(function(response) {
				response.json().then(function(responseData) {
					vm.groupData = responseData.data;
				})
			})
		},
		//关闭群组弹窗
		closeGroup: function() {
			var vm = this;
			vm.groupShow = false
		},
		//查看部门弹窗
		seeDepartmentPop: function() {
			var vm = this;
			vm.departmentShow = true;
			Vue.http.get(vm.httpUrl.department + "?sn=" + vm.currentViewMember.sn).then(function(response) {
				response.json().then(function(responseData) {
					vm.groupData = responseData.data;
				})
			})
		},
		//关闭部门弹窗
		closeDepartment: function() {
			this.departmentShow = false
		},
		//添加考勤点
		addNewPlace: function(id) {
			var vm = this;
			vm.newPlacePop = true;
			Vue.http.get(vm.httpUrl.newPlace + "?id=" + id + "&" + "&keyword=" + vm.newPlaceSearch).then(function(response) {
				response.json().then(function(responseData) {
					vm.aboutPlace = responseData.data
				})
			})
		},
		//确定添加的考勤点
		checkedPlace: function() {
			var vm = this;
			//TODO 功能未完成
			//						Vue.http.get(vm.httpUrl.update + "?id="+ id + "&" + "&keyword=" + vm.newPlaceSearch).then(function(response){
			//							response.json().then(function(responseData){
			//								vm.aboutPlace = responseData.data
			//							})
			//						})						
			vm.newPlacePop = false;
		}
	}
})</script>		
	</body>
</html>

