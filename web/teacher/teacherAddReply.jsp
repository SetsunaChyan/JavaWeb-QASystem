<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../index/headerInclude.jsp" %>
    <c:if test="${requestScope.mode eq 'add'}">
        <title>添加回答</title>
    </c:if>
    <c:if test="${requestScope.mode eq 'update'}">
        <title>修改回答</title>
    </c:if>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@ include file="teacherHeader.jsp" %>
    <c:set var="q" value="${requestScope.reply}"/>
    <div class="layui-body">
        <div class="layui-fluid">
            <div class="layui-card">
                <c:if test="${requestScope.mode eq 'add'}">
                    <div class="layui-card-header">添加对 ${requestScope.title} 的回答</div>
                </c:if>
                <c:if test="${requestScope.mode eq 'update'}">
                    <div class="layui-card-header">修改对 ${requestScope.title} 的回答</div>
                </c:if>
                <div class="layui-card-body" style="padding: 15px;">
                    <form class="layui-form" action="${pageContext.request.contextPath}/teacher/doSubmitReply"
                          id="component-form-group" lay-filter="component-form-group" method="post">
                        <div class="layui-form-item">
                            <label class="layui-form-label">回答内容</label>
                            <div class="layui-input-block">
                                <textarea type="textarea" name="context" class="layui-textarea"></textarea>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">上传图片</label>
                            <div class="layui-input-inline">
                                <div class="layui-upload-drag pic" id="pic">
                                    <i class="layui-icon layui-icon-upload"></i>
                                    <p>点击上传图片，或将图片拖拽到此处</p>
                                </div>
                            </div>
                            <label class="layui-form-label">图片预览</label>
                            <div class="layui-input-inline">
                                <img style="max-width: 190px;max-height:190px;" id="preview" src="">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn layui-btn-normal" id="table_add_form_submit"
                                        lay-submit="table_add_form_submit"
                                        lay-filter="table_add_form_submit">添加
                                </button>
                            </div>
                        </div>
                        <c:if test="${requestScope.mode eq 'update'}">
                            <input type="text" name="rid" value="" style="display:none">
                        </c:if>
                        <input type="text" name="qid" value="${requestScope.qid}" style="display:none">
                        <input type="text" name="picPath" value="" style="display:none">
                        <input type="text" name="mode" value="${requestScope.mode}" style="display:none">
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

                form.render();

                <c:if test="${requestScope.mode eq 'update'}">
                form.val('component-form-group', {
                    "context": "${q.context}", "picPath": "${q.picPath}"
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
