<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/4/20
  Time: 14:03
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
    <script type="text/javascript" src="js/salary_record.js"></script>
    <!-- 根据设置动态加载js语言 -->
    <script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
    <fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
</head>
<body>
    <input type="hidden" id="salaryId" value="${salary.id}"/>
    <div class="container" style="margin: 10px 10px">
        <i class="newBtn layui-icon layui-btn layui-btn-radius layui-btn-normal" onclick="showAddSalaryRecord(${salary.id})">&#xe61f; <fmt:message key="btn.add"/></i>
        <table class="layui-table" style="min-width: 850px;">
            <thead>
                <th width="50px"><fmt:message key="salary.salary"/></th>
                <th width="80px"><fmt:message key="salary.stat_date"/></th>
                <th><fmt:message key="note"/></th>
                <th width="80px"><fmt:message key="entry_user"/></th>
                <th width="150px"><fmt:message key="entry_date"/></th>
                <th width="150px"><fmt:message key="operation"/></th>
            </thead>
            <tbody id="dataTable">
            </tbody>
        </table>
        <div id="page"></div>
    </div>
</body>
</html>
