<%@ page session="false" pageEncoding="utf-8" %>
<% request.setAttribute("ctx", request.getContextPath());%><!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Your access is limited (403)</title>
    <link href="${ctx}/css/static.css" media="screen" rel="stylesheet" type="text/css"/>
</head>

<body>
<h1>暂不可访问</h1>

<h3>您的账号还未审核，请联系管理员</h3>
<hr/>
<p>您可能没有访问此操作的权限， <a href="${ctx}/">点击这里</a> 回到首页.</p>
</body>
</html>

