/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

/**
 *
 * @author haonguyen
 */
public class ParseXML {
    
  private DocumentBuilder builder;
  private Document document;
  
  public ParseXML(String xmlData) throws Exception
  {
    builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    ByteArrayInputStream stream = new ByteArrayInputStream(xmlData.getBytes());
    document = builder.parse(stream);
  }
  
  public ParseXML(File file) throws Exception
  {
    builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    document = builder.parse(new FileInputStream(file));
  }
  
  public String getValue(String xpathExpression) throws Exception
  {
    XPathFactory xPathfactory = XPathFactory.newInstance();
    XPath xpath = xPathfactory.newXPath();
    XPathExpression expr = xpath.compile(xpathExpression);
    return (String)expr.evaluate(document, XPathConstants.STRING);
  }
  
  public Node getNode(String xpathExpression) throws Exception
  {
    XPathFactory xPathfactory = XPathFactory.newInstance();
    XPath xpath = xPathfactory.newXPath();
    XPathExpression expr = xpath.compile(xpathExpression);
    return (Node)expr.evaluate(document, XPathConstants.NODE);
  }
}
