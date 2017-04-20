/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.servlet;

import java.io.IOException;
import java.io.PrintWriter;
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
import teenwolves.com.github.lms.entity.admin.utilities.AdminUtilities;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.user.authentication.UserAuthenticator;
import teenwolves.com.github.lms.entity.user.authentication.exception.AuthenticationException;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.UserById;
import teenwolves.com.github.lms.util.Utility;

/**
 *
 * @author Sudarshana Panditha
 */
@WebServlet(name = "UpdateAdminServlet", urlPatterns = {"/admin/updateadmin"})
public class UpdateAdminServlet extends HttpServlet {
    // Database to access
    private MySQLDatabase database;
    // User authenticator object to authenticate user
    private UserAuthenticator authenticator;
    
    // Overriding the init method

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
            out.println("<title>Servlet UpdateAdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateAdminServlet at " + request.getContextPath() + "</h1>");
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
        String url = "/admin/admins?action=view";
        User user = null;
        
        // Authenticating the User
        try {
            user = authenticator.getUser(request);
        } catch (AuthenticationException ex) {
            message = "Please Login.";
            url = "/admin/login.jsp";
            Utility.dispatchRequest(getServletContext(), 
                    request, response, url, message);
        }
        
        // User is authenticated
        String uid = request.getParameter("id");
        boolean isAdminManager = Boolean.parseBoolean(
                request.getParameter("adminmanager"));
        boolean isLecturerManager = Boolean.parseBoolean(
                request.getParameter("lecturermanager"));
        boolean isModuleManager = Boolean.parseBoolean(
                request.getParameter("modulemanager"));
        boolean isScheduleManager = Boolean.parseBoolean(
                request.getParameter("schedulemanager"));
        
        Admin admin = null;
        
        if(Utility.hasPresence(uid)){
            try {
                // Get the id of the user
                int id = Integer.parseInt(uid);
                admin = user.getAdminManager()
                        .findAdmins(new UserById(id)).get(0);
                admin.setAdminManager(
                        AdminUtilities.getAdminManager(isAdminManager));
                admin.setLecturerManager(
                        AdminUtilities.getLecturerManager(isLecturerManager));
                admin.setModuleManager(
                        AdminUtilities.getModuleManager(isModuleManager));
                admin.setScheduleManager(
                        AdminUtilities.getScheduleManager(isScheduleManager));
                
                // Update the admin
                user.getAdminManager().updateAdmin(admin);
                message = "Admin is updated.";
                
            } catch (NumberFormatException ex) {
                message = "Please select the Admin to update.";
                url = "/admin/admins?action=update";
            }catch( AdminBehaviourException ex){
                message = ex.getError().getMessage();
                url = "/admin/admins?action=update";
            }
        }else{
            message = "Please select the Admin to update.";
            url = "/admin/admins?action=update";
        }
        
        // Setting the message
        request.setAttribute("message", message);
        // Dispatching the request
        Utility.dispatchRequest(getServletContext(), 
                request, response, url, message);
        
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
