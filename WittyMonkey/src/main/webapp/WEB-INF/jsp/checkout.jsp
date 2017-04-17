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
    <title><fmt:message key="checkin.checkout.title"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <link href="style/checkout.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="js/checkout.js"></script>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
    <form id="checkout-form" method="post">
        <input type="hidden" disabled value="${checkin.id}" id="checkinId">
        <table class="form-table">
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="room.add.number"/></label></td>
                <td style="width: 300px">
                    <div class="input"><input disabled type="text" class="layui-input" id="roomNum" name="roomNum"
                                              value="${checkin.room.number}"/></div>
                </td>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="room.add.name"/></label></td>
                <td>
                    <div class="input"><input disabled type="text" class="layui-input" id="roomName" name="roomName"
                                              value="${checkin.room.name}"/></div>
                </td>
            </tr>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="checkin.name"/></label></td>
                <td colspan="3">
                    <table class="customers layui-table">
                        <thead>
                        <th width="200px"><fmt:message key="checkin.idcard"/></th>
                        <th width="150px"><fmt:message key="checkin.name"/></th>
                        </thead>
                    </table>
                    <div class="table-div">
                        <table class="customers layui-table table-content">
                            <tbody id="tbody" class="tbody">
                            <c:forEach items="${checkin.customers}" var="customer">
                                <tr>
                                    <td width="200px">${customer.idCard}</td>
                                    <td width="150px">${customer.name}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="checkout.checkin.date"/></label></td>
                <td><div class="input"><input disabled type="text" class="layui-input" value="${checkinDate}"></div></td>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="checkout.checkout.est_date"/></label></td>
                <td><div class="input"><input disabled type="text" class="layui-input" value="${estCheckoutDate}"></div></td>
            </tr>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="checkin.room.price"/></label></td>
                <td>
                    <input type="number" id="roomPriceReserve" class="layui-input" value="${checkin.price}" disabled/>
                </td>
                <td class="td_title">
                    <label class="layui-form-label"><fmt:message key="checkin.room.foregift"/></label>
                </td>
                <td><input type="number" id="foregiftReserve" class="layui-input foregift" name="foregift" value="${checkin.foregift}" disabled></td>
            </tr>
            <tr>
                <td class="td_title">
                    <label class="layui-form-label"> <fmt:message key="checkout.checkin.note"/></label>
                </td>
                <td colspan="3"><textarea class="layui-textarea" disabled>${checkin.note}</textarea></td>
            </tr>
        </table>
    </form>
<div id="btnGroup">
    <input id="closeBtn" type="button"
           value="<fmt:message key="regist.btn.close"/>"
           class="layui-btn layui-btn-radius layui-btn-danger" onclick="closeMe()"/>
    <input id="checkinBtn" type="button"
           value="<fmt:message key="btn.checkout"/>"
           class="layui-btn layui-btn-radius" onclick="checkout()"/>
</div>
</body>
</html>
