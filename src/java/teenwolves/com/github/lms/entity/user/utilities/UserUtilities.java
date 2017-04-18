/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.user.utilities;

/**
 *
 * @author Sudarshana Panditha
 */
public class UserUtilities {
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
}
