/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.schedule.schedulerepository;

import java.util.List;
import teenwolves.com.github.lms.entity.schedule.Schedule;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public interface AbstractScheduleRepository {
    public void addSchedule(Schedule schedule) throws RepositoryException;
    public void updateSchedule(Schedule schedule) throws RepositoryException;
    public void deleteSchedule(Schedule schedule) throws RepositoryException;
    
    public List<Schedule> query(ScheduleSpecification specification) throws RepositoryException;
}
