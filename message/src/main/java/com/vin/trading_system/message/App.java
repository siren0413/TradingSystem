package com.vin.trading_system.message;

import java.util.Enumeration;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.TextMessage;

import com.vin.trading_system.message.builder.JMSMessageBuilder;
import com.vin.trading_system.message.builder.JMSMessageBuilderAdapter;
import com.vin.trading_system.message.cache.MessageConfigEntry;
import com.vin.trading_system.message.initialize.MessageConfiguration;
import com.vin.trading_system.message.initialize.MessagePublisher;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException
    {
        MessageConfiguration configuration = new MessageConfiguration();
        List<MessageConfigEntry> list = configuration.loadMessageSenderConfiguration("message-config.xml");
        System.out.println(list.get(0).getSubject());
        
        MessagePublisher publisher = configuration.createMessageSender(list.get(0));
        JMSMessageBuilder msgBuilder = new JMSMessageBuilderAdapter(publisher);


        msgBuilder.createTextMessage();
        TextMessage textmsg = msgBuilder.addToTextMessage("fdsfldsjflds");
        publisher.publishTextMessage(textmsg);

        
        
    }
}
