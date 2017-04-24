/**
 * Created by neilw on 2017/4/24.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
});
function save() {
    if (!validateBarcode("add", $("#barcode"))) {
        return false;
    }
    if (!validateLength($("#name"), materiel_name, 50)) {
        return false;
    }
    if (!validateLength($("#unit"), materiel_unit, 10)) {
        return false;
    }
    if (!validateNote($("#note"))) {
        return false;
    }
    var load = layer.load();
    $.ajax({
        url: "saveMateriel.do",
        data: $("#materiel_form").serialize(),
        dataType: "json",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(messageOfValidateNull(materiel_add_barcode), $("#barcode"), {tips: 2});
                    break;
                case 401:
                    layer.tips(messageOfValidateLength(materiel_add_barcode, 50), $("#barcode"), {tips: 2});
                    break;
                case 402:
                    layer.tips(materiel_barcode_exist, $("#barcode"), {tips: 2});
                    break;
                case 410:
                    layer.tips(messageOfValidateNull(materiel_name), $("#name"), {tips: 2});
                    break;
                case 411:
                    layer.tips(messageOfValidateLength(materiel_name, 50), $("#name"), {tips: 2});
                    break;
                case 420:
                    layer.tips(messageOfValidateNull(materiel_unit), $("#unit"), {tips: 2});
                    break;
                case 421:
                    layer.tips(messageOfValidateLength(materiel_unit, 10), $("#unit"), {tips: 2});
                    break;
                case 430:
                    layer.tips(meteriel_warn_error, $("#warnStock"), {tips: 2});
                    break;
                case 431:
                    layer.tips(materiel_warn_negative, $("#warnStock"), {tips: 2});
                    break;
                case 440:
                    layer.tips(meteriel_sell_error, $("#sellPrice"), {tips: 2});
                    break;
                case 441:
                    layer.tips(materiel_sell_negative, $("#sellPrice"), {tips: 2});
                    break;
                case 450:
                    layer.tips(messageOfValidateLength(message_note, 10), $("#note"), {tips: 2});
                    break;
                case 200:
                    layer.msg(materiel_add_success, {
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