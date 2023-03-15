<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" isELIgnored="false"%>
<%@page import="entity.*,java.util.*,java.text.SimpleDateFormat"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<html>
<head>
  <title>Admin page</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" type="text/css"
        href="../../css/style.css" />
</head>

<body>
<div id="wrap">
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

          <br />
        </p>
      </div>
      <div id="topheader">
        <h1 id="title">
          Main function
        </h1>
      </div>
      <div id="navigation">
      </div>
    </div>
    <div id="content">
      <p id="whereami">
      </p>
      <h1>
        Administrator page
      </h1>
      <h2> <a href="<%=request.getContextPath()%>/staffList.do">UserInfo Management</a></h2>
      <h2> <a href="<%=request.getContextPath()%>/carList.do">Car Management</a></h2>
      <h2> <a href="<%=request.getContextPath()%>/OrderList.do">Order Show</a></h2>
      <h2> <a href="<%=request.getContextPath()%>/resetPwd.jsp">ReSetUserPassword</a></h2>
      <h2> <a href="<%=request.getContextPath()%>/commentList.do">OrderCommentList</a></h2>
    </div>
  </div>
  <div id="footer">
    <div id="footer_bg">

    </div>
  </div>
</div>
</body>
</html>