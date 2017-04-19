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
<%@include file="common/iconfont.jsp" %>
<html>
<head>
</head>
<link rel="stylesheet" type="text/css" href="style/common.css"/>
<link rel="stylesheet" type="text/css" href="style/reimburse.css"/>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/reimburse_detail.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<form id="add-form" class="layui-form">
    <table class="form_table">
        <tr>
            <td><label class="layui-form-label"><fmt:message key="reimburse.apply_user"/></label></td>
            <td><input class="layui-input" value="${reimburse.applyUser.realName}" disabled/></td>
            <td><label class="layui-form-label" style="margin-left: 10px"><fmt:message key="reimburse.money"/></label>
            </td>
            <td><input type="number" name="money" id="money" class="layui-input" value="${reimburse.money}" disabled/>
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="reimburse.apply_date"/></label></td>
            <td><input class="layui-input"
                       value="<fmt:formatDate value='${reimburse.applyDatetime}' pattern="yyyy-MM-dd HH:mm:ss"/>"
                       disabled/></td>
            <td><label class="layui-form-label" style="margin-left: 10px"><fmt:message key="reimburse.status"/></label>
            </td>
            <td>
                <c:if test="${reimburse.status eq 1}">
                    <input class="layui-input" disabled value="<fmt:message key='reimburse.status.pending'/>"/>
                </c:if>
                <c:if test="${reimburse.status eq 2}">
                    <input class="layui-input" disabled value="<fmt:message key='reimburse.status.approved'/>"/>
                </c:if>
                <c:if test="${reimburse.status eq 3}">
                    <input class="layui-input" disabled value="<fmt:message key='reimburse.status.rejected'/>"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="reimburse.apply_note"/></label></td>
            <td colspan="3"><textarea id="applyNote" name="applyNote" class="layui-textarea"
                                      disabled>${reimburse.applyUserNote}</textarea></td>
        </tr>
        <c:if test="${reimburse.status ne 1}">
            <tr>
                <td><label><fmt:message key="reimburse.opt_user"/></label></td>
                <td><input class="layui-input" disabled value="${reimburse.entryUser.realName}"/></td>
                <td><label class="layui-form-label" style="margin-left: 10px"><fmt:message
                        key="reimburse.opt_date"/></label></td>
                <td><input class="layui-input"
                           value="<fmt:formatDate value='${reimburse.entryDatetime}' pattern="yyyy-MM-dd HH:mm:ss"/>"
                           disabled/></td>
            </tr>
        </c:if>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="reimburse.opt_note"/></label></td>
            <td colspan="3"><textarea id="optNote" name="optNote" class="layui-textarea"
                    <c:if test="${reimburse.status ne 1}">
                        disabled
                    </c:if>
                                      onblur="validateNote(this)">${reimburse.entryUserNote}</textarea></td>
        </tr>
    </table>
</form>
<div id="btnGroup">
    <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
           onclick="closeMe()"/>
    <c:if test="${reimburse.status eq 1}">
        <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="reimburse.opt.approved"/>"
               onclick="operate(1)"/>
        <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="reimburse.opt.rejected"/>"
               onclick="operate(0)"/>
    </c:if>
</div>
</body>
</html>
