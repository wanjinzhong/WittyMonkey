/**
 * Created by neilw on 2017/5/3.
 */
var layer;
var laypage;
var element;
var content;
var tab;
layui.use(['layer', 'laypage', 'element'], function () {
    layer = layui.layer;
    laypage = layui.laypage;
    element = layui.element();
    element.on("tab(box-tab)", function () {
        tab = this.getAttribute("lay-id");
        tabChange(tab);
    });
    tab = 1;
    tabChange(tab);
});

function showAddNotify() {
    var index = layer.open({
        type: 2,
        content: 'toAddNotify.do',
        area: ['320px', '195px'],
        maxmin: true
    });
    layer.full(index);
}
function tabChange(tab) {
    if (tab == 1) {
        boxPage("inBox.do", tab, undefined, undefined);
    } else if (tab == 2) {
        boxPage("outBox.do", tab, undefined, undefined);
    } else if (tab == 3) {
        boxPage("trashBin.do", tab, undefined, undefined);
    }
}
function refreshTable(data) {
    var html = '';
    if (data.length == 0) {
        if (tab == 1) {
            html = "<div class='isNull'>" + inbox_null + "</div>";
        } else if (tab == 2) {
            html = "<div class='isNull'>" + outbox_null + "</div>";
        } else if (tab == 3) {
            html = "<div class='isNull'>" + trashbin_null + "</div>";
        }
    } else {
        for (var i in data) {
            html += notifyUI(data[i]);
        }
    }
    if (tab == 1) {
        $("#inbox").html(html);
    } else if (tab == 2) {
        $("#outbox").html(html);
    } else if (tab == 3) {
        $("#trash").html(html);
    }

}
function notifyUI(data) {
    var html = '<div class="notify';
    if (tab == 1 && !data["readed"]) {
        html += " unread";
    }
    html += '" onclick="detail(' + data["notifyId"] + ')">';
    if (tab == 2) {
        html += '<div class="receiver">' + data["receivers"] + '</div>';
    } else {
        html += '<div class="sender">' + data["sender"] + '</div>';
    }
    html += '<div class="time">' + formatDate(data["sendDate"]) + '</div>' +
        '<div class="subject">' + data["subject"] + '</div>';
    if (tab == 3) {
        html += '<i style="margin-left: 10px" class="layui-icon optBtn" onclick="deleteNotify(' + data[""] + ')">&#xe640;</i>';
        html += '<svg class="icon optBtn" aria-hidden="true" onclick="recovery( + data["notifyId"] + )">' +
            '<use xlink:href="#icon-huifu"></use>' +
            '</svg>';
    } else if (tab == 1) {
        html += '<i class="layui-icon optBtn" onclick="deleteNotify(' + data[""] + ')">&#xe640;</i>';
    } else if (tab == 2) {
        html += '<i class="layui-icon optBtn" onclick="deleteNotifyReciever(' + data[""] + ')">&#xe640;</i>';
    }
    html += '</div>';
    return html;
}
function detail(id) {
    var index = layer.open({
        type: 2,
        content: 'toShowNotify.do?id=' + id,
        area: ['320px', '195px'],
        maxmin: true
    });
    layer.full(index);
}

/**
 * 分页
 * @param curr
 */
function boxPage(url, tab, curr, condition) {
    if (curr == undefined) {
        curr = 1;
    }
    if (condition == undefined) {
        condition = {"curr": curr};
    } else {
        condition["curr"] = curr;
    }
    var page;
    if (tab == 1) {
        page = 'inbox_page';
    } else if (tab == 2) {
        page = 'outbox_page';
    } else if (tab == 3) {
        page = "trashbin_page";
    }
    var load = layer.load();
    $.ajax({
        type: "GET",
        url: url,
        data: condition,
        dataType: "json",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            var pageSize = res["pageSize"];
            laypage({
                cont: page,
                pages: Math.ceil(res["count"] / pageSize),
                curr: curr || 1,
                first: page_first,
                last: page_last,
                prev: page_prev,
                next: page_next,
                skip: true,
                jump: function (obj, first) {
                    if (!first) {
                        page(url, obj.curr, condition);
                    }
                    refreshTable(res["data"]);
                }
            });
        }
    });
}