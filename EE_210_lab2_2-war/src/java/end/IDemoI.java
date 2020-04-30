/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package end;

import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import mes.AMessagesImplLocal;

/**
 *
 * @author user
 */
@WebService(serviceName = "IDemoI")
public class IDemoI implements IDemo{

    @EJB
    AMessagesImplLocal ejb;

    @WebMethod(operationName = "add")
    @Override
    public boolean add(@WebParam(name = "user") String user, @WebParam(name = "message") String message) {
        return ejb.addMessage(user, message);
    }

    @WebMethod(operationName = "getMessage")
    @Override
    public String getMessage(@WebParam(name = "user") String user, @WebParam(name = "index") int index) {
        String message="";
        try{
        message = ejb.getMessage(user, index);
        }catch(EJBException ex){
           message="<span  style=\"text-align: left; color:#F00000;\">Внимание! Сообщения с таким индексом нет.</span>"; 
        }
        return message;
    }

    @WebMethod(operationName = "getAllMessage")
    @Override
    public String[] getAllMessage(@WebParam(name = "user") String user) {
        return ejb.getMessageList(user);
    }
}
