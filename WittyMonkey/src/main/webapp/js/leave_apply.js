/**
 * Created by neilw on 2017/4/18.
 */
var layer;
var form;
var laypage;
layui.use(['layer', 'form', 'laypage'], function () {
    layer = layui.layer;
    form = layui.form();
    laypage = layui.laypage;
    form.on("select(type)", function (data) {
        search();
    });
    search();
});
function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="8">' + no_data + '</td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            html += '<tr class="text-c">' +
                '<td>' + formatDay(obj[i]['from']) + "<br/>" + formatDay(obj[i]['to']) + '</td>' +
                '<td>' + obj[i]['days'] + '</td>' +
                '<td>' + obj[i]['leaveType'] + '</td>';
            switch (obj[i]['status']) {
                case 1:
                    html += '<td>' + reimburse_pending + '</td>';
                    break;
                case 2:
                    html += '<td>' + reimburse_approved + '</td>';
                    break;
                case 3:
                    html += '<td>' + reimburse_rejected + '</td>';
                    break;
                default:
                    html += '<td>' + reimburse_unknown + '</td>';
            }
            html += '<td>' + (obj[i]['entryUser'] != undefined ? obj[i]['entryUser'] : "") + '</td>' +
                '<td>' + (obj[i]['entryDatetime'] != undefined ? formatDate(obj[i]['entryDatetime']) : "") + '</td>';
            if (obj[i]["status"] == 1) {
                html += "<td><i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editLeaveApply(" + obj[i]["id"] + ")'>&#xe642; " + btn_edit + "</i>" +
                    "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='deleteLeaveApply(" + obj[i]["id"] + ")'>&#xe640; " + btn_delete + "</i></td>";
            } else {
                html += "<td><i class='showBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='showDetail(" + obj[i]["id"] + ")'>&#xe60b; " + btn_detail + "</i></td>";
            }
            html += "</tr>";
        }
    }
    $("#dataTabel").html(html);
}

function search() {
    var type = $("#type").val();
    var condition = {"type": type, "justMe": true};
    page("getLeaveByPage.do", undefined, condition);
}

function showAddLeaveApply() {
    layer.open({
        type: 2,
        area: ['780px', '330px'],
        maxmin: false,
        shade: 0.4,
        title: leave_apply_title,
        content: "toAddLeaveApply.do"
    });
}

function editLeaveApply(id) {
    layer.open({
        type: 2,
        area: ['780px', '330px'],
        maxmin: false,
        shade: 0.4,
        title: leave_apply_edit_title,
        content: "toEditLeaveApply.do?id=" + id
    });
}

function deleteLeaveApply(id) {
    layer.confirm(apply_delete_hint, {icon: 7, title: apply_delete_title},
        function (index) {
            var load = layer.load();
            $.ajax({
                url: "deleteLeaveApply.do",
                data: {"id": id},
                dataType: "JSON",
                type: "POST",
                success: function (data) {
                    layer.close(load);
                    var res = eval("(" + data + ")");
                    switch (res["status"]) {
                        case 400:
                            layer.msg(leave_not_exist, {
                                icon: 2, time: 2000
                            }, function () {
                                location.reload();
                                closeMe();
                            });
                            break;
                        case 410:
                            layer.msg(apply_approved, {
                                icon: 2, time: 2000
                            }, function () {
                                location.reload();
                                closeMe();
                            });
                            break;
                        case 411:
                            layer.msg(apply_rejected, {
                                icon: 2, time: 2000
                            }, function () {
                                location.reload();
                                closeMe();
                            });
                            break;
                        case 500:
                            layer.msg(error_500, {
                                icon: 2, time: 2000
                            }, function () {
                                location.reload();
                                closeMe();
                            });
                            break;
                        case 200:
                            layer.msg(apply_delete_success, {
                                icon: 1,
                                time: 2000
                            }, function () {
                                location.reload();
                                closeMe();
                            });
                            break;
                    }

                }
            });
        });
}