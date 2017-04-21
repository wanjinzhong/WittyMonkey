/**
 * Created by neilw on 2017/4/21.
 */
$(document).ready(function () {
    var chart = echarts.init(document.getElementById('main'));
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
                                var content = "时间：" + key + "<br/>" +
                                    "基本工资：" + tips[i]["total"] + "<br/>" +
                                    "请假扣薪: -" + tips[i]["leave"] + "<br/>" +
                                    "其它扣薪: -" + tips[i]["other"] + "<br/>" +
                                    "奖金: +" + tips[i]["bonus"] + "<br/>" +
                                    "实际工资：" + d[0]["data"];
                                return content;
                            }
                        }
                    }
                },
                legend: {
                    data: ["工资"]//[salary_legend]
                },
                xAxis: {
                    data: res["time"]
                },
                yAxis: {},
                series: [{
                    name: "工资",//salary_legend,
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