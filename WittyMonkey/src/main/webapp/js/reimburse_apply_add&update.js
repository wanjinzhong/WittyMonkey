/**
 * Created by neilw on 2017/4/19.
 */
var layer;
layui.use(['layer'], function () {
    layer = layui.layer;
});

function save() {
    var method = $("#method").val();
    if (!validateMoney($("#money"))) {
        return false;
    }
    if (!validateNote($("#applyNote"))) {
        return false;
    }
    var success_hint;
    if (method == "add"){
        success_hint = reimburse_apply_add_success;
    } else if (method == "update"){
        success_hint = apply_edit_success;
    }
    var load = layer.load();
    $.ajax({
        url: "saveReimburseApply.do",
        data: $("#add-form").serialize(),
        dataType: "json",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.msg(money_error, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 401:
                    layer.msg(money_only_positive, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 410:
                    layer.msg(messageOfValidateLength(apply_note, 1024), {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 420:
                    layer.msg(apply_approved, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 421:
                    layer.msg(apply_rejected, {
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
                    layer.msg(success_hint, {
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