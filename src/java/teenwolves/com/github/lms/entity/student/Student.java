package teenwolves.com.github.lms.entity.student;

import java.io.Serializable;
import teenwolves.com.github.lms.entity.User;

/**
 * <code>Student</code> class is a POJO class which represents a Student
 * in the lms application
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public class Student extends User implements Serializable{
    private String studentId;

    public Student() {
        super();
        studentId = "";
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    
}
