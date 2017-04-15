/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.course.courserepository.lmscourserepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.entity.course.Course;
import teenwolves.com.github.lms.entity.course.courserepository.AbstractCourseRepository;
import teenwolves.com.github.lms.entity.course.courserepository.CourseSpecification;
import teenwolves.com.github.lms.entity.course.faculty.Faculty;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.repository.RepositoryUtility;

/**
 *
 * @author Sudarshana Panditha
 */
public class CourseRepository implements AbstractCourseRepository{

    private MySQLDatabase database;

    public CourseRepository(MySQLDatabase database) {
        this.database = database;
    }
    
    @Override
    public void addCourse(Course course) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO course(");
        query.append("directorid, coursecode, coursename, faculty) VALUES('");
        query.append(course.getCourseDirectorId());
        query.append("','");
        query.append(course.getCourseCode());
        query.append("','");
        query.append(course.getCourseName());
        query.append("','");
        query.append(course.getFaculty().toString());
        query.append("')");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Course is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateCourse(Course course) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE course SET ");
        query.append("directorid = '");
        query.append(course.getCourseDirectorId());
        query.append("', coursecode = '");
        query.append(course.getCourseCode());
        query.append("', coursename = '");
        query.append(course.getCourseName());
        query.append("', faculty = '");
        query.append(course.getFaculty().toString());
        query.append("' WHERE id = ");
        query.append(course.getCourseId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Course is not updated.");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void deleteCourse(Course course) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM course WHERE id=");
        query.append(course.getCourseId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Course is not deleted.");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public List<Course> query(CourseSpecification specification) throws RepositoryException {
        List<Course> courses = null;
        String query = "SELECT * FROM course";
        
        try {
            // Connecting to the database
            database.connect();
            
            // Retrieving data
            ResultSet rows = database.select(query);
            
            //Creating the hall list
            Course course = null;
            while(rows.next()){
                course = new Course();
                course.setCourseId(rows.getInt("id"));
                course.setCourseDirectorId(rows.getInt("directorid"));
                course.setCourseCode(rows.getString("coursecode"));
                course.setCourseName(rows.getString("coursename"));
                
                String faculty = rows.getString("faculty");
                if(faculty.equals(Faculty.COMPUTING.toString())){
                    course.setFaculty(Faculty.COMPUTING);
                }else if(faculty.equals(Faculty.MANAGEMENT.toString())){
                    course.setFaculty(Faculty.MANAGEMENT);
                }else if(faculty.equals(Faculty.ENGINEERING.toString())){
                    course.setFaculty(Faculty.ENGINEERING);
                }
                
                if(specification.specified(course)){
                    if(courses == null){
                        courses = new ArrayList<>();
                    }
                    courses.add(course);
                }
                
            }
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            throw new RepositoryException(RepositoryError.COURSE_NOT_FOUND);
        }
        
        if(courses == null){
            throw new RepositoryException(RepositoryError.COURSE_NOT_FOUND);
        }
        return courses;
    }
    
}
