/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.hall;

import java.io.Serializable;
import teenwolves.com.github.lms.entity.hall.halls.Halls;

/**
 *
 * @author Sudarshana Panditha
 */
public class Hall implements Serializable{
    private int id;
    private String hallCode;
    private Halls hallType;

    public Hall() {
        id = 0;
        hallCode = "";
        hallType = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Halls getHallType() {
        return hallType;
    }

    public void setHallType(Halls hallType) {
        this.hallType = hallType;
    }

    public String getHallCode() {
        return hallCode;
    }

    public void setHallCode(String hallCode) {
        this.hallCode = hallCode;
    }
    
}
