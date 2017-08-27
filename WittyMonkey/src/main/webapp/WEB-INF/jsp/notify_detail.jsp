<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/5/3
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@ include file="common/iconfont.jsp" %>
<html>
<head>
    <script type="text/javascript" src="js/common.js"></script>
    <link href="style/notify.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/notify.js"></script>
</head>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<style>
    table{
        width: 98%;
    }
    tr{
        margin-bottom: 5px;
    }
</style>
<body>
<table style="width: 98%">
    <tr>
        <td class="td"><label class="layui-form-label"><fmt:message key="notify.sender"/></label></td>
        <td class="td"><input class="layui-input" disabled value="${notify.sender}"/></td>
        <td class="td"><label class="layui-form-label"><fmt:message key="notify.send_time"/></label></td>
        <td class="td"><input class="layui-input" disabled value="<fmt:formatDate value="${notify.sendDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"/></td>
    </tr>
    <tr>
        <td class="td"><label class="layui-form-label"><fmt:message key="notify.receiver"/></label></td>
        <td class="td" colspan="3"><input class="layui-input" disabled value="${notify.receivers}"/></td>
    </tr>
    <tr>
        <td class="td"><label class="layui-form-label"><fmt:message key="notify.subject"/></label></td>
        <td class="td" colspan="3"><input class="layui-input" disabled value="${notify.subject}"/></td>
    </tr>
    <tr>
        <td class="td" colspan="4"><div style="padding: 10px;min-height: 100px;border: solid 1px #c2c2c2; border-radius: 3px">${notify.content}</div></td>
    </tr>
</table>
</body>
</html>
