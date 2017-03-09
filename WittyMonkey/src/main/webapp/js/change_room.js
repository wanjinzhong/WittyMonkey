/**
 * Created by neilw on 2017/3/9.
 */
$.ready(function () {
    $(".showRoom").click(function () {
        parent.layer.open({
            title: choose_room_title,
            content: "toChooseRoom.do?id=" + $("#checkin_id").val(),
            area: ['700px', '500px'],
            maxmin: false,
            scrollbar: false,
            type: 2
        });
    });
});