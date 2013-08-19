package com.vin.commons;

import java.util.List;

import org.junit.Test;

import com.vin.commons.xml.GenericXMLParser;

public class GenericXMLParserTest {

	private GenericXMLParser parser = new GenericXMLParser("message-config.xml");
	
//	@Ignore
//	public void testInit() {
//		parser = new GenericXMLParser("C:\\Users\\Mao\\Project\\TradingSystem\\commons\\src\\main\\resources\\message-config.xml");
//	}
	
	@Test
	public void testGetElementText() {
		System.out.println(parser.getContentSize());
		List<String> list = parser.getElementText("network");
		System.out.println(list);
	}
	
	
	
	
}
