<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/2/20
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@include file="common/iconfont.jsp" %>
<html>
<head>
    <title><fmt:message key="room.add.title"/></title>
    <link rel="stylesheet" type="text/css" href="style/common.css"/>
    <script type="text/javascript" src="js/common.js"></script>
    <link rel="stylesheet" type="text/css" href="style/room_add.css"/>
    <script type="text/javascript" src="js/room_add.js"></script>
    <!-- 根据设置动态加载js语言 -->
    <script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
    <fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
</head>
<body>
<div class="add-form">
    <form id="room-form" class="layui-form">
        <table>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="room.add.number"/></label></td>
                <td style="width: 300px">
                    <div class="input"><input type="text" class="layui-input" id="roomNum" name="roomNum" onblur="validateRoomNo(this)"/></div>
                </td>
                <td><label class="layui-form-label"><fmt:message key="room.add.name"/></label></td>
                <td>
                    <div class="input"><input type="text" class="layui-input" id="roomName" name="roomName" onblur="validateRoomName(this)"/></div>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="layui-form-label"><fmt:message key="room.add.single_bed.num"/></label></td>
                <td>
                    <div class="input inner"><input type="number" class="layui-input" id="singleBedNum" name="singleBedNum" onblur="validateBed(this)"/></div>
                </td>
                <td><label class="layui-form-label"><fmt:message key="room.add.double_bed.num"/></label></td>
                <td>
                    <div class="input inner"><input type="number" class="layui-input" id="doubleBedNum" name="doubleBedNum" onblur="validateBed(this)"/></div>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="room.add.availabel.num"/></label></td>
                <td>
                    <div class="input inner"><input type="number" class="layui-input" id="availableNum" name="availableNum" id="availableNum" onblur="validateAvailable(this)"/></div>
                </td>
                <td><label class="layui-form-label"><fmt:message key="room.add.floor_no"/></label></td>
                <td>
                    <div class="input inner"><select name="floorNo" id="floorNo">
                        <c:forEach items="${floors}" var="f">
                            <option value="${f.id}">${f.floorNo}</option>
                        </c:forEach>
                    </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="layui-form-label"><fmt:message key="room.add.area"/></label></td>
                <td>
                    <div class="input inner"><input type="number" class="layui-input" id="area" name="area" onblur="validateArea(this)"/></div>
                </td>
                <td><label class="layui-form-label"><fmt:message key="room.add.price"/></label></td>
                <td>
                    <div class="input inner"><input type="number" class="layui-input" id="price" name="price" onblur="validateArea(this)"/></div>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="room.add.extra_info"/></label></td>
                <td colspan="3">
                    <div class="checkbox">
                        <input type="checkbox" name="extra" value="window" title="<fmt:message key="room.add.window"/>">
                        <input type="checkbox" name="extra" value="aircondition" title="<fmt:message key="room.add.aircondition"/>">
                        <input type="checkbox" name="extra" value="wifi" title="<fmt:message key="room.add.wifi"/>">
                        <input type="checkbox" name="extra" value="reticel" title="<fmt:message key="room.add.reticle"/>">
                        <input type="checkbox" name="extra" value="tv" title="<fmt:message key="room.add.tv"/>">
                        <input type="checkbox" name="extra" value="phone" title="<fmt:message key="room.add.phone"/>">
                        <input type="checkbox" name="extra" value="kettle" title="<fmt:message key="room.add.kettle"/>">
                        <input type="checkbox" name="extra" value="extraBed" title="<fmt:message key="room.add.extra_bed"/>">
                    </div>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="room.add.other_facility"/></label></td>
                <td colspan="3">
                    <div class=""><textarea class="layui-textarea" id="otherFacility" name="otherFacility" onblur="validateOtherFacility(this)"></textarea></div>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="note"/></label></td>
                <td colspan="3"><textarea class="layui-textarea" id="note" name="note" onblur="validateNote(this)"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<div id="btnGroup">
    <input type="button" class="layui-btn layui-btn-danger layui-btn-radius" value="<fmt:message key="btn.close"/>" onclick="closeMe()"/>
    <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.save"/>" onclick="save()"/>
</div>
</body>
</html>
