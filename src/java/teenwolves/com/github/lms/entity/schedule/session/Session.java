/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.schedule.session;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import teenwolves.com.github.lms.entity.hall.Hall;

/**
 *
 * @author Sudarshana Panditha
 */
public class Session implements Serializable{
    private int id;
    private int scheduleId;
    private LocalDateTime date;
    private Hall hall;

    public Session() {
        id = 0;
        scheduleId = 0;
        date = null;
        hall = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    public Hall getHall() {
        return hall;
    }

    public void setHall(Hall hall) {
        this.hall = hall;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
    
    
    
}
