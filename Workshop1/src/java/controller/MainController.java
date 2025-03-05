/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ProjectDAO;
import dto.StartUpProjectDTO;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import utils.AuthUtils;

@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    private static final String LOGIN_PAGE = "login.jsp";
    public ProjectDAO pjDao = new ProjectDAO();

    private String search(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            url = url = "dashboard.jsp";
            String searchName = request.getParameter("searchName");

            if (searchName == null) {
                searchName = "";
            }
            List<StartUpProjectDTO> projects = pjDao.searchByName(searchName);
            request.setAttribute("projects", projects);
            request.setAttribute("searchTerm", searchName);
        }
        return url;
    }

    private String login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        String usName = request.getParameter("txtUsername");
        String pw = request.getParameter("txtPassword");
        if (AuthUtils.verifyUser(usName, pw)) {
            url = "dashboard.jsp";
            request.getSession().setAttribute("user", AuthUtils.getUser(usName));
            search(request, response);
        } else {
            url = "login.jsp";
            request.setAttribute("message", "Incorrect UserName or Password!");
        }
        return url;
    }

    private String logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isLoggedIn(session)) {
            url = "login.jsp";
            request.getSession().invalidate();
        }
        return url;
    }

    private String update(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isFounder(session)) {
            int pjId = Integer.parseInt(request.getParameter("projectID"));
            String newStatus = request.getParameter("status");
            StartUpProjectDTO pj = pjDao.readById(pjId);
            if (!pj.getStatus().equals(newStatus)) {
                boolean checkSuccess = false;
                if (pjDao.updateStatus(pjId, newStatus)) {
                    checkSuccess = true;
                    request.setAttribute("updateMess", "Update success!");
                    request.setAttribute("checkSuccess", checkSuccess);
                } else {
                    request.setAttribute("checkSuccess", checkSuccess);
                    request.setAttribute("updateMess", "Update fail!");
                }
            }
            url = "dashboard.jsp";
            search(request, response);
        }

        return url;
    }

    private String back(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isFounder(session)) {
            url = "dashboard.jsp";
            search(request, response);
        }
        return url;
    }

    private String create(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url = LOGIN_PAGE;
        HttpSession session = request.getSession();
        if (AuthUtils.isFounder(session)) {
            boolean checkError = false;
            try {
                String pjName = request.getParameter("txtProjectName");
                String pjDes = request.getParameter("txtProjectDes");
                String pjStatus = request.getParameter("txtStatus");
                LocalDate launch_date = null;
                try {
                    String date = request.getParameter("txtLaunchDate");
                    if (date == null || date.trim().isEmpty()) {
                        checkError = true;
                        request.setAttribute("errorDate", "Launch date must be selected!");
                    } else {
                        launch_date = LocalDate.parse(date);
                        if (launch_date.isAfter(LocalDate.now())) {
                            checkError = true;
                            request.setAttribute("errorDate", "Launch date cannot be in the future!");
                        }
                    }
                } catch (Exception e) {
                    checkError = true;
                    request.setAttribute("errorDate", "Invalid date format!");
                }

                if (pjName == null || pjName.trim().isEmpty()) {
                    checkError = true;
                    request.setAttribute("errorName", "This field is required!");
                }
                if (pjDes == null || pjDes.trim().isEmpty()) {
                    checkError = true;
                    request.setAttribute("errorDes", "This field is required!");
                }
                if (pjStatus.isEmpty() || pjStatus == null) {
                    checkError = true;
                    request.setAttribute("errorStatus", "Status must be selected!");
                }
                StartUpProjectDTO newPj = new StartUpProjectDTO(pjName, pjDes, pjStatus, launch_date);
                if (!checkError) {
                    pjDao.create(newPj);
                    url = "dashboard.jsp";
                    search(request, response);
                } else {
                    url = "projectForm.jsp";
                    request.setAttribute("project", newPj);
                }

            } catch (Exception e) {
                log("Error: " + e.toString());
            }
        }

        return url;
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
                    url = login(request, response);
                } else if (action.equals("logout")) {
                    url = logout(request, response);
                } else if (action.equals("search")) {
                    url = search(request, response);
                } else if (action.equals("create")) {
                    url = create(request, response);
                } else if (action.equals("update")) {
                    url = update(request, response);
                } else if (action.equals("back")) {
                    url = back(request, response);
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
