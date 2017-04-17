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
    <title><fmt:message key="index.menu.floor.add"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/finance_add.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
    <form id="add_form" class="layui-form">
        <table class="form-table">
            <tr>
                <td><label class="layui-form-label"><fmt:message key="finance_type.in/out"/></label></td>
                <td>
                    <select>
                        <option value="1" selected><fmt:message key="finance_type.in"/></option>
                        <option value="0"><fmt:message key="finance_type.out"/></option>
                    </select>
                </td>
                <td><label class="layui-form-label"  style="margin-left: 10px"><fmt:message key="finance.type"/></label></td>
                <td>
                    <select id="type" name="type">
                        <c:forEach items="${types}" var="type">
                            <option value="${type.id}">${type.name}</option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="finance.money"/></label></td>
                <td colspan="3"><input type="number" class="layui-input" name="money" id="money"></input></td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="note"/></label></td>
                <td colspan="3"><textarea class="layui-textarea" name="note" id="note"></textarea></td>
            </tr>
        </table>
    </form>
    <div id="btnGroup">
        <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
               onclick="closeMe()"/>
        <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.save"/>"
               onclick="save()"/>
    </div>
</body>
</html>
