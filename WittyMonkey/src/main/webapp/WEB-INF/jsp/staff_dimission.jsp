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
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/staff_dimission.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
    <form class="layui-form" id="dimission_form">
        <table class="form_table">
            <tr>
                <td><label class="layui-form-label"><fmt:message key="salary.basic"/></label></td>
                <td><input class="layui-input" disabled id="basic"
                           value="<fmt:formatNumber value="${dimissionPayroll.basic}" pattern="0.00" maxFractionDigits="2"/>"
                ></td>
                <td><label class="layui-form-label"><fmt:message key="salary.leave_pay"/></label></td>
                <td><input class="layui-input" disabled id="leave" value="<fmt:formatNumber value="${dimissionPayroll.leave}" pattern="0.00" maxFractionDigits="2"/>"></td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="salary.other_pay"/></label></td>
                <td><input class="layui-input" value="0.0" onblur="calc()" type="number" name="other" id="other"></td>
                <td><label class="layui-form-label"><fmt:message key="salary.dimission_benefits"/></label></td>
                <td><input class="layui-input" value="0.0" onblur="calc()" type="number" name="benefits" id="benefits"></td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="salary.amount"/></label></td>
                <td><input class="layui-input" value="0.0" id="amount" disabled></td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="salary.dimission_note"/></label></td>
                <td colspan="3"><textarea class="layui-textarea" name="note" id="note" onblur="validateNote(this)"></textarea></td>
            </tr>
        </table>
    </form>
    <div id="btnGroup">
        <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
               onclick="closeMe()"/>
        <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="dimission.btn"/>"
               onclick="dimission()"/>
    </div>
</body>
</html>
