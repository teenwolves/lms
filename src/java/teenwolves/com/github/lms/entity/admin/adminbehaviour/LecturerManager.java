package teenwolves.com.github.lms.entity.admin.adminbehaviour;

import java.util.List;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.entity.user.userspecification.UserSpecification;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public interface LecturerManager extends AdminBehaviour{
    public void addLecturer(Lecturer lecturer) throws AdminBehaviourException;
    public void deleteLecturer(Lecturer lecturer) throws AdminBehaviourException;
    public List<Lecturer> findLecturers(UserSpecification specification) throws AdminBehaviourException;
}
