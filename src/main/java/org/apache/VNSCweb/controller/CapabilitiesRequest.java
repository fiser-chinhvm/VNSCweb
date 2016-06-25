/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.controller;

import java.util.ArrayList;
import java.util.List;
import org.apache.VNSCweb.model.Element;
import org.apache.VNSCweb.model.GetCapabilitie;

/**
 *
 * @author haonguyen
 */
public class CapabilitiesRequest {

    public CapabilitiesRequest() {
    }

    public List<GetCapabilitie> GetCapabilitiesRequest() {
        List<GetCapabilitie> m1 = new ArrayList<>();
        String[] version = {"2.0.2", "2.0.0", "1.0.7"};
        String[] ouputformat = {"application/xml"};
        Element a1 = new Element();
        a1.setVersion(version);
        Element a2 = new Element();
        a2.setOutputFormat(ouputformat);
        GetCapabilitie m2 = new GetCapabilitie(a1, a2);
        m1.add(m2);
        return m1;
    }
}
