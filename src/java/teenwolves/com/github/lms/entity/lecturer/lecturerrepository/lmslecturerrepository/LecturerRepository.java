/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.lecturer.lecturerrepository.lmslecturerrepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.entity.exceptions.UserException;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.AbstractLecturerRepository;
import teenwolves.com.github.lms.entity.lecturer.lecturerspecification.LecturerSpecification;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.user.userspecification.UserSpecification;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.AllUsers;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.entity.userrepository.lmsuserrepository.UserRepository;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.repository.RepositoryUtility;

/**
 * <code>LecturerRepository</code> class is an implementation of the 
 * AbstractLecturerRepository interface
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see AbstractLecturerRepository
 */
public class LecturerRepository implements AbstractLecturerRepository{
    // Database to query
    private MySQLDatabase database;
    private AbstractUserRepository userRepository;

    public LecturerRepository(MySQLDatabase database) {
        this.database = database;
        this.userRepository = new UserRepository(database);
    }
    
    @Override
    public void addLecturer(Lecturer lecturer) throws RepositoryException{
        //User object to get the added user
        List<User> users = null;
        User user = null;
        // Catching any exception thrown when adding to the user table
        try{
            userRepository.addUser(lecturer);
            users = userRepository.query(new AllUsers());
            user = getUserForLecturer(lecturer, users);
            
            lecturer.setAttributes(user);
        }catch(RepositoryException re){
            throw re;
        } catch (UserException ex) {
            RepositoryError error = RepositoryError.TECHNICAL_ERROR;
            error.setErrorMessage(ex.getError().getMessage());
            throw new RepositoryException(error);
        }
        // Adding to the lecturer table
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO lecturer(");
        query.append("id) VALUES(");
        query.append(lecturer.getId());
        query.append(")");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Lecturer is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateLecturer(Lecturer lecturer) throws RepositoryException{
        userRepository.updateUser(lecturer);
    }

    @Override
    public void deleteLecturer(Lecturer lecturer) throws RepositoryException{
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM lecturer WHERE id=");
        query.append(lecturer.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Lecturer is not deleted");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
        
        userRepository.deleteUser(lecturer);
    }

    @Override
    public List<Lecturer> query(UserSpecification specification) throws RepositoryException {
        List<Lecturer> lecturers = null;
        List<User> users = null;
        String query = "SELECT * FROM lecturer";
        
        if(specification.getClass() == LecturerSpecification.class){
            lecturers = specifyLecturers(query, specification);
        }else{
            try{
                users = userRepository.query(specification);
                lecturers = getUserLecturerIntersection(users, 
                        specifyLecturers(query, new AllUsers()));
            }catch(RepositoryException re){
                throw re;
            }
        }
        
        if(lecturers.isEmpty()){
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        }
        return lecturers;
        
    }
    
    private List<Lecturer> specifyLecturers(String query, UserSpecification specification) throws RepositoryException{
        List<Lecturer> lecturers = null;
        try {
            // Connecting to the database
            database.connect();
            
            // Retrieving data
            ResultSet rows = database.select(query);
            
            // Creating the admin list
            Lecturer lecturer = null;
            while(rows.next()){
                lecturer = new Lecturer();
                lecturer.setId(rows.getInt("id"));
                
                if(specification.specified(lecturer)){
                    if(lecturers == null){
                        lecturers = new ArrayList<>();
                    }
                    lecturers.add(lecturer);
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
        return lecturers;
    }
    
    private List<Lecturer> getUserLecturerIntersection(List<User> users, List<Lecturer> lecturers) throws RepositoryException{
        List<Lecturer> outputLecturers = null;
        if(lecturers == null){
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        }else{
            outputLecturers = new ArrayList<>();
            for(Lecturer lecturer: lecturers){
                for(User user: users){
                    if(user.equals(lecturer)){
                        try {
                            lecturer.setAttributes(user);
                            outputLecturers.add(lecturer);
                        } catch (UserException ex) {
                            RepositoryError error = RepositoryError.TECHNICAL_ERROR;
                            error.setErrorMessage(ex.getError().getMessage());
                            throw new RepositoryException(error);
                        }
                    }
                }
            }
        }
        return outputLecturers;
    }
    
    private User getUserForLecturer(Lecturer lecturer,List<User> users) throws RepositoryException{
        User outputUser = null;
        for (User user : users) {
            if(user.equals(lecturer)){
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
