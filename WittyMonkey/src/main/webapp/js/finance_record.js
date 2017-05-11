/**
 * Created by neilw on 2017/4/17.
 */
var layer;
var laypage;
var form;
layui.use(['layer', 'laypage', 'form'], function () {
    layer = layui.layer;
    laypage = layui.laypage;
    form = layui.form();
    page("getFinanceByPage.do", undefined, {"type": 2});
    form.on('select(searchType)', function (data) {
        searchTypeChange();
    });
    searchTypeChange(1);
    search();
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
});
function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="6">' + no_data + '</td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            html += "<tr class='text-c'>" +
                "<td>" + formatDate(obj[i].entryDatetime) + "</td>" +
                "<td>" + obj[i].financeType + "</td>" +
                "<td>" + (obj[i].income ? finance_type_in : finance_type_out) + "</td>" +
                "<td>" + obj[i].money + "</td>" +
                "<td>" + ((obj[i].note == undefined) ? "" : obj[i].note) + "</td>" +
                "<td>" + obj[i].entryUser + "</td>";
                // html += ""<td>"<i class='editBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='editFinance(" + obj[i].id + ")'>&#xe642; " + btn_edit + "</i>" +
                //     "<i class='deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small' onclick='deleteFinance(" + obj[i].id + ")'>&#xe640; " + btn_delete + "</i></td>";
            html += "</tr>";
        }
    }
    $("#dataTabel").html(html);
}

function searchTypeChange() {
    var type = $("#searchType").val();
    //page("getFinanceByPage.do", undefined, {"type": type});
    var html = '<select name="type" id="type" lay-filter="type" lay-verify="required" onchange="changeType()">';
    if (type == 2){
        html += '<option value="-3">' +finance_type_all + '</option>';
    } else if (type == 1 || type == 0){
        var load = layer.load();
        $.ajax({
            type: "get",
            data: {"type": type},
            dataType: "json",
            url: "getFinanceTypeByType.do",
            async: false,
            success: function (data) {
                var res = eval("(" + data + ")");
                layer.close(load);
                var types = res["data"];
                if (type == 1) {
                    html += '<option value="-2">' + finance_type_all + '</option>';
                } else if (type == 0){
                    html += '<option value="-1">' + finance_type_all + '</option>';
                }
                for (var i in types){
                    html += '<option value="' + types[i]["id"] + '">' + types[i]["name"] + '</option>';
                }
            }
        });
    }
    html += "</select>";
    $("#typeDiv").html(html);
    form.render("select");
}
function search(){
    var type = $("#type").val();
    var from = $("#from").val();
    var to = $("#to").val();
    var condition = {"type": type, "from": from, "to": to};
    page("getFinanceByPage.do", undefined, condition);
}
function showAddFinance() {
    layer.open({
        type: 2,
        area: ['770px', '350px'],
        maxmin: false,
        shade: 0.4,
        title: finance_add,
        content: "toAddFinance.do"
    });
}