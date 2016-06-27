/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.VNSCweb.model.DatabaseClass;
import org.apache.VNSCweb.model.SummaryRecord;

/**
 *
 * @author haonguyen
 */
public class ProfileService {

	private Map<String, SummaryRecord> profiles = DatabaseClass.getRecord1();
	
	public ProfileService() throws Exception {
            ReadXML a = new ReadXML();
		profiles.put("MOD021KM", a.getModismetadata());
                profiles.put("GEOTIFF", a.getLandsatmetadata());
	}
	
	public List<SummaryRecord> getAllProfiles() {
		return new ArrayList<SummaryRecord>(profiles.values()); 
	}
	
	public SummaryRecord getProfile(String format) {
		return profiles.get(format);
	}
	
	public SummaryRecord addProfile(SummaryRecord profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getType(), profile);
		return profile;
	}
	
	public SummaryRecord updateProfile(SummaryRecord profile) {
		if (profile.getType().isEmpty()) {
			return null;
		}
		profiles.put(profile.getType(), profile);
		return profile;
	}
	
	public SummaryRecord removeProfile(String profileName) {
		return profiles.remove(profileName);
	}
	
	
}
