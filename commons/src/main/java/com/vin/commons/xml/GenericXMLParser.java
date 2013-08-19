package com.vin.commons.xml;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class GenericXMLParser {

	private final String filepath;
	private Document doc;
	private Logger LOGGER = Logger.getLogger(this.getClass());

	public GenericXMLParser(String filepath) {
		super();
		this.filepath = filepath;
		init();
	}

	private void init() {
		LOGGER.info("initializing GenericXMLParser...");


		// load from class path
		LOGGER.debug("attempt to load XML from class path.");
		try {
			doc = loadXMLFromClassPath(filepath);
		} catch (JDOMException e) {
			LOGGER.debug("Unable to parse XML file[" + filepath + "]");
		} catch (IOException e) {
			LOGGER.debug("Unable to locate XML file[" + filepath + "], make sure the file is in path");
		}
		if (doc != null) {
			LOGGER.info("successfully load XML from classpath[" + filepath + "]");
			return;
		}

		// load from absolute path
		LOGGER.debug("attempt to load XML from absolute path.");
		try {
			doc = loadXMLFromAbsolutePath(filepath);
		} catch (JDOMException e) {
			LOGGER.debug("Unable to parse XML file[" + filepath + "]");
		} catch (IOException e) {
			LOGGER.debug("Unable to locate XML file[" + filepath + "], make sure the file is in path");
		}
		if (doc != null) {
			LOGGER.info("successfully load XML from absolute path[" + filepath + "]");
			return;
		}

		LOGGER.debug("attempt to load XML from URL.");
		try {
			doc = loadXMLFromURL(filepath);
		} catch (JDOMException e) {
			LOGGER.debug("Unable to parse XML file[" + filepath + "]");
		} catch (IOException e) {
			LOGGER.debug("Unable to locate XML file[" + filepath + "], make sure the file is in path");
		}
		if (doc != null) {
			LOGGER.info("successfully load XML from URL[" + filepath + "]");
			return;
		}

		LOGGER.error("fail to load XML[" + filepath + "]");

	}

	private Element getRootElement() {
		return doc.getRootElement();
	}

	public int getContentSize() {
		return doc.getContentSize();
	}

//	private void getAllElements(Element root, List<Element> elements) {
//		for(Element element : root.getChildren()) {
//			elements.add(element);
//			getAllElements(element, elements);
//		}
//	}
	
	private void getAllElementsByTag(String tagName,Element root, List<Element> elements) {
		for(Element element : root.getChildren()) {
			if(element.getName().equals(tagName))
				elements.add(element);
			getAllElementsByTag(tagName,element, elements);
		}
	}
	

//	private List<Element> getElementsByName(String name) {
//		Element root = getRootElement();
//		return root.getChildren(name);
//	}
//
//	private Element getSingleElementByName(String name) {
//		Element root = getRootElement();
//		return root.getChild(name);
//	}

	
	public List<String> getElementText(String tagName) {
		List<Element> elements = new ArrayList<Element>();
		getAllElementsByTag(tagName,getRootElement(), elements);
		List<String> resultList = new ArrayList<String>();
		for (Element element : elements) {
			resultList.add(element.getText());
		}
		return resultList;
	}

	public List<String> getElementAttributeValue(String tagName, String attr_name) {
		List<Element> elements = new ArrayList<Element>();
		getAllElementsByTag(tagName,getRootElement(), elements);
		List<String> resultList = new ArrayList<String>();
		for (Element element : elements) {
			resultList.add(element.getAttributeValue(attr_name));
		}
		return resultList;
	}

	private Document loadXMLFromClassPath(String fileName) throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		InputStream in = XMLUtil.class.getClassLoader().getResourceAsStream(fileName);
		return sb.build(in);
	}

	private Document loadXMLFromAbsolutePath(String filePath) throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		return sb.build(filePath);
	}

	private Document loadXMLFromURL(String URL) throws JDOMException, IOException {
		SAXBuilder sb = new SAXBuilder();
		return sb.build(URL);
	}

}
