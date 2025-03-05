<%-- 
    Document   : search
    Created on : Mar 2, 2025, 9:55:31 PM
    Author     : Dell
--%>

<%@page import="utils.AuthUtils"%>
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
    <% if (session.getAttribute("user") != null) { 
        UserDTO us = (UserDTO) session.getAttribute("user");
    %>
    <h2 style="color: green">Welcome <%= us.getName() %></h2>
    
    <form action="MainController">
        <input type="hidden" name="action" value="logout">
        <input type="submit" value="Logout">
    </form>
    <br>

    <% 
        String searchTerm = (String) request.getAttribute("searchTerm");
        searchTerm = (searchTerm == null) ? "" : searchTerm;
    %>
    
    <form action="MainController">
        <input type="hidden" name="action" value="search">
        Project Name: <input type="text" name="searchName" value="<%= searchTerm %>">
        <input type="submit" value="Search">
    </form>
    <br>

    <% if (AuthUtils.isFounder(session)) { %>
        <button>
            <a href="projectForm.jsp" style="text-decoration: none">Add New Project</a>
        </button>
    <% } %>

    <% 
        List<StartUpProjectDTO> projects = (List<StartUpProjectDTO>) request.getAttribute("projects");
        if (projects != null) { 
    %>
        <table>
            <thead>
                <tr>
                    <th>Project Name</th>
                    <th>Description</th>
                    <th>Estimated Launch</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
                <% for (StartUpProjectDTO item : projects) { %>
                    <tr>
                        <td><%= item.getProjectName() %></td>
                        <td><%= item.getDescription() %></td>     
                        <td><%= item.getEstimated_launch() %></td>
                        <td>
                            <% if (AuthUtils.isFounder(session)) { %>
                                <form action="MainController" method="post">
                                    <input type="hidden" name="action" value="update">
                                    <input type="hidden" name="projectID" value="<%= item.getProjectId() %>">
                                    <select name="status">
                                        <option value="Ideation" <%= "Ideation".equals(item.getStatus()) ? "selected" : "" %>>Ideation</option>
                                        <option value="Development" <%= "Development".equals(item.getStatus()) ? "selected" : "" %>>Development</option>
                                        <option value="Launch" <%= "Launch".equals(item.getStatus()) ? "selected" : "" %>>Launch</option>
                                        <option value="Scaling" <%= "Scaling".equals(item.getStatus()) ? "selected" : "" %>>Scaling</option>
                                    </select> 
                                    <input type="submit" value="Update">
                                </form>
                            <% } else { %>
                                <%= item.getStatus() %>
                            <% } %>
                        </td>
                    </tr>
                <% } %>
            </tbody>
        </table>
        <br>

        <% 
            String updateMess = (String) request.getAttribute("updateMess");
            Boolean checkSuccess = (Boolean) request.getAttribute("checkSuccess");
            if (updateMess != null && checkSuccess != null) { 
        %>
            <div style="color: <%= checkSuccess ? "green" : "red" %>;">
                <%= updateMess %>
            </div>
        <% } %>
    
    <% } %>

    <% } else { %>
        <h3 style="color: red">You cannot access this page!!!</h3>
    <% } %>

</body>
</html>
