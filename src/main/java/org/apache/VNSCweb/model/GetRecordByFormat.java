/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chinhvm
 */
public class GetRecordByFormat {
    Map<String,ArrayList<SummaryRecord>> data = new HashMap<>();
    public GetRecordByFormat() throws Exception{
        Storage store = new Storage();
        data.put("GEOTIFF", store.getGeoTiffSumRecord());
        data.put("MOD021KM", store.getModisSumRecord());
    }
    
    public ArrayList<SummaryRecord> GetByFormat(String format){
        String modis = "mod021km";
        String geo = "geotiff";
//        System.out.print("ABC"+data.get("GEOTIFF").get(0).getFormat());
        if(geo.indexOf(format.toLowerCase()) >= 0){
            return data.get("GEOTIFF");
        }
        if(modis.indexOf(format.toLowerCase()) >= 0){
            return data.get("MOD021KM");
        }
        return new ArrayList<SummaryRecord>();
    }
    
}
