/**
 * Created by neilw on 2017/5/1.
 */
var layer;
var form;
var totalInOut;
var inReport;
var outReport;

var message_total;
var message_net_margin;
var message_in;
var messafe_out;
layui.use(["layer", "form"], function () {
    layer = layui.layer;
    form = layui.form();
    form.on('radio(type)', function (data) {
        changeRange(data.value);
    });
});

$(document).ready(function () {
    $('#from').dateRangePicker({
        language: $("#lang").val(),
        showShortcuts: false,
        singleMonth: true,
        autoClose: true,
        singleDate: true,
        showShortcuts: false,
        setValue: function (s) {
            $(this).val(s);
        }
    });
    $('#to').dateRangePicker({
        language: $("#lang").val(),
        showShortcuts: false,
        singleMonth: true,
        autoClose: true,
        singleDate: true,
        showShortcuts: false,
        setValue: function (s) {
            $(this).val(s);
        }
    });
    changeRange();
    loadI18n();
    totalInOut = echarts.init(document.getElementById("totalInOut"));
    inReport = echarts.init(document.getElementById("inReport"));
    outReport = echarts.init(document.getElementById("outReport"));


});

function loadI18n() {
    message_total = total_in_out_title;
    message_net_margin = net_margin;
    message_in = in_title;
    message_out = out_title;
}

function changeRange() {
    var type = $("input[name='type']:checked").val();
    if (type == 3) {
        $("#from").attr("disabled", false);
        $("#to").attr("disabled", false);
    } else {
        $("#from").val("");
        $("#to").val("");
        $("#from").attr("disabled", true);
        $("#to").attr("disabled", true);
    }
}

function search() {
    var type = $("input[name='type']:checked").val();
    var condition;
    var from = $("#from").val();
    var to = $("#to").val();
    if (type == 3) {
        if (from == undefined || from == "" || to == undefined || to == "") {
            layer.msg(report_date_null, {icon: 2, time: 2000});
            return false;
        }
    }
    condition = {"type": type, "from": from, "to": to};
    var load = layer.load();
    $.ajax({
        url: "getReport.do",
        data: condition,
        dataType: "JSON",
        type: "GET",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res["status"]) {
                case 400:
                    layer.msg(date_wrong, {icon: 2, time: 2000});
                    return false;
                    break;
                case 200:
                    $("#totalHint").html(message_net_margin + (res["totalIn"] - res["totalOut"]).toFixed(2));
                    totalInOut.setOption({
                        title: {
                            text: message_total,
                            left: 'center',
                            top: 20
                        },

                        tooltip: {
                            trigger: 'item',
                            formatter: "{a} <br/>{b} : {c} ({d}%)"
                        },

                        visualMap: {
                            show: false,
                            min: 80,
                            max: 600,
                            inRange: {
                                colorLightness: [0, 1]
                            }
                        },
                        series: [
                            {
                                name: message_total,
                                type: 'pie',
                                radius: '55%',
                                center: ['50%', '50%'],
                                data: [
                                    {value: res["totalIn"], name: message_in},
                                    {value: res["totalOut"], name: message_out}
                                ].sort(function (a, b) {
                                    return a.value - b.value
                                }),
                                roseType: 'angle',
                                label: {
                                    normal: {
                                        textStyle: {
                                            color: 'rgba(255, 255, 255, 0.3)'
                                        }
                                    }
                                },
                                labelLine: {
                                    normal: {
                                        lineStyle: {
                                            color: 'rgba(255, 255, 255, 0.3)'
                                        },
                                        smooth: 0.2,
                                        length: 10,
                                        length2: 20
                                    }
                                },
                                itemStyle: {
                                    normal: {
                                        color: '#c23531',
                                        shadowBlur: 200,
                                        shadowColor: 'rgba(0, 0, 0, 0.1)'
                                    }
                                },

                                animationType: 'scale',
                                animationEasing: 'elasticOut'
                            }
                        ]
                    });
                    inReport.setOption({
                        color: ['#5FB878'],
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        xAxis: [
                            {
                                type: 'category',
                                data: res["inType"],
                                axisTick: {
                                    alignWithLabel: true
                                }
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: message_in,
                                type: 'bar',
                                barWidth: '60%',
                                data: res["inValue"]
                            }
                        ],
                        dataZoom: [
                            {   // 这个dataZoom组件，默认控制x轴。
                                type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
                                start: 0,      // 左边在 80% 的位置。
                                end: 100         // 右边在 100% 的位置。
                            },
                            {   // 这个dataZoom组件，也控制x轴。
                                type: 'inside', // 这个 dataZoom 组件是 inside 型 dataZoom 组件
                                start: 0,      // 左边在 80% 的位置。
                                end: 1000         // 右边在 1000% 的位置。
                            }
                        ]
                    });
                    outReport.setOption({
                        tooltip: {
                            trigger: 'axis',
                            axisPointer: {            // 坐标轴指示器，坐标轴触发有效
                                type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
                            }
                        },
                        xAxis: [
                            {
                                type: 'category',
                                data: res["outType"],
                                axisTick: {
                                    alignWithLabel: true
                                }
                            }
                        ],
                        yAxis: [
                            {
                                type: 'value'
                            }
                        ],
                        series: [
                            {
                                name: messafe_out,
                                type: 'bar',
                                barWidth: '60%',
                                data: res["outValue"]
                            }
                        ],
                        dataZoom: [
                            {   // 这个dataZoom组件，默认控制x轴。
                                type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
                                start: 0,      // 左边在 80% 的位置。
                                end: 100         // 右边在 100% 的位置。
                            },
                            {   // 这个dataZoom组件，也控制x轴。
                                type: 'inside', // 这个 dataZoom 组件是 inside 型 dataZoom 组件
                                start: 0,      // 左边在 80% 的位置。
                                end: 1000         // 右边在 1000% 的位置。
                            }
                        ]
                    });
                    break;
            }
        }
    });
}