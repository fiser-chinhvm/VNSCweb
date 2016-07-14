/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more 
 * contributor license agreements.  See the NOTICE file distributed with 
 * this work for additional information regarding copyright ownership. 
 * The ASF licenses this file to You under the Apache License, Version 2.0 
 * (the "License"); you may not use this file except in compliance with 
 * the License.  You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */
package org.apache.sis.services.csw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.bind.JAXBException;
import static org.apache.sis.internal.util.CollectionsExt.first;
import org.apache.sis.storage.DataStoreException;
import org.apache.sis.storage.geotiff.LandsatReader;
import org.apache.sis.xml.XML;
import org.opengis.metadata.Metadata;
import org.opengis.metadata.citation.Responsibility;
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
                try {
                    Metadata ModisMD = (Metadata) XML.unmarshal(new File(file.getPath()));
                    Identification id = first(ModisMD.getIdentificationInfo());
                    String identifier = ModisMD.getFileIdentifier();
                    String format = id.getCitation().getTitle().toString();
                    String title = id.getCitation().getTitle().toString();
                    String type = first(ModisMD.getHierarchyLevels()).name();
                    Date modified = ModisMD.getDateStamp();
                    String subject = first(id.getTopicCategories()).IMAGERY_BASE_MAPS_EARTH_COVER.toString();
//                List<Responsibility> responsibility = new ArrayList<>(first(ModisMD.getIdentificationInfo()).getPointOfContacts());
                    String creator = "";
                    String publisher = "";
                    String contributor = "";
//
                    String language = ModisMD.getLanguage().toString();
                    String relation = first(id.getAggregationInfo()).getAggregateDataSetName().getTitle().toString();
                    Extent et = first(id.getExtents());
                    GeographicBoundingBox gbd = (GeographicBoundingBox) first(et.getGeographicElements());
                    BoundingBox bbox = new BoundingBox(gbd);
                    
                    SummaryRecord m1 = new SummaryRecord(creator, contributor, publisher, subject, identifier, relation, type, title, modified, language, format, bbox);
                    record.add(m1);
                    i++;
                } catch (JAXBException e) {
                    throw new DataStoreException("Can not read " + file, e);
                }
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
                try {
                    BufferedReader in = new BufferedReader(new FileReader(file.getPath()));
                    Metadata LandsatMD = new LandsatReader(in).read();

                    Identification id = first(LandsatMD.getIdentificationInfo());
                    String name = file.getName();
                    String identifier = LandsatMD.getFileIdentifier();

                    String format = first(first(LandsatMD.getDistributionInfo()).getDistributionFormats()).getName().toString();
                    String title = id.getCitation().getTitle().toString();
                    String type = first(LandsatMD.getHierarchyLevels()).name();
                    Date modified = LandsatMD.getDateStamp();
                    String subject = first(first(id.getDescriptiveKeywords()).getKeywords()).toString();
                    List<Responsibility> responsibility = new ArrayList<>(first(LandsatMD.getIdentificationInfo()).getPointOfContacts());
                    String creator = first(responsibility.get(0).getParties()).getName().toString();
                    String publisher = first(responsibility.get(1).getParties()).getName().toString();
                    String contributor = first(responsibility.get(2).getParties()).getName().toString();

                    String language = LandsatMD.getLanguage().toString();
                    String relation = first(id.getAggregationInfo()).getAggregateDataSetName().getTitle().toString();
                    Extent et = first(id.getExtents());
                    GeographicBoundingBox gbd = (GeographicBoundingBox) first(et.getGeographicElements());
                    BoundingBox bbox = new BoundingBox(gbd);
                    
                    SummaryRecord m1 = new SummaryRecord(creator, contributor, publisher, subject, identifier, relation, type, title, modified, language, format, bbox);
                    record.add(m1);
                    i++;
                } catch (JAXBException e) {
                    throw new DataStoreException("Can not read " + file, e);
                }
            }
        }

        return record;
    }

    public static void main(String[] args) throws Exception {

     XMLReader test = new XMLReader() ;
    
        System.out.println(test.listGeotiff());
        System.out.println(test.listModis());

    }
}
