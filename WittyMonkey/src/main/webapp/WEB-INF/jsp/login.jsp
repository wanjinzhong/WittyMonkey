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

h2 {
	margin-top: 50px;
	text-align: center;
}

#username,#password {
	margin: 0 auto;
	width: 300px;
}

.btn {
	width: 80px;
}

table {
	
}

td {
	padding-top: 20px;
	text-align: center;
}
</style>
<body background="pic/login/login.png">
	<div class="responsive">
		<img id="logo" src="pic/logo.gif" />
		<form id="login-form" action="index.do" method="post">
			<h2 id="fullname">
				<fmt:message key="fullname" />
			</h2>
			<table>
				<tr>
					<td><input id="username" name="username" type="text"
						placeholder="<fmt:message key='login.username'/>"
						class="input-text radius" /></td>
				</tr>
				<tr>
					<td><input id="password" name="password" type="text"
						placeholder="<fmt:message key='login.password'/>"
						class="input-text radius" /></td>
				</tr>
				<tr>
					<td><input type="submit" class="btn btn-success radius"
						value=<fmt:message key="login.loginbtn"/>> <input
						type="button" class="btn btn-secondary radius"
						value=<fmt:message key="login.registbtn"/>></td>
				</tr>
			</table>

		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#fullname").addClass("hui-bouncein");
	})
</script>
</html>