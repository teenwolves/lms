package teenwolves.com.github.lms.entity.repository;

import java.util.List;
import teenwolves.com.github.lms.entity.User;

/**
 *
 * <code>AbstractUserRepository</code> class is an interface to create
 * repositories to retrieve database data
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public interface AbstractUserRepository {
    
    public List<User> query(UserSpecification specification) throws RepositoryException;
}
