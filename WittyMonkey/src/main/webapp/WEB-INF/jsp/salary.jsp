<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/2/15
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //User loginUser = (User) session.getAttribute("loginUser");
    //String lang = loginUser.getSetting().getLang();
    String lang = "zh_CN";
%>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@ include file="common/iconfont.jsp" %>
<html>
<head>
    <title><fmt:message key="finance.salary.title"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
<%--<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>--%>
<fmt:setBundle basename="i18n/messages"/>
<body>
<nav class="breadcrumb">
    <i class="refreshBtn layui-icon layui-btn layui-btn-small" onclick="reload();">&#x1002;</i>
</nav>
<div id="main">
    <form class="layui-form">
        <div>
            <div class="searchTypeDiv">
                <select name="type" id="type" lay-filter="type" >
                    <option value="0" selected><fmt:message key="reimburse.status.all"/></option>
                    <option value="1"><fmt:message key="reimburse.status.pending"/></option>
                    <option value="2"><fmt:message key="reimburse.status.approved"/></option>
                    <option value="3"><fmt:message key="reimburse.status.rejected"/></option>
                </select>
            </div>
            <input class="layui-input date" id="from" placeholder="" style="display: inline-block;"/>
            -
            <input class="layui-input date" id="to" style="display: inline-block;"/>
            <div class="searchBtn" onclick="search()" style="display: inline-block;"><i
                    class="layui-btn layui-icon">&#xe615;</i>
            </div>
        </div>
    </form>
    <table class="layui-table" lay-skin="line">
        <thead>
        <tr>
            <th width="100px"><fmt:message key="reimburse.apply_user"/></th>
            <th width="150px"><fmt:message key="reimburse.apply_date"/></th>
            <th width="50px"><fmt:message key="reimburse.money"/></th>
            <th width="50px"><fmt:message key="reimburse.status"/></th>
            <th width="100px"><fmt:message key="reimburse.opt_user"/></th>
            <th width="150px"><fmt:message key="reimburse.opt_date"/></th>
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
