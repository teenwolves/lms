/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourError;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminManager;
import teenwolves.com.github.lms.entity.admin.adminrepository.AbstractAdminRepository;
import teenwolves.com.github.lms.entity.admin.adminrepository.lmsadminrepository.AdminRepository;
import teenwolves.com.github.lms.entity.user.userspecification.UserSpecification;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.AllUsers;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public class LmsAdminManager implements AdminManager{
    
    private MySQLDatabase database;
    private AbstractAdminRepository adminRepository;

    public LmsAdminManager() {
        database = new LmsMySQLDatabase();
        adminRepository = new AdminRepository(database);
    }
    
    @Override
    public void addAdmin(Admin admin) throws AdminBehaviourException {
        try {
            adminRepository.addAdmin(admin);
        } catch (RepositoryException ex) {
            AdminBehaviourError error = AdminBehaviourError.ACTION_FAILED;
            error.setMessage("Admin is not added.");
            throw new AdminBehaviourException(error);
        }
    }

    @Override
    public void updateAdmin(Admin admin) throws AdminBehaviourException {
        try {
            adminRepository.updateAdmin(admin);
        } catch (RepositoryException ex) {
            AdminBehaviourError error = AdminBehaviourError.ACTION_FAILED;
            error.setMessage("Admin is not updated.");
            throw new AdminBehaviourException(error);
        }
    }

    @Override
    public void deleteAdmin(Admin admin) throws AdminBehaviourException {
        try {
            adminRepository.deleteAdmin(admin);
        } catch (RepositoryException ex) {
            AdminBehaviourError error = AdminBehaviourError.ACTION_FAILED;
            error.setMessage("Admin is not deleted.");
            throw new AdminBehaviourException(error);
        }
    }

    @Override
    public List<Admin> findAdmins(UserSpecification specification) throws AdminBehaviourException {
        try {
            return adminRepository.query(specification);
        } catch (RepositoryException ex) {
            AdminBehaviourError error = AdminBehaviourError.ACTION_FAILED;
            error.setMessage("No Admins are found");
            throw new AdminBehaviourException(error);
        }
    }

    @Override
    public int toInt() {
        return 1;
    }
    
}
