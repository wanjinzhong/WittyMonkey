<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/5/3
  Time: 17:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@ include file="common/iconfont.jsp" %>
<html>
<head>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.config.js"></script>
    <script type="text/javascript" src="lib/ueditor/1.4.3/ueditor.all.js"></script>
    <script type="text/javascript" src="js/notify_add.js"></script>
</head>
<script type="text/javascript" src="js/common.js"></script>
<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<style type="text/css">
    table {
        margin: 5px 30px;
    }

    td {
        padding: 10px;
    }
</style>
<form>
    <table>
        <tr>
            <td style="width: 70px; padding: 0;"><span class="layui-btn layui-btn-primary" style="padding: 0 5px"><fmt:message key="notify.receiver"/></span></td>
            <td><input class="layui-input" id="receivers" name="receivers" placeholder="<fmt:message key="notify.choose_receiver"/>"/></td>
        </tr>
        <tr>
            <td style="width: 70px; padding: 0;"><fmt:message key="notify.subject"/></td>
            <td><input class="layui-input" id="subject" name="subject"/></td>
        </tr>
        <tr>
            <td colspan="3" style="text-align: left">
                <!-- 加载编辑器的容器 -->
                <script id="container" name="content" type="text/plain">
                </script>
            </td>
        </tr>
    </table>
    <div id="btnGroup">
        <input type="button" class="layui-btn layui-btn-radius layui-btn-danger" value="<fmt:message key="btn.close"/>"
               onclick="closeMe()"/>
        <i class="layui-icon layui-btn layui-btn-radius" onclick="send()" style="font-size: 13px">&#xe609;
            <fmt:message key="btn.send"/></i>
    </div>
</form>
</body>
</html>
