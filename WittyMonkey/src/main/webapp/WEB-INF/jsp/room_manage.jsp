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
    <title><fmt:message key="floor.manage.title"/></title>
</head>
<link rel="stylesheet" type="text/css" href="style/common.css"/>
<script type="text/javascript" src="js/common.js"></script>
<link rel="stylesheet" type="text/css" href="style/room_manage.css"/>
<script type="text/javascript" src="js/room_manage.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<div>
    <i class="refreshBtn layui-icon layui-btn layui-btn-small" onclick="reload();">&#x1002;</i>
    <div id="main">
        <i class="newBtn layui-icon layui-btn layui-btn-radius layui-btn-normal" onclick="showAddRoom()">&#xe61f;
            <fmt:message key="room.btn.add"/></i>
        <div class="hint">
            <div class="red"></div>
            <span>&nbsp;<fmt:message key="room.hint.checkin"/></span>&nbsp;&nbsp;
            <div class="green"></div>
            <span>&nbsp;<fmt:message key="room.hint.free"/></span>&nbsp;&nbsp;
            <div class="blue"></div>
            <span>&nbsp;<fmt:message key="room.hint.booked"/></span>&nbsp;&nbsp;
            <div class="yellow"></div>
            <span>&nbsp;<fmt:message key="room.hint.clean"/></span>&nbsp;&nbsp;
        </div>
        <br/>
        <div id="search">
            <form class="layui-form">
                <div class="searchType">
                    <select name="type" id="type" lay-filter="type" lay-verify="required">
                        <option value=""></option>
                        <option value="0" selected><fmt:message key="room.search.method.status"/></option>
                        <option value="1"><fmt:message key="room.search.method.floo_no"/></option>
                        <option value="2"><fmt:message key="room.search.method.room_no"/></option>
                        <option value="3"><fmt:message key="room.search.method.room_name"/></option>
                        <option value="4"><fmt:message key="room.search.method.person_num"/></option>
                    </select>
                </div>
                <div class="searchContent">

                </div>
                <div class="searchBtn" onclick="search()"><i class="layui-btn layui-icon">&#xe615;</i>
                </div>
            </form>
            <div id="content">
            </div>
            <div id="page"></div>
        </div>
    </div>
</div>
</body>
</html>
