<%--
  Created by IntelliJ IDEA.
  User: neilw
  Date: 2017/2/20
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="common/taglib.jsp" %>
<%@ include file="common/js&css.jsp" %>
<%@include file="common/iconfont.jsp" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style/common.css"/>
    <script type="text/javascript" src="js/common.js"></script>
    <script type="text/javascript" src="js/stock.js"></script>
    <!-- 根据设置动态加载js语言 -->
    <script type="text/javascript" src="i18n/messages_${loginUser.setting.lang }.js"></script>
    <fmt:setBundle basename="i18n/messages_${loginUser.setting.lang }"/>
</head>
<body>
<form id="outstock_form" class="layui-form">
    <input type="hidden" id="method" value="out"/>
    <table class="form_table">
        <tr>
            <td><label class="layui-form-label"><fmt:message key="materiel.barcode"/></label></td>
            <td style="width: 200px">
                <div class="input">
                    <input class="layui-input" id="barcode" name="barcode" list="barcodeList"
                           onblur="validateBarcode()"/>
                    <datalist id="barcodeList">
                        <c:forEach items="${materiels}" var="materiel">
                            <option>${materiel.barcode}</option>
                        </c:forEach>
                    </datalist>
                </div>
            </td>
            <td><label class="layui-form-label"><fmt:message key="materiel.name"/></label></td>
            <td>
                <div class="input">
                    <input type="text" class="layui-input" id="name" name="name" list="nameList"
                           onblur="validateName()"/>
                    <datalist id="nameList">
                        <c:forEach items="${materiels}" var="materiel">
                            <option>${materiel.name}</option>
                        </c:forEach>
                    </datalist>
                </div>
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="outstock.type"/></label></td>
            <td>
                <div>
                    <select id="type" name="type">
                        <option value="1"><fmt:message key="outstock.type.sell"/></option>
                        <option value="2"><fmt:message key="outstock.type.consume"/></option>
                        <option value="3"><fmt:message key="outstock.type.damage"/></option>
                    </select>
                </div>
            </td>
        </tr>
        <tr>
            <td>
                <label class="layui-form-label"><fmt:message key="outstock.sell_price"/></label></td>
            <td>
                <div class="input inner"><input type="number" class="layui-input" id="sellPrice" name="sellPrice" value="0"
                                                onblur="calc()"/></div>
            </td>
            <td>
                <label class="layui-form-label"><fmt:message key="outstock.qty"/></label></td>
            <td>
                <div class="input inner"><input type="number" class="layui-input" id="qty" name="qty" value="0"
                                                onblur="calc()"/></div>
            </td>
        </tr>
        <tr>
            <td><label class="layui-form-label"><fmt:message key="outstock.pay"/></label></td>
            <td>
                <div class="input inner"><input type="number" class="layui-input" id="pay" name="pay" value="0"/></div>
            </td>
        </tr>
        <tr>
            <td style="margin-top: 10px;"><label class="layui-form-label"><fmt:message key="note"/></label></td>
            <td colspan="3"><textarea class="layui-textarea" id="note" name="note"
                                      onblur="validateNote(this)"></textarea></td>
        </tr>
    </table>
</form>
<div id="btnGroup">
    <input type="button" class="layui-btn layui-btn-danger layui-btn-radius" value="<fmt:message key="btn.close"/>"
           onclick="closeMe()"/>
    <input type="button" class="layui-btn layui-btn-radius" value="<fmt:message key="btn.save"/>" onclick="save()"/>
</div>
</body>
</html>
