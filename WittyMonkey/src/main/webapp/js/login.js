/**
 * Created by Neil on 2017/2/18.
 */
var layer;
layui.use(['layer','form'],function () {
    layer = layui.layer;
    var form = layui.form();
    $("#regist").click(function() {
        layer.open({
            type : 2,
            area : [ '750px', '650px' ],
            maxmin : false,
            shade : 0.4,
            title : regist_title,
            content : "toRegist.do",
            scrollbar: false
        });

    });
});
    function changeCode() {
        var time = new Date().getTime();
        $("#codeImage").attr("src", "ValidateCodeServlet?time=" + time);
    }
    function login() {
        var form = $("#login_form");
        if ($("#loginName").val() == "") {
            layer.tips(regist_input_name_first, $("#loginName"),
                {
                    tips : 4
                });
            return;
        } else if ($("#password").val() == "") {
            layer.tips(regist_input_password_first,
                $("#password"), {
                    tips : 4
                });
            return;
        } else if ($("#validateCode").val() == "") {
            layer.tips(regist_input_validate_code_first,
                $("#validateCode"), {
                    tips : 4
                });
            return;
        } else {
            var code = $("#validatePicCode").val();
            var load = layer.load();
            $.ajax({
                type : "POST",
                url : "login.do",
                data : $("#login_form").serialize(),
                dataType : "json",
                success : function(data) {
                    layer.close(load);
                    var result = eval("(" + data + ")");
                    switch (result.status) {
                        case 400:
                            layer.msg(regist_input_name_first, {
                                time : 3000,
                                icon : 5
                            });
                            return;
                            break;
                        case 410:
                            layer.msg(regist_input_password_first, {
                                time : 3000,
                                icon : 5
                            });
                            return;
                            break;
                        case 420:
                            layer.msg(regist_input_validate_code_first, {
                                time : 3000,
                                icon : 5
                            });
                            return;
                            break;
                        case 421:
                            layer.msg(regist_code_is_wrong, {
                                time : 3000,
                                icon : 5
                            });
                            return;
                            break;
                        case 430:
                            layer.msg(regist_login_error, {
                                time : 3000,
                                icon : 5
                            });
                            return;
                            break;
                        case 200:
                            window.location = result.url;
                            break;
                    }
                },
            });
        }
    }
//});
