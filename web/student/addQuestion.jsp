<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../index/headerInclude.jsp" %>
    <c:if test="${requestScope.mode eq 'add'}">
        <title>添加留言</title>
    </c:if>
    <c:if test="${requestScope.mode eq 'update'}">
        <title>修改留言</title>
    </c:if>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@ include file="../index/studentHeader.jsp" %>
    <c:set var="q" value="${requestScope.question}"/>
    <div class="layui-body">
        <div class="layui-fluid">
            <div class="layui-card">
                <c:if test="${requestScope.mode eq 'add'}">
                    <div class="layui-card-header">添加新的留言</div>
                </c:if>
                <c:if test="${requestScope.mode eq 'update'}">
                    <div class="layui-card-header">修改 ${q.title}</div>
                </c:if>
                <div class="layui-card-body" style="padding: 15px;">
                    <form class="layui-form" action="${pageContext.request.contextPath}/student/doSubmitQuestion"
                          id="component-form-group" lay-filter="component-form-group" method="post">
                        <div class="layui-form-item">
                            <label class="layui-form-label">留言标题</label>
                            <div class="layui-input-inline">
                                <input type="text" name="title" lay-verify="required" autocomplete="off"
                                       placeholder="请输入标题" class="layui-input">
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">所属课程</label>
                            <div class="layui-input-inline">
                                <select name="curr" lay-verify="required"></select>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">留言内容</label>
                            <div class="layui-input-block">
                                <textarea type="text" name="context" lay-verify="required" placeholder="请输入留言内容"
                                          autocomplete="off" class="layui-textarea"></textarea>
                            </div>
                        </div>

                        <div class="layui-form-item">
                            <label class="layui-form-label">上传图片</label>
                            <div class="layui-input-inline">
                                <div class="layui-upload-drag" id="pic">
                                    <i class="layui-icon layui-icon-upload"></i>
                                    <p>点击上传图片，或将图片拖拽到此处</p>
                                </div>
                            </div>
                            <label class="layui-form-label">图片预览</label>
                            <div class="layui-input-inline">
                                <img style="max-width: 190px;max-height:190px;" id="preview" src="${q.picPath}">
                            </div>
                        </div>

                        <div class="layui-form-item layui-layout-admin">
                            <div class="layui-input-block">
                                <div class="layui-footer" style="left: 0;">
                                    <button class="layui-btn" lay-submit="">立即提交
                                    </button>
                                    <button type="reset" class="layui-btn layui-btn-primary">重置</button>
                                </div>
                            </div>
                        </div>

                        <input type="text" name="mode" value="${requestScope.mode}" style="display:none">
                        <input type="text" name="picPath" value="${q.picPath}" style="display:none">
                        <c:if test="${requestScope.mode eq 'update'}">
                            <input type="text" name="qid" value="${q.qid}" style="display:none">
                        </c:if>
                    </form>
                </div>
            </div>
        </div>

        <script>
            layui.use(['form', 'element', 'layer', 'upload'], function () {
                var $ = layui.$
                    , element = layui.element
                    , form = layui.form
                    , upload = layui.upload
                    , layer = layui.layer;

                var obj = $('#component-form-group');
                var options;
                obj.find("[name=curr]").empty();
                <c:forEach items="${requestScope.currs}" var="it">
                options = "<option value=\"${it.name}\">${it.name}</option>";
                obj.find("[name=curr]").append(options);
                </c:forEach>

                form.render();

                <c:if test="${requestScope.mode eq 'update'}">
                form.val('component-form-group', {
                    "title": "${q.title}", "curr": "${q.cur_name}", "context": "${q.context}", "picPath": "${q.picPath}"
                });
                </c:if>

                form.verify({
                    required: [/[\S]+/, "必填项不能为空"]
                });

                //上传功能
                upload.render({
                    elem: '#pic'
                    , url: '${pageContext.request.contextPath}/index/uploadIMG'
                    , acceptMime: 'image/jpg'
                    , choose: function (obj) {
                        obj.preview(function (index, file, result) {
                            $('#preview').attr('src', result);
                        })
                    }
                    , done: function (res) {
                        if (res.code > 0)
                            return layer.msg("上传失败", {icon: 2});
                        form.val('component-form-group', {
                            "picPath": res.src
                        });
                        layer.msg("提交成功", {icon: 1, time: 1500});
                    }
                    , error: function () {
                        layer.msg("提交失败", {icon: 2})
                    }
                });
            });
        </script>
    </div>
</div>
</body>
</html>
