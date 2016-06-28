/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;


import org.apache.VNSC.controllers.ReadXML;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
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

    private Map<Long, SummaryRecord> messages = DatabaseClass.getRecord();

    public Record() throws ParseException, Exception {
        ReadXML a = new ReadXML();
        messages.put(1L, a.getModismetadata());
        messages.put(2L, a.getLandsatmetadata());
    }
    

    public List<SummaryRecord> getAllRecord() {
        return new ArrayList<SummaryRecord>(messages.values());
    }
    public List<SummaryRecord> getAllRecordForYear(int year) {
		List<SummaryRecord> messagesForYear = new ArrayList<>();
		Calendar cal = Calendar.getInstance();
		for (SummaryRecord message : messages.values()) {
			cal.setTime(message.getModified());
			if (cal.get(Calendar.YEAR) == year) {
				messagesForYear.add(message);
			}
		}
		return messagesForYear;
	}
	
	public List<SummaryRecord> getAllRecordPaginated(int start, int size) {
		ArrayList<SummaryRecord> list = new ArrayList<SummaryRecord>(messages.values());
		if (start + size > list.size()) return new ArrayList<SummaryRecord>();
		return list.subList(start, start + size); 
	}
	
    public SummaryRecord getRecordById(long id) {
        SummaryRecord message = messages.get(id);
        if (message ==null){
            throw new DataNotFoundException("Record with id " + id +" not found");
        }
        return message;
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
