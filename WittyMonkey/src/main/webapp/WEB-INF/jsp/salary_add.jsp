<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/4/20
  Time: 14:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@ include file="common/iconfont.jsp" %>
<html>
<head>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/salary_add.js"></script>
    <!-- 根据设置动态加载js语言 -->
    <script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
    <fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
</head>
<body>
<input type="hidden" id="lang" value="${loginUser.setting.lang}"/>
    <form id="add_form">
        <input type="hidden" id="id" name="id" value="${id}"/>
        <table class="form_table" style="width: 600px; padding-top: 20px">
            <tr>
                <td><label class="layui-form-label"><fmt:message key="salary.salary"/></label></td>
                <td><input class="layui-input" type="number" onblur="validateMoney(this)" name="salary" id="salary"/></td>
                <td><label class="layui-form-label" style="width:80px"><fmt:message key="start_date_hint"/></label></td>
                <td><input class="layui-input date" id="startDate" name="startDate"/></td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="note"/></label></td>
                <td colspan="3"><textarea class="layui-textarea" name="note" id="note" onblur="validateNote(this)"></textarea></td>
            </tr>
        </table>
    </form>
    <div id="btnGroup">
        <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.back"/>"
               onclick="toBack()"/>
        <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.save"/>"
               onclick="save()"/>
    </div>
</body>
</html>
