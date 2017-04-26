/**
 * Created by neilw on 2017/4/26.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
});

function validateLeaveTypeName(obj) {
    var name = $(obj).val();
    var method = $("#method").val();
    if (name == undefined || name.length <= 0) {
        layer.tips(messageOfValidateNull(leave_type_name), obj, {tips: 2});
        return false;
    } else if (name.length > 20) {
        layer.tips(messageOfValidateLength(leave_type_name, 20), obj, {tips: 2});
        return false;
    }
    $.ajax({
        url: "validateLeaveTypeName.do",
        data: {"method": method, "name": name},
        dataType: "JSON",
        type: "GET",
        success: function (data) {
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(messageOfValidateNull(leave_type_name), obj, {tips: 2});
                    break;
                case 401:
                    layer.tips(messageOfValidateLength(leave_type_name, 20), obj, {tips: 2});
                    break;
                case 201:
                    layer.tips(leave_type_name_exist, obj, {tips: 2});
                    break;
            }
        }
    });
}

function validateDeduct(obj) {
    var deduct = $(obj).val();
    if (deduct == undefined || deduct.length <= 0) {
        layer.tips(messageOfValidateNull(leave_type_deduct), obj, {tips: 2});
        return false;
    }
    if (deduct < 0 || deduct > 100) {
        layer.tips(leave_type_deduct_wrong, obj, {tips: 2});
        return false;
    }
    return true;
}

function save() {
    var method = $("#method").val();
    var url;
    var success_hint;
    if (method == "add") {
        url = "saveLeaveType.do";
        success_hint = leave_type_add_success;
    } else if (method == "update") {
        url = "updateLeaveType.do";
        success_hint = leave_type_edit_success;
    }
    var name = $("#name").val();
    if (name == undefined || name.length <= 0) {
        layer.tips(messageOfValidateNull(leave_type_name), $("#name"), {tips: 2});
        return false;
    } else if (name.length > 20) {
        layer.tips(messageOfValidateLength(leave_type_name, 20), $("#name"), {tips: 2});
        return false;
    }
    if (!validateDeduct($("#deduct"))) {
        return false;
    }
    if (!validateNote($("#note"))) {
        return false;
    }
    var load = layer.load();
    $.ajax({
        url: url,
        data: $("#leave_type_form").serialize(),
        dataType: "JSON",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(messageOfValidateNull(leave_type_name), $("#name"), {tips: 2});
                    break;
                case 401:
                    ayer.tips(messageOfValidateLength(leave_type_name, 20), $("#name"), {tips: 2});
                    break;
                case 402:
                    layer.tips(leave_type_name_exist, $("#name"), {tips: 2});
                    break;
                case 410:
                    layer.tips(messageOfValidateNull(leave_type_deduct), $("#deduct"), {tips: 2});
                    break;
                case 411:
                    layer.tips(leave_type_deduct_wrong, $("#deduct"), {tips: 2});
                    break;
                case 420:
                    layer.tips(messageOfValidateLength(message_note, 1024), $("#note"), {tips: 2});
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