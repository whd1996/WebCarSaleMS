<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" isELIgnored="false"%>
<%@page import="java.text.SimpleDateFormat,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>ResetUserPwd</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" type="text/css" href="css/style.css" />
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
								<a href="#">ResetUserPwd</a>
							</h1>
						</div>
						<div id="navigation">
						</div>
					</div>
				<div id="content">
					<p id="whereami">
					</p>
					<h1>
					</h1>
					<form action="toResetPwd.do" method="get">
						<table cellpadding="0" cellspacing="0" border="0"
							class="form_table">
							<tr>
								<td valign="middle" align="right">
									username:
								</td>
								<td valign="middle" align="left">
									<input type="text" class="inputgri" name="username" />
								</td>
								<td style="color: red">
									<%
										if(request.getAttribute("username")!=null)
											out.println(request.getAttribute("username"));
										if(request.getAttribute("result") != null) 
											out.println(request.getAttribute("result"));
									 %>
								
								</td>
							</tr>
							<tr>
								<td style="color: red">
									<% 
										if(request.getAttribute("name")!=null) 
											out.println(request.getAttribute("name"));
									 %>
								
								</td>
							</tr>
							<tr>
								<td valign="middle" align="right">
									password:
								</td>
								<td valign="middle" align="left">
									<input type="text" readonly="readonly" value="123456" class="inputgri" name="pwd" />
								</td>
								<td style="color: red">
									<% 
										if(request.getAttribute("password")!=null)
										out.println(request.getAttribute("password"));
									 %>
								</td>
							</tr>
						</table>
						<p>
							<input type="submit" class="button" value="Confirm &raquo;" />
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