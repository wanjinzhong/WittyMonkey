/**
 * Created by neilw on 2017/3/9.
 */


function showRoom() {
    parent.layer.open({
        title: choose_room_title,
        content: "toChooseRoom.do?id=" + $("#checkin_id").val(),
        area: ['800px', '700px'],
        maxmin: false,
        scrollbar: false,
        type: 2
    });
}