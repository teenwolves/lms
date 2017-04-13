/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin;

import java.io.Serializable;
import teenwolves.com.github.lms.entity.user.User;

/**
 * <code>Admin</code> is a POJO class which extends 
 * <code>User</code>
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see User
 */
public class Admin extends User implements Serializable{
    // Admin id
    private String adminId;

    /**
     * Class constructor with no arguments
     */
    public Admin() {
        super();
        adminId = "";
    }

    /**
     * This method gets the admin id of an <code>Admin</code>
     * @return the admin id.
     */
    public String getAdminId() {
        return adminId;
    }

    /**
     * This method sets the admin id of an <code>Admin</code>
     * @param adminId to set
     */
    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }
    
}
