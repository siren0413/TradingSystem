package com.vin.trading_system.message;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.vin.trading_system.message.builder.JMSMessageBuilder;
import com.vin.trading_system.message.builder.JMSMessageBuilderAdapter;
import com.vin.trading_system.message.cache.MessageConfigEntry;
import com.vin.trading_system.message.initialize.MessageConfiguration;
import com.vin.trading_system.message.initialize.MessageHandler;
import com.vin.trading_system.message.initialize.MessageListener;
import com.vin.trading_system.message.initialize.MessagePublisher;
import com.vin.trading_system.message.parser.MessageParser;
import com.vin.trading_system.message.parser.SmartMessageParser;

public class App {
	public static void main(String[] args) throws InterruptedException, JMSException {
		MessageConfiguration configuration = new MessageConfiguration();
		List<MessageConfigEntry> list = configuration.loadMessageSenderConfiguration("message-config.xml");
		// System.out.println(list.get(0).getSubject());

		MessagePublisher publisher = configuration.createMessageSender(list.get(0));
		JMSMessageBuilder msgBuilder = new JMSMessageBuilderAdapter(publisher);

		msgBuilder.createTextMessage();
		TextMessage textmsg = msgBuilder.addToTextMessage("fdsfldsjflds");

		List<MessageConfigEntry> list2 = configuration.loadMessageReceiverConfiguration("message-config.xml");
		MessageListener listener = configuration.createMessageReceiver(list2.get(0), new MyMessageHandler());
		
		listener.start();
		publisher.start();
		
		publisher.publishTextMessage(textmsg);

		// for(int i = 0 ; i < 100; i ++) {
		// Thread.sleep(1000);
		// Logger.getLogger(App.class).info("dsalfjdfkjlasldhfklashdf");
		// }
		
		publisher.close();
		listener.close();
	}
}

class MyMessageHandler implements MessageHandler {

	private final Logger LOGGER = Logger.getLogger(MessageListener.class);

	public void onMessage(Message message) {

		LOGGER.debug("receive message:" + message);
		MessageParser parser = new SmartMessageParser(message);

		System.out.println(message);
		System.out.println(parser.getMessageBlock());
		System.out.println(parser.getMessageType());

	}

}