/**
 * Created by neilw on 2017/3/25.
 */
var layer;
var laypage;
layui.use(['layer', 'laypage'],function () {
    layer = layui.layer;
    laypage = layui.laypage;
    page("getMaterialTypeByPage.do");
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
                "<td>" + obj[i].materielNum + "</td>" +
                "<td>" + (obj[i].note != undefined? obj[i].note : "") + "</td>" +
                "<td>" + obj[i].entryUser + "</td>" +
                "<td>" + formatDate(obj[i].entryDatetime) + "</td>" +
                "<td>";
                if (obj[i].editable) {
                    html += "<i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editMaterielType(" + obj[i].id + ")'>&#xe642; " + btn_edit + "</i>" +
                    "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='deleteMaterielType(" + obj[i].id + ")'>&#xe640; " + btn_delete + "</i>";
                }
            html += "</td></tr>";
        }
    }
    $("#dataTabel").html(html);
}
function deleteMaterielType(typeId) {
    layer.confirm(materiel_type_delete_hint, {icon: 7, title: materiel_type_delete_title},
        function (index) {
            var load = layer.load();
            $.ajax({
                url: "deleteMaterielType.do",
                data: {"typeId": typeId},
                dataType: "json",
                type: "POST",
                success: function (data) {
                    layer.close(load);
                    var result = eval("(" + data + ")");
                    switch (result.status) {
                        case 400:
                            layer.msg(materiel_type_not_exist, {
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
                            layer.msg(materiel_type_delete_success, {
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
function editMaterielType(typeId) {
    layer.open({
        type: 2,
        area: ['500px', '300px'],
        maxmin: false,
        shade: 0.4,
        title: materiel_type_edit_title,
        content: "toEditMaterielType.do?typeId=" + typeId
    });
}
function showAddMaterielType() {
    layer.open({
        type: 2,
        area: ['500px', '300px'],
        maxmin: false,
        shade: 0.4,
        title: materiel_type_add_title,
        content: "toAddMaterielType.do"
    });
}