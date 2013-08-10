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
			//TODO
		}
		try {
			if(message.getJMSType().equals(MessageType.TEXT.toString())) {
				//TODO
			}else if (message.getJMSType().equals(MessageType.MAP.toString())){
				MapMessage msg = (MapMessage)message;
				MessageField field = new MessageField(key, msg.getString(key));
				return field;
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
		
	}

	
}
