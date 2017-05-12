/**
 * Created by neilw on 2017/5/1.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
    calc();
});

function calc() {
    var basic = Number($("#basic").val());
    var leave = Number($("#leave").val());
    var other = Number($("#other").val());
    var benefits = Number($("#benefits").val());
    var amount = (basic - leave - other + benefits).toFixed(2);
    $("#amount").val(amount);
}

function dimission() {
    if (!validateNote($("#note"))) {
        return false;
    }
    var load = layer.load();
    $.ajax({
        url: "dimission.do",
        data: $("#dimission_form").serialize(),
        dataType: "JSON",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.tips(other_pay_wrong, $("#other"), {tips: 2});
                    break;
                case 401:
                    layer.tips(dimission_benefits_wrong, $("#bonus"), {tips: 2});
                    break;
                case 410:
                    layer.tips(messageOfValidateLength(message_note, 1024), $("#note"), {tips: 2});
                    break;
                case 200:
                    layer.confirm(dimission_success, {
                        icon: 3,
                        title: dimission_success
                    }, function (index) {
                        parent.location.reload();
                    });
                    break;
            }
        }
    });
}