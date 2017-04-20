/**
 * Created by neilw on 2017/4/20.
 */
var layer;
var laypage;
var form;
layui.use(['layer','laypage', 'form'], function () {
    layer = layui.layer;
    laypage = layui.laypage;
    form = layui.form();
    form.on("select(type)", function (data) {
        changeType();
    });
    changeType();
    page("getSalaryByPage.do", undefined, {"type": 1});
});


function changeType() {
    var type = $("#type").val();
    var html = "";
    switch (Number(type)) {
        case 1:
            html += '<input class="layui-input" type="number" id="from" style="display: inline-block;"/>' +
                ' - ' +
                '<input class="layui-input" type="number" id="to" style="display: inline-block;"/>';
            break;
        case 2:
            html += '<input class="layui-input" id="name" style="display: inline-block;"/>';
            break;
        case 3:
            html += '<input class="layui-input" id="staffNo" style="display: inline-block;"/>'
            break;
    }
    $("#searchContent").html(html);
}

function search() {
    var type = $("#type").val();
    var condition;
    switch (Number(type)) {
        case 1:
            condition = {"type": type, "from": $("#from").val(), "to": $("#to").val()};
            break;
        case 2:
            condition = {"type": type, "name": $("#name").val()};
            break;
        case 3:
            condition = {"type": type, "staffNo": $("#staffNo").val()};
            break;
    }
    page("getSalaryByPage.do", undefined, condition);
}

function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="7">' + no_data + '</td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            html += "<tr class='text-c'>" +
                "<td>" + obj[i]["staffNo"] + "</td>" +
                "<td>" + obj[i]["staff"] + "</td>" +
                "<td>" + obj[i]["money"] + "</td>" +
                "<td>" + formatDay(obj[i]["startDate"]) + "</td>" +
                "<td>" + obj[i]["entryUser"] + "</td>" +
                "<td>" + formatDate(obj[i]["entryDatetime"]) + "</td>" +
                "<td>" +
                "<i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='changeSalary(" + obj[i].id + ")'>&#xe631; " + btn_change + "</i>" +
                "<i class='showBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='salaryHistory(" + obj[i].id + ")'>&#xe62c; " + btn_history + "</i>" +
                "</td>";
        }
    }
    $("#dataTabel").html(html);
}

function changeSalary(id) {
    layer.open({
        title: change_title,
        content: "toSalaryChangeRecord.do?id=" + id,
        area: ['900px', '550px'],
        maxmin: false,
        shade: 0.4,
        scrollbar: false,
        type: 2
    });
}

function salaryHistory(id) {

}