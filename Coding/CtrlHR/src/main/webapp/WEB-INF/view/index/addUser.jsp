<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/view/include/taglibs.jspf" %>
<!DOCTYPE html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js">
<!--<![endif]-->
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>添加员工弹窗</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <%@ include file="/WEB-INF/view/include/head_include.jspf" %>
</head>
	<body>
		<div class="wrap">
			<div class="addUserPop">
				<div class="addUserPopInner">
					<p class="newTitle">新建员工</p>
					<p class="topMsg">在这里您可以对个人信息进行设置<span class="requiredNotice">*为必填</span></p>
					<div class="addWrap clearfix">
						<div class="leftBtns fl">
							<div class="leftTabs">
								<div class="leftTabInner">
									<a href="#" class="btnItem">个人信息</a>
									<a href="#" class="btnItem">履历信息</a>
									<a href="#" class="btnItem">就职信息</a>
									<a href="#" class="btnItem">个人信息</a>
									<a href="#" class="btnItem">履历信息</a>
									<a href="#" class="btnItem">就职信息</a>
									<a href="#" class="btnItem">个人信息</a>
									<a href="#" class="btnItem">履历信息</a>
									<a href="#" class="btnItem">就职信息</a>
								</div>
							</div>
						</div>
						<div class="rightTable fl"></div>
					</div>
					<i class="closePop iconfont icon-close"></i>
				</div>
			</div>
		</div>
	</body>
</html>
