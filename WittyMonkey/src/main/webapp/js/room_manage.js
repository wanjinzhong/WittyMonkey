/**
 * Created by neilw on 2017/2/20.
 */
var layer;
var form;
var laypage;

layui.use(['layer', 'form', 'laypage'], function () {
    layer = layui.layer;
    laypage = layui.laypage;
    form = layui.form();
    page("getRoomByHotel.do");
    form.on('select(type)', function (data) {
        changeType(data.value);
    });
    changeType(0);
});
function showAddRoom() {
    layer.open({
        title: room_add_title,
        area: ['1000px', '700px'],
        maxmin: false,
        shade: 0.4,
        content: "toAddRoom.do",
        scrollbar: false,
        type: 2,
    });
}

function search() {
    var type = $("#type").val();
    var content = $("#searchContent").val();
    var condition = {"type": type, "content": content};
    page("getRoomByCondition.do", undefined, condition);
}

function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="6"><fmt:message key="no_data"/></td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            if (obj[i].status == 0) {
                html += '<div class="roomBorder roomBorder2" onclick="showRoomDetail(' + obj[i]["id"] + ')">' +
                    '<span class="number">' + obj[i]["number"] + '</span>' +
                    '<span class="name">' + obj[i]["name"] + '</span>' +
                    '<br/>' +
                    '<dl class="btn_opt">' +
                    '<li class="btn_book">' +
                    '<svg class="icon" aria-hidden="true">' +
                    '<use xlink:href="#icon-yuding"></use>' +
                    '</svg> ' + btn_book +
                    '</li>' +
                    '<li class="btn_checkin">' +
                    '<svg class="icon" aria-hidden="true">' +
                    '<use xlink:href="#icon-ruzhu"></use>' +
                    '</svg> ' + btn_checkin +
                    '</li>' +
                    '</dl>' +
                    '</div>';
            } else if (obj[i].status == 1) {
                html += '<div class="roomBorder roomBorder3" onclick="showRoomDetail(' + obj[i]["id"] + ')">' +
                    '<span class="number">' + obj[i]["number"] + '</span>' +
                    '<span class="name">' + obj[i]["name"] + '</span>' +
                    '<br/>' +
                    '<dl class="btn_opt">' +
                    '<li class="btn_unbook">' +
                    '<svg class="icon" aria-hidden="true">' +
                    '<use xlink:href="#icon-tuiding"></use>' +
                    '</svg> ' + btn_unbook +
                    '</li>' +
                    '<li class="btn_checkin">' +
                    '<svg class="icon" aria-hidden="true">' +
                    '<use xlink:href="#icon-ruzhu"></use>' +
                    '</svg> ' + btn_checkin +
                    '</li>' +
                    '</dl>' +
                    '</div>';
            }
            else if (obj[i].status == 2) {
                html += '<div class="roomBorder roomBorder1" onclick="showRoomDetail(' + obj[i]["id"] + ')">' +
                    '<span class="number">' + obj[i]["number"] + '</span>' +
                    '<span class="name">' + obj[i]["name"] + '</span>' +
                    '<br/>' +
                    '<dl class="btn_opt">' +
                    '<li class="btn_change">' +
                    '<svg class="icon" aria-hidden="true">' +
                    '<use xlink:href="#icon-huanfang"></use>' +
                    '</svg> ' + btn_change +
                    '</li>' +
                    '<li class="btn_checkout">' +
                    '<svg class="icon" aria-hidden="true">' +
                    '<use xlink:href="#icon-tuifang"></use>' +
                    '</svg> ' + btn_checkout +
                    '</li>' +
                    '</dl>' +
                    '</div>';
            }
            else if (obj[i].status == 3) {
                html += '<div class="roomBorder roomBorder4" onclick="showRoomDetail(' + obj[i]["id"] + ')">' +
                    '<span class="number">' + obj[i]["number"] + '</span>' +
                    '<span class="name">' + obj[i]["name"] + '</span>' +
                    '<br/>' +
                    '<dl class="btn_opt">' +
                    '<li class="btn_clean">' +
                    '<svg class="icon" aria-hidden="true">' +
                    '<use xlink:href="#icon-qingli"></use>' +
                    '</svg> ' + btn_clean +
                    '</li>' +
                    '</dl>' +
                    '</div>' +
                    '</div>';
            }
        }
    }
    $("#content").html(html);
}

function changeType(obj) {
    var type = obj | $("#type").val();
    var html;
    if (type == 0) {
        html = '<select name="searchContent" lay-verify="required" id="searchContent">' +
            '<option value="0" selected>' + room_hint_free + '</option>' +
            '<option value="1" >' + room_hint_booked + '</option>' +
            '<option value="2">' + room_hint_checkin + '</option>' +
            '<option value="3">' + room_hint_clean + '</option>' +
            '</select>';
    } else if (type == 1) {
        $.ajax({
            url: "getFloor.do",
            dataType: "json",
            type: "get",
            success: function (data) {
                var res = eval("(" + data + ")");
                var html;
                html = '<select name="searchContent" lay-verify="required" id="searchContent">';
                for (var i in res) {
                    html += '<option value="' + res[i]["id"] + '">' + res[i]["floorNo"] + '</option>';
                }
                html += '</select>';
                $(".searchContent").html(html);
                form.render("select");
            }
        });
    } else {
        html = '<input type="text" class="layui-input" name="searchContent" id="searchContent"/>';
    }
    $(".searchContent").html(html);
    form.render("select");
}
var room_detail;
function showRoomDetail(id){
    room_detail = layer.open({
        title: room_detail_title,
        area: ['1000px', '700px'],
        maxmin: false,
        shade: 0.4,
        content: "showRoomDetail.do?roomId=" + id,
        scrollbar: false,
        type: 2,
    });
}