/**
 * Created by neilw on 2017/4/25.
 */
var layer;
var form;
layui.use(["layer","form"], function () {
    layer = layui.layer;
    form = layui.form();
    form.on('select(type)', function (data) {
        var type = $("#type").val();
        if (type == 1){
            $("#sellPrice").attr("disabled", false);
            $("#pay").attr("disabled", false);
        } else {
            $("#sellPrice").val(0);
            $("#pay").val(0);
            $("#sellPrice").attr("disabled", true);
            $("#pay").attr("disabled", true);
        }
    })
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
                $("#name").val(res["materiel"]["name"]);
                var method = $("#method").val();
                if (method == "out"){
                    var type = $("#type").val();
                    if (type == 1) {
                        $("#sellPrice").val(res["materiel"]["sellPrice"]);
                    }
                }
            }
            calc();
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
                $("#barcode").val(res["materiel"]["barcode"]);
            }
        }
    });
}

function save() {
    if (!validateNote($("#note"))){
        return false;
    }
    var method = $("#method").val();
    var url;
    var data;
    var success_hint;
    if (method == "out"){
        url = "saveOutStock.do";
        data = $("#outstock_form").serialize();
        success_hint = outstock_success;
    } else if (method == "in"){
        url = "saveInStock.do";
        data = $("#instock_form").serialize();
        success_hint = inststock_success;
    }
    var load = layer.load();
    $.ajax({
        url: url,
        data: data,
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
                case 422:
                    layer.tips(outstock_qty_ecceed + res["stock"], $("#qty"), {tips: 2});
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

function calc() {
    var method = $("#method").val();
    var qty = $("#qty").val();
    if (method == "in") {
        var price = $("#price").val();
        if (price.length <= 0 || qty.length <= 0) {
            $("#pay").val(0);
        } else {
            $("#pay").val(Number(price) * Number(qty));
        }
    } else if (method == "out"){
        var sellPrice = $("#sellPrice").val();
        if (sellPrice.length <= 0 || qty.length <= 0) {
            $("#pay").val(0);
        } else {
            $("#pay").val(Number(sellPrice) * Number(qty));
        }
    }
}