<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="headerInclude.jsp" %>
    <title>主页 - 所有留言</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@ include file="studentHeader.jsp" %>
    <div class="layui-body">
        <div class="layui-fluid layadmin-message-fluid">
            <div class="layui-row">
                <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content">
                    <div class="media-body">
                        <form class="layui-form caller-seach"
                              action="${pageContext.request.contextPath}/index/goQuestions">
                            <input type="text" name="keyword" placeholder="&#xe615; 请输入搜索内容"
                                   autocomplete="off" class="layui-input layui-icon caller-pl32">
                            <input type="text" name="mode" value="find" style="display:none">
                            <input type="text" name="page" value="1" style="display:none">
                            <input type="submit" name="btn" style="display:none">
                        </form>
                    </div>
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
                                <p class="fontColor"><a
                                        href="${pageContext.request.contextPath}/index/showQuestion?qid=${it.qid}">${it.title}&ensp;</a>
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
                                    &emsp;&emsp;
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
    <script>
        $(document).keydown(function (e) {
            if (e.keyCode === 13) {
                $('#btn').trigger("click");
            }
        });
    </script>
</body>
</html>
