/**
 * Created by Neil on 2017/2/19.
 */
var time = 60;
var form;
var layer;
layui.use(['layer', 'form'], function () {
    form = layui.form();
    layer = layui.layer;
});
$(document).ready(function () {
    $("#get_code").click(function () {
        var email = $("#email").val();
        if (!validateEmail($("#email"))) {
            return false;
        }
        $.ajax({
            type: "GET",
            url: "getValidateCode.do",
            data: {
                "email": email
            },
            dataType: "text",
            success: function (data) {
            }
        });
        t1 = window.setInterval("cutdown()", 1000);
        $("#get_code").removeClass("btn-secondary");
        $("#get_code").addClass("disabled");
        $("#get_code").attr("disabled", true);
        $("#get_code").val(time);
    });
});
function prev() {
    $("#user_form").attr("action", "toPrev.do");
    $("#user_form").submit();
}
function regist() {
    if (!validateRegistUserForm($("#regist_form"))) {
        return;
    }
    var myCode = $("#code").val();
    validateCode($("#code"));
    $.ajax({
        type: "POST",
        url: "regist.do",
        data: $("#user_form").serialize(),
        dataType: "json",
        success: function (data) {
            var result = eval("(" + data + ")");
            var stu = result.status;
            switch (stu) {
                case 400:
                    layer.tips(regist_input_name_first, $("#loginName"), {
                        tips: 4
                    });
                    break;
                case 401:
                    layer.tips(regist_user_is_exist, $("#loginName"), {
                        tips: 4
                    });
                    break;
                case 410:
                    layer.tips(regist_password_less_six, $("#password"), {
                        tips: 4
                    });
                    break;
                case 411:
                    layer.tips(regist_password_not_same, $("#repassword"), {
                        tips: 4
                    });
                    break;
                case 420:
                    layer.tips(regist_email_is_wrong, $("#email"), {
                        tips: 4
                    });
                    break;
                case 421:
                    layer.tips(regist_validate_email_exist, $("#email"), {
                        tips: 4
                    });
                    break;
                case 430:
                    layer.tips(regist_get_code_first, $("#get_code"), {
                        tips: 1
                    });
                    break;
                case 431:
                    layer.tips(regist_code_is_wrong, $("#code"), {
                        tips: 4
                    });
                    break;
                case 200:
                    window.location = result.url;
                    break;
                default:
                    break;
            }
        }
    });
}
function cutdown() {
    if (time > 0) {
        $("#get_code").val(time);
        time = time - 1;
    } else {
        $("#get_code").removeClass("disabled");
        $("#get_code").addClass("btn-secondary");
        $("#get_code").attr("disabled", false);
        window.clearInterval(t1);
        $("#get_code").val(regist_get_code);
        time = 60;
    }
}
