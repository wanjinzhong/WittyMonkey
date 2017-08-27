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
<script type="text/javascript" src="js/salary_payroll.js"></script>
<link type="text/css" rel="stylesheet" href="style/salary.css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<div id="main">
    <fmt:message key="salary.date"/><span id="salaryDate"></span>
    <form class="layui-form" id="payroll-form">
        <table class="layui-table" lay-skin="line">
            <thead>
            <tr>
                <th width="50px"><fmt:message key="salary.staff_no"/></th>
                <th width="100px"><fmt:message key="salary.staff.name"/></th>
                <th width="100px"><fmt:message key="salary.basic"/></th>
                <th width="100px"><fmt:message key="salary.leave_pay"/></th>
                <th width="100px"><fmt:message key="salary.other_pay"/></th>
                <th width="100px"><fmt:message key="salary.bonus"/></th>
                <th width="100px"><fmt:message key="salary.amount"/></th>
            </tr>
            </thead>
            <tbody id="dataTabel" style="overflow: scroll">
            </tbody>
        </table>
    </form>
    <div id="btnGroup">
        <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
               onclick="closeMe()"/>
        <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="salary.pay.btn"/>"
               onclick="payroll()"/>
    </div>
</div>
</body>
</html>
