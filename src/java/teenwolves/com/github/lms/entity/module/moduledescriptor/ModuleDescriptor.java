/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.module.moduledescriptor;

import java.io.Serializable;

/**
 *
 * @author Sudarshana Panditha
 */
public class ModuleDescriptor implements Serializable{
    private int id;
    private int moduleId;
    private String title;
    private String description;

    public ModuleDescriptor() {
        id = 0;
        moduleId = 0;
        title = "";
        description = "";
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getModuleId() {
        return moduleId;
    }

    public void setModuleId(int moduleId) {
        this.moduleId = moduleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
    
}
