/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.module.servlet;

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
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.AbstractLecturerRepository;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.lmslecturerrepository.LecturerRepository;
import teenwolves.com.github.lms.entity.module.Module;
import teenwolves.com.github.lms.entity.module.modulespecification.implementations.AllModules;
import teenwolves.com.github.lms.entity.module.modulespecification.implementations.ModuleById;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.user.authentication.UserAuthenticator;
import teenwolves.com.github.lms.entity.user.authentication.exception.AuthenticationException;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.AllUsers;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.util.Utility;

/**
 *
 * @author Sudarshana Panditha
 */
@WebServlet(name = "ModuleServlet", urlPatterns = {"/admin/modules"})
public class ModuleServlet extends HttpServlet {
    // Database to access
    private MySQLDatabase database;
    // UserAuthenticattor to authenticate user
    private UserAuthenticator authenticator;
    // Lecturer repository to get lecturer details
    private AbstractLecturerRepository lecturerRepository;
    // Overriding the init method to setup database and repositories

    @Override
    public void init() throws ServletException {
        super.init();
        database = new LmsMySQLDatabase();
        authenticator = new UserAuthenticator(database);
        lecturerRepository = new LecturerRepository(database);
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
            out.println("<title>Servlet ModuleServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ModuleServlet at " + request.getContextPath() + "</h1>");
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
        // Message to display
        String message = "";
        String url = "/admin/modules.jsp";;
        String fileurl = "";
        String heading = "";
        
        // Authenticating the USer
        User user = null;
        try {
            user = authenticator.getUser(request);
        } catch (AuthenticationException ex) {
            message = "Please login.";
            url = "/admin/login.jsp";
            Utility.dispatchRequest(getServletContext(), request, response, url, message);
        }
        
        // User is authenticated
        String action = request.getParameter("action");
        String mid = request.getParameter("id");
        
        Module module = null;
        if(Utility.hasPresence(action)){
            if(action.equals("update") && Utility.hasPresence(mid)){
                int id = Integer.parseInt(mid);
                try {
                    // Find the module for the id
                    module = user.getModuleManager()
                            .findModules(new ModuleById(id)).get(0);
                    
                    // Get the lecturers
                    List<Lecturer> lecturers = null;
                    try {
                        lecturers = lecturerRepository.query(new AllUsers());
                    } catch (RepositoryException ex) {
                        message = "There are no lecturers.";
                    }
                    
                    // Setting file url to include and the heading
                    fileurl = "../includes/updatemoduleform.jsp";
                    heading = "Update Module";
                    
                    // Set attributes to dispatch
                    request.setAttribute("updatingModule", module);
                    request.setAttribute("lecturers", lecturers);
                    request.setAttribute("selectedId", module.getLecturerId());
                    request.setAttribute("fileUrl", fileurl);
                    request.setAttribute("heading", heading);
                    // Dispatch the request
                    Utility.dispatchRequest(getServletContext(), request, response, url, message);
                } catch (AdminBehaviourException ex) {
                    message = ex.getError().getMessage();
                    action = "update";
                }
            }
        }else{
            // default action
            action = "view";
        }
        
        // Determining the correct file to include
        if(action.equals("delete")){
            fileurl = "../includes/deletemodules.jsp";
            heading = "Delete Modules";
        }else if(action.equals("update")){
            fileurl = "../includes/selectmodules.jsp";
            heading = "Update Modules";
        }else{
            fileurl = "../includes/modulestable.jsp";
            heading = "All Modules";
        }
        
        try {
            List<Module> modules = user.getModuleManager()
                    .findModules(new AllModules());
            // Setting the queried modules
            request.setAttribute("modules", modules);
            
        } catch (AdminBehaviourException ex) {
            message = ex.getError().getMessage();
        }
        
        // Setting attributes
        request.setAttribute("fileUrl", fileurl);
        request.setAttribute("heading", heading);
        
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
