package teenwolves.com.github.lms.entity.student.studentspecification;

import teenwolves.com.github.lms.entity.student.Student;
import teenwolves.com.github.lms.entity.user.userspecification.UserSpecification;

/**
 *
 * <code>StudentSpecification</code> class is an interface with a single method 
 * to specify how a query is selected.
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public interface StudentSpecification extends UserSpecification{
    public boolean specified(Student student);
}
