/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.exceptions;

import teenwolves.com.github.lms.entity.exceptions.errors.UserError;

/**
 *
 * @author Sudarshana Panditha
 */
public class UserException extends Exception{
    private final UserError error;

    public UserException(UserError error) {
        this.error = error;
    }
    
}
