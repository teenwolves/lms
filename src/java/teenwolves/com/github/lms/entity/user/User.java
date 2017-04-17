/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.user;

import java.io.Serializable;
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

    public User() {
        id = -1;
        name = "";
        username = "";
        password = "";
        email = "";
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
