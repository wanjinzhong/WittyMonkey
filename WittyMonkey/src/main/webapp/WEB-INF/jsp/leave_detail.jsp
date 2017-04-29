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
<script type="text/javascript" src="js/leave_detail.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<input hidden value="${loginUser.setting.lang}" id="lang"/>
<form id="leave_form" class="layui-form">
    <table class="form_table" width="550px">
        <tr>
            <td><label class="layui-form-label"><fmt:message key="leave.apply_user"/></label></td>
            <td><input value="${leaveVO.staffNo} - ${leaveVO.applyUser}" class="layui-input" disabled/></td>
            <td><label class="layui-form-label" style="margin-left: 10px"><fmt:message key="leave.type"/></label></td>
            <td><input class="layui-input" value="${leaveVO.leaveType}" disabled/></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="leave.date"/></label></td>
            <td><input type="text" id="leaveDate" class="layui-input"
                       value="<fmt:formatDate value="${leaveVO.from}" pattern="yyyy-MM-dd"/> -<fmt:formatDate
                    value="${leaveVO.to}" pattern="yyyy-MM-dd"/>" disabled/>
            </td>
            <td><label class="layui-form-label"><fmt:message key="leave.days"/></label></td>
            <td><input type="number" id="days" name="days" class="layui-input" disabled value="${leaveVO.days}"/></td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="leave.status"/></label></td>
            <td>
                <input class="layui-input" disabled
                        <c:if test="${leaveVO.status eq 1}">
                            value='<fmt:message key="leave.status.pending"/>'
                        </c:if>
                        <c:if test="${leaveVO.status eq 2}">
                            value='<fmt:message key="leave.status.approved"/>'
                        </c:if>
                        <c:if test="${leaveVO.status eq 3}">
                            value='<fmt:message key="leave.status.rejected"/>'
                        </c:if>
                />
            </td>
            <td><label class="layui-form-label"><fmt:message key="leave.deduct"/></label></td>
            <td><input type="number" name="deduct" id="deduct" class="layui-input" disabled value="${leaveVO.deduct}"/>
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="leave.apply_note"/></label></td>
            <td colspan="3"><textarea id="applyNote" name="applyNote" class="layui-textarea" disabled
                                      onblur="validateNote(this)">${leaveVO.applyUserNote}</textarea></td>
        </tr>
        <c:if test="${leaveVO.status ne 1}">
            <tr>
                <td><label class="layui-form-label"><fmt:message key="leave.opt_user"/></label></td>
                <td><input class="layui-input" value="${leaveVO.entryUser}" disabled/>
                </td>
                <td><label class="layui-form-label"><fmt:message key="leave.opt_date"/></label></td>
                <td><input class="layui-input" value="<fmt:formatDate value="${leaveVO.entryDatetime}" pattern="yyyy-MM-dd HH:mm:ss"/>" disabled/></td>
            </tr>
        </c:if>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="leave.opt_note"/></label></td>
            <td colspan="3"><textarea id="optNote" name="optNote" class="layui-textarea"
                                      <c:if test="${leaveVO.status ne 1}">
                                          disabled
                                      </c:if>
                                      onblur="validateNote(this)">${leaveVO.entryUserNote}</textarea></td>
        </tr>
    </table>
</form>
<div id="btnGroup">
    <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
           onclick="closeMe()"/>
    <c:if test="${leaveVO.status eq 1}">
        <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="reimburse.opt.approved"/>"
               onclick="operate(2)"/>
        <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="reimburse.opt.rejected"/>"
               onclick="operate(3)"/>
    </c:if>
</div>
</body>
</html>
