/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour;

import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourError;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.LecturerManager;

/**
 *
 * @author Sudarshana Panditha
 */
public class UnauthorizedLecturerManager implements LecturerManager{

    @Override
    public void addLecturer(Lecturer lecturer) throws AdminBehaviourException {
        throw new AdminBehaviourException(AdminBehaviourError.ACTION_DENIED);
    }

    @Override
    public int toInt() {
        return 0;
    }
    
}
