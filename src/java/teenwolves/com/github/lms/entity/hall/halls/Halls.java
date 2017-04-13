/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.hall.halls;

import java.io.Serializable;

/**
 *
 * @author Sudarshana Panditha
 */
public enum Halls implements Serializable{
    LECTURE_HALL("lecture"),
    LAB("lab");

    private String hallType;

    private Halls(String hallType) {
        this.hallType = hallType;
    }
    
    public String getHallType() {
        return hallType;
    }

    @Override
    public String toString() {
        return hallType;
    }
    
}
