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
package org.apache.sis.services.csw.request;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.sis.services.csw.ConfigurationReader;
import org.apache.sis.services.csw.RecordConfigure;
import org.apache.sis.services.csw.RecordResult;

/**
 * @author Thi Phuong Hao Nguyen (VNSC)
 * @author Minh Chinh Vu (VNSC)
 * @since 0.8
 * @version 0.8
 * @module
 */
public class AnyText {    
    String constraint;
    String text;
    String identifier;
    String format;
    String constraintLanguage;
    int start;
    int size;
    /**
     * A bouding box for identifying a geographic area of interest use to
     * search.
     */
    BoundingBox bbox = new BoundingBox();
    /**
     * The value startDate use to search .
     */
    String startDate;
    /**
     * The value rangeDate use to search .
     */
    String rangeDate;
    List<AbstractRecord> data = new ArrayList<AbstractRecord>();
    static ConfigurationReader path = new ConfigurationReader();
    
    /**
     * Constructor for AnyText
     *
     * @throws Exception checked exceptions. Checked exceptions need to be
     * declared in a method or constructor's {@code throws} clause if they can
     * be thrown by the execution of the method or constructor and propagate
     * outside the method or constructor boundary.
     */
    public AnyText(String path,String version,String service) throws Exception {
        RecordConfigure a = new RecordConfigure(path,version,service);
        List<AbstractRecord> temp = a.getAllRecord();
        for(int i = 0; i < temp.size(); i++) {
            AbstractRecord summary = new SummaryRecord().SummaryRecord(temp.get(i)); //dung ch
            data.add(summary);
        }
       
//        data.addAll(a.getAllRecord());
    }

    /**
     * Return metadata
     *
     * @return data
     */
  
    public List<AbstractRecord> getData() {
        return data;
    }
    
    public RecordResult getResult(int start, int size, String elementSet) {
        if(start == 0 && size == 0){
        start = 0;
        size = data.size();
        }
        this.start = start;
        this.size = size;
        RecordResult result = new RecordResult();
        result.setElementset(elementSet);
        result.setNumberofrecordsmatched(data.size());
        
        if(size  > data.size()) size = data.size();
        
        if((start + size*2) > data.size()){
            result.setNextrecord(data.size() - start - size);
        } else {
            result.setNextrecord(size);
        }
        
        result.setNumberofrecordsreturned(size);
        
        List<AbstractRecord> a = data;
        
        if (start + size > data.size()) {
            a = data.subList(start, data.size());
        } else {
            a = data.subList(start, start + size);
        }
        if(elementSet.equals("summary")) result.setSummary(a);
        if(elementSet.equals("brief")) result.setBrif(a);
        if(elementSet.equals("full")) result.setRecord(a);
        return result;
    }

    /**
     * Sets a bounding box use to search record.
     *
     * @param west The value is expressed in longitude in decimal degrees
     * (positive east)
     * @param east The value is expressed in longitude in decimal degrees
     * (positive east)
     * @param south The value is expressed in latitude in decimal degrees
     * (positive north).
     * @param north The value is expressed in latitude in decimal degrees
     * (positive north).
     */

    public void setBbox(String west, String east, String south, String north) {
        if(west == null || east == null || south == null || north == null) {
            west = "";
            east = "";
            south = "";
            north = "";
        }
        
        if(west.equals("") || east.equals("") || south.equals("") || north.equals("")) {
            bbox.setLowerCorner(-180 + " " + -180);
            bbox.setUpperCorner(180 + " " +  180);
        } else {
            bbox.setLowerCorner(west + " " + south);
            bbox.setUpperCorner(east + " " + north);
        }
    }

    /**
     * AnyText used to search.
     *
     * @param format the physical or digital manifestation of the resource
     * @param identifier a unique reference to the record within the catalogue
     * @param startDate date from
     * @param rangeDate date to
     * @throws Exception Constructs a new exception with the specified detail
     * message.
     */
//    public AnyText(String path, String version, String service, String constraintLanguage, ) throws Exception {
//        bbox.setLowerCorner(-180 + " " + -180);
//        bbox.setUpperCorner(180 + " " + 180);
//
//        RecordConfigure a = new RecordConfigure(path,version,service);
//        List<AbstractRecord> temp = a.getAllRecord();
//        for(int i = 0; i < temp.size(); i++) {
//            AbstractRecord summary = new SummaryRecord().SummaryRecord(temp.get(i)); //dung ch
//            data.add(summary);
//        }
//        
//        this.constraintLanguage = constraintLanguage;
//        this.constraint = constraint;
////        
//    }
    
