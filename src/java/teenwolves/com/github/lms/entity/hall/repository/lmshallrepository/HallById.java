/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.hall.repository.lmshallrepository;

import teenwolves.com.github.lms.entity.hall.Hall;
import teenwolves.com.github.lms.entity.hall.repository.HallSpecification;

/**
 *
 * @author Sudarshana Panditha
 */
public class HallById implements HallSpecification{
    private final int hallId;

    public HallById(int hallId) {
        this.hallId = hallId;
    }
    
    @Override
    public boolean specified(Hall hall) {
        return hallId == hall.getId();
    }
    
}
