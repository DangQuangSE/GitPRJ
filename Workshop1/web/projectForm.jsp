<%-- 
    Document   : projectForm
    Created on : Mar 3, 2025, 1:56:43 AM
    Author     : Dell
--%>

<%@page import="utils.AuthUtils"%>
<%@page import="dto.StartUpProjectDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROJECT FORM JSP</title>
    </head>
    <body>
        <%
            if (AuthUtils.isLoggedIn(session)) {
                if (AuthUtils.isFounder(session)) {
        %>
        <%
            StartUpProjectDTO project = new StartUpProjectDTO();
            try {
                project = (StartUpProjectDTO) request.getAttribute("project");
            } catch (Exception e) {
                project = new StartUpProjectDTO();
            }
            if (project == null) {
                project = new StartUpProjectDTO();
            }
            String errorName = request.getAttribute("errorName") + "";
            errorName = errorName.equals("null") ? "" : errorName;
            String errorDes = request.getAttribute("errorDes") + "";
            errorDes = errorDes.equals("null") ? "" : errorDes;
            String errorStatus = request.getAttribute("errorStatus") + "";
            errorStatus = errorStatus.equals("null") ? "" : errorStatus;
            String errorDate = request.getAttribute("errorDate") + "";
            errorDate = errorDate.equals("null") ? "" : errorDate;
        %>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="create">
            <label for="pjName">Project Name</label>
            <input type="text" name="txtProjectName" id="pjName" value="<%=project.getProjectName()%>"><br>
            <%if (!errorName.isEmpty()) {
            %>
            <div style="color: red"><%=errorName%></div>
            <%
                }%>

            <label for="pjDes">Description</label>
            <input type="text" name="txtProjectDes" id="pjDes" value="<%= project.getDescription()%>"><br>
            <%if (!errorDes.isEmpty()) {
            %>
            <div style="color: red"><%=errorDes%></div>
            <%
                }
            %>
            <label for="pjStatus">Status</label>
            <select name="txtStatus" id="pjStatus">
                <option value="" <%= (project.getStatus().isEmpty()) ? "selected" : ""%>>-- Select Status --</option>
                <option value="Ideation" <%="Ideation".equals(project.getStatus()) ? "selected" : ""%>>Ideation</option>
                <option value="Development"<%="Development".equals(project.getStatus()) ? "selected" : ""%>>Development</option>
                <option value="Launch"<%="Launch".equals(project.getStatus()) ? "selected" : ""%>>Launch</option>
                <option value="Scaling"<%="Scaling".equals(project.getStatus()) ? "selected" : ""%>>Scaling</option>
            </select> <br>
            <%if (!errorStatus.isEmpty()) {
            %>
            <div style="color: red"><%=errorStatus%></div>
            <%
                }
            %>
            <label for="pjLaunchDate">Launch Date</label>
            <input type="date" name="txtLaunchDate" id="pjLaunchDate"><br>
            <%if (!errorDate.isEmpty()) {
            %>
            <div style="color: red"><%=errorDate%></div>
            <%
                }
            %>
            <input type="submit" value="Save">
            <input type="reset" value="Reset">
        </form>
        <br>
        <%
        } else {
        %>
        You do not have permission to access this content!
        <%
            }
        } else {
        %>
        Please login to access this page!
        <a href="login.jsp">Go to Login</a>
        <%
            }
        %>
        <form action="MainController">
            <input type="hidden" name="action" value="back">
            <input type="submit" value="Back">
        </form>
    </body>
</html>
