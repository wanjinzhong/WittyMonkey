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
    <title><fmt:message key="index.menu.floor.add"/></title>
    <link href="style/common.css" rel="stylesheet"
          type="text/css"/>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
<%--<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>--%>
<fmt:setBundle basename="i18n/messages"/>
<style type="text/css">
    #border {
        position: absolute;
        left: 30px;
        top: 30px;
    }

    #add_form {
        border: 2px solid #eeeeee;
        border-radius: 10px;
        width: 640px;
        height: 200px;
        /*margin: 20px auto;*/

    }

    table {
        width: 400px;
        margin: 0 auto;
        margin-top: 20px;
    }

    #note {
        height: 100px;
    }

    td {
        text-align: right;
        padding: 5px;
    }
    .table-header{
        width: 100px;
    }
</style>
<body>
<div id="border">
    <form id="add_form">
        <!-- java端处理方式 -->
        <input type="hidden" name="method" value="update"/>
        <table>
            <tr>
                <td class="table-header"><label class="layui-form-label"><fmt:message key="floor.manage.floor_no"/></label></td>
                <td><input type="text" class="layui-input" name="floorNo"
                           value="${editFloor.floorNo}"
                           id="floorNo" onblur="validateFloorNo('update',this)"></td>
            </tr>
            <tr>
                <td class="table-header"><label class="layui-form-label"><fmt:message key="note"/></label></td>
                <td><textarea class="layui-textarea" name="note" id="note">${editFloor.note}</textarea></td>
            </tr>
        </table>
    </form>
    <div id="btnGroup">
        <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
               onclick="closeMe()"/>
        <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.save"/>"
               onclick="save()"/>
    </div>
</div>
<script type="text/javascript">
    var layer;
    layui.use('layer', function () {
        layer = layui.layer;
    })
    function save() {
        if (validateFloorNo("update",$("#floorNo"))) {
            $.ajax({
                type: "GET",
                url: "saveFloor.do",
                data: $("#add_form").serialize(),
                dataType: "json",
                success: function (data) {
                    var funResult = eval("(" + data + ")");
                    switch (funResult.status) {
                        case 400:
                            layer.tips(floor_validate_no_exist, $("#floorNo"), {tips: 2});
                            break;
                        case 401:
                            layer.tips(floor_validate_no_wrong, $("#floorNo"), {tips: 2});
                            break;
                        case 410:
                            layer.tips(messageOfValidateLength($("note"), 1024), $("#note"), {tips: 2});
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
                            layer.msg(floor_manage_edit_success, {
                                icon: 1,
                                time: 2000
                            }, function () {
                                parent.location.reload();
                                closeMe();
                            });
                            ;
                            break;
                    }
                }
            });
        }
    }
</script>
</body>
</html>
