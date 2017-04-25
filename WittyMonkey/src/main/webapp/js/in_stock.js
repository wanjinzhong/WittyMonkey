/**
 * Created by neilw on 2017/4/25.
 */
var layer;
layui.use("layer", function () {
    layer = layui.layer;
});

function validateBarcode() {
    var barcode = $("#barcode").val();
    if (barcode.length <= 0){
        layer.tips(messageOfValidateNull(materiel_add_barcode), $("#barcode"), {tips: 2});
        return false;
    }
    $.ajax({
        url: "getNameByBarCode.do",
        data: {"barcode": barcode},
        dataType: "JSON",
        type: "GET",
        success: function (data) {
            var res = eval("(" + data + ")");
            if (res["status"] == 400){
                layer.tips(instock_barcode_not_exist, $("#barcode"), {tips: 2});
            } else if (res["status"] == 200){
                $("#name").val(res["name"]);
            }
        }
    });
}

function validateName(){
    var name = $("#name").val();
    if (name.length <= 0){
        layer.tips(messageOfValidateNull(materiel_name), $("#name"), {tips: 2});
        return false;
    }
    $.ajax({
        url: "getBarcodeByName.do",
        data: {"name": name},
        dataType: "JSON",
        type: "GET",
        success: function (data) {
            var res = eval("(" + data + ")");
            if (res["status"] == 400){
                layer.tips(instock_name_not_exist, $("#name"), {tips: 2});
            } else if (res["status"] == 200){
                $("#barcode").val(res["barcode"]);
            }
        }
    });
}

function save() {
    if (!validateNote($("#note"))){
        return false;
    }
    var load = layer.load();
    $.ajax({
        url: "saveInStock.do",
        data: $("#instock_form").serialize(),
        dataType: "JSON",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]){
                case 400:
                    layer.msg(instock_materiel_not_exist, {
                        icon: 2, time: 2000
                    });
                    break;
                case 410:
                    layer.tips(messageOfValidateNull(instock_price), $("#price"), {tips: 2});
                    break;
                case 411:
                    layer.tips(instock_price_wrong, $("#price"), {tips: 2});
                    break;
                case 420:
                    layer.tips(messageOfValidateNull(instock_qty), $("#qty"), {tips: 2});
                    break;
                case 421:
                    layer.tips(instock_qty_wrong, $("#qty"), {tips: 2});
                    break;
                case 430:
                    layer.tips(messageOfValidateNull(instock_pay), $("#pay"), {tips: 2});
                    break;
                case 431:
                    layer.tips(instock_pay_wrong, $("#pay"), {tips: 2});
                    break;
                case 440:
                    layer.tips(messageOfValidateLength(message_note, 1024), $("#note"), {tips: 2});
                    break;
                case 200:
                    layer.msg(instock_success, {
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

function calc() {
    var price = $("#price").val();
    var qty = $("#qty").val();
    if (price.length <= 0 || qty.length <= 0){
        $("#pay").val(0);
    } else {
        $("#pay").val(Number(price) * Number(qty));
    }
}