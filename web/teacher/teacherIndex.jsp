<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../index/headerInclude.jsp" %>
    <title>主页</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@ include file="teacherHeader.jsp" %>
    <div class="layui-body">
        <div class="layui-fluid layadmin-message-fluid">
            <div class="layui-row">
                <div class="layui-col-md12"></div>
                <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content">
                    <div class="media-body">
                        <div class="pad-btm">
                            <p class="fontColor"><a href="javascript:;">教师名称: </a> ${requestScope.data.name}</p>
                            <br>
                            <p class="fontColor"><a href="javascript:;">教师职称: </a> ${requestScope.data.title}</p>
                            <br>
                            <p class="fontColor"><a href="javascript:;">教师信息: </a> ${requestScope.data.inf}</p>
                            <br>
                        </div>
                    </div>
                    <hr>
                    <c:forEach items="${requestScope.currs}" var="it">
                        <div class="media-body">
                            <div class="pad-btm">
                                <p class="fontColor">
                                    <a href="${pageContext.request.contextPath}/teacher/goQuestions?page=1&mode=cur&cur_name=${it.name}">
                                            ${it.name} - ${it.dept}</a></p>
                            </div>
                            <p class="message-text">${it.inf}</p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
