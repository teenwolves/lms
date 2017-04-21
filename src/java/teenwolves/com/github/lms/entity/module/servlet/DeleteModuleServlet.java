/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.module.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourError;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.module.Module;
import teenwolves.com.github.lms.entity.module.modulespecification.implementations.ModuleById;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.user.authentication.UserAuthenticator;
import teenwolves.com.github.lms.entity.user.authentication.exception.AuthenticationException;
import teenwolves.com.github.lms.util.Utility;

/**
 *
 * @author Sudarshana Panditha
 */
@WebServlet(name = "DeleteModuleServlet", urlPatterns = {"/admin/deletemodule"})
public class DeleteModuleServlet extends HttpServlet {
    // Database to access
    private MySQLDatabase database;
    // UserAuthenticattor to authenticate user
    private UserAuthenticator authenticator;
    
    // Overriding the init method to setup database and repositories
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
            out.println("<title>Servlet DeleteModuleServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteModuleServlet at " + request.getContextPath() + "</h1>");
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
        // Message to display
        String message = "";
        String url = "/admin/modules?action=view";
        
        // Authenticating the USer
        User user = null;
        try {
            user = authenticator.getUser(request);
        } catch (AuthenticationException ex) {
            message = "Please login.";
            url = "/admin/login.jsp";
            Utility.dispatchRequest(getServletContext(), request, response, url, message);
        }
        
        // Deleted count of modules
        int deleted = 0;
        AdminBehaviourError error = null;
        
        // Retrieving the selected modules
        String deletingModules [] = request.getParameterValues("deletingmodules");
        Module module = null;
        int id;
        // Checking if any modules is selected
        if(deletingModules != null){
            for (String deletingModule : deletingModules) {
                id = Integer.parseInt(deletingModule);
                try {
                    // Get the matching modules for the id
                    module = user.getModuleManager()
                            .findModules(new ModuleById(id)).get(0);
                    
                    // Delete the module
                    user.getModuleManager().deleteModule(module);
                    
                    // Incrementing the count
                    deleted++;
                } catch (AdminBehaviourException ex) {
                    error = ex.getError();
                }
            }
        }else{
            // No module is selected
            message = "Please select the modules to delete.";
            url = "/admin/modules?action=delete";
        }
        
        // Setting the deleted modules message
        String deletionMessage = "";
        if (deleted > 0) {
            if (deleted == 1) {
                message += "1 module is deleted.";
            } else {
                message += deleted + " modules are deleted.";
            }
        }
        
        if(error != null){
            message = "Error occurred. " + deletionMessage;
            url = "/admin/modules?action=delete";
        }
        
        Utility.dispatchRequest(getServletContext(), request, response, url, message);
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
