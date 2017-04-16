/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.user.userspecification.implementations;

import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.user.userspecification.UserSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public class UserById implements UserSpecification{

    private int id;

    public UserById(int id) {
        this.id = id;
    }
    
    @Override
    public boolean specified(User user) {
        return id == user.getId();
    }
    
}
