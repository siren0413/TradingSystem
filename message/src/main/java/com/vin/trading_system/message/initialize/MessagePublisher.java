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
		} catch (JMSException e) {
			LOGGER.error("Unable to start connection!",e);
		}
	}

	public void stop() {
		try {
			connection.stop();
		} catch (JMSException e) {
			LOGGER.error("Unable to start connection!",e);
		}
	}

	public MessageProducer getProducer() {
		return producer;
	}
	public Session getSession() {
		return session;
	}

}
