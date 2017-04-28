/**
 * Created by neilw on 2017/4/28.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
    getDate();
});

function getDate() {
    var load = layer.load();
    $.ajax({
        url: "getSetting.do",
        dataType: "JSON",
        type: "GET",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            $("#dataPerPage").val(res["setting"]["pageSize"]);
            $("#lang").val(res["setting"]["lang"]);
            form.render("select");
        }
    });
}

function save() {
    var load = layer.load();
    $.ajax({
        url: "saveSetting.do",
        data: $("#setting_form").serialize(),
        dataType: "JSON",
        type: "POST",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            switch (res['status']) {
                case 400:
                    layer.tips(messageOfValidateNull(setting_data), $("#dataPerPage"), {tips: 2});
                    break;
                case 401:
                    layer.tips(setting_data_wrong, $("#dataPerPage"), {tips: 2});
                    break;
                case 200:
                    layer.msg(setting_save_success, {
                        icon: 1,
                        time: 2000
                    });
                    break;
            }
        }
    });
}