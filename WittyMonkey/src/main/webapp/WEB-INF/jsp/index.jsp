<%@ page language="java" contentType="text/html; charset=UTF-8" import="com.wittymonkey.entity.User"
         pageEncoding="UTF-8" %>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@ include file="common/iconfont.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="name"/></title>
</head>
<script type="text/javascript" src="js/common.js"></script>
<link href="style/common.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="style/index.css"/>
<script src="js/index.js"></script>
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<body>
<div class="layui-layout layui-layout-admin">
    <div class="layui-header header header-demo">
        <div class="layui-main">
                <div class="title">
                    <img src="pic/logo.gif" style="width: 50px; height: 50px;vertical-align: middle">
                    <span class="hotel-name">
                        <%--${hotel.name}--%>
                    </span>

                </div>
            <ul class="layui-nav admin-header-item">

                <li class="layui-nav-item" id="video1">
                    <a href="javascript:;" id="roles">
                        <%--<c:forEach items="${loginUser.roles}" var="role" varStatus="index">
                            ${role.name}
                            <c:if test="${fn:length(loginUser.roles) < index.index + 1}">/</c:if>
                        </c:forEach>--%>
                    </a>
                </li>
                <li class="layui-nav-item">
                    <a href="javascript:;" class="admin-header-user">
                        <span id="realName">
                        <%--    ${loginUser.realName}--%>
                        </span>
                    </a>
                    <dl class="layui-nav-child">
                        <%--<dd>--%>
                            <%--<a href="javascript:;"><i class="fa fa-user-circle" aria-hidden="true"></i> 个人信息</a>--%>
                        <%--</dd>--%>
                        <%--<dd>--%>
                            <%--<a href="javascript:;"><i class="fa fa-gear" aria-hidden="true"></i> 设置</a>--%>
                        <%--</dd>--%>
                        <%--<dd id="lock">--%>
                            <%--<a href="javascript:;">--%>
                                <%--<i class="fa fa-lock" aria-hidden="true" style="padding-right: 3px;padding-left: 1px;"></i> 锁屏 (Alt+L)--%>
                            <%--</a>--%>
                        <%--</dd>--%>
                        <dd>
                            <a href="logout.do"><i class="fa fa-sign-out" aria-hidden="true"></i><fmt:message key="logout"/></a>
                        </dd>
                    </dl>
                </li>
            </ul>
            <ul class="layui-nav admin-header-item-mobile">
                <li class="layui-nav-item">
                    <a href="login.html"><i class="fa fa-sign-out" aria-hidden="true"></i> 注销</a>
                </li>
            </ul>
        </div>
    </div>
    <div class="layui-side layui-bg-black" id="admin-side">
        <div class="layui-side-scroll" id="admin-navbar-side" lay-filter="side">
        </div>
    </div>
    <div class="layui-body" style="bottom: 0;border-left: solid 2px #1AA094;" id="admin-body">
        <div class="layui-tab admin-nav-card layui-tab-brief" lay-filter="admin-tab">
            <%--<div class="side-toggle" onclick="sideBtnClick()">--%>
                <%--<svg class="icon sideBtn" aria-hidden="true">--%>
                    <%--<use xlink:href="#icon-caidan"/>--%>
                <%--</svg>--%>
            <%--</div>--%>
            <ul class="layui-tab-title">
                <li class="layui-this">
                    <i class="fa fa-dashboard" aria-hidden="true"></i>
                    <cite><fmt:message key="dashboard"/></cite>
                </li>
            </ul>
            <div class="layui-tab-content">
                <div class="layui-tab-item layui-show">
                    <iframe src="toDashboard.do" class="frame-content"></iframe>
                </div>
            </div>
        </div>
    </div>

    <script>
    </script>
</div>
</body>
</html>
