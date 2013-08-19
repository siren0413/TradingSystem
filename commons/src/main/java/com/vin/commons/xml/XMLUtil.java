package com.vin.commons.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class XMLUtil {
	
	public static Document loadXMLFromClassPath(String fileName) throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		InputStream in = XMLUtil.class.getClassLoader().getResourceAsStream(fileName);
		return sb.build(in);
	}
	
	public static Document loadXMLFromAbsolutePath(String filePath) throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		return sb.build(filePath);
	}
	
	public static Document loadXMLFromURL(String URL) throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		return sb.build(URL);
	}
	
	public static Element getRootElement(Document doc) {
		return doc.getRootElement();		
	}
	
	public static int getContentSize(Document doc) {
		return doc.getContentSize();
	}
	
	public static List<Element> getAllElements(Document doc) {
		Element root = getRootElement(doc);
		return root.getChildren();
	}
	
	public static List<Element> getElementsByName(Document doc, String name){
		Element root = getRootElement(doc);
		return root.getChildren(name);
	}
	
	public static Element getSingleElementByName(Document doc, String name) {
		Element root = getRootElement(doc);
		return root.getChild(name);
	}
	
	public static String getElementText(Element element) {
		return element.getText();
	}
	
	public static String getElementAttributeValue(Element element, String attr_name) {
		Attribute attr = element.getAttribute(attr_name);
		return attr.getValue();
	}
	
	public static Element getChildByName(Element element, String name) {
		return element.getChild(name);
	}
	
	public static List<Element> getChildrenByName(Element element, String name){
		return element.getChildren(name);
	}
	
	public static Element getParent(Element element) {
		return element.getParentElement();
	}
	
	
	
	
	
	
}
