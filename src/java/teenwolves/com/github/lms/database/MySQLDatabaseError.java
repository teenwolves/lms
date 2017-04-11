package teenwolves.com.github.lms.database;

/**
 * <code>MySQLDatabaseError</code> is an <code>enum</code>,
 * which is a collection of Errors that occurs in a MySQLDatabase
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see MySQLDatabase
 */
public enum MySQLDatabaseError{
    CONNECTION_ERROR("Connection Error"), SQL_ERROR("SQL Error"),TECHNICAL_ERROR("Technical Error");
    
    private final String message;

    private MySQLDatabaseError(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
    
}
