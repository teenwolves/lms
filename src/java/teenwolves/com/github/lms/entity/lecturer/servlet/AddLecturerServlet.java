/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.lecturer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.admin.adminrepository.AbstractAdminRepository;
import teenwolves.com.github.lms.entity.admin.adminrepository.lmsadminrepository.AdminRepository;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.AbstractLecturerRepository;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.lmslecturerrepository.LecturerRepository;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.UserByUsername;
import teenwolves.com.github.lms.login.utility.LoginUtility;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.util.Utility;

/**
 *
 * @author Sudarshana Panditha
 */
@WebServlet(name = "AddLecturerServlet", urlPatterns = {"/admin/addlecturer"})
public class AddLecturerServlet extends HttpServlet {
    // Lecturer repository to access lecturer data
    private AbstractLecturerRepository lecturerRepository;
    // Database to access data from
    private MySQLDatabase database;

    @Override
    public void init() throws ServletException {
        super.init();
        database = new LmsMySQLDatabase();
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
            out.println("<title>Servlet AddLecturerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AddLecturerServlet at " + request.getContextPath() + "</h1>");
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
        String message = null;
        
        // Getting the request parameter values
        String name = request.getParameter("name");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        if(Utility.hasPresence(name) && Utility.hasPresence(email)){
            name = Utility.inputFormat(name);
            email = Utility.inputFormat(email);
            
            // Setting default values for username
            if(!Utility.hasPresence(username)){
                username = name.replaceAll(" ", "");
            }else{
                username = Utility.inputFormat(username);
            }
            
            // Setting default values for password
            if(!Utility.hasPresence(password)){
                password = name.replaceAll(" ", "");
            }else{
                password = Utility.inputFormat(password);
            }
            
            Lecturer lecturer = new Lecturer();
            lecturer.setName(name);
            lecturer.setEmail(email);
            lecturer.setUsername(username);
            lecturer.setPassword(password);
            
            try {
                lecturerRepository.addLecturer(lecturer);
                // Setting a message saying that the lecturer is added
                message = "Lecturer is added successfully.";
                
            } catch (RepositoryException ex) {
                message = ex.getError().getErrorMessage();
            }
            
        }else{
            // invalid data
            message = "Please check you input data.";
        }
        request.setAttribute("message", message);
        getServletContext().getRequestDispatcher("/admin/AddLecturer.jsp").forward(request, response);
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
