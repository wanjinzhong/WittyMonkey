<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="com.wittymonkey.entity.User"%>
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
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="i18n/messages_<%=lang%>.js"></script>
<fmt:setBundle basename="i18n/messages" />
</head>
<style>
#logo {
	width: 150px;
	height: 150px;
}

h2 {
	margin-top: 50px;
	text-align: center;
}

#loginName, #password, #email, #repassword, #code {
	margin: 0 auto;
	width: 400px;
}

#email {
	width: 270px;
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
		<form id="regist_form" action="index.do" method="post">
			<table id="login_table">
				<tr>
					<td><input id="loginName" name="loginName" type="text"
						placeholder="<fmt:message key='regist.username'/>"
						class="input-text radius" onblur="validateLoginName(this)" /></td>
				</tr>
				<tr>
					<td><input id="password" name="password" type="password"
						placeholder="<fmt:message key='regist.password'/>"
						class="input-text radius" onblur='validatePassword(this)' /></td>
				</tr>
				<tr>
					<td><input id="repassword" name="repassword" type="password"
						placeholder="<fmt:message key='regist.repassword'/>"
						class="input-text radius" onblur="validateRepassword(this)" /></td>
				</tr>
				<tr>
					<td><input id="email" name="email" type="text"
						placeholder="<fmt:message key='regist.email'/>"
						class="input-text radius" onblur="validateEmail(this)" /> <input
						type="button" id="get_code" class="btn btn-secondary radius"
						value="<fmt:message key='regist.get_code'/>"></td>
				</tr>
				<tr>
					<td><input id="code" name="code" type="text"
						placeholder="<fmt:message key='regist.code'/>"
						class="input-text radius" /></td>
				</tr>
				<tr>
					<td><input type="button" class="btn btn-success radius"
						value="<fmt:message key="regist.registbtn" />" onclick="regist()" />
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	var time = 60;
	$("#get_code").click(function() {
		var email = $("#email").val();
		$.ajax({
			type : "GET",
			url : "getValidateCode.do",
			data : {
				"email" : email
			},
			dataType : "text",
			success : function(data) {
			}
		});
		t1 = window.setInterval("cutdown()", 1000);
		$("#get_code").removeClass("btn-secondary");
		$("#get_code").addClass("disabled");
		$("#get_code").attr("disabled", true);
		$("#get_code").val(time);
	});
	function regist() {
		if (!validateRegistUserForm($("#regist_form"))) {
			return;
		}
		var myCode = $("#code").val();
		validateCode($("#code"));
		$.ajax({
			type : "POST",
			url : "regist.do",
			data : $("#regist_form").serialize(),
			dataType : "json",
			success : function(data) {
				var result = eval("(" + data + ")");
				var stu = result.status;
				switch (stu) {
				case 400:
					layer.tips(regist_input_name_first,
							$("#loginName"), {
								tips : 4
							});
					break;
				case 401:
					layer.tips(regist_user_is_exist,
							$("#loginName"), {
								tips : 4
							});
					break;
				case 410:
					layer.tips(regist_password_less_six,
							$("#password"), {
								tips : 4
							});
					break;
				case 411:
					layer.tips(regist_password_not_same,
							$("#repassword"), {
								tips : 4
							});
					break;
				case 420:
					layer.tips(regist_email_is_wrong,
							$("#email"), {
								tips : 4
							});
					break;
				case 430:
					layer.tips(regist_get_code_first,
							$("#get_code"), {
								tips : 1
							});
					break;
				case 431:
					layer.tips(regist_code_is_wrong, $("#code"),
							{
								tips : 4
							});
					break;
				case 200:
					layer.msg(regist_regist_success, {
						time : 3000,
						icon : 1
					});
					setTimeout(function() {
						var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
						parent.layer.close(index); //再执行关闭   
					}, 3000);
					parent.$("#username").val($("loginName").val());
					break;
				default:
					break;
				}
			}
		});
	}
	function cutdown() {
		if (time > 0) {
			$("#get_code").val(time);
			time = time - 1;
		} else {
			$("#get_code").removeClass("disabled");
			$("#get_code").addClass("btn-secondary");
			$("#get_code").attr("disabled", false);
			window.clearInterval(t1);
			$("#get_code").val(regist_get_code);
			time = 60;
		}
	}
</script>
</html>