/**
 * Created by neilw on 2017/4/25.
 */
var layer;
var form;
var laypage;
layui.use(['layer', 'form', 'laypage'], function () {
        layer = layui.layer;
        form = layui.form();
        laypage = layui.laypage;
        form.on('select(searchType)', function (data) {
            changeType();
        });
        page("getInstockByPage.do", undefined, {"searchType": 0});
    }
);
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

function changeType() {
    var type = $("#searchType").val();
    var content = "";
    if (type == 1) {
        content = "<select id='type' class='type'>";
        $.ajax({
            url: "getAllMaterielTypeByHotel.do",
            dataType: "json",
            type: "GET",
            async: false,
            success: function (data) {
                var res = eval("(" + data + ")");
                for (var i in res) {
                    content += "<option value='" + res[i]["id"] + "'>" + res[i]["name"] + "</option>"
                }
            }
        });
        content += "</select>";
    } else if (type == 2) {
        content = "<input type='text' class='layui-input' id='barcode'/>"
    } else if (type == 3) {
        content = "<input type='text' class='layui-input' id='name'/>"
    }
    $("#searchContent").html(content);
    form.render("select");
}

function refreshTable(obj) {
    var html = "";
    if (obj.length == 0) {
        html = '<tr class="text-c">' +
            '<td colspan="7">' + no_data + '</td>' +
            '</tr>"';
    } else {
        for (var i in obj) {
            html += "<tr class='text-c'>" +
                "<td>" + (obj[i]["materiel"] == undefined ? instock_materiel_null : obj[i]["materiel"]) + "</td>" +
                "<td>" + obj[i]["purchasePrice"] + "</td>" +
                "<td>" + obj[i]["quantity"] + "</td>" +
                "<td>" + obj[i]["payment"] + "</td>" +
                "<td>" + obj[i]["note"] + "</td>" +
                "<td>" + obj[i]["entryUser"] + "</td>" +
                "<td>" + formatDate(obj[i]["entryDatetime"]) + "</td>" +
                "</tr>";
        }
    }
    $("#dataTable").html(html);
}

function search() {
    var condition = "";
    var searchType = $("#searchType").val();
    var from = $("#from").val();
    var to = $("#to").val();
    if (searchType == 0) {
        condition = {"searchType": searchType, "from": from, "to": to};
    } else if (searchType == 1) {
        var type = $("#type").val();
        condition = {"searchType": searchType, "type": type, "from": from, "to": to};
    } else if (searchType == 2) {
        var barcode = $("#barcode").val();
        condition = {"searchType": searchType, "barcode": barcode, "from": from, "to": to};
    } else if (searchType == 3) {
        var name = $("#name").val();
        condition = {"searchType": searchType, "name": name, "from": from, "to": to};
    }
    page("getInstockByPage.do", undefined, condition);
}

function showInStock() {
    layer.open({
        type: 2,
        area: ['800px', '350px'],
        maxmin: false,
        shade: 0.4,
        title: instock_add,
        content: "toInStock.do"
    });
}