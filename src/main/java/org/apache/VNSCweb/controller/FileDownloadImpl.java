/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.apache.VNSCweb.controller;

import java.io.File;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.xml.ws.soap.MTOM;
import org.opengis.metadata.distribution.DataFile;

/**
 *
 * @author haonguyen
 */

public class FileDownloadImpl implements FileDownload {

    @Override
    public DataHandler downloadFile(String fileName) {
        FileDataSource dataSource = new FileDataSource("/home/haonguyen/data"+fileName);
	DataHandler fileDataHandler = new DataHandler(dataSource);
	   return fileDataHandler;
    }
}
