<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
	String lang;
	if (request.getLocale().equals(java.util.Locale.US)) {
		lang = "en_US";
	} else {
		lang = "zh_CN";
	}
%>
<%@ include file="common/taglib.jsp"%>
<%@ include file="common/js&css.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="name" /></title>
<link href="lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet"
	type="text/css" />
<link href="style/common.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
<fmt:setBundle basename="i18n/messages" />
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

#proccess {
	position: absolute;
	left: 50px;
	top: 10px;
}

#regist_hotel {
	position: absolute;
	top: 100px;
	left: 30px;
}

.regist_step {
	font-size: 10px;
}

.hotel {
	font-size: 12px;
	margin-left: -18px;
	font-weight: bold;
	margin-left: -18px;
}

.user {
	margin-left: 192px;
}

.complete {
	margin-left: 240px;
}

#regist_form {
	border: 2px solid #eeeeee;
	border-radius: 10px;
	width: 690px;
	height: 330px;
	margin-top: 10px;
}

table {
	width: 500px;
	margin: 0 auto;
}

td {
	padding: 5px;
}

select {
	width: 100px;
}
</style>
<body>
	<fmt:setBundle basename="i18n/messages" />
	<div id="proccess">
		<img class="img_circle" src="pic/regist/circle_green.png" /><img
			class="img_line" src="pic/regist/line_gray.png" /><img
			class="img_circle" src="pic/regist/circle_gray.png" /><img
			class="img_line" src="pic/regist/line_gray.png" /><img
			class="img_circle" src="pic/regist/circle_gray.png"> <br /> <span
			class="regist_step hotel"><fmt:message key="regist.step.hotel" /></span>
		<span class="regist_step user"><fmt:message
				key="regist.step.user" /></span> <span class="regist_step complete"><fmt:message
				key="regist.step.complete" /></span>
	</div>
	<div id="regist_hotel">
		<span id="form_title"><fmt:message key="regist.hotel.title" /></span>
		<form action="#" id="regist_form">
			<table>
				<tr>
					<td class="td_title"><fmt:message
							key="regist.hotel.hotel_name" /></td>
					<td><input type="text" class="input-text radius"
						name="hotelName" /></td>
				</tr>
				<tr>
					<td class="td_title"><fmt:message
							key="regist.hotel.legal_name" /></td>
					<td><input type="text" class="input-text radius"
						name="legalName" /></td>
				</tr>
				<tr>
					<td class="td_title"><fmt:message
							key="regist.hotel.legal_idcard" /></td>
					<td><input type="text" class="input-text radius"
						name="legalIdCard" /></td>
				</tr>
				<tr>
					<td class="td_title"><fmt:message
							key="regist.hotel.license_no" /></td>
					<td><input type="text" class="input-text radius"
						name="licenseNo" /></td>
				</tr>
				<tr>
					<td class="td_title"><fmt:message key="regist.hotel.place" /></td>
					<td><select id="province" onchange="setCity(this)">
							<c:forEach items="${provinces }" var="province">
								<option value="${province.code }">${province.name}</option>
							</c:forEach>
							<option value="0"><fmt:message
									key="regist.hotel.place.overseas" /></option>
					</select> &nbsp;&nbsp; <select id="city" onchange="setArea(this)">
							<c:forEach items="${cities }" var="city">
								<option value="${city.code }">${city.name}</option>
							</c:forEach>
					</select> &nbsp;&nbsp; <select id="area">
							<c:forEach items="${areas }" var="area">
								<option value="${area.code }">${area.name}</option>
							</c:forEach>
					</select></td>
				</tr>
				<tr>
					<td class="td_title"><fmt:message
							key="regist.hotel.place.detail" /></td>
					<td><textarea class="textarea radius" name="placeDetail"></textarea></td>
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	function setCity(obj) {
		var provinceCode = $(obj).val();
		$.ajax({
			url:"getCityByProvince.do",
			data: {"code": provinceCode},
			dataType: "json",
			success: function(data) {
				$(".textarea").val(data);
				var cities = eval("(" + data + ")");
				var html = "";
				for (var i = 0; i < cities.length; i ++){
					html += "<option value='" + cities[i].code +"'>" + i + cities[i].name + "</option>"
				}
				$("#city").html(html);
			}
 		});
	}
</script>
</html>