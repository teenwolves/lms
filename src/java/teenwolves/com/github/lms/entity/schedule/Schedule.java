/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.schedule;

import java.io.Serializable;
import java.util.List;
import teenwolves.com.github.lms.entity.course.Course;
import teenwolves.com.github.lms.entity.schedule.session.Session;

/**
 *
 * @author Sudarshana Panditha
 */
public class Schedule implements Serializable{
    private int id;
    private Course course;
    private int year;
    private int semester;
    private List<Session> sessions;

    public Schedule() {
        id = 0;
        course = new Course();
        year = 0;
        semester = 0;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public List<Session> getSessions() {
        return sessions;
    }

    public void setSessions(List<Session> sessions) {
        this.sessions = sessions;
    }
    
    
    
    
}
