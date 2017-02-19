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
<html>
<head>
    <title><fmt:message key="floor.manage.title"/></title>
</head>

<!-- 根据设置动态加载js语言 -->
<script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
<fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
<style>
</style>
<body>
<div>
    <nav class="breadcrumb">
        <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-home"></use>
        </svg>
        <fmt:message key="nav.home"/> <span class="c-gray en">&gt;</span> <fmt:message key="index.menu.room"/> <span
            class="c-gray en">&gt;</span> <fmt:message key="index.menu.room"/>
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
        <input type="button" class="btn btn-secondary radius" value="<fmt:message key="room.btn.add"/>"
               onclick="showAddFloor()"/>

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
        <div id="search">
            <div class="layui-form">
                <div class="layui-input-block">
                    <select name="city" lay-verify="required">
                        <option value=""></option>
                        <option value="0"><fmt:message key="room.search.method.status"/></option>
                        <option value="1"><fmt:message key="room.search.method.floo_no"/></option>
                        <option value="2"><fmt:message key="room.search.method.room_no"/></option>
                        <option value="3"><fmt:message key="room.search.method.room_name"/></option>
                        <option value="4"><fmt:message key="room.search.method.person_num"/></option>
                    </select>
                </div>
            </div>
        </div>
        <div class="roomBorder roomBorder1">
            <span class="number">101</span>
            <span class="name">情侣房</span>
            <br/>
            <div class="btn_opt">
                <div class="btn_change">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-huanfang"></use>
                    </svg>
                    换房
                </div>
                <div class="btn_opt btn_checkout">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-tuifang"></use>
                    </svg>
                    退房
                </div>
            </div>
        </div>
        <div class="roomBorder roomBorder2">
            <span class="number">102</span>
            <span class="name">单人房</span>
            <br/>
            <div class="btn_opt">
                <div class="btn_book">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-icon04"></use>
                    </svg>
                    预订
                </div>
                <div class="btn_opt btn_checkin">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-ruzhu"></use>
                    </svg>
                    入住
                </div>
            </div>
        </div>
        <div class="roomBorder roomBorder3">
            <span class="number">103</span>
            <span class="name">标间</span>
            <br/>
            <div class="btn_opt">
                <div class="btn_unbook">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-tuiding"></use>
                    </svg>
                    退订
                </div>
                <div class="btn_opt btn_checkin">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-ruzhu"></use>
                    </svg>
                    入住
                </div>
            </div>
        </div>
        <div class="roomBorder roomBorder4">
            <span class="number">104</span>
            <span class="name">套房</span>
            <br/>
            <div class="btn_opt">
                <div class="btn_clean">
                    <svg class="icon" aria-hidden="true">
                        <use xlink:href="#icon-qingli"></use>
                    </svg>
                    已清理
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
