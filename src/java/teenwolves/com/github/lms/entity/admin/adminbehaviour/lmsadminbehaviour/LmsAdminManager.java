/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour;

import java.util.logging.Level;
import java.util.logging.Logger;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminManager;
import teenwolves.com.github.lms.entity.admin.adminrepository.AbstractAdminRepository;
import teenwolves.com.github.lms.entity.admin.adminrepository.lmsadminrepository.AdminRepository;
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
            Logger.getLogger(LmsAdminManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void updateAdmin(Admin admin) throws AdminBehaviourException {
        
    }

    @Override
    public void deleteAdmin(Admin admin) throws AdminBehaviourException {
        
    }

    @Override
    public String toString() {
        return Integer.toString(1);
    }

    
    
}
