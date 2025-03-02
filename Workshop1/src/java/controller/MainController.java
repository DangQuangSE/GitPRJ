/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dao.UserDAO;
import dto.StartUpProjectDTO;
import dto.UserDTO;
import java.io.IOException;
import java.time.LocalDate;
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
    public ProjectDAO pjDao = new ProjectDAO();

    public UserDTO getUser(String usName) {
        UserDAO usDao = new UserDAO();
        return usDao.readByUSName(usName);
    }

    public boolean verifyUser(String usName, String password) {
        UserDTO us = getUser(usName);
        return us != null && us.getPassword().equals(password);
    }

    protected void search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("searchName");

        if (searchName == null) {
            searchName = "";
        }
        List<StartUpProjectDTO> projects = pjDao.searchByName(searchName);
        request.setAttribute("projects", projects);
        request.setAttribute("searchTerm", searchName);
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
                        search(request, response);
                    } else {
                        url = "login.jsp";
                        request.setAttribute("message", "Incorrect UserName or Password!");
                    }
                } else if (action.equals("logout")) {
                    url = "login.jsp";
                    request.getSession().invalidate();
                } else if (action.equals("search")) {
                    url = "dashboard.jsp";
                    search(request, response);
                } else if (action.equals("create")) {
                    try {
                        String pjName = request.getParameter("txtProjectName");
                        String pjDes = request.getParameter("txtProjectDes");
                        String pjStatus = request.getParameter("txtStatus");
                        LocalDate launch_date = LocalDate.parse(request.getParameter("txtLaunchDate"));
                        StartUpProjectDTO pj = new StartUpProjectDTO(pjName, pjDes, pjStatus, launch_date);
                        pjDao.create(pj);
                        url = "dashboard.jsp";
                        search(request, response);
                    } catch (Exception e) {
                        log("Error: " + e.toString());
                    }
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
