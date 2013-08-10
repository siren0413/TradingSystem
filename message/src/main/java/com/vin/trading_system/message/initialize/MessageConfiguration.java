package com.vin.trading_system.message.initialize;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQTopic;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.vin.commons.xml.XMLUtil;
import com.vin.trading_system.message.cache.MessageConfigEntry;

public class MessageConfiguration {

	public List<MessageConfigEntry> loadMessageReceiverConfiguration(String filename) {

		try {

			Document doc = XMLUtil.loadXMLFromClassPath(filename);
			Element receivers = XMLUtil.getSingleElementByName(doc, "receivers");
			List<Element> receiverList = XMLUtil.getChildrenByName(receivers, "receiver");
			List<MessageConfigEntry> messageConfigEntryList = new ArrayList<MessageConfigEntry>();
			for (Element element : receiverList) {
				MessageConfigEntry entry = new MessageConfigEntry();
				entry.setName(XMLUtil.getChildByName(element, "name").getText());
				entry.setNetwork(XMLUtil.getChildByName(element, "network").getText());
				entry.setSubject(XMLUtil.getChildByName(element, "subject").getText());
				messageConfigEntryList.add(entry);
			}

			return messageConfigEntryList;

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public List<MessageConfigEntry> loadMessageSenderConfiguration(String filename) {

		try {
			Document doc = XMLUtil.loadXMLFromClassPath(filename);
			Element senders = XMLUtil.getSingleElementByName(doc, "senders");
			List<Element> senderList = XMLUtil.getChildrenByName(senders, "sender");
			List<MessageConfigEntry> messageConfigEntryList = new ArrayList<MessageConfigEntry>();
			for (Element element : senderList) {
				MessageConfigEntry entry = new MessageConfigEntry();
				entry.setName(XMLUtil.getChildByName(element, "name").getText());
				entry.setNetwork(XMLUtil.getChildByName(element, "network").getText());
				entry.setSubject(XMLUtil.getChildByName(element, "subject").getText());
				messageConfigEntryList.add(entry);
			}

			return messageConfigEntryList;

		} catch (JDOMException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return null;
	}

	public MessageListener createMessageReceiver(MessageConfigEntry config, MessageHandler handler) {
		String name = config.getName();
		String network = config.getNetwork();
		String subject = config.getSubject();
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(network);

		Connection connection = null;
		try {
			connection = factory.createConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		// Create Topic
		Topic topic = new ActiveMQTopic(subject);
		try {
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessageListener listener = new MessageListener(session.createConsumer(topic), session,connection, handler);
			return listener;
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return null;

	}

	public MessagePublisher createMessageSender(MessageConfigEntry config) {
		String name = config.getName();
		String network = config.getNetwork();
		String subject = config.getSubject();
		ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory(network);

		Connection connection = null;
		try {
			connection = factory.createConnection();
		} catch (JMSException e) {
			e.printStackTrace();
		}

		// Create Topic
		Topic topic = new ActiveMQTopic(subject);
		try {
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			MessagePublisher publisher = new MessagePublisher(session.createProducer(topic), session, connection);
			return publisher;
		} catch (JMSException e) {
			e.printStackTrace();
		}

		return null;
	}

}
