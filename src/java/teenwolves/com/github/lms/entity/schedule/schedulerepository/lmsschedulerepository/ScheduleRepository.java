/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.schedule.schedulerepository.lmsschedulerepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.entity.course.Course;
import teenwolves.com.github.lms.entity.course.courserepository.AbstractCourseRepository;
import teenwolves.com.github.lms.entity.course.courserepository.lmscourserepository.CourseById;
import teenwolves.com.github.lms.entity.course.courserepository.lmscourserepository.CourseRepository;
import teenwolves.com.github.lms.entity.schedule.Schedule;
import teenwolves.com.github.lms.entity.schedule.schedulerepository.AbstractScheduleRepository;
import teenwolves.com.github.lms.entity.schedule.schedulerepository.ScheduleSpecification;
import teenwolves.com.github.lms.entity.schedule.session.Session;
import teenwolves.com.github.lms.entity.schedule.session.sessionrepository.AbstractSessionRepository;
import teenwolves.com.github.lms.entity.schedule.session.sessionrepository.lmssessionrepository.SessionByScheduleId;
import teenwolves.com.github.lms.entity.schedule.session.sessionrepository.lmssessionrepository.SessionRepository;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.repository.RepositoryUtility;

/**
 *
 * @author Sudarshana Panditha
 */
public class ScheduleRepository implements AbstractScheduleRepository{
    
    private MySQLDatabase database;
    private AbstractSessionRepository sessionRepository;
    private AbstractCourseRepository courseRepository;

    public ScheduleRepository(MySQLDatabase database) {
        this.database = database;
        sessionRepository = new SessionRepository(database);
        courseRepository = new CourseRepository(database);
    }

    @Override
    public void addSchedule(Schedule schedule) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO schedule(");
        query.append("courseid, year, semester) VALUES(");
        query.append(schedule.getCourse().getCourseId());
        query.append(",");
        query.append(schedule.getYear());
        query.append(",");
        query.append(schedule.getSemester());
        query.append(")");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Schedule is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateSchedule(Schedule schedule) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE schedule SET ");
        query.append("courseid = ");
        query.append(schedule.getCourse().getCourseId());
        query.append(", year = ");
        query.append(schedule.getYear());
        query.append(", semester = ");
        query.append(schedule.getSemester());
        query.append(" WHERE id = ");
        query.append(schedule.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Schedule is not updated");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void deleteSchedule(Schedule schedule) throws RepositoryException {
        StringBuilder scheduleQuery = new StringBuilder();
        scheduleQuery.append("DELETE FROM schedule WHERE id = ");
        scheduleQuery.append(schedule.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        
        try{
            List<Session> sessions = sessionRepository.query(
                new SessionByScheduleId(schedule.getId()));
            
            for (Session session : sessions) {
                sessionRepository.deleteSession(session);
            }
            
            try{
                error.setErrorMessage("Schedule is not deleted");
                RepositoryUtility.executeQuery(database, scheduleQuery.toString(), error);
            }catch(RepositoryException re){
                for (Session session : sessions) {
                    sessionRepository.addSession(session);
                }
                throw re;
            }
            
        }catch(RepositoryException ex){
            if(ex.getError() == RepositoryError.SESSIONS_NOT_FOUND){
                error.setErrorMessage("Schedule is not deleted");
                RepositoryUtility.executeQuery(database, scheduleQuery.toString(), error);
            }else{
                throw ex;
            }
        }
        
    }

    @Override
    public List<Schedule> query(ScheduleSpecification specification) throws RepositoryException {
        List<Schedule> schedules = null;
        String query = "SELECT * FROM schedule";
        
        try {
            //Connecting to the database
            database.connect();
            
            //Retrieving data
            ResultSet rows = database.select(query);
            
            //Creating the schedule list
            Schedule schedule;
            while (rows.next()) {
                schedule = new Schedule();
                schedule.setId(rows.getInt("id"));
                schedule.setYear(rows.getInt("year"));
                schedule.setSemester(rows.getInt("semester"));
                int courseId = rows.getInt("courseid");
                
                List<Course> courses = courseRepository.query(
                        new CourseById(courseId));
                schedule.setCourse(courses.get(0));
                
                List<Session> sessions = sessionRepository.query(
                        new SessionByScheduleId(schedule.getId()));
                schedule.setSessions(sessions);
                
                if(specification.specified(schedule)){
                    if(schedules == null){
                        schedules = new ArrayList<>();
                    }
                    schedules.add(schedule);
                }
                
            }
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            throw new RepositoryException(RepositoryError.SCHEDULE_NOT_FOUND);
        }
        
        if(schedules == null){
            throw new RepositoryException(RepositoryError.SCHEDULE_NOT_FOUND);
        }
        
        return schedules;
    }
    
}
