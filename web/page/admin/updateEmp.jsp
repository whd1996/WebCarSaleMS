<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" isELIgnored="false"%>
<%@page import="entity.*,java.util.*,java.text.SimpleDateFormat"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
	<head>
		<title>UpdateUserInfo</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css"
			href="../../css/style.css" />
	</head>

	<body>
		<div id="wrap">
			<div id="top_content">
					<div id="header">
						<div id="rightheader">
							<p>
								<%
									Date date = new Date();
									SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
									out.println(fmt.format(date));
							 	%>	
							
								<br />
							</p>
						</div>
						<div id="topheader">
							<h1 id="title">
								UserInfoMS
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						Modify UserInfo information:
					</h1>
					<form action="modifyStaff.do?id=${e.id}" method="post">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									ID:
								</td>
								<td valign="middle" align="left" name="id">
									${e.id}
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									Name:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="name" value="${e.name}"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									gender:
								</td>
								<td valign="middle" align="left">
									<input type="radio" class="inputgri" name="gender" value="Male" checked="checked"/>Male
									<input type="radio" class="inputgri" name="gender" value="FeMale"/>FeMale
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									age:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="age" value="${e.age}"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									contact_number:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="contactNumber" value="${e.contactNumber}"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									address:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="address" value="${e.address}"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									email:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="email" value="${e.email}"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									IC_Number:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="icNumber" value="${e.icNumber}"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									UserRole:
								</td>
								<td valign="middle" align="left">
									<input type="radio" class="inputgri" name="userRole" value="2"/>ManagingStaff
									<input type="radio" name="userRole" value="1">SalesMan
									<input type="radio" name="userRole" value="0" checked="checked">Customers
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="confirm" />
						</p>
					</form>
				</div>
			</div>
			<div id="footer">
				<div id="footer_bg">

				</div>
			</div>
		</div>
	</body>
</html>