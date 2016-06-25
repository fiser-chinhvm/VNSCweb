/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.model;

import java.util.Date;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import org.opengis.metadata.extent.GeographicBoundingBox;

/**
 *
 * @author haonguyen
 */
@XmlRootElement(name = "SummaryRecord")
public class SummaryRecord {

    private long id;
    private String title;
    private String type;
//    private String[] subject;
    private String format;
//    private String[] relation;
    private Date modified;
//    private String[] abstracts;
//    private String[] spatial;
//    private GeographicBoundingBox bbox;
    public SummaryRecord() {

    }

    public SummaryRecord(long identifier, String title, String type, String format,Date modified) {
        this.id = identifier;
        this.title = title;
        this.type = type;
        this.format = format;
//    this.BoundingBox = Bounding;
        this.modified = modified;

    }
    

//    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "subject")

//    @XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "relation")
//    
//    @XmlElement(namespace = "http://purl.org/dc/terms", name = "modified")
//    
//    @XmlElement(namespace = "http://purl.org/dc/terms", name = "abstract")
//    
//
//    @XmlElement(namespace = "http://purl.org/dc/terms", name = "spatial")
//    
//
//    @XmlElement(namespace = "http://www.opengis.net/ows", name = "BoundingBox")
@XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "identifier")
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

//    public String[] getSubject() {
//        return subject;
//    }
//
//    public void setSubject(String[] subject) {
//        this.subject = subject;
//    }
@XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "format")
    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

//    public String[] getRelation() {
//        return relation;
//    }
//
//    public void setRelation(String[] relation) {
//        this.relation = relation;
//    }
@XmlElement(namespace = "http://purl.org/dc/terms", name = "modified")
    public Date getModified() {
        return modified;
    }

    public void setModified(Date modified) {
        this.modified = modified;
    }

//    public String[] getAbstracts() {
//        return abstracts;
//    }
//
//    public void setAbstracts(String[] abstracts) {
//        this.abstracts = abstracts;
//    }

//    public String[] getSpatial() {
//        return spatial;
//    }
//
//    public void setSpatial(String[] spatial) {
//        this.spatial = spatial;
//    }

//    public GeographicBoundingBox getBbox() {
//        return bbox;
//    }
//
//    public void setBbox(GeographicBoundingBox bbox) {
//        this.bbox = bbox;
//    }
//    

}
