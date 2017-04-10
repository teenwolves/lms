package teenwolves.com.github.lms.database;

/**
 * <code>DatabaseException</code> is thrown when a Database specific
 * Exception occurs in a <code>Database</code> method.
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see Database
 */
public class DatabaseException extends Exception{
    private String message;

    /**
     * Class constructor specifying the reason for an <code>Exception</code> to occur.
     * @param message specifies reason for the exception to occur
     */
    public DatabaseException(String message) {
        this.message = message;
    }

    /**
     * @return the reason for the <code>Exception</code> to occur
     */
    public String getMessage() {
        return message;
    }
    
    
}
