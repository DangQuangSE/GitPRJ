
package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "InforStu", urlPatterns = {"/InforStu"})
public class InforStu extends HttpServlet {  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet InforStu</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(request.getParameter("id"));
            out.println(request.getParameter("name"));
            out.println(request.getParameter("gender"));
            out.println("</body>");
            out.println("</html>");
        }
    }
}
