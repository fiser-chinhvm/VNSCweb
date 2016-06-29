/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import java.io.BufferedReader;
import org.apache.VNSC.controllers.ParseXML;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.VNSCweb.model.Element;
import org.apache.VNSCweb.model.SummaryRecord;
import static org.apache.sis.internal.util.CollectionsExt.first;
import org.apache.sis.storage.geotiff.LandsatReader;
import org.apache.sis.xml.XML;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.identification.Identification;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.Node;

/**
 *
 * @author haonguyen
 */
public class ReadXML {
        public SummaryRecord getModismetadata() throws Exception {
        Metadata ModisMD = (Metadata) XML.unmarshal(new File("/home/chinhvm/Documents/G184220810-LAADS.iso19115"));
        Identification id =  first(ModisMD.getIdentificationInfo());       
        Extent et = first(id.getExtents());
        GeographicBoundingBox gbd = (GeographicBoundingBox) first(et.getGeographicElements());
                
        String identifier = first(id.getCitation().getIdentifiers()).getCode(); 
                    
        String format = id.getCitation().getTitle().toString();
        String title = id.getCitation().getTitle().toString();

        Date Modified = first(id.getCitation().getDates()).getDate();
            
        double west = gbd.getWestBoundLongitude();
        double east = gbd.getEastBoundLongitude();
        double south = gbd.getSouthBoundLatitude();
        double north = gbd.getNorthBoundLatitude();
        Element bbox = new Element();
        bbox.setWestBoundLongitude(west);
        bbox.setEastBoundLongitude(east);
        bbox.setSouthBoundLongitude(south);
        bbox.setNorthBoundLongitude(north);
        SummaryRecord m1 = new SummaryRecord(1, identifier, title, "xml", format, Modified, bbox);
        return m1;
    }
    
    public SummaryRecord getLandsatmetadata() throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("/home/chinhvm/Documents/LC81230522014071LGN00_MTL.txt"));
        Metadata LandsatMD = new LandsatReader(in).read();
        
        Identification id =  first(LandsatMD.getIdentificationInfo());       
        Extent et = first(id.getExtents());
        GeographicBoundingBox gbd = (GeographicBoundingBox) first(et.getGeographicElements());
            
            
        String identifier = first(id.getCitation().getIdentifiers()).getCode(); 
                    
        String format = first(first(id.getResourceFormats()).getFormatSpecificationCitation().getAlternateTitles()).toString();
        String title = first(first(id.getResourceFormats()).getFormatSpecificationCitation().getAlternateTitles()).toString();
//            String modified = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/extent/EX_Extent/temporalElement/EX_TemporalExtent/extent/TimePeriod/endPosition");
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date Modified = first(id.getCitation().getDates()).getDate();
            
        double west = gbd.getWestBoundLongitude();
        double east = gbd.getEastBoundLongitude();
        double south = gbd.getSouthBoundLatitude();
        double north = gbd.getNorthBoundLatitude();
        Element bbox = new Element();
        bbox.setWestBoundLongitude(west);
        bbox.setEastBoundLongitude(east);
        bbox.setSouthBoundLongitude(south);
        bbox.setNorthBoundLongitude(north);
        SummaryRecord m1 = new SummaryRecord(2, identifier, title, "txt", format, Modified, bbox);
        return m1;
    }
    
//    public static void main(String[] args) throws Exception {
//        ReadXML test = new ReadXML();
//        System.out.println(test.getLandsatmetadata().getFormat());
//    }
}
