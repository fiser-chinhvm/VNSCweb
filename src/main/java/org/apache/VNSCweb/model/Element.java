/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author haonguyen
 */
@XmlRootElement(namespace = "http://www.opengis.net/ows")
public class Element {
    private String[] Version; 
   private String[] OutputFormat;
   private String parameterName;
@XmlElement(namespace = "http://www.opengis.net/ows",name= "Version")
    public String[] getVersion() {
        return Version;
    }

    public void setVersion(String[] Version) {
        this.Version = Version;
    }
@XmlElement(namespace = "http://www.opengis.net/ows",name= "OutputFormat")
    public String[] getOutputFormat() {
        return OutputFormat;
    }

    public void setOutputFormat(String[] OutputFormat) {
        this.OutputFormat = OutputFormat;
    }
@XmlElement(namespace = "http://www.opengis.net/ows",name= "parameterName")
    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

}
