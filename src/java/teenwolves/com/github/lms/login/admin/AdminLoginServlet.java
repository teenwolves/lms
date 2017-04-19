/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.login.admin;

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
import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.admin.adminrepository.AbstractAdminRepository;
import teenwolves.com.github.lms.entity.admin.adminrepository.lmsadminrepository.AdminRepository;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.UserById;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.UserByUsernameAndPassword;
import teenwolves.com.github.lms.entity.user.utilities.UserUtilities;
import teenwolves.com.github.lms.entity.userrepository.lmsuserrepository.UserRepository;
import teenwolves.com.github.lms.login.utility.LoginUtility;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.util.Utility;

/**
 *
 * @author Sudarshana Panditha
 */
@WebServlet(name = "AdminLoginServlet", urlPatterns = {"/admin/login"})
public class AdminLoginServlet extends HttpServlet {
    // Repositories to access Admin data
    private AbstractAdminRepository adminRepository;
    private MySQLDatabase database;
    
    //Overiding the init method to set up repositories and databases
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
            out.println("<title>Servlet AdminLoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet AdminLoginServlet at " + request.getContextPath() + "</h1>");
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
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Admin admin = null;
        
        if(Utility.hasPresence(username) && Utility.hasPresence(password)){
            try {
                // Formatting the inputs
                username = Utility.inputFormat(username);
                password = Utility.inputFormat(password);
                
                // Retrieving the admin
                List<Admin> admins = adminRepository.query(new UserByUsernameAndPassword(username, password));
                
                admin = admins.get(0);
                
                // if remember me is checked
                boolean isRememberMeChecked = LoginUtility.isChecked(
                        request.getParameter("rememberme"), "on");
                
                // if remember me is checked
                if(isRememberMeChecked){
                    Cookie user = new Cookie("lmsuser", username);
                    response.addCookie(user);
                }else{
                    request.getSession().setAttribute("user", admin);
                }
                
                
            } catch (RepositoryException ex) {
                System.out.println(ex.getError().getErrorMessage());
                String url = "/admin/login.jsp";
                LoginUtility.forwardError(getServletContext(), request, 
                            response, url, ex.getError());
            }
            
        }else{
            String url = "/admin/login.jsp";
            LoginUtility.forwardError(getServletContext(), request, 
                            response, url, RepositoryError.USER_NOT_FOUND);
        }
        
        // Admin found, redirecting to the admins home page
        response.sendRedirect("home.jsp");
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
