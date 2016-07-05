/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.DateFormat;
import org.apache.VNSC.controllers.ReadXML;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.VNSCweb.exception.DataNotFoundException;
import org.apache.VNSCweb.model.DatabaseClass;
import org.apache.VNSCweb.model.SummaryRecord;

/**
 *
 * @author haonguyen
 */
public class Record {

    public Record() {
    }

    public List<SummaryRecord> getAllRecord() throws Exception {
        ReadXML a = new ReadXML();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.listGeotiff());
        b.addAll(a.listModis());
        return b;
    }

    public List<SummaryRecord> getRecordByText(String format) throws Exception {
        ReadXML a = new ReadXML();
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
        ReadXML a = new ReadXML();
        List<SummaryRecord> b = new ArrayList<SummaryRecord>();
        b.addAll(a.listGeotiff());
        b.addAll(a.listModis());
        List<SummaryRecord> messagesForYear = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date da1= df.parse(date1);
        Date da2= df.parse(date2);
        
        long day = (da2.getTime() - da1.getTime())/(24*60*60*1000);
        
        for (SummaryRecord message : b) {
           Date da3= message.getModified();
           long day1 = (da3.getTime()- da1.getTime())/(24*60*60*1000);
            if (day1 >= 0 && day1 <= day) {
                messagesForYear.add(message);
            }
        }
        return messagesForYear;
    }
    public List<SummaryRecord> getAllRecordPaginated(int start, int size) throws Exception {
        ReadXML a = new ReadXML();
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
        ReadXML a = new ReadXML();
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

//    public static void main(String[] args) throws Exception {
//    Record a = new Record();
//        System.out.println(a.getRecordByText("MOD021KM"));
//    }
}
