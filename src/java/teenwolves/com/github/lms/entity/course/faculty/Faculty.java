/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.course.faculty;

/**
 *
 * @author Sudarshana Panditha
 */
public enum Faculty {
    COMPUTING("computing"), MANAGEMENT("management"), ENGINEERING("engineering");
    
    private String faculty;

    private Faculty(String faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return faculty;
    }
    
    
}
