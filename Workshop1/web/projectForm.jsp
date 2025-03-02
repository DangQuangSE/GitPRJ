<%-- 
    Document   : projectForm
    Created on : Mar 3, 2025, 1:56:43 AM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>PROJECT FORM JSP</title>
    </head>
    <body>
        <form action="MainController" method="post">
            <input type="hidden" name="action" value="create">
            <label for="pjName">Project Name</label>
            <input type="text" name="txtProjectName" id="pjName"><br>
            <label for="pjDes">Description</label>
            <input type="text" name="txtProjectDes" id="pjDes"><br>
            <label for="pjStatus">Status</label>
            <input type="text" name="txtStatus" id="pjStatus"><br>
            <label for="pjLaunchDate">Launch Date</label>
            <input type="date" name="txtLaunchDate" id="pjLaunchDate"><br>
            <input type="submit" value="Save">
            <input type="reset" value="Reset">
        </form>
    </body>
</html>
