/**
 * Created by Neil on 2017/2/20.
 */
var layer;
var laypage;
layui.use(['layer', 'laypage'], function () {
    layer = layui.layer;
    laypage = layui.laypage;
    page("getRoleByPage.do");
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
                "<td>" + obj[i].menus + "</td>" +
                "<td>" + ((obj[i].note == undefined) ? "" : obj[i].note) + "</td>" +
                "<td>" + obj[i].users + "</td>" +
                "<td>" + obj[i].entryUser + "</td>" +
                "<td>" + formatDate(obj[i].entryDatetime) + "</td>" +
                "<td>" +
                "<i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editFloor(" + obj[i].id + ")'>&#xe642; " + btn_edit + "</i>" +
                "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='deleteFloor(" + obj[i].id + ")'>&#xe640; " + btn_delete + "</i>" +
                "</td>" +
                "</tr>";
        }
    }
    $("#dataTabel").html(html);
}
function deleteRole(id) {
    layer.confirm(role_delete_hint, {icon: 7, title: role_delete_title},
        function (index) {
            $.ajax({
                url: "deleteRole.do",
                data: {"id": id},
                dataType: "json",
                type: "GET",
                success: function (data) {
                    var result = eval("(" + data + ")");
                    switch (result.status) {
                        case 400:
                            layer.msg(role_delete_not_exist, {
                                icon: 2, time: 2000
                            }, function () {
                                parent.location.reload();
                                closeMe();
                            });
                            break;
                        case 500:
                            layer.msg(error_500, {
                                icon: 2, time: 2000
                            }, function () {
                                parent.location.reload();
                                closeMe();
                            });
                            break;
                        case 200:
                            layer.msg(role_delete_success, {
                                icon: 6,
                                time: 2000
                            }, function () {
                                window.location.reload();
                                closeMe();
                            });
                            break;
                    }
                }
            });
            layer.close(index);
        });
}
function editFloor(id) {
    layer.open({
        type: 2,
        area: ['400px', '280px'],
        maxmin: false,
        shade: 0.4,
        title: role_edit_title,
        content: "toEditRole.do?id=" + id
    });
}
function showAddRole() {
    layer.open({
        type: 2,
        area: ['700px', '450px'],
        maxmin: false,
        shade: 0.4,
        title: role_add_title,
        content: "toAddRole.do"
    });
}

