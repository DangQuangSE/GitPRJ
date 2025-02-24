<%-- 
    Document   : search
    Created on : Feb 13, 2025, 1:42:26 PM
    Author     : tungi
--%>

<%@page import="dto.BookDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div style="min-height: 500px; padding: 20px">



            <%
                if (session.getAttribute("user") != null) {

                    UserDTO user = (UserDTO) session.getAttribute("user");
            %>
            Welcome <b> <%=user.getFullName()%> </b>
            <br/>

            <form action="MainController">
                <input type="hidden" name="action" value="logout"/>
                <input type="submit" value="Logout"/>
            </form>

            <hr/>
            <form action="MainController">
                <input type="hidden" name="action" value="search"/>
                Search Books <input type="text" name="searchTerm" />
                <input type="submit" value="Search"/>
            </form>
            <br/>
            <br/>
            <%
                if (request.getAttribute("books") != null) {
                    List<BookDTO> books = (List<BookDTO>) request.getAttribute("books");
            %>
            <table border="1">
                <tr>
                    <td>BookID</td>
                    <td>Title</td>
                    <td>Author</td>
                    <td>PublishYear</td>
                    <td>Price</td>
                    <td>Quantity</td>
                     <th>Action</th>
                </tr>
                <%
                    for (BookDTO b : books) {
                %>
                <tr>
                    <td><%=b.getBookID()%></td>
                    <td><%=b.getTitle()%></td>
                    <td><%=b.getAuthor()%></td>
                    <td><%=b.getPublishYear()%></td>
                    <td><%=b.getPrice()%></td>
                    <td><%=b.getQuantity()%></td>
                    <td><a href="MainController?action=delete&id=<%=b.getBookID()%>&searchTerm=<%=searchTerm%>">
                            <i class="fa-solid fa-trash" style="color: black"></i>                                
                            </a></td>
                </tr>
                <%
                    }
                %>
            </table>
            <%
                }
            %>
            <% } else { %>
            You do not have permission to access this content.
            <% }%>

        </div>
        <%@include file="footer.jsp" %>
    </body>
</html>