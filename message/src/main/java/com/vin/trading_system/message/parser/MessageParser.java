package com.vin.trading_system.message.parser;

import com.vin.trading_system.message.cache.MessageBlock;
import com.vin.trading_system.message.cache.MessageField;


public interface MessageParser {

	
	public MessageBlock getMessageBlock();
	public MessageField getMessageField(String key);
	public String getMessageType();
}
