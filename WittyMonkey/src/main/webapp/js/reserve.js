/**
 * Created by neilw on 2017/2/23.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
});

function showDatePicker(obj) {
    var lang = $("#lang").val();
    var id = $(obj).attr('id');
    if (id == "from_date") {
        WdatePicker({
            lang: lang,
            minDate: '%y-%M-%d',
            dateFmt:'yyyy-MM-dd HH:mm:ss',
            readOnly: true
        });
    } else if (id == "to_date") {
        WdatePicker({
            lang: lang,
            minDate: '%y-%M-{%d+1}',
            dateFmt:'yyyy-MM-dd HH:mm:ss',
            readOnly: true});
    }
}
/**
 * 查找用户
 * @param inp
 */
function findCustomer(inp) {
    var idCard = $(inp).val();
    $.ajax({
        url: "findCustomer.do",
        data: {"idCard": idCard},
        dataType: "json",
        type: "get",
        success: function (data) {
            var res = eval("(" + data + ")");
            if (res["status"] == 200) {
                $("#custId").val(res["id"]);
                $("#name").val(res["name"]);
                $("#tel").val(res["tel"]);
            }
        }
    });
}
/**
 * 预定
 * @returns {boolean}
 */
function reserve() {
    if (!validateIdCard($("#idcard"))) {
        return false;
    } else if (!validateRealName($("#name"))) {
        return false;
    } else if (!validateTel($("#tel"))) {
        return false;
    } else if ($("#from_date").val() == "" || $("#to_date").val() == "") {
        layer.tips(messageOfValidateNull(reserve), $(".date"), {tips: 2});
    } else if (!validateMonney($("#deposit"))) {
        return false;
    } else {
        // 预定 ajax
        $.ajax({
            url: "reserve.do",
            data: $("#reserve-form").serialize(),
            dataType: "json",
            type: "get",
            success: function (data) {
                var res = eval("(" + data + ")");
                switch (res["status"]) {
                    case 400:
                        layer.tips(regist_idCard, $("#idcard"), {tips: 2});
                        return false;
                    case 410:
                        layer.tips(messageOfValidateNull(cust_name), $("#name"), {tips: 2});
                        return false;
                    case 411:
                        layer.tips(messageOfValidateLength(cust_name, 20), $("#name"), {tips: 2});
                        return false;
                    case 420:
                        layer.tips(messageOfValidateNull(tel), $("#tel"), {tips: 2});
                        return false;
                    case 421:
                        layer.tips(messageOfValidateLength(tel, 20), $("#tel"), {tips: 2});
                        return false;
                    case 430:
                        layer.tips(messageOfValidateNull(room_reserve_date), $(".date"), {tips: 2});
                        return false;
                    case 431:
                        layer.tips(date_wrong, $(".date"), {tips: 2});
                        return false;
                    case 432:
                        layer.tips(date_range, $(".date"), {tips: 2});
                        return false;
                    case 440:
                        layer.tips(messageOfValidateNull(deposit), $("#deposit"), {tips: 2});
                        return false;
                    case 441:
                        layer.tips(deposit_wrong, $("#deposit"), {tips: 2});
                        return false;
                    case 450:
                        layer.tips(messageOfValidateLength(message_note, 1024), $("#note"), {tips: 2});
                        return false;
                    case 200:
                        layer.msg(room_reserve_success, {
                            icon: 1,
                            time: 2000
                        }, function () {
                            parent.location.reload();
                            closeMe();
                        });
                        return true;
                }
            }
        });
    }
}