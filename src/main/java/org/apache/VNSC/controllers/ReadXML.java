/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import org.apache.VNSC.controllers.ParseXML;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.apache.VNSCweb.model.Element;
import org.apache.VNSCweb.model.SummaryRecord;
import org.springframework.format.annotation.DateTimeFormat;
import org.w3c.dom.Node;

/**
 *
 * @author haonguyen
 */
public class ReadXML {
        public SummaryRecord getModismetadata() throws Exception {
        File xml = new File("/home/haonguyen/data/G184220810-LAADS.iso19115");
        ParseXML parseXML = new ParseXML(xml);
        String identifier = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/citation/CI_Citation/identifier/MD_Identifier/code/CharacterString");
        String format = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/citation/CI_Citation/title/CharacterString");
        String title = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/citation/CI_Citation/title/CharacterString");
        String modified = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/extent/EX_Extent/temporalElement/EX_TemporalExtent/extent/TimePeriod/endPosition");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
        Date Modified = df.parse(modified);
        String west = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/westBoundLongitude/Decimal");
        String east = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/eastBoundLongitude/Decimal");
        String south = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/southBoundLatitude/Decimal");
        String north = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/northBoundLatitude/Decimal");
        Element bbox = new Element();
        bbox.setWestBoundLongitude(west);
        bbox.setEastBoundLongitude(east);
        bbox.setSouthBoundLongitude(south);
        bbox.setNorthBoundLongitude(north);
        SummaryRecord m1 = new SummaryRecord(1, identifier, title, "xml", format, Modified, bbox);
        return m1;
    }
    public SummaryRecord getLandsatmetadata() throws Exception {
        File xml = new File("/home/haonguyen/data/LC81230522014071LGN00_MTL.xml");
        ParseXML parseXML = new ParseXML(xml);
        String identifier = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/citation/CI_Citation/identifier/MD_Identifier/code/CharacterString");
        String format = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/resourceFormat/MD_Format/name/CharacterString");
        String title = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/resourceFormat/MD_Format/name/CharacterString");
        String modified = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/citation/CI_Citation/date/CI_Date/date/DateTime");
//        String west = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/westBoundLongitude/Decimal");
//        String east = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/eastBoundLongitude/Decimal");
//        String south = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/southBoundLatitude/Decimal");
//        String north = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/northBoundLatitude/Decimal");
        Element bbox = new Element();
        bbox.setWestBoundLongitude("108.33624");
        bbox.setEastBoundLongitude("110.44435");
        bbox.setSouthBoundLongitude("10.49989");
        bbox.setNorthBoundLongitude("12.62381");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        Date Modified = df.parse(modified);
        SummaryRecord m1 = new SummaryRecord(2, identifier, title, "txt", format, Modified, bbox);
        return m1;
    }
}
