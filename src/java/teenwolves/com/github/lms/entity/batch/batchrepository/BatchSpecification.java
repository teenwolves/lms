/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.batch.batchrepository;

import teenwolves.com.github.lms.entity.batch.Batch;

/**
 *
 * @author Sudarshana Panditha
 */
public interface BatchSpecification {
    public boolean specified(Batch batch);
}
