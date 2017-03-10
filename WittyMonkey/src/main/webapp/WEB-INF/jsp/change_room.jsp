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
    <title><fmt:message key="checkin.change_room.title"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
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
    <table>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="change.room"/></label></td>
            <td><input class="layui-input" type="text" disabled id="toRoom" value="${checkin.room.number}-${checkin.room.name}"/>
            </td>
            <td rowspan="2" class="to">
                <svg class="icon">
                    <use xlink:href="#icon-arrow-right-copy"/>
                </svg>
            </td>
            <td><input class="layui-input" type="text" disabled id="toNumber"/></td>
            <td><i class="showRoom layui-icon" onclick="showRoom()">&#xe62d;</i></td>
        </tr>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="change.price"/></label></td>
            <td><input class="layui-input" type="text" disabled value="${checkin.price}"/></td>
            <td><input class="layui-input" type="text" disabled id="toPrice"/></td>
        </tr>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="change.date"/></label></td>
            <td colspan="3">
                <input type="hidden" id="checkin_id" value="${checkin.id}"/>
                <input class="layui-input" type="text" disabled
                                   value="${now} - ${to}  <fmt:message key="change.total"/> ${total} <fmt:message key="change.day"/>">
            </td>
        </tr>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="change.diff"/></label></td>
            <td colspan="3"><input class="layui-input" disabled
                                   value="<c:if test='${diff <= 0}'><fmt:message key='change.price.refund'/>${-diff}</c:if><c:if test='${diff > 0}'><fmt:message key='change.price.pay'/>${diff}</c:if>">
            </td>
        </tr>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="change.reason"/></label></td>
            <td colspan="3"><textarea class="layui-textarea"></textarea></td>
        </tr>
    </table>
</div>
</body>
</html>
