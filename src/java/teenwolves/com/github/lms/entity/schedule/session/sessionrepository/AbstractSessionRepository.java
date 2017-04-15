/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.schedule.session.sessionrepository;

import java.util.List;
import teenwolves.com.github.lms.entity.schedule.session.Session;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public interface AbstractSessionRepository {
    public void addSession(Session session) throws RepositoryException;
    public void updateSession(Session session) throws RepositoryException;
    public void deleteSession(Session session) throws RepositoryException;
    
    public List<Session> query(SessionSpecification specification) throws RepositoryException;
}
