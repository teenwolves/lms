/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.schedule.session.sessionrepository.lmssessionrepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.entity.hall.Hall;
import teenwolves.com.github.lms.entity.hall.repository.AbstractHallRepository;
import teenwolves.com.github.lms.entity.hall.repository.lmshallrepository.HallById;
import teenwolves.com.github.lms.entity.hall.repository.lmshallrepository.HallRepository;
import teenwolves.com.github.lms.entity.schedule.session.Session;
import teenwolves.com.github.lms.entity.schedule.session.sessionrepository.AbstractSessionRepository;
import teenwolves.com.github.lms.entity.schedule.session.sessionrepository.SessionSpecification;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.repository.RepositoryUtility;
import teenwolves.com.github.lms.util.Utility;

/**
 *
 * @author Sudarshana Panditha
 */
public class SessionRepository implements AbstractSessionRepository{
    
    private MySQLDatabase database;
    private AbstractHallRepository hallRepository;

    public SessionRepository(MySQLDatabase database) {
        this.database = database;
        this.hallRepository = new HallRepository(database);
    }

    @Override
    public void addSession(Session session) throws RepositoryException {
        
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO session(");
        query.append("scheduleid, sessiondate, hallid) VALUES(");
        query.append(session.getScheduleId());
        query.append(",'");
        query.append(Utility.formatLocalDateTime(session.getDate()));
        query.append("',");
        query.append(session.getHall().getId());
        query.append(")");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Session is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateSession(Session session) throws RepositoryException {
        
        StringBuilder query = new StringBuilder();
        query.append("UPDATE session SET ");
        query.append("scheduleid = ");
        query.append(session.getScheduleId());
        query.append(",sessiondate = '");
        query.append(Utility.formatLocalDateTime(session.getDate()));
        query.append("',hallid = ");
        query.append(session.getHall().getId());
        query.append("WHERE id = ");
        query.append(session.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Session is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void deleteSession(Session session) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM session WHERE id = ");
        query.append(session.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Session is not deleted");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public List<Session> query(SessionSpecification specification) throws RepositoryException {
        List<Session> sessions = null;
        String query = "SELECT * FROM session";
        
        try {
            // Connecting to the database
            database.connect();
            
            // Retrieving data
            ResultSet rows = database.select(query);
            
            // Creating the hall list
            Session session = null;
            while(rows.next()){
                session = new Session();
                session.setId(rows.getInt("id"));
                session.setScheduleId(rows.getInt("scheduleid"));
                
                // Setting the date
                Date date = rows.getDate("sessiondate");
                session.setDate(Utility.dateToLocalDate(date));
                
                // Setting the hall
                int hallId = rows.getInt("hallid");
                List<Hall> halls = hallRepository.query(new HallById(hallId));
                Hall hall = halls.get(0);
                session.setHall(hall);
                
                if(specification.specified(session)){
                    if(sessions == null){
                        sessions = new ArrayList<>();
                    }
                    sessions.add(session);
                }
                
            }
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            throw new RepositoryException(RepositoryError.SESSIONS_NOT_FOUND);
        }
        
        if(sessions == null){
            throw new RepositoryException(RepositoryError.SESSIONS_NOT_FOUND);
        }
        return sessions;
    }
    
}
