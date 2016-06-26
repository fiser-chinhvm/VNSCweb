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
    private String west;
    private String north;
    private String south;
    private String east;

    @XmlElement(namespace = "http://www.opengis.net/ows", name = "westBoundLongitude")
    public String getWest() {
        return west;
    }

    public void setWest(String west) {
        this.west = west;
    }

    @XmlElement(namespace = "http://www.opengis.net/ows", name = "northBoundLongitude")
    public String getNorth() {
        return north;
    }

    public void setNorth(String north) {
        this.north = north;
    }

    @XmlElement(namespace = "http://www.opengis.net/ows", name = "southVersion")
    public String getSouth() {
        return south;
    }

    public void setSouth(String south) {
        this.south = south;
    }

    @XmlElement(namespace = "http://www.opengis.net/ows", name = "eastBoundLongitude")
    public String getEast() {
        return east;
    }

    public void setEast(String east) {
        this.east = east;
    }

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

    @XmlElement(namespace = "http://www.opengis.net/ows", name = "parameterName")
    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

}
