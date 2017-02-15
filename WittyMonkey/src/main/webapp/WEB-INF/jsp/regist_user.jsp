<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String contextPath = request.getContextPath();
    String lang;
    if (request.getLocale().equals(java.util.Locale.US)) {
        lang = "en_US";
    } else {
        lang = "zh_CN";
    }
%>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@include file="common/iconfont.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="name"/></title>
    <%--alibaba iconfont字体图标--%>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <fmt:setBundle basename="i18n/messages"/>
</head>
<style type="text/css">
    .img_circle {
        height: 50px;
        width: 50px;
    }

    .img_line {
        height: 50px;
        width: 250px;
    }

    #proccess {
        position: absolute;
        left: 50px;
        top: 10px;
    }

    #regist_hotel {
        position: absolute;
        top: 100px;
        left: 30px;
    }

    .regist_step {
        font-size: 12px;
    }

    .hotel {
        position: absolute;
        left: -18px;
    }

    .user {
        position: absolute;
        left: 275px;
        font-size: 12px;
        font-weight: bold;
    }

    .complete {
        position: absolute;
        left: 615px;
    }

    #regist_form {
        border: 2px solid #eeeeee;
        border-radius: 10px;
        width: 690px;
        height: 330px;
        margin-top: 10px;
    }

    table {
        width: 500px;
        margin: 0 auto;
        margin-top: 10px;
    }

    td {
        padding: 5px;
    }

    select {
        width: 100px;
    }

    #form_title {
        line-height: 40px;
        margin-left: 10px;
        vertical-align: top;
    }

    #code {
        width: 280px;
    }

    #get_code {
        float: right;
        width: 100px;
        height: 31px;
    }

    #icon_user {
        width: 40px;
        height: 40px;
        margin-left: 10px;
    }
</style>
<body>
<fmt:setBundle basename="i18n/messages"/>
<div id="proccess">
    <img class="img_circle" src="pic/regist/circle_green.png"/><img
        class="img_line" src="pic/regist/line_green.png"/><img
        class="img_circle" src="pic/regist/circle_green.png"/><img
        class="img_line" src="pic/regist/line_gray.png"/><img
        class="img_circle" src="pic/regist/circle_gray.png"> <br/> <span
        class="regist_step hotel"><fmt:message key="regist.step.hotel"/></span>
    <span class="regist_step user"><fmt:message
            key="regist.step.user"/></span> <span class="regist_step complete"><fmt:message
        key="regist.step.complete"/></span>
</div>
<div id="regist_hotel">
    <svg id="icon_user" class="icon" aria-hidden="true">
        <use xlink:href="#icon-yonghuzhuce"></use>
    </svg>
    <span
            id="form_title"><fmt:message key="regist.user.title"/></span>
    <div id="regist_form">
        <form id="user_form" method="post">
            <table>
                <tr>
                    <td class="td_title"><fmt:message
                            key="regist.user.login_name"/></td>
                    <td><input type="text" class="input-text radius"
                               name="loginName" id="loginName" onblur="validateLoginName(this)"
                               value="${registUser.loginName}"/></td>
                </tr>
                <tr>
                    <td class="td_title"><fmt:message
                            key="regist.user.real_name"/></td>
                    <td><input type="text" class="input-text radius" id="realName"
                               name="realName" onblur="validateRealName(this)"
                               value="${registUser.realName}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key='regist.user.password'/></td>
                    <td class="td_title"><input id="password" name="password"
                                                type="password" class="input-text radius"
                                                onblur='validatePassword(this)'/></td>
                </tr>
                <tr>
                    <td><fmt:message key='regist.user.repassword'/></td>
                    <td class="td_title"><input id="repassword" name="repassword"
                                                type="password" class="input-text radius"
                                                onblur="validateRepassword(this)"/></td>
                </tr>
                <tr>
                    <td><fmt:message key="regist.user.idcard"/></td>
                    <td><input type="text" class="input-text radius" id="idCard"
                               name="idCard" onblur="validateIdCard(this)"
                               value="${registUser.idCardNo}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key='regist.user.email'/></td>
                    <td class="td_title"><input id="email" name="email"
                                                type="text" class="input-text radius"
                                                onblur="validateEmail(this)"
                                                value="${registUser.email}"/></td>
                </tr>
                <tr>
                    <td><fmt:message key='regist.user.code'/></td>
                    <td class="td_title"><input id="code" name="code" type="text"
                                                class="input-text radius" value="${registCode}"/>
                        <input type="button"
                               id="get_code"
                               class="btn btn-secondary radius"
                               value="<fmt:message key='regist.user.get_code'/>">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="btnGroup">
        <input id="closeBtn" type="button"
               value="<fmt:message key="regist.btn.close"/>"
               class="btn btn-danger radius" onclick="closeMe()"/> <input
            type="button" value="<fmt:message key="regist.btn.prev"/>"
            onclick="prev()"
            class="btn btn-success radius" id="prevBtn"/><input
            id="confirmBtn" type="button"
            value="<fmt:message key="regist.btn.confirm"/>"
            class="btn btn-success radius" onclick="regist()"/>
    </div>
</div>
</body>
<script type="text/javascript">
    var time = 60;
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

</script>
</html>