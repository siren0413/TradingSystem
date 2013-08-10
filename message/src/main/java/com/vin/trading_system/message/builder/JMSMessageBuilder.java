package com.vin.trading_system.message.builder;

import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.TextMessage;


public interface JMSMessageBuilder {

	public Message createMapMessage();
	public Message createTextMessage();
	public MapMessage addToMapMessage(String key, String value);
	public TextMessage addToTextMessage(String text);
	public Message clearMessageBody();
	
}
