/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.course;

import java.io.Serializable;
import teenwolves.com.github.lms.entity.course.faculty.Faculty;

/**
 *
 * @author Sudarshana Panditha
 */
public class Course implements Serializable{
    private int courseId;
    private int courseDirectorId;
    private String courseCode;
    private String courseName;
    private Faculty faculty;

    public Course() {
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getCourseDirectorId() {
        return courseDirectorId;
    }

    public void setCourseDirectorId(int courseDirectorId) {
        this.courseDirectorId = courseDirectorId;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }
    
    
}
