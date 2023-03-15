<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@page import="dao.*,util.*,entity.*,java.util.*,java.text.SimpleDateFormat" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Car List</title>
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
                <p>${sessionScope.get("user").username} <a href="../../logout.jsp">  log out</a></p>
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
                    <a href="#">CarMS</a>
                </h1>
            </div>
            <div id="navigation">

            </div>
        </div>
        <div id="content">
            <p id="whereami">
            </p>
            <h1>
                Car List
            </h1>
            <table>
                <tr>
                   <%-- <th>
                        ID
                    </th>--%>
                    <th>
                        car_name
                    </th>
                    <th>
                        age_limit
                    </th>
                    <th>
                        type
                    </th>
                    <th>
                        quality
                    </th>
                    <th>
                        price
                    </th>
                    <th>
                        status
                    </th>
                    <th>
                        salesmenName
                    </th>
                    <th>
                        operation
                    </th>
                </tr>
                <c:forEach var="e" items="${cars}" varStatus="status">
                    <tr>
                        <td>
                                ${e.carName}
                        </td>
                        <td>
                            ${e.ageLimit}
                        </td>
                        <td>
                                ${e.type}
                        </td>
                        <td>
                                ${e.quality}
                        </td>
                        <td>
                               ${e.price} RM
                        </td>
                        <td>
                                ${e.status}
                        </td>
                        <td>
                                ${e.salesmenName}
                        </td>
                        <td>
                            <a href="<%=request.getContextPath()%>/deleteCar.do?id=${e.id}" onclick="return confirm('Are you sure to delete?')">delete</a>&nbsp;<a
                                href="<%=request.getContextPath()%>/loadCar.do?id=${e.id}">update</a>
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
                <input type="button" class="button" value="addCarInfo"
                       onclick="location='<%=request.getContextPath()%>/addCar.do'"/>
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