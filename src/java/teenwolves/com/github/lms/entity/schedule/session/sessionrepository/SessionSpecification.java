/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.entity.schedule.session.sessionrepository;

import teenwolves.com.github.lms.entity.schedule.session.Session;

/**
 *
 * @author Sudarshana Panditha
 */
public interface SessionSpecification {
    public boolean specified(Session session);
}
