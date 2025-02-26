<%-- 
    Document   : search
    Created on : Feb 26, 2025, 1:51:50 AM
    Author     : Dell
--%>

<%@page import="dto.ShoesDTO"%>
<%@page import="java.util.List"%>
<%@page import="dto.UserDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-16WWW">
        <title>Search Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css" integrity="sha512-Evv84Mr4kqVGRNSgIGL/F/aIDqQb7xQ2vcrdIwxfjThSH8CSR7PBEakCr51Ck+w+/U6swU2Im1vVX0SVk9ABhg==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            .book-table {
                width: 100%;
                border-collapse: collapse;
                margin: 25px 0;
                font-size: 14px;
                font-family: Arial, sans-serif;
                box-shadow: 0 0 20px rgba(0, 0, 0, 0.1);
            }

            .book-table thead th {
                background-color: #009879;
                color: #ffffff;
                text-align: left;
                font-weight: bold;
                padding: 12px 15px;
            }

            .book-table tbody tr {
                border-bottom: 1px solid #dddddd;
            }

            .book-table tbody tr:nth-of-type(even) {
                background-color: #f3f3f3;
            }

            .book-table tbody tr:last-of-type {
                border-bottom: 2px solid #009879;
            }

            .book-table tbody td {
                padding: 12px 15px;
            }

            .book-table tbody tr:hover {
                background-color: #f5f5f5;
                transition: 0.3s ease;
            }

            /* Responsive design */
            @media screen and (max-width: 600px) {
                .book-table {
                    font-size: 12px;
                }

                .book-table thead th,
                .book-table tbody td {
                    padding: 8px 10px;
                }
            }
        </style> 
    </head>
    <body>
        <%@include file="header.jsp" %>
        <div style="min-height: 500px; padding: 10px" >
            <%if (session.getAttribute("user") != null) {
                    UserDTO us = (UserDTO) session.getAttribute("user");
            %>
            <h2>Welcome: <%= us.getName()%></h2>
            <form action="MainController">
                <input type="hidden" name="action" value="logout">
                <input type="submit" value="logout">
            </form>
            <br>
            <%
                String searchName = request.getAttribute("searchTerm") + "";
                searchName = searchName.equals("null") ? "" : searchName;
            %>
            <form action="MainController">
                <input type="hidden" name="action" value="search">
                Search Shoes: <input type="text" name="searchShoes" value="<%=searchName%>">
                <input type="submit" value="Search">
            </form>
            <%
                if (request.getAttribute("shoes") != null) {
                    List<ShoesDTO> liShoes = (List<ShoesDTO>) request.getAttribute("shoes");
            %>
            <table class="book-table">
                <thead>
                    <tr>
                        <th>ShoesID</th>
                        <th>ShoesName</th>
                        <th>Trademark</th>
                        <th>Publish Year</th>
                        <th>Quantity</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        for (ShoesDTO item : liShoes) {
                    %>
                    <tr>
                        <td><%=item.getShoesID()%></td>
                        <td><%=item.getShoesName()%></td>
                        <td><%=item.getTrademark()%></td>
                        <td><%=item.getPublishYear()%></td>
                        <td><%=item.getQuantity()%></td>
                        <td><a href="MainController?action=delete&id=<%=item.getShoesID()%>&searchName=<%=searchName%>">
                                <i class="fa-solid fa-trash" style="color: black"></i></a>
                        </td>
                    </tr>
                    <%
                        }
                    %>
                </tbody>
            </table>
            <%
                }
            %>

            <%
            } else {
            %>
            You do not have permission to access this content.
            <%
                }%>

        </div>
        <jsp:include page="footer.jsp"/>
    </body>
</html>
