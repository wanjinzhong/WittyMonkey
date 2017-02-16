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
<%@ include file="common/laypage.jsp" %>
<html>
<head>
    <title><fmt:message key="floor.manage.title"/></title>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<style type="text/css">
    table {
        margin-top: 20px;
    }
</style>
<body>
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
                <th width="100px"><fmt:message key="floor.manage.floor_no"/></th>
                <th width="100px"><fmt:message key="floor.manage.room.num"/></th>
                <th><fmt:message key="note"/></th>
                <th width="150px"><fmt:message key="floor.manage.entry_user"/></th>
                <th width="200px"><fmt:message key="floor.manage.entry_date"/></th>
                <th width="200px"><fmt:message key="opertion"/></th>
            </tr>
            </thead>
            <tbody id="dataTabel">
            </tbody>
        </table>
        <div id="page"></div>
    </div>
</div>
</body>
<script type="text/javascript">
    $(document).ready(function () {
        page();
    });

    function page(curr) {
        $.ajax({
            type: "GET",
            url: "getFloorByPage.do",
            data: {"curr": curr || 1},
            dataType: "json",
            success: function (data) {
                var res = eval("(" + data + ")");
                var pageSize = res["pageSize"];
                laypage({
                    cont: "page",
                    pages: Math.ceil(res["count"] / pageSize),
                    curr: curr || 1,
                    first: page_first,
                    last: page_last,
                    prev: page_prev,
                    next: page_next,
                    skip: true,
                    jump: function (obj, first) {
                        if (!first) {
                            page(obj.curr);
                        }
                        refreshTable(res["data"]);
                    }
                });
            }
        })
    }

    function refreshTable(obj) {
        var html = "";
        if (obj.length == 0) {
            html = '<tr class="text-c">' +
                '<td colspan="6"><fmt:message key="no_data"/></td>' +
                '</tr>"';
        } else {
            for (var i in obj) {
                html += "<tr class='text-c'>" +
                    "<td>" + obj[i].floorNo + "</td>" +
                    "<td>" + obj[i].roomNum + "</td>" +
                    "<td>" + obj[i].note + "</td>" +
                    "<td>" + obj[i].entryUser + "</td>" +
                    "<td>" + formatDate(obj[i].entryDatetime) + "</td>" +
                    "<td>" +
                    "<span style='margin-right:10px;' class='btn btn-success radius' onclick='editFloor(" + obj[i].floorNo + ")'>" +
                    "<svg class='icon' aria-hidden='true'>" +
                    "<use xlink:href='#icon-bianji'></use>" +
                    "</svg>" +
                    "<fmt:message key='edit'/>" +
                    "</span>" +
                    "<span class='btn btn-danger radius' onclick='deleteFloor(" + obj[i].floorNo + ")'>" +
                    "<svg class='icon' aria-hidden='true'>" +
                    "<use xlink:href='#icon-shanchu1'></use>" +
                    "</svg>" +
                    "<fmt:message key='delete'/>" +
                    "</span>" +
                    "</td>" +
                    "</tr>";
            }
        }
        $("#dataTabel").html(html);
    }
    function deleteFloor(floorNo) {
        layer.confirm(floor_manage_delete_hint, {icon: 7, title: floor_manage_delete_title},
            function (index) {
                $.ajax({
                    url: "deleteFloor.do",
                    data: {"floorNo": floorNo},
                    dataType: "json",
                    type: "GET",
                    success: function (data) {
                        var result = eval("(" + data + ")");
                        switch (result.status) {
                            case 400:
                                layer.msg(floor_manage_delete_not_exist, {
                                    icon: 2, time: 2000
                                }, function () {
                                    parent.location.reload();
                                    closeMe();
                                });
                                break;
                            case 500:
                                layer.msg(error_500, {
                                    icon: 2, time: 2000
                                }, function () {
                                    parent.location.reload();
                                    closeMe();
                                });
                                break;
                            case 200:
                                layer.msg(floor_manage_delete_success, {
                                    icon: 1,
                                    time: 2000
                                }, function () {
                                    window.location.reload();
                                    closeMe();
                                });
                                break;
                        }
                    }
                });
                layer.close(index);
            });
    }
    function editFloor(obj) {
        layer.open({
            type: 2,
            area: ['700px', '350px'],
            maxmin: false,
            shade: 0.4,
            title: floor_manage_edit_title,
            content: "toEditFloor.do?floorNo=" + obj
        });
    }
    function showAddFloor() {
        layer.open({
            type: 2,
            area: ['700px', '350px'],
            maxmin: false,
            shade: 0.4,
            title: floor_manage_add_title,
            content: "toAddFloor.do"
        });
    }
</script>
</html>
