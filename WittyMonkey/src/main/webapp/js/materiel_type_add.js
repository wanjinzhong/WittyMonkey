/**
 * Created by neilw on 2017/2/20.
 */
var layer;
layui.use('layer',function () {
    layer = layui.layer;
});


/**
 * 验证新增物料类型
 * @param inp
 */
function validateMaterielType(form) {
    var name = $(form).find("#name");
    var note = $(form).find("#note");
    if (!validateMaterielTypeName($(form).find("#method"), name)) {
        return false;
    } else if (!validateNote(note)) {
        return false;
    } else {
        return true;
    }
}
/**
 * 新增物料类型
 */
function save() {
    if (validateMaterielType($("#add_form"))) {
        $.ajax({
            type: "GET",
            url: "saveMaterielType.do",
            data: $("#add_form").serialize(),
            dataType: "json",
            success: function (data) {
                var res = eval("(" + data + ")");
                switch (res.status) {
                    case 400:
                        layer.tips(materiel_type_validate_name_empty, $("#name"), {tips: 2});
                        break;
                    case 401:
                        layer.tips(messageOfValidateLength(null, 50), $("#name"), {tips: 2});
                        break;
                    case 410:
                        layer.tips(messageOfValidateLength(null, 1024), $("#note"), {tips: 2});
                        break;
                    case 420:
                        layer.tips(materiel_type_validate_name_exist, $("#name"), {tips: 2});
                    case 500:
                        layer.msg(error_500, {
                            icon: 2, time: 2000
                        }, function () {
                            parent.location.reload();
                            closeMe();
                        });
                        break;
                    case 200 :
                        layer.msg(materiel_type_add_success, {
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
}