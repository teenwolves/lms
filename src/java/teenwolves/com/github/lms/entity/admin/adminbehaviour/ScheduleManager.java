package teenwolves.com.github.lms.entity.admin.adminbehaviour;

/**
 *
 * @author Sudarshana Panditha
 */
public interface ScheduleManager extends AdminBehaviour{
    public void addSchedule();
    public void updateShedule();
    public void deleteSchedule();
    
    public void addSession();
    public void updateSession();
    public void deleteSession();
}
