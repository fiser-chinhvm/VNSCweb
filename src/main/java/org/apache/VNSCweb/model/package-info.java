/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
@XmlSchema(elementFormDefault=XmlNsForm.QUALIFIED,
        namespace="http://www.opengis.net/cat/csw/2.0.2",
        xmlns={
            @XmlNs(prefix="csw",namespaceURI="http://www.opengis.net/cat/csw/2.0.2"),
            @XmlNs(prefix="ows",namespaceURI="http://www.opengis.net/ows")
        })
package org.apache.VNSCweb.model;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlNsForm;
import javax.xml.bind.annotation.XmlSchema;

