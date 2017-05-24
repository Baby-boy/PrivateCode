<%@ page session="false" pageEncoding="utf-8" %>
<% request.setAttribute("ctx", request.getContextPath());%><!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>The page you visit does not exist (404)</title>
    <link href="${ctx}/css/static.css" media="screen" rel="stylesheet" type="text/css" />
</head>
<body>
    <h1>404</h1>
    <h3>你所访问的页面不存在</h3>
    <hr/>
    <p>你可能输入了不存在的URL地址，或者该资源已经被删除， <a href="${ctx}/">点击这里</a> 回到首页.</p>
</body>
</html>
