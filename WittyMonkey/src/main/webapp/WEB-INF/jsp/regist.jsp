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
#logo {
	width: 150px;
	height: 150px;
}

h2 {
	margin-top: 50px;
	text-align: center;
}

#username, #password {
	margin: 0 auto;
	width: 300px;
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
					<td><input id="username" name="username" type="text"
						placeholder="<fmt:message key='regist.username'/>"
						class="input-text radius" /></td>
				</tr>
				<tr>
					<td><input id="password" name="password" type="text"
						placeholder="<fmt:message key='regist.password'/>"
						class="input-text radius" /></td>
				</tr>
				<tr>
					<td><input id="password" name="repassword" type="text"
						placeholder="<fmt:message key='regist.repassword'/>"
						class="input-text radius" /></td>
				</tr>
				<tr>
					<td><input id="email" name="email" type="text"
						placeholder="<fmt:message key='regist.email'/>"
						class="input-text radius" /></td>
					<td><input type="button" id="get_code"
						class="btn btn-secondary radius"
						value="<fmt:message key='regist.get_code'/>"></td>
				</tr>
				<tr>
					<td><input id="code" name="code" type="text"
						placeholder="<fmt:message key='regist.code'/>"
						class="input-text radius" /></td>
				</tr>
				<tr>
					<td><input type="button" class="btn btn-success radius"
						value="<fmt:message key="regist.registbtn" />" onclick="regist()">
				</tr>
			</table>
		</form>
	</div>
</body>
<script type="text/javascript">
	$("#get_code").click(function() {
		var email = $("#email").val();
		$.ajax({
			type : "GET",
			url : "getValidateCode.do",
			data : {
				"email" : email
			},
			dataType: "text",
			success : function(data) {
				alert(data);
			}
		});
		t1 = window.setInterval("cutdown()",1000);
		$("#get_code").removeClass("btn-secondary");
		$("#get_code").addClass("disabled");
		$("#get_code").attr("disabled","disabled");
	});
	function regist() {
		var myCode = $("#code").val();
		if (code == null || code == "") {
			layer.msg("请先获取验证码", {
				icon : 7,
				time : 2000
			//2秒关闭（如果不配置，默认是3秒）
			}, function() {
				//do something
			});
			return;
		}
		//var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
		//parent.layer.close(index);
		$.ajax({
			type : "POST",
			url : "regist.do",
			data : $("#regist_form").serialize(),
			success : function(data) {
				if (data == "success") {
					var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
					parent.layer.close(index);
				}
			}
		});
	}
	var time = 60;
	function cutdown(){
		if (time > 0){
			$("#get_code").val(time);
			time = time - 1;
		} else{
			$("#get_code").removeClass("disabled");
			$("#get_code").addClass("btn-secondary");
			$("#get_code").attr("disabled","enabled");
			window.clearInterval(t1);
			time = 60;
		}
	}
</script>
</html>