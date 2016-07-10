/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.VNSCweb.model.BoundingBox;
import org.apache.VNSCweb.model.SummaryRecord;
import static org.apache.sis.internal.util.CollectionsExt.first;
import org.apache.sis.storage.geotiff.LandsatReader;
import org.apache.sis.xml.XML;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.extent.Extent;
import org.opengis.metadata.extent.GeographicBoundingBox;
import org.opengis.metadata.identification.Identification;

/**
 * @author Thi Phuong Hao NGUYEN
 * @author Minh Chinh VU
 */
public class XMLReader {
    
    
      ConfigurationReader path = new ConfigurationReader();

    public List<SummaryRecord> listModis() throws Exception {
        //get all the files from a directory
        ConfigurationReader path = new ConfigurationReader();
        List<SummaryRecord> record = new ArrayList<>();
        File directory = new File(path.getPropValues());
        //get all the files from a directory
        File[] fList = directory.listFiles();
        int i = 1;
        for (File file : fList) {
            if (file.isFile() && file.getName().endsWith(".iso19115")) {
                Metadata ModisMD = (Metadata) XML.unmarshal(new File(file.getPath()));
                Identification id = first(ModisMD.getIdentificationInfo());
                Extent et = first(id.getExtents());
                GeographicBoundingBox gbd = (GeographicBoundingBox) first(et.getGeographicElements());

                String identifier = first(id.getCitation().getIdentifiers()).getCode();

                String format = id.getCitation().getTitle().toString();
                String title = file.getName();
                Date Modified = first(id.getCitation().getDates()).getDate();
                double west = gbd.getWestBoundLongitude();
                double east = gbd.getEastBoundLongitude();
                double south = gbd.getSouthBoundLatitude();
                double north = gbd.getNorthBoundLatitude();
                BoundingBox bbox = new BoundingBox();
                bbox.setWestBoundLongitude(west);
                bbox.setEastBoundLongitude(east);
                bbox.setSouthBoundLatitude(south);
                bbox.setNorthBoundLatitude(north);
                SummaryRecord m1 = new SummaryRecord(i, identifier, title, "xml", format, Modified, bbox);
                record.add(m1);
                i++;
            }
        }

        return record;
    }

    public List<SummaryRecord> listGeotiff() throws Exception {
        //get all the files from a directory
       
        List<SummaryRecord> record = new ArrayList<>();
        File directory = new File(path.getPropValues());
        //get all the files from a directory
        File[] fList = directory.listFiles();
        int i = 1000;
        for (File file : fList) {
            if (file.isFile() && file.getName().endsWith(".txt")) {

                BufferedReader in = new BufferedReader(new FileReader(file.getPath()));
                Metadata LandsatMD = new LandsatReader(in).read();

                Identification id = first(LandsatMD.getIdentificationInfo());
                Extent et = first(id.getExtents());
                GeographicBoundingBox gbd = (GeographicBoundingBox) first(et.getGeographicElements());

                String identifier = first(id.getCitation().getIdentifiers()).getCode();

                String format = first(id.getResourceFormats()).getFormatSpecificationCitation().getTitle().toString();
                String title = file.getName();
                Date Modified = first(id.getCitation().getDates()).getDate();

                double west = gbd.getWestBoundLongitude();
                double east = gbd.getEastBoundLongitude();
                double south = gbd.getSouthBoundLatitude();
                double north = gbd.getNorthBoundLatitude();
                BoundingBox bbox = new BoundingBox();
                bbox.setWestBoundLongitude(west);
                bbox.setEastBoundLongitude(east);
                bbox.setSouthBoundLatitude(south);
                bbox.setNorthBoundLatitude(north);
                SummaryRecord m1 = new SummaryRecord(i, identifier, title, "txt", format, Modified, bbox);
                record.add(m1);
                i++;
            }
        }

        return record;
    }

//    public static void main(String[] args) throws Exception {
//        ReadXML test = new ReadXML();
//        System.out.println(test.listModis());
//        System.out.println(test.listGeotiff());
//
//    }
}
