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
                "<td>" +
                "<i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editFinanceType(" + obj[i].id + ")'>&#xe642; " + btn_edit + "</i>" +
                "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='deleteFinanceType(" + obj[i].id + ")'>&#xe640; " + btn_delete + "</i>" +
                "</td>" +
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

function editFinanceType(id){

}

function deleteFinanceType(id){

}