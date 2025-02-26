/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.ShoesDAO;
import dao.UserDAO;
import dto.ShoesDTO;
import dto.UserDTO;
import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
@WebServlet(name = "MainController", urlPatterns = {"/MainController"})
public class MainController extends HttpServlet {

    public static final String LOGIN_PAGE = "login.jsp";
    // hàm xác định xem user đó có tồn tại không?

    public UserDTO getUser(String usName) {
        UserDAO usd = new UserDAO();
        return usd.readByUserName(usName);
    }
    // hàm verify login

    public boolean isValidLogin(String usName, String password) {
        UserDAO usd = new UserDAO();
        UserDTO us = usd.readByUserName(usName);
        System.out.println(us);
        return (us != null && us.getPassword().equalsIgnoreCase(password));
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = LOGIN_PAGE;
        try {
            String action = request.getParameter("action");
            if (action == null) {
                url = "login.jsp";
            } else {
                if (action.equals("login")) {
                    String usName = request.getParameter("UserName");
                    String usPassword = request.getParameter("Password");
                    if (isValidLogin(usName, usPassword)) {
                        url = "search.jsp";
                        UserDTO user = getUser(usName);
                        request.getSession().setAttribute("user", user);
                    } else {
                        request.setAttribute("message", "Incorrect UserName or Password!");
                        url = "login.jsp";
                    }
                } else if (action.equals("logout")) {
                    url = "login.jsp";
                    request.getSession().invalidate();
                } else if (action.equals("search")) {
                    url = "search.jsp";
                    ShoesDAO sDao = new ShoesDAO();
                    String searchName = request.getParameter("searchShoes");
                    //lấy ra danh sách:
                    List<ShoesDTO> shoes = sDao.readByName2(searchName);
                    //trả lại search.jsp để hiển thị ra màn hình:
                    request.setAttribute("shoes", shoes);
                    //in lại tên muốn tìm trên thanh search:
                    request.setAttribute("searchTerm", searchName);
                } else if (action.equals("delete")) {
                    ShoesDAO sDao = new ShoesDAO();
                    sDao.updateQuantityToZero(request.getParameter("id"));
                    //search lại:
                    url = "search.jsp";
                    String searchName = request.getParameter("searchName");
                    List<ShoesDTO> liShoes = sDao.readByName2(searchName);
                    request.setAttribute("shoes", liShoes);
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
