/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.signup;

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
import teenwolves.com.github.lms.entity.batch.Batch;
import teenwolves.com.github.lms.entity.batch.batchrepository.AbstractBatchRepository;
import teenwolves.com.github.lms.entity.batch.batchrepository.lmsbatchrepository.AllBatches;
import teenwolves.com.github.lms.entity.batch.batchrepository.lmsbatchrepository.BatchRepository;
import teenwolves.com.github.lms.entity.course.Course;
import teenwolves.com.github.lms.entity.course.courserepository.AbstractCourseRepository;
import teenwolves.com.github.lms.entity.course.courserepository.lmscourserepository.AllCourses;
import teenwolves.com.github.lms.entity.course.courserepository.lmscourserepository.CourseRepository;
import teenwolves.com.github.lms.entity.exceptions.UserException;
import teenwolves.com.github.lms.entity.student.Student;
import teenwolves.com.github.lms.entity.student.studentrepository.AbstractStudentRepository;
import teenwolves.com.github.lms.entity.student.studentrepository.lmsstudentrepository.StudentRepository;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.util.Utility;

/**
 *
 * @author Sudarshana Panditha
 */
@WebServlet(name = "SignupServlet", urlPatterns = {"/signup"})
public class SignupServlet extends HttpServlet {
    // Database to access
    private MySQLDatabase database;
    // Repository to access course data
    private AbstractCourseRepository courseRepository;
    // Repository to access batch data
    private AbstractBatchRepository batchRepository;
    // Student Repository to save students
    private AbstractStudentRepository studentRepository;
    
    // Overriding the init method to setup database and repositories
    @Override
    public void init() throws ServletException {
        super.init();
        database = new LmsMySQLDatabase();
        courseRepository = new CourseRepository(database);
        batchRepository = new BatchRepository(database);
        studentRepository = new StudentRepository(database);
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
            out.println("<title>Servlet SignupServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SignupServlet at " + request.getContextPath() + "</h1>");
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
        // Messages to send
        String message = "";
        String url = "";
        // User action
        String action = request.getParameter("action");
        
        if (!Utility.hasPresence(action)) {
            action = "signup";
        }

        if (action.equals("signup")) {
            // local variables
            List<Course> courses = null;
            List<Batch> batches = null;

            // Finding all the courses available
            try {
                courses = courseRepository.query(new AllCourses());
            } catch (RepositoryException ex) {
                Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

            // Finding all the batches
            try {
                batches = batchRepository.query(new AllBatches());
            } catch (RepositoryException ex) {
                Logger.getLogger(SignupServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            // Setting courses and batches in the request
            request.setAttribute("courses", courses);
            request.setAttribute("batches", batches);
            
            // URL to dispatch
            url = "/signup.jsp";
            
            // Dispatching the request
            Utility.dispatchRequest(getServletContext(), request, response, url, message);
            
        } else if (action.equals("signme")) {
            
            String name = request.getParameter("name");
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String email = request.getParameter("email");
            
            String batch = request.getParameter("batch");
            String course = request.getParameter("course");
            
            boolean isDataHasPresence = Utility.hasPresence(name) 
                    && Utility.hasPresence(username) 
                    && Utility.hasPresence(password) 
                    && Utility.hasPresence(email)
                    && Utility.hasPresence(batch)
                    && Utility.hasPresence(course);
            url = "/signup?action=signup";
            if(isDataHasPresence){
                Student student = new Student();
                name = Utility.inputFormat(name);
                username = Utility.inputFormat(username);
                password = Utility.inputFormat(password);
                email = Utility.inputFormat(email);
                batch = Utility.inputFormat(batch);
                course = Utility.inputFormat(course);
                
                int courseId = Integer.parseInt(course);
                // Setting student attributes
                try {
                    student.setName(name);
                    student.setUsername(username);
                    student.setPassword(password);
                    student.setEmail(email);
                    student.setBatchId(batch);
                    student.setCourseId(courseId);
                    
                    // Add the Student to the database
                    studentRepository.addStudent(student);
                    
                    //Setting the url
                    url = "/home.jsp";
                } catch (UserException ex) {
                    message = ex.getError().getMessage();
                    
                } catch (RepositoryException ex) {
                    message = ex.getError().getErrorMessage();
                }
            }else{
                message = "Please check your inputs.";
            }
            System.out.println("message: " + message);
            System.out.println("url: " + url);
            // Dispatching the request
            Utility.dispatchRequest(getServletContext(), request, response, url, message);
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
