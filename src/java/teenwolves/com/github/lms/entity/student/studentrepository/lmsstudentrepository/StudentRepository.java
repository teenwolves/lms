/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.student.studentrepository.lmsstudentrepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.entity.exceptions.UserException;
import teenwolves.com.github.lms.entity.student.Student;
import teenwolves.com.github.lms.entity.student.studentrepository.AbstractStudentRepository;
import teenwolves.com.github.lms.entity.student.studentspecification.StudentSpecification;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.user.userspecification.UserSpecification;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.AllUsers;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.entity.userrepository.lmsuserrepository.UserRepository;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.repository.RepositoryUtility;

/**
 * <code>StudentRepository</code> class is an implementation of the 
 * <code>AbstractStudentRepository</code> interface
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see AbstractUserRepository
 */
public class StudentRepository implements AbstractStudentRepository{
    // The databse that StudentRepository uses to query
    private MySQLDatabase database;
    private AbstractUserRepository userRepository;

    public StudentRepository(MySQLDatabase database) {
        this.database = database;
        userRepository = new UserRepository(database);
    }
    
    @Override
    public void addStudent(Student student) throws RepositoryException {
        //User object to get the added user
        List<User> users = null;
        User user = null;
        // Catching any exception thrown when adding to the user table
        try{
            userRepository.addUser(student);
            users = userRepository.query(new AllUsers());
            user = getUserForStudent(student, users);
            
            student.setAttributes(user);
        }catch(RepositoryException re){
            throw re;
        } catch (UserException ex) {
            RepositoryError error = RepositoryError.TECHNICAL_ERROR;
            error.setErrorMessage(ex.getError().getMessage());
            throw new RepositoryException(error);
        }
        
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO student VALUES(");
        query.append(student.getId());
        query.append(", ");
        query.append(student.getCourseId());
        query.append(", '");
        query.append(student.getBatchId());
        query.append("')");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Student is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateStudent(Student student) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteStudent(Student student) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Student> query(UserSpecification specification) throws RepositoryException {
           List<Student> students = null;
        List<User> users = null;
        String query = "SELECT * FROM student";
        
        if(specification.getClass() == StudentSpecification.class){
            students = specifyStudents(query, specification);
        }else{
            try{
                users = userRepository.query(specification);
                students = getUserStudentIntersection(users, 
                        specifyStudents(query, new AllUsers()));
            }catch(RepositoryException re){
                throw re;
            }
        }
        
        if(students.isEmpty()){
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        }
        return students;
        
    }
    
    private List<Student> specifyStudents(String query, UserSpecification specification) throws RepositoryException{
        List<Student> students = null;
        try {
            // Connecting to the database
            database.connect();
            
            // Retrieving data
            ResultSet rows = database.select(query);
            
            // Creating the admin list
            Student student = null;
            while(rows.next()){
                student = new Student();
                student.setId(rows.getInt("id"));
                
                if(specification.specified(student)){
                    if(students == null){
                        students = new ArrayList<>();
                    }
                    students.add(student);
                }
            }
            
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        } catch (UserException ex) {
            RepositoryError error = RepositoryError.TECHNICAL_ERROR;
            error.setErrorMessage(ex.getError().getMessage());
            throw new RepositoryException(error);
        }
        return students;
    }
    
    private List<Student> getUserStudentIntersection(List<User> users, List<Student> students) throws RepositoryException{
        List<Student> outputStudents = null;
        if(students == null){
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        }else{
            outputStudents = new ArrayList<>();
            for(Student student: students){
                for(User user: users){
                    if(user.equals(student)){
                        try {
                            student.setAttributes(user);
                            outputStudents.add(student);
                        } catch (UserException ex) {
                            RepositoryError error = RepositoryError.TECHNICAL_ERROR;
                            error.setErrorMessage(ex.getError().getMessage());
                            throw new RepositoryException(error);
                        }
                    }
                }
            }
        }
        return outputStudents;
    }
    
    private User getUserForStudent(Student student,List<User> users) throws RepositoryException{
        User outputUser = null;
        for (User user : users) {
            if(user.equals(student)){
                outputUser = user;
                break;
            }
        }
        if(outputUser == null){
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        }
        return outputUser;
    }
    
}
