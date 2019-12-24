<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@ include file="../index/headerInclude.jsp" %>
    <title>仪表盘 - 授课信息维护</title>
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
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
    </div>

    <!--用于编辑-->
    <div class="layui-row" id="table-edit-frame" style="display:none;position: absolute;
    top: 0; left: 0; bottom: 0; right: 0;">
        <div class="layui-col-md11">
            <form id="table_edit_form" class="layui-form" action="" lay-filter="table_edit_form"
                  style="margin-top: 20px;align:center;">
                <div class="layui-form-item">
                    <label class="layui-form-label">课程名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" lay-verify="required"
                               autocomplete="off" class="layui-input" disabled="true">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">开课学院</label>
                    <div class="layui-input-block">
                        <select name="dept"></select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">课程信息</label>
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
                    <label class="layui-form-label">课程名称</label>
                    <div class="layui-input-block">
                        <input type="text" name="name" lay-verify="required"
                               autocomplete="off" class="layui-input">
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">开课学院</label>
                    <div class="layui-input-block">
                        <select name="dept"></select>
                    </div>
                </div>
                <div class="layui-form-item">
                    <label class="layui-form-label">课程信息</label>
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
        <table id="table-curr" lay-filter="table-curr"></table>
        <script>
            let index;

            layui.use(['table', 'layer', 'form'], function () {
                const table = layui.table
                    , layer = layui.layer
                    , form = layui.form;

                table.render({
                    elem: '#table-curr',
                    toolbar: '#toolbarDemo',
                    defaultToolbar: [],
                    url: '${pageContext.request.contextPath}/getJSON?datatype=curr',
                    cols: [[
                        {field: 'name', title: '课程名称', width: 200},
                        {field: 'dept', title: '开课学院', width: 200},
                        {field: 'inf', title: '基本信息', width: 788},
                        {title: '操作', toolbar: '#barDemo', width: 115, fixed: 'right'}
                    ]],
                    text: {
                        none: '暂无相关数据'
                    }
                });

                //头工具栏事件
                table.on('toolbar(table-curr)', function (obj) {
                    if (obj.event === 'add')
                        table_edit_form('添加新的课程', "#table-add-frame", 380, 350, false, '');
                });

                //监听行工具事件
                table.on('tool(table-curr)', function (obj) {
                    const data = obj.data;
                    if (obj.event === 'del') {
                        layer.confirm('确定删除？', function (index) {
                            $.ajax({
                                type: "post",
                                url: "${pageContext.request.contextPath}/admin/doDataUpdate",
                                data: {type: "curr", op: "del", name: data.name},
                                dataType: "json",
                                success: function (msg) {
                                    if (msg.success) {
                                        layer.msg("删除成功", {icon: 1, time: 1500});
                                        obj.del();
                                    } else {
                                        layer.msg("删除失败", {icon: 2});
                                    }
                                    table.reload('table-curr');
                                }
                            });
                            layer.close(index);
                        });
                    } else if (obj.event === 'edit')
                        table_edit_form('编辑课程信息', "#table-edit-frame", 380, 350, true, data);
                });

                //提交修改
                form.on('submit(table_edit_form_submit)', function (data) {
                    const formData = data.field;
                    const name = formData.name,
                        inf = formData.inf,
                        dept = formData.dept;
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/admin/doDataUpdate",
                        data: {type: "curr", op: "update", name: name, dept: dept, inf: inf},
                        dataType: "json",
                        success: function (msg) {
                            if (msg.success) {
                                layer.msg("提交成功", {icon: 1, time: 1500});
                                table.reload('table-curr');
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
                        dept = formData.dept;
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/admin/doDataUpdate",
                        data: {type: "curr", op: "add", name: name, dept: dept, inf: inf},
                        dataType: "json",
                        success: function (msg) {
                            if (msg.success) {
                                layer.msg("添加成功", {icon: 1, time: 1500});
                                table.reload('table-curr');
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
                    $.ajax({
                        type: "post",
                        url: "${pageContext.request.contextPath}/getJSON",
                        data: {datatype: "department"},
                        dataType: "json",
                        success: function (callback) {
                            const obj = $(name), selecterID = "dept";
                            //动态增加select选项
                            obj.find("[name=" + selecterID + "]").empty();
                            $.each(callback.data, function (key, val) {
                                const options = "<option value=\"" + val.dept_name + "\">" + val.dept_name + "</option>";
                                obj.find("[name=" + selecterID + "]").append(options);
                            });
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
                            form.render();
                            if (isEdit)
                                form.val("table_edit_form", {"name": data.name, "dept": data.dept, "inf": data.inf});
                            else
                                form.val("table_add_form", {"name": "", "inf": ""});
                        }
                    });
                }
            });
        </script>
    </div>
</body>
</html>
