/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.VNSCweb.model.DatabaseClass;
import org.apache.VNSCweb.model.SummaryRecord;

/**
 *
 * @author haonguyen
 */
public class G {
    private Map<String, SummaryRecord> messages1 = DatabaseClass.getRecord1();

    public G() throws ParseException, Exception {
        ReadXML a = new ReadXML();
        messages1.put("xml", a.getModismetadata());
        messages1.put("txt", a.getLandsatmetadata());
    }
    public List<SummaryRecord> getAllRecord() {
        return new ArrayList<SummaryRecord>(messages1.values());
    }
    public SummaryRecord getRecord(String type) {
        return messages1.get(type);
    }
}
