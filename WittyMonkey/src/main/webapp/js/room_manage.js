/**
 * Created by neilw on 2017/2/20.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
})
function showAddRoom() {
    layer.open({
        title: room_add_title,
        area: ['800px', '700px'],
        maxmin: false,
        shade: 0.4,
        content: "toAddRoom.do",
        scrollbar: false,
        type: 2,
    });
}