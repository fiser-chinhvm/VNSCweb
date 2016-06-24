/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb;

import chinh.vnsc.data.Library;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.xml.bind.JAXB;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.apache.sis.metadata.iso.citation.DefaultCitation;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.xml.XML;
import org.opengis.metadata.ApplicationSchemaInformation;
import org.opengis.metadata.Identifier;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.MetadataExtensionInformation;
import org.opengis.metadata.MetadataScope;
import org.opengis.metadata.PortrayalCatalogueReference;
import org.opengis.metadata.acquisition.AcquisitionInformation;
import org.opengis.metadata.citation.Citation;
import org.opengis.metadata.citation.CitationDate;
import org.opengis.metadata.citation.OnlineResource;
import org.opengis.metadata.citation.Responsibility;
import org.opengis.metadata.constraint.Constraints;
import org.opengis.metadata.content.ContentInformation;
import org.opengis.metadata.distribution.Distribution;
import org.opengis.metadata.identification.CharacterSet;
import org.opengis.metadata.identification.Identification;
import org.opengis.metadata.lineage.Lineage;
import org.opengis.metadata.maintenance.MaintenanceInformation;
import org.opengis.metadata.maintenance.ScopeCode;
import org.opengis.metadata.quality.DataQuality;
import org.opengis.metadata.spatial.SpatialRepresentation;
import org.opengis.referencing.ReferenceSystem;
import org.opengis.util.FactoryException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author chinhvm
 */
public class GetRecord {
    String constrain;
    Library lib;
    Metadata result;
    
    public GetRecord() throws IOException, DataStoreException, JAXBException, ParseException, FactoryException{
        lib = new Library();
    }
    
    
    public String search(String query) throws JAXBException{
        if(query.equals("1")){
            return XML.marshal(lib.getMetadata(0)).toString();
        } else if(query.equals("2")){
            return XML.marshal(lib.getMetadata(0)).toString();
        } else{
            return "empty";
        }
        
        
    }
    
    public static void main(String[] args) throws IOException, DataStoreException, JAXBException, ParseException, FactoryException, ParserConfigurationException, SAXException {
        GetRecord a = new GetRecord();
        Library test = new Library();
        String check = "attribute";
        
        String xmlRecords = XML.marshal(test.getMetadata(0));
        InputSource is = new InputSource();
    
        is.setCharacterStream(new StringReader(xmlRecords));
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        Document doc = dBuilder.parse(is);
        
        Node node = doc.getElementById("gmd:identifier");
        System.out.println(xmlRecords);
        System.out.println(node.getNodeName());
    }
}
