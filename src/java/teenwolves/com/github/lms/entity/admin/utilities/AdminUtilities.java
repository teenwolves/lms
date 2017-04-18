/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.utilities;

import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.LecturerManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.ModuleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.ScheduleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.LmsAdminManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.LmsLecturerManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.LmsModuleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.LmsScheduleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedAdminManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedLecturerManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedModuleManager;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour.UnauthorizedScheduleManager;

/**
 *
 * @author Sudarshana Panditha
 */
public class AdminUtilities {
    public static AdminManager getAdminManager(boolean isAdminManager){
        if(isAdminManager){
            return new LmsAdminManager();
        }
        return new UnauthorizedAdminManager();
    }
    
    public static LecturerManager getLecturerManager(boolean isLecturerManager){
        if(isLecturerManager){
            return new LmsLecturerManager();
        }
        return new UnauthorizedLecturerManager();
    }
    
    public static ModuleManager getModuleManager(boolean isModuleManager){
        if(isModuleManager){
            return new LmsModuleManager();
        }
        return new UnauthorizedModuleManager();
    }
    
    public static ScheduleManager getScheduleManager(boolean isScheduleManager){
        if(isScheduleManager){
            return new LmsScheduleManager();
        }
        return new UnauthorizedScheduleManager();
    }
}
