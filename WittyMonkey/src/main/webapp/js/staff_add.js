/**
 * Created by neilw on 2017/4/11.
 */
var layer;
var form;
layui.use(['layer', 'form'], function (){
    layer = layui.layer;
    form = layui.form();
});
function save(type) {
    if (!validateRealName($("#realName"))) {
        return false;
    }
    if (!validateIdCard($("#idcard"))) {
        return false;
    }
    if (!validateTel($("#tel"))) {
        return false;
    }
    if (!validateEmailFormat($("#email"))) {
        return false;
    }
    var url;
    if (type == "save") {
        url = "saveStaff.do";
    } else if (type == "update") {
        url = "updateStaff.do";
    }
    $.ajax({
        url: url,
        data: $("#add_form").serialize(),
        dataType: "JSON",
        type: "POST",
        success: function (data) {
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(messageOfValidateNull(regist_real_name), $("#realName"), {tips: 2});
                    break;
                case 401:
                    layer.tips(messageOfValidateLength(regist_real_name, 20), $("#realName"), {tips: 2});
                    break;
                case 410:
                    layer.tips(regist_validate_common_idcard_wrong, $("#idcard"), {tips: 2});
                    break;
                case 420:
                    layer.tips(messageOfValidateLength(message_tel, 15), $("#tel"), {tips: 2});
                    break;
                case 430:
                    layer.tips(messageOfValidateLength(regist_email, 50), $("#email"), {tips: 2});
                    break;
                case 200:
                    if (type == "save") {
                        var tips = staff_no + ": " + res["staffNo"] + "<br/>" +
                            staff_initpassword + ": " + res["initPwd"];
                        layer.open({
                            title: staff_add_success,
                            content: tips,
                            yes: function () {
                                parent.location.reload();
                                closeMe();
                            },
                            cancel: function () {
                                parent.location.reload();
                                closeMe();
                            }
                        });
                    } else if (type == "update"){
                        layer.msg(staff_edit_success, {
                            icon: 1,
                            time: 2000
                        }, function () {
                            parent.location.reload();
                            closeMe();
                        });
                        break;
                    }
                    break;
            }
        }
    });
}