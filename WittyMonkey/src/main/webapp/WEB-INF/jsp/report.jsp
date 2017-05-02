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
    <title><fmt:message key="report.title"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
</head>
<%--Echart--%>
<script type="text/javascript" src="lib/echarts.common.min.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/report.js"></script>
<body>
<input type="hidden" id="lang" value="${loginUser.setting.lang }"/>
<form class="layui-form" style="min-width: 700px">
    <div>
        <input lay-filter="type" type="radio" name="type" value="1" title="<fmt:message key="report.last_month"/>"
               checked/>
        <input lay-filter="type" type="radio" name="type" value="2" title="<fmt:message key="report.last_year"/>"/>
        <input lay-filter="type" type="radio" name="type" value="3" title="<fmt:message key="report.custom"/>"/>
        <input class="layui-input date" id="from" placeholder="<fmt:message key="start_date_hint"/>"
               style="display: inline-block; width: 150px"/>
        -
        <input class="layui-input date" id="to" placeholder="<fmt:message key="end_date_hint"/>"
               style="display: inline-block;width: 150px"/>
        <div class="searchBtn" onclick="search()"><i class="layui-btn layui-icon">&#xe615;</i></div>
    </div>
</form>
<div style="margin: 10px; margin-bottom: 50px;">
    <div style="width: 300px">
        <div style="width: 300px; height:300px;" id="totalInOut"></div>
        <div id="totalHint" style="text-align: center; margin-top:  -30px; font-size: 18px; font-weight: bold;"></div>
    </div>
    <div>
        <div style="width: 45%; height:400px; display: inline-block;" id="inReport"></div>
        <div style="width: 45%; height:400px;display: inline-block;" id="outReport"></div>
    </div>
</div>
</body>
</html>
