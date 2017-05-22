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
    <script type="text/javascript" src="js/checkin.js"></script>
    <script type="text/javascript" src="js/common.js"></script>
    <link rel="stylesheet" href="style/checkin.css"/>
    <script type="text/javascript" src="lib/My97DatePicker/WdatePicker.js"></script>

    <fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
</head>
<body>
<input type="hidden" value="${loginUser.setting.lang }" id="lang"/>
<form id="checkin-form" method="post">
    <input type="hidden" value="${checkinRoom.id}" name="roomId"/>
    <input type="hidden" value="${reserve.id}" name="reserveId" id="reserveId"/>
    <table class="form_table">
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="room.add.number"/></label></td>
            <td style="width: 300px">
                <div class="input"><input disabled type="text" class="layui-input" id="roomNum" name="roomNum"
                                          value="${checkinRoom.number}"/></div>
            </td>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="room.add.name"/></label></td>
            <td>
                <div class="input"><input disabled type="text" class="layui-input" id="roomName" name="roomName"
                                          value="${checkinRoom.name}"/></div>
            </td>
        </tr>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="checkin.name"/><i
                    class="layui-icon btn-add-customer">&#xe61f;</i> </label></td>
            <td colspan="3">
                <table class="customers layui-table">
                    <thead>
                    <th width="200px"><fmt:message key="checkin.idcard"/></th>
                    <th width="150px"><fmt:message key="checkin.name"/></th>
                    <th width="150px"><fmt:message key="operation"/></th>
                    </thead>
                </table>
                <div class="table-div">
                    <table class="customers layui-table table-content">
                        <tbody id="tbody" class="tbody">
                        <c:if test="${reserve ne null}">
                            <tr>
                                <input type="hidden" class="id-input"/>
                                <td width="200px"><input type="text" value="${reserve.customer.idCard}"
                                                         onblur="if(validateIdCard(this)) findCustomer(this)"
                                                         class="table-input idcard-input" name="idcard"/></td>
                                <td width="150px"><input type="text" value="${reserve.customer.name}"
                                                         class="table-input name-input" name="name"/></td>
                                <td class="operation" width="150px"><i
                                        class="deleteBtn layui-icon layui-btn layui-btn-primary layui-btn-small"
                                        onclick="removePerson(this)">&#xe640;<fmt:message key="delete"/></i></td>
                            </tr>
                        </c:if>
                        <c:if test="${reserve eq null}">
                            <tr>
                                <td width="200px"><input type="text" class="table-input idcard-input"
                                                         onblur="if(validateIdCard(this)) findCustomer(this)"
                                                         name="idcard"/></td>
                                <td width="150px"><input type="text" class="table-input name-input" name="name"/>
                                </td>
                                <td class="operation"></td>
                            </tr>
                        </c:if>
                        <tr>
                            <td width="200px"><input type="text" class="table-input idcard-input"
                                                     onblur="if(validateIdCard(this)) findCustomer(this)"
                                                     name="idcard"/></td>
                            <td width="150px"><input type="text" class="table-input name-input" name="name"/></td>
                            <td class="operation"></td>
                        </tr>
                        <tr>
                            <td width="200px"><input type="text" class="table-input idcard-input"
                                                     onblur="if(validateIdCard(this)) findCustomer(this)"
                                                     name="idcard"/></td>
                            <td width="150px"><input type="text" class="table-input name-input" name="name"/></td>
                            <td class="operation"></td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </td>
        </tr>
        <c:if test="${reserve ne null}">
            <tr>
                <td class="td_title">
                    <lable class="layui-form-label"><fmt:message key='room.reserve.estcheckin'/></lable>
                </td>
                <td>
                    <input type="hidden" value="${fromDate}" id="from" name="from"/>
                    <input type="hidden" value="${toDate}" id="to" name="to"/>
                    <input disabled type="text" class="layui-input"
                           value="${fromDate}<fmt:message key="checkin.date.checkin"/> ${toDate}<fmt:message key="checkin.date.checkout"/>"/>
                </td>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="checkin.room.price"/></label>
                </td>
                <td>
                    <input type="number" id="roomPriceReserve" class="layui-input" value="${reserve.room.price}"
                           disabled/>
                </td>
            </tr>
            <tr>
                <td class="td_title">
                    <label class="layui-form-label"> <fmt:message key="checkin.reserve.note"/></label>
                </td>
                <td colspan="3"><textarea class="layui-textarea" disabled>${reserve.note}</textarea></td>
            </tr>

            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message
                        key='room.reserve.deposit'/></label>
                </td>
                <td><input id="deposit" name="deposit"
                           type="number" class="layui-input"
                           onblur="validateMoney(this)" value="${reserve.deposit}" disabled/></td>
                <td class="td_title">
                    <label class="layui-form-label"><fmt:message key="checkin.room.foregift"/></label>
                </td>
                <td><input type="number" id="foregiftReserve" class="layui-input foregift" name="foregift" value="0"
                           onchange="calcPrice()"></td>
            </tr>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message
                        key="checkin.room.charge"/></label></td>
                <td><input type="number" id="chargeReserve" class="layui-input" value="${reserve.room.price}" disabled/>
                </td>
                <td class="td_title"><label class="layui-form-label"><fmt:message
                        key="checkin.room.account_pay"/></label></td>
                <td><input class="layui-input" type="number" id="payReserve" disabled/></td>
            </tr>
        </c:if>
        <c:if test="${reserve eq null}">
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message
                        key='checkin.date.to'/></label></td>
                <td>
                    <input class="layui-input" type="text" id="to_date" name="toDate"/>
                </td>
                <td class="td_title"><label class="layui-form-label"><fmt:message key="checkin.room.price"/></label>
                </td>
                <td>
                    <input type="number" id="roomPrice" class="layui-input" value="${checkinRoom.price}" disabled/>
                </td>
            </tr>
            <tr>
                <td class="td_title">
                    <label class="layui-form-label"><fmt:message key="checkin.room.foregift"/></label>
                </td>
                <td><input type="number" id="foregift" class="layui-input foregift" name="foregift" value="0"
                           onblur="calcPrice()"></td>
                <td class="td_title"><label class="layui-form-label"><fmt:message
                        key="checkin.room.charge"/></label></td>
                <td><input type="number" id="charge" class="layui-input" disabled/></td>
            </tr>
            <tr>
                <td class="td_title"><label class="layui-form-label"><fmt:message
                        key="checkin.room.account_pay"/></label></td>
                <td colspan="3"><input class="layui-input" type="number" id="pay" disabled/></td>
            </tr>
        </c:if>
        <tr>
            <td class="td_title"><label class="layui-form-label"><fmt:message key="note"/></label></td>
            <td colspan="3"><textarea class="layui-textarea" id="note" name="note"
                                      onblur="validateNote(this)"></textarea></td>
        </tr>
    </table>
</form>
<div id="btnGroup">
    <input id="closeBtn" type="button"
           value="<fmt:message key="regist.btn.close"/>"
           class="layui-btn layui-btn-radius layui-btn-danger" onclick="closeMe()"/>
    <input id="checkinBtn" type="button"
           value="<fmt:message key="btn.checkin"/>"
           class="layui-btn layui-btn-radius" onclick="checkin()"/>
</div>
</div>
</body>
</html>