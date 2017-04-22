/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.course.faculty.facultyrepository;

import java.util.List;
import teenwolves.com.github.lms.entity.course.faculty.Faculty;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public interface AbstractFacultyRepository {
    public void addFaculty(Faculty faculty) throws RepositoryException;
    public void updateFaculty(Faculty faculty) throws RepositoryException;
    public void deleteFaculty(Faculty faculty) throws RepositoryException;
    
    public List<Faculty> query(FacultySpecification specification) throws RepositoryException;
}
