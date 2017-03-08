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
<div id="reserve-border">
    <input type="hidden" value="${loginUser.setting.lang }" id="lang"/>
    <form id="reserve-form" method="post">
        <input type="hidden" value="${reserveRoom.id}" name="roomId"/>
        <input type="hidden" name="custId" id="custId"/>
        <table>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="room.add.number"/></label></td>
                <td style="width: 300px">
                    <div class="input"><input disabled type="text" class="layui-input" id="roomNum" name="roomNum"
                                              value="${reserveRoom.number}"/></div>
                </td>
            </tr>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="room.add.name"/></label></td>
                <td>
                    <div class="input"><input disabled type="text" class="layui-input" id="roomName" name="roomName"
                                              value="${reserveRoom.name}"/></div>
                </td>
            </tr>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message
                        key="room.reserve.idcard"/></label></td>
                <td><input type="text" class="layui-input"
                           name="idcard" id="idcard" onblur="if(validateIdCard(this))findCustomer(this);"/></td>
            </tr>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message
                        key="room.reserve.name"/></label></td>
                <td><input type="text" class="layui-input" id="name"
                           name="name" onblur="validateRealName(this)"/></td>
            </tr>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message key='room.reserve.tel'/></label></td>
                <td><input id="tel" name="tel"
                           type="text" class="layui-input"
                           onblur="validateTel(this)"/></td>
            </tr>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message
                        key='room.reserve.estcheckin'/></label></td>
                <td>
                    <input type="text" class="layui-input" id="dateDisp"
                           name="dateDisp"/></td>
                    <input type="hidden" id="from_date" name="from"/>
                    <input type="hidden" id="to_date" name="to"/>
                </td>
                <td><svg class="icon eye" aria-hidden="true" onclick="showReserve(${reserveRoom.id})">
                    <use xlink:href="#icon-chakan-copy"></use>
                    </svg></td>
            </tr>
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