/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.VNSCweb.model.BoundingBox;
import org.apache.VNSCweb.model.SummaryRecord;

/**
 *
 * @author chinhvm
 */
public class AnyText {
    String format;
    String identifier;
    BoundingBox bbox = new BoundingBox();
    String startDate;
    String rangeDate;
    List<SummaryRecord> data = new ArrayList<SummaryRecord>();

    public AnyText() throws Exception {
        ReadXML a = new ReadXML();
        data.addAll(a.listGeotiff());
        data.addAll(a.listModis());
    }
    
    public List<SummaryRecord> getData(){
        return data;
    }

    public void setBbox(double west , double east, double south, double north) {
        bbox.setEastBoundLongitude(east);
        bbox.setWestBoundLongitude(west);
        bbox.setNorthBoundLongitude(north);
        bbox.setSouthBoundLongitude(south);
    }
    
    public AnyText(String format, String identifier, String startDate, String rangeDate) throws Exception {
        ReadXML a = new ReadXML();
        data.addAll(a.listGeotiff());
        data.addAll(a.listModis());
        this.format = format;
        this.identifier = identifier;
        this.startDate = startDate;
        this.rangeDate = rangeDate;
    }
    
    /**
    * CheckBBOX
    * True: input in Element Bound
    */
    
    public boolean checkBBOX(double east, double west, double south, double north, BoundingBox bound){
        double itWest = bound.getWestBoundLongitude();
        double itNorth = bound.getNorthBoundLongitude();
        double itSouth = bound.getSouthBoundLongitude();
        double itEast = bound.getEastBoundLongitude();
        
        if(east < itWest) return false;
        if(west > itEast)return false;
        if(north < itSouth) return false;
        if(south > itNorth) return false;
        
        return true;
    }
    
    //true:
    public boolean checkDate(String date1, String date2, SummaryRecord record) throws Exception {             
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date da1 = df.parse(date1);
        Date da2 = df.parse(date2);

        long day = (da2.getTime() - da1.getTime()) / (24 * 60 * 60 * 1000);

        Date da3 = record.getModified();
        long day1 = (da3.getTime() - da1.getTime()) / (24 * 60 * 60 * 1000);
        if (day1 >= 0 && day1 <= day) return true;
        return false;
    }
     
    public void filter() throws Exception{
        double east = bbox.getEastBoundLongitude();
        double west = bbox.getWestBoundLongitude();
        double south = bbox.getSouthBoundLongitude();
        double north = bbox.getNorthBoundLongitude();
        
        for (Iterator<SummaryRecord> it=data.iterator(); it.hasNext();) {
            SummaryRecord itSum = it.next();
           
            //Remove Out of range Date
            if(!this.checkDate(startDate,rangeDate,itSum)){
                it.remove();
                continue;
            }
            
            //Check by identifier
            if (!itSum.getIdentifier().contains(identifier)){
                it.remove();
                continue;
            }
            
            //Check by format type
            if (!itSum.getFormat().contains(format)){
                it.remove();
                continue; // NOTE: Iterator's remove method, not ArrayList's, is used.
            }
             
            //Remove picture out of BBOX range
            if(!checkBBOX(east, west, south, north, itSum.getBoundingBox())){
                it.remove();
                continue;
            }
            
        }
    }
    
//    public static void main(String[] args) throws Exception {
//        AnyText a= new AnyText("","","2009-04-04","2015-05-05");
//        AnyText b= new AnyText();
//        a.setBbox(5,130 , 5, 130);
//        a.filter();
//        System.out.println(a.getData().size());
////        System.out.println(a.getData().get(0).getIdentifier());
////        System.out.println(a.getData().get(0).getBoundingBox().getWestBoundLongitude());
////        System.out.println(a.getData().get(0).getBoundingBox().getEastBoundLongitude());
////        System.out.println(a.getData().get(0).getBoundingBox().getSouthBoundLongitude());
////        System.out.println(a.getData().get(0).getBoundingBox().getNorthBoundLongitude());
//    }
}
