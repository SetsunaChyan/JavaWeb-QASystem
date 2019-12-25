<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="headerInclude.jsp" %>
    <title>主页 - ${requestScope.cur_name} - 所有留言</title>
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
                            <p class="fontColor"><a href="javascript:;">课程名称:</a> ${requestScope.cur_name}</p>
                            <br>
                            <p class="fontColor"><a href="javascript:;">课程信息:</a> ${requestScope.inf}</p>
                            <br>
                        </div>
                    </div>
                    <hr>
                    <c:if test="${empty requestScope.questions}">
                        <div class="media-body">
                            <div class="pad-btm">
                                <p class="fontColor">暂无留言</p>
                            </div>
                        </div>
                    </c:if>
                    <c:forEach items="${requestScope.questions}" var="it">
                        <div class="media-body">
                            <div class="pad-btm">
                                <p class="fontColor"><a href="javascript:;">${it.title} </a>
                                    <c:if test="${it.solved} eq 0">
                                        <span class="layui-badge-rim">未解决</span>
                                    </c:if>
                                    <c:if test="${it.solved} eq 1">
                                        <span class="layui-badge-rim layui-bg-green">已解决</span>
                                    </c:if>
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
                    </c:forEach>
                    <c:if test="${not empty requestScope.questions}">
                        <div class="media-body" align="center">
                            <div class="pad-btm">
                                <p class="fontColor">
                                    <c:if test="${requestScope.page gt 1}">
                                        <a href="${pageContext.request.contextPath}/index/goQuestions${requestScope.suffix}&page=${requestScope.page-1}">上一页</a>
                                    </c:if>
                                    <c:if test="${requestScope.page lt requestScope.mxPage}">
                                        <a href="${pageContext.request.contextPath}/index/goQuestions${requestScope.suffix}&page=${requestScope.page+1}">下一页</a>
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
