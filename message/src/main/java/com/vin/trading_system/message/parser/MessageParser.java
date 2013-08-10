package com.vin.trading_system.message.parser;

import com.vin.trading_system.message.cache.MessageField;


public interface MessageParser {

	
	public String getMessageBlock();
	public MessageField getMessageField(String key);
}
