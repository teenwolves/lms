package teenwolves.com.github.lms.entity.student.studentrepository;

import java.util.List;
import teenwolves.com.github.lms.entity.student.Student;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * <code>AbstractStudentRepository</code> class is an interface to create
 * repositories to retrieve database data related to Student
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public interface AbstractStudentRepository {
    
    public void addStudent(Student student) throws RepositoryException;
    public void updateStudent(Student student) throws RepositoryException;
    public void deleteStudent(Student student) throws RepositoryException;
    
    public List<Student> query(StudentSpecification specification) 
            throws RepositoryException;
    
}

