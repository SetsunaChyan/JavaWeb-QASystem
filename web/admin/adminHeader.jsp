<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="layui-header">
    <ul class="layui-nav layui-layout-left">
        <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="${pageContext.request.contextPath}/index/goIndex" target="_blank" title="主页">
                <i class="layui-icon layui-icon-home"></i>
            </a>
        </li>
        <li class="layui-nav-item" lay-unselect>
            <a href="javascript:window.location.reload()" title="刷新">
                <i class="layui-icon layui-icon-refresh-3"></i>
            </a>
        </li>
    </ul>

    <ul class="layui-nav layui-layout-right" lay-filter="layadmin-layout-right">
        <li class="layui-nav-item" lay-unselect>
            <a href="javascript:;">
                <cite>${sessionScope.user.username}</cite>
            </a>
            <dl class="layui-nav-child">
                <dd><a href="${pageContext.request.contextPath}/login/changePsw.jsp">修改密码</a></dd>
                <hr>
                <dd style="text-align: center;"><a href="${pageContext.request.contextPath}/login/doLogout">退出</a>
                </dd>
            </dl>
        </li>

        <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;"><i class="layui-icon layui-icon-more-vertical"></i></a>
        </li>
    </ul>
</div>

<div class="layui-side layui-side-menu">
    <div class="layui-side-scroll">
        <div class="layui-logo">
            <span>在线答疑系统 - 管理员</span>
        </div>
        <ul class="layui-nav layui-nav-tree layui-nav-side" lay-filter="">
            <li class="layui-nav-item" lay-unselect><a href="../admin/dashboard.jsp"><i
                    class="layui-icon layui-icon-console"></i>仪表盘</a></li>
            <li class="layui-nav-item" lay-unselect><a href="../admin/department.jsp"><i
                    class="layui-icon layui-icon-component"></i>学院信息维护</a></li>
            <li class="layui-nav-item" lay-unselect><a href="../admin/curriculum.jsp"><i
                    class="layui-icon layui-icon-layer"></i>课程信息维护</a>
            </li>
            <li class="layui-nav-item layui-nav-itemed" lay-unselect>
                <a href="javascript:;"><i class="layui-icon layui-icon-user"></i>账号管理</a>
                <dl class="layui-nav-child">
                    <dd lay-unselect><a href="../admin/teacherAccount.jsp">教师账号</a></dd>
                    <dd lay-unselect><a href="../admin/studentAccount.jsp">学生账号</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" lay-unselect><a href="../admin/teach.jsp"><i
                    class="layui-icon layui-icon-table"></i>授课信息维护</a>
            <li class="layui-nav-item" lay-unselect><a href=""><i class="layui-icon layui-icon-dialogue"></i>留言信息维护</a>
            </li>
        </ul>
    </div>
</div>

<div class="layadmin-pagetabs">
    <script>document.write(document.title);</script>
</div>

<script type="text/javascript" src="${pageContext.request.contextPath}/static/jquery-3.4.1.min.js"
        charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/static/common.js" charset="UTF-8"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/layui/layui.js" charset="UTF-8"></script>
<script>
    layui.use('element', function () {
        const element = layui.element;
        element.render();
    });
</script>
