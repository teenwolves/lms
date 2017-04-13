/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.lecturer.lecturerrepository;

import teenwolves.com.github.lms.entity.lecturer.Lecturer;

/**
 * <code>LecturerSpecification</code> class is an interface with a single method 
 * to specify how a query is selected.
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public interface LecturerSpecification {
    public boolean specified(Lecturer lecturer);
}
