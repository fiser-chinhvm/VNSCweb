/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.controller;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import org.apache.VNSCweb.model.SummaryRecord;

/**
 *
 * @author haonguyen
 */
public class Record {

    public static void main(String[] args) throws JAXBException, FileNotFoundException {
        JAXBContext ctx = JAXBContext.newInstance(SummaryRecord.class);
        String[] b = {"imagery", "baseMaps", "earthCover"};
        SummaryRecord a = new SummaryRecord();
        a.setIdentifier(new String[]{"00180e67-b7cf-40a3-861d-b3a09337b195"});
        a.setTitle(new String[]{"Image ..."});
        a.setType("dataset");
        a.setSubject(b);
        a.setFormat(new String[]{"BIL"});
        a.setModified(new String[]{"2016-06-23 00:00:00"});
        a.setAbstracts(new String[]{"product 1 individual ...."});
        Marshaller m = ctx.createMarshaller();
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        m.marshal(a, System.out);
        OutputStream os = new FileOutputStream("/home/haonguyen/data/SummaryRecord.xml");
        m.marshal(a, os);
    }

}
