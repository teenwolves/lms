/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.course.courserepository;

import java.util.List;
import teenwolves.com.github.lms.entity.course.Course;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public interface AbstractCourseRepository {
    public void addCourse(Course course) throws RepositoryException;
    public void updateCourse(Course course) throws RepositoryException;
    public void deleteCourse(Course course) throws RepositoryException;
    
    public List<Course> query(CourseSpecification specification) throws RepositoryException;
}
