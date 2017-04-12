<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/2/14
  Time: 15:40
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String contextPath = request.getContextPath();
    String lang;
    if (request.getLocale().equals(java.util.Locale.US)) {
        lang = "en_US";
    } else {
        lang = "zh_CN";
    }
%>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@include file="common/iconfont.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><fmt:message key="name"/></title>
    <link href="style/common.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
    <fmt:setBundle basename="i18n/messages"/>
</head>
<style type="text/css">
    .img_circle {
        height: 50px;
        width: 50px;
    }

    .img_line {
        height: 50px;
        width: 250px;
    }

    .regist_step {
        font-size: 12px;
    }

    .hotel {
        position: absolute;
        font-size: 12px;
        left: -18px;
        font-weight: bold;
    }

    #proccess {
        position: absolute;
        left: 50px;
        top: 10px;
    }

    #regist_complete {
        position: absolute;
        top: 100px;
        left: 30px;
    }

    #result_form {
        border: 2px solid #eeeeee;
        border-radius: 10px;
        width: 690px;
        height: 330px;
        margin-top: 10px;
    }

    .hotel {
        position: absolute;
        font-size: 12px;
        left: -18px;
        font-weight: bold;
    }

    .user {
        position: absolute;
        left: 275px;
    }

    .complete {
        position: absolute;
        left: 615px;
    }

    #icon_complete {
        width: 40px;
        height: 40px;
        margin-left: 10px;
    }

    #disp {
        margin-top: 50px;
        margin-left: 30px;
    }

    .hint {
        font-size: 20px;
        font-weight: bold;
        text-align: center;
        width: 100%;
        display: inline-block;
    }

    #loginNameHint, #emailHint {
        font-size: 18px;
        font-weight: bold;
        width: 120px;
    }

    #emailHint {
        margin-top: 10px;
    }

    #loginName, #email {
        font-weight: bold;
    }

    table {
        margin: 0 auto;
        margin-top: 20px;
    }

    td {
        text-align: left;
        padding-bottom: 10px;
    }

    #form_title {
        line-height: 40px;
        margin-left: 10px;
        vertical-align: top;
    }

    #btnGroup {
        margin-top: 5px;
        margin-right: 10px;
        float: right;
    }
</style>
<body>
<fmt:setBundle basename="i18n/messages"/>
<div id="proccess">
    <img class="img_circle" src="pic/regist/circle_green.png"/><img
        class="img_line" src="pic/regist/line_green.png"/><img
        class="img_circle" src="pic/regist/circle_green.png"/><img
        class="img_line" src="pic/regist/line_green.png"/><img
        class="img_circle" src="pic/regist/circle_green.png"> <br/> <span
        class="regist_step hotel"><fmt:message key="regist.step.hotel"/></span>
    <span class="regist_step user"><fmt:message
            key="regist.step.user"/></span> <span class="regist_step complete"><fmt:message
        key="regist.step.complete"/></span>
</div>
<div id="regist_complete">
    <svg id="icon_complete" class="icon" aria-hidden="true">
        <use xlink:href="#icon-chenggong"></use>
    </svg>
    <span id="form_title"><fmt:message key="regist.complete.title"/></span>
    <div id="result_form">
        <div id="disp">
            <span class="hint"><fmt:message key="regist.complete.staff_no.hint"/>${staffNo}</span>
            <span class="hint"><fmt:message key="regist.complete.login_method.hint"/> </span>
        </div>
    </div>
    <div id="btnGroup">
        <input class="layui-btn layui-btn-radius" type="button" value="<fmt:message key="btn.complete"/>"
               onclick="complete()">
    </div>
</div>
</body>
<script type="text/javascript">
    function complete() {
        var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
        parent.layer.close(index); //再执行关闭
    }
</script>
</html>
