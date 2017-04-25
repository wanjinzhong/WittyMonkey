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
    var method = $("#method").val();
    if (!validateBarcode(method, $("#barcode"))) {
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
    var url;
    var success_hint;
    if (method == "add"){
        url = "saveMateriel.do";
        success_hint = materiel_add_success;
    } else if (method == "update"){
        url = "updateMateriel.do";
        success_hint = materiel_update_success;
    }
    $.ajax({
        url: url,
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
function edit(){
    $("#barcode").attr("disabled", false);
    $("#name").attr("disabled", false);
    $("#unit").attr("disabled", false);
    $("#typeId").attr("disabled", false);
    $("#warnStock").attr("disabled", false);
    $("#sellPrice").attr("disabled", false);
    $("#note").attr("disabled", false);
    $('#updateBtn').attr('type', "button");
    form.render("select");
}