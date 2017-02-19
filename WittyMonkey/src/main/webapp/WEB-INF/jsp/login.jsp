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
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="name"/></title>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
    <script type="text/javascript" src="js/login.js"></script>
    <link type="text/css" rel="stylesheet" href="style/login.css"/>
    <fmt:setBundle basename="i18n/messages"/>
</head>
<body id="b" background="pic/login/login.png">
<div class="responsive">
    <img id="logo" src="pic/logo.gif"/>
    <div class="layui-form">
        <form id="login_form" style=" background-image: url(pic/login/login_background.png);" class="layui-form"
              action="login.do" method="post">
			<span id="fullname">
				<fmt:message key="fullname"/>
			</span>
            <table id="login_table">
                <tr>
                    <td>
                        <input id="loginName" name="loginName" type="text"
                               placeholder="<fmt:message key='login.loginname'/>"
                               class="layui-input"/></td>
                </tr>
                <tr>
                    <td><input id="password" name="password" type="password"
                               placeholder="<fmt:message key='login.password'/>"
                               class="layui-input"/></td>
                </tr>
                <tr>
                    <td>
                        <div style="width: 200px; display: inline-block;">
                            <input type="text" id="code" name="code"
                                   class="layui-input"
                                   placeholder="<fmt:message key='login.validateCode'/>"/>
                        </div>
                        <a href="javascript:changeCode();">
                            <img id="codeImage" src="ValidateCodeServlet"/></a></td>
                </tr>
                <tr>
                    <td><input type="button" class="layui-btn layui-btn-radius"
                               value="<fmt:message key="login.loginbtn"/>" onclick="login()"/> <input
                            type="button" id="regist" class="layui-btn layui-btn-radius layui-btn-normal"
                            value="<fmt:message key="login.registbtn"/>"></td>
                </tr>
            </table>

        </form>
    </div>
</div>
</body>
</html>