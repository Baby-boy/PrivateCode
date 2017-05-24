<%-- trimDirectiveWhitespaces 是 JSP 2.1 以后的,tomcat 6 之前有问题 --%>
<%@ tag isELIgnored="false" pageEncoding="UTF-8" body-content="empty" description="分页" trimDirectiveWhitespaces="true" %>
<%@ attribute name="pageParam" type="java.lang.String" required="false" description="当前页码参数名称" %>
<%@ attribute name="totalPages" type="java.lang.Integer" required="true" description="总页码" %>
<%@ attribute name="sliderSize" type="java.lang.Integer" required="false" description="分页滑块宽度" %>
<%@ attribute name="skip" type="java.lang.Boolean" required="false" description="是否显示跳转输入" %>
<%@ attribute name="escape" type="java.lang.Boolean" required="false" description="是否转义XML特殊字符" %>
<%--<%@ attribute name="preText" type="java.lang.String" required="false" description="上一页文本" %>--%>
<%--<%@ attribute name="nextText" type="java.lang.String" required="false" description="下一页文本" %>--%>
<%--<%@ attribute name="goText" type="java.lang.String" required="false" description="跳转页码文本" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:set var="pageParam" value="${not empty pageParam ? pageParam : 'page'}"/>  <%-- 页码参数 --%>
<c:set var="sliderSize" value="${null != sliderSize ? sliderSize : 3 }"/>     <%-- 滑块大小 --%>
<c:set var="preText" value="${not empty preText ? preText : '&lt;上一页'}" />      <%-- 上一页文本 --%>
<c:set var="nextText" value="${not empty nextText ? nextText : '下一页&gt;'}" />   <%-- 上一页文本 --%>
<c:set var="goText" value="${not empty goText ? goText : '跳转'}" />       <%-- 跳转页码文本 --%>
<%-- query string --%>
<c:forEach var="pair" items="${paramValues}" varStatus="st">
    <c:if test="${pair.key != pageParam}">
        <c:forEach var="v" items="${pair.value}">
            <c:choose>
                <%-- 设置为false 才不转义 --%>
                <c:when test="${escape == false}">
                    <c:set var="search" value="${search}&${pair.key}=${v}"/>
                </c:when>
                <c:otherwise>
                    <c:set var="search" value="${search}&${fn:escapeXml(pair.key)}=${fn:escapeXml(v)}"/>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </c:if>
</c:forEach>
<%--<c:set var="search" value="${fn:substringBefore(search, '&')}"/>--%>
<c:set var="currentPage" value="${not empty param[pageParam] ? param[pageParam] : '1'}"/>
<c:catch var="ex">
    <c:set var="currentPage" value="${currentPage + 0}" />
</c:catch>
<c:set var="currentPage" value="${null != ex ? '1' : currentPage}" />
<%--<c:set var="currentPage" value="${currentPage > totalPages ? 1 : currentPage}" />--%>
<c:set var="sliderSize" value="${sliderSize > totalPages ? totalPages : sliderSize}"/>
<c:set var="begin" value="${currentPage - (sliderSize - 1) / 2}"/>
<c:set var="begin" value="${begin < 1 ? 1 : begin}"/>
<c:set var="begin" value="${begin + sliderSize - 1 > totalPages ? totalPages + 1 - sliderSize : begin}"/>
<ul class="pagination">
    <%-- 上一页 --%>
    <c:choose>
        <c:when test="${currentPage > 1}">
            <li><a href="?${pageParam}=${currentPage - 1}${search}">${preText}</a></li>
            <!--&laquo;-->
        </c:when>
        <c:otherwise>
            <li class="disabled"><span>${preText}</span></li>
        </c:otherwise>
    </c:choose>
    <c:if test="${0 eq sliderSize}">
        <li class="disabled"><span>1</span></li>
    </c:if>
    <%-- 页码 --%>
    <c:forEach var="p" varStatus="st" begin="${begin}" end="${begin + sliderSize - 1}">
        <%-- 起始大于1 --%>
        <c:if test="${st.first and p > 1}">
            <li><a href="?${pageParam}=1${search}">1</a></li>
            <c:if test="${p > 2}">
                <li class="more"><span>...</span></li>
            </c:if>
        </c:if>
        <c:choose>
            <%-- 当前页  --%>
            <c:when test="${p == currentPage}">
                <li class="active"><span>${p}</span></li>
            </c:when>
            <c:otherwise>
                <li><a href="?${pageParam}=${p}${search}">${p}</a></li>
            </c:otherwise>
        </c:choose>
        <c:if test="${st.last and p < totalPages}">
            <c:if test="${p < totalPages - 1}">
                <li class="more"><span>...</span></li>
            </c:if>
            <li><a href="?${pageParam}=${totalPages}${search}">${totalPages}</a></li>
        </c:if>
    </c:forEach>
    <%-- 下一页 --%>
    <c:choose>
        <c:when test="${currentPage < totalPages}">
            <li><a href="?${pageParam}=${currentPage + 1}${search}">${nextText}</a></li>
        </c:when>
        <c:otherwise>
            <li class="disabled"><span>${nextText}</span></li>
        </c:otherwise>
    </c:choose>
    <%-- 最后一页 --%>
    <%--
    <c:choose>
        <c:when test="${currentPage == totalPages}">
            <span class="disabled">&gt;&gt;</span>
        </c:when>
        <c:otherwise>
            <a href="#">&gt;&gt;</a>
        </c:otherwise>
    </c:choose>
    --%>
    <%-- skip to --%>
    <c:if test="${ skip and totalPages > 0}">
        <li class="page-skip">
            共&ensp;${totalPages}&ensp;页，向第 <input name="${pageParam}" type="text" value="${currentPage}"/>页
            <c:forEach var="pair" items="${paramValues}" varStatus="st">
                <c:if test="${pair.key != pageParam}">
                    <c:forEach var="v" items="${pair.value}">
                        <c:choose>
                            <%-- 设置为false 才不转义 --%>
                            <c:when test="${escape == false}">
                                <input type="hidden" name="${pair.key}" value="${v}">
                            </c:when>
                            <c:otherwise>
                                <input type="hidden" name="${fn:escapeXml(pair.key)}" value="${fn:escapeXml(v)}">
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:if>
            </c:forEach>
                <%--<button type="submit">&gt;&gt;</button>--%>
            <button class="btn" type="submit">${goText}</button>
        </li>
    </c:if>
</ul>
