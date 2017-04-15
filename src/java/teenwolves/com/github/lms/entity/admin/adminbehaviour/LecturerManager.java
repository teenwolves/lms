package teenwolves.com.github.lms.entity.admin.adminbehaviour;

import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public interface LecturerManager {
    public void addLecturer(Lecturer lecturer) throws AdminBehaviourException;
}
