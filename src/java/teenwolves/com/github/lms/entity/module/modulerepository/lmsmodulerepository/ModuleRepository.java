/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.module.modulerepository.lmsmodulerepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.entity.module.Module;
import teenwolves.com.github.lms.entity.module.modulerepository.AbstractModuleRepository;
import teenwolves.com.github.lms.entity.module.modulespecification.ModuleSpecification;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.repository.RepositoryUtility;

/**
 *
 * @author Sudarshana Panditha
 */
public class ModuleRepository implements AbstractModuleRepository{
    
    private MySQLDatabase database;

    public ModuleRepository(MySQLDatabase database) {
        this.database = database;
    }

    @Override
    public void addModule(Module module) throws RepositoryException {
        StringBuilder query  = new StringBuilder();
        query.append("INSERT INTO module");
        query.append("(lecturerid, facultyid, title, description) ");
        query.append("VALUES(");
        query.append(module.getLecturerId());
        query.append(", ");
        query.append(module.getFacultyId());
        query.append(", '");
        query.append(module.getTitle());
        query.append("' , '");
        query.append(module.getDescription());
        query.append("')");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Module is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
        
    }

    @Override
    public void updateModule(Module module) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("UPDATE module SET ");
        query.append("lecturerid = ");
        query.append(module.getLecturerId());
        query.append(", facultyid = ");
        query.append(module.getFacultyId());
        query.append(", title = '");
        query.append(module.getTitle());
        query.append("', description = '");
        query.append(module.getDescription());
        query.append("' WHERE id = ");
        query.append(module.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Module is not updated");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void deleteModule(Module module) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM module WHERE id = ");
        query.append(module.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Module is not deleted");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public List<Module> query(ModuleSpecification specification) throws RepositoryException {
        List<Module> modules = null;
        String query = "SELECT * FROM module";
        
        try {
            //Connecting to the database
            database.connect();
            
            //Get results
            ResultSet rows = database.select(query);
            
            // creating the list of modules
            Module module = null;
            while (rows.next()) {
                module = new Module();
                module.setId(rows.getInt("id"));
                module.setLecturerId(rows.getInt("lecturerid"));
                module.setFacultyId(rows.getInt("facultyid"));
                module.setTitle(rows.getString("title"));
                module.setDescription(rows.getString("description"));
                
                if(specification.specify(module)){
                    if(modules == null){
                        modules = new ArrayList<>();
                    }
                    modules.add(module);
                }
            }
            
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            throw new RepositoryException(RepositoryError.MODULES_NOT_FOUND);
        }
        
        if(modules == null){
            throw new RepositoryException(RepositoryError.MODULES_NOT_FOUND);
        }
        
        return modules;
    }
    
}
