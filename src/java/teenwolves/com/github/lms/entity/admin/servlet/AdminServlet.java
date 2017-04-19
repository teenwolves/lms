/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.user.authentication.UserAuthenticator;
import teenwolves.com.github.lms.entity.user.authentication.exception.AuthenticationException;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.AllUsers;
import teenwolves.com.github.lms.util.Utility;

/**
 *
 * @author Sudarshana Panditha
 */
@WebServlet(name = "AdminServlet", urlPatterns = {"/admin/admins"})
public class AdminServlet extends HttpServlet {
    // Database to access
    private MySQLDatabase database;
    // User authenticator to authenticate user
    private UserAuthenticator authenticator;
    
    // Overriding the init method to setup database and user
    @Override
    public void init() throws ServletException {
        super.init();
        database = new LmsMySQLDatabase();
        authenticator = new UserAuthenticator(database);
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet AdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String message = "";
        String url = null;
        
        // Authenticating the user
        User user = null;
        try {
            user = authenticator.getUser(request);
        } catch (AuthenticationException ex) {
            message = "Please login.";
            url = "/admin/login.jsp";
            Utility.dispatchRequest(getServletContext(), request, response, url, message);
        }
        
        // User authenticated
        String action = request.getParameter("action");
        
        // Setting default action
        if(!Utility.hasPresence(action)){
            action = "view";
        }
        
        // Setting dispatching urls
        if(action.equals("delete")){
            url = "/admin/deleteadmin.jsp";
        }else{
            url = "/admin/admins.jsp";
        }
        
        // Retrieving admins
        try {            
            List<Admin> admins = user.getAdminManager().findAdmins(new AllUsers());
            
            // Set the admins in the request
            request.setAttribute("admins", admins);
        } catch (AdminBehaviourException ex) {
            message = ex.getError().getMessage();
        }
        
        // Dispatching the request, response
        Utility.dispatchRequest(getServletContext(), request, response, url, message);
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
        doGet(request, response);
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
