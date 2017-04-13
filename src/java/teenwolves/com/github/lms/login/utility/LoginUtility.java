/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teenwolves.com.github.lms.login.utility;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import teenwolves.com.github.lms.repository.RepositoryError;

/**
 * <code>LoginUtility</code> is an utility to support
 * <code>LoginServlet</code>'s operations.
 * @author https://github.com/teenwolves
 * @version 1.0
 */
public class LoginUtility {
    /**
     * 
     * @param servletContext of the application.
     * @param request object corresponding to the client's request.
     * @param response object which is the response sent to the client.
     * @param url where the request and response is dispatched to.
     * @param error
     * @throws ServletException
     * @throws IOException 
     */
    public static void forwardError(ServletContext servletContext, 
            HttpServletRequest request, 
            HttpServletResponse response,
            String url,
            RepositoryError error) throws ServletException, IOException{
        // Setting the message
        request.setAttribute("message", error.getErrorMessage());
        
        // forwarding the request
        servletContext.getRequestDispatcher(url).forward(request, response);
        
    }
}
