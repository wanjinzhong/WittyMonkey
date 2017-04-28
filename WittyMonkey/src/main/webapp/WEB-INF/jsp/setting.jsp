<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/2/15
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@ include file="common/iconfont.jsp" %>
<html>
<head>
    <title><fmt:message key="setting.title"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/setting.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<form class="layui-form" id="setting_form">
    <table class="form_table">
        <tr>
            <td><label class="layui-form-label"><fmt:message key="setting.dataPerPage"/></label></td>
            <td><input class="layui-input" type="number" id="dataPerPage" name="dataPerPage"/></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="setting.lang"/></label></td>
            <td>
                <select id="lang" name="lang">
                    <option value="zh_CN"><fmt:message key="lang.ch"/></option>
                    <option value="en_US"><fmt:message key="lang.en"></fmt:message></option>
                </select>
            </td>
        </tr>
    </table>
</form>
<div id="btnGroup" >
    <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="setting.reset"/>"
           onclick="getDate()"/>
    <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.save"/>"
           onclick="save()"/>
</div>
</body>
</html>
