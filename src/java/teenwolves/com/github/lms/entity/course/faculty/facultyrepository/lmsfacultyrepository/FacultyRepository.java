/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.course.faculty.facultyrepository.lmsfacultyrepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.course.faculty.Faculty;
import teenwolves.com.github.lms.entity.course.faculty.facultyrepository.AbstractFacultyRepository;
import teenwolves.com.github.lms.entity.course.faculty.facultyrepository.FacultySpecification;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.repository.RepositoryUtility;

/**
 *
 * @author Sudarshana Panditha
 */
public class FacultyRepository implements AbstractFacultyRepository{
    private MySQLDatabase database;

    public FacultyRepository() {
        database = new LmsMySQLDatabase();
    }

    @Override
    public void addFaculty(Faculty faculty) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO faculty(name) VALUES('");
        query.append(faculty.getName());
        query.append("')");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Faculty is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateFaculty(Faculty faculty) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteFaculty(Faculty faculty) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM faculty WHERE id=");
        query.append(faculty.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Faculty is not deleted.");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public List<Faculty> query(FacultySpecification specification) throws RepositoryException {
        List<Faculty> faculties = null;
        String query = "SELECT * FROM batch";
        
        try {
            // Connecting to the database
            database.connect();
            
            // Retrieving data
            ResultSet rows = database.select(query);
            
            //Creating the hall list
            Faculty faculty = null;
            while(rows.next()){
                faculty = new Faculty();
                faculty.setId(rows.getInt("id"));
                faculty.setName(rows.getString("name"));
                
                if(specification.specified(faculty)){
                    if(faculties == null){
                        faculties = new ArrayList<>();
                    }
                    faculties.add(faculty);
                }
            }
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            throw new RepositoryException(RepositoryError.BATCHES_NOT_FOUND);
        }
        
        if(faculties == null){
            throw new RepositoryException(RepositoryError.BATCHES_NOT_FOUND);
        }
        return faculties;
    }
    
    
    
    
}
