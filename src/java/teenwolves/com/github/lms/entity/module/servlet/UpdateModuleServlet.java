/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.module.servlet;

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
@WebServlet(name = "UpdateModuleServlet", urlPatterns = {"/admin/updatemodule"})
public class UpdateModuleServlet extends HttpServlet {
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
            out.println("<title>Servlet UpdateModuleServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet UpdateModuleServlet at " + request.getContextPath() + "</h1>");
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
        String url = "/admin/addmodule.jsp";;
        
        // Authenticating the USer
        User user = null;
        try {
            user = authenticator.getUser(request);
        } catch (AuthenticationException ex) {
            message = "Please login.";
            url = "/admin/modules?action=view";
            Utility.dispatchRequest(getServletContext(), request, response, url, message);
        }
        
        // User authenticated
        
        String mid = request.getParameter("id");
        String lecturer = request.getParameter("lecturer");
        
        if(Utility.hasPresence(mid) && Utility.hasPresence(lecturer)){
            int id = Integer.parseInt(mid);
            int lecturerId = Integer.parseInt(lecturer);
            
            try {
                // Find the module to update
                Module module = user.getModuleManager()
                        .findModules(new ModuleById(id)).get(0);
                // Update the module
                module.setLecturerId(lecturerId);
                
                // Update the module in the database
                user.getModuleManager().updateModule(module);
                
                message = "Update Successful.";
            } catch (AdminBehaviourException ex) {
                message = ex.getError().getMessage();
                url = "/admin/modules?action=update";
            }
        }else{
            message = "Please select the module update again.";
            url = "/admin/modules?action=update";
        }
        
        // Dispatching the request and response
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
