/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.model;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author haonguyen
 */
public class DatabaseClass {
    private static Map<Long,SummaryRecord> record = new HashMap<>();
    private static Map<String,SummaryRecord> record1 = new HashMap<>();
  
    public static Map<Long,SummaryRecord> getRecord(){
        return record;
    }
    public static Map<String,SummaryRecord> getRecord1(){
        return record1;
    }
}
