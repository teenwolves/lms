/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.batch.batchrepository.lmsbatchrepository;

import teenwolves.com.github.lms.entity.batch.Batch;
import teenwolves.com.github.lms.entity.batch.batchrepository.BatchSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public class AllBatches implements BatchSpecification{

    @Override
    public boolean specified(Batch batch) {
        return true;
    }
    
}
