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

    private String[] Identifier;
    private String[] title;
    private String type;
    private String[] subject;
    private String[] format;
    private String[] relation;
    private String[] modified;
    private String[] abstracts;
    private String[] spatial;
    private String[] BoundingBox;
    
@XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "identifier")
    public String[] getIdentifier() {
        return Identifier;
    }

    public void setIdentifier(String[] Identifier) {
        this.Identifier = Identifier;
    }
@XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "title")
    public String[] getTitle() {
        return title;
    }

    public void setTitle(String[] title) {
        this.title = title;
    }
@XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
@XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "subject")
    public String[] getSubject() {
        return subject;
    }

    public void setSubject(String[] subject) {
        this.subject = subject;
    }
@XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "format")
    public String[] getFormat() {
        return format;
    }

    public void setFormat(String[] format) {
        this.format = format;
    }
@XmlElement(namespace = "http://purl.org/dc/elements/1.1/", name = "relation")
    public String[] getRelation() {
        return relation;
    }

    public void setRelation(String[] relation) {
        this.relation = relation;
    }
@XmlElement(namespace = "http://purl.org/dc/terms", name = "modified")
    public String[] getModified() {
        return modified;
    }

    public void setModified(String[] modified) {
        this.modified = modified;
    }
@XmlElement(namespace = "http://purl.org/dc/terms", name = "abstract")
    public String[] getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String[] abstracts) {
        this.abstracts = abstracts;
    }
@XmlElement(namespace = "http://purl.org/dc/terms", name = "spatial")
    public String[] getSpatial() {
        return spatial;
    }

    public void setSpatial(String[] spatial) {
        this.spatial = spatial;
    }
@XmlElement(namespace = "http://www.opengis.net/ows", name = "BoundingBox")
    public String[] getBoundingBox() {
        return BoundingBox;
    }

    public void setBoundingBox(String[] BoundingBox) {
        this.BoundingBox = BoundingBox;
    }
}
