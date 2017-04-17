/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.user.User;
import teenwolves.com.github.lms.entity.userrepository.AbstractUserRepository;
import teenwolves.com.github.lms.entity.userrepository.lmsuserrepository.UserRepository;

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
    
    public static boolean isUserLoggedIn(String user, HttpServletRequest request){
        Cookie userCookie = Utility.getCookie("user", request);
        boolean isUserSet = Utility.isCookieSet(userCookie);
        return isUserSet;
    }
    
    public static Cookie getCookie(String name, HttpServletRequest request){
        Cookie cookies [] = request.getCookies();
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals(name)){
                return cookie;
            }
        }
        return null;
    }
    
    public static boolean isCookieSet(Cookie cookie){
        if(cookie == null){
            return false;
        }else{
            return Utility.hasPresence(cookie.getValue());
        }
    }
}
