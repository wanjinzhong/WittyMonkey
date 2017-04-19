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
                '<td>' + obj[i]['applyUser'] + '</td>' +
                '<td>' + formatDate(obj[i]['applyDatetime']) + '</td>' +
                '<td>' + obj[i]['money'] + '</td>';
                switch (obj[i]['status']) {
                    case 1:
                        html += '<td>' + reimburse_pending + '</td>';
                        break;
                    case 2:
                        html += '<td>' + reimburse_passed + '</td>';
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
    var from = $("#from").val();
    var to = $("#to").val();
    var condition = {"type": type, "from": from, "to": to};
    page("getReimburseByPage.do", undefined, condition);
}

function showAddReimburse() {
    layer.open({
        type: 2,
        area: ['780px', '420px'],
        maxmin: false,
        shade: 0.4,
        title: role_add_title,
        content: "toAddReimburse.do"
    });
}