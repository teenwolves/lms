/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.database.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseError;
import teenwolves.com.github.lms.database.MySQLDatabaseException;

/**
 * <code>LmsMySQLDatabase</code> is an implementation of the
 * <code>MySQLDatabase</code> to access lms database.
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see MySQLDatabases
 */
public class LmsMySQLDatabase implements MySQLDatabase{
    
    private Connection connection;
    private Statement statement;
    private final String driver;
    private final String url;
    private final String user;
    private final String password;

    public LmsMySQLDatabase() {
        this.driver = "com.mysql.jdbc.Driver";
        this.url = "jdbc:mysql://localhost:3306/lms";
        this.user = "root";
        this.password = "";
    }

    @Override
    public void connect() throws MySQLDatabaseException{
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
        } catch (ClassNotFoundException ex) {
            throw new MySQLDatabaseException(MySQLDatabaseError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            System.out.println("xxx");
            throw new MySQLDatabaseException(MySQLDatabaseError.SQL_ERROR);
        }
    }

    @Override
    public ResultSet select(String query) throws MySQLDatabaseException{
        ResultSet rows = null;
        try {
            rows = statement.executeQuery(query);
        } catch (SQLException ex) {
            System.out.println("xxx");
            throw new MySQLDatabaseException(MySQLDatabaseError.SQL_ERROR);
        }
        
        return rows;
    }

    @Override
    public int execute(String query) throws MySQLDatabaseException{
        int rowsAffected = 0;
        
        try {
            rowsAffected = statement.executeUpdate(query);
        } catch (SQLException ex) {
            throw new MySQLDatabaseException(MySQLDatabaseError.SQL_ERROR);
        }
        
        return rowsAffected;
    }

    @Override
    public void close() throws MySQLDatabaseException{
        try {
            connection.close();
        } catch (SQLException ex) {
            throw new MySQLDatabaseException(MySQLDatabaseError.TECHNICAL_ERROR);
        }
    }
    
}
