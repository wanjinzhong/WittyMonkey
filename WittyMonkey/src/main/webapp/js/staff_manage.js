/**
 * Created by neilw on 2017/4/7.
 */
var layer;
var laypage;
layui.use(['layer', 'laypage'], function () {
    layer = layui.layer;
    laypage = layui.laypage;
    page("getStaffByPage.do");
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
                "<td>" + obj[i].staffNo + "</td>" +
                "<td>" + obj[i].realName + "</td>" +
                "<td>" + obj[i].roles + "</td>" +
                "<td>" + formatDate(obj[i].registDate) + "</td>" +
                "<td>";
            if (obj[i].tel != undefined){
                html += obj[i].tel;
            }
            html += "</td>" +
                "<td>";
            if (obj[i].email != undefined) {
                html += obj[i].email;
            }
            html += "</td>" +
                "<td>" +
                "<i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editStaff(" + obj[i].id + ")'>&#xe642; " + btn_edit + "</i>" +
                "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='dimissionStaff(" + obj[i].id + ")'>&#xe640; " + btn_dimission + "</i>" +
                "</td>" +
                "</tr>";
        }
    }
    $("#dataTabel").html(html);
}
function dimissionStaff(id) {
    layer.open({
        type: 2,
        area: ['700px', '350px'],
        maxmin: false,
        shade: 0.4,
        title: dimission_title,
        content: "toDimission.do?id=" + id
    });
}
function editStaff(id) {
    layer.open({
        type: 2,
        area: ['700px', '450px'],
        maxmin: false,
        shade: 0.4,
        title: floor_manage_edit_title,
        content: "toEditStaff.do?id=" + id
    });
}
function showAddStaff() {
    layer.open({
        type: 2,
        area: ['700px', '450px'],
        maxmin: false,
        shade: 0.4,
        title: staff_add,
        content: "toAddStaff.do"
    });
}
