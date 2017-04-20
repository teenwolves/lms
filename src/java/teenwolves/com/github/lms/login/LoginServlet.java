/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.login;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
import teenwolves.com.github.lms.entity.exceptions.UserException;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.AbstractLecturerRepository;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.lmslecturerrepository.LecturerRepository;
import teenwolves.com.github.lms.entity.student.Student;
import teenwolves.com.github.lms.entity.student.studentrepository.AbstractStudentRepository;
import teenwolves.com.github.lms.entity.student.studentrepository.lmsstudentrepository.StudentRepository;
import teenwolves.com.github.lms.entity.user.userspecification.UserSpecification;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.UserById;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.UserByUsernameAndPassword;
import teenwolves.com.github.lms.entity.userrepository.lmsuserrepository.UserRepository;
import teenwolves.com.github.lms.login.utility.LoginUtility;
import teenwolves.com.github.lms.util.Utility;

/**
 * LoginServlet is a controller class for login validations
 * @author https://github.com/teenwolves
 * @version 1.0
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    // Database to access
    private MySQLDatabase database;
    // AbstractUserRepository to access Database
    private AbstractStudentRepository studentRepository;
    private AbstractLecturerRepository lecturerRepository;
    // User cookie age to one year
    private static final int MAX_AGE = 31536000;
            
    
    // Setting up a Repository
    @Override
    public void init() throws ServletException {
        super.init();
        database = new LmsMySQLDatabase();
        studentRepository = new StudentRepository(database);
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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        // Url to dispatch
        String url = "/home.jsp";
        String message = "";
        
        // local variables to instantiate a User
        User user = null;
        
        // Retrieving form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // General Validations
        if(Utility.hasPresence(username) && Utility.hasPresence(password)){
            username = Utility.inputFormat(username);
            password = Utility.inputFormat(password);

            // specification to get user for matching username and password
            UserSpecification specification = new UserByUsernameAndPassword(username, password);
            System.out.println("Trying to find");
            try {
                // Checking in the Student repository
                user = studentRepository.query(specification).get(0);
                System.out.println("student found.");
            } catch (RepositoryException ex) {
                System.out.println("" + ex.getError().getErrorMessage());
                try {
                    // Checking in the Lecturer repository
                    user = lecturerRepository.query(specification).get(0);
                    System.out.println("lecturer found.");
                } catch (RepositoryException ex1) {
                    System.out.println("No user found");
                    url = "/login.jsp";
                    message = "Please check your username and password.";
                }
            }

            // User found
            // if remember me is checked
            boolean isRememberMeChecked = LoginUtility.isChecked(
                    request.getParameter("rememberme"), "on");

            // if remember me is checked
            if (isRememberMeChecked) {
                // Create cookie for the user
                Cookie userCookie = new Cookie("lmsuser", username);
                userCookie.setMaxAge(MAX_AGE);
                response.addCookie(userCookie);
            } else {
                request.getSession().setAttribute("user", user);
            }
          
        }else{
            url = "/login.jsp";
            message = "Please check your username and password.";
            System.out.println("No presence");
        }
        
        Utility.dispatchRequest(getServletContext(), request, response,
                url, message);
        
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
