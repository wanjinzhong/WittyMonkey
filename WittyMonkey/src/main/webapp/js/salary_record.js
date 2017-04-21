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
                "<td>" +
                "<i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editSalaryRecord(" + obj[i]["id"] + ")'>&#xe642; " + btn_edit + "</i>" +
                "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='deleteSalaryRecord(" + obj[i]["id"] + ")'>&#xe640; " + btn_delete + "</i>" +
                "</td>" +
                "</tr>";
        }
    }
    $("#dataTable").html(html);
}
function editSalaryRecord(id){
    window.location.href="toEditSalary.do?salaryId=" + $("#salaryId").val() + "&recordId=" + id;
}

function deleteSalaryRecord(id) {
    layer.confirm(salary_delete_hint, {icon: 3, title: salary_delete_title}, function (index) {
        var load = layer.load();
        $.ajax({
            url: "deleteSalaryRecord.do",
            data: {"id": id},
            dataType: "json",
            type: "POST",
            success: function (data) {
                layer.close(load);
                var res = eval("(" + data + ")");
                switch (res["status"]){
                    case 400:
                        layer.msg(salary_not_exist, {
                            icon: 2, time: 2000
                        }, function () {
                            location.reload();
                            closeMe();
                        });
                        break;
                    case 200:
                        layer.msg(salary_delete_success,
                            {icon: 1, time: 2000},
                            function () {
                                location.reload();
                            });
                        break;
                }
            }
        });
        layer.close(index);
    });
}

function showAddSalaryRecord(id){
    window.location.href="toSalaryAdd.do?id=" + id;
}