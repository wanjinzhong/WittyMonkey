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
<style>
    .roomBorder {
        width: 180px;
        height: 80px;
        border-radius: 10px;
        padding: 10px;
        transition: background-color, box-shadow 0.8s, 0.8s;
        margin: 10px;
        display: inline-block;
        box-shadow: 2px 2px 2px #A9A9A9;
    }

    .roomBorder:hover {
        box-shadow: 4px 4px 4px #C0C0C0;
    }

    .roomBorder1 {
        background-color: #EE6363;
    }

    .roomBorder1:hover {
        background-color: #EE3B3B;
    }

    .roomBorder2 {
        background-color: #7CCD7C;
    }

    .roomBorder2:hover {
        background-color: #66CD00;
    }

    .roomBorder3 {
        background-color: #6495ED;
    }

    .roomBorder3:hover {
        background-color: #4876FF;
    }

    .roomBorder4 {
        background-color: #EEB422;
    }

    .roomBorder4:hover {
        background-color: #EE9A49;
    }

    .number {
        color: white;
        font-size: 30px;
    }

    .hint div {
        width: 20px;
        height: 20px;
        border-radius: 10px;
        display: inline-block;
    }

    .red {
        background-color: #EE6363;
    }

    .green {
        background-color: #7CCD7C;
    }

    .blue {
        background-color: #6495ED;
    }

    .yellow {
        background-color: #EEB422;
    }

    .hint span {
        display: inline-block;
        line-height: 20px;
        vertical-align: top;
    }

    #container {
        margin: 10px;
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
    <div id="container">
        <div class="hint">
            <div class="red"></div>
            <span>&nbsp;已入住</span>&nbsp;&nbsp;
            <div class="green"></div>
            <span>&nbsp;空闲</span>&nbsp;&nbsp;
            <div class="blue"></div>
            <span>&nbsp;已预定</span>&nbsp;&nbsp;
            <div class="yellow"></div>
            <span>&nbsp;待清理</span>&nbsp;&nbsp;
        </div>
        <br/>
        <div class="roomBorder roomBorder1">
            <span class="number">101</span>
            <span class="name">情侣房</span>
        </div>
        <div class="roomBorder roomBorder2">
            <span class="number">102</span>
            <span class="name">单人房</span>
        </div>
        <div class="roomBorder roomBorder3">
            <span class="number">103</span>
            <span class="name">标间</span>
        </div>
        <div class="roomBorder roomBorder4">
            <span class="number">104</span>
            <span class="name">套房</span>
        </div>
    </div>
</div>
</body>
</html>
