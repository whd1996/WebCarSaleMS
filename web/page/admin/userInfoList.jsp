<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@page import="dao.*,util.*,entity.*,java.util.*,java.text.SimpleDateFormat" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>UserInfo list</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="../../css/style.css"/>
    <style type="text/css">
        .row1 {
            color: blue;
        }

        .row2 {
            color: yellow;
        }
        table {
            border-collapse: separate;
            border-spacing: 50px 0;
        }

        td {
            padding: 10px 0;
        }
    </style>
</head>
<body>
<div id="">
    <div id="top_content">
        <div id="header">
            <div id="rightheader">
                <p><a href="../../logout.jsp"> log out</a></p>
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
                    <a href="#">UserInfoMS</a>
                </h1>
            </div>
            <div id="navigation">

            </div>
        </div>
        <div id="content">
            <p id="whereami">
            </p>
            <h1>
                UserInfo list
            </h1>
            <table>
                <tr>
                   <%-- <th>
                        ID
                    </th>--%>
                    <th>
                        Name
                    </th>
                    <th>
                       gender
                    </th>
                    <th>
                       age
                    </th>
                    <th>
                        contact_number
                    </th>
                    <th>
                        address
                    </th>
                    <th>
                        email
                    </th>
                    <th>
                        IC_Number
                    </th>
                    <th>
                        UserRole
                    </th>
                    <th>
                        operation
                    </th>
                </tr>
                <c:forEach var="e" items="${employees}" varStatus="status">
                    <tr>
                       <%-- <td>
                                ${e.id}
                        </td>--%>
                        <td>
                                ${e.name}
                        </td>
                        <td>
                            ${e.gender}
                        </td>
                        <td>
                                ${e.age}
                        </td>
                        <td>
                                ${e.contactNumber}
                        </td>
                        <td>
                                ${e.address}
                        </td>
                        <td>
                                ${e.email}
                        </td>
                        <td>
                                ${e.icNumber}
                        </td>
                        <td>
                                ${e.userRole}
                        </td>
                        <td>
                            <a href="<%=request.getContextPath()%>/deleteStaff.do?id=${e.id}" onclick="return confirm('Are you sure to delete?')">delete</a>&nbsp;<a
                                href="<%=request.getContextPath()%>/loadStaff.do?id=${e.id}">update</a>
                        </td>
                    </tr>

                </c:forEach>

            </table>
            <div>
                There are ${count} records in total!
                </hr>
                <div>
                    <ul style="display:inline-block">
                        <li style="display:inline-block"><a href="#">«</a></li>
                         ${bar}
                        <li style="display:inline-block"><a href="#">»</a></li>
                    </ul>
                </div>
            </div>
            <p>
                <input type="button" class="button" value="addPerson"
                       onclick="location='<%=request.getContextPath()%>/page/admin/addPerson.jsp'"/>
            </p>
        </div>
    </div>
    <div id="footer">
        <div id="footer_bg">
            <h2><a href="<%=request.getContextPath()%>/page/admin/admin.jsp">return</a></h2>
        </div>
    </div>
</div>
</body>
</html>