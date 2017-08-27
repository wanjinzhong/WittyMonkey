<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/4/14
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@ include file="common/iconfont.jsp" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<link type="text/css" rel="stylesheet" href="style/finance.css"/>
<script type="text/javascript" src="js/finance_type_add.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<form id="add_form" class="layui-form">
    <table class="form_table">
        <tr>
            <td><label class="layui-form-label"><fmt:message key="finance_type.name"/></label></td>
            <td><input type="text" class="layui-input" name="name"
                       id="name" onblur="validateFinanceTypeName('add',this)"></td>
        </tr>
        <tr><td><label class="layui-form-label"><fmt:message key="finance_type.in/out"/></label></td>
            <td style="text-align: left"><select name="type" style="max-height: 200px">
                <option value="1" selected><fmt:message key="finance_type.in"/></option>
                <option value="0"><fmt:message key="finance_type.out"/></option>
            </select></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="note"/></label></td>
            <td><textarea class="layui-textarea" name="note" id="note"></textarea></td>
        </tr>
    </table>
</form>
<div id="btnGroup">
    <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
           onclick="closeMe()"/>
    <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.save"/>"
           onclick="save()"/>
</div>
</body>
</html>
