<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<body>
	<h2>Hello World!</h2>
	<table border="1" >
	<tr>
		<th>编号</th>
		<th>名称</th>
		<th>城市</th>
		<th>地址</th>
		<th>电话</th>
		<th>传真</th>
		<th>邮箱</th>
		<th>星级</th>
		<th>开业时间</th>
		<th>加入时间</th>
		<th>更新时间</th>
	</tr>
	<tr>
		<td>${hotel.id }</td>
		<td>${hotel.name }</td>
		<td>${hotel.city }</td>
		<td>${hotel.place }</td>
		<td>${hotel.tel }</td>
		<td>${hotel.fax }</td>
		<td>${hotel.email }</td>
		<td>${hotel.star }</td>
		<td>${hotel.openDate }</td>
		<td>${hotel.addDate }</td>
		<td>${hotel.updateDate }</td>
	</tr>
	</table>
</body>
</html>
