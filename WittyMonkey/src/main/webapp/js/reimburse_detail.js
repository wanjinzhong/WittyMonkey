/**
 * Created by neilw on 2017/4/19.
 */
var layer;
var form;
layui.use(["layer", "form"], function () {
    layer = layui.layer;
    form = layui.form();
});
function operate(type) {
    if (!validateNote($("#optNote"))) {
        return false;
    }
    var load = layer.load();
    var title;
    var hint;
    if (type == 1) {
        title = reimburse_approve_title;
        hint = reimburse_approve_hint;
    } else if (type == 2) {
        title = reimburse_reject_title;
        hint = reimburse_reject_hint;
    }
    $.ajax({
        url: "reimburseOperate.do",
        data: {"method": type, "note": $("#optNote").val()},
        dataType: "json",
        type: "POST",
        async: false,
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(messageOfValidateLength(message_note, 1024), $("#optNote"), {tips: 2});
                    break;
                case 401:
                    layer.msg(reimburse_not_exist, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 410:
                    layer.msg(reimburse_updated, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 422:
                    layer.msg(reimburse_has_approved + res["optUser"], {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 423:
                    layer.msg(reimburse_has_reject + res["optUser"], {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 200:
                    layer.msg(reimburse_reject_success, {
                        icon: 1, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 201:
                    layer.msg(reimburse_approve_success, {
                        icon: 1, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
            }
        }
    });
}
