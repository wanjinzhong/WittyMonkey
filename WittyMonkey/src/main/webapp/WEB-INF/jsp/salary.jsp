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
    <title><fmt:message key="finance.salary.title"/></title>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/salary.js"></script>
    <link type="text/css" rel="stylesheet" href="style/salary.css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<nav class="breadcrumb">
    <i class="refreshBtn layui-icon layui-btn layui-btn-small" onclick="reload();">&#x1002;</i>
</nav>
<div id="main">
    <i class="newBtn layui-icon layui-btn layui-btn-radius layui-btn-normal" onclick="showPayroll()">&#xe61f; <fmt:message key="salary.pay"/></i>
    <form class="layui-form" style="min-width: 850px;">
        <div>
            <div class="searchTypeDiv">
                <select name="type" id="type" lay-filter="type" >
                    <option value="1"><fmt:message key="salary.search_type.range"/></option>
                    <option value="2"><fmt:message key="salary.search_type.name"/></option>
                    <option value="3"><fmt:message key="salary.search_type.staff_no"/></option>
                </select>
            </div>
            <div id="searchContent">
            </div>
            <div class="searchBtn" onclick="search()" style="display: inline-block;"><i
                    class="layui-btn layui-icon">&#xe615;</i>
            </div>
        </div>
    </form>
    <table class="layui-table" lay-skin="line" style="min-width: 850px;">
        <thead>
        <tr>
            <th width="50px"><fmt:message key="salary.staff_no"/></th>
            <th width="100px"><fmt:message key="salary.staff.name"/></th>
            <th width="50px"><fmt:message key="salary.salary"/></th>
            <th width="150px"><fmt:message key="salary.stat_date"/></th>
            <th width="100px"><fmt:message key="entry_user"/></th>
            <th width="150px"><fmt:message key="entry_date"/></th>
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
