/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.user.authentication.exception;

import teenwolves.com.github.lms.entity.user.authentication.errors.AuthenticationError;

/**
 *
 * @author Sudarshana Panditha
 */
public class AuthenticationException extends Exception{
    private AuthenticationError error;

    public AuthenticationException(AuthenticationError error) {
        this.error = error;
    }

    public AuthenticationError getError() {
        return error;
    }
    
}
