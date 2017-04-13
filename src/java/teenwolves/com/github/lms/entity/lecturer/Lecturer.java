/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.lecturer;

import java.io.Serializable;
import teenwolves.com.github.lms.entity.User;

/**
 * <code>Lecturer</code> is an POJO class which represents a Lecturer 
 * in the lms application
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public class Lecturer extends User implements Serializable{
    
    private String lecturerId;

    public Lecturer() {
        super();
        lecturerId = "";
    }
    public String getLecturerId() {
        return lecturerId;
    }

    public void setLecturerId(String lecturerId) {
        this.lecturerId = lecturerId;
    }
    
}