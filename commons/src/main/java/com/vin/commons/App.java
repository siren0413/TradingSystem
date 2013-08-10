package com.vin.commons;

import java.io.IOException;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

import com.vin.commons.xml.XMLUtil;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws JDOMException, IOException
    {
       Document doc = XMLUtil.loadXMLFromClassPath("message-config.xml");
       XMLUtil.loadXMLFromAbsolutePath("C:\\Users\\Mao\\workspace\\commons\\src\\main\\resources\\message-config.xml");
       XMLUtil.loadXMLFromURL("C:\\Users\\Mao\\workspace\\commons\\src\\main\\resources\\message-config.xml");
       
       Element ele = XMLUtil.getSingleElementByName(doc, "network");
       System.out.println(ele);
       
    }
}
