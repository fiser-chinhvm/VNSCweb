/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.controller;


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

    public Record() throws ParseException {
        SimpleDateFormat textFormat = new SimpleDateFormat("yyyy-MM-dd");
        String a = "2007-12-25";
        Date date = textFormat.parse(a);
        messages.put(1L, new SummaryRecord(1, "Hello World", "koushik", "13243", date));
        messages.put(2L, new SummaryRecord(2, "Hello Jersey", "koushik", "12132",date));
    }

    public List<SummaryRecord> getAllRecord() {
        return new ArrayList<SummaryRecord>(messages.values());
    }
    public SummaryRecord getRecord(String anytext) {
        return messages.get(anytext);
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
