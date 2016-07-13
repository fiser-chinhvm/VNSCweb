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
@XmlSchema(
        xmlns = {
            @XmlNs(prefix = "csw", namespaceURI = "http://www.opengis.net/cat/csw/2.0.2"),
            @XmlNs(prefix = "ows", namespaceURI = "http://www.opengis.net/ows"),
            @XmlNs(prefix = "dc", namespaceURI = "http://purl.org/dc/elements/1.1/"),
            @XmlNs(prefix = "dct", namespaceURI = "http://purl.org/dc/terms")
        })
package org.apache.sis.services.csw;

import javax.xml.bind.annotation.XmlNs;
import javax.xml.bind.annotation.XmlSchema;
