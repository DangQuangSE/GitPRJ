<%-- 
    Document   : output2
    Created on : Feb 10, 2025, 2:24:13 PM
    Author     : Dell
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            int n = (int) request.getAttribute("n");
        %>
        <%
            if (n % 2 == 0) {
        %> <%=n%> is Even number<%
                } else {
        %> <%=n%> is Odd number<%
    }
        %>
    </body>
</html>
