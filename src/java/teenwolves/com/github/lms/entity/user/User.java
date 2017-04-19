/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.user;

import java.io.Serializable;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.LecturerManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.ModuleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.ScheduleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.LmsScheduleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedAdminManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedLecturerManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedModuleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedScheduleManager;
import teenwolves.com.github.lms.entity.exceptions.UserException;
import teenwolves.com.github.lms.entity.exceptions.errors.UserError;

/**
 * <code>User</code> is a POJO class in the lms application.
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public class User implements Serializable{
    private int id;
    private String name;
    private String username;
    private String password;
    private String email;
    
    private AdminManager adminManager;
    private LecturerManager lecturerManager;
    private ModuleManager moduleManager;
    private ScheduleManager scheduleManager;

    public User() {
        id = -1;
        name = "";
        username = "";
        password = "";
        email = "";
        adminManager = new UnauthorizedAdminManager();
        lecturerManager = new UnauthorizedLecturerManager();
        moduleManager = new UnauthorizedModuleManager();
        scheduleManager = new UnauthorizedScheduleManager();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) throws UserException{
        if(id < 1){
            UserError error = UserError.INVALID_VALUE;
            error.setMessage("Invalid id");
            throw new UserException(error);
        }
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws UserException{
        if(name.length() > 50){
            UserError error = UserError.INVALID_SIZE;
            error.setMessage("Name can not have more than 50 characters.");
            throw new UserException(error);
        }
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) throws UserException{
        if(username.length() > 15){
            UserError error = UserError.INVALID_SIZE;
            error.setMessage("Userame can not have more than 15 characters.");
            throw new UserException(error);
        }
        
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) throws UserException {
        if(password.length() > 15){
            UserError error = UserError.INVALID_SIZE;
            error.setMessage("Password can not have more than 15 characters.");
            throw new UserException(error);
        }
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws UserException{
        if(email.length() > 255){
            UserError error = UserError.INVALID_SIZE;
            error.setMessage("Email address can not have more than 255 characters.");
            throw new UserException(error);
        }
        this.email = email;
    }
    
    public AdminManager getAdminManager() {
        return adminManager;
    }

    public void setAdminManager(AdminManager adminManager) {
        this.adminManager = adminManager;
    }

    public LecturerManager getLecturerManager() {
        return lecturerManager;
    }

    public void setLecturerManager(LecturerManager lecturerManager) {
        this.lecturerManager = lecturerManager;
    }

    public ModuleManager getModuleManager() {
        return moduleManager;
    }

    public void setModuleManager(ModuleManager moduleManager) {
        this.moduleManager = moduleManager;
    }

    public ScheduleManager getScheduleManager() {
        return scheduleManager;
    }

    public void setScheduleManager(ScheduleManager scheduleManager) {
        this.scheduleManager = scheduleManager;
    }
    
    public void setAttributes(User user) throws UserException{
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.email = user.getEmail();
    }
    
    @Override
    public boolean equals(Object o) {
        if(o == null){
            return false;
        }else if(this == o){
            return true;
        }else if(!(o instanceof User)){
            return false;
        }else{
            User user = (User) o;
            return user.getId() == this.id
                    || user.getUsername().equals(this.getUsername());
        }
        
    }   
    
}
