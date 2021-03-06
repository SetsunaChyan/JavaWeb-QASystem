<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../index/headerInclude.jsp" %>
    <title>仪表盘 - 账号管理 - 教师账号</title>
</head>
<body class="layui-layout-body">
<div class="layui-layout layui-layout-admin">
    <%@ include file="../admin/adminHeader.jsp" %>

    <div id="toolbarDemo" style="visibility: hidden">
        <div class="layui-btn-container">
            <button class="layui-btn layui-btn-sm" lay-event="add"><i class="layui-icon layui-icon-add-circle"></i>添加
            </button>
        </div>
    </div>

    <div id="barDemo" style="visibility: hidden">
        <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="reset">重置密码</a>
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </div>

    <!--用于编辑-->
    <div class="layui-row" id="table-edit-frame" style="display:none;position: absolute;
    top: 0; left: 0; bottom: 0; right: 0;">
        <div class="layui-col-md11">
            <form id="table_edit_form" class="layui-form" action="" lay-filter="table_edit_form"
                  style="margin-top: 20px;align:center;">
                <div class="layui-form-item">
                    <label class="layui-form-label">教师名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" lay-verify="required"
                               autocomplete="off" class="layui-input" disabled="true">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">教师职称</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">教师简介</label>
                    <div class="layui-input-block">
                        <textarea type="textarea" name="inf" class="layui-textarea"></textarea>
                    </div>
                </div>
                <div class="layui-form-item">
                    <div class="layui-input-block">
                        <button class="layui-btn layui-btn-normal" id="table_edit_form_submit"
                                lay-submit="table_edit_form_submit"
                                lay-filter="table_edit_form_submit">修改
                        </button>
                    </div>
                </div>
            </form>
        </div>
    </div>

    <!--用于添加-->
    <div class="layui-row" id="table-add-frame" style="display:none;position: absolute;
    top: 0; left: 0; bottom: 0; right: 0;">
        <div class="layui-col-md11">
            <form id="table_add_form" class="layui-form" lay-filter="table_add_form" action=""
                  style="margin-top: 20px;align:center;">
                <div class="layui-form-item">
                    <label class="layui-form-label">教师名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" lay-verify="required"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">教师职称</label>
                    <div class="layui-input-block">
                        <input type="text" name="title" autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">教师简介</label>
                    <div class="layui-input-block">
                        <textarea type="textarea" name="inf" class="layui-textarea"></textarea>
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
            </form>
        </div>
    </div>

    <div class="layui-body">
        <table id="table-teacher" lay-filter="table-teacher"></table>
        <script>
            let index;

            layui.use(['table', 'layer', 'form'], function () {
                const table = layui.table
                    , layer = layui.layer
                    , form = layui.form;

                table.render({
                    elem: '#table-teacher',
                    toolbar: '#toolbarDemo',
                    defaultToolbar: [],
                    url: '${pageContext.request.contextPath}/getJSON?datatype=teacher',
                    cols: [[
                        {field: 'name', title: '教师名称', width: 200},
                        {field: 'title', title: '教师职称', width: 200},
                        {field: 'inf', title: '教师简介', width: 718},
                        {title: '操作', toolbar: '#barDemo', width: 185, fixed: 'right'}
                    ]],
                    text: {
                        none: '暂无相关数据'
                    }
                });

                //头工具栏事件
                table.on('toolbar(table-teacher)', function (obj) {
                    if (obj.event === 'add')
                        table_edit_form('添加新的教师', "#table-add-frame", 380, 350, false, '');
                });

                //监听行工具事件
                table.on('tool(table-teacher)', function (obj) {
                    const data = obj.data;
                    if (obj.event === 'del') {
                        layer.confirm('确定删除？', function (index) {
                            $.ajax({
                                type: "post",
                                url: "${pageContext.request.contextPath}/admin/doDataUpdate",
                                data: {type: "teacher", op: "del", name: data.name},
                                dataType: "json",
                                success: function (msg) {
                                    if (msg.success) {
                                        layer.msg("删除成功", {icon: 1, time: 1500});
                                        obj.del();
                                    } else {
                                        layer.msg("删除失败", {icon: 2});
                                    }
                                    table.reload('table-teacher');
                                }
                            });
                            layer.close(index);
                        });
                    } else if (obj.event === 'reset') {
                        layer.confirm('确定重置密码？', function (index) {
                            $.ajax({
                                type: "post",
                                url: "${pageContext.request.contextPath}/admin/doDataUpdate",
                                data: {type: "teacher", op: "reset", name: data.name},
                                dataType: "json",
                                success: function (msg) {
                                    if (msg.success) {
                                        layer.msg("重置成功", {icon: 1, time: 1500});
                                        obj.del();
                                    } else {
                                        layer.msg("重置失败", {icon: 2});
                                    }
                                    table.reload('table-teacher');
                                }
                            });
                            layer.close(index);
                        });
                    } else if (obj.event === 'edit')
                        table_edit_form('编辑教师信息', "#table-edit-frame", 380, 350, true, data);
                });

                form.verify({
                    required: [/[\S]+/, "必填项不能为空"]
                });

                //提交修改
                form.on('submit(table_edit_form_submit)', function (data) {
                    const formData = data.field;
                    const name = formData.name,
                        inf = formData.inf,
                        title = formData.title;
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/admin/doDataUpdate",
                        data: {type: "teacher", op: "update", name: name, title: title, inf: inf},
                        dataType: "json",
                        success: function (msg) {
                            if (msg.success) {
                                layer.msg("提交成功", {icon: 1, time: 1500});
                                table.reload('table-teacher');
                            } else {
                                layer.msg("提交失败", {icon: 2});
                            }
                            layer.close(index);
                        }
                    });
                    return false;
                });

                //提交增加
                form.on('submit(table_add_form_submit)', function (data) {
                    const formData = data.field;
                    const name = formData.name,
                        inf = formData.inf,
                        title = formData.title;
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/admin/doDataUpdate",
                        data: {type: "teacher", op: "add", name: name, title: title, inf: inf},
                        dataType: "json",
                        success: function (msg) {
                            if (msg.success) {
                                layer.msg("添加成功", {icon: 1, time: 1500});
                                table.reload('table-teacher');
                            } else {
                                layer.msg("添加失败", {icon: 2});
                            }
                            layer.close(index);
                        }
                    });
                    return false;
                });

                //打开弹出层
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
                        content: $(name).html()
                    });
                    if (isEdit)
                        form.val("table_edit_form", {"name": data.name, "title": data.title, "inf": data.inf});
                    else
                        form.val("table_add_form", {"name": "", "title": "", "inf": ""});
                }
            });
        </script>
    </div>
</body>
</html>
