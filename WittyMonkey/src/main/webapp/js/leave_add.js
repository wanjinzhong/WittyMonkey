/**
 * Created by neilw on 2017/4/27.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
    form.on('select(status)', function (data) {
        if (data.value == 2) {
            $("#deductLable").html('<label class="layui-form-label">' + leave_deduct + '</label>');
            $("#deductInput").html('<input type="number" name="deduct" id="deduct" class="layui-input"/>');
        } else {
            $("#deductLable").html('');
            $("#deductInput").html('');
        }
        if (data.value == 1) {
            $("#optNote").val("");
            $("#optNote").attr("disabled", true);
        } else {
            $("#optNote").attr("disabled", false);
        }
    });

});

$(document).ready(function () {
    $("#leaveDate").dateRangePicker({
        language: $("#lang").val(),
        startDate: new Date(),
        selectForward: true,
        autoClose: true,
        showShortcuts: false,
        singleMonth: true,
        getValue: function () {
            if ($("#from").val() && $("#to").val())
                return $("#from").val() + ' to ' + $("#to").val();
            else
                return '';
        },
        setValue: function (s, s1, s2) {
            $("#leaveDate").val(s1 + " - " + s2);
            $("#from").val(s1);
            $("#to").val(s2);
            $("#days").val(dateDiff(s1, s2) + 1);
        }
    });
});

// 验证时间和获取扣薪
function validateDate() {
    var from = $("#from").val();
    var to = $("#to").val();
    var days = $("#days").val();
    var dateDiff = leaveDays(from, to);
    if (days < dateDiff - 1 || days > dateDiff) {
        layer.tips(dateDiff - 1 + "-" + dateDiff, $("#days"), {tips: 2});
        return false;
    }
    var status = $("#status").val();
    if (status == 2) {
        $.ajax({
            url: "calcDeduct.do",
            data: {"from": from, "to": to, "days": days, "applyUser": $("#applyUser").val()},
            dataType: "JSON",
            type: "GET",
            success: function (data) {
                var res = eval("(" + data + ")");
                switch (res["status"]) {

                }
            }
        });
    }
    return true;
}
/**
 *计算天数差的函数，通用
 */
function leaveDays(from, to) {    //sDate1和sDate2是2006-12-18格式
    if (from == undefined || to == undefined) {
        return 0;
    }
    if (from instanceof Date) {
        from = from.format("yyyy-MM-dd");
    }
    var fromDate = new Date(from.replace(/-/g, "/"));
    var toDate = new Date(to.replace(/-/g, "/"));
    return parseInt(Math.abs(toDate - fromDate) / 1000 / 60 / 60 / 24) + 1    //把相差的毫秒数转换为天数;
}