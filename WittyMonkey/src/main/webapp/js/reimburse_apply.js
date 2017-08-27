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
    search();
});
$(document).ready(function () {
    $('#from').dateRangePicker({
        language: $("#lang").val(),
        showShortcuts: false,
        singleMonth: true,
        autoClose: true,
        singleDate: true,
        showShortcuts: false,
        setValue: function (s) {
            $(this).val(s);
        }
    });
    $('#to').dateRangePicker({
        language: $("#lang").val(),
        showShortcuts: false,
        singleMonth: true,
        autoClose: true,
        singleDate: true,
        showShortcuts: false,
        setValue: function (s) {
            $(this).val(s);
        }
    });
});
function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="7">' + no_data + '</td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            html += '<tr class="text-c">' +
                '<td>' + formatDate(obj[i]['applyDatetime']) + '</td>' +
                '<td>' + obj[i]['money'] + '</td>';
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
                html += "<td><i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editReimburseApply(" + obj[i]["id"] + ")'>&#xe642; " + btn_edit + "</i>" +
                    "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='deleteReimburseApply(" + obj[i]["id"] + ")'>&#xe640; " + btn_delete + "</i></td>";
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
    var from = $("#from").val();
    var to = $("#to").val();
    var condition = {"type": type, "from": from, "to": to, "justMe": true};
    page("getReimburseByPage.do", undefined, condition);
}

function showAddReimburse() {
    layer.open({
        type: 2,
        area: ['550px', '280px'],
        maxmin: false,
        shade: 0.4,
        title: reimburse_add,
        content: "toAddReimburseApply.do"
    });
}

function showDetail(id) {
    layer.open({
        type: 2,
        area: ['650px', '450px'],
        maxmin: false,
        shade: 0.4,
        title: reimburse_detail,
        content: "toShowReimburseApply.do?id=" + id
    });
}

function editReimburseApply(id) {
    layer.open({
        type: 2,
        area: ['550px', '280px'],
        maxmin: false,
        shade: 0.4,
        title: reimburse_add,
        content: "toEditReimburseApply.do?id=" + id
    });
}

function deleteReimburseApply(id) {
    layer.confirm(apply_delete_hint, {icon: 7, title: apply_delete_title},
        function (index) {
            var load = layer.load();
            $.ajax({
                url: "deleteReimburseApply.do",
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
