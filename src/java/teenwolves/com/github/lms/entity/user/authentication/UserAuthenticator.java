/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.user.authentication;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.admin.adminrepository.AbstractAdminRepository;
import teenwolves.com.github.lms.entity.admin.adminrepository.lmsadminrepository.AdminRepository;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.AbstractLecturerRepository;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.lmslecturerrepository.LecturerRepository;
import teenwolves.com.github.lms.entity.student.studentrepository.AbstractStudentRepository;
import teenwolves.com.github.lms.entity.student.studentrepository.lmsstudentrepository.StudentRepository;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.user.authentication.errors.AuthenticationError;
import teenwolves.com.github.lms.entity.user.authentication.exception.AuthenticationException;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.UserByUsername;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.entity.userrepository.lmsuserrepository.UserRepository;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public class UserAuthenticator {
    
    private final MySQLDatabase database;
    private final AbstractStudentRepository studentRepository;
    private final AbstractLecturerRepository lecturerRepository;
    private final AbstractAdminRepository adminRepository;

    public UserAuthenticator(MySQLDatabase database) {
        this.database = database;
        studentRepository = new StudentRepository(database);
        lecturerRepository = new LecturerRepository(database);
        adminRepository = new AdminRepository(database);
    }
    
    public User getUser(HttpServletRequest request) throws AuthenticationException{
        // User to return
        User user = null;
        HttpSession session = request.getSession();
        
        user = (User) session.getAttribute("user");
        if(user == null){
            Cookie userCookie= getCookie("lmsuser",request);
            if(userCookie != null){
                try {
                    // Search in Student
                    user = studentRepository.query(
                            new UserByUsername(userCookie.getValue())).get(0);
                    
                } catch (RepositoryException ex) {
                    try {
                        // Search in Lecturer
                        user = lecturerRepository.query(
                                new UserByUsername(userCookie.getValue())).get(0);
                        
                    } catch (RepositoryException ex1) {
                        try {
                            // Search in Admin
                            user = adminRepository.query(
                                    new UserByUsername(userCookie.getValue())).get(0);
                            
                        } catch (RepositoryException ex2) {
                            throw new AuthenticationException(AuthenticationError.UNIDENTIFIED_USER);
                        }
                    }
                }
            }else{
                throw new AuthenticationException(AuthenticationError.USER_NOT_FOUND);
            }
        }
        
        if(user == null){
            throw new AuthenticationException(AuthenticationError.USER_NOT_FOUND);
        }else{
            session.setAttribute("user", user);
        }
        return user;
    }
    
    // Helper methods
    private Cookie getCookie(String name, HttpServletRequest request){
        Cookie user = null;
        Cookie cookies [] = request.getCookies();
        if(cookies != null){
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)){
                    user = cookie;
                }
            }
        }
        return user;
    }
}
