/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.schedule.schedulerepository;

import teenwolves.com.github.lms.entity.schedule.Schedule;

/**
 *
 * @author Sudarshana Panditha
 */
public interface ScheduleSpecification {
    public boolean specified(Schedule schedule);
}
