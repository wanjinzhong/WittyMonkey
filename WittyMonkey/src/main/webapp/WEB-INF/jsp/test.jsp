<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ include file="common/taglib.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
身份证号：${id } <br/>
<c:if test="${card eq null }">非法身份证</c:if>
<c:if test="${card ne null }">
省：${card.province }<br/>
市：${card.city }<br/>
区：${card.area }<br/>
生日：<fmt:formatDate value="${card.birthday }" pattern="yyyy年MM月dd日"/><br/>
性别：<c:if test="${card.sex eq 'M'}">男
</c:if><c:if test="${card.sex eq 'F'}">女</c:if><br/>
</c:if>
</body>
</html>