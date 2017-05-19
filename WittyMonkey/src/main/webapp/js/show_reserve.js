/**
 * Created by neilw on 2017/3/8.
 */
var laypage;
var layer;
layui.use(['laypage','layer'], function () {
    laypage = layui.laypage;
    layer = layui.layer;
    var roomId = $("#roomId").val();
    var condition = {"roomId":roomId};
    page("getReserveByPage.do", undefined, condition);
});
function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="7">' + no_data + '</td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            html += '<tr>'+
                '<td>' + obj[i]["custName"] + '</td>'+
                '<td>' + obj[i]["custTel"] + '</td>'+
                '<td>' + new Date(obj[i]["reserveDate"]).format("yyyy-MM-dd") + '</td>'+
                '<td>' + new Date(obj[i]["estCheckinDate"]).format("yyyy-MM-dd") + '</td>'+
                '<td>' + new Date(obj[i]["estCheckoutDate"]).format("yyyy-MM-dd") + '</td>'+
                '<td>' + obj[i]["deposit"] + '</td>'+
                '<td>' +
                '<i class="deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small" onclick="unsubscribe(' + obj[i]["id"] + ',' + obj[i]["deposit"] + ')">' +
                '<svg class="icon">' +
                '<use xlink:href="#icon-tuiding"></use>' +
                '</svg> ' + 
                btn_unsubscribe + '</i>' +
                '</td>'+
                '</tr>';
        }
    }
    $("#dataTabel").html(html);
}

function unsubscribe(id, deposit) {
    layer.prompt({
        formType: 0,
        value: '0',
        title: unsubscribe_title,
    }, function(value, index, elem){
        if (!validateMoneyValue(value)){
            layer.msg(wrong_refund, {icon:2, time: 2000}, function () {
                layer.close(index);
            });
            return false;
        }
        if (value > deposit){
            layer.msg(refund_too_large, {icon:2, time: 2000}, function () {
                layer.close(index);
            });
            return false;
        }
        var load = layer.load();
        $.ajax({
            url: "unsubscribe.do",
            data: {"id": id, "refund": value},
            dataType: "json",
            type: "get",
            success: function (data) {
                layer.close(load);
                var res = eval("(" + data + ")");
                switch (res["status"]){
                    case 200:
                        layer.msg(unsubscribe_success, {icon:6, time:2000}, function () {
                            location.reload();
                        });
                        break;
                    case 400:
                        layer.msg(reserve_not_exist, {icon:2, time:2000}, function () {
                            location.reload();
                        });
                        break;
                    case 410:
                        layer.msg(wrong_refund, {icon:2, time:2000}, function () {
                            location.reload();
                        });
                        break;
                    case 420:
                        layer.msg(unsubscribe_checkedin, {icon:2, time:2000}, function () {
                            location.reload();
                        });
                        break;
                    case 421:
                        layer.msg(unsubscribe_unsubscribe, {icon:2, time:2000}, function () {
                            location.reload();
                        });
                        break;
                    case 430:
                        layer.msg(refund_too_large, {icon:2, time:2000}, function () {
                            parent.location.reload();
                        });
                        break;
                    case 500:
                        layer.msg(error_500, {icon:2, time:2000}, function () {
                            parent.location.reload();
                        });
                        break;
                }
            }
        });
    });
}