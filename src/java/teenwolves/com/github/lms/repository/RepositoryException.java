package teenwolves.com.github.lms.repository;

/**
 *
 * <code>RepositoryException</code> class is an extention of 
 * <code>Exception</code> class
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see Exception
 */
public class RepositoryException extends Exception{
    // Specifies the type of error
    private RepositoryError error;

    /**
     * Class constructor with <code>RepositoryError</code> specifying the type of error
     * @param error is the type of error causing the Exception
     * @see RepositoryError
     */
    public RepositoryException(RepositoryError error) {
        this.error = error;
    }

    public RepositoryError getError() {
        return error;
    }
    
}
