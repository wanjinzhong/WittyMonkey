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
    <form class="layui-form">
        <table>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="room.add.number"/></label></td>
                <td style="width: 300px">
                    <div class="input"><input type="text" class="layui-input" name="roomNum"/></div>
                </td>
                <td><label class="layui-form-label"><fmt:message key="room.add.name"/></label></td>
                <td>
                    <div class="input"><input type="text" class="layui-input" name="roomName"/></div>
                </td>
            </tr>
            <tr>
                <td>
                    <label class="layui-form-label"><fmt:message key="room.add.single_bed.num"/></label></td>
                <td>
                    <div class="input inner"><input type="text" class="layui-input" name="singleBedNum"/></div>
                </td>
                <td><label class="layui-form-label"><fmt:message key="room.add.double_bed.num"/></label></td>
                <td>
                    <div class="input inner"><input type="text" class="layui-input" name="doubleBedNum"/></div>
                </td>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="room.add.availabel.num"/></label></td>
                <td>
                    <div class="input inner"><input type="text" class="layui-input" name="availableNum"/></div>
                </td>
                <td><label class="layui-form-label"><fmt:message key="room.add.floor_no"/></label></td>
                <td>
                    <div class="input inner"><select>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                    </select>
                    </div>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="room.add.extra_info"/></label></td>
                <td colspan="3">
                    <div class="checkbox">
                        <input type="checkbox" name="window" title="<fmt:message key="room.add.window"/>">
                        <input type="checkbox" name="wifi" title="<fmt:message key="room.add.wifi"/>">
                        <input type="checkbox" name="reticel" title="<fmt:message key="room.add.reticle"/>">
                        <input type="checkbox" name="tv" title="<fmt:message key="room.add.tv"/>">
                        <input type="checkbox" name="phone" title="<fmt:message key="room.add.phone"/>">
                        <input type="checkbox" name="kettle" title="<fmt:message key="room.add.kettle"/>">
                        <input type="checkbox" name="extraBed" title="<fmt:message key="room.add.extra_bed"/>">
                    </div>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="room.add.breakfast_num"/></label></td>
                <td>
                    <div class="input"><input type="text" class="layui-input" name="breakfastNum"/></div>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="room.add.other_facility"/></label></td>
                <td colspan="3">
                    <div class=""><textarea class="layui-textarea" name="otherFacility"></textarea></div>
                </td>
            </tr>
            <tr>
                <td><label class="layui-form-label"><fmt:message key="note"/></label></td>
                <td colspan="3"><textarea class="layui-textarea" name="note"></textarea></td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
