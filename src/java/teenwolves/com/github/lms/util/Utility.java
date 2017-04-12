/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.util;

/**
 *
 * @author Sudarshana Panditha
 */
public class Utility {
    public static boolean hasPresence(String input){
        return input != null && !input.isEmpty();
    }
    
    public static String inputFormat(String input){
        return input.trim().toLowerCase();
    }
}
