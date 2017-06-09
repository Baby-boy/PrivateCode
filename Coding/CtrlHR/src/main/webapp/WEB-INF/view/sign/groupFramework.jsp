<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ include file="/WEB-INF/view/include/taglibs.jspf"%>
<!DOCTYPE html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">

<title>用户选择</title>
<meta name="keywords" content="">
<meta name="description" content="">
<link href="${ctx}/css/common.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/css/jquery.mCustomScrollbar.css" />
<link rel="stylesheet" type="text/css"
	href="${ctx}/js/plugin/zTree/css/zTreeStyle/zTreeStyle.css" />
<style type="text/css">
.group-container {
	width: 760px;
	margin: 0 auto;
	font-size: 12px;
	position: relative;
}

.group-container .group-title {
	color: #999;
	line-height: 40px;
}

.group-container .group-search {
	position: relative;
	height: 30px;
	background-color: #f5f5f5;
}

.group-container .group-search .group-search-input-box {
	position: absolute;
	top: 0;
	right: 30px;
	left: 10px;
	bottom: 0;
}

.group-container .group-search .group-search-input {
	width: 100%;
	height: 30px;
	line-height: 30px;
	border: 0;
	color: #333;
	outline: none;
	background-color: transparent;
}

.group-container .group-search .group-search-ico {
	position: absolute;
	top: 0;
	right: 0;
	padding: 0;
	width: 30px;
	height: 30px;
	border: 0;
	background: url(../../images/icon-serch.png) center no-repeat;
	outline: none;
	cursor: pointer;
}

.group-container .group-select-box {
	float: left;
	width: 430px;
}

.group-container .group-selected-box {
	float: right;
	width: 288px;
}

.group-container .group-content-box {
	border: 1px solid #e5e5e5;
	border-radius: 3px;
	padding: 10px 20px;
	height: 440px;
	position: relative;
}

.group-container .group-tree {
	padding: 0;
	border-right: solid 1px #e5e5e5;
	position: absolute;
	top: 90px;
	left: 20px;
	right: 50%;
	bottom: 10px;
}

.group-container .group-select-user, .group-container .group-selected-user
	{
	padding: 0;
	position: absolute;
	top: 90px;
	left: 54%;
	right: 20px;
	bottom: 10px;
}

.group-container .group-selected-user {
	left: 20px;
}

.group-container .group-select-all {
	color: #4891E7;
	display: block;
	line-height: 30px;
}

.group-container .group-tab-box {
	margin: 8px 0;
	border-bottom: 1px solid #e5e5e5;
}

.group-container .group-tab-box a {
	display: inline-block;
	line-height: 24px;
	position: relative;
	border-top: 2px solid #fff;
	border-right: 1px solid #fff;
	border-left: 1px solid #fff;
	background-color: #fff;
	border-bottom: 1px solid white;
	color: #4891E7;
}

.group-container .group-tab-box .group-tab {
	border-top-color: #4891E7;
	border-right-color: #ccc;
	border-left-color: #ccc;
	bottom: -1px;
	padding: 0 12px;
	color: #333;
}

.group-container .group-select-user-box {
	margin: 0;
	padding: 0;
	line-height: 44px;
	position: absolute;
	top: 30px;
	left: 0;
	right: 0;
	bottom: 0;
}

/*.group-container .group-select-user-empty {
             }*/

/*.group-container .group-select-user-empty .mCustomScrollBox {
                 display: none;
             }*/
.group-container .group-select-user-empty .mCustomScrollBox:after {
	content: "暂无数据";
	color: #ccc;
	line-height: 20px;
	height: 20px;
	text-align: center;
	position: absolute;
	font-size: 14px;
	top: 50%;
	margin-top: -10px;
	left: 0;
	right: 0;
	letter-spacing: 4px;
}

.group-container .group-selected-user .group-select-user-box {
	top: 0;
}

.group-container .group-select-user-box .group-user-item {
	position: relative;
	display: none;
}

.group-container .group-select-user-box .group-user-item.group-user-filter
	{
	display: block;
	width: 95%;
}

.group-container .group-select-user-box .group-user-item.group-user-selected
	{
	display: none;
}

.group-container .group-select-user-box .group-user-item .group-user-operation
	{
	display: none;
	float: right;
	margin-right: 5px;
}

.group-container .group-select-user-box .group-user-item.group-user-active,
	.group-container .group-select-user-box .group-user-item:hover {
	background-color: #f2f8fb;
	cursor: pointer;
	color: #4891E7;
}

.group-container .group-select-user-box .group-user-item:hover .group-user-operation
	{
	display: block;
	color: #4891E7;
	position: absolute;
	top: 0;
	right: 10px;
	bottom: 0;
}

.group-container .group-select-user-box .group-user-item img {
	border-radius: 50%;
	width: 40px;
	height: 40px;
	margin: 2px 10px;
}

.group-container .group-arrow-box {
	position: absolute;
	left: 430px;
	right: 288px;
	top: 0;
	bottom: 0;
}

