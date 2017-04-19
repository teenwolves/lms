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
import teenwolves.com.github.lms.entity.admin.adminrepository.AbstractAdminRepository;
import teenwolves.com.github.lms.entity.admin.adminrepository.lmsadminrepository.AdminRepository;
import teenwolves.com.github.lms.entity.admin.utilities.AdminUtilities;
import teenwolves.com.github.lms.entity.exceptions.UserException;
import teenwolves.com.github.lms.entity.user.utilities.UserUtilities;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.util.Utility;

/**
 *
 * @author Sudarshana Panditha
 */
@WebServlet(name = "AddAdminServlet", urlPatterns = {"/admin/addadmin"})
public class AddAdminServlet extends HttpServlet {
    // Database to access
    private MySQLDatabase database;
    // AdminRepository to access the database
    private AbstractAdminRepository adminRepository;
    
    // Overriding the init method to setup database and repositories

    @Override
    public void init() throws ServletException {
        super.init();
        database = new LmsMySQLDatabase();
        adminRepository = new AdminRepository(database);
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
            out.println("<title>Servlet AddAdminServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddAdminServlet at " + request.getContextPath() + "</h1>");
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
        String url = "/admin/addadmin.jsp";
        
        // Admin to add
        Admin admin = null;

        // Getting the request parameter values
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        boolean isAdminManager = Boolean.parseBoolean(
                request.getParameter("adminmanager"));
        boolean isLecturerManager = Boolean.parseBoolean(
                request.getParameter("lecturermanager"));
        boolean isModuleManager = Boolean.parseBoolean(
                request.getParameter("modulemanager"));
        boolean isScheduleManager = Boolean.parseBoolean(
                request.getParameter("schedulemanager"));
        
        if(Utility.hasPresence(name) && Utility.hasPresence(email)){
            // General formatting
            name = Utility.inputFormat(name);
            email = Utility.inputFormat(email);
            
            // Generating a Username
            if(!Utility.hasPresence(username)){
                username = UserUtilities.generateUsername(name);
            }
            // Generating a password
            if(!Utility.hasPresence(password)){
                password = UserUtilities.generatePassword(name);
            }
            
            // Setting Admin instance variables
            try {
                admin = new Admin();
                admin.setName(name);
                admin.setEmail(email);
                if(UserUtilities.isUserNameUnique(username)){
                    admin.setUsername(username);
                }
                admin.setPassword(password);
                admin.setAdminManager(
                        AdminUtilities.getAdminManager(isAdminManager));
                admin.setLecturerManager(
                        AdminUtilities.getLecturerManager(isLecturerManager));
                admin.setModuleManager(
                        AdminUtilities.getModuleManager(isModuleManager));
                admin.setScheduleManager(
                        AdminUtilities.getScheduleManager(isScheduleManager));
                
                // Adding the Admin to the database
                adminRepository.addAdmin(admin);
                
                message = "Admin is successfully added";
                
            } catch (UserException ex) {
                message = ex.getError().getMessage();
            } catch (RepositoryException ex) {
                message = ex.getError().getErrorMessage();
            }
            
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/admin/addadmin.jsp").forward(request, response);
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
