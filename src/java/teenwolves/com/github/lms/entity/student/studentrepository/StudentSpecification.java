package teenwolves.com.github.lms.entity.student.studentrepository;

import teenwolves.com.github.lms.entity.student.Student;

/**
 *
 * <code>StudentSpecification</code> class is an interface with a single method 
 * to specify how a query is selected.
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public interface StudentSpecification {
    public boolean specified(Student student);
}
