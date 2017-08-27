<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/4/13
  Time: 10:56
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
<link type="text/css" rel="stylesheet" href="style/floor_add.css"/>
<script type="text/javascript" src="js/role_edit.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<form id="edit_form" class="layui-form">
    <table class="form_table">
        <tr>
            <td><label class="layui-form-label"><fmt:message key="role.name"/></label></td>
            <td width="400px" ><input type="text" class="layui-input" name="name" value="${editRole.name}"
                                     id="name" onblur="validateRoleName('update',this)"></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="role.permission"/></label></td>
            <td width="400px" style="text-align: left">
                <c:forEach items="${menus}" var="menu">
                    <input type="checkbox" name="menu" value="${menu.id}" title="${menu.name}"
                           <c:if test="${menu.selected}">checked="checked"</c:if>/>
                </c:forEach>

            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="note"/></label></td>
            <td width="400px"><textarea class="layui-textarea" name="note" id="note"
                                        onblur="validateNote(this)" >${editRole.note}</textarea></td>
        </tr>
    </table>
</form>
<div id="btnGroup">
    <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
           onclick="closeMe()"/>
    <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.save"/>"
           onclick="update()"/>
</div>
</body>
</html>
</title>
</head>
<body>

</body>
</html>
