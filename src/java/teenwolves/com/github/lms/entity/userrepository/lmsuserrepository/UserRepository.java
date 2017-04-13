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
import java.util.logging.Level;
import java.util.logging.Logger;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.entity.userrepository.UserSpecification;
import teenwolves.com.github.lms.repository.RepositoryUtility;

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

    @Override
    public void addUser(User user) throws RepositoryException{
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO user");
        query.append("(name, username, password, email) ");
        query.append("VALUES('");
        query.append(user.getName());
        query.append("','");
        query.append(user.getUsername());
        query.append("','");
        query.append(user.getPassword());
        query.append("','");
        query.append(user.getEmail());
        query.append("')");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("User not added.");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateUser(User user) throws RepositoryException{
        StringBuilder query = new StringBuilder();
        query.append("UPDATE user SET ");
        query.append("name = ");
        query.append(user.getName());
        query.append(", username = ");
        query.append(user.getUsername());
        query.append(", password = ");
        query.append(user.getPassword());
        query.append(", email = ");
        query.append(user.getEmail());
        query.append(" WHERE id = ");
        query.append(user.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Update Unsuccessful");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void deleteUser(User user) throws RepositoryException{
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM user WHERE id=");
        query.append(user.getId());
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Deletion Unsuccessful");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }
    
}
