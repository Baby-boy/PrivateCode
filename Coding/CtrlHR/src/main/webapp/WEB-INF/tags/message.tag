<%@ tag pageEncoding="UTF-8" description="显示操作成功/失败的消息，内容为:message/error" %>
<%@ attribute name="message" type="java.lang.String" required="false" description="成功消息" %>
<%@ attribute name="error" type="java.lang.String" required="false" description="失败消息" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
<c:if test="${not empty message}">
    <div class="alert alert-success">
        <button type="button" class="close" content-dismiss="alert">&times;</button>
        <span class="icon-ok-sign icon-large"></span>&nbsp;${message}
    </div>
</c:if>
<c:if test="${not empty error}">
    <div class="alert alert-error">
        <button type="button" class="close" content-dismiss="alert">&times;</button>
        <span class="icon-remove-sign icon-large"></span>&nbsp;${error}
    </div>
</c:if>
--%>
<script type="text/javascript" >
    if ("${error}") {
        $.messager.show({
            title: 'Error',
            msg: '${error}'
        });
    } else if ('${message}') {
        $.messager.show({
            title: '提示',
            msg: '${message}'
        });
    }
</script>
