/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminbehaviour;

import java.util.List;
import teenwolves.com.github.lms.entity.module.Module;
import teenwolves.com.github.lms.entity.module.modulespecification.ModuleSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public interface ModuleManager extends AdminBehaviour{
    public void addModule(Module module) throws AdminBehaviourException;
    public void updateModule(Module module) throws AdminBehaviourException;
    public void deleteModule(Module module) throws AdminBehaviourException;
    
    public List<Module> findModules(ModuleSpecification specification) throws AdminBehaviourException;
}
