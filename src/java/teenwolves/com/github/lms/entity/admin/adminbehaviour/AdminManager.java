/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminbehaviour;

import teenwolves.com.github.lms.entity.admin.Admin;

/**
 *
 * @author Sudarshana Panditha
 */
public interface AdminManager {
    public void addAdmin(Admin admin) throws AdminBehaviourException;
    public void updateAdmin(Admin admin) throws AdminBehaviourException;
    public void deleteAdmin(Admin admin) throws AdminBehaviourException;

    @Override
    public String toString();
    
}
