package teenwolves.com.github.lms.database;

import java.sql.ResultSet;

/**
 * <code>MySQLDatabase</code> is an interface for a MySQL database
 * to create specific implementations
 * 
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see MySQLDatabaseException
 */
public interface MySQLDatabase {
    /**
     * This method is an interface to connect to a MySQL Database.
     * @throws MySQLDatabaseException 
     */
    public void connect() throws MySQLDatabaseException;
    
    /**
     * This method is an interface for retrieving data from a MySQL Database.
     * @param query specifies the database objects to retrieve
     * @return a <code>ResultSet</code> of fetched data
     * @throws MySQLDatabaseException 
     */
    public ResultSet select(String query) throws MySQLDatabaseException;
    
    /**
     * This method is an interface for executing MySQL Database operations.
     * For example, insert, update, delete and etc.
     * @param query specifies the operation to execute
     * @return the number of database objects affected. For example,
     *         the number of affected table rows.
     * @throws MySQLDatabaseException 
     */
    public int execute(String query) throws MySQLDatabaseException;
    
    /**
     * This method is an interface for closing a MySQL Database connection.
     * @throws MySQLDatabaseException 
     */
    public void close() throws MySQLDatabaseException;
}
