/**
 * Created by neilw on 2017/4/12.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
});



function save() {
    if (!validateRoleName("add", $("#name"))) {
        return;
    }
    if (!validateNote($("#note"))) {
        return;
    }
    $.ajax({
        type: "get",
        url: "saveRole.do",
        data: $("#add_form").serialize(),
        dataType: "json",
        success: function (data) {
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(messageOfValidateNull(role_name), $("#name"), {tips: 2});
                    break;
                case 401:
                    layer.tips(messageOfValidateLength(role_name, 10), $("#name"), {tips: 2});
                    break;
                case 402:
                    layer.tips(role_exist, $("#name"), {tips: 2});
                    break;
                case 410:
                    layer.tips(messageOfValidateLength(message_note, 1024), $("#note"), {tips: 2});
                    break;
                case 420:
                    layer.msg(no_menu_chosen, {
                        icon: 2, time: 2000
                    });
                    break;
                case 421:
                    layer.msg(menu_duplicate + res["duplicateName"], {
                        icon: 2, time: 2000
                    });
                    break;
                case 200:
                    layer.msg(role_add_success, {
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