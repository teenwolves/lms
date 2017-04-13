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
import java.util.logging.Level;
import java.util.logging.Logger;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.admin.adminrepository.AbstractAdminRepository;
import teenwolves.com.github.lms.entity.admin.adminrepository.AdminSpecification;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;

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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateAdmin(Admin admin) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteAdmin(Admin admin) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
}
