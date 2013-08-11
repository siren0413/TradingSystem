package com.vin.trading_system.message.parser;


import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

import org.apache.log4j.Logger;

import com.vin.trading_system.message.cache.MessageType;
import com.vin.trading_system.message.initialize.MessageListener;

public class GenericMessageParser {

	protected Message message;
	protected final Logger LOGGER = Logger.getLogger(MessageListener.class);
	
	public GenericMessageParser(Message message) {
		super();
		this.message = message;
	}

	@SuppressWarnings("unchecked")
	public String getMessageBlock() {
		if(message == null) {
			LOGGER.error("Unable to parse message --> message == null!");
			return null;
		}
		
		try {
			if(message.getJMSType().equals(MessageType.TEXT.toString())) {
				TextMessage msg = (TextMessage)message;
				return msg.getText();
			}else if (message.getJMSType().equals(MessageType.MAP.toString())){
				MapMessage msg = (MapMessage)message;
				Enumeration<String> en = msg.getMapNames();
				StringBuilder sb = new StringBuilder();
				while(en.hasMoreElements()) {
					String key = en.nextElement();
					String value = msg.getString(key);
					sb.append(key+"="+value+" ");
				}
				return sb.toString();
			}
		} catch (JMSException e) {
			LOGGER.error("Unable to parse message["+message+"]",e);
		}
		
		return null;
	}
	
	
	
}
