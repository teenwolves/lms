/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.userrepository.lmsuserrepository;

import teenwolves.com.github.lms.entity.User;
import teenwolves.com.github.lms.entity.userrepository.UserSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public class UserByUsernameAndPassword implements UserSpecification{
    
    private String username;
    private String password;

    public UserByUsernameAndPassword(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public boolean specified(User user) {
        return user.getUsername().equals(username) 
                && user.getPassword().equals(password);
    }
    
}
