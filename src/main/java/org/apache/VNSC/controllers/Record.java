/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;


import org.apache.VNSC.controllers.ReadXML;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.apache.VNSCweb.model.DatabaseClass;
import org.apache.VNSCweb.model.SummaryRecord;

/**
 *
 * @author haonguyen
 */
public class Record {

    private Map<Long, SummaryRecord> messages = DatabaseClass.getRecord();
    private Map<String, SummaryRecord> messages1 = DatabaseClass.getRecord1();

    public Record() throws ParseException, Exception {
        ReadXML a = new ReadXML();
        messages.put(1L, a.getModismetadata());
        messages.put(2L, a.getLandsatmetadata());
    }
    

    public List<SummaryRecord> getAllRecord() {
        return new ArrayList<SummaryRecord>(messages.values());
    }
    
    public SummaryRecord getRecordById(long id) {
        return messages.get(id);
    }

    public SummaryRecord addRecord(SummaryRecord message) {
        message.setId(messages.size() + 1);
        messages.put(message.getId(), message);
        return message;
    }

    public SummaryRecord updateRecord(SummaryRecord message) {
        if (message.getId() <= 0) {
            return null;
        }
        messages.put(message.getId(), message);
        return message;
    }

    public void removeRecord(long id) {
        messages.remove(id);
    }

}
