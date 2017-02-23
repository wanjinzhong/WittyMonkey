<%@ page import="com.wittymonkey.entity.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@include file="common/iconfont.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="name"/></title>
    <%--alibaba iconfont字体图标--%>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
    <script type="text/javascript" src="js/reserve.js"></script>
    <link rel="stylesheet" href="style/reserve.css"/>
    <script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>

    <fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
</head>
<body>
<div id="checkin-border">
    <input type="hidden" value="${loginUser.setting.lang }" id="lang"/>
    <form id="checkin-form" method="post">
        <input type="hidden" value="${checkinRoom.id}" name="roomId"/>
        <table>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="room.add.number"/></label></td>
                <td style="width: 300px">
                    <div class="input"><input disabled type="text" class="layui-input" id="roomNum" name="roomNum"
                                              value="${reserveRoom.number}"/></div>
                </td>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="room.add.name"/></label></td>
                <td>
                    <div class="input"><input disabled type="text" class="layui-input" id="roomName" name="roomName"
                                              value="${reserveRoom.name}"/></div>
                </td>
            </tr>
            <tr>
                <td colspan="4">
                    <table>
                        <thead>
                        <th><fmt:message key="checkin.name"/></th>
                        <th><fmt:message key="checkin.idcard"/></th>
                        <th><fmt:message key="opertion"/></th>
                        </thead>
                        <tbody></tbody>
                    </table>
                </td>
            </tr>
            <c:if test="${reserve ne null}">
            <tr>
                <td>
                    <fmt:message key="checkin.reserve.note"/>
                </td>
                <td colspan="3"><textarea class="layui-textarea" disabled>${reserve.note}</textarea></td>
            </tr>
            </c:if>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message key='room.reserve.deposit'/></label>
                </td>
                <td><input id="deposit" name="deposit"
                           type="number" class="layui-input"
                           onblur="validateMonney(this)"/></td>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="note"/></label></td>
                <td><textarea class="layui-textarea" id="note" name="note" onblur="validateNote(this)"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<div id="btnGroup">
    <input id="closeBtn" type="button"
           value="<fmt:message key="regist.btn.close"/>"
           class="layui-btn layui-btn-radius layui-btn-danger" onclick="closeMe()"/>
    <input id="reserveBtn" type="button"
           value="<fmt:message key="btn.reserve"/>"
           class="layui-btn layui-btn-radius" onclick="reserve()"/>
</div>
</div>
</body>
</html>