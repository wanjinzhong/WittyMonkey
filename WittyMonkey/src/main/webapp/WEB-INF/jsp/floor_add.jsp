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
</style>
<body>
<div id="border">
    <form id="add_form">
        <table>

            <tr>
                <td><fmt:message key="floor.manage.floor_no"/></td>
                <td><input type="text" class="input-text radius" name="floorNo"
                           id="floorNo" onblur="validateFloorNo(this)"></td>
            </tr>
            <tr>
                <td><fmt:message key="note"/></td>
                <td><textarea class="input-text radius" name="note" id="note"></textarea></td>
            </tr>
        </table>
    </form>
    <div id="btnGroup">
        <input type="button" class="btn btn-danger radius" value="<fmt:message key="btn.close"/>"
               onclick="closeMe()"/>
        <input type="button" class="btn btn-success radius" value="<fmt:message key="btn.save"/>"
               onclick="save()"/>
    </div>
</div>
<script type="text/javascript">
    function save() {
        if (validateFloorNo($("#floorNo"))) {
            $.ajax({
                type: "GET",
                url: "addFloor.do",
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
                        case 200:
                            layer.msg(floor_manage_add_success, {
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
