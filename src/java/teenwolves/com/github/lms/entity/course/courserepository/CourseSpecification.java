/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.course.courserepository;

import teenwolves.com.github.lms.entity.course.Course;

/**
 *
 * @author Sudarshana Panditha
 */
public interface CourseSpecification {
    public boolean specified(Course course);
}
