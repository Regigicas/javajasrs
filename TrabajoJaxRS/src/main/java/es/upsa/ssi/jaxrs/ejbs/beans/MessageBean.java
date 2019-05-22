/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.upsa.ssi.jaxrs.ejbs.beans;

import es.upsa.ssi.jarxrs.cdi.qualifiers.LocallyProduced;
import es.upsa.ssi.jarxrs.exceptions.TrabajoJaxRSException;
import es.upsa.ssi.jaxrs.ejbs.Logica;
import es.upsa.ssi.jaxrs.pojos.Item;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.TextMessage;
import javax.json.bind.JsonbBuilder;

/**
 *
 * @author regigicas
 */
@MessageDriven(activationConfig = {
    @ActivationConfigProperty(propertyName = "destinationLookup",       propertyValue = "jms/upsaQueue"),
    @ActivationConfigProperty(propertyName = "destinationType",         propertyValue = "javax.jms.Queue"),
    @ActivationConfigProperty(propertyName = "connectionFactoryLookup", propertyValue = "jms/upsaConnectionFactory")
})

public class MessageBean implements MessageListener
{
    @Inject
    @LocallyProduced
    private Logica dao;
    
    @Resource(name = "jms/queue2")
    private Queue queue2;
    
    @Resource(name = "jms/cf")
    private ConnectionFactory cf;
    
    @Override
    public void onMessage(Message message) 
    {
        try 
        {
            Integer codItem = message.getBody(Integer.class);
            Item item = dao.getItem(codItem);
            
            try (JMSContext context = cf.createContext())
            {
                String replyString = JsonbBuilder.create().toJson(item);
                TextMessage response = context.createTextMessage(replyString);
                response.setJMSCorrelationID(message.getJMSCorrelationID());
                context.createProducer().send(queue2, response);
            }
        }
        catch (JMSException | TrabajoJaxRSException jmsException) 
        {
            Logger.getLogger(MessageBean.class.getName()).log(Level.SEVERE, null, jmsException);
        }   
    }  
}
