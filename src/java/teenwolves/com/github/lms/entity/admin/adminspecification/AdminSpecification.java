/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminspecification;

import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.entity.user.userspecification.UserSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public interface AdminSpecification extends UserSpecification{
    public boolean specified(Admin admin);
}
