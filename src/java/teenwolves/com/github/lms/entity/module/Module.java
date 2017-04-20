/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.module;

import java.io.Serializable;

/**
 *
 * @author Sudarshana Panditha
 */
public class Module implements Serializable{
    private int id;
    private int lecturerId;
    private int courseid;

    public Module() {
        this.id = 0;
        this.lecturerId = 0;
        this.courseid = 0;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(int lecturerId) {
        this.lecturerId = lecturerId;
    }

    public int getCourseid() {
        return courseid;
    }

    public void setCourseid(int courseid) {
        this.courseid = courseid;
    }
    
    
}
