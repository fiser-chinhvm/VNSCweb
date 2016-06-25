/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author haonguyen
 */
@XmlRootElement(name = "GetCapabilities", namespace = "http://www.opengis.net/cat/csw/2.0.2")
public class GetCapabilitie {

    private Element acceptversion;
    private Element acceptformat;

    public GetCapabilitie() {

    }

    public GetCapabilitie(Element acceptversion, Element acceptformat) {
        this.acceptversion = acceptversion;
        this.acceptformat = acceptformat;
    }

    @XmlElement(name = "AcceptVersion", namespace = "http://www.opengis.net/ows")
    public Element getAcceptversion() {
        return acceptversion;
    }

    public void setAcceptversion(Element acceptversion) {
        this.acceptversion = acceptversion;
    }

    @XmlElement(name = "AcceptFormat", namespace = "http://www.opengis.net/ows")
    public Element getAcceptformat() {
        return acceptformat;
    }

    public void setAcceptformat(Element acceptformat) {
        this.acceptformat = acceptformat;
    }

}
