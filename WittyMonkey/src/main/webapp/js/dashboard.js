/**
 * Created by neilw on 2017/5/2.
 */
var layer;
layui.use('layer', function () {
    layer = layui.layer;
    var load = layer.load();
    $.ajax({
        url: "getDashboard.do",
        dataType: "JSON",
        type: "GET",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            var lang = $("#lang").val();
            if (lang == "zh_CN"){
                zh_CN(res);
            } else if (lang == "en_US"){
                en_US(res);
            }
        }
    });
});

function zh_CN(res) {
    if (res["reserve"] != undefined || res["checkin"] != undefined){
        var html = "<ul>";
        var reserve = res["reserve"];
        if (reserve.length == 0){
            html += "<li>没有预计今日入住的预定。</li>";
        } else {
            for (var i in reserve) {
                html += "<li>" + reserve[i]["name"] + "已预定" + reserve[i]["room"] + "房间,预计今日入住。</li>"
            }
        }
        var checkin = res["checkin"];
        if (checkin.length == 0){
            html += "<li>今日没有预计退房。</li>";
        } else {
            for (var i in checkin) {
                html += "<li>" + checkin[i]["name"];
                if (checkin[i]["name"] > 1){
                    html += "等" + checkin[i]["person"] + "人";
                }
                html += "预计今日退房，房间号: " + checkin[i]["room"] + "。</li>";
            }
        }
        html += "</ul>"
        $('#room').css('display', 'block');
        $('#room_content').html(html);
    }
    if (res["zeroInventory"] != undefined || res["lowInventory"] != undefined){
        var html = "<ul>";
        var html = "<ul>";
        var zero = res["zeroInventory"];
        if (zero.length == 0){
            html += "<li>没有零库存物料。</li>";
        } else {
            for (var i in zero) {
                html += "<li>物料\"" + zero[i]["name"] + "(" + zero[i]["barcode"] +")\"库存为0，请立即入库。</li>";
            }
        }
        var low = res["lowInventory"];
        if (low.length == 0){
            html += "<li>没有低库存物料。</li>";
        } else {
            for (var i in low) {
                html += "<li>物料\"" + low[i]["name"] + "(" + low[i]["barcode"] +")\"库存低于预警库存，请及时入库。</li>";
            }
        }
        html += "</ul>";
        $('#inventory').css('display', 'block');
        $('#inventory_content').html(html);
    }
    if (res["salary"] != undefined){
        var html = "<ul>";
        if (res["salary"]["number"] == 0){
            html += "<li>没有需要发放的上月工资。</li>";
        } else {
            html += "<li>需要发放" + res["salary"]["name"];
            if (res["salary"]["number"] > 3){
                html += "等" + res["salary"]["number"] + "人";
            }
            html += "上月工资。</li>";
        }
        html += "</ul>";
        $('#salary').css('display', 'block');
        $('#salary_content').html(html);
    }
    if (res["leave"] != undefined){
        var html = "<ul>";
        var leave = res["leave"];
        if (leave.length == 0){
            html += "<li>没有需要处理的请假申请。</li>";
        } else {
            for (var i in leave) {
                html += "<li>有" + leave[i]["number"] + "条" + leave[i]["name"] + "的请假申请未处理。</li>";
            }
        }
        html += "</ul>";
        $('#leave').css('display', 'block');
        $('#leave_content').html(html);
    }
    if (res["reimburse"] != undefined){
        var html = "<ul>";
        var reimburse = res["reimburse"];
        if (reimburse.length == 0){
            html += "<li>没有需要处理的报销申请</li>";
        } else {
            for (var i in reimburse) {
                html += "<li>有" + reimburse[i]["number"] + "条" + reimburse[i]["name"] + "的报销申请未处理。</li>";
            }
        }
        html += "</ul>";
        $('#reimburse').css('display', 'block');
        $('#reimburse_content').html(html);
    }
}
function en_US(res) {
    if (res["reserve"] != undefined || res["checkin"] != undefined){
        var html = "<ul>";
        var reserve = res["reserve"];
        if (reserve.length == 0){
            html += "<li>No reserve to check in for today.</li>";
        } else {
            for (var i in reserve) {
                html += "<li>" + reserve[i]["name"] + " reserved room " + reserve[i]["room"] + ", expected check in today.</li>"
            }
        }
        var checkin = res["checkin"];
        if (checkin.length == 0){
            html += "<li>No expected to check out today.</li>";
        } else {
            for (var i in checkin) {
                html += "<li>" + checkin[i]["name"];
                if (checkin[i]["name"] > 1){
                    html += ", " + checkin[i]["person"] + "person ";
                }
                html += " expected to check out today，room number: " + checkin[i]["room"] + "。</li>";
            }
        }
        html += "</ul>"
        $('#room').css('display', 'block');
        $('#room_content').html(html);
    }
    if (res["zeroInventory"] != undefined || res["lowInventory"] != undefined){
        var html = "<ul>";
        var html = "<ul>";
        var zero = res["zeroInventory"];
        if (zero.length == 0){
            html += "<li>No material which inventory is zero.</li>";
        } else {
            for (var i in zero) {
                html += "<li>A materiel \"" + zero[i]["name"] + "(" + zero[i]["barcode"] +")\" which inventory is zero，Please in stock immediately。</li>";
            }
        }
        var low = res["lowInventory"];
        if (low.length == 0){
            html += "<li>No material with low inventory .</li>";
        } else {
            for (var i in low) {
                html += "<li>A materiel \"" + low[i]["name"] + "(" + low[i]["barcode"] +")\" which inventory is lower than the warning inventory, please in stock in time。</li>";
            }
        }
        html += "</ul>";
        $('#inventory').css('display', 'block');
        $('#inventory_content').html(html);
    }
    if (res["salary"] != undefined){
        var html = "<ul>";
        if (res["salary"]["number"] == 0){
            html += "<li>No need to pay the monthly salary.</li>";
        } else {
            html += "<li>Need to pay " + res["salary"]["name"];
            if (res["salary"]["number"] > 3){
                html += ", " + res["salary"]["number"] + "person";
            }
            html += "for salary last month。</li>";
        }
        html += "</ul>";
        $('#salary').css('display', 'block');
        $('#salary_content').html(html);
    }
    if (res["leave"] != undefined){
        var html = "<ul>";
        var leave = res["leave"];
        if (leave.length == 0){
            html += "<li>No need to apply for leave.</li>";
        } else {
            for (var i in leave) {
                if (leave[i]["number"] == 1){
                    html += "<li>There is a leave application from " + leave[i]["name"] + " need to apply.</li>";
                } else {
                    html += "<li>There are " + leave[i]["number"] + " leave application from " + leave[i]["name"] + " need to apply.</li>";
                }
            }
        }
        html += "</ul>";
        $('#leave').css('display', 'block');
        $('#leave_content').html(html);
    }
    if (res["reimburse"] != undefined){
        var html = "<ul>";
        var reimburse = res["reimburse"];
        if (reimburse.length == 0){
            html += "<li>There is no need to apply for reimbursement</li>";
        } else {
            for (var i in reimburse) {
                if (reimburse[i]["number"] == 1){
                    html += "<li>There is a reimbursement application from " + reimburse[i]["name"] + " need to apply.</li>";
                } else {
                    html += "<li>There are " + reimburse[i]["number"] + " reimbursement application from " + reimburse[i]["name"] + " need to apply.</li>";
                }
            }
        }
        html += "</ul>";
        $('#reimburse').css('display', 'block');
        $('#reimburse_content').html(html);
    }
}