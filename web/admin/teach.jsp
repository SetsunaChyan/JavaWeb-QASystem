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
        <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
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
                            <select name="cur_name" lay-verify="required"></select>
                        </div>
                    </div>
                    <div class="layui-form-item">
                        <label class="layui-form-label">教师名称</label>
                        <div class="layui-input-block">
                            <select name="te_name" lay-verify="required"></select>
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
            <table id="table-teach" lay-filter="table-teach"></table>
            <script>
                let index;

                layui.use(['table', 'layer', 'form'], function () {
                    const table = layui.table
                        , layer = layui.layer
                        , form = layui.form;

                    table.render({
                        elem: '#table-teach',
                        toolbar: '#toolbarDemo',
                        defaultToolbar: [],
                        url: '${pageContext.request.contextPath}/getJSON?datatype=teach',
                        cols: [[
                            {field: 'cur_name', title: '课程名称', width: 615, sort: true},
                            {field: 'te_name', title: '教师名称', width: 615, sort: true},
                            {title: '操作', toolbar: '#barDemo', width: 65, fixed: 'right'}
                        ]],
                        text: {
                            none: '暂无相关数据'
                        }
                    })
                    ;

                    //头工具栏事件
                    table.on('toolbar(table-teach)', function (obj) {
                        if (obj.event === 'add')
                            table_edit_form('添加新的授课信息', "#table-add-frame", 380, 250);
                    });

                    //监听行工具事件
                    table.on('tool(table-teach)', function (obj) {
                        const data = obj.data;
                        if (obj.event === 'del') {
                            layer.confirm('确定删除？', function (index) {
                                $.ajax({
                                    type: "post",
                                    url: "${pageContext.request.contextPath}/admin/doDataUpdate",
                                    data: {type: "teach", op: "del", cur_name: data.cur_name, te_name: data.te_name},
                                    dataType: "json",
                                    success: function (msg) {
                                        if (msg.success) {
                                            layer.msg("删除成功", {icon: 1, time: 1500});
                                            obj.del();
                                        } else {
                                            layer.msg("删除失败", {icon: 2});
                                        }
                                        table.reload('table-teach');
                                    }
                                });
                                layer.close(index);
                            });
                        }
                    });

                    form.verify({
                        required: [/[\S]+/, "必填项不能为空"]
                    });

                    //提交增加
                    form.on('submit(table_add_form_submit)', function (data) {
                        $.ajax({
                            type: "post",
                            url: "${pageContext.request.contextPath}/admin/doDataUpdate",
                            data: {
                                type: "teach",
                                op: "add",
                                cur_name: data.field.cur_name,
                                te_name: data.field.te_name
                            },
                            dataType: "json",
                            success: function (msg) {
                                if (msg.success) {
                                    layer.msg("添加成功", {icon: 1, time: 1500});
                                    table.reload('table-teach');
                                } else {
                                    layer.msg("添加失败", {icon: 2});
                                }
                                layer.close(index);
                            }
                        });
                        return false;
                    });

                    //打开弹出层
                    function table_edit_form(title, name, w, h) {
                        $.ajax({
                            type: "post",
                            url: "${pageContext.request.contextPath}/getJSON",
                            data: {datatype: "curr&teacher"},
                            dataType: "json",
                            success: function (callback) {
                                const obj = $(name);
                                obj.find("[name=cur_name]").empty();
                                obj.find("[name=te_name]").empty();
                                $.each(callback.cur_name, function (i, val) {
                                    const options = "<option value=\"" + val + "\">" + val + "</option>";
                                    console.log(val);
                                    obj.find("[name=cur_name]").append(options);
                                });
                                $.each(callback.te_name, function (i, val) {
                                    const options = "<option value=\"" + val + "\">" + val + "</option>";
                                    obj.find("[name=te_name]").append(options);
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
                        }
                    });
                }
            });
        </script>
    </div>
</body>
</html>
