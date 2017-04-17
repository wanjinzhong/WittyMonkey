/**
 * Created by neilw on 2017/4/17.
 */
var layer;
var laypage;
var form;
layui.use(['layer', 'laypage', 'form'], function () {
    layer = layui.layer;
    laypage = layui.laypage;
    form = layui.form();
    page("getFinanceByPage.do", undefined, {"type": 2});
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
                "<td>" + formatDate(obj[i].entryDatetime) + "</td>" +
                "<td>" + obj[i].financeType + "</td>" +
                "<td>" + (obj[i].income ? finance_type_in : finance_type_out) + "</td>" +
                "<td>" + obj[i].money + "</td>" +
                "<td>" + ((obj[i].note == undefined) ? "" : obj[i].note) + "</td>" +
                "<td>" + obj[i].entryUser + "</td>" +
                "<td>";
                html += "<i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editFinance(" + obj[i].id + ")'>&#xe642; " + btn_edit + "</i>" +
                    "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='deleteFinance(" + obj[i].id + ")'>&#xe640; " + btn_delete + "</i>";
            html += "</td>" +
                "</tr>";
        }
    }
    $("#dataTabel").html(html);
}

function changeType() {
    var type = $("#type").val();
    page("getFinanceByPage.do", undefined, {"type": type});
}

function showAddFinance() {
    layer.open({
        type: 2,
        area: ['700px', '350px'],
        maxmin: false,
        shade: 0.4,
        title: finance_type_add_title,
        content: "toAddFinance.do"
    });
}