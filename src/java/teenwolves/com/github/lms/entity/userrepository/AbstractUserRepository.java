package teenwolves.com.github.lms.entity.userrepository;

import teenwolves.com.github.lms.repository.RepositoryException;
import java.util.List;
import teenwolves.com.github.lms.entity.user.User;

/**
 *
 * <code>AbstractUserRepository</code> class is an interface to create
 * repositories to retrieve database data
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public interface AbstractUserRepository {
    
    public void addUser(User user) throws RepositoryException;
    public void updateUser(User user) throws RepositoryException;
    public void deleteUser(User user) throws RepositoryException;
    
    public List<User> query(UserSpecification specification) throws RepositoryException;
}
