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
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/common.js"></script>
    <link href="style/notify.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/notify.js"></script>
</head>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<nav class="breadcrumb">
    <i class="refreshBtn layui-icon layui-btn layui-btn-small" onclick="reload();">&#x1002;</i>
</nav>
<div style="margin: 0 20px;">
    <i class="newBtn layui-icon layui-btn layui-btn-radius layui-btn-normal" onclick="showAddNotify()">&#xe61f;
        <fmt:message key="notify.add"/></i>
    <div class="layui-tab layui-tab-card" style="height: 90%" lay-filter="box-tab">
        <ul class="layui-tab-title">
            <li class="layui-this" lay-id="1"><fmt:message key="notify.inbox"/></li>
            <li lay-id="2"><fmt:message key="notify.outbox"/></li>
            <li lay-id="3"><fmt:message key="notify.Trash"/></li>
        </ul>
        <div class="layui-tab-content" style="padding: 10px;">
            <div class="layui-tab-item layui-show">
                <div id="inbox"></div>
                <div id="inbox_page"></div>
            </div>
            <div class="layui-tab-item">
                <div id="outbox"></div>
                <div id="outbox_page"></div>
            </div>
            <div class="layui-tab-item">
                <div id="trash"></div>
                <div id="trashbin_page"></div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
