<%-- 
    Document   : search
    Created on : Mar 2, 2025, 9:55:31 PM
    Author     : Dell
--%>

<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Dashboard Page</title>
    </head>
    <body>
        <%
            if (session.getAttribute("user") != null) {
                UserDTO us = (UserDTO) session.getAttribute("user");
        %>
        <h2 style="color: green">Welcome <%= us.getName()%></h2>
        <form action="MainController">
            <input type="hidden" name="action" value="logout">
            <input type="submit" value="Logout">
        </form>
        <br>
        <form action="MainController">
            <input type="hidden" name="action" value="search">
            ProjectName: <input type="text" name="searchName"> 
            <input type="submit" value="search">
        </form>

        <%
        } else {
        %>
        <h3 style="color: red">You cannot access this page!!!</h3>
        <%
            }
        %>

    </body>
</html>
