/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.user.utilities;

import java.util.logging.Level;
import java.util.logging.Logger;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.exceptions.UserException;
import teenwolves.com.github.lms.entity.exceptions.errors.UserError;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.user.userspecification.implementations.UserByUsername;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.entity.userrepository.lmsuserrepository.UserRepository;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public class UserUtilities {
    private static final MySQLDatabase DATABASE = new LmsMySQLDatabase();
    private static final AbstractUserRepository USER_REPOSITORY = new UserRepository(DATABASE);
    
    public static String generateUsername(String string){
        string = string.replaceAll(" ", "");
        if(string.length() <= 15){
            return string;
        }else{
            return string.substring(0, 15);
        }
    }
    
    public static String generatePassword(String string){
        string = string.replaceAll(" ", "");
        if(string.length() <= 15){
            return string;
        }else{
            return string.substring(0, 15);
        }
    }
    
    public static boolean isUserNameUnique(String username) throws UserException{
        boolean isUnique;
        try {
            User user = USER_REPOSITORY.query(new UserByUsername(username)).get(0);
            UserError error = UserError.INVALID_VALUE;
            error.setMessage("Username already taken.");
            throw new UserException(error);
        } catch (RepositoryException ex) {
            if(ex.getError() == RepositoryError.USER_NOT_FOUND){
                isUnique = true;
            }else{
                UserError error = UserError.TECHNICAL_ERROR;
                error.setMessage("Error occured.");
                throw new UserException(error);
            }
        }
        return isUnique;
    }
}
