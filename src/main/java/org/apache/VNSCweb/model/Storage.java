/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import javax.xml.bind.JAXBException;
import org.apache.VNSC.controllers.ReadXML;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.geotiff.LandsatReader;
import org.apache.sis.xml.XML;
import org.opengis.metadata.Metadata;
import org.opengis.util.FactoryException;

/**
 *
 * @author chinhvm
 */
public class Storage {
    ArrayList<SummaryRecord> store = new ArrayList<SummaryRecord>();
    ReadXML data = new ReadXML();
    public Storage() throws JAXBException, IOException, DataStoreException, ParseException, FactoryException, Exception{
        
        for(int i = 0; i<5; i++){
            store.add(data.getLandsatmetadata());
            store.add(data.getModismetadata());
        }
    }
    
    public SummaryRecord getSumRecordById(int id){
        return store.get(id);
    }
    
    public ArrayList<SummaryRecord> getGeoTiffSumRecord() throws Exception{
        ArrayList<SummaryRecord> result = new ArrayList<SummaryRecord>();
        for(int i = 0; i<5; i++){
            result.add(data.getLandsatmetadata());
        }
        return result;
    }
    
    public ArrayList<SummaryRecord> getModisSumRecord() throws Exception{
        ArrayList<SummaryRecord> result = new ArrayList<SummaryRecord>();
        for(int i = 0; i<5; i++){
            result.add(data.getModismetadata());
        }
        return result; 
    }
    
    public ArrayList<SummaryRecord> getAllSumRecord(){
        return store;
    }
}
