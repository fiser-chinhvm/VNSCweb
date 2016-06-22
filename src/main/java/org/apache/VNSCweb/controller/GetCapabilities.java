/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.controller;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.VNSCweb.model.Element;
import org.apache.VNSCweb.model.GroupElement;


/**
 *
 * @author haonguyen
 */
public class GetCapabilities {
    public GetCapabilities(){
    }
    public GroupElement GetCapabilitiesRequest() throws JAXBException{
        JAXBContext ctx = JAXBContext.newInstance(GroupElement.class);
       String[] a1 = {"2.0.2","2.0.0","0.7.2"};
       String[] a2 ={"application/xml"};
        Element a = new Element();
        a.setVersion(a1);
        Element a3 = new Element();
        a3.setOutputFormat(a2);
        GroupElement b = new GroupElement();
        b.setAcceptVersions(a);
        b.setAcceptFormats(a3);
       Marshaller m = ctx.createMarshaller();
       m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
       m.marshal(b, System.out); 
       return b;
    }
    public static void main(String[] args) throws JAXBException {
        GetCapabilities a = new GetCapabilities();
        a.GetCapabilitiesRequest();
    }

}
