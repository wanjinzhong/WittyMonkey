<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<%@ include file="common/taglib.jsp"%>
<%@ include file="common/i18n.jsp"%>
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
#login_form {
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

#loginName, #password {
	margin: 0 auto;
	width: 300px;
}

#code {
	width: 195px;
}

.btn {
	width: 125px;
}

table {
	
}

td {
	padding-top: 20px;
	text-align: center;
}

#regist {
	margin-left: 50px;
}

.login_form_to_left {
	
}
</style>
<body id="b" background="pic/login/login.png">
	<div class="responsive">
		<img id="logo" src="pic/logo.gif" />
		<form id="login_form" action="login.do" method="post">
			<h2 id="fullname">
				<fmt:message key="fullname" />
			</h2>
			<table id="login_table">
				<tr>
					<td><input id="loginName" name="loginName" type="text"
						placeholder="<fmt:message key='login.loginname'/>"
						class="input-text radius" /></td>
				</tr>
				<tr>
					<td><input id="password" name="password" type="password"
						placeholder="<fmt:message key='login.password'/>"
						class="input-text radius" /></td>
				</tr>
				<tr>
					<td><input type="text" id="code" name="code"
						class="input-text radius"
						placeholder="<fmt:message key='login.validateCode'/>" /> <a
						href="javascript:changeCode();"><img id="codeImage"
							src="ValidateCodeServlet" /></a></td>
				</tr>
				<tr>
					<td><input type="button" class="btn btn-success radius"
						value="<fmt:message key="login.loginbtn"/>" onclick="login()" /> <input
						type="button" id="regist" class="btn btn-secondary radius"
						value="<fmt:message key="login.registbtn"/>"></td>
				</tr>
			</table>

		</form>
	</div>
</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#fullname").addClass("hui-bouncein");
		$("#regist").click(function() {
			layer.open({
				type : 2,
				area : [ '750px', '500px' ],
				fix : true,
				maxmin : false,
				shade : 0.4,
				title : $.i18n.prop('regist.title'),
				content : "toRegist.do"
			});

		});
	})
	function changeCode() {
		var time = new Date().getTime();
		$("#codeImage").attr("src", "ValidateCodeServlet?time=" + time);
	}
	function login() {
		var form = $("#login_form");
		if ($("#loginName").val() == "") {
			layer.tips($.i18n.prop("regist.input_name_first"), $("#loginName"),
					{
						tips : 4
					});
			return;
		} else if ($("#password").val() == "") {
			layer.tips($.i18n.prop("regist.input_password_first"),
					$("#password"), {
						tips : 4
					});
			return;
		} else if ($("#validateCode").val() == "") {
			layer.tips($.i18n.prop("regist.input_validate_code_first"),
					$("#validateCode"), {
						tips : 4
					});
			return;
		} else {
			var code = $("#validatePicCode").val();
			$.ajax({
				type : "POST",
				url : "login.do",
				data : $("#login_form").serialize(),
				dataType : "json",
				success : function(data) {
					var result = eval("(" + data + ")");
					switch (result.status) {
					case 400:
						layer.msg($.i18n
								.prop('regist.input_name_first'), {
							time : 3000,
							icon : 5
						});
						return;
						break;
					case 410:
						layer.msg($.i18n.prop('regist.input_password_first'), {
							time : 3000,
							icon : 5
						});
						return;
						break;
					case 420:
						layer.msg($.i18n.prop('regist.input_validate_code_first'), {
							time : 3000,
							icon : 5
						});
						return;
						break;
					case 421:
						layer.msg($.i18n.prop('regist.code_is_wrong'), {
							time : 3000,
							icon : 5
						});
						return;
						break;
					case 430:
						layer.msg($.i18n.prop('regist.login_error'), {
							time : 3000,
							icon : 5
						});
						return;
						break;
					case 200:
						window.location = result.url;
						break;
					}
				}
			});
		}
	}
</script>
</html>