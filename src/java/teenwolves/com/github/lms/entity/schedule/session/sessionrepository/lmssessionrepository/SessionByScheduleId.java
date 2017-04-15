/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.schedule.session.sessionrepository.lmssessionrepository;

import teenwolves.com.github.lms.entity.schedule.session.Session;
import teenwolves.com.github.lms.entity.schedule.session.sessionrepository.SessionSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public class SessionByScheduleId implements SessionSpecification{
    private int scheduleId;

    public SessionByScheduleId(int scheduleId) {
        this.scheduleId = scheduleId;
    }

    @Override
    public boolean specified(Session session) {
        return scheduleId == session.getScheduleId();
    }
    
    
}
