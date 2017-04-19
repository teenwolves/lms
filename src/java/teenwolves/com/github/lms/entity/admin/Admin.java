/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin;

import java.io.Serializable;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.LecturerManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.ModuleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.ScheduleManager;
import teenwolves.com.github.lms.entity.user.User;

/**
 * <code>Admin</code> is a POJO class which extends 
 * <code>User</code>
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see User
 */
public class Admin extends User implements Serializable{
    /**
     * Class constructor with no arguments
     */
    public Admin() {
        super();
    }
    
}
