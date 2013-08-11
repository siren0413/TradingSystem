package com.vin.trading_system.message.parser;


import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

import com.vin.trading_system.message.cache.MessageField;
import com.vin.trading_system.message.cache.MessageType;

public class SmartMessageParser extends GenericMessageParser implements MessageParser {

	public SmartMessageParser(Message message) {
		super(message);
	}

	public MessageField getMessageField(String key) {
		if(message == null) {
			LOGGER.error("Unable to parse message --> message == null!");
			return null;
		}
		try {
			if(message.getJMSType().equals(MessageType.TEXT.toString())) {
				LOGGER.warn("Unable to get message field --> message type != TEXT !");
			}else if (message.getJMSType().equals(MessageType.MAP.toString())){
				MapMessage msg = (MapMessage)message;
				MessageField field = new MessageField(key, msg.getString(key));
				return field;
			}
		} catch (JMSException e) {
			LOGGER.error("Unable to parse message["+message+"]",e);
		}
		
		return null;
	}

	
}
