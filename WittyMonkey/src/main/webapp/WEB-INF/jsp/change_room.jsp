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
    <link href="style/change_room.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/change_room.js"></script>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<div class="container">
    <input type="hidden" id="id"/>
    <input type="hidden" id="checkin_id" value="${checkin.id}"/>
    <input type="hidden" id="toId"/>
    <table class="form_table">
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="room.add.number"/></label></td>
            <td><input class="layui-input" type="text" disabled value="${checkin.room.number}" id="fromNumber"/>
            </td>
            <td rowspan="3" class="to">
                <svg class="icon">
                    <use xlink:href="#icon-arrow-right-copy"/>
                </svg>
            </td>
            <td><input class="layui-input" type="text" disabled id="toNumber" }/></td>
            <td><i class="showRoom layui-icon" onclick="showRoom()">&#xe62d;</i></td>
        </tr>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="room.add.name"/></label></td>
            <td><input class="layui-input" type="text" disabled value="${checkin.room.price}" id="fromName"/></td>
            <td><input class="layui-input" type="text" disabled id="toName"/></td>
        </tr>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="change.price"/></label></td>
            <td><input class="layui-input" type="text" disabled value="${checkin.price}" id="fromPrice"/></td>
            <td><input class="layui-input" type="text" disabled id="toPrice" onchange="calcDiff()"/></td>
        </tr>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="change.date"/></label></td>
            <td colspan="3">
                <input id="total" type="hidden" value="${total}"/>
                <input class="layui-input" type="text" disabled
                       value="${now} - ${to}  <fmt:message key="change.total"/> ${total} <fmt:message key="change.day"/>">
            </td>
        </tr>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="change.diff"/></label></td>
            <td colspan="3"><input class="layui-input" disabled id="diff"/>
            </td>
        </tr>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="change.reason"/></label></td>
            <td colspan="3"><textarea class="layui-textarea"
                                      onblur="validateLength(this,'<fmt:message key="change.reason"/>',1024)"
                                      id="reason"></textarea></td>
        </tr>
    </table>
<div id="btnGroup">
    <input type="button" class="layui-btn layui-btn-danger layui-btn-radius" value="<fmt:message key="btn.close"/>"
           onclick="closeMe()"/>
    <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.change"/>" onclick="change()"/>
</div>
</div>
</body>
</html>
