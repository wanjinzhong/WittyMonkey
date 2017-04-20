/**
 * Created by neilw on 2017/4/20.
 */
var laryer;
layui.use(["layer"], function () {
    layer = layui.layer;
    $('#startDate').dateRangePicker({
        language: $("#lang").val(),
        showShortcuts: false,
        singleMonth: true,
        autoClose: true,
        singleDate: true,
        showShortcuts: false,
        setValue: function (s) {
            s = s.substring(0, s.lastIndexOf("-"));
            $(this).val(s);
        }
    });
});

function save() {
    if (!validateMoney($("#salary"))) {
        return false;
    }
    if (!validateNote($("#note"))) {
        return false;
    }
    var load = layer.load();
    $.ajax({
        url: "saveSalaryChange.do",
        data: $("#add_form").serialize(),
        dataType: "json",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(money_error, $("#salary"), {tips: 2});
                    break;
                case 410:
                    layer.tips(time_error, $("#startDate"), {tips: 2});
                    break;
                case 411:
                    layer.msg(time_conflict, {
                        icon: 2, time: 2000
                    }, function () {
                        closeMe();
                    });
                    break;
                case 420:
                    layer.tips(messageOfValidateLength(message_note, 1024), $("#note"), {tips: 2});
                    break;
                case 500:
                    layer.msg(error_500, {
                        icon: 2, time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
                case 200:
                    layer.msg(add_success,
                        {icon: 1, time: 2000},
                        function () {
                            window.location.href = "toSalaryChangeRecord.do?id=" + $("#id").val();
                        });
            }
        }
    })
}

function toBack() {
    window.location.href = "toSalaryChangeRecord.do?id=" + $("#id").val();
}