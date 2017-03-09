/**
 * Created by Neil on 2017/2/25.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
});
/**
 * 计算价格
 */
function calcPrice() {
    if ($("reserveId").val() != undefined && $("reserveId").val() != "") {
        var from = $("#from").val();
        var to = $("#to").val();
        // 入住天数
        var days = dateDiff(from, to);
        var price = $("#roomPriceReserve").val();
        var deposit = $("#deposit").val();
        var foregift = $("#foregiftReserve").val();
        $("#chargeReserve").val(days * price);
        $("#payReserve").val(days * price - Number(deposit) + Number(foregift));
    } else {
        var from = new Date();
        var to = $("#to_date").val();
        var days = dateDiff(from, to);
        var price = $("#roomPrice").val();
        var foregift = $("#foregift").val();
        $("#charge").val(days * price);
        $("#pay").val(days * price + Number(foregift));
    }
}
$(document).ready(function () {
    $(".btn-add-customer").click(function () {
        var tr = document.createElement("tr");
        var td1 = document.createElement("td");
        var input1 = document.createElement("input");
        $(td1).attr("width","200px");
        $(input1).attr("type","text");
        $(input1).attr("class","table-input idcard-input");
        $(input1).attr("name", "idcard");
        $(input1).blur(function () {
            if(validateIdCard(this)) findCustomer(this);
        });
        var td2 = document.createElement("td");
        var input2 = document.createElement("input");
        $(td2).attr("width", "150px");
        $(input2).attr("type", "text");
        $(input2).attr("class", "table-input name-input");
        $(input2).attr("name", "name");
        var td3 = document.createElement("td");
        $(td3).addClass("operation");
        td1.appendChild(input1);
        td2.appendChild(input2);
        tr.appendChild(td1);
        tr.appendChild(td2);
        tr.appendChild(td3);
        var tbody = document.getElementById("tbody");
        tbody.appendChild(tr);
        $(".table-div").scrollTop($(".table-divtbody").scrollHeight);
    });
    $('#to_date').dateRangePicker({
        language:$("#lang").val(),
        startDate: new Date(),
        selectForward: true,
        autoClose: true,
        showShortcuts: false,
        singleMonth: true,
        autoClose: true,
        singleDate : true,
        showShortcuts: false,
        setValue: function (s) {
            $("#to_date").val(s);
            calcPrice();
        }
    });

    // 自动计算预定价格
    calcPrice();
});

/**
 * 查找用户
 * @param inp
 */
function findCustomer(inp) {
    $(inp).parent().parent().find(".operation").html("<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='removePerson(this)'>&#xe640;" + btn_delete + "</i>");
    var idCard = $(inp).val();
    $.ajax({
        url: "findCustomer.do",
        data: {"idCard": idCard},
        dataType: "json",
        type: "get",
        success: function (data) {
            var res = eval("(" + data + ")");
            if (res["status"] == 200) {
                $(inp).parent().parent().find(".id-input").val(res["id"]);
                $(inp).parent().parent().find(".name-input").val(res["name"]);
            }
        }
    });
}

/**
 * 入住
 */
function checkin() {
    var index = layer.load();
    $.ajax({
        url: "checkin.do",
        type: "POST",
        data: $("#checkin-form").serialize(),
        dataType: "json",
        success: function (data) {
            layer.close(index);
            var res = eval("(" + data + ")");
            switch (res["status"]){
                case 400: layer.msg(not_match_name,{icon:2, time: 2000});break;
                case 410: layer.msg(no_customer,{icon:2, time: 2000});break;
                case 420: layer.tips(foregift_wrong,$(".foregift"), {tips: 2});break;
                case 421: layer.tips(foregift_negative,$(".foregift"), {tips: 2});break;
                case 430: layer.tips(messageOfValidateLength(message_note, 1024), $("#note"), {tips: 2});break;
                case 440: layer.tips(date_wrong,$("#to_date"), {tips: 2});break;
                case 200: layer.msg(checkin_success,{icon:6, time: 2000}, function () {
                    parent.location.reload();
                    closeMe();
                });break;
            }
        }
    });
}
/**
 * 移除入住人
 * @param obj
 */
function removePerson(obj){
    $(obj).parent().parent().find(".idcard-input").val("");
    $(obj).parent().parent().find(".name-input").val("");
    $(obj).parent().parent().find(".operation").html("");
}