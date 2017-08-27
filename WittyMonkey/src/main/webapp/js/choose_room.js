/**
 * Created by neil on 17-3-9.
 */
var laypage;
var layer;
layui.use(['laypage', 'layer'], function () {
    laypage = layui.laypage;
    layer = layui.layer;
    var checkinId = $("#checkinId").val();
    var condition = {"id": checkinId};
    page("getFreeRoomByDateRange.do", 1, condition);
});

function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="6">' + no_data + '</td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            html += '<tr>' +
                '<input type="hidden" id="id" value="' + obj[i]["id"] + '"/>' +
                '<td id="number">' + obj[i]["number"] + '</td>' +
                '<td id="name">' + obj[i]["name"] + '</td>' +
                '<td>' + obj[i]["singleBedNum"] + '</td>' +
                '<td>' + obj[i]["doubleBedNum"] + '</td>' +
                '<td>' + obj[i]["availableNum"] + '</td>' +
                '<td id="price">' + obj[i]["price"] + '</td>' +
                '<td>' + '<i class="layui-icon layui-btn layui-btn-primary layui-btn-small layui-btn-r layui-icon" onclick="choose(this)">&#xe618;&nbsp;&nbsp;' + btn_change + '</i>' + '</td>' +
                '</tr>';
        }
    }
    $("#dataTabel").html(html);
}
function choose(obj) {
    var roomId = $(obj).parent().parent().find("#id").val();
    // var checkinId = $("#checkinId").val();
    parent.$("#toId").val($(obj).parent().parent().find("#id").val());
    parent.$("#toNumber").val($(obj).parent().parent().find("#number").html());
    parent.$("#toName").val($(obj).parent().parent().find("#name").html());
    parent.$("#toPrice").val($(obj).parent().parent().find("#price").html());
    window.parent.calcDiff();
    closeMe();
}