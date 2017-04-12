/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.student.studentrepository.lmsstudentrepository;

import teenwolves.com.github.lms.entity.student.Student;
import teenwolves.com.github.lms.entity.student.studentrepository.StudentSpecification;

/**
 * <code>StudentByStudentId</code> class is an implementation of the 
 * <code>StudentSpecification</code> interface
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see StudentSpecification
 */
public class StudentById implements StudentSpecification{
    // id of the searching student
    private int id;

    /**
     * Class constructor which specifies the id to be searched.
     * @param id is the searching id
     */
    public StudentById(int id) {
        this.id = id;
    }
    
    @Override
    public boolean specified(Student student) {
        return student.getId() == id;
    }
    
}
