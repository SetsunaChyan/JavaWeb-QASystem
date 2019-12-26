<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../index/headerInclude.jsp" %>
    <title>更改密码</title>
</head>
<body>
<div class="layui-col-md6 layui-col-md-offset3" align="center" style="margin-top: 5cm;">
    <form class="layui-form" action="doChangePwd" method="post">
        <div class="layadmin-user-login-box layadmin-user-login-header">
            <h2>更改密码</h2>
        </div>
        <div class="layui-form-item">
            <div class="layui-box layui-icon" style="width: 240px">
                <input type="password" name="oldPwd" placeholder="&#xe673; 旧密码" lay-verify="required|pwd"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-box layui-icon" style="width: 240px">
                <input type="password" name="pwd" placeholder="&#xe673; 新密码" lay-verify="required|pwd"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-box layui-icon" style="width: 240px">
                <input type="password" name="pwd_confirm" placeholder="&#xe673; 确认新密码" lay-verify="required|pwd_confirm"
                       class="layui-input">
            </div>
        </div>
        <div class="layui-form-item">
            <div class="layui-box">
                <button class="layui-btn" lay-submit lay-filter="submit" style="width: 240px">修改密码</button>
            </div>
        </div>
    </form>
</div>

<script src="${pageContext.request.contextPath}/layui/layui.js" charset="UTF-8"></script>
<script>
    layui.use('form', function () {
        const form = layui.form;

        form.verify({
            required: [/[\S]+/, "必填项不能为空"],
            username: function (value, item) { //value：表单的值、item：表单的DOM对象
                if (!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)) {
                    return '用户名不能有特殊字符';
                }
                if (/(^\_)|(\__)|(\_+$)/.test(value)) {
                    return '用户名首尾不能出现下划线\'_\'';
                }
                if (/^\d+\d+\d$/.test(value)) {
                    return '用户名不能全为数字';
                }
            },
            pwd: [/^[\S]{6,16}$/, '密码必须6到16位，且不能出现空格'],
            pwd_confirm: function (value) {
                const pwd = $("input[name=pwd]").val();
                if (pwd !== value)
                    return "二次输入的密码不一致！";
            }
        });
    });
</script>
</body>
</html>
