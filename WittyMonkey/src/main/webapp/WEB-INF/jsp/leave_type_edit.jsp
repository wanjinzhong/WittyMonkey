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
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/leave_type_add&update.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
    <form id="leave_type_form">
        <!-- java端处理方式 -->
        <input type="hidden" id="method" name="method" value="update"/>
        <table class="form_table">
            <tr>
                <td><label class="layui-form-label"><fmt:message key="leave_type.name"/></label></td>
                <td><input type="text" class="layui-input" name="name"
                           id="name" value="${editLeaveType.name}" onblur="validateLeaveTypeName(this)"
                           <c:if test="${!editLeaveType.deletable}">
                               disabled
                           </c:if>
                    /></td>
                <td><label class="layui-form-label"><fmt:message key="leave_type.deduct"/></label></td>
                <td>
                    <input type="number" class="layui-input" name="deduct" value="${editLeaveType.deduct * 100}"
                           id="deduct" onblur="validateDeduct(this)"/>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="note"/></label></td>
                <td colspan="3"><textarea class="layui-textarea" name="note" id="note" onblur="validateNote(this)">${editLeaveType.note}</textarea></td>
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
