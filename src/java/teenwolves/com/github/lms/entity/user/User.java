/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.user;

import java.io.Serializable;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public void setAttributes(User user){
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
            return user.getId() == this.id;
        }
        
    }   
    
}
