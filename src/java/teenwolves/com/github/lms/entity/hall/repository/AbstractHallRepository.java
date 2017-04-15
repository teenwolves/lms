/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.hall.repository;

import java.util.List;
import teenwolves.com.github.lms.entity.hall.Hall;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public interface AbstractHallRepository {
    public void addHall(Hall hall) throws RepositoryException;
    public void updateHall(Hall hall) throws RepositoryException;
    public void deleteHall(Hall hall) throws RepositoryException;
    
    public List<Hall> query(HallSpecification specification) throws RepositoryException;
}
