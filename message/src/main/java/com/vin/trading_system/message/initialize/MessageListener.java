package com.vin.trading_system.message.initialize;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

public class MessageListener {

	private final MessageConsumer consumer;
	private final Session session;
	private final Connection connection;
	private final MessageHandler messageHandler;
	
	
	public MessageListener(MessageConsumer consumer, Session session, Connection connection, MessageHandler messageHandler) {
		super();
		this.consumer = consumer;
		this.session = session;
		this.connection = connection;
		this.messageHandler = messageHandler;
		try {
			consumer.setMessageListener(messageHandler);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void start() {
		try {
			connection.start();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stop() {
		try {
			connection.stop();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public MessageConsumer getConsumer() {
		return consumer;
	}

	public Session getSession() {
		return session;
	}

	public javax.jms.MessageListener getMessageHandler() {
		return messageHandler;
	}
	
	
	
	
}
