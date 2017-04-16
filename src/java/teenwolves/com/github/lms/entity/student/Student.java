package teenwolves.com.github.lms.entity.student;

import java.io.Serializable;
import teenwolves.com.github.lms.entity.user.User;

/**
 * <code>Student</code> class is a POJO class which represents a Student
 * in the lms application
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public class Student extends User implements Serializable{

    public Student() {
        super();
    }

    public Student(String studentId) {
        
    }
    
}
