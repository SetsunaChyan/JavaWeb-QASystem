<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../index/headerInclude.jsp" %>
    <title>主页 - 新消息</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@ include file="teacherHeader.jsp" %>
    <div class="layui-body">
        <div class="layui-fluid layadmin-message-fluid">
            <div class="layui-row">
                <div class="layui-col-md12"></div>
                <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content">
                    <c:if test="${empty requestScope.questions}">
                        <div class="media-body">
                            <div class="pad-btm">
                                <p class="fontColor">暂无留言</p>
                            </div>
                        </div>
                    </c:if>
                    <c:forEach items="${requestScope.questions}" var="it">
                        <c:if test="${it.solved eq 0}">
                            <div class="media-body">
                                <div class="pad-btm">
                                    <p class="fontColor"><a
                                            href="${pageContext.request.contextPath}/teacher/showQuestion?qid=${it.qid}">${it.title}&ensp;</a>
                                        <span class="layui-badge-rim layui-bg-red">未解决</span>
                                        &ensp;
                                        <a href="${pageContext.request.contextPath}/teacher/doModifyQuestion?mode=update&qid=${it.qid}">编辑&ensp;|&ensp;</a>
                                        <a href="${pageContext.request.contextPath}/teacher/doModifyQuestion?mode=del&qid=${it.qid}">删除</a>
                                    </p>
                                    <p class="min-font">
                                <span class="layui-breadcrumb" lay-separator="-">
                                    <a href="javascript:;">${it.username}</a>
                                    <a href="javascript:;">${it.timestamp}</a>
                                </span>
                                    </p>
                                </div>
                                <p class="message-text">${it.context}</p>
                            </div>
                        </c:if>
                    </c:forEach>
                    <c:if test="${not empty requestScope.questions}">
                        <div class="media-body" align="center">
                            <div class="pad-btm">
                                <p class="fontColor">
                                    <c:if test="${requestScope.page gt 1}">
                                        <a href="${pageContext.request.contextPath}/teacher/goQuestions${requestScope.suffix}&page=${requestScope.page-1}">上一页</a>
                                    </c:if>
                                    &emsp;&emsp;
                                    <c:if test="${requestScope.page lt requestScope.mxPage}">
                                        <a href="${pageContext.request.contextPath}/teacher/goQuestions${requestScope.suffix}&page=${requestScope.page+1}">下一页</a>
                                    </c:if>
                                </p>
                            </div>
                        </div>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
