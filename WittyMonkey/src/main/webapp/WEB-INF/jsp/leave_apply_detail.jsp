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
<%@include file="common/iconfont.jsp" %>
<html>
<head>
</head>
<link rel="stylesheet" type="text/css" href="style/common.css"/>
<link rel="stylesheet" type="text/css" href="style/reimburse.css"/>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/leave_apply_add&update.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<style type="text/css">
    .layui-form-select dl {
        max-height: 150px;
    }
</style>
<body>
<input hidden value="${loginUser.setting.lang}" id="lang"/>
<form id="leave_form" class="layui-form">
    <input type="hidden" name="method" value="update" id="method"/>
    <table class="form_table" width="550px">
        <tr>
            <td><label class="layui-form-label"><fmt:message key="leave.date"/></label></td>
            <td><input class="layui-input" disabled
                       value="<fmt:formatDate value="${leave.from}"/> - <fmt:formatDate value="${leave.to}"/>"/>
            </td>
            <td><label class="layui-form-label"><fmt:message key="leave.days"/></label></td>
            <td><input class="layui-input" value="${leave.days}" disabled/></td>
        </tr>
        <tr>
            <td><label class="layui-form-label" style="margin-left: 10px"><fmt:message key="leave.type"/></label></td>
            <td><input class="layui-input" value="${leave.leaveType}" disabled/></td>
            <td><label class="layui-form-label"><fmt:message key="leave.deduct"/></label></td>
            <td><input class="layui-input" value="${leave.deduct}" disabled/></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="leave.apply_note"/></label></td>
            <td colspan="3"><textarea class="layui-textarea" disabled>${leave.applyUserNote}</textarea></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="leave.opt_user"/></label></td>
            <td><input class="layui-input" value="${leave.entryUser}" disabled/>
            </td>
            <td><label class="layui-form-label"><fmt:message key="leave.opt_date"/></label></td>
            <td><input class="layui-input" value="<fmt:formatDate value="${leave.entryDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled/></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="leave.apply_note"/></label></td>
            <td colspan="3"><textarea class="layui-textarea" disabled>${leave.entryUserNote}</textarea></td>
        </tr>
    </table>
</form>
<div id="btnGroup">
    <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
           onclick="closeMe()"/>
</div>
</body>
</html>
