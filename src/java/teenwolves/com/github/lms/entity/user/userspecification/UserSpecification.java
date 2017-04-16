package teenwolves.com.github.lms.entity.user.userspecification;

import teenwolves.com.github.lms.entity.user.User;

/**
 *
 * <code>UserSpecification</code> class is an interface with a single method 
 * to specify how a query is selected.
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public interface UserSpecification {
    default public boolean specified(User user){return true;}
}
