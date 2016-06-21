/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author haonguyen
 */
public class GetCapabilities {

    private List<String> AcceptVersions() {
        List<String> Version = new ArrayList<>();
        Version.add("2.0.2");
        Version.add("2.0.0");
        Version.add("0.7.2");
        return Version;
    }
    private List<String> AcceptFormats() {
        List<String> OutputFormat = new ArrayList<>();
        OutputFormat.add("application/xml");
        
        return OutputFormat;
    }
}
