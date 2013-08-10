package com.vin.trading_system.message.initialize;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import com.vin.trading_system.message.cache.MessageType;

public class MessagePublisher {

	private final MessageProducer producer;
	private final Session session;
	private final Connection connection;

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
			
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void publishMapMessage(MapMessage msg) {

		try {
			
			msg.setJMSType(MessageType.MAP.toString());
			producer.send(msg);
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

	public MessageProducer getProducer() {
		return producer;
	}
	public Session getSession() {
		return session;
	}

}