.group-container .group-arrow-box .group-arrow-left, .group-container .group-arrow-box .group-arrow-right
	{
	position: absolute;
	width: 16px;
	height: 25px;
	left: 13px;
	cursor: pointer;
	top: 50%;
	opacity: 0.3;
	background-position: center;
	background-repeat: no-repeat;
}

.group-container .group-arrow-box .group-arrow-left {
	background-image: url(../../images/icon-arrow-left-gray06.png);
	margin-top: 60px;
}

.group-container .group-arrow-box .group-arrow-right {
	background-image: url(../../images/icon-arrow-right-gray06.png);
	margin-top: -60px;
}

.group-container .group-arrow-box .group-arrow-left.group-arrow-active {
	background-image: url(../../images/icon-arrow-left-blue06.png);
	opacity: 1;
}

.group-container .group-arrow-box .group-arrow-right.group-arrow-active
	{
	background-image: url(../../images/icon-arrow-right-blue06.png);
	opacity: 1;
}

/*按钮通用样式 今后放入common*/
.pop-btn-box {
	font-size: 0;
	padding: 20px 0;
	text-align: center;
}

.pop-btn {
	border: 0;
	display: inline-block;
	padding: 0 34px;
	height: 32px;
	text-align: center;
	line-height: 32px;
	text-decoration: none;
	letter-spacing: 5px;
	font-size: 14px;
	border-radius: 2px;
	border-width: 1px;
	border-style: solid;
	cursor: pointer;
	outline: none;
	margin: 0 5px;
}

.pop-btn.pop-btn-blue {
	color: #FFF;
	background-color: #4891E7;
	border-color: #4891E7;
}

.pop-btn.pop-btn-blue:hover {
	opacity: 0.8;
}

.pop-btn.pop-btn-white {
	color: #333;
	background-color: #fff;
	border: 1px solid #4891E7;
}

.pop-btn.pop-btn-gray {
	color: #333;
	background-color: #CCC;
	border: 1px solid #CCC;
}

.pop-btn.pop-btn-disabled {
	color: #333;
	background-color: #CCC;
	border: 1px solid #CCC;
	cursor: default;
}

.pop-btn.pop-btn-disabled:hover {
	opacity: 1;
}

.pop-btn.pop-btn-gray:hover, .pop-btn.pop-btn-white:hover {
	color: #FFF;
	background-color: #4891E7;
	border-color: #4891E7;
}
</style>
</head>

