<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>successful registration</title>
<style>
h3 {
	color: pink;
	font-size: 16px;
}
</style>
</head>
<body>
	<center>
		<h3>Congratulations on your successful operation!</h3>
	</center>
	<%
		response.setHeader("refresh", "1;url=page/admin/admin.jsp");
	%>
</body>
</html>