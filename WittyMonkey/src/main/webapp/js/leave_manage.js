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
                '<td>' + obj[i]['applyUser'] + '</td>' +
                '<td>' + formatDay(obj[i]['from']) + " - " + formatDate(obj[i]['to']) + '</td>' +
                '<td>' + obj[i]['days'] + '</td>' +
                '<td>' + obj[i]['deduct'] + '</td>';
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
                html += '<td>' + (obj[i]['entryUser'] != undefined?obj[i]['entryUser']:"") + '</td>' +
                    '<td>' + (obj[i]['entryDatetime'] != undefined?formatDate(obj[i]['entryDatetime']):"") + '</td>';
                html += "<td><i class='showBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='showDetail(" + obj[i].id + ")'>&#xe60b; " + btn_detail + "</i></td>" +
                    '</tr>';
            }
    }
    $("#dataTabel").html(html);
}

function search() {
    var type = $("#type").val();
    var condition = {"type": type};
    page("getLeaveByPage.do", undefined, condition);
}

function showAddReimburse() {
    layer.open({
        type: 2,
        area: ['780px', '420px'],
        maxmin: false,
        shade: 0.4,
        title: reimburse_add,
        content: "toAddLeave.do"
    });
}

function showDetail(id) {
    layer.open({
        type: 2,
        area: ['780px', '450px'],
        maxmin: false,
        shade: 0.4,
        title: reimburse_detail,
        content: "toShowLeave.do?id=" + id
    });
}