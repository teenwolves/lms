/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.course.courserepository.lmscourserepository;

import teenwolves.com.github.lms.entity.course.Course;
import teenwolves.com.github.lms.entity.course.courserepository.CourseSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public class CourseById implements CourseSpecification{

    private int courseId;

    public CourseById(int courseId) {
        this.courseId = courseId;
    }
    
    @Override
    public boolean specified(Course course) {
        return courseId == course.getCourseId();
    }
    
}
