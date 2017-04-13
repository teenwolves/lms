/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.userrepository.lmsuserrepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import teenwolves.com.github.lms.entity.User;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.entity.userrepository.UserSpecification;

/**
 *
 * <code>UserRepository</code> class is an implementation of the 
 * AbstractUserRepository interface
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see AbstractUserRepository
 */
public class UserRepository implements AbstractUserRepository{

    private MySQLDatabase database;

    public UserRepository(MySQLDatabase database) {
        this.database = database;
    }
    @Override
    public List<User> query(UserSpecification specification) throws RepositoryException {
        
        List<User> users = null;
        String query = "SELECT * FROM user";
        
        try {
            // Connecting to the database
            database.connect();
            
            // Querying the database
            ResultSet rows = database.select(query);
            
            // Creating the users list
            User user;
            while(rows.next()){
                user = new User();
                user.setId(rows.getInt("id"));
                user.setName(rows.getString("name"));
                user.setUsername(rows.getString("username"));
                user.setPassword(rows.getString("password"));
                user.setEmail(rows.getString("email"));
                
                // Adding users to the user list which meets the specification
                if(specification.specified(user)){
                    if(users == null){
                        users = new ArrayList<>();
                    }
                    users.add(user);
                }
            }
            
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        }
        
        // Throwing an Exception if no users are not found.
        if(users == null){
            throw new RepositoryException(RepositoryError.USER_NOT_FOUND);
        }
        return users;
    }
    
}
