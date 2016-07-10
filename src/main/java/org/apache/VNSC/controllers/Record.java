/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;


import java.text.DateFormat;
import org.apache.VNSC.controllers.XMLReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.VNSCweb.model.SummaryRecord;

/**
 *
 * @author Thi Phuong Hao NGUYEN
 * @author Minh Chinh VU
 */
public class Record {

    public Record() {
    }

    public List<SummaryRecord> getAllRecord() throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.listGeotiff());
        b.addAll(a.listModis());
        return b;
    }

    public List<SummaryRecord> getRecordByText(String format) throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.listGeotiff());
        b.addAll(a.listModis());
        List<SummaryRecord> messagesByText = new ArrayList<>();
        for (SummaryRecord message : b) {
            if (message.getFormat().equals(format)) {
                messagesByText.add(message);
            }
        }
        return messagesByText;
    }

    public List<SummaryRecord> SearchDate(String date1, String date2) throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.listGeotiff());
        b.addAll(a.listModis());
        List<SummaryRecord> messagesForYear = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date da1 = df.parse(date1);
        Date da2 = df.parse(date2);

        long day = (da2.getTime() - da1.getTime()) / (24 * 60 * 60 * 1000);

        for (SummaryRecord message : b) {
            Date da3 = message.getModified();
            long day1 = (da3.getTime() - da1.getTime()) / (24 * 60 * 60 * 1000);
            if (day1 >= 0 && day1 <= day) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }

    public List<SummaryRecord> getAllRecordPaginated(int start, int size) throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.listGeotiff());
        b.addAll(a.listModis());
        ArrayList<SummaryRecord> list = new ArrayList<SummaryRecord>(b);
        if (start + size > list.size()) {
            return new ArrayList<SummaryRecord>();
        }
        return list.subList(start, start + size);
    }

    public List<SummaryRecord> getRecordById(long id) throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.listGeotiff());
        b.addAll(a.listModis());
        List<SummaryRecord> messagesById = new ArrayList<>();
        for (SummaryRecord message : b) {
            if (id == message.getId()) {
                messagesById.add(message);
            }
        }
        return messagesById;
    }

    public List<SummaryRecord> BoundingBox(double west , double east, double south, double north  ) throws Exception {
        XMLReader a = new XMLReader();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.listGeotiff());
        b.addAll(a.listModis());
        List<SummaryRecord> messageSearch = new ArrayList<>();
        for (SummaryRecord message : b) {
            double west1 = message.getBoundingBox().getWestBoundLongitude();
            double east1 = message.getBoundingBox().getEastBoundLongitude();
            double south1 = message.getBoundingBox().getSouthBoundLatitude();
            double north1 = message.getBoundingBox().getNorthBoundLatitude();
            if (west1>=west && east1<= east && south1 >= south && north1 <=north) {
                messageSearch.add(message);
             }
        }
        return messageSearch;
    }
//
//    public static void main(String[] args) throws Exception {
//        Record a = new Record();
//        System.out.println(a.BoundingBox(105.19, 109.55, 14.7, 17.9));
//    }
}
