/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourError;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.user.authentication.UserAuthenticator;
import teenwolves.com.github.lms.entity.user.authentication.exception.AuthenticationException;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.AllUsers;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.UserById;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.util.Utility;

/**
 *
 * @author Sudarshana Panditha
 */
@WebServlet(name = "DeleteAdminServlet", urlPatterns = {"/admin/deleteadmin"})
public class DeleteAdminServlet extends HttpServlet {
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
            out.println("<title>Servlet DeleteAdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteAdminServlet at " + request.getContextPath() + "</h1>");
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
        doPost(request, response);
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
        String message = "";
        String url = "/admin/admins";
        AdminBehaviourError error = null;
        
        // Authenticating the user
        User user = null;
        try {
            user = authenticator.getUser(request);
        } catch (AuthenticationException ex) {
            message = "Please login.";
            url = "/admin/login.jsp";
            Utility.dispatchRequest(getServletContext(), request, response, url, message);
        }
        
        // User is authenticated
        
        // Deleted count of admins
        int deleted = 0;
        
        String deletingAdmins [] = request.getParameterValues("deletingadmins");
        Admin admin = null;
        int id;
        // Checking if any admin is selected
        if(deletingAdmins != null){
            for (String deletingLecturer : deletingAdmins) {
                id = Integer.parseInt(deletingLecturer);
                try {
                    // Get the matching admin for the id
                    admin = user.getAdminManager().findAdmins(new UserById(id)).get(0);
                    
                    // Delete the admin
                    user.getAdminManager().deleteAdmin(admin);
                    // Incrementing the count
                    deleted++;
                } catch (AdminBehaviourException ex) {
                    error = ex.getError();
                }
            }
        }else{
            // No lecturer is selected
            message = "Please select the admins to delete.";
            url = "/admin/admins?action=delete";
        }
        
        // Setting the deleted admin message
        String deletionMessage = "";
        if (deleted > 0) {
            if (deleted == 1) {
                message += "1 admin is deleted.";
            } else {
                message += deleted + " admins are deleted.";
            }
        }
        
        if(error != null){
            message = "Error occurred. " + deletionMessage;
            url = "/admin/admins?action=delete";
        }
        
        // Setting the message
        request.setAttribute("message", message);
        
        // Dispatching the request
        getServletContext().getRequestDispatcher(url).forward(request, response);
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
