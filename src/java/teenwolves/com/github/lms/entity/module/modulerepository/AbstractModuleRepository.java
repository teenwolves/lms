/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.module.modulerepository;

import java.util.List;
import teenwolves.com.github.lms.entity.module.Module;
import teenwolves.com.github.lms.entity.module.modulespecification.ModuleSpecification;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public interface AbstractModuleRepository {
    public void addModule(Module module) throws RepositoryException;
    public void updateModule(Module module) throws RepositoryException;
    public void deleteModule(Module module) throws RepositoryException;
    
    public List<Module> query(ModuleSpecification specification) throws RepositoryException;
}
