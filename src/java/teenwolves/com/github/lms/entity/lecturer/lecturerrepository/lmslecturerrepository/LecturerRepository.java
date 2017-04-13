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
import java.util.logging.Level;
import java.util.logging.Logger;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.AbstractLecturerRepository;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.LecturerSpecification;
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

    public LecturerRepository(MySQLDatabase database) {
        this.database = database;
    }
    
    @Override
    public void addLecturer(Lecturer lecturer) throws RepositoryException{
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO lecturer(");
        query.append("id, lecturerid)");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Lecturer is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateLecturer(Lecturer lecturer) throws RepositoryException{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteLecturer(Lecturer lecturer) throws RepositoryException{
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM lecturer WHERE id=");
        query.append(lecturer.getId());
        query.append(" AND lecturerid=");
        query.append(lecturer.getLecturerId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Lecturer is not deleted");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public List<Lecturer> query(LecturerSpecification specification) throws RepositoryException {
        List<Lecturer> lecturers = null;
        String query = "SELECT * FROM lecturer";
        
        try {
            // Connecting to the database
            database.connect();
            
            // Querying the database
            ResultSet rows = database.select(query);
            
            // Creating the list of lecturers
            Lecturer lecturer = null;
            
            while (rows.next()) {                
                lecturer = new Lecturer();
                lecturer.setId(rows.getInt("id"));
                lecturer.setLecturerId(rows.getString("lecturerid"));
                
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
        }
        
        // Throwing an Exception if no users are not found.
        if(lecturers == null){
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        }
        return lecturers;
        
    }
    
}
