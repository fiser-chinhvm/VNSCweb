/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.model;

/**
 *
 * @author haonguyen
 */
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author haonguyen
 */
@XmlRootElement(namespace = "http://www.opengis.net/ows")
public class Capacibilities {

    private String[] Version;
    private String[] OutputFormat;
    

    @XmlElement(namespace = "http://www.opengis.net/ows", name = "Version")
    public String[] getVersion() {
        return Version;
    }

    public void setVersion(String[] Version) {
        this.Version = Version;
    }
    @XmlElement(namespace = "http://www.opengis.net/ows", name = "OutputFormat")
    public String[] getOutputFormat() {
        return OutputFormat;
    }

    public void setOutputFormat(String[] OutputFormat) {
        this.OutputFormat = OutputFormat;
    }

    

}

