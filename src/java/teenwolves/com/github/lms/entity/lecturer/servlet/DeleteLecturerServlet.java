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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.AbstractLecturerRepository;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.lmslecturerrepository.LecturerRepository;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.UserById;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
@WebServlet(name = "DeleteLecturerServlet", urlPatterns = {"/admin/deletelecturer"})
public class DeleteLecturerServlet extends HttpServlet {
    // Databse to access
    private MySQLDatabase database;
    // LecturerRepository to access lecturer data
    private AbstractLecturerRepository lecturerRepository;
    
    // Overriding the init method to setup databse and repositories

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
            out.println("<title>Servlet DeleteLecturerServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeleteLecturerServlet at " + request.getContextPath() + "</h1>");
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
        // Message to send if an error occurs
        String message = "";
        String url = "/admin/lecturers";
        RepositoryError error = null;
        
        // Deleted count of lecturers
        int deleted = 0;
        
        // Retrieving the selected lecturers
        String deletingLecturers [] = request.getParameterValues("deletinglecturers");
        Lecturer lecturer = null;
        int id;
        // Checking if any lecturer is selected
        if(deletingLecturers != null){
            for (String deletingLecturer : deletingLecturers) {
                id = Integer.parseInt(deletingLecturer);
                try {
                    // Get the matching lecturer for the id
                    lecturer = lecturerRepository.query(new UserById(id)).get(0);
                    
                    // Delete the lecturer
                    lecturerRepository.deleteLecturer(lecturer);
                    
                    // Incrementing the count
                    deleted++;
                } catch (RepositoryException ex) {
                    error = ex.getError();
                }
            }
        }else{
            // No lecturer is selected
            message = "Please select the lecturers to delete.";
            url = "/admin/lecturers?action=delete";
        }
        
        // Setting the deleted lecturers message
        String deletionMessage = "";
        if (deleted > 0) {
            if (deleted == 1) {
                message += "1 lecturer is deleted.";
            } else {
                message += deleted + " lecturers are deleted.";
            }
        }
        
        if(error != null){
            message = "Error occurred. " + deletionMessage;
            url = "/admin/lecturers?action=delete";
        }
        
        // Setting the message
        request.setAttribute("message", message);
        
        // Dispatching the request
        getServletContext().getRequestDispatcher(url).forward(request, response);
        
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
