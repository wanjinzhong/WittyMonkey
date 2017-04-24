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
<%@ include file="common/laypage.jsp" %>
<html>
<head>
    <title><fmt:message key="materiel.manage.title"/></title>
</head>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/materiel_manage.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<link href="style/common.css" rel="stylesheet" type="text/css"/>
<link href="style/materiel.css" rel="stylesheet" type="text/css"/>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<div>
    <nav class="breadcrumb">
        <i class="refreshBtn layui-icon layui-btn layui-btn-small" onclick="reload();">&#x1002;</i>
    </nav>
    <div id="main">
    <i class="newBtn layui-icon layui-btn layui-btn-radius layui-btn-normal" onclick="showAddMateriel()">&#xe61f;
        <fmt:message key="materiel.add_btn"/></i>
    <form class="layui-form">
        <div class="searchTypeDiv">
            <select name="searchType" id="searchType" lay-filter="searchType">
                <option value="1" selected><fmt:message key="materiel.search.all"/></option>
                <option value="2"><fmt:message key="materiel.search.type"/></option>
                <option value="3"><fmt:message key="materiel.search.warn"/></option>
                <option value="4"><fmt:message key="materiel.barcode"/></option>
                <option value="5"><fmt:message key="materiel.name"/></option>
            </select>
        </div>
        <div class="searchContent"></div>
    </form>
        <table class="layui-table" lay-skin="line" style="min-width: 850px">
            <thead>
            <tr>
                <th width="150px"><fmt:message key="materiel.barcode"/></th>
                <th width="100px"><fmt:message key="materiel.name"/></th>
                <th width="100px"><fmt:message key="materiel.type"/></th>
                <th width="50px"><fmt:message key="materiel.stock"/></th>
                <th width="50px"><fmt:message key="materiel.unit"/></th>
                <th width="150px"><fmt:message key="materiel_type.entry_user"/></th>
                <th width="200px"><fmt:message key="materiel_type.entry_datetime"/></th>
                <th width="200px"><fmt:message key="operation"/></th>
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
