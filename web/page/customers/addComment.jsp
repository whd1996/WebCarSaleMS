<%@page pageEncoding="utf-8" contentType="text/html;charset=utf-8" isELIgnored="false" %>
<%@page import="java.util.*,java.text.SimpleDateFormat" %>
<html>
<head>
    <title>addComment</title>
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
                    CommentMS
                </h1>
            </div>
            <div id="navigation">
            </div>
        </div>
        <div id="content">
            <p id="whereami">
            </p>
            <h1>
                addComment
            </h1>
            <form action="/AddCommentServlet" method="post">
                <table cellpadding="0" cellspacing="0" border="0"
                       class="form_table">
                    <tr>
                        <td valign="middle" align="right">
                            OrderId:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="oId"  readonly="readonly" required="required" value="${oId}"/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            content:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="content"  required="required" value=""/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            name:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="name" readonly="readonly" required="required" value="${name}"/>
                        </td>
                    </tr>
                    <tr>
                        <td valign="middle" align="right">
                            commentDate:
                        </td>
                        <td valign="middle" align="left">
                            <input type="text" class="inputgri" name="commentDate" readonly="readonly" required="required" value="${commentDate}"/>
                        </td>
                    </tr>
                </table>
                <p>
                    <input type="submit" class="button" value="submit" />
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