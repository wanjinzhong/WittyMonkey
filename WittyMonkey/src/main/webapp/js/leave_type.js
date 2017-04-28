/**
 * Created by Neil on 2017/2/20.
 */
var layer;
var laypage;
layui.use(['layer', 'laypage'], function () {
    layer = layui.layer;
    laypage = layui.laypage;
    page("getLeaveTypeByPage.do");
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
                "<td>" + obj[i]["name"] + "</td>" +
                "<td>" + obj[i]["deduct"] * 100 + "%</td>" +
                "<td>" + (obj[i]["note"] == undefined ? "" : obj[i].note) + "</td>" +
                "<td>" + obj[i]["entryUser"] + "</td>" +
                "<td>" + formatDate(obj[i]["entryDatetime"]) + "</td>" +
                "<td>" +
                "<i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editLeaveType(" + obj[i]["id"] + ")'>&#xe642; " + btn_edit + "</i>";
            if (obj[i]["deletable"]) {
                html += "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='deleteLeaveType(" + obj[i]["id"] + ")'>&#xe640; " + btn_delete + "</i>";
            }
            html += "</td></tr>";
        }
    }
    $("#dataTabel").html(html);
}
function deleteLeaveType(id) {
    layer.confirm(leave_type_delete_hint, {icon: 7, title: leave_type_delete_title},
        function (index) {
            var load = layer.load();
            $.ajax({
                url: "deleteLeaveType.do",
                data: {"id": id},
                dataType: "json",
                type: "POST",
                success: function (data) {
                    layer.close(load);
                    var result = eval("(" + data + ")");
                    switch (result.status) {
                        case 400:
                            layer.msg(leave_type_not_exist, {
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
                            layer.msg(leave_type_delete_success, {
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
function editLeaveType(id) {
    layer.open({
        type: 2,
        area: ['720px', '270px'],
        maxmin: false,
        shade: 0.4,
        title: leave_type_edit_title,
        content: "toEditLeaveType.do?id=" + id
    });
}
function showAddLeaveType() {
    layer.open({
        type: 2,
        area: ['720px', '270px'],
        maxmin: false,
        shade: 0.4,
        title: leave_type_add_title,
        content: "toAddLeaveType.do"
    });
}