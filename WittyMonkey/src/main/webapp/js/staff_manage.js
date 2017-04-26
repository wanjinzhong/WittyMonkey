/**
 * Created by neilw on 2017/4/7.
 */
var layer;
var laypage;
var form;
layui.use(['layer', 'laypage', 'form'], function () {
    layer = layui.layer;
    laypage = layui.laypage;
    form = layui.form();
    page("getStaffByPage.do", undefined, {"type": 0});
    form.on('select(type)', function (data) {
        changeType(data.value);
    });
});
function changeType() {
    var type = $("#type").val();
    page("getStaffByPage.do", undefined, {"type": type});
}
function refreshTable(obj) {
    var head = "";
    var html = "";
    var type = $("#type").val();
    if (type == 0) {
        head = '<tr>' +
            '<th width="50px">' + staff_no + '</th>' +
            '<th width="100px">' + staff_real_name + '</th>' +
            '<th>' + staff_roles + '</th>' +
            '<th width="130px">' + staff_induction_date + '</th>' +
            '<th width="130px">' + staff_tel + '</th>' +
            '<th width="150px">' + staff_email + '</th>' +
            '<th width="150px">' + operation + '</th>' +
            '</tr>';
    } else if (type == 1) {
        head = '<tr>' +
            '<th width="50px">' + staff_no + '</th>' +
            '<th width="100px">' + staff_real_name + '</th>' +
            '<th width="130px">' + staff_induction_date + '</th>' +
            '<th width="130px">' + staff_dimission_date + '</th>' +
            '<th>' + staff_dimission_note + '</th>' +
            '</tr>';
    }
    if (obj.length == 0) {
        html = '<tr class="text-c">';
        if (type == 0) {
            html += '<td colspan="7">';
        } else if (type == 1) {
            html += '<td colspan="6">';
        }
        html += no_data + '</td></tr>"';
    } else {
        if (type == 0) {
            for (var i in obj) {
                html += "<tr class='text-c'>" +
                    "<td>" + obj[i].staffNo + "</td>" +
                    "<td>" + obj[i].realName + "</td>" +
                    "<td>" + obj[i].roles + "</td>" +
                    "<td>" + formatDate(obj[i].registDate) + "</td>" +
                    "<td>";
                if (obj[i].tel != undefined) {
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
        } else if (type == 1) {
            for (var i in obj) {
                html += "<tr class='text-c'>" +
                    "<td>" + obj[i].staffNo + "</td>" +
                    "<td>" + obj[i].realName + "</td>" +
                    "<td>" + formatDate(obj[i].registDate) + "</td>" +
                    "<td>" + formatDate(obj[i].dimissionDate) + "</td>" +
                    "<td>";
                if (obj[i].dimissionNote != undefined) {
                    html += obj[i].dimissionNote;
                }
            }
        }
    }
    $("#tableHead").html(head);
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
        area: ['720px', '400px'],
        maxmin: false,
        shade: 0.4,
        title: staff_edit,
        content: "toEditStaff.do?id=" + id
    });
}
function showAddStaff() {
    layer.open({
        type: 2,
        area: ['720px', '400px'],
        maxmin: false,
        shade: 0.4,
        title: staff_add,
        content: "toAddStaff.do"
    });
}
