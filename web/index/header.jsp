<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<ul class="layui-nav" lay-filter="">
    <li class="layui-nav-item"><a href="">首页</a></li>
    <li class="layui-nav-item"><a href="">gao1</a></li>
    <li class="layui-nav-item"><a href="">gao2</a></li>
    <li class="layui-nav-item">
        <a href="javascript:;">gao3</a>
        <dl class="layui-nav-child"> <!-- 二级菜单 -->
            <dd><a href="">gao31</a></dd>
            <dd><a href="">gao32</a></dd>
            <dd><a href="">gao33</a></dd>
        </dl>
    </li>
    <li class="layui-nav-item"><a href="">社区</a></li>
</ul>

<script src="${pageContext.request.contextPath}/layui/layui.js" charset="UTF-8"></script>
<script>
    layui.use('element', function () {
        const element = layui.element;
    });
</script>
</body>
</html>
