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
</head>
<script type="text/javascript" src="js/common.js"></script>
<link type="text/css" rel="stylesheet" href="style/finance.css"/>
<script type="text/javascript" src="js/leave_apply.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<input type="hidden" id="lang" value="${loginUser.setting.lang }"/>
<div>
    <nav class="breadcrumb">
        <i class="refreshBtn layui-icon layui-btn layui-btn-small" onclick="reload();">&#x1002;</i>
    </nav>
    <div id="main">
        <i class="newBtn layui-icon layui-btn layui-btn-radius layui-btn-normal" onclick="showAddLeave()">&#xe61f;
            <fmt:message key="leave.apply.add"/></i>
        <form class="layui-form">
            <div style="min-width: 800px">
                <div class="searchTypeDiv">
                    <select name="type" id="type" lay-filter="type" >
                        <option value="0" selected><fmt:message key="leave.status.all"/></option>
                        <option value="1"><fmt:message key="leave.status.pending"/></option>
                        <option value="2"><fmt:message key="leave.status.approved"/></option>
                        <option value="3"><fmt:message key="leave.status.rejected"/></option>
                    </select>
                </div>
            </div>
        </form>
        <table class="layui-table" lay-skin="line" style="min-width: 980px">
            <thead>
            <tr>
                <th width="150px"><fmt:message key="leave.date"/></th>
                <th width="50px"><fmt:message key="leave.days"/></th>
                <th width="100px"><fmt:message key="leave.type"/></th>
                <th width="50px"><fmt:message key="leave.status"/></th>
                <th width="100px"><fmt:message key="leave.opt_user"/></th>
                <th width="100px"><fmt:message key="leave.opt_date"/></th>
                <th width="100px"><fmt:message key="operation"/></th>
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
