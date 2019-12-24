<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../index/headerInclude.jsp" %>
    <title>登录</title>
</head>
<body>

<div class="layui-col-md6 layui-col-md-offset3" align="center" style="margin-top: 5cm;">
    <form class="layui-form" action="doLogin" method="post">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>线上答疑系统</h2>
        </div>
        <div class="layui-form-item">
            <div class="layui-box layui-icon box" style="width: 240px;">
                <input type="text" name="username" placeholder="&#xe66f; 用户名" lay-verify="required" class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-box layui-icon" style="width: 240px">
                <input type="password" name="password" placeholder="&#xe673; 密码" lay-verify="required"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-box">
                <button class="layui-btn" lay-submit lay-filter="submit" style="width: 240px">登&nbsp;&nbsp;录</button>
            </div>
        </div>
        <div class="layui-form-item">
            <a href="regPage.jsp" class="layadmin-user-jump-change layadmin-link" style="width: 77%">学生注册</a>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/layui/layui.js" charset="UTF-8"></script>
<script>
    layui.use('form', function () {
        const form = layui.form;
        //监听提交

    });
</script>
</body>
</html>
