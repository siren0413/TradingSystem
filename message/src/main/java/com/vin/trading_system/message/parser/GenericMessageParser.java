package com.vin.trading_system.message.parser;


import java.util.Enumeration;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;

import com.vin.trading_system.message.cache.MessageType;

public class GenericMessageParser {

	protected Message message;
	
	public GenericMessageParser(Message message) {
		super();
		this.message = message;
	}

	public String getMessageBlock() {
		if(message == null) {
			//TODO
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
}
