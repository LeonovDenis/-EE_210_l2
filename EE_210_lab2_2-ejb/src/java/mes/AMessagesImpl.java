/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mes;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;

/**
 *
 * @author user
 */
@Stateless
public class AMessagesImpl extends AMessages implements AMessagesImplLocal {

    @Override
    public String getMessage(String user, int index) throws InvalidParameterException {
        List<String> usermess = messages.get(user);
        if (usermess == null) {
            throw new InvalidParameterException("Ошибка: такого пользователя нет");
        }
        if (index < 0 || index >= usermess.size()) {
            throw new InvalidParameterException("Ошибка: неправильный индекс сообщения");
        }
        return messages.get(user).get(index);
    }

    @Override
    public boolean addMessage(String user, String message) {
        if (user.matches("\\w{2,8}") && message.matches("[\\s\\w\\pPunct]{1,255}")) {
            if (messages.containsKey(user)) {
                messages.get(user).add(message);
            } else {
                List<String> list = new ArrayList<>();
                list.add(message);
                messages.put(user, list);
            }
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String[] getMessageList(String user) {
        if (messages.containsKey(user)) {
            String[] array = messages.get(user).toArray(new String[0]);
            return array;
        }
        return null;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
