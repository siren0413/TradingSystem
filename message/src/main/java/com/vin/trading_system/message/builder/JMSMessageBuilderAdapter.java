package com.vin.trading_system.message.builder;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.vin.trading_system.message.initialize.MessageListener;
import com.vin.trading_system.message.initialize.MessagePublisher;

public class JMSMessageBuilderAdapter implements JMSMessageBuilder {
	
	private Message message;
	private MessagePublisher publisher;
	
	public JMSMessageBuilderAdapter() {
		super();
	}

	public JMSMessageBuilderAdapter(MessagePublisher publisher) {
		super();
		this.publisher = publisher;
	}

	public Message createMapMessage() {
		if(publisher == null) {
			//TODO
		}
		Session session = publisher.getSession();
		try {
			message = session.createMapMessage();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}

	public Message createTextMessage() {
		if(publisher == null) {
			//TODO
		}
		Session session = publisher.getSession();
		try {
			message = session.createTextMessage();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return message;
	}

	public MapMessage addToMapMessage(String key, String value) {
		if(!(message instanceof MapMessage)) {
			//TODO
		}
		try {
			((MapMessage)message).setString(key, value);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (MapMessage)message;
	}

	public TextMessage addToTextMessage(String text) {
		if(!(message instanceof TextMessage)) {
			//TODO
		}
		try {
			((TextMessage)message).setText(text);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (TextMessage)message;
	}

	public Message clearMessageBody() {
		try {
			message.clearBody();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return message;
	}

}
