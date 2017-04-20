/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.module.modulerepository.lmsmodulerepository;

import java.util.List;
import teenwolves.com.github.lms.database.MySQLDatabase;
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

    @Override
    public void addModule(Module module) throws RepositoryException {
        StringBuilder query  = new StringBuilder();
        query.append("INSERT INTO module");
        query.append("(lecturerid, courseid, mdid) ");
        query.append("VALUES(");
        query.append(module.getLecturerId());
        query.append(", ");
        query.append(module.getCourseid());
        query.append(")");
        
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
        query.append(", courseid = ");
        query.append(module.getCourseid());
        query.append(" WHERE id = ");
        query.append(module.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Module is not updated");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void deleteModule(Module module) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Module> query(ModuleSpecification specification) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
