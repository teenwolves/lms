package teenwolves.com.github.lms.repository;

/**
 * <code>RepositoryError</code> is an <code>enum</code> representing common
 * that causes a <code>RepositoryException</code>
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see RepositoryException
 */
public enum RepositoryError {
    TECHNICAL_ERROR("We are unavailable at the moment. Please come back later."), 
    NOT_FOUND("Not found."),
    USER_NOT_FOUND("Please check your username/password.");
    
    // Message for displaying purposes
    private final String errorMessage;

    /**
     * <code>Enum</code> constructor which takes a <code>String</code>
     * as an argument.
     * @param errorMessage 
     */
    private RepositoryError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * This method is used to get the error message of the 
     * <code>RepositoryError</code>.
     * @return the error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }
    
}
