package teenwolves.com.github.lms.entity.admin.adminrepository;

import java.util.List;
import teenwolves.com.github.lms.entity.admin.Admin;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public interface AbstractAdminRepository {
    public void addAdmin(Admin admin) throws RepositoryException;
    public void updateAdmin(Admin admin) throws RepositoryException;
    public void deleteAdmin(Admin admin) throws RepositoryException;
    
    public List<Admin> query(AdminSpecification specification) throws RepositoryException;
}
