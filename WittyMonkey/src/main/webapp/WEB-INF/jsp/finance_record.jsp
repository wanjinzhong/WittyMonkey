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
    <title><fmt:message key="finance.add.title"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/finance_record.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>

<div>
    <nav class="breadcrumb">
        <i class="refreshBtn layui-icon layui-btn layui-btn-small" onclick="reload();">&#x1002;</i>
    </nav>
    <div id="main">
        <i class="newBtn layui-icon layui-btn layui-btn-radius layui-btn-normal" onclick="showAddFinance()">&#xe61f;
            <fmt:message key="finance.add"/></i>
        <form class="layui-form">
            <div style="width: 200px; margin: 0 auto">
                <select name="type" id="type" lay-filter="type" lay-verify="required" onchange="changeType()">
                    <option value="2" selected><fmt:message key="all"/></option>
                    <option value="1"><fmt:message key="finance_type.in"/></option>
                    <option value="0"><fmt:message key="finance_type.out"/></option>
                </select>
            </div>
        </form>
        <table class="layui-table" lay-skin="line">
            <thead>
            <tr>
                <th width="150px"><fmt:message key="finance.time"/></th>
                <th width="100px"><fmt:message key="finance.type"/></th>
                <th width="50px"><fmt:message key="finance.in/out"/></th>
                <th width="50px"><fmt:message key="finance.money"/></th>
                <th width="200px"><fmt:message key="note"/></th>
                <th width="50px"><fmt:message key="floor.manage.entry_user"/></th>
                <th width="150px"><fmt:message key="operation"/></th>
            </tr>
            </thead>
            <tbody id="dataTabel">
            </tbody>
        </table>
        <div id="page"></div>
    </div>
</div>
</body>
</html>
