/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.repository;

import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;

/**
 *
 * @author Sudarshana Panditha
 */
public class RepositoryUtility {
    public static void executeQuery(MySQLDatabase database,String query,RepositoryError error)throws RepositoryException{
        try {
            // Connecting to the database
            database.connect();
            
            // Executing the query
            int affectedRows = database.execute(query);
            
            if(affectedRows == 0){
                throw new RepositoryException(error);
            }
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        }
    }
}
