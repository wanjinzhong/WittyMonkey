/**
 * Created by neilw on 2017/4/11.
 */
var layer;
layui.use(['layer'], function (){
    layer = layui.layer;
});
function save() {
    if (!validateRealName($("#realName"))){
        return false;
    }
    if (!validateIdCard($("#idCard"))){
        return false;
    }
    if (!validateTel($("#tel"))){
        return false;
    }
    if (!validateEmail($("#email"))){
        return false;
    }
    $.ajax({
        url: "saveStaff.do",
        data: $("#add_form").serialize(),
        dataType: "JSON",
        Type: "get",
        success: function (data) {
            var res = eval("(" + data + ")");
            switch (res["status"]){

            }
        }
    });
}