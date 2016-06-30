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
@XmlRootElement(name = "GetCapabilities", namespace = "http://www.opengis.net/cat/csw/2.0.2")
public class GetCapabilitie {

    private Capacibilities acceptversion;
    private Capacibilities acceptformat;

    public GetCapabilitie() {

    }

    public GetCapabilitie(Capacibilities acceptversion, Capacibilities acceptformat) {
        this.acceptversion = acceptversion;
        this.acceptformat = acceptformat;
    }

    @XmlElement(name = "AcceptVersion", namespace = "http://www.opengis.net/ows")
    public Capacibilities getAcceptversion() {
        return acceptversion;
    }

    public void setAcceptversion(Capacibilities acceptversion) {
        this.acceptversion = acceptversion;
    }

    @XmlElement(name = "AcceptFormat", namespace = "http://www.opengis.net/ows")
    public Capacibilities getAcceptformat() {
        return acceptformat;
    }

    public void setAcceptformat(Capacibilities acceptformat) {
        this.acceptformat = acceptformat;
    }

}
