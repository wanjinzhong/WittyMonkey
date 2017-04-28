/**
 * Created by neilw on 2017/4/28.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
});

function operate(type) {
    if (!validateNote($("#optNote"))) {
        return false;
    }
    var load = layer.load();
    $.ajax({
        url: "leaveOperator.do",
        data: {"status": type, "optNote": $("#optNote").val()},
        dataType: "JSON",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]){
                case 400:
                    layer.msg(leave_not_exist, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 410:
                    layer.msg(leave_changed, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 420:
                    layer.tips(messageOfValidateLength(message_note), $("#optNote"), {tips: 2});
                    break;
                case 200:
                    layer.msg(leave_approve_success, {
                        icon: 1,
                        time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 201:
                    layer.msg(leave_reject_success, {
                        icon: 1,
                        time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
            }
        }
    });
}