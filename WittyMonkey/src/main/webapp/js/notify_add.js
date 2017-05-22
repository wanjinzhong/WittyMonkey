/**
 * Created by neilw on 2017/5/3.
 */
var layer;
var ueditor;
layui.use(['layer'], function () {
    layer = layui.layer;
    element = layui.element;
    ueditor = UE.getEditor('container');
    ueditor.ready(function () {
        // 隐藏 路径一行
        $(".edui-editor-bottomContainer").hide();
    });
});

function showAddNotify(){
    var index = layer.open({
        type: 2,
        content: 'toAddNotify.do',
        area: ['320px', '195px'],
        maxmin: true
    });
    layer.full(index);
}


function chooseReceiver(){

}

function send(){
    var reciever = $("#receivers").val();
    var content = ueditor.getContent();
    var subject = $("#subject").val();
    if (reciever.length == 0) {
        layer.msg(notify_no_receiver, {
            icon: 2, time: 2000
        });
        return false;
    }
    var load = layer.load();
    $.ajax({
        url: "sendNotify.do",
        data: {"receiver": reciever, "content": content, "subject": subject},
        dataType: "JSON",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]){
                case 400:
                    layer.msg(notify_no_receiver, {
                        icon: 2, time: 2000
                    });
                    break;
                case 200:
                    layer.msg(send_success, {
                        icon: 1,
                        time: 2000
                    }, function () {
                        parent.location.reload();
                        closeMe();
                    });
                    break;
            }
        }
    });
}