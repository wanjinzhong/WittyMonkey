/**
 * Created by neilw on 2017/4/14.
 */
var layer;
var layform;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    layform = layui.form();
});

function update() {
    if (!validateFinanceTypeName("update", $("#name"))) {
        return;
    }
    if (!validateNote($("#note"))) {
        return;
    }
    $.ajax({
        type: "POST",
        url: "updateFinanceType.do",
        data: $("#add_form").serialize(),
        dataType: "json",
        success: function (data) {
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(messageOfValidateNull(finance_type_name), $("#name"), {tips: 2});
                    break;
                case 401:
                    layer.tips(messageOfValidateLength(finance_type_name, 10), $("#name"), {tips: 2});
                    break;
                case 402:
                    layer.tips(finance_type_validate_name_exist, $("#name"), {tips: 2});
                    break;
                case 410:
                    layer.tips(messageOfValidateLength(message_note, 1024), $("#note"), {tips: 2});
                    break;
                case 420:
                    layer.tips(finance_type_edit_not_exist, $("#note"), {tips: 2});
                    break;
                case 420:
                    layer.tips(finance_type_can_not_edit, $("#note"), {tips: 2});
                    break;
                case 500:
                    layer.msg(error_500, {
                        icon: 2, time: 2000
                    });
                    break;
                case 200:
                    layer.msg(finance_type_edit_success, {
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