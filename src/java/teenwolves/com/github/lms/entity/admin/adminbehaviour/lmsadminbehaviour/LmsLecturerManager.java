/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.admin.adminbehaviour.lmsadminbehaviour;

import java.util.logging.Level;
import java.util.logging.Logger;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourError;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.AdminBehaviourException;
import teenwolves.com.github.lms.entity.lecturer.Lecturer;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.AbstractLecturerRepository;
import teenwolves.com.github.lms.entity.lecturer.lecturerrepository.lmslecturerrepository.LecturerRepository;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.entity.admin.adminbehaviour.LecturerManager;

/**
 *
 * @author Sudarshana Panditha
 */
public class LmsLecturerManager implements LecturerManager{

    private MySQLDatabase database;
    private AbstractLecturerRepository lecturerRepository;

    public LmsLecturerManager() {
        database = new LmsMySQLDatabase();
        lecturerRepository = new LecturerRepository(database);
    }
    
    @Override
    public void addLecturer(Lecturer lecturer) throws AdminBehaviourException {
        try {
            // Registering the lecturer
            lecturerRepository.addLecturer(lecturer);
        } catch (RepositoryException ex) {
            AdminBehaviourError error = AdminBehaviourError.ACTION_FAILED;
            error.setMessage("Lecturer is not added.");
            throw new AdminBehaviourException(error);
        }
    }

    @Override
    public int toInt() {
        return 1;
    }
    
}
