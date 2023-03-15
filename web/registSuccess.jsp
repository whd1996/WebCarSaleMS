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
	font-size: 28px;
}
</style>
</head>
<body>
	<center>
		<h3>Congratulations on your successful registration,3s later jump to the login page!</h3>
	</center>
	<%
		response.setHeader("refresh", "3;url=login.jsp");
	%>
</body>
</html>