    public AnyText(String path,String version, String service, String elementSet, String constraintLanguage, String constraint, String format, String identifier, String startDate, String rangeDate) throws Exception {
        bbox.setLowerCorner(-180 + " " + -180);
        bbox.setUpperCorner(180 + " " + 180);
        this.constraintLanguage = constraintLanguage;
        
        
        
        if(constraintLanguage.equals("CQL_TEXT")) {
            this.constraint = constraint;
            this.text = convertConstraint(constraint);
        }
        
        if(constraintLanguage.equals("filter")) {
            if(identifier == null) identifier = "";
            if(format == null) format = "";
            if(startDate == null || rangeDate == null) {
                startDate = "";
                rangeDate = "";
            }
            
            this.identifier = identifier;
            this.format = format;
            this.startDate = startDate;
            this.rangeDate = rangeDate;
        }
        
        //Handle ElementSetName
        RecordConfigure a = new RecordConfigure(path,version,service);
        List<AbstractRecord> temp = a.getAllRecord();
        
        if(elementSet.equals("brief")){
            for(int i = 0; i < temp.size(); i++) {
                AbstractRecord record = new Record().Record(temp.get(i)); //dung ch
                data.add(record);
            }
        }
        
        if(elementSet.equals("summary")) {
             for(int i = 0; i < temp.size(); i++) {
                AbstractRecord summary = new SummaryRecord().SummaryRecord(temp.get(i)); //dung ch
                data.add(summary);
            }
        }
        
        if(elementSet.equals("full")) {
             for(int i = 0; i < temp.size(); i++) {
                AbstractRecord record = new Record().Record(temp.get(i)); //dung ch
                data.add(record);
            }
        } 
    }
    
    public String convertConstraint(String cons) {
        String result = "";
        boolean check = false;
        for(int i = 0; i < cons.length(); i++) {
            if(cons.charAt(i) == '\'') {
                check = !check;
                continue;
            }
            
            if(check) {
                result += cons.charAt(i);
            }
        }
        return result;
    }
    /**
     * Set Bouding Box
     *
     * @param west The value is expressed in longitude in decimal degrees
     * (positive east)
     * @param east The value is expressed in longitude in decimal degrees
     * (positive east)
     * @param south The value is expressed in latitude in decimal degrees
     * (positive north).
     * @param north The value is expressed in latitude in decimal degrees
     * (positive north).
     * @param bound bounding box
     * @return bounding box define
     */
    public boolean checkBBOX(BoundingBox bound) {
        String lower[] = bound.getLowerCorner().split(" ");
        String upper[] = bound.getUpperCorner().split(" ");
        
        double itWest = Double.parseDouble(lower[0]);
        double itNorth = Double.parseDouble(upper[1]);
        double itSouth = Double.parseDouble(lower[1]);
        double itEast = Double.parseDouble(upper[0]);

        String bboxLower[] = bbox.getLowerCorner().split(" ");
        String bboxUpper[] = bbox.getUpperCorner().split(" ");
        
//        System.out.println(lower[0] + " " + upper[0]);
        double west = Double.parseDouble(bboxLower[0]);
        double north = Double.parseDouble(bboxUpper[1]);
        double south = Double.parseDouble(bboxLower[1]);
        double east = Double.parseDouble(bboxUpper[0]);
        
        if (east < itWest) {
            return false;
        }
        if (west > itEast) {
            return false;
        }
        if (north < itSouth) {
            return false;
        }
        if (south > itNorth) {
            return false;
        }

        return true;
    }
    
    /**
     * CheckFormat
     *
     * @param itFormat set the date value start
     * @return true itFormat have value in format 
     */
    
    public boolean checkFormat(String itFormat) {
        if(format.contains(",")){
            String[] result = format.split(",");
            for(int i = 0; i < result.length; i++) {
                if(itFormat.contains(result[i])) {
                    return true;
                }
            }
        } else {
             if(itFormat.contains(format)) {
                 return true;
            }
        }
        return false;
    }
    
