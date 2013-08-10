package com.vin.trading_system.message.cache;

public class MessageConfigEntry {

	private String name;
	private String network;
	private String subject;

	
	public MessageConfigEntry() {
		super();
	}
	public MessageConfigEntry(String name, String network, String subject) {
		super();
		this.name = name;
		this.network = network;
		this.subject = subject;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	
	
	
	
	
}
