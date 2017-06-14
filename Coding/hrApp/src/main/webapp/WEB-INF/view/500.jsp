<%@ page session="false" isErrorPage="true" pageEncoding="utf-8" %>
<% request.setAttribute("ctx", request.getContextPath()); %><!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Server error came into being (500)</title>
    <link href="${ctx}/css/static.css" media="screen" rel="stylesheet" type="text/css" />
</head>
<body>
<h1>500</h1>
<h3>您访问的页面出现错误！</h3>
<hr/>
<p>我们已经将此错误信息记录下来，并将尽快处理，为此造成您的不便请多见谅</p>
<p><a href="${ctx}/">点击这里</a> 返回首页.</p>
<%
    if (null != exception) {
        java.io.StringWriter buffer = new java.io.StringWriter();
        exception.printStackTrace(new java.io.PrintWriter(buffer));

        buffer.flush();
        pageContext.setAttribute("stackTraces", buffer.toString());
        pageContext.setAttribute("uri", pageContext.getErrorData().getRequestURI());
        pageContext.setAttribute("code", pageContext.getErrorData().getStatusCode());
    }
    //if (null != request.getParameter("debug")) {
%>
<!--
错误页面: ${uri}
错误代码: ${code}
错误堆栈信息:
<code>
<pre>
${stackTraces}
</pre>
</code>
-->
</body>
</html>