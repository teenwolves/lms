/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.exceptions.errors;

/**
 *
 * @author Sudarshana Panditha
 */
public enum UserError {
    INVALID_VALUE,
    INVALID_SIZE;
    
    // Info about the error
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
