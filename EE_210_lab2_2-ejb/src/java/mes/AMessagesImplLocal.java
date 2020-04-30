/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.security.InvalidParameterException;
import javax.ejb.Local;

/**
 *
 * @author user
 */
@Local
public interface AMessagesImplLocal {

    public String getMessage(String user, int index) throws InvalidParameterException;

    public boolean addMessage(String user, String message);

    public String[] getMessageList(String user);
}
