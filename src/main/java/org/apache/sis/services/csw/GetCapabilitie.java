/* 
 * Licensed to the Apache Software Foundation (ASF) under one or more 
 * contributor license agreements.  See the NOTICE file distributed with 
 * this work for additional information regarding copyright ownership. 
 * The ASF licenses this file to You under the Apache License, Version 2.0 
 * (the "License"); you may not use this file except in compliance with 
 * the License.  You may obtain a copy of the License at 
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0 
 * 
 * Unless required by applicable law or agreed to in writing, software 
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License. 
 */ 
package org.apache.sis.services.csw;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author haonguyen
 */
@XmlRootElement(name = "GetCapabilities", namespace = "http://www.opengis.net/cat/csw/2.0.2")
public class GetCapabilitie {
    /**
     * Accept version use to in service.
     */
    private Capacibilities acceptversion;
    /**
     * Accept format use to in service.
     */
    private Capacibilities acceptformat;

    public GetCapabilitie() {

    }

    /**
     * Creates get capabilities
     *
     * @param acceptversion
     * @param acceptformat
     */
    public GetCapabilitie(Capacibilities acceptversion, Capacibilities acceptformat) {
        this.acceptversion = acceptversion;
        this.acceptformat = acceptformat;
    }

    /**
     * Get Accept version
     *
     * @return Accept version use to in service.
     */
    @XmlElement(name = "AcceptVersion", namespace = "http://www.opengis.net/ows")
    public Capacibilities getAcceptversion() {
        return acceptversion;
    }

    /**
     * Set Accept version
     *
     * @param acceptversion
     */
    public void setAcceptversion(Capacibilities acceptversion) {
        this.acceptversion = acceptversion;
    }

    /**
     * Get Accept format
     *
     * @return
     */
    @XmlElement(name = "AcceptFormat", namespace = "http://www.opengis.net/ows")
    public Capacibilities getAcceptformat() {
        return acceptformat;
    }

    /**
     * Set Acceptformat
     *
     * @param acceptformat
     */
    public void setAcceptformat(Capacibilities acceptformat) {
        this.acceptformat = acceptformat;
    }

}
