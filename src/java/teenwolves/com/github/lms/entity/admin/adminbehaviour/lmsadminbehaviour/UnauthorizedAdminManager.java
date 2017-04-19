/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour;

import java.util.List;
import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourError;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminManager;

/**
 *
 * @author Sudarshana Panditha
 */
public class UnauthorizedAdminManager implements AdminManager{

    @Override
    public void addAdmin(Admin admin) throws AdminBehaviourException {
        throw new AdminBehaviourException(AdminBehaviourError.ACTION_DENIED);
    }

    @Override
    public void updateAdmin(Admin admin) throws AdminBehaviourException {
        throw new AdminBehaviourException(AdminBehaviourError.ACTION_DENIED);
    }

    @Override
    public void deleteAdmin(Admin admin) throws AdminBehaviourException {
        throw new AdminBehaviourException(AdminBehaviourError.ACTION_DENIED);
    }

    @Override
    public List<Admin> findAllAdmins() throws AdminBehaviourException {
            throw new AdminBehaviourException(AdminBehaviourError.ACTION_DENIED);
    }
    
    @Override
    public int toInt() {
        return 0;
    }
    
}
