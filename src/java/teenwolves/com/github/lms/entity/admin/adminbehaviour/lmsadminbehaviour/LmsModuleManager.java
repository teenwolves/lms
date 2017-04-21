/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour;

import java.util.List;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourError;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.ModuleManager;
import teenwolves.com.github.lms.entity.module.Module;
import teenwolves.com.github.lms.entity.module.modulerepository.AbstractModuleRepository;
import teenwolves.com.github.lms.entity.module.modulerepository.lmsmodulerepository.ModuleRepository;
import teenwolves.com.github.lms.entity.module.modulespecification.ModuleSpecification;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public class LmsModuleManager implements ModuleManager{

    private final MySQLDatabase database;
    private final AbstractModuleRepository moduleRepository;

    public LmsModuleManager() {
        database = new LmsMySQLDatabase();
        moduleRepository = new ModuleRepository(database);
    }
    
    @Override
    public void addModule(Module module) throws AdminBehaviourException {
        try {
            moduleRepository.addModule(module);
        } catch (RepositoryException ex) {
            AdminBehaviourError error = AdminBehaviourError.ACTION_FAILED;
            error.setMessage("Module is not added.");
            throw new AdminBehaviourException(error);
        }
    }

    @Override
    public void updateModule(Module module) throws AdminBehaviourException {
        try {
            moduleRepository.updateModule(module);
        } catch (RepositoryException ex) {
            AdminBehaviourError error = AdminBehaviourError.ACTION_FAILED;
            error.setMessage("Module is not updated.");
            throw new AdminBehaviourException(error);
        }
    }

    @Override
    public void deleteModule(Module module) throws AdminBehaviourException {
        try {
            moduleRepository.deleteModule(module);
        } catch (RepositoryException ex) {
            AdminBehaviourError error = AdminBehaviourError.ACTION_FAILED;
            error.setMessage("Module is not deleted.");
            throw new AdminBehaviourException(error);
        }
    }

    @Override
    public List<Module> findModules(ModuleSpecification specification) throws AdminBehaviourException{
        try {
            return moduleRepository.query(specification);
        } catch (RepositoryException ex) {
            AdminBehaviourError error = AdminBehaviourError.ACTION_FAILED;
            error.setMessage("No Modules are found");
            throw new AdminBehaviourException(error);
        }
    }
    
    @Override
    public int toInt() {
        return 1;
    }
    
}
