<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" isELIgnored="false" %>
<%@page import="java.util.*,java.text.SimpleDateFormat" %>
<html>
<head>
    <title>addStaff</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css"
          href="../../css/style.css"/>
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
                    <br/>
                </p>
            </div>
            <div id="topheader">
                <h1 id="title">
                    EmployeesMS
                </h1>
            </div>
            <div id="navigation">
            </div>
        </div>
        <div id="content">
            <p id="whereami">
            </p>
            <h1>
                AddStaff
            </h1>
            <form action="/AddStaffServlet" method="post">
                <table cellpadding="0" cellspacing="0" border="0"
                       class="form_table">
                    <tr>
                        <td valign="middle" align="right">
                            Name:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="name" required="required" value=""/>
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
                            <input type="text" class="inputgri" name="age"  required="required" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            contact_number:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="contactNumber" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            address:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="address" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            email:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="email" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            IC_Number:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="icNumber" value=""/>
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