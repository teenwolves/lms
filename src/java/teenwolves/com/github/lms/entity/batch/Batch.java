/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.batch;

import java.io.Serializable;

/**
 *
 * @author Sudarshana Panditha
 */
public class Batch implements Serializable{
    private String id;
    private int courseId;

    public Batch() {
        id = "";
        courseId = 0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
    
}
