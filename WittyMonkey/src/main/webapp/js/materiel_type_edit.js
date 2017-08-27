/**
 * Created by neilw on 2017/4/23.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
});

/**
 * 新增物料类型
 */
function save() {
    if (!validateMaterielTypeName($("#method").val(), $("#name"))) {
        return false;
    } if (!validateNote(note)) {
        return false;
    }
    var load = layer.load();
    $.ajax({
        type: "POST",
        url: "updateMaterielType.do",
        data: $("#add_form").serialize(),
        dataType: "json",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res.status) {
                case 400:
                    layer.tips(materiel_type_validate_name_empty, $("#name"), {tips: 2});
                    break;
                case 401:
                    layer.tips(messageOfValidateLength(null, 10), $("#name"), {tips: 2});
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
                    layer.msg(materiel_type_update_success, {
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