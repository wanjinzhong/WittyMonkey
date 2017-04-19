/**
 * Created by neilw on 2017/4/19.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
    form.on("select(status)", function (data) {
        changeStatus();
    });
    changeStatus();
});

function changeStatus() {
    var status = $("#status").val();
    if (status == 1) {
        $("#optNote").val("");
        $("#optNote").attr("disabled", true);
    } else {
        $("#optNote").attr("disabled", false);
    }
}

function save() {
    var status = $("#status").val();
    if (!validateMoney($("#money"))){
        return false;
    }
    if (!validateNote($("#applyNote"))){
        return false;
    }
    if (status != 1 && !validateNote($("#optNote"))){
        return false
    }
    var load = layer.load();
    $.ajax({
        url: "saveReimburse.do",
        data: $("#add-form").serialize(),
        dataType: "json",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]){
                case 400:  layer.msg(money_error, {
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
                    layer.msg(messageOfValidateLength(opt_note, 1024), {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 200:
                    layer.msg(finance_add_success, {
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