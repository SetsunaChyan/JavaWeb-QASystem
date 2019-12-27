<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../index/headerInclude.jsp" %>
    <c:set var="it" value="${requestScope.question}"/>
    <title>主页 - ${it.title} - 所有回答</title>
</head>
<body class="layui-layout-body">

<!--添加回答-->
<div class="layui-row" id="table-add-frame" style="display:none;position: absolute;
    top: 0; left: 0; bottom: 0; right: 0;">
    <div class="layui-col-md11">
        <form id="table_add_form" class="layui-form" lay-filter="table_add_form"
              action="${pageContext.request.contextPath}/teacher/doReply"
              style="margin-top: 20px;align:center;">
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
            <input type="text" name="rid" value="" style="display:none">
            <input type="text" name="picPath" value="" style="display:none">
            <input type="text" name="type" value="add" style="display:none">
        </form>
    </div>
</div>

<div class="layui-layout layui-layout-admin">
    <%@ include file="teacherHeader.jsp" %>
    <div class="layui-body">
        <div class="layui-fluid layadmin-message-fluid">
            <div class="layui-row">
                <div class="layui-col-md12"></div>
                <div class="layui-col-md12 layadmin-homepage-list-imgtxt message-content">
                    <div class="media-body">
                        <div class="pad-btm">
                            <p class="fontColor"><a
                                    href="javascript:;">${it.title}&ensp;</a>
                                <c:if test="${it.solved eq 0}">
                                    <span class="layui-badge-rim layui-bg-red">未解决</span>
                                </c:if>
                                <c:if test="${it.solved eq 1}">
                                    <span class="layui-badge-rim layui-bg-green">已解决</span>
                                </c:if>
                                &ensp;
                                <a href="${pageContext.request.contextPath}/teacher/doReply?mode=add&qid=${it.qid}">回答</a>
                                <a href="${pageContext.request.contextPath}/teacher/doModifyQuestion?mode=update&qid=${it.qid}">&ensp;|&ensp;编辑&ensp;|&ensp;</a>
                                <a href="${pageContext.request.contextPath}/teacher/doModifyQuestion?mode=del&qid=${it.qid}">删除</a>
                            </p>
                            <p class="min-font">
                                <span class="layui-breadcrumb" lay-separator="-">
                                    <a href="javascript:;">${it.username}</a>
                                    <a href="javascript:;">${it.timestamp}</a>
                                </span>
                            </p>
                        </div>
                        <p class="message-text">
                            ${it.context}<br>
                            <c:if test="${not empty it.picPath}">
                                <a class="fontColor"
                                   href="${pageContext.request.contextPath}/index/goIMG?picPath=${it.picPath}">[显示图片]</a>
                            </c:if>
                        </p>
                    </div>
                    <hr>
                    <c:if test="${empty requestScope.replies}">
                        <div class="media-body">
                            <div class="pad-btm">
                                <p class="fontColor">暂无回答</p>
                            </div>
                        </div>
                    </c:if>
                    <c:forEach items="${requestScope.replies}" var="rit">
                        <div class="media-body">
                            <div class="pad-btm">
                                <p class="fontColor"><a href="javascript:;">${rit.username}&ensp;</a>
                                    &ensp;
                                    <c:if test="${user.username eq rit.username}">
                                        <a href="${pageContext.request.contextPath}/teacher/doReply?mode=update&rid=${rit.rid}">编辑&ensp;|&ensp;</a>
                                        <a href="${pageContext.request.contextPath}/teacher/doReply?mode=del&rid=${rit.rid}">删除</a>
                                    </c:if>
                                </p>
                                <p class="min-font">
                                <span class="layui-breadcrumb" lay-separator="-">
                                    <a href="javascript:;">${rit.timestamp}</a>
                                </span>
                                </p>
                            </div>
                            <p class="message-text">
                                    ${rit.context}<br>
                                <c:if test="${not empty rit.picPath}">
                                    <a class="fontColor"
                                       href="${pageContext.request.contextPath}/index/goIMG?picPath=${rit.picPath}">[显示图片]</a>
                                </c:if>
                            </p>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
    <script>
        let index;
        var table, layer, form, upload, element;
        layui.use(['layer', 'form', 'upload', 'element'], function () {
            table = layui.table, layer = layui.layer, form = layui.form, upload = layui.upload, element = layui.element;
            form.verify({
                required: [/[\S]+/, "必填项不能为空"]
            });
        });

        function table_edit_form(title, name, w, h, isEdit, data) {
            index = layer.open({
                type: 1,
                title: title,
                area: [w, h],
                fix: true,
                resize: false,
                maxmin: false,
                shadeClose: false,
                shade: 0.4,
                content: $(name).html(),
            });
            form.render();
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
                    form.val("table_add_form", {
                        "picPath": res.src
                    });
                    layer.msg("提交成功", {icon: 1, time: 1500});
                }
                , error: function () {
                    layer.msg("提交失败", {icon: 2})
                }
            });
            if (isEdit)
                form.val("table_add_form", {
                    "context": data.context,
                    "picPath": data.picPath,
                    "rid": data.rid
                });
            else
                form.val("table_add_form", {"context": "", "picPath": ""});
        }
    </script>
</body>
</html>
