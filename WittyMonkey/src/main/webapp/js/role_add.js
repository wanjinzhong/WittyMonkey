/**
 * Created by neilw on 2017/4/12.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
});

/**
 * 验证角色名
 * @param type
 * @param inp
 * @returns {*}
 */
function validateRoleName(type, inp) {
    var roleName = $(inp).val();
    if (roleName == undefined || roleName.trim().length <= 0) {
        layer.tips(messageOfValidateNull(role_name), inp, {tips: 2});
        return false;
    }
    if (roleName.trim().length > 50) {
        layer.tips(messageOfValidateLength(role_name, 50), inp, {tips: 2});
        return false;
    }
    var funcRes;
    $.ajax({
        type: "get",
        url: "validateRoleName.do",
        data: {"type": type, "roleName": roleName},
        dataType: "json",
        async: false,
        success: function (data) {
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(messageOfValidateNull(role_name), inp, {tips: 2});
                    funcRes = false;
                    break;
                case 401:
                    layer.tips(messageOfValidateLength(role_name, 50), inp, {tips: 2});
                    funcRes = false;
                    break;
                case 200:
                    layer.tips(role_exist, inp, {tips: 2});
                    funcRes = false;
                    break;
                case 201:
                    funcRes = true;
                    break;
            }
        }
    });
    return funcRes;
}