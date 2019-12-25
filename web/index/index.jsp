<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="headerInclude.jsp" %>
    <title>主页</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@ include file="studentHeader.jsp" %>
    <div class="layui-body">
        <div class="layui-fluid layadmin-cmdlist-fluid">
            <!--所有学院-->
            <div class="layui-card">
                <div class="layui-card-header">
                    <label class="layadmin-font-blod">所有学院</label>
                </div>
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space30">
                        <c:forEach items="${requestScope.depts}" var="it">
                            <div class="layui-col-md2 layui-col-sm4">
                                <div class="cmdlist-container">
                                    <a href="${pageContext.request.contextPath}/index/goCurr?pageType=dept&name=${it.dept}">
                                        <img src="${pageContext.request.contextPath}/img/dept.jpg">
                                    </a>
                                    <div class="cmdlist-text">
                                        <p class="info" align="center">${it.dept}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>

            <!--所有教师-->
            <div class="layui-card">
                <div class="layui-card-header">
                    <label class="layadmin-font-blod">所有教师</label>
                </div>
                <div class="layui-card-body">
                    <div class="layui-row layui-col-space30">
                        <c:forEach items="${requestScope.teachers}" var="it">
                            <div class="layui-col-md2 layui-col-sm4">
                                <div class="cmdlist-container">
                                    <a href="${pageContext.request.contextPath}/index/goCurr?pageType=teacher&name=${it.name}">
                                        <img src="${pageContext.request.contextPath}/img/teacher.jpg">
                                    </a>
                                    <div class="cmdlist-text">
                                        <p class="info" align="center">${it.name}</p>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
