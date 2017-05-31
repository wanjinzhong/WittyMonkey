/**
 * Created by neilw on 2017/5/31.
 */
/**
 * Created by neilw on 2017/4/20.
 */
var laypage;
var layer;
layui.use(['laypage', 'layer'], function () {
    laypage = layui.laypage;
    layer = layui.layer;
    page("getSalaryRecordByPage.do");
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
                "<td>" + obj[i]["money"] + "</td>" +
                "<td>" + formatDay(obj[i]["startDate"]) + "</td>" +
                "<td>" + obj[i]["note"] + "</td>" +
                "<td>" + obj[i]["entryUser"] + "</td>" +
                "<td>" + formatDate(obj[i]["entryDatetime"]) + "</td>" +
                "</tr>";
        }
    }
    $("#dataTable").html(html);
}



function showSalaryHistory(id){
    layer.open({
        title: salary_history,
        content: "toSalaryHistory.do?id=" + id,
        area: ['850px', '520px'],
        maxmin: false,
        shade: 0.4,
        scrollbar: false,
        type: 2
    });
}