    public boolean checkConstraint(AbstractRecord rec) {
        if(text.startsWith("%") && text.endsWith("%")) {
            String temp = text.replaceAll("%", "");
            if(rec.getFormat().contains(temp)) return true;
            if(rec.getIdentifier().contains(temp)) return true;
        }
        
        if(text.startsWith("%")) {
            String temp = text.replaceAll("%", "");
            if(rec.getFormat().endsWith(temp)) return true;
            if(rec.getIdentifier().endsWith(temp)) return true;
        }
        
        if(text.endsWith("%")) {
            String temp = text.replaceAll("%", "");
            if(rec.getFormat().startsWith(temp)) return true;
            if(rec.getIdentifier().startsWith(temp)) return true;    
        }
        
        if(rec.getFormat().equals(text)) return true;
        if(rec.getIdentifier().equals(text)) return true;
            
        return false;
    }
    
    /**
     * CheckDate
     *
     * @param date1 set the date value start
     * @param date2 set the date value final
     * @param record set the record define
     * @return the record define
     * @throws Exception checked exceptions. Checked exceptions need to be
     * declared in a method or constructor's {@code throws} clause if they can
     * be thrown by the execution of the method or constructor and propagate
     * outside the method or constructor boundary.
     */
    public boolean checkDate(String date1, String date2, AbstractRecord record) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        if(date1.equals("") || date2.equals("")) return true; 
        Date da1 = df.parse(date1);
        Date da2 = df.parse(date2);

        long day = (da2.getTime() - da1.getTime()) / (24 * 60 * 60 * 1000);

        Date da3 = record.getModified();
        long day1 = (da3.getTime() - da1.getTime()) / (24 * 60 * 60 * 1000);
        if (day1 >= 0 && day1 <= day) {
            return true;
        }
        return false;
    }

    /**
     * Filter a bounding box for identifying a geographic area of interest use
     * to search.
     *
     * @throws Exception Exception checked exceptions. Checked exceptions need
     * to be declared in a method or constructor's {@code throws} clause if they
     * can be thrown by the execution of the method or constructor and propagate
     * outside the method or constructor boundary.
     */
    public void filter() throws Exception {
        
        for (Iterator<AbstractRecord> it = data.iterator(); it.hasNext();) {
            AbstractRecord itSum = it.next();
            
            /**
             * Remove Out of range Date.
             */
            
            if(constraintLanguage.equals("CQL_TEXT")) {
                if (!checkConstraint(itSum)) {
                    it.remove();
                    continue;
                }
            } 
            
            if(constraintLanguage.toUpperCase().equals("FILTER")){
                if(format.equals("") && identifier.equals("") && startDate.equals("") && rangeDate.equals("") && bbox.getLowerCorner().contains("-180") && bbox.getUpperCorner().contains("180")) {
                    data = new ArrayList<AbstractRecord>();
                    break;
                }
                
                if (!this.checkDate(startDate, rangeDate, itSum)) {
                    it.remove();
                    continue;
                }
                

            /**
             * Check by constraint.
             */
                if (!itSum.getIdentifier().contains(identifier)) {
                    it.remove();
                    continue;
                }

                if (!checkFormat(itSum.getFormat())) {
                    it.remove();
                    continue;
                }
                /**
                 * Remove picture out of BBOX range.
                 */
//                System.out.println(checkBBOX(itSum.getBoundingBox()));
                if (!checkBBOX(itSum.getBoundingBox())) {
//                    System.out.println("CHUI VAO DAY");
                    it.remove();
                    continue;
                }
            }
            
        }
    }
    
    public static void main(String[] args) throws Exception {
        AnyText t = new AnyText(path.getValue("Path"), "2.0.2", "GetRecords", "full", "filter", "", "", "MY", "", "");
//        AnyText z = new AnyText(path, version, service, elementSet, constraintLanguage, constraint, format, identifier, startDate, rangeDate)
//        t.setBbox("", "", "","");
        t.filter();
        
        System.out.println(t.getData().size());
        for(int i = 0; i < t.getData().size(); i++) {
            AbstractRecord temp = t.getData().get(i);
            System.out.println(temp.getCloudcover() + " SUb=" + temp.getSubject() + " type=" +temp.getType());
        }
//        
//        System.out.println(t.getData().size());
        
//        System.out.println(t.convertConstraint("csw:AnyText Like '%pollution%'"));

    }
}
