/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.controller;

import java.io.File;
import org.w3c.dom.Node;

/**
 *
 * @author haonguyen
 */
public class ReadXML {
     public static void main(String[] args)
  {
    File xml = new File("/home/haonguyen/data/G184220810-LAADS.iso19115");
    //System.out.println(xml);
    try
    { 
      ParseXML parseXML = new ParseXML(xml);
      String name = parseXML.getValue("/MI_Metadata/fileIdentifier/CharacterString");
//      System.out.println("Type: "+parseXML.getValue("/MI_Metadata/fileIdentifier/CharacterString"));
        System.out.println(name);
//              +" "+parseXML.getValue("/gco:CharacterString/gmd:fileIdentifier"));
//      System.out.println("Title: 
//      System.out.println("Format 
//      System.out.println("Modified: 
//      System.out.println("BoudingBox: 
//      Node node = parseXML.getNode("/person");
//      System.out.println("Node name: "+node.getNodeName());
//      System.out.println("Node type: "+node.getNodeType());
//      System.out.println("Child nodes available: "+node.hasChildNodes());
//      System.out.println("hasAttributes: "+node.hasAttributes());
    } catch (Exception e) {
      System.out.println(e.getMessage());
    } 
  }
}
