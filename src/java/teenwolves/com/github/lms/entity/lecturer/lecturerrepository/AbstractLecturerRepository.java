/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.lecturer.lecturerrepository;

import java.util.List;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 * <code>AbstractLecturerRepository</code> class is an interface to create
 * repositories to retrieve database data related to Lecturers
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public interface AbstractLecturerRepository {
    public void addLecturer(Lecturer lecturer) throws RepositoryException;
    public void updateLecturer(Lecturer lecturer) throws RepositoryException;
    public void deleteLecturer(Lecturer lecturer) throws RepositoryException;
    
    public List<Lecturer> query(LecturerSpecification specification) throws RepositoryException; 
}
