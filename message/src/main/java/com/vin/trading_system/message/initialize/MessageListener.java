package com.vin.trading_system.message.initialize;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.Session;

import org.apache.log4j.Logger;

public class MessageListener {

	private final MessageConsumer consumer;
	private final Session session;
	private final Connection connection;
	private final MessageHandler messageHandler;
	private final Logger LOGGER = Logger.getLogger(MessageListener.class);
	
	
	public MessageListener(MessageConsumer consumer, Session session, Connection connection, MessageHandler messageHandler) {
		super();
		this.consumer = consumer;
		this.session = session;
		this.connection = connection;
		this.messageHandler = messageHandler;
		try {
			consumer.setMessageListener(messageHandler);
		} catch (JMSException e) {
			LOGGER.error("Unable to set message listener for receiver!",e);
		}
	}
	
	public void start() {
		try {
			connection.start();
			LOGGER.info("Starting message listener...");
		} catch (JMSException e) {
			LOGGER.error("Unable to start connection!",e);
		}
	}
	
	public void stop() {
		try {
			connection.stop();
			LOGGER.info("Stopping message listener...");
		} catch (JMSException e) {
			LOGGER.error("Unable to stop connection!",e);
		}
	}
	
	public void close() {
		try {
			connection.close();
			LOGGER.info("Closing message listener...");
		} catch (JMSException e) {
			LOGGER.error("Unable to close connection!",e);
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

	public Connection getConnection() {
		return connection;
	}
	
	
	
	
	
}
