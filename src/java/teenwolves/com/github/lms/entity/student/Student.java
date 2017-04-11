package teenwolves.com.github.lms.entity.student;

import java.io.Serializable;

/**
 * <code>Student</code> class is a POJO class which represents a Student
 * in the lms application
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public class Student implements Serializable{
    private String studentId;
    private String name;
    private String username;
    private String password;
    private String email;

    public Student() {
        studentId = "";
        name = "";
        username = "";
        password = "";
        email = "";
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    
    
}
