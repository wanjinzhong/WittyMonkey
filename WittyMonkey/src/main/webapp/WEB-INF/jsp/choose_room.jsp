<%--
  Created by IntelliJ IDEA.
  User: neil
  Date: 17-3-9
  Time: 下午9:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@ include file="common/iconfont.jsp" %>
<html>
<head>
    <title><fmt:message key="checkin.change_room.title"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <link href="style/choose_room.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/choose_room.js"></script>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<div class="container">
    <input type="hidden" value="${checkinId}" id="checkinId"/>
    <table class="layui-table">
        <thead>
        <th><fmt:message key="room.add.number"/></th>
        <th><fmt:message key="room.add.name"/></th>
        <th><fmt:message key="room.add.single_bed.num"/></th>
        <th><fmt:message key="room.add.double_bed.num"/></th>
        <th><fmt:message key="room.add.availabel.num"/></th>
        <th><fmt:message key="room.add.price"/></th>
        <th><fmt:message key="operation"/></th>
        </thead>
        <tbody id="dataTabel">
        </tbody>
    </table>
    <div id="page"/>
</div>
</body>
</html>