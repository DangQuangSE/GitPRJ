<%-- 
    Document   : search
    Created on : Mar 2, 2025, 9:55:31 PM
    Author     : Dell
--%>

<%@page import="dto.StartUpProjectDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Project Dashboard Page</title>
        <style>
            table {
                border: 2px solid black;
                border-collapse: collapse;
                width: 100%;
            }

            th, td {
                border: 1px solid black;
                padding: 8px;
                text-align: left;
            }
        </style>
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


        <%
            String searchTerm = request.getAttribute("searchTerm") + "";
            searchTerm = searchTerm.equals("null") ? "" : searchTerm;
        %>
        <form action="MainController">
            <input type="hidden" name="action" value="search">
            ProjectName: <input type="text" name="searchName" value="<%=searchTerm%>"> 
            <input type="submit" value="search">
        </form>
        <br>
        <button>
            <a href="projectForm.jsp" style="text-decoration: none">Add New Project</a>
        </button>
        <%
            if (request.getAttribute("projects") != null) {
                List<StartUpProjectDTO> projects = (List<StartUpProjectDTO>) request.getAttribute("projects");
        %>
        <table>
            <thead>
                <tr>
                    <th>Project Name</th>
                    <th>Description</th>
                    <th>Status</th>
                    <th>Estimated Launch</th>
                </tr>
            </thead>
            <tbody>
                <%
                    for (StartUpProjectDTO item : projects) {
                %>
                <tr>
                    <td><%= item.getProjectName()%></td>
                    <td><%= item.getDescription()%></td>
                    <td><%= item.getStatus()%></td>
                    <td><%= item.getEstimated_launch()%></td>
                </tr>
                <%
                    }
                %>
            </tbody>
        </table>
        <%
            }
        } else {
        %>
        <h3 style="color: red">You cannot access this page!!!</h3>
        <%
            }
        %>

    </body>
</html>
