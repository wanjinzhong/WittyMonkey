/**
 * Created by Neil on 2017/2/19.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
});
$(document).ready(function () {
    $("#question").mouseover(function () {
        layer.tips(regist_license_tip, $("#question"), {
            tips: [4, '#3595CC'],
            time: 4000
        });
    });
    $("#question").mouseout(function () {
        var index = layer.tips();
        layer.close(index);
    });
});
function setPlace(obj) {
    var type = $(obj).attr("id");
    var url;
    if (type == "province") {
        url = "getCityByProvince.do";
    } else if (type == "city") {
        url = "getAreaByCity.do";
    }
    var code = $(obj).val();
    $.ajax({
        url: url,
        data: {
            "code": code
        },
        dataType: "json",
        success: function (data) {
            var cities = JSON.parse(data);
            var str = "";
            for (var i in cities) {
                str += "<option value='" + cities[i].code + "'>" + cities[i].name + "</option>"
            }
            if (type == "province") {
                $("#city").html(str);
                if (str == "") {
                    $("#area").html("");
                } else {
                    setPlace($("#city"));
                }
            } else if (type == "city") {
                $("#area").html(str);
            }
        }
    });
}
function validatePlace(obj) {
    var code = $(obj).val();
    var reg = /(^[0-9]*$)/;
    if (!reg.test(code)) {
        layer.tips(regist_validate_number_only, obj, {tips: 2});
        return;
    } else if (code == "" || code.length < 6) {
        return;
    } else {
        $.ajax({
            url: "validatePlace.do",
            data: {"code": code},
            type: "get",
            dataType: "json",
            success: function (data) {
                var result = eval("(" + data + ")");
                if (result.status == 400) {
                    return;
                } else if (result.status == 200) {
                    $("#province").val(result.provinceCode);
                    var cities = result["cities"];
                    var citiesHtml = "";
                    var areas = result["areas"];
                    var areasHtml = "";
                    for (var i in cities) {
                        citiesHtml += "<option value='" + cities[i].code + "'";
                        if (result.cityCode == cities[i].code) {
                            citiesHtml += "selected='selected'"
                        }
                        citiesHtml += ">" + cities[i].name + "</option>";
                    }
                    for (var i in areas) {
                        areasHtml += "<option value='" + areas[i].code + "'";
                        if (result.areaCode == areas[i].code) {
                            areasHtml += "selected='selected'"
                        }
                        areasHtml += ">" + areas[i].name + "</option>";
                    }
                    $("#city").html(citiesHtml);
                    $("#area").html(areasHtml);
                    form.render("select");
                }

            }
        });
    }

}
function toNext() {
    if (validateRegistHotelForm($("#hotel_form"))) {
        $("#hotel_form").submit();
    }
}