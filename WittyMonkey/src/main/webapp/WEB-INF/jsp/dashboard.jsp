<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/5/2
  Time: 19:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@include file="common/iconfont.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/common.js"></script>
    <link rel="stylesheet" href="style/dashboard.css"/>
    <script src="js/dashboard.js"></script>
    <script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
    <fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
</head>
<body>
<input type="hidden" id="lang" value="${loginUser.setting.lang}"/>
<nav class="breadcrumb">
    <i class="refreshBtn layui-icon layui-btn layui-btn-small" onclick="reload();">&#x1002;</i>
</nav>
<div class="panes">
    <div class="pane" id="room">
        <div class="title" id="room_title">&nbsp;&nbsp;<fmt:message key="dashboard.room"/></div>
        <div class="content" id="room_content"></div>
    </div>
    <div class="pane" id="inventory">
        <div class="title" id="inventory_title">&nbsp;&nbsp;<fmt:message key="dashboard.inventory"/></div>
        <div class="content" id="inventory_content"></div>
    </div>
    <div class="pane" id="salary">
        <div class="title" id="salary_title">&nbsp;&nbsp;<fmt:message key="dashboard.salary"/></div>
        <div class="content" id="salary_content"></div>
    </div>
    <div class="pane" id="leave">
        <div class="title" id="leave_title">&nbsp;&nbsp;<fmt:message key="dashboard.leave"/></div>
        <div class="content" id="leave_content"></div>
    </div>
    <div class="pane" id="reimburse">
        <div class="title" id="reimburse_title">&nbsp;&nbsp;<fmt:message key="dashboard.reimburse"/></div>
        <div class="content" id="reimburse_content"></div>
    </div>
</div>
</body>
</html>
