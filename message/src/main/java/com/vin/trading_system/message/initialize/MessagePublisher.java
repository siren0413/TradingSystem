package com.vin.trading_system.message.initialize;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.vin.trading_system.message.cache.MessageType;

public class MessagePublisher {

	private final MessageProducer producer;
	private final Session session;
	private final Connection connection;
	private final Logger LOGGER = Logger.getLogger(MessageListener.class);

	public MessagePublisher(MessageProducer producer, Session session, Connection connection) {
		super();
		this.producer = producer;
		this.session = session;
		this.connection = connection;
	}

	public void publishTextMessage(TextMessage msg) {

		try {
			msg.setJMSType(MessageType.TEXT.toString());
			producer.send(msg);
			LOGGER.debug("publish message:"+msg);
		} catch (JMSException e) {
			LOGGER.error("Unable to send text message[" + msg + "]", e);
		}

	}

	public void publishMapMessage(MapMessage msg) {

		try {
			msg.setJMSType(MessageType.MAP.toString());
			producer.send(msg);
			LOGGER.debug("publish message:"+msg);
		} catch (JMSException e) {
			LOGGER.error("Unable to send map message[" + msg + "]", e);
		}

	}

	public void start() {
		try {
			connection.start();
			LOGGER.info("Starting message publisher...");
		} catch (JMSException e) {
			LOGGER.error("Unable to start connection!",e);
		}
	}

	public void stop() {
		try {
			connection.stop();
			LOGGER.info("Stopping message publisher...");
		} catch (JMSException e) {
			LOGGER.error("Unable to stop connection!",e);
		}
	}
	
	public void close() {
		try {
			connection.close();
			LOGGER.info("Closing message publisher...");
		} catch (JMSException e) {
			LOGGER.error("Unable to close connection!",e);
		}
	}

	public MessageProducer getProducer() {
		return producer;
	}
	public Session getSession() {
		return session;
	}

	public Connection getConnection() {
		return connection;
	}
	
	

}
