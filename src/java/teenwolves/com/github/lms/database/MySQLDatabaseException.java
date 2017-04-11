/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.database;

/**
 * <code>MySQLDatabaseException</code> is thrown when a Database specific
 * Exception occurs in a <code>MySQLDatabase</code> method.
 * @author https://github.com/teenwolves
 * @version 1.0
 * @see MySQLDatabase
 */
public class MySQLDatabaseException extends Exception{
    private final MySQLDatabaseError databaseError;

    /**
     * Class constructor specifying the reason for an <code>Exception</code>
     * to occur as an <code>Enum</code> in <code>MySQLDatabaseError</code>
     * @param databaseError specifies reason for the exception to occur
     * @see MySQLDatabaseError
     */
    public MySQLDatabaseException(MySQLDatabaseError databaseError) {
        this.databaseError = databaseError;
    }

    /**
     * This method gets the reason for the <code>Exception</code> to occur
     * @return a <code>MySQLDatabaseError</code>
     * @see MySQLDatabaseError
     */
    public MySQLDatabaseError getDatabaseError() {
        return databaseError;
    }
    
}
