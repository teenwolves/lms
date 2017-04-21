/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.module.modulespecification.implementations;

import teenwolves.com.github.lms.entity.module.Module;
import teenwolves.com.github.lms.entity.module.modulespecification.ModuleSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public class ModuleById implements ModuleSpecification{
    private final int moduleId;

    public ModuleById(int moduleId) {
        this.moduleId = moduleId;
    }
    
    @Override
    public boolean specify(Module module) {
        return module.getId() == moduleId;
    }
    
    
}
