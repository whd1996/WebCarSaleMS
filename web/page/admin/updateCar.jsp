<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" isELIgnored="false"%>
<%@page import="entity.*,java.util.*,java.text.SimpleDateFormat"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
	<head>
		<title>UpdateCarInfo</title>
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
								CarInfoMS
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
						Modify CarInfo information:
					</h1>
					<form action="modifyCar.do?id=${e.id}" method="post">
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
									carName:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="carName" value="${e.carName}"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									ageLimit:
								</td>
								<td valign="middle" align="left">
									<input type="number" class="inputgri" name="ageLimit" required="required" value="${e.ageLimit}"/>
								</td>
							</tr>

							<tr>
								<td valign="middle" align="right">
									type:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="type" value="${e.type}"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									quality:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="quality" value="${e.quality}"/>
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									price:
								</td>
								<td valign="middle" align="left">
									<input type="number" class="inputgri" name="price" value="${e.price}"/>
								</td>
							</tr>

							<tr>
								<td valign="middle" align="right">
									status:
								</td>
								<td valign="middle" align="left">
									<input type="radio" class="inputgri" name="status" value="available"/>available
									<input type="radio" name="status" value="booked">booked
									<input type="radio" name="status" value="paid">paid
									<input type="radio" name="status" checked="checked" value="cancel">cancel
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									salesmenName:
								</td>

								<td valign="middle" align="left">
									<input type="text" class="inputgri" id="demoCombo" required="required" list="salesmenName"
										   name="salesmenName" value=""/>
									<c:forEach var="name" items="${salesmenNameList}" varStatus="status">
									<datalist id="salesmenName">　　
										<option>${name}</option>
										</c:forEach>
									</datalist>
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