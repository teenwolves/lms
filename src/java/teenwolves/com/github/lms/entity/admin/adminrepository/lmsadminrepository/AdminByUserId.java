/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminrepository.lmsadminrepository;

import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.admin.adminrepository.AdminSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public class AdminByUserId implements AdminSpecification{

    private int userId;
    
    public AdminByUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean specified(Admin admin) {
        return admin.getId() == userId;
    }
    
}
