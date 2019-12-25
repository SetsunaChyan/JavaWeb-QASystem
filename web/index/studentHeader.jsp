<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="layui-header">
    <ul class="layui-nav layui-layout-left">
        <c:if test="${sessionScope.user.usertype eq 'admin'}">
            <li class="layui-nav-item" lay-unselect>
                <a href="${pageContext.request.contextPath}/admin/dashboard.jsp" target="_blank" title="仪表盘">
                    <i class="layui-icon  layui-icon-console"></i>
                </a>
            </li>
        </c:if>
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
            <c:if test="${empty sessionScope.user}">
                <a href="${pageContext.request.contextPath}/login/loginPage.jsp">
                    <cite>登录</cite>
                </a>
            </c:if>
            <c:if test="${not empty sessionScope.user}">
                <dl class="layui-nav-child">
                    <dd><a lay-href="">修改密码</a></dd>
                    <hr>
                    <dd style="text-align: center;"><a href="${pageContext.request.contextPath}/login/doLogout">退出</a>
                    </dd>
                </dl>
            </c:if>
        </li>
        <li class="layui-nav-item layui-hide-xs" lay-unselect>
            <a href="javascript:;"><i class="layui-icon layui-icon-more-vertical"></i></a>
        </li>
    </ul>
</div>

<div class="layui-side layui-side-menu">
    <div class="layui-side-scroll">
        <div class="layui-logo">
            <span>在线答疑系统</span>
        </div>
        <ul class="layui-nav layui-nav-tree layui-nav-side" lay-filter="">
            <li class="layui-nav-item" lay-unselect><a href="${pageContext.request.contextPath}/index/goIndex"
                                                       title="主页"><i
                    class="layui-icon layui-icon-home"></i>主页</a></li>
            <li class="layui-nav-item" lay-unselect><a href=""><i
                    class="layui-icon layui-icon-component"></i>2</a></li>
            <li class="layui-nav-item" lay-unselect><a href=""><i
                    class="layui-icon layui-icon-layer"></i>3</a>
            </li>
            <li class="layui-nav-item layui-nav-itemed" lay-unselect>
                <a href="javascript:;"><i class="layui-icon layui-icon-user"></i>4</a>
                <dl class="layui-nav-child">
                    <dd lay-unselect><a href="">44</a></dd>
                    <dd lay-unselect><a href="">55</a></dd>
                </dl>
            </li>
            <li class="layui-nav-item" lay-unselect><a href=""><i class="layui-icon layui-icon-table"></i>6</a>
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
