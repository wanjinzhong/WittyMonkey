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
<script type="text/javascript" src="js/finance_record.js"></script>
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
        <i class="newBtn layui-icon layui-btn layui-btn-radius layui-btn-normal" onclick="showAddFinance()">&#xe61f;
            <fmt:message key="finance.add"/></i>
        <form class="layui-form">
            <div style="min-width: 850px">
                <div class="searchTypeDiv">
                    <select name="searchType" id="searchType" lay-filter="searchType" onchange="searchTypeChange()">
                        <option value="2" selected><fmt:message key="all"/></option>
                        <option value="1"><fmt:message key="finance_type.in"/></option>
                        <option value="0"><fmt:message key="finance_type.out"/></option>
                    </select>
                </div>
                <div class="searchTypeDiv" id="typeDiv">

                </div>
                <input class="layui-input date" id="from" style="display: inline-block;"/>
                -
                <input class="layui-input date" id="to" style="display: inline-block;"/>
                <div class="searchBtn" onclick="search()" style="display: inline-block;"><i
                        class="layui-btn layui-icon">&#xe615;</i>
                </div>
            </div>
        </form>
        <table class="layui-table" lay-skin="line" style="min-width: 850px">
            <thead>
            <tr>
                <th width="150px"><fmt:message key="finance.time"/></th>
                <th width="100px"><fmt:message key="finance.type"/></th>
                <th width="50px"><fmt:message key="finance.in/out"/></th>
                <th width="50px"><fmt:message key="finance.money"/></th>
                <th width="200px"><fmt:message key="note"/></th>
                <th width="50px"><fmt:message key="floor.manage.entry_user"/></th>
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
