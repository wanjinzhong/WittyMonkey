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
    <script type="text/javascript" src="js/common.js"></script>
    <%--alibaba iconfont字体图标--%>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
    <script type="text/javascript" src="js/regist_validator.js"></script>
    <link rel="stylesheet" href="style/regist.css"/>
    <script type="text/javascript" src="js/regist_user.js"></script>
    <fmt:setBundle basename="i18n/messages"/>
</head>
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
                    <td class="td_title"><label class="layui-form-label"><fmt:message
                            key="regist.user.real_name"/></label></td>
                    <td><input type="text" class="layui-input"  id="realName"
                               name="realName" onblur="validateRealName(this)"
                               value="${registUser.realName}"/></td>
                </tr>
                <tr>
                    <td><label class="layui-form-label"><fmt:message key='regist.user.password'/></label></td>
                    <td class="td_title"><input id="password" name="password"
                                                type="password" class="layui-input"
                                                onblur='validatePassword(this)'/></td>
                </tr>
                <tr>
                    <td><label class="layui-form-label"><fmt:message key='regist.user.repassword'/></label></td>
                    <td class="td_title"><input id="repassword" name="repassword"
                                                type="password" class="layui-input"
                                                onblur="validateRepassword(this)"/></td>
                </tr>
                <tr>
                    <td><label class="layui-form-label"><fmt:message key="regist.user.idcard"/></label></td>
                    <td><input type="text" class="layui-input"  id="idCard"
                               name="idCard" onblur="validateIdCard(this)"
                               value="${registUser.idCardNo}"/></td>
                </tr>
                <tr>
                    <td><label class="layui-form-label"><fmt:message key='regist.user.email'/></label></td>
                    <td class="td_title"><input id="email" name="email"
                                                type="text" class="layui-input"
                                                onblur="validateEmail(this)"
                                                value="${registUser.email}"/></td>
                </tr>
                <tr>
                    <td><label class="layui-form-label"><fmt:message key='regist.user.code'/></label></td>
                    <td class="td_title">
                        <div class="codeInpDiv"><input id="code" name="code" type="text"
                                    class="layui-input"  value="${registCode}"/>
                        </div>
                        <input type="button"
                               id="get_code"
                               class="layui-btn layui-btn-radius layui-btn-normal"
                               value="<fmt:message key='regist.user.get_code'/>">
                    </td>
                </tr>
            </table>
        </form>
    </div>
    <div id="btnGroup">
        <input id="closeBtn" type="button"
               value="<fmt:message key="regist.btn.close"/>"
               class="layui-btn layui-btn-radius layui-btn-danger" onclick="closeMe()"/> <input
            type="button" value="<fmt:message key="regist.btn.prev"/>"
            onclick="prev()"
            class="layui-btn layui-btn-radius" id="prevBtn"/><input
            id="confirmBtn" type="button"
            value="<fmt:message key="regist.btn.confirm"/>"
            class="layui-btn layui-btn-radius" onclick="regist()"/>
    </div>
</div>
</body>
</html>