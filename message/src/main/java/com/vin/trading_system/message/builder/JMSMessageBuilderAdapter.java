package com.vin.trading_system.message.builder;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.vin.trading_system.message.initialize.MessageListener;
import com.vin.trading_system.message.initialize.MessagePublisher;

public class JMSMessageBuilderAdapter implements JMSMessageBuilder {
	
	protected Message message;
	protected MessagePublisher publisher;
	protected final Logger LOGGER = Logger.getLogger(MessageListener.class);
	
	public JMSMessageBuilderAdapter() {
		super();
	}

	public JMSMessageBuilderAdapter(MessagePublisher publisher) {
		super();
		this.publisher = publisher;
	}

	public Message createMapMessage() {
		if(publisher == null) {
			LOGGER.error("Unable to create map-message --> publisher == null!");
			return null;
		}
		Session session = publisher.getSession();
		try {
			message = session.createMapMessage();
		} catch (JMSException e) {
			LOGGER.error("Unable to create map-message!",e);
		}
		return message;
	}

	public Message createTextMessage() {
		if(publisher == null) {
			LOGGER.error("Unable to create text-message --> publisher == null!");
			return null;
		}
		Session session = publisher.getSession();
		try {
			message = session.createTextMessage();
		} catch (JMSException e) {
			LOGGER.error("Unable to create text-message!",e);
		}
		
		return message;
	}

	public MapMessage addToMapMessage(String key, String value) {
		if(!(message instanceof MapMessage)) {
			LOGGER.error("Unable to add to map-message --> message type != MAP !");
			return null;
		}
		try {
			((MapMessage)message).setString(key, value);
		} catch (JMSException e) {
			LOGGER.error("Unable to add to map-message!",e);
		}
		
		return (MapMessage)message;
	}

	public TextMessage addToTextMessage(String text) {
		if(!(message instanceof TextMessage)) {
			LOGGER.error("Unable to add to text-message --> message type != TEXT !");
			return null;
		}
		try {
			((TextMessage)message).setText(text);
		} catch (JMSException e) {
			LOGGER.error("Unable to add to text-message!",e);
		}
		
		return (TextMessage)message;
	}

	public Message clearMessageBody() {
		try {
			message.clearBody();
		} catch (JMSException e) {
			LOGGER.error("Unable to clear message body!",e);
		}
		return message;
	}

}
