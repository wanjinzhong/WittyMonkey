<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
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
    <link href="style/regist.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
    <script type="text/javascript" src="js/regist_validator.js"></script>
    <script type="text/javascript" src="js/regist_hotel.js"></script>
    <fmt:setBundle basename="i18n/messages"/>
</head>

<body>
<fmt:setBundle basename="i18n/messages"/>
<div id="proccess">
    <img class="img_circle" src="pic/regist/circle_green.png"/><img
        class="img_line" src="pic/regist/line_gray.png"/><img
        class="img_circle" src="pic/regist/circle_gray.png"/><img
        class="img_line" src="pic/regist/line_gray.png"/><img
        class="img_circle" src="pic/regist/circle_gray.png"> <br/> <span
        class="regist_step hotel"><fmt:message key="regist.step.hotel"/></span>
    <span class="regist_step user"><fmt:message
            key="regist.step.user"/></span> <span class="regist_step complete"><fmt:message
        key="regist.step.complete"/></span>
</div>
<div id="regist_hotel">
    <svg id="icon_hotel" class="icon" aria-hidden="true">
        <use xlink:href="#icon-jiudian"></use>
    </svg>
    <span id="form_title"><fmt:message key="regist.hotel.title"/></span>
    <div id="regist_form">
        <form id="hotel_form" class="layui-form" action="toRegistUser.do" method="POST">
            <table>
                <tr>
                    <td class="td_title"><label class="layui-form-label"><fmt:message
                            key="regist.hotel.hotel_name"/></label></td>
                    <td><input type="text" class="layui-input" id="hotelName"
                               name="hotelName" value="${registHotel.name }" onblur="validateHotelName(this)"/></td>
                </tr>
                <tr>
                    <td class="td_title"><label class="layui-form-label"><fmt:message
                            key="regist.hotel.license_no"/></label></td>
                    <td><input type="text" class="layui-input"
                               name="licenseNo" value="${registHotel.licenseNo }"
                               id="licenseNo"
                               onkeypress="validatePlace(this)"
                               onblur="if(validateLicenseNo(this))validatePlace(this)"/></td>
                    <td>
                        <svg id="question" class="icon" aria-hidden="true">
                            <use xlink:href="#icon-wenhao"></use>
                        </svg>
                    </td>
                </tr>
                <tr>
                    <td class="td_title"><label class="layui-form-label"><fmt:message
                            key="regist.hotel.legal_name"/></label></td>
                    <td><input type="text" class="layui-input"
                               id="legalName"
                               name="legalName" value="${registHotel.legalName }"
                               onblur="validateLegalName(this)"/></td>
                </tr>
                <tr>
                    <td class="td_title"><label class="layui-form-label"><fmt:message
                            key="regist.hotel.legal_idcard"/></label></td>
                    <td><input type="text" class="layui-input"
                               name="legalIdCard" id="idCard" value="${registHotel.legalIdCard }"
                               onblur="validateIdCard(this)"/></td>
                </tr>
                <tr>
                    <td class="td_title"><label class="layui-form-label"><fmt:message key="regist.hotel.place"/></label></td>
                    <td>
                        <div class="place">
                            <select id="province" name="provinceCode">
                                <c:forEach items="${provinces }" var="province">
                                    <c:if test="${registHotelProvinceCode eq  province.code}">
                                        <option value="${province.code }" selected="selected">${province.name}</option>
                                    </c:if>
                                    <c:if test="${registHotelProvinceCode ne  province.code}">
                                        <option value="${province.code }">${province.name}</option>
                                    </c:if>
                                </c:forEach>
                                <option value="0"><fmt:message
                                        key="regist.hotel.place.overseas"/></option>
                            </select>
                        </div>&nbsp;&nbsp;
                        <div class="place"><select id="city" onchange="setPlace(this)" name="cityCode">
                            <c:forEach items="${cities }" var="city">
                                <c:if test="${registHotelCityCode eq  city.code}">
                                    <option value="${city.code }" selected="selected">${city.name}</option>
                                </c:if>
                                <c:if test="${registHotelCityCode ne  city.code}">
                                    <option value="${city.code }">${city.name}</option>
                                </c:if>
                            </c:forEach>
                        </select></div> &nbsp;&nbsp;
                        <div class="place"><select id="area" name="areaCode">
                            <c:forEach items="${areas }" var="area">
                                <c:if test="${registHotelAreaCode eq  area.code}">
                                    <option value="${area.code }" selected="selected">${area.name}</option>
                                </c:if>
                                <c:if test="${registHotelAreaCode ne  area.code}">
                                    <option value="${area.code }">${area.name}</option>
                                </c:if>
                            </c:forEach>
                        </select></div>
                    </td>
                </tr>
                <tr>
                    <td class="td_title"><label class="layui-form-label"><fmt:message
                            key="regist.hotel.place.detail"/></label></td>
                    <td><textarea class="layui-textarea"
                                  id="placeDetail" name="placeDetail"
                                  onblur="validatePlaceDetail(this)">${registHotel.placeDetail}</textarea></td>
                </tr>
            </table>
        </form>
    </div>
    <div id="btnGroup">
        <input id="closeBtn" type="button"
               value="<fmt:message key="regist.btn.close"/>"
               class="layui-btn layui-btn-radius layui-btn-danger" onclick="closeMe()"/> <input
            id="nextBtn" type="button"
            value="<fmt:message key="regist.btn.save_and_next"/>"
            class="layui-btn layui-btn-radius" onclick="toNext()"/>
    </div>
</div>
</body>
</html>