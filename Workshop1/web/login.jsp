<%-- 
    Document   : login
    Created on : Mar 2, 2025, 9:18:00 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>LOGIN Page</title>
    </head>
    <body>

        <form action="MainController" method="post">
            <input type="hidden" name="action" value="login">
            <label for="usName">UserName</label> 
            <input type="text" name="txtUsername" id="usName"><br>
            <label for="password">Password</label> 
            <input type="password" name="txtPassword" id="password"><br>
            <input type="submit" value="Submit">          
        </form>
        <%
            String mess = request.getAttribute("message") + "";
            mess = mess.equals("null") ? "" : mess;
        %>
        <h3 style="color: red"><%=mess%></h3>
    </body>
</html>
