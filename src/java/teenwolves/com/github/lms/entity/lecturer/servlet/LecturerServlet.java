/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.lecturer.servlet;

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
@WebServlet(name = "LecturerServlet", urlPatterns = {"/admin/lecturers"})
public class LecturerServlet extends HttpServlet {
    // Database to access
    private MySQLDatabase database;
    // LecturerRepository to access Lecturer Data
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
            out.println("<title>Servlet LecturerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LecturerServlet at " + request.getContextPath() + "</h1>");
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
        String url = "/admin/lecturers.jsp";
        String message = "";
        String heading = "";
        String fileUrl = "";

        // Authenticating the user
        User user = null;
        try {
            user = authenticator.getUser(request);
        } catch (AuthenticationException ex) {
            url = "/admin/login.jsp";
            message = "Please Login.";
            Utility.dispatchRequest(getServletContext(), request, response, url, message);
        }
        
        // User has been authenticated
        String action = request.getParameter("action");
        
        // Setting default action
        if(!Utility.hasPresence(action)){
            action = "view";
        }
        // Setting dispatching urls
        if(action.equals("delete")){
            fileUrl = "../includes/deletelecturers.jsp";
            heading = "Delete Lecturers";
        }else{
            fileUrl = "../includes/lecturerstable.jsp";
            heading = "All Lecturers";
        }
        
        try {
            List<Lecturer> lecturers = user.getLecturerManager()
                    .findLecturers(new AllUsers());
            // Setting the lecturers as an attribute 
            request.setAttribute("lecturers", lecturers);
        } catch (AdminBehaviourException ex) {
            message = ex.getError().getMessage();
        }
        // Setting data to send
        request.setAttribute("fileUrl", fileUrl);
        request.setAttribute("heading", heading);
        // Dispatching the request
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
