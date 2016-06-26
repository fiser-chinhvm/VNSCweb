/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import org.apache.VNSC.controllers.ParseXML;
import java.io.File;
import org.apache.VNSCweb.model.Element;
import org.apache.VNSCweb.model.SummaryRecord;
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
        String west = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/westBoundLongitude/Decimal");
        String east = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/eastBoundLongitude/Decimal");
        String south = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/southBoundLatitude/Decimal");
        String north = parseXML.getValue("/MI_Metadata/identificationInfo/MD_DataIdentification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/northBoundLatitude/Decimal");
        Element bbox = new Element();
        bbox.setWest(west);
        bbox.setEast(east);
        bbox.setSouth(south);
        bbox.setNorth(north);
        SummaryRecord m1 = new SummaryRecord(1, identifier, title, "txt", format, modified, bbox);
        return m1;
    }
    public SummaryRecord getLandsatmetadata() throws Exception {
          File xml = new File("/home/haonguyen/data/LC81230522014071LGN00_MTL.xml");
        ParseXML parseXML = new ParseXML(xml);
        String identifier = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/citation/CI_Citation/identifier/MD_Identifier/code/CharacterString");
        String format = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/resourceFormat/MD_Format/name/CharacterString");
        String title = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/resourceFormat/MD_Format/name/CharacterString");
        String modified = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/citation/CI_Citation/date/CI_Date/extent/date/DateTime");
//        String west = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/westBoundLongitude/Decimal");
//        String east = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/eastBoundLongitude/Decimal");
//        String south = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/southBoundLatitude/Decimal");
//        String north = parseXML.getValue("/MD_Metadata/identificationInfo/MD_Identification/extent/EX_Extent/geographicElement/EX_GeographicBoundingBox/northBoundLatitude/Decimal");
        Element bbox = new Element();
        bbox.setWest("108.33624");
        bbox.setEast("110.44435");
        bbox.setSouth("10.49989");
        bbox.setNorth("12.62381");
        SummaryRecord m1 = new SummaryRecord(1, identifier, title, "txt", format, modified, bbox);
        return m1;
    }
}
