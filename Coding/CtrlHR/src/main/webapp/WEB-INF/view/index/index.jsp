<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ include file="/WEB-INF/view/include/taglibs.jspf" %>
<!DOCTYPE html>
<html class="no-js">
<head>
  <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>CtrlHR</title>
    <meta name="description" content="">  
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Place favicon.ico and apple-touch-icon.png in the root directory -->
    <%@ include file="/WEB-INF/view/include/head_include.jspf" %>
</head>
<body>
    <!--[if lt IE 7]>
        <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
    <![endif]-->
    <div class="wrap">
        <div class="head">
            <div class="name">CtrlHR</div>
            <div class="menu iconfont icon-lisimoshicaidandaohang"></div>
            <div class="nav">
                <a href="#" onclick="test();">组织</a>
                <a href="#">员工</a>
                <a href="#">考勤</a>
            </div>
            <a href="" class="manageNav">
                <i class="iconfont icon-shezhi icon1"></i>
                <span>管理中心</span>
                <i class="iconfont icon-xiala icon2"></i>
            </a>
            <div class="loginUser">
                <img src="${ctx}/images/people.jpg" class="loginHead">
                <span class="userName">test</span>
                <i class="iconfont icon-xiala icon2"></i>
            </div>
        </div>
    </div>
</body>
</html>