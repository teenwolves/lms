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
import teenwolves.com.github.lms.entity.user.userspecification.UserSpecification;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.entity.userrepository.lmsuserrepository.UserRepository;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;

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
        try{
            userRepository.addUser(student);
        }catch(RepositoryException re){
            throw re;
        }
        
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO student VALUES(");
        query.append(student.getId());
        query.append(", ");
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
        String query = "SELECT * FROM student";
        
        try {
            // Connecting to the database
            database.connect();
            
            // Query the database
            ResultSet rows = database.select(query);
            
            // Creating the student list
            Student student;
            
            while (rows.next()) {                
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
        
        if(students == null){
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        }
        
        return students;
    }
    
}
