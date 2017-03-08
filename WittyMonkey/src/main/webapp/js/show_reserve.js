/**
 * Created by neilw on 2017/3/8.
 */
var laypage;
layui.use('laypage', function () {
    laypage = layui.laypage;
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
                '<td width="100px">' + obj[i]["customer"]["name"] + '</td>'+
                '<td width="200px">' + obj[i]["customer"]["idCard"] + '</td>'+
                '<td width="100px">' + obj[i]["customer"]["tel"] + '</td>'+
                '<td width="100px">' + new Date(obj[i]["reserveDate"]).format("yyyy-MM-dd") + '</td>'+
                '<td width="100px">' + new Date(obj[i]["estCheckinDate"]).format("yyyy-MM-dd") + '</td>'+
                '<td width="100px">' + new Date(obj[i]["estCheckoutDate"]).format("yyyy-MM-dd") + '</td>'+
                '<td width="100px"></td>'+
                '</tr>';
        }
    }
    $("#dataTabel").html(html);
}