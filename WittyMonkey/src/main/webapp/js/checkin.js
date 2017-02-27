/**
 * Created by Neil on 2017/2/25.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
});
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
        showShortcuts: false
    });

    // 自动计算预定价格
    if ($("reserveId").val() != ""){
        var from = $("#from").val();
        var to = $("#to").val();

        alert(dateDiff(from, to));

    }
});

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
            }
        }
    });
}