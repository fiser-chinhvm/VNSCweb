/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author haonguyen
 */
@XmlRootElement
public class ErrorMessage {
    private String errorRecord;
    private int errorCode;
    private String documentation;
    public ErrorMessage(){
        
    }
    public String getErrorRecord() {
        return errorRecord;
    }

    public void setErrorRecord(String errorRecord) {
        this.errorRecord = errorRecord;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }

    public ErrorMessage(String errorRecord, int errorCode, String documentation) {
        this.errorRecord = errorRecord;
        this.errorCode = errorCode;
        this.documentation = documentation;
    }
    
}
