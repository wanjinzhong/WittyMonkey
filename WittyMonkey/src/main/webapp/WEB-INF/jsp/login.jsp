<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%@ include file="common/taglib.jsp"%>
<%@ include file="common/i118n.jsp"%>
<%@ include file="common/js&css.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><fmt:message key="name" /></title>
<link href="lib/Hui-iconfont/1.0.7/iconfont.css" rel="stylesheet"
	type="text/css" />
</head>
<style>
#login-form {
	position: absolute;
	width: 600px;
	height: 480px;
	left: 50%;
	top: 50%;
	background-image: url(pic/login/login_background.png);
	background-size: cover;
	margin: -240px 0 0 -300px;
}

#logo {
	width: 150px;
	height: 150px;
}

h1 {
	margin-top: 50px;
	text-align: center;
}
</style>
<body background="pic/login/login.png">
	<img id="logo" src="pic/logo.gif" />
	<form id="login-form">
		<h1 id="fullname">
			<fmt:message key="fullname" />
		</h1>
		<table>
			<tr>
				<td>
					<input type="text" placeholder=<fmt:message key='login.username'/> class="input-text radius"/>
				</td>
			</tr>
		</table>
	</form>
</body>
<script type="text/javascript">
$(document).ready(function(){
	$("#fullname").addClass("hui-bouncein");
})
</script>
</html>