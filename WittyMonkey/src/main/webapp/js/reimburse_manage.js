/**
 * Created by neilw on 2017/4/18.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
    search();
});

function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="6">' + no_data + '</td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            html += '<tr class="text-c">' +
                '<td>' + obj[i]['applyUser'] + '</td>' +
                '<td>' + formatDate(obj[i]['applyDatetime']) + '</td>' +
                '<td>' + obj[i]['money'] + '</td>' +
                '<td>' + obj[i]['status'] + '</td>' +
                '<td>' + obj[i]['entryUser'] + '</td>' +
                '<td>' + obj[i]['entryDatetime'] + '</td>' +
                    // todo
                '</tr>';
        }
    }
}

function search() {
    var type = $("#type").val();
    var from = $("#from").val();
    var to = $("#to").val();
    var condition = {"type": type, "from": from, "to": to};
    page("getReimburseByPage.do", undefined, condition);
}