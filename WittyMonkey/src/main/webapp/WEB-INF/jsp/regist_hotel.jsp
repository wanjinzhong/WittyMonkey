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
	font-size: 12px;
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
	margin-top: 10px;
}

td {
	padding: 5px;
}

select {
	width: 90px;
}

#btnGroup {
	margin-top: 5px;
	margin-right: 10px;
	float: right;
}

#closeBtn {
	margin-right: 10px;
}

#question {
	width: 20px;
	height: 20px;
}
#hotel_icon{
	width: 40px;
	height: 40px;
	margin-left: 10px;
}
#form_title{
	margin-left: 10px;
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
		<img src="pic/regist/hotel_icon.png" id="hotel_icon"/>
		<span id="form_title"><fmt:message key="regist.hotel.title" /></span>
		<div id="regist_form">
			<form id="hotel_form" action="toRegistUser.do" method="POST">
				<table>
					<tr>
						<td class="td_title"><fmt:message
								key="regist.hotel.hotel_name" /></td>
						<td><input type="text" class="input-text radius"
							name="hotelName" value="${registHotel.name }"/></td>
					</tr>
					<tr>
						<td class="td_title"><fmt:message
								key="regist.hotel.legal_name" /></td>
						<td><input type="text" class="input-text radius"
							name="legalName" value="${registHotel.legalName }"/></td>
					</tr>
					<tr>
						<td class="td_title"><fmt:message
								key="regist.hotel.legal_idcard" /></td>
						<td><input type="text" class="input-text radius"
							name="legalIdCard" value="${registHotel.legalIdCard }"/></td>
						<td><img id="question" src="pic/regist/question.png"></td>
					</tr>
					<tr>
						<td class="td_title"><fmt:message
								key="regist.hotel.license_no"/></td>
						<td><input type="text" class="input-text radius"
							name="licenseNo" value="${registHotel.licenseNo }"/></td>
					</tr>
					<tr>
						<td class="td_title"><fmt:message key="regist.hotel.place" /></td>
						<td><select id="province" name="provinceCode" onchange="setPlace(this)">
								<c:forEach items="${provinces }" var="province">
									<c:if test="${registHotelProvinceCode eq  province.code}">
										<option value="${province.code }" selected="selected">${province.name}</option>
									</c:if>
									<c:if test="${registHotelProvinceCode ne  province.code}">
										<option value="${province.code }">${province.name}</option>
									</c:if>
								</c:forEach>
								<option value="0"><fmt:message
										key="regist.hotel.place.overseas" /></option>
						</select> &nbsp;&nbsp; <select id="city" onchange="setPlace(this)" name="cityCode">
								<c:forEach items="${cities }" var="city">
									<c:if test="${registHotelCityCode eq  city.code}">
										<option value="${city.code }" selected="selected">${city.name}</option>
									</c:if>
									<c:if test="${registHotelCityCode ne  city.code}">
										<option value="${city.code }">${city.name}</option>
									</c:if>
								</c:forEach>
						</select> &nbsp;&nbsp; <select id="area" name="areaCode">
								<c:forEach items="${areas }" var="area">
									<option value="${area.code }">${area.name}</option>
									<c:if test="${registHotelAreaCode eq  area.code}">
										<option value="${area.code }" selected="selected">${area.name}</option>
									</c:if>
									<c:if test="${registHotelAreaCode ne  area.code}">
										<option value="${area.code }">${area.name}</option>
									</c:if>
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
		<div id="btnGroup">
			<input id="closeBtn" type="button"
				value="<fmt:message key="regist.btn.close"/>"
				class="btn btn-danger radius" onclick="closeMe()" /> <input
				id="nextBtn" type="button"
				value="<fmt:message key="regist.btn.save_and_next"/>"
				class="btn btn-success radius" onclick="toNext()"/>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#question").mouseover(function() {
			layer.tips(regist_license_tip, $("#question"), {
				tips : [ 4, '#3595CC' ],
				time : 4000
			});
		});
		$("#question").mouseout(function(){
			var index = layer.tips();
			layer.close(index); 
		});
	});
	function setPlace(obj) {
		var type = $(obj).attr("id");
		var url;
		if (type == "province") {
			url = "getCityByProvince.do";
		} else if (type == "city") {
			url = "getAreaByCity.do";
		}
		var code = $(obj).val();
		$.ajax({
			url : url,
			data : {
				"code" : code
			},
			dataType : "json",
			success : function(data) {
				var cities = JSON.parse(data);
				var str = "";
				for (var i in cities) {
					str += "<option value='" + cities[i].code + "'>" + cities[i].name + "</option>"
				}
				if (type == "province") {
					$("#city").html(str);
					if (str == "") {
						$("#area").html("");
					} else {
						setPlace($("#city"));
					}
				} else if (type == "city") {
					$("#area").html(str);
				}
			}
		});
	}
	function closeMe() {
		var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		parent.layer.close(index); //再执行关闭 
	}
	function toNext() {
		$("#hotel_form").submit();
	}
</script>
</html>