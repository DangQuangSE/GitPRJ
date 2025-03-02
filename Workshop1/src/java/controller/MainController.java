/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dao.UserDAO;
import dto.StartUpProject;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";

    public UserDTO getUser(String usName) {
        UserDAO usDao = new UserDAO();
        return usDao.readByUSName(usName);
    }

    public boolean verifyUser(String usName, String password) {
        UserDTO us = getUser(usName);
        return us != null && us.getPassword().equals(password);
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = LOGIN_PAGE;
            } else {
                if (action.equals("login")) {
                    String usName = request.getParameter("txtUsername");
                    String pw = request.getParameter("txtPassword");
                    if (verifyUser(usName, pw)) {
                        url = "dashboard.jsp";
                        request.getSession().setAttribute("user", getUser(usName));
                    } else {
                        url = "login.jsp";
                        request.setAttribute("message", "Incorrect UserName or Password!");
                    }
                } else if (action.equals("logout")) {
                    url = "login.jsp";
                    request.getSession().invalidate();
                } else if (action.equals("search")) {
                    url = "dashboard.jsp";
                    ProjectDAO pjDao = new ProjectDAO();
                    String searchName = request.getParameter("searchName");
                    List<StartUpProject> projects = pjDao.searchByName(searchName);
                    request.setAttribute("projects", projects);
                    request.setAttribute("searchTerm", searchName);
                }
            }
        } catch (Exception e) {
            log("Error in MainController: " + e.toString());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
