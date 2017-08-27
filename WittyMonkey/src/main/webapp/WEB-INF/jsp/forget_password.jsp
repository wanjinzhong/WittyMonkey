<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/5/19
  Time: 15:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="name"/></title>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
    <script type="text/javascript" src="js/forget_password.js"></script>
    <script type="text/javascript" src="js/regist_validator.js"></script>
    <link type="text/css" rel="stylesheet" href="style/login.css"/>
    <fmt:setBundle basename="i18n/messages"/>
</head>
<body>
<form id="data_form">
    <table style="width: 500px; margin-left: 8px;">
        <tr>
            <td><label class="layui-form-label"><fmt:message key="salary.staff_no"/></label></td>
            <td style="width: 300px"><input class="layui-input" type="number" name="staffNo" id="staffNo" onblur="validateStaffNo(this)"/></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="checkin.idcard"/></label></td>
            <td style="width: 300px"><input class="layui-input" name="idcard" id="idcard" onblur="validateIdCard(this)"/></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="new.password"/></label></td>
            <td style="width: 300px"><input class="layui-input" type="password" name="newPassword" id="password" onblur="validatePassword(this)"/></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="repassword"/></label></td>
            <td style="width: 300px"><input class="layui-input" type="password" name="rePassword" id="rePassword" onblur="validateRepassword(this)"/></td>
        </tr>
    </table>
</form>
<div id="btnGroup">
    <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
           onclick="closeMe()"/>
    <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.save"/>"
           onclick="changePassword()"/>
</div>
</body>
</html>
