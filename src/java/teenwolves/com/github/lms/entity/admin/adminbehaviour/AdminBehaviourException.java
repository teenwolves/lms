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
public class AdminBehaviourException extends Exception{
    private AdminBehaviourError error;

    public AdminBehaviourException(AdminBehaviourError error) {
        this.error = error;
    }

    public AdminBehaviourError getError() {
        return error;
    }
    
}
