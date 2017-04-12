package teenwolves.com.github.lms.entity.userrepository;

import teenwolves.com.github.lms.entity.User;

/**
 *
 * <code>UserSpecification</code> class is an interface with a single method 
 * to specify how a query is selected.
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public interface UserSpecification {
    public boolean specified(User user);
}
