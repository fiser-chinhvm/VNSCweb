/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSC.controllers;

import javax.activation.DataHandler;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 *
 * @author haonguyen
 */
@SOAPBinding(style = Style.RPC)
public interface FileDownload {

    public DataHandler downloadFile(String fileName);
}
