/**
 * Created by neilw on 2017/3/9.
 */
var layer;
layui.use(['layer'], function () {
    layer = layui.layer;
});

function showRoom() {
    layer.open({
        title: false,
        content: "toChooseRoom.do?id=" + $("#checkin_id").val(),
        area: ['750px', '450px'],
        maxmin: false,
        scrollbar: false,
        type: 2,
    });
}

function calcDiff(){
    var to = $("#toPrice").val();
    var from = $("#fromPrice").val();
    var total = $("#total").val();
    $("#diff").val((Number(to) - Number(from)) * Number(total));
}
function change() {
    var toId = $("#toId").val();
    var checkinId = $("#checkin_id").val();
    var reason =  $("#reason").val()
    $.ajax({
        url: "changeRoom.do",
        data: {"toRoomId":toId, "checkinId":checkinId,"reason":reason},
        dataType: "json",
        type: "get",
        success: function (data) {
            var res = eval("(" + data + ")");
            switch (res["status"]){
                case 400: layer.msg(choose_room_not_exist, {icon: 2, time: 2000});break;
                case 410: layer.msg(choose_checkin_not_exist, {icon: 2, time: 2000});break;
                case 420: layer.tips(messageOfValidateLength(reason, 1024), $(".layui-textarea").val(), {type: 2});break;
                case 500: layer.msg(error_500, {icon: 2, time: 2000});break;
                case 200: layer.msg(change_success, {icon: 6, time:200}, function () {
                    parent.window.location.reload();
                    closeMe();
                });break;
            }
        }
    });
}