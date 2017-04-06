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
    <title><fmt:message key="index.menu.floor.add"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <link href="style/floor_add.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="js/floor_edit.js"></script>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
<%--<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>--%>
<fmt:setBundle basename="i18n/messages"/>
<body>
    <form id="add_form">
        <!-- java端处理方式 -->
        <input type="hidden" name="method" value="update"/>
        <table>
            <tr>
                <td class="table-header"><label class="layui-form-label"><fmt:message key="floor.manage.floor_no"/></label></td>
                <td><input type="text" class="layui-input" name="floorNo"
                           value="${editFloor.floorNo}"
                           id="floorNo" onblur="validateFloorNo('update',this)"></td>
            </tr>
            <tr>
                <td class="table-header"><label class="layui-form-label"><fmt:message key="note"/></label></td>
                <td><textarea class="layui-textarea" name="note" id="note">${editFloor.note}</textarea></td>
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
