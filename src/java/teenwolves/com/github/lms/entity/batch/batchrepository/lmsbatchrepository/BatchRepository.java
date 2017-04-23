/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.batch.batchrepository.lmsbatchrepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import teenwolves.com.github.lms.database.MySQLDatabase;
import teenwolves.com.github.lms.database.MySQLDatabaseException;
import teenwolves.com.github.lms.database.mysql.LmsMySQLDatabase;
import teenwolves.com.github.lms.entity.batch.Batch;
import teenwolves.com.github.lms.entity.batch.batchrepository.AbstractBatchRepository;
import teenwolves.com.github.lms.entity.batch.batchrepository.BatchSpecification;
import teenwolves.com.github.lms.repository.RepositoryError;
import teenwolves.com.github.lms.repository.RepositoryException;
import teenwolves.com.github.lms.repository.RepositoryUtility;

/**
 *
 * @author Sudarshana Panditha
 */
public class BatchRepository implements AbstractBatchRepository{
    
    private MySQLDatabase database;

    public BatchRepository(MySQLDatabase database) {
        this.database = database;
    }

    @Override
    public void addBatch(Batch batch) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("INSERT INTO batch VALUES('");
        query.append(batch.getId());
        query.append("', ");
        query.append(batch.getCourseId());
        query.append(")");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Batch is not added");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
        
    }

    @Override
    public void deleteBatch(Batch batch) throws RepositoryException {
        StringBuilder query = new StringBuilder();
        query.append("DELETE FROM batch WHERE id='");
        query.append(batch.getId());
        query.append("'");
        
        RepositoryError error = RepositoryError.UNSUCCESSFUL_EXECUTION;
        error.setErrorMessage("Batch is not deleted.");
        
        RepositoryUtility.executeQuery(database, query.toString(), error);
    }

    @Override
    public void updateBatch(Batch batch) throws RepositoryException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Batch> query(BatchSpecification specification) throws RepositoryException {
        List<Batch> batches = null;
        String query = "SELECT * FROM batch";
        
        try {
            // Connecting to the database
            database.connect();
            
            // Retrieving data
            ResultSet rows = database.select(query);
            
            //Creating the hall list
            Batch batch = null;
            while(rows.next()){
                batch = new Batch();
                batch.setId(rows.getString("id"));
                batch.setCourseId(rows.getInt("courseid"));
                
                if(specification.specified(batch)){
                    if(batches == null){
                        batches = new ArrayList<>();
                    }
                    batches.add(batch);
                }
            }
        } catch (MySQLDatabaseException ex) {
            throw new RepositoryException(RepositoryError.TECHNICAL_ERROR);
        } catch (SQLException ex) {
            throw new RepositoryException(RepositoryError.BATCHES_NOT_FOUND);
        }
        
        if(batches == null){
            throw new RepositoryException(RepositoryError.BATCHES_NOT_FOUND);
        }
        return batches;
    }
    
}
