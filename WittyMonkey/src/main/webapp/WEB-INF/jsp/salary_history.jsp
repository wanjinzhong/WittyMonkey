<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/4/21
  Time: 10:26
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
    <%--Echart--%>
    <script type="text/javascript" src="lib/echarts.common.min.js"></script>
    <script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
    <fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
    <script type="text/javascript" src="js/salary_history.js"></script>
</head>
<body>
    <input type="hidden" id="id" value="${id}"/>
    <div id="main" style="width: 800px; height: 350px; margin: 10px auto"></div>
</body>
</html>
