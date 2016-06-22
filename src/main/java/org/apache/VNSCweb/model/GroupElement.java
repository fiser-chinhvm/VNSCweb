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
@XmlRootElement
public class GroupElement {
    private Element AcceptVersions;
    private Element AcceptFormats;
@XmlElement(namespace = "http://www.opengis.net/ows",name= "AcceptVersion")
    public Element getAcceptVersions() {
        return AcceptVersions;
    }
    public void setAcceptVersions(Element AcceptVersions) {
        this.AcceptVersions = AcceptVersions;
    }
@XmlElement(namespace = "http://www.opengis.net/ows",name= "AcceptFormat")
    public Element getAcceptFormats() {
        return AcceptFormats;
    }
    public void setAcceptFormats(Element AcceptFormats) {
        this.AcceptFormats = AcceptFormats;
    }
}
