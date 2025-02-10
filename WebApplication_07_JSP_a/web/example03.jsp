<%-- 
    Document   : example03
    Created on : Feb 10, 2025, 1:26:19 PM
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
        <%! int a = 100;%>
        <%
            if (a % 2 == 0) {
        %>
        a is Even
        <%
        } else { %>
        a is Odd
        <%
            }
        %>
    </body>
</html>
