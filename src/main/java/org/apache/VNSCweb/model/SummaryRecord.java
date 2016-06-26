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
@XmlRootElement(name = "SummaryRecord")
public class SummaryRecord {
    private long id;
    private String identifier;
    private String title;
    private String type;
    private String format;
    private String modified;
    private Element bbox;

    public SummaryRecord() {

    }
    public SummaryRecord(long id, String identifier,String title, String type, String format, String modified, Element bbox) {
        this.id = id;
        this.identifier=identifier;
        this.title = title;
        this.type = type;
        this.format = format;
        this.bbox = bbox;
        this.modified = modified;
    }
    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "id")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
@XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "identifier")
    public String getIdentifier() {
        return identifier;
    }

    public void setIdentifier(String identifier) {
        this.identifier = identifier;
    }
    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }
    @XmlElement(namespace = "http://purl.org/dc/terms", name = "modified")
    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    @XmlElement(namespace = "http://www.opengis.net/ows", name = "BoundingBox")
    public Element getBbox() {
        return bbox;
    }

    public void setBbox(Element bbox) {
        this.bbox = bbox;
    }
   
}
