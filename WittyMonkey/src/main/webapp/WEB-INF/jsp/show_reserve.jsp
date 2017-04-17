<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/3/8
  Time: 8:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@ include file="common/iconfont.jsp" %>
<html>
<head>
    <title><fmt:message key="checkin.checkout.title"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <link href="style/show_reserve.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/show_reserve.js"></script>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<div class="container">
    <input type="hidden" disabled value="${room.id}" id="roomId"/>
    <table class="layui-table" lay-skin="line">
        <thead>
        <th width="50px"><fmt:message key="show_reserve.reserver"/></th>
        <th width="120px"><fmt:message key="show_reserve.tel"/></th>
        <th width="100px"><fmt:message key="show_reserve.reserve_date"/></th>
        <th width="100px"><fmt:message key="show_reserve.est_checkin_date"/></th>
        <th width="100px"><fmt:message key="show_reserve.est_checkout_date"/></th>
        <th width="50px"><fmt:message key="room.reserve.deposit"/></th>
        <th width="100px"><fmt:message key="operation"/></th>
        </thead>
        <tbody id="dataTabel"></tbody>
    </table>
    <div id="page"></div>
</div>
</body>
</html>