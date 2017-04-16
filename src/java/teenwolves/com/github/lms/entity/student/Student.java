package teenwolves.com.github.lms.entity.student;

import java.io.Serializable;
import java.util.List;
import teenwolves.com.github.lms.entity.module.Module;
import teenwolves.com.github.lms.entity.student.instructorbehaviour.Instructor;
import teenwolves.com.github.lms.entity.user.User;

/**
 * <code>Student</code> class is a POJO class which represents a Student
 * in the lms application
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public class Student extends User implements Serializable{

    private Instructor instructor;
    private List<Module> modules;
    
    public Student() {
        super();
    }

    public Student(String studentId) {
        
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
    
}
