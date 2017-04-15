/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminrepository.lmsadminrepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.LmsAdminManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.LmsLecturerManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.LmsModuleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.LmsScheduleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedAdminManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedLecturerManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedModuleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedScheduleManager;
import teenwolves.com.github.lms.entity.admin.adminrepository.AbstractAdminRepository;
import teenwolves.com.github.lms.entity.admin.adminrepository.AdminSpecification;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.repository.RepositoryUtility;

/**
 *
 * @author Sudarshana Panditha
 */
public class AdminRepository implements AbstractAdminRepository{
    // Database to access Admin data
    private MySQLDatabase database;

    /**
     * Class constructor which takes a <code>MySQLDatabase</code>
     * as an argument.
     * @param database to query data.
     */
    public AdminRepository(MySQLDatabase database) {
        this.database = database;
    }

    @Override
    public void addAdmin(Admin admin) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO admin(");
        query.append("id, ");
        query.append("adminid, ");
        query.append("adminmanager, ");
        query.append("lecturermanager, ");
        query.append("schedulemanager, ");
        query.append("modulemanager) VALUES(");
        query.append(admin.getId());
        query.append(", '");
        query.append(admin.getAdminId());
        query.append("', ");
        query.append(admin.getAdminManager().toString());
        query.append(", ");
        query.append(admin.getLecturerManager().toString());
        query.append(", ");
        query.append(admin.getScheduleManager().toString());
        query.append(", ");
        query.append(admin.getModuleManager().toString());
        query.append(")");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Admin is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateAdmin(Admin admin) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE admin SET ");
        query.append("adminid = '");
        query.append(admin.getAdminId());
        query.append("', adminmanager = ");
        query.append(admin.getAdminManager().toString());
        query.append(", lecturermanager = ");
        query.append(admin.getLecturerManager().toString());
        query.append(", schedulemanager = ");
        query.append(admin.getScheduleManager().toString());
        query.append(", modulemanager = ");
        query.append(admin.getModuleManager().toString());
        query.append(" WHERE id = ");
        query.append(admin.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Admin is not updated");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void deleteAdmin(Admin admin) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM admin WHERE id = ");
        query.append(admin.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Admin is not deleted");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public List<Admin> query(AdminSpecification specification) throws RepositoryException {
        List<Admin> admins = null;
        String  query = "SELECT * FROM user";
        
        try {
            // Connecting to the database
            database.connect();
            
            // Retrieving data
            ResultSet rows = database.select(query);
            
            // Creating the admin list
            Admin admin = null;
            while(rows.next()){
                admin = new Admin();
                admin.setId(rows.getInt("id"));
                admin.setAdminId(rows.getString("adminid"));
                
                int adminManager = rows.getInt("adminmanager");
                int lecturerManager = rows.getInt("lecturermanager");
                int scheduleManager = rows.getInt("schedulemanager");
                int moduleManager = rows.getInt("modulemanager");
                
                setAdminBehaviours(admin, adminManager, lecturerManager, scheduleManager, moduleManager);
                
                if(specification.specified(admin)){
                    if(admins == null){
                        admins = new ArrayList<>();
                    }
                    admins.add(admin);
                }
            }
            
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        }
        if(admins == null){
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        }
        return admins;
    }

    // Helper Methods
    private void setAdminBehaviours( Admin admin, int adminManager, int lecturerManager, int scheduleManager, int moduleManager) {
        if(adminManager == 1){
            admin.setAdminManager(new LmsAdminManager());
        }else{
            admin.setAdminManager(new UnauthorizedAdminManager());
        }
        
        if(lecturerManager == 1){
            admin.setLecturerManager(new LmsLecturerManager());
        }else{
            admin.setLecturerManager(new UnauthorizedLecturerManager());
        }
        
        if(scheduleManager == 1){
            admin.setScheduleManager(new LmsScheduleManager());
        }else{
            admin.setScheduleManager(new UnauthorizedScheduleManager());
        }
        
        if(moduleManager == 1){
            admin.setModuleManager(new LmsModuleManager());
        }else{
            admin.setModuleManager(new UnauthorizedModuleManager());
        }
    }
    
}