<body>
	<div class="group-container clearfix">
		<div class="group-select-box">
			<div class="group-title">选择人员</div>
			<div class="group-content-box clearfix">
				<div class="group-search">
					<div class="group-search-input-box">
						<input class="group-search-input" id="txtSelectUser" type="text"
							placeholder="搜索人员" />
					</div>
					<a class="group-search-ico"></a>
				</div>
				<div class="group-tab-box">
					<a class="group-tab" href="javascript:;">组织架构</a>
				</div>
				<div class="group-tree ztree" id="groupTree"></div>
				<div class="group-select-user">
					<a id="allUser" href="javascript:;" class="group-select-all">全选</a>
					<div id="selectUserList"
						class="group-select-user-box group-select-user-empty"></div>
				</div>
			</div>
		</div>
		<div class="group-arrow-box">
			<div class="group-arrow-left"></div>
			<div class="group-arrow-right"></div>
		</div>
		<div class="group-selected-box">
			<div class="group-title">已选择的人员</div>
			<div class="group-content-box">
				<div class="group-search">
					<div class="group-search-input-box">
						<input id="txtSelectedUser" class="group-search-input" type="text"
							placeholder="搜索人员" />
					</div>
					<a class="group-search-ico"></a>
				</div>
				<div class="group-tab-box">
					<a id="allSelectUser" href="javascript:;" class="group-select-all">全选</a>
				</div>
				<div class="group-selected-user">
					<div id="selectedUserList"
						class="group-select-user-box group-select-user-empty"></div>

				</div>
			</div>
		</div>
	</div>
	<div class="pop-btn-box">
		<input id="btnOK" class="pop-btn pop-btn-blue" type="button"
			value="保存" /> <input id="btnCancel" class="pop-btn pop-btn-gray"
			type="button" value="取消" />
	</div>
	<script type="text/html" id="selectUserTemplate">
        {{ for(var i=0;i < data.length;i++){ }}
        <div data-id="{{=data[i]['id'] }}" data-letters="{{=data[i]['letters'] }}" class="group-user-item" data-deptid="{{=data[i]['deptId'] }}">
            <img src="{{=baseImgPath}}{{=data[i]['avatar'] }}" />
            <span>{{=data[i]['realname'] }}</span>
            <a href="javascript:;" class="group-user-operation">添加</a>
        </div>
        {{ } }}
    </script>
	<script type="text/html" id="selectedUserTemplate">
        {{ for(var i=0;i < data.length;i++){ }}
        <div data-id="{{=data[i]['id'] }}" data-letters="{{=data[i]['letters'] }}" class="group-user-filter group-user-item" data-deptid="{{=data[i]['deptId'] }}">
            <img src="{{=baseImgPath}}{{=data[i]['avatar'] }}" />
            <span>{{=data[i]['realname'] }}</span>
            <a href="javascript:;" class="group-user-operation">移除</a>
        </div>
        {{ } }}
    </script>

	<script src="${ctx}/js/plugin/jquery-1.12.4.js"></script>
	<script src="${ctx}/js/jquery.glanway.util.js"></script>
	<script src="${ctx}/js/jquery.glanway.baseEffect.js"></script>
	<script type="text/javascript"
		src="${ctx}/js/plugin/jquery.mCustomScrollbar.concat.min.js"></script>
	<script type="text/javascript"
		src="${ctx}/js/plugin/zTree/js/jquery.ztree.all-3.5.min.js"></script>
	<script src="${ctx}/js/baiduTemplate.js"></script>
	<script src="${ctx}/js/jquery.glanway.groupFramework.js"></script>
	<script type="text/javascript">
		$(function() {
			var allUserData = {
				"data" : [ {
					id : 1,
					realname : "王杰",
					avatar : "img-man.png",
					letters : "W",
					deptId : 11
				}, {
					id : 2,
					realname : "陈敏",
					avatar : "img-woman.png",
					letters : "C",
					deptId : 12
				}, {
					id : 3,
					realname : "杨常贺",
					avatar : "img-man.png",
					letters : "Y",
					deptId : 13
				}, {
					id : 4,
					realname : "张明",
					avatar : "img-man.png",
					letters : "Z",
					deptId : 14
				}, {
					id : 5,
					realname : "夏威",
					avatar : "img-man.png",
					letters : "X",
					deptId : 21
				}, {
					id : 6,
					realname : "林立",
					avatar : "img-man.png",
					letters : "L",
					deptId : 11
				}, {
					id : 7,
					realname : "陈彦希",
					avatar : "img-woman.png",
					letters : "C",
					deptId : 12
				}, {
					id : 8,
					realname : "李春妹",
					avatar : "img-man.png",
					letters : "L",
					deptId : 13
				}, {
					id : 9,
					realname : "瞿胜",
					avatar : "img-man.png",
					letters : "J,Q",
					deptId : 14
				}, {
					id : 10,
					realname : "张显",
					avatar : "img-man.png",
					letters : "Z",
					deptId : 21
				}, {
					id : 11,
					realname : "施冬冬",
					avatar : "img-man.png",
					letters : "S",
					deptId : 11
				}, {
					id : 12,
					realname : "齐忠宝",
					avatar : "img-woman.png",
					letters : "Q",
					deptId : 31
				}, {
					id : 13,
					realname : "李九祥",
					avatar : "img-man.png",
					letters : "L",
					deptId : 13
				}, {
					id : 14,
					realname : "陈胜",
					avatar : "img-man.png",
					letters : "C",
					deptId : 14
				}, {
					id : 15,
					realname : "吴杨",
					avatar : "img-man.png",
					letters : "W",
					deptId : 11
				} ]
			};
			var groupData = [ {
				id : 1,
				parentId : 0,
				name : "上海总部"
			}, {
				id : 11,
				parentId : 1,
				name : "项目中心"
			}, {
				id : 12,
				parentId : 1,
				name : "互联网业务群"
			}, {
				id : 13,
				parentId : 1,
				name : "软件业务群"
			}, {
				id : 14,
				parentId : 1,
				name : "人事行政中心"
			}, {
				id : 15,
				parentId : 1,
				name : "销售中心"
			}, {
				id : 16,
				parentId : 1,
				name : "业务部门"
			}, {
				id : 17,
				parentId : 1,
				name : "实施部门"
			}, {
				id : 18,
				parentId : 1,
				name : "董事会"
			}, {
				id : 2,
				parentId : 0,
				name : "苏州交付中心"
			}, {
				id : 21,
				parentId : 2,
				name : "人事行政部门"
			}, {
				id : 22,
				parentId : 2,
				name : "Java组"
			}, {
				id : 23,
				parentId : 2,
				name : ".NET组"
			}, {
				id : 24,
				parentId : 2,
				name : "Web前端组"
			}, {
				id : 3,
				parentId : 0,
				name : "西安交付中心"
			}, {
				id : 31,
				parentId : 3,
				name : "人事行政部门"
			}, {
				id : 32,
				parentId : 3,
				name : "Java组"
			}, {
				id : 33,
				parentId : 3,
				name : ".NET组"
			}, {
				id : 34,
				parentId : 3,
				name : "Web前端组"
			} ];

			var groupFramework = new GroupFramework({
				baseImgPath : "../../images/",
				user : allUserData,
				group : groupData,
				isRequired : true,
				selectedCallback : function(data) {
					window.parent.selectedUser
							&& window.parent.selectedUser(data);
				},
				cancelCallback : function() {
					window.parent.cancel && window.parent.cancel();
				}
			});
		});
	</script>
</body>
</html>
