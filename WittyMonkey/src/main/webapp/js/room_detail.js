/**
 * Created by neilw on 2017/2/22.
 */
var form;
var layer;
layui.use(['form', 'layer'], function () {
    form = layui.form();
    layer = layui.layer;
});
function editRoom() {
    parent.layer.title(room_edit_title);
    $('#roomNum').removeAttr("disabled");
    $('#roomName').removeAttr("disabled");
    $('#singleBedNum').removeAttr("disabled");
    $('#doubleBedNum').removeAttr("disabled");
    $('#availableNum').removeAttr("disabled");
    $('#floor').removeAttr("disabled");
    $('#area').removeAttr("disabled");
    $('#price').removeAttr("disabled");
    $('#otherFacility').removeAttr("disabled");
    $('#note').removeAttr("disabled");
    $.ajax({
        url: "getFloor.do",
        dataType: "json",
        type: "get",
        success: function (data) {
            var res = eval("(" + data + ")");
            var html;
            html = '<select name="floorId" lay-verify="required" id="floorId">';
            for (var i in res) {
                html += '<option value="' + res[i]["id"] + '">' + res[i]["floorNo"] + '</option>';
            }
            html += '</select>';
            $(".select").html(html);
            form.render("select");
        }
    });
    var html = '<input type="button" class="layui-btn layui-btn-radius" value="' + btn_save + '" onclick="save(\'update\')"/>';
    $(".changeableBtn").html(html);
}

function deleteRoom() {
    var id = $("#id").val();
    layer.confirm(room_delete_hint, {icon: 7, title: room_delete_title},
        function (index) {
            $.ajax({
                url: "deleteRoom.do",
                data: {"id": id},
                dataType: "json",
                type: "get",
                success: function (data) {
                    var result = eval("(" + data + ")");
                    switch (result.status) {
                        case 400:
                            layer.msg(room_delete_not_exist, {
                                icon: 2, time: 2000
                            }, function () {
                                closeMe();
                            });
                            break;
                        case 410:
                            layer.msg(room_delete_book, {
                                icon: 2, time: 2000
                            }, function () {
                                closeMe();
                            });
                            break;
                        case 411:
                            layer.msg(room_delete_checkin, {
                                icon: 2, time: 2000
                            }, function () {
                                closeMe();
                            });
                            break;
                        case 500:
                            layer.msg(error_500, {
                                icon: 2, time: 2000
                            }, function () {
                                parent.parent.location.reload();
                                closeMe();
                            });
                            break;
                        case 200:
                            layer.msg(room_delete_success, {
                                icon: 1,
                                time: 2000
                            }, function () {
                                parent.parent.location.reload();
                                closeMe();
                            });
                            break;
                    }
                }
            });
            layer.close(index);
        });
}