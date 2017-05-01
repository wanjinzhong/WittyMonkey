/**
 * Created by neilw on 2017/4/17.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
    form.on('select(type)', function (data) {
        changeType(data.value);
    });
    changeType(1);
});
function changeType(obj) {
    var type = obj | $("#type").val();
    var html = "";
    var load = layer.load();
    $.ajax({
        url: "getFinanceTypeByType.do",
        data: {"type": type},
        dataType: "json",
        type: "get",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            var html;
            var types = res["data"];
            html = "<select name='financeType'>";
            for (var i in types) {
                html += '<option value="' + types[i]["id"] + '">' + types[i]["name"] + '</option>';
            }
            html += "</select>";
            $("#financeType").html(html);
            form.render("select");
        }
    });
}

function save() {
    if (!validateMoney($("#money"))) {
        return flase;
    }
    if (!validateNote($("#note"))) {
        return false;
    }
    var load = layer.load();
    $.ajax({
        type: "POST",
        dataType: "json",
        data: {"money": $("#money").val(), "note": $("#note").val(), "type": $("#financeType select").val()},
        url: "saveFinance.do",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.msg(finance_type_not_exist, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 410:
                    layer.msg(money_error, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 411:
                    layer.msg(money_only_positive, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 420:
                    layer.msg(messageOfValidateLength(message_note, 1024), {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 200:
                    layer.msg(finance_add_success, {
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