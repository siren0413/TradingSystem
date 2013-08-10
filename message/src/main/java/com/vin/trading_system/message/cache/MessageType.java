package com.vin.trading_system.message.cache;


public enum MessageType {
	
	TEXT("TEXT"),BYTE("BYTE"),STREAM("STREAM"),MAP("MAP");
	
	private String messageType;
	
	private MessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public String toString() {
		return messageType;
	}	
	
}
