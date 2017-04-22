/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.batch.batchrepository;

import java.util.List;
import teenwolves.com.github.lms.entity.batch.Batch;
import teenwolves.com.github.lms.repository.RepositoryException;

/**
 *
 * @author Sudarshana Panditha
 */
public interface AbstractBatchRepository {
    public void addBatch(Batch batch) throws RepositoryException;
    public void deleteBatch(Batch batch) throws RepositoryException;
    public void updateBatch(Batch batch) throws RepositoryException;
    
    public List<Batch> query(BatchSpecification specification) throws RepositoryException;
}
