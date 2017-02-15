<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/2/15
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //User loginUser = (User) session.getAttribute("loginUser");
    //String lang = loginUser.getSetting().getLang();
    String lang = "zh_CN";
%>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@ include file="common/iconfont.jsp" %>
<html>
<head>
    <title><fmt:message key="floor.manage.title"/></title>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
<%--<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>--%>
<fmt:setBundle basename="i18n/messages"/>
<style type="text/css">
    table {
        margin-top: 20px;
    }
</style>
<div>
    <nav class="breadcrumb">
        <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-home"></use>
        </svg>
        <fmt:message key="nav.home"/> <span class="c-gray en">&gt;</span> <fmt:message key="index.menu.floor"/> <span
            class="c-gray en">&gt;</span> <fmt:message key="index.menu.floor"/>
        <a class="btn btn-success radius r"
           style="line-height:1.6em;margin-top:3px"
           href="javascript:location.replace(location.href);"
           title="<fmt:message key="btn.refresh"/>">
            <svg class="icon" aria-hidden="true" id="refresh_btn">
                <use xlink:href="#icon-refresh"></use>
            </svg>
        </a>
    </nav>
    <div id="main">
        <input type="button" class="btn btn-secondary radius" value="<fmt:message key="floor.btn.add"/>"
               onclick="showAddFloor()"/>
        <table class="table table-border table-bordered table-hover table-bg">
            <thead>
            <tr>
                <th><fmt:message key="floor.manage.floor_no"/></th>
                <th><fmt:message key="floor.manage.room.num"/></th>
                <th><fmt:message key="note"/></th>
                <th><fmt:message key="floor.manage.entry_user"/></th>
                <th><fmt:message key="floor.manage.entry_date"/></th>
                <th><fmt:message key="opertion"/></th>
            </tr>
            </thead>
            <c:if test="${fn:length(hotel.floors) eq 0}">
                <tr class="text-c">
                    <td colspan="6"><fmt:message key="no_data"/></td>
                </tr>
            </c:if>
            <c:forEach items="${hotel.floors}" var="floor" varStatus="index">
                <tr class="text-c">
                    <td>${floor.floorNo}</td>
                    <td>${fn:length(floor.roomMasters)}</td>
                    <td>${floor.note}</td>
                    <td>${floor.entryUser.realName}</td>
                    <td>${floor.entryDatetime}</td>
                    <td>
                        <span class="btn btn-success radius">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-bianji"></use>
                            </svg>
                            <fmt:message key="edit"/>
                        </span>
                        <span class="btn btn-danger radius">
                            <svg class="icon" aria-hidden="true">
                                <use xlink:href="#icon-shanchu1"></use>
                            </svg>
                            <fmt:message key="delete"/>
                        </span>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
</body>
<script type="text/javascript">
    function showAddFloor(){
        layer.open({
            type : 2,
            area: ['700px', '350px'],
            maxmin: false,
            shade: 0.4,
            title: floor_manage_add_title,
            content: "toAddFloor.do"
        });
    }
</script>
</html>
