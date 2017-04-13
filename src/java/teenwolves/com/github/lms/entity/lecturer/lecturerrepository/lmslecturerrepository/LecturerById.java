/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.lecturer.lecturerrepository.lmslecturerrepository;

import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.LecturerSpecification;

/**
 * <code>LecturerById</code> class is an implementation of the 
 * <code>LecturerSpecification</code> interface
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see LecturerSpecification
 */
public class LecturerById implements LecturerSpecification{
    // id of the searching lecturer
    private int id;

    public LecturerById(int id) {
        this.id = id;
    }

    @Override
    public boolean specified(Lecturer lecturer) {
        return lecturer.getId() == id;
    }
    
}
