<%@page pageEncoding="utf-8" contentType="text/html; charset=utf-8" %>
<%@page import="dao.*,util.*,entity.*,java.util.*,java.text.SimpleDateFormat" isELIgnored="false" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
    <title>Comment List</title>
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
                    <a href="#">Comment List</a>
                </h1>
            </div>
            <div id="navigation">

            </div>
        </div>
        <div id="content">
            <p id="whereami">
            </p>
            <h1>
                Show Comment List
            </h1>
            <table>
                <tr>
                    <th>
                        orderID
                    </th>
                    <th>
                        content
                    </th>
                    <th>
                        name
                    </th>
                    <th>
                        commentDate
                    </th>
                    <th>
                        operation
                    </th>
                </tr>
                <c:forEach var="e" items="${list}" varStatus="status">
                    <tr>
                        <td>
                                ${e.oId}
                        </td>
                        <td>
                                ${e.content}
                        </td>
                        <td>
                                ${e.name}
                        </td>
                        <td>
                                ${e.commentDate}
                        </td>
                        <td>
                            <a href="<%=request.getContextPath()%>/deleteComment.do?id=${e.id}&name=${e.name}" onclick="return confirm('Are you sure to delete this?')">delete</a>&nbsp;<a

                        </td>
                    </tr>

                </c:forEach>

            </table>

        </div>
    </div>


    <div id="footer">
        <div id="footer_bg">
            <h2><a href="<%=request.getContextPath()%>/page/${path}">Return</a></h2>
        </div>
    </div>
</div>
</body>
</html>