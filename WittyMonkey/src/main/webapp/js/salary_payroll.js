/**
 * Created by neilw on 2017/4/30.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
    var load = layer.load();
    $.ajax({
        url: "getLastMonthSalary.do",
        dataType: "json",
        type: "get",
        success: function (data) {
            var res = eval("(" + data + ")");
            layer.close(load);
            refreshTable(res["data"]);
            $("#salaryDate").html(formatMonth(res["date"]));
        }
    });
});

function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="7">' + no_salary + '</td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            var amount = Number(obj[i]["basic"]) - Number(obj[i]["leave"]);
            if (amount < 0) {
                amount = 0;
            }
            html += "<tr class='text-c'>" +
                "<td>" + obj[i]["staffNo"] + "</td>" +
                "<td>" + obj[i]["staffName"] + "</td>" +
                "<td><input class='layui-input' disabled id='basic_" + obj[i]['staffId'] + "' value='" + Number(obj[i]["basic"]).toFixed(2) + "'/></td>" +
                "<td><input class='layui-input' disabled id='leave_" + obj[i]['staffId'] + "' value='" + Number(obj[i]["leave"]).toFixed(2) + "'/></td>" +
                "<td><input type='number' class='layui-input' name='other_" + obj[i]["staffId"] + "' onblur='calcAmount(" + obj[i]["staffId"] + ")' value='0.00' id='other_" + obj[i]["staffId"] + "'/></td>" +
                "<td><input type='number' class='layui-input' name='bonus_" + obj[i]["staffId"] + "' onblur='calcAmount(" + obj[i]["staffId"] + ")' value='0.00' id='bonus_" + obj[i]["staffId"] + "'/></td>" +
                "<td><input disabled class='layui-input' id='amount_" + obj[i]["staffId"] + "' value='" + Number(amount).toFixed(2) + "'></td>" +
                "</tr>";
        }
    }
    $("#dataTabel").html(html);
}

function calcAmount(id) {
    var basic = $("#basic_" + id).val();
    var leave = $("#leave_" + id).val();
    var other = $("#other_" + id).val();
    var bonus = $("#bonus_" + id).val();
    var amount = Number(basic) - Number(leave) - Number(other) + Number(bonus);
    if (amount < 0) {
        amount = 0;
    }
    $("#amount_" + id).val(amount.toFixed(2));
}

function payroll() {
    var load = layer.load();
    $.ajax({
        url: "batchPayroll.do",
        data: $("#payroll-form").serialize(),
        dataType: "JSON",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(other_pay_wrong, $("#other_" + res["staffId"]), {tips: 2});
                    break;
                case 401:
                    layer.tips(bonus_wrong, $("#bonus_" + res["staffId"]), {tips: 2});
                    break;
                case 200:
                    layer.confirm(payroll_success +　res["total"], {icon: 3, title:payroll_success_title}, function(index){
                        location.reload();
                    });
                    break;
            }
        }
    });
}