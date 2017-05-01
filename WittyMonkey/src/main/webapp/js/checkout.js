/**
 * Created by neilw on 2017/3/7.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
});
function checkout() {
    var id = $("#checkinId").val();
    var diff = $("#checkoutRefund").val();
    layer.prompt({
        formType: 0,
        value: diff,
        title: checkout_payback
    }, function(value, index, elem){
        var load = layer.load();
        $.ajax({
            url: "checkout.do",
            data: {"id": id, "refund": value},
            dataType: "json",
            type: "POST",
            success: function (data) {
                layer.close(load);
                var res = eval("(" + data + ")");
                switch (res["status"]) {
                    case 400:
                        layer.msg(checkin_not_exist, {icon: 2, time: 2000});
                        break;
                    case 410:
                        layer.msg(checkout_checkin_already_checkout, {icon: 2, time: 2000});
                        break;
                    case 420:
                        layer.msg(checkout_refund_wrong + res["range"], {icon: 2, time: 2000});
                        break;
                    case 500:
                        layer.msg(error_500, {icon: 2, time: 2000});
                        break;
                    case 200:
                        layer.msg(checkout_success, {icon: 6, time: 2000}, function () {
                                parent.location.reload();
                                closeMe();
                            }
                        );
                        break;
                }
                layer.close(index);
            }
        });
    });

}