<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="headerInclude.jsp" %>
    <c:set var="it" value="${requestScope.question}"/>
    <title>主页 - ${it.title} - 所有回答</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@ include file="studentHeader.jsp" %>
    <div class="layui-body">
        <div class="layui-fluid layadmin-message-fluid">
            <div class="layui-row">
                <div class="layui-col-md12"></div>
                <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content">
                    <div class="media-body">
                        <div class="pad-btm">
                            <p class="fontColor"><a href="javascript:;">${it.title}&ensp;</a>
                                <c:if test="${it.solved eq 0}">
                                    <span class="layui-badge-rim layui-bg-red">未解决</span>
                                </c:if>
                                <c:if test="${it.solved eq 1}">
                                    <span class="layui-badge-rim layui-bg-green">已解决</span>
                                </c:if>
                                &ensp;
                                <c:if test="${not empty sessionScope.user}">
                                    <c:set var="user" value="${sessionScope.user}"/>
                                    <c:if test="${user.username eq it.username or user.usertype eq 'admin'}">
                                        <a href="${pageContext.request.contextPath}/student/doAddQuestion?mode=update&qid=${it.qid}">编辑&ensp;|&ensp;</a>
                                    </c:if>
                                    <c:if test="${user.username eq it.username or user.usertype eq 'admin' or user.usertype eq 'teacher'}">
                                        <a href="${pageContext.request.contextPath}/student/doAddQuestion?mode=del&qid=${it.qid}">删除</a>
                                    </c:if>
                                </c:if>
                            </p>
                            <p class="min-font">
                                <span class="layui-breadcrumb" lay-separator="-">
                                    <a href="javascript:;">${it.username}</a>
                                    <a href="javascript:;">${it.timestamp}</a>
                                </span>
                            </p>
                        </div>
                        <p class="message-text">
                            ${it.context}<br>
                            <c:if test="${not empty it.picPath}">
                                <a class="fontColor"
                                   href="${pageContext.request.contextPath}/index/goIMG?picPath=${it.picPath}">[显示图片]</a>
                            </c:if>
                        </p>
                    </div>
                    <hr>
                    <c:if test="${empty requestScope.replies}">
                        <div class="media-body">
                            <div class="pad-btm">
                                <p class="fontColor">暂无回答</p>
                            </div>
                        </div>
                    </c:if>
                    <c:forEach items="${requestScope.replies}" var="rit">
                        <div class="media-body">
                            <div class="pad-btm">
                                <p class="fontColor"><a href="javascript:;">${rit.username}&ensp;</a>
                                    &ensp;
                                    <c:if test="${not empty sessionScope.user}">
                                        <c:set var="user" value="${sessionScope.user}"/>
                                        <c:if test="${user.username eq it.username or user.usertype eq 'admin'}">
                                            <a href="${pageContext.request.contextPath}/student/doAddQuestion?mode=update&qid=${it.qid}">编辑&ensp;|&ensp;</a>
                                        </c:if>
                                        <c:if test="${user.username eq it.username or user.usertype eq 'admin'}">
                                            <a href="${pageContext.request.contextPath}/student/doAddQuestion?mode=del&qid=${it.qid}">删除</a>
                                        </c:if>
                                    </c:if>
                                </p>
                                <p class="min-font">
                                <span class="layui-breadcrumb" lay-separator="-">
                                    <a href="javascript:;">${rit.timestamp}</a>
                                </span>
                                </p>
                            </div>
                            <p class="message-text">
                                    ${rit.context}<br>
                                <c:if test="${not empty rit.picPath}">
                                    <a class="fontColor"
                                       href="${pageContext.request.contextPath}/index/goIMG?picPath=${rit.picPath}">[显示图片]</a>
                                </c:if>
                            </p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
