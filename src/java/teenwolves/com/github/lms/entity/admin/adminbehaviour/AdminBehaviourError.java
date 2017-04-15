/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminbehaviour;

/**
 *
 * @author Sudarshana Panditha
 */
public enum AdminBehaviourError {
    ACTION_DENIED("You have no authorization."),
    ACTION_FAILED;
    
    private String message;

    private AdminBehaviourError() {
        message = "";
    }

    private AdminBehaviourError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
