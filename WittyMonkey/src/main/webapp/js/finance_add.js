/**
 * Created by neilw on 2017/4/17.
 */
var layer;
var form;
layui.use(['layer', 'form'], function () {
    layer = layui.layer;
    form = layui.form();
    form.on('select(type)', function (data) {
        changeType(data.value);
    });
    changeType(1);
});
function changeType(obj) {
    var type = obj | $("#type").val();
    var html = "";
    var load = layer.load();
    $.ajax({
        url: "getFinanceTypeByType.do",
        data: {"type": type},
        dataType: "json",
        type: "get",
        success: function (data) {
            layer.close(load);
            var res = eval("(" + data + ")");
            var html = "";
            var types = res["data"];
            for (var i in types) {
                html += '<option value="' + types[i]["id"] + '">' + types[i]["name"] + '</option>';
            }
            $("#financeType").html(html);
            form.render("select");
        }
    });
}

function save() {
    changeType(1);
}