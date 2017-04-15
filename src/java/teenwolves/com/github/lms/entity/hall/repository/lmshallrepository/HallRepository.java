/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.hall.repository.lmshallrepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.entity.hall.Hall;
import teenwolves.com.github.lms.entity.hall.halls.Halls;
import teenwolves.com.github.lms.entity.hall.repository.AbstractHallRepository;
import teenwolves.com.github.lms.entity.hall.repository.HallSpecification;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.repository.RepositoryUtility;

/**
 *
 * @author Sudarshana Panditha
 */
public class HallRepository implements AbstractHallRepository{

    private MySQLDatabase database;

    public HallRepository(MySQLDatabase database) {
        this.database = database;
    }
    
    @Override
    public void addHall(Hall hall) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO hall(");
        query.append("hallcode, halltype) VALUES('");
        query.append(hall.getHallCode());
        query.append("','");
        query.append(hall.getHallType().toString());
        query.append("')");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Hall is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateHall(Hall hall) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE hall SET ");
        query.append("hallcode = '");
        query.append(hall.getHallCode());
        query.append("', halltype = '");
        query.append(hall.getHallType().toString());
        query.append("' WHERE id = ");
        query.append(hall.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Hall is not updated.");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void deleteHall(Hall hall) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM hall WHERE id=");
        query.append(hall.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Hall is not deleted.");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public List<Hall> query(HallSpecification specification) throws RepositoryException {
        List<Hall> halls = null;
        String query = "SELECT * FROM hall";
        
        try {
            // Connecting to the database
            database.connect();
            
            // Retrieving data
            ResultSet rows = database.select(query);
            
            //Creating the hall list
            Hall hall = null;
            while(rows.next()){
                hall = new Hall();
                hall.setId(rows.getInt("id"));
                hall.setHallCode(rows.getString("hallcode"));
                String hallType = rows.getString("halltype");
                if(hallType.equals(Halls.LECTURE_HALL.toString())){
                    hall.setHallType(Halls.LECTURE_HALL);
                }else if(hallType.equals(Halls.LAB.toString())){
                    hall.setHallType(Halls.LAB);
                }
                
                if(specification.specified(hall)){
                    if(halls == null){
                        halls = new ArrayList<>();
                    }
                    halls.add(hall);
                }
            }
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            throw new RepositoryException(RepositoryError.HALL_NOT_FOUND);
        }
        
        if(halls == null){
            throw new RepositoryException(RepositoryError.HALL_NOT_FOUND);
        }
        return halls;
    }
    
}
