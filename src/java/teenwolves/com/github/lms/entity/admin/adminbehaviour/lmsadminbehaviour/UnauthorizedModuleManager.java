/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour;

import java.util.List;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourError;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.ModuleManager;
import teenwolves.com.github.lms.entity.module.Module;
import teenwolves.com.github.lms.entity.module.modulespecification.ModuleSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public class UnauthorizedModuleManager implements ModuleManager{

    @Override
    public void addModule(Module module) throws AdminBehaviourException {
        throw new AdminBehaviourException(AdminBehaviourError.ACTION_DENIED);
    }

    @Override
    public void updateModule(Module module) throws AdminBehaviourException {
        throw new AdminBehaviourException(AdminBehaviourError.ACTION_DENIED);
    }

    @Override
    public void deleteModule(Module module) throws AdminBehaviourException {
        throw new AdminBehaviourException(AdminBehaviourError.ACTION_DENIED);
    }

    @Override
    public List<Module> findModules(ModuleSpecification specification) throws AdminBehaviourException {
        throw new AdminBehaviourException(AdminBehaviourError.ACTION_DENIED);
    }

    @Override
    public int toInt() {
        return 0;
    }
    
}
