package teenwolves.com.github.lms.database;

import java.util.List;

/**
 * <code>Database</code> is an interface for specific database
 * implementations.
 * 
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see DatabaseException
 */
public interface Database {
    /**
     * This method is an interface to connect to a specific Database.
     * @throws DatabaseException 
     */
    public void connect() throws DatabaseException;
    
    /**
     * This method is an interface for retrieving data from a Database.
     * @param query specifies the database objects to retrieve
     * @return a <code>List</code> of <code>Object</code> arrays which
     *         represents a database object. For example, a table row.
     * @throws DatabaseException 
     */
    public List <Object []> select(String query) throws DatabaseException;
    
    /**
     * This method is an interface for executing database operations.
     * For example, insert, update, delete and etc.
     * @param query specifies the operation to execute
     * @return the number of database objects affected. For example,
     *         the number of affected table rows.
     * @throws DatabaseException 
     */
    public int execute(String query) throws DatabaseException;
    
    /**
     * This method is an interface for closing a Database connection.
     * @throws DatabaseException 
     */
    public void close() throws DatabaseException;
}
