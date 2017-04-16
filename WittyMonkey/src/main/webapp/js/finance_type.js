/**
 * Created by neilw on 2017/4/14.
 */
var layer;
var laypage;
var form;
layui.use(['layer', 'laypage', 'form'], function () {
    layer = layui.layer;
    laypage = layui.laypage;
    form = layui.form();
    page("getFinanceTypeByPage.do", undefined, {"type": 2});
    form.on('select(type)', function (data) {
        changeType(data.value);
    });
});
function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="6">' + no_data + '</td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            html += "<tr class='text-c'>" +
                "<td>" + obj[i].name + "</td>" +
                "<td>" + (obj[i].income ? finance_type_in : finance_type_out) + "</td>" +
                "<td>" + ((obj[i].note == undefined) ? "" : obj[i].note) + "</td>" +
                "<td>" + obj[i].entryUser + "</td>" +
                "<td>" + formatDate(obj[i].entryDatetime) + "</td>" +
                "<td>";
            if (obj[i].editable) {
                html += "<i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editFinanceType(" + obj[i].id + ")'>&#xe642; " + btn_edit + "</i>" +
                    "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='deleteFinanceType(" + obj[i].id + ")'>&#xe640; " + btn_delete + "</i>";
            }
            html += "</td>" +
                "</tr>";
        }
    }
    $("#dataTabel").html(html);
}

function changeType() {
    var type = $("#type").val();
    page("getFinanceTypeByPage.do", undefined, {"type": type});
}

function showAddFinanceType() {
    layer.open({
        type: 2,
        area: ['500px', '350px'],
        maxmin: false,
        shade: 0.4,
        title: finance_type_add_title,
        content: "toAddFinanceType.do"
    });
}

function editFinanceType(id) {
    layer.open({
        type: 2,
        area: ['500px', '350px'],
        maxmin: false,
        shade: 0.4,
        title: finance_type_edit_title,
        content: "toEditFinanceType.do?id=" + id
    });
}

function deleteFinanceType(id) {
    layer.confirm(finance_type_delete_hint, {icon: 7, title: finance_type_delete_title},
        function (index) {
            var load = layer.load();
            $.ajax({
                url: "deleteFinanceType.do",
                data: {"id": id},
                dataType: "json",
                type: "POST",
                success: function (data) {
                    layer.close(load);
                    var result = eval("(" + data + ")");
                    switch (result.status) {
                        case 400:
                            layer.msg(finance_type_delete_not_exist, {
                                icon: 2, time: 2000
                            }, function () {
                                parent.location.reload();
                                closeMe();
                            });
                            break;
                        case 500:
                            layer.msg(error_500, {
                                icon: 2, time: 2000
                            }, function () {
                                parent.location.reload();
                                closeMe();
                            });
                            break;
                        case 200:
                            layer.msg(finance_type_delete_success, {
                                icon: 6,
                                time: 2000
                            }, function () {
                                window.location.reload();
                                closeMe();
                            });
                            break;
                    }
                }
            });
            layer.close(index);
        });
}