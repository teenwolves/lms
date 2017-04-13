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
    USER_NOT_FOUND("Please check your username/password."),
    UNSUCCESSFUL_EXECUTION,
    HALL_NOT_FOUND("Hall is not found.");
    
    // Message for displaying purposes
    private String errorMessage;

    /**
     * Class constructor with no arguments
     */
    private RepositoryError() {
        errorMessage = "";
    }
    
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

    /**
     * This method sets the error message of the occurred exception
     * @param errorMessage of the <code>Exception</code>
     */
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
    
}
