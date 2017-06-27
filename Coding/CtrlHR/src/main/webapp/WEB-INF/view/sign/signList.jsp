<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/view/include/taglibs.jspf"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>考勤记录</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
<link rel="stylesheet" type="text/css" href="${ctx}/css/jquery.mCustomScrollbar.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/js/plugin/zTree/css/zTreeStyle/zTreeStyle.css" />
<%@ include file="/WEB-INF/view/include/head_include.jspf"%>
<link rel="stylesheet" type="text/css" href="${ctx}/css/attendance.css" />
</head>
<body>
	<%@ include file="/WEB-INF/view/components/left_nav.jspf"%>
	<%@ include file="/WEB-INF/view/components/page_top.jspf"%>
	<%@ include file="/WEB-INF/view/components/search.jspf"%>
	<%@ include file="/WEB-INF/view/components/rightCard.jspf"%>
	<%@ include file="/WEB-INF/view/components/header.jspf"%>
	<%@ include file="/WEB-INF/view/components/tree.jspf"%>
	<%@ include file="/WEB-INF/view/sign/signManager/signManager.jspf"%>
	<div class="wrapInner" id="wrapInner" v-cloak>
		<div class="wrap">
			<header-nav></header-nav>
			<div class="contentWrap">
				<left-nav :left-list="leftList" :current="0"></left-nav>
				<!--顶部筛选-->
				<page-top page-title="考勤记录" page-description="在这里您可以对公司进行考勤信息进行设置">
				<div class="pageTopButton fr">
					<el-button type="text" icon="setting" class="textBtn" size="mini"
						@click="popupShow=true">考勤管理</el-button>
				</div>
				<div class="pageTopSearch fr">
					<search search-tips="搜索姓名或者职员代码..." @search="searchAttendance"></search>
				</div>
				</page-top>
				<div class="pageOperate clearfix">
					<el-button type="primary" size="small" @click="getExport">
					<i class="iconfont icon-export"></i>&nbsp;导出</el-button>
					<el-button type="primary" size="small" @click="getDetailExport">
					<i class="iconfont icon-export"></i>&nbsp;导出考勤详情</el-button>
					<a href="javascript:;" @click="showRightSearch"
						class="fr btnFillter"><i class="iconfont icon-sort"></i>&nbsp;高级筛选</a>
					<!--右侧高级筛选-->
					<right-card card-title="筛选器" v-model="showRight" ref="rightCard">
					<dl class="dateFillter">
						<dt>
							<i class="el-icon-date"></i> 时间筛选
						</dt>
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
								<el-date-picker type="date" placeholder="开始日期"
									v-model="beginDate"></el-date-picker>
								~
								<el-date-picker type="date" placeholder="结束日期" v-model="endDate"></el-date-picker>
							</div>
						</dd>
					</dl>
					<div class="searchBtns">
						<el-button type="primary" @click="fillterdate" :loading="searchLoading"> <span>确
							定</span> </el-button>
						<el-button type="button" @click="resetSearch"> <span>清
							空</span> </el-button>
					</div>
					</right-card>
				</div>
				<!--数据列表-->
				<el-table :data="attendanceData.list" stripe v-loading="isloading">
				<el-table-column prop="employeeName" label="姓名" width="110"></el-table-column>
				<el-table-column prop="employeeCode" align="center" label="职员代码"
					width="160"></el-table-column> <el-table-column prop="deptName"
					align="center" label="部门" width="180"></el-table-column> <el-table-column
					prop="jobName" align="center" label="职位" width="180"></el-table-column>
				<el-table-column prop="dateFrom" align="center" label="考勤开始日期"
					width="180"></el-table-column> <el-table-column prop="dateTo"
					align="center" label="考勤结束日期" width="180"></el-table-column> <el-table-column
					prop="days" align="center" label="实际出勤天数"></el-table-column> <el-table-column
					prop="hours" align="center" label="实际出勤小时"></el-table-column> </el-table>
				<div class="pagination"
					v-if="attendanceData.list && attendanceData.list.length>0">
					<el-pagination layout="total,sizes,prev,pager,next,jumper" 
						:current-page="page" @current-change="handleCurrentChange"
						:page-size="pageSize" 
						:page-sizes="[10,20,30]" @size-change="sizeChange"
						:total="attendanceData.totalCount">
					</el-pagination>
				</div>
			</div>
		</div>
		<manager v-model="popupShow" v-if="popupShow"></manager>
	</div>
	<script>
	new Vue({
	el: '#wrapInner',
	data: {
		showRight: false,
		popupShow: false,
		isloading: true,
		searchLoading:false,
		leftList: [{
			"id": 1,
			"text": "考勤记录",
			"link": "javascript:;"
		}],
		attendanceData: '',
		searchTxt: '',
		beginDate: '',
		endDate: '',
		equipmentData: '',
		pageSize:10,
		page: 1,
		params: {
			"dateStart": "",
			"dateEnd": ""
		},
	},
	mounted: function() {
		this.currentVisualPage = "attendance";
		this.getAttendance();
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
				Util.processRes(response,function(responseData) {
					vm.searchLoading=false;
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
			vm.getAttendance();
		},
		//打开右侧高级筛选
		showRightSearch: function(e) {
			e.stopPropagation();
			var vm = this;
			vm.showRight = true;
			$(".rightCard").on("click", function(e) {
				e.stopPropagation();
			})
			$("body").click(function(e) {
				vm.showRight = false;
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
			this.showRight = false;
			this.searchLoading=true;
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
		    vm.showRight = false;
		    vm.getAttendance();
		},
		//分页方法
		handleCurrentChange: function(val) {
			this.page = val;
			this.getAttendance();
		},
		sizeChange:function(val){
			this.pageSize = val;
			this.getAttendance();
		}
	}
})
</script>
</body>
</html>

