/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.util;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.entity.userrepository.lmsuserrepository.UserRepository;

/**
 *
 * @author Sudarshana Panditha
 */
public class Utility {
    
    public static void dispatchRequest(ServletContext context, 
            HttpServletRequest request, 
            HttpServletResponse response, 
            String url, String message) throws IOException, ServletException{
        context.getRequestDispatcher(url).forward(request, response);
    }
    
    public static boolean hasPresence(String input){
        return input != null && !input.isEmpty();
    }
    
    public static String inputFormat(String input){
        return input.trim().toLowerCase();
    }
    
    public static LocalDateTime dateToLocalDate(Date date){
        ZoneId defaultZoneId = ZoneId.systemDefault();
        Instant instant = date.toInstant();
        return instant.atZone(defaultZoneId).toLocalDateTime();
    }
    
    public static String formatLocalDateTime(LocalDateTime dateTime){
        int year = dateTime.getYear();
        int month = dateTime.getMonthValue();
        int day = dateTime.getDayOfMonth();
        
        int hour = dateTime.getHour();
        int minutes = dateTime.getMinute();
        int seconds = dateTime.getSecond();
        
        String timestamp = String.format("%04d:%02d:%02d %02d:%02d:%02d",
                year,month,day,hour,minutes,seconds);
        
        return timestamp;
    }
}
