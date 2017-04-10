package teenwolves.com.github.lms.database.mysql;

import teenwolves.com.github.lms.database.Database;
import teenwolves.com.github.lms.database.DatabaseException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * <code>MySQLDatabase</code> is a class which implements the
 * <code>Database</code> interface to connect to a MySQL database
 * and carry out database operations on it.
 * 
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see Database
 */
public class MySQLDatabase implements Database{
    
    private Connection connection;
    private Statement statement;
    private final String driver;
    private final String url;
    private final String user;
    private final String password;

    /**
     * Class constructor which specifies the driver, database url, the user
     * and the password to the database
     * @param driver to connect to the MySQL database
     * @param url of the MySQL database
     * @param user of the MySQL database
     * @param password of the <code>user</code>
     */
    public MySQLDatabase(String driver, String url, String user, String password) {
        this.driver = driver;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    /**
     * Implemented <code>connect()</code> method of the <code>Database</code>
     * interface.
     * @throws DatabaseException 
     * @see Database
     */
    @Override
    public void connect() throws DatabaseException {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            throw new DatabaseException("Database driver Error");
        } catch (SQLException ex) {
            throw new DatabaseException("Connection Error");
        }
    }

    /**
     * Implemented <code>select(String query)</code> method of the <code>Database</code>
     * interface.
     * @param query is the SQL select statement to query
     * @return a <code>List</code> of <code>Object</code> arrays in which each
     * array corresponds to a row in a MySQL table 
     * @throws DatabaseException
     * @see Database
     */
    @Override
    public List <Object []> select(String query) throws DatabaseException {
        List <Object []> list = null;
        ResultSet rows = null;
        try {
            rows = statement.executeQuery(query);
            ResultSetMetaData rsmd = rows.getMetaData();
            int columnCount = rsmd.getColumnCount();
            list = new ArrayList<>();
            Object [] row;
            while(rows.next()){
                row = new Object[columnCount];
                for (int i = 1; i <= columnCount; i++) {
                    row[i-1] = rows.getString(i);
                }
                list.add(row);
            }
        } catch (SQLException ex) {
            throw new DatabaseException("SQL Error");
        }
        return list;
    }

    /**
     * Implemented <code>execute(String query)</code> method of the <code>Database</code>
     * interface.
     * @param query is the SQL insert, update or delete statement to execute 
     * @return the number of affected rows by executing <code>query</code>
     * @throws DatabaseException
     * @see Database
     */
    @Override
    public int execute(String query) throws DatabaseException {
        int affectedRows = -1;
        try {
            affectedRows = statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new DatabaseException("SQL Error");
        }
        
        return affectedRows;
    }

    /**
     * Implemented <code>close()</code> method of the <code>Database</code>
     * interface.
     * @throws DatabaseException 
     */
    @Override
    public void close() throws DatabaseException {
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new DatabaseException("Connection Error");
        }
    }
    
}
