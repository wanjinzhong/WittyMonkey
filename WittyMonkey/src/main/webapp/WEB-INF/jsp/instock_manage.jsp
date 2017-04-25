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
<script type="text/javascript" src="js/instock_manage.js"></script>
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
        <i class="newBtn layui-icon layui-btn layui-btn-radius layui-btn-normal" onclick="showInStock()">&#xe61f;
            <fmt:message key="instock.add"/></i>
        <form class="layui-form">
            <div style="min-width: 800px">
                <div class="searchTypeDiv">
                    <select name="searchType" id="searchType" lay-filter="searchType" >
                        <option value="0" selected><fmt:message key="instock.search.all"/></option>
                        <option value="1"><fmt:message key="instock.search.type"/></option>
                        <option value="2"><fmt:message key="instock.search.barcode"/></option>
                        <option value="3"><fmt:message key="instock.search.name"/></option>
                    </select>
                </div>
                <div id="searchContent" style="display:inline-block;max-width:200px"></div>
                <input class="layui-input date" id="from" placeholder="<fmt:message key="start_date_hint"/>" style="display: inline-block;"/>
                -
                <input class="layui-input date" id="to" placeholder="<fmt:message key="end_date_hint"/>" style="display: inline-block;"/>
                <div class="searchBtn" onclick="search()" style="display: inline-block;"><i
                        class="layui-btn layui-icon">&#xe615;</i>
                </div>
            </div>
        </form>
        <table class="layui-table" lay-skin="line" style="min-width: 800px">
            <thead>
            <tr>
                <th width="100px"><fmt:message key="instock.materiel"/></th>
                <th width="150px"><fmt:message key="instock.price"/></th>
                <th width="50px"><fmt:message key="instock.qty"/></th>
                <th width="50px"><fmt:message key="instock.pay"/></th>
                <th><fmt:message key="note"/></th>
                <th width="100px"><fmt:message key="entry_user"/></th>
                <th width="150px"><fmt:message key="entry_date"/></th>
            </tr>
            </thead>
            <tbody id="dataTable">
            </tbody>
        </table>
        <div id="page"></div>
    </div>
</div>
</body>
</html>
