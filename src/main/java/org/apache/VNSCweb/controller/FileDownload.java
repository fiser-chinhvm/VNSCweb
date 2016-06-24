/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.controller;

import java.io.File;
import javax.activation.DataHandler;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.ws.rs.core.Response;

/**
 *
 * @author haonguyen
 */
@SOAPBinding(style = Style.RPC)
public interface FileDownload {

    public DataHandler downloadFile(String fileName);
}
