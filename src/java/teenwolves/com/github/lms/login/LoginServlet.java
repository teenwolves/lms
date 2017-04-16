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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.AbstractLecturerRepository;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.lmslecturerrepository.LecturerRepository;
import teenwolves.com.github.lms.entity.student.Student;
import teenwolves.com.github.lms.entity.student.studentrepository.AbstractStudentRepository;
import teenwolves.com.github.lms.entity.student.studentrepository.lmsstudentrepository.StudentRepository;
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
    // AbstractUserRepository to access Database
    private AbstractUserRepository userRepository;
    private AbstractStudentRepository studentRepository;
    private AbstractLecturerRepository lecturerRepository;
            
    
    // Setting up a Repository
    @Override
    public void init() throws ServletException {
        super.init();
        MySQLDatabase database = new LmsMySQLDatabase();
        userRepository = new UserRepository(database);
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
        // local variables to instantiate a User
        User user = null;
        Student student = null;
        Lecturer lecturer = null;
        
        // Retrieving form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        // General Validations
        if(Utility.hasPresence(username) && Utility.hasPresence(password)){
            try {
                // Formatting the inputs
                username = Utility.inputFormat(username);
                password = Utility.inputFormat(password);
                
                // Retrieving the user who matches username and password
                List<User> users = userRepository.query(
                        new UserByUsernameAndPassword(username, password));
                
                // User who matches the username and password
                user = users.get(0);
                
                /*// Setting the user to the Session object
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                
                // Redirecting to the home page
                response.sendRedirect("home.jsp");*/
                
            } catch (RepositoryException ex) {
                String url = "/login.jsp";
                LoginUtility.forwardError(getServletContext(), request, 
                            response, url, ex.getError());
                
            }
            
            try {
                // Checking if the user is a student
                List<Student> students = studentRepository.query(
                        new UserById(user.getId()));
                
                student = students.get(0);
                // Setting User attributes
                student.setName(user.getName());
                student.setEmail(user.getEmail());
                student.setUsername(user.getUsername());
                student.setPassword(user.getPassword());
                
                // Setting the user to the Session object
                HttpSession session = request.getSession();
                session.setAttribute("user", student);
                
                // Redirecting to the home page
                response.sendRedirect("home.jsp");
                
            } catch (RepositoryException ex) {
                
                if(ex.getError() == RepositoryError.USER_NOT_FOUND){
                    try {
                        // Checking if the user is a lecturer
                        List<Lecturer> lecturers = lecturerRepository.query(
                                new UserById(user.getId()));
                        
                        lecturer = lecturers.get(0);
                        
                        // Setting User attributes
                        lecturer.setAttributes(user);
                        
                        // Setting the user to the Session object
                        HttpSession session = request.getSession();
                        session.setAttribute("user", lecturer);
                        
                        // Redirecting to the home page
                        response.sendRedirect("home.jsp");
                        
                    } catch (RepositoryException ex1) {
                        String url = "/login.jsp";
                        LoginUtility.forwardError(getServletContext(), request, 
                            response, url, ex.getError());
                    }
                    
                }else{
                    String url = "/login.jsp";
                    LoginUtility.forwardError(getServletContext(), request, 
                            response, url, ex.getError());
                }
                
            }
          
        }else{
            String url = "/login.jsp";
            LoginUtility.forwardError(getServletContext(), request, 
                            response, url, RepositoryError.USER_NOT_FOUND);
        }
        
        
        
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
