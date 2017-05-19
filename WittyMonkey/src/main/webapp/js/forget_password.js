/**
 * Created by neilw on 2017/5/19.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
});
function validateStaffNo(obj) {
    var val = $(obj).val();
    if (val == undefined || val.length <= 0) {
        layer.tips(messageOfValidateNull(staff_no), $(obj), {tips: 2});
        return false;
    } else {
        return true;
    }
}
function changePassword() {
    if (!validateStaffNo($("#staffNo"))) {
        return false;
    }
    if (!validateIdCard($("#idcard"))) {
        return false;
    }
    if (!validatePassword($("#password"))) {
        return false;
    }
    if (!validateRepassword($("#rePassword"))) {
        return false;
    }
    var load = layer.load();
    $.ajax({
        url: "forgetPassword.do",
        type: "POST",
        data: $("#data_form").serialize(),
        dataType: "JSON",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res['status']){
                case 400:
                    layer.tips(staff_no_not_exist, $("#staffNo"), {tips: 2});
                    break;
                case 410:
                    layer.tips(regist_validate_common_idcard_wrong, $("#idcard"), {tips: 2});
                    break;
                case 420:
                    layer.tips(regist_password_not_same, $("#rePassword"), {tips: 2});
                    break;
                case 200:
                    layer.msg(password_change_success, {
                        icon: 1,
                        time: 2000
                    }, function () {
                        closeMe();
                    });
            }
        }
    });
}