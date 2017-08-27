/**
 * Created by neilw on 2017/4/21.
 */
$(document).ready(function () {
    var chart = echarts.init(document.getElementById('main'));
    var message_date = salary_history_date;
    var message_basic = salary_history_basic;
    var message_leave = salary_history_leave;
    var message_other = salary_history_Other;
    var message_bonus = salary_history_bonus;
    var message_amount = salary_history_Amount;
    var message_legend = salary_history_legend;
    $.ajax({
        url: "getSalaryHistory.do",
        data: {"id": $("#id").val()},
        dataType: "JSON",
        type: "GET",
        success: function (data) {
            var res = eval("(" + data + ")");
            var tips = res["tips"];
            chart.setOption({
                title: {
                    text: salary_history
                },
                tooltip: {
                    trigger: "axis",
                    formatter: function (d) {
                        var key = d[0]["axisValue"];
                        for (var i = 0; i < tips.length; i++) {
                            if (tips[i]["date"] == key) {
                                var content = message_date + key + "<br/>" +
                                    message_basic + tips[i]["total"] + "<br/>" +
                                    message_leave + "-" + tips[i]["leave"] + "<br/>" +
                                    message_other + "-" + tips[i]["other"] + "<br/>" +
                                    message_bonus + "+" + tips[i]["bonus"] + "<br/>" +
                                    message_amount + d[0]["data"];
                                return content;
                            }
                        }
                    }
                },
                legend: {
                    data: [message_legend]
                },
                xAxis: {
                    data: res["time"]
                },
                yAxis: {},
                series: [{
                    name: message_legend,
                    type: "line",
                    data: res["salary"]
                }],
                dataZoom: [
                    {   // 这个dataZoom组件，默认控制x轴。
                        type: 'slider', // 这个 dataZoom 组件是 slider 型 dataZoom 组件
                        start: 80,      // 左边在 80% 的位置。
                        end: 100         // 右边在 100% 的位置。
                    },
                    {   // 这个dataZoom组件，也控制x轴。
                        type: 'inside', // 这个 dataZoom 组件是 inside 型 dataZoom 组件
                        start: 80,      // 左边在 80% 的位置。
                        end: 1000         // 右边在 1000% 的位置。
                    }
                ]
            });
        }
    });
});