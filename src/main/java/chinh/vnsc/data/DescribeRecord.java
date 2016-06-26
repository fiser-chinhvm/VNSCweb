    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chinh.vnsc.data;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
/**
 *
 * @author chinhvm
 */
public class DescribeRecord {
    public DescribeRecord(){}
    
    public DOMSource getSource() throws TransformerConfigurationException, TransformerException, ParserConfigurationException{
     DocumentBuilderFactory dbFactory =
         DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder = 
         dbFactory.newDocumentBuilder();
         Document doc = dBuilder.newDocument();
         // root element
        Element rootElement = doc.createElement("DescribeRecord");
        Attr service = doc.createAttribute("service");
        service.setValue("CSW");
        
        Attr xmlns_csw = doc.createAttribute("xmlns:csw");
        xmlns_csw.setValue("http://www.opengis.net/cat/csw/2.0.2");
        rootElement.setAttributeNode(xmlns_csw);
        
        Attr xmlns = doc.createAttribute("xmlns");
        xmlns.setValue("http://www.opengis.net/cat/csw/2.0.2");
        rootElement.setAttributeNode(xmlns);
        
        Attr xmlns_xsi = doc.createAttribute("xmlns:xsi");
        xmlns_xsi.setValue("http://www.w3.org/2001/XMLSchema-instance");
        rootElement.setAttributeNode(xmlns_xsi);
        
        Attr schemaLanguage = doc.createAttribute("schemaLanguage");
        schemaLanguage.setValue("http://www.w3.org/2001/XMLSchema");
        rootElement.setAttributeNode(schemaLanguage);
        
        Attr outputFormat = doc.createAttribute("outputFormat");
        outputFormat.setValue("application/xml");
        rootElement.setAttributeNode(outputFormat);
        
        Attr version = doc.createAttribute("version");
        version.setValue("2.0.2");
        rootElement.setAttributeNode(version);        
        
        doc.appendChild(rootElement);

         //  supercars element
        Element type = doc.createElement("TypeName");
        type.appendChild(doc.createTextNode("Default"));
        rootElement.appendChild(type);

        // write the content into xml file
        TransformerFactory transformerFactory =
        TransformerFactory.newInstance();
        Transformer transformer =
        transformerFactory.newTransformer();
        DOMSource source = new DOMSource(doc);
        
         // Output to console for testing
        StreamResult consoleResult = new StreamResult(System.out);
        transformer.transform(source, consoleResult);
        
        return source;
    }
    
    public static void main(String[] args) throws TransformerException, TransformerConfigurationException, ParserConfigurationException {
        DescribeRecord d = new DescribeRecord();
        System.out.println(d.getSource().toString());
    }
}