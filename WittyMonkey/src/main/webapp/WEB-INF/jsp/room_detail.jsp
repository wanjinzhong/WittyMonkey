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
    <link rel="stylesheet" type="text/css" href="style/room_detail.css"/>
    <script type="text/javascript" src="js/room_validator.js"></script>
    <script type="text/javascript" src="js/room_detail.js"></script>
    <!-- 根据设置动态加载js语言 -->
    <script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
    <fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
</head>
<body>
<i class="layui-icon layui-btn editBtn layui-btn-small" onclick="editRoom()">&#xe642;</i>
<i class="layui-icon layui-btn deleteBtn layui-btn-small layui-btn-danger" onclick="deleteRoom()">&#xe640;</i>
<form id="room-form" class="layui-form">
    <!-- java端处理方式 -->
    <input type="hidden" name="method" value="update"/>
    <input type="hidden" name="id" id="id" value="${room.id}"/>
    <table>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="room.add.number"/></label></td>
            <td style="width: 300px">
                <div class="input"><input disabled type="text" class="layui-input" id="roomNum" name="roomNum"
                                          onblur="validateRoomNo('update',this)" value="${room.number}"/></div>
            </td>
            <td><label class="layui-form-label"><fmt:message key="room.add.name"/></label></td>
            <td>
                <div class="input"><input disabled type="text" class="layui-input" id="roomName" name="roomName"
                                          onblur="validateRoomName(this)" value="${room.name}"/></div>
            </td>
        </tr>
        <tr>
            <td>
                <label class="layui-form-label"><fmt:message key="room.add.single_bed.num"/></label></td>
            <td>
                <div class="input inner"><input disabled type="number" class="layui-input" id="singleBedNum"
                                                name="singleBedNum" onblur="validateBed(this)"
                                                value="${room.singleBedNum}"/></div>
            </td>
            <td><label class="layui-form-label"><fmt:message key="room.add.double_bed.num"/></label></td>
            <td>
                <div class="input inner"><input disabled type="number" class="layui-input" id="doubleBedNum"
                                                name="doubleBedNum" onblur="validateBed(this)"
                                                value="${room.doubleBedNum}"/></div>
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="room.add.availabel.num"/></label></td>
            <td>
                <div class="input inner"><input disabled type="number" class="layui-input" id="availableNum"
                                                name="availableNum" id="availableNum"
                                                onblur="validateAvailable(this)" value="${room.availableNum}"/>
                </div>
            </td>
            <td><label class="layui-form-label"><fmt:message key="room.add.floor_no"/></label></td>
            <td>
                <div class="input inner select">
                    <%--<select name="floorId" id="floorId">--%>
                    <%--<c:forEach items="${floors}" var="f">--%>
                    <%--<option value="${f.id}">${f.floorNo}</option>--%>
                    <%--</c:forEach>--%>
                    <%--</select>--%>
                    <input disabled type="number" class="layui-input" id="floor" value="${room.floor.floorNo}">
                </div>

            </td>
        </tr>
        <tr>
            <td>
                <label class="layui-form-label"><fmt:message key="room.add.area"/></label></td>
            <td>
                <div class="input inner"><input disabled type="number" class="layui-input" id="area" name="area"
                                                onblur="validateArea(this)" value="${room.area}"/></div>
            </td>
            <td><label class="layui-form-label"><fmt:message key="room.add.price"/></label></td>
            <td>
                <div class="input inner"><input disabled type="number" class="layui-input" id="price" name="price"
                                                onblur="validateArea(this)" value="${room.price}"/></div>
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="room.add.extra_info"/></label></td>
            <td colspan="3">
                <div class="checkbox">
                    <input type="checkbox" name="extra" value="window" title="<fmt:message key="room.add.window"/>"
                            <c:if test="${room.roomExt.hasWindow}">
                                checked="checked"
                            </c:if>
                    />
                    <input type="checkbox" name="extra" value="aircondition"
                           title="<fmt:message key="room.add.aircondition"/>"
                            <c:if test="${room.roomExt.hasAirCondition}">
                                checked="checked"
                            </c:if>
                    />
                    <input type="checkbox" name="extra" value="wifi" title="<fmt:message key="room.add.wifi"/>"
                            <c:if test="${room.roomExt.hasWifi}">
                                checked="checked"
                            </c:if>
                    />
                    <input type="checkbox" name="extra" value="reticel"
                           title="<fmt:message key="room.add.reticle"/>"
                            <c:if test="${room.roomExt.hasReticle}">
                                checked="checked"
                            </c:if>
                    />
                    <input type="checkbox" name="extra" value="tv" title="<fmt:message key="room.add.tv"/>"
                            <c:if test="${room.roomExt.hasTV}">
                                checked="checked"
                            </c:if>
                    />
                    <input type="checkbox" name="extra" value="phone" title="<fmt:message key="room.add.phone"/>"
                            <c:if test="${room.roomExt.hasPhone}">
                                checked="checked"
                            </c:if>
                    />
                    <input type="checkbox" name="extra" value="kettle" title="<fmt:message key="room.add.kettle"/>"
                            <c:if test="${room.roomExt.hasKettle}">
                                checked="checked"
                            </c:if>
                    />
                    <input type="checkbox" name="extra" value="extraBed"
                           title="<fmt:message key="room.add.extra_bed"/>"
                            <c:if test="${room.roomExt.extraBed}">
                                checked="checked"
                            </c:if>
                    />
                </div>
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="room.add.other_facility"/></label></td>
            <td colspan="3">
                <div class=""><textarea disabled class="layui-textarea" id="otherFacility" name="otherFacility"
                                        onblur="validateOtherFacility(this)">${room.roomExt.otherFacility}</textarea>
                </div>
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="note"/></label></td>
            <td colspan="3"><textarea disabled class="layui-textarea" id="note" name="note"
                                      onblur="validateNote(this)">${room.roomExt.note}</textarea></td>
        </tr>
    </table>
</form>
<div id="btnGroup">
    <input type="button" class="layui-btn layui-btn-danger layui-btn-radius" value="<fmt:message key="btn.close"/>"
           onclick="closeMe()"/>
    <div class="changeableBtn">
        <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.ok"/>"
               onclick="closeMe()"/>
    </div>
</div>
</body>
</html